package com.formation.tp.keyboard;

import com.formation.tp.color.Color_Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Keyboard_Context {

    // -- CONS
    private static int ref_Int_TimeToSleep_Engine = 500;

    // -- VARS
    final private static Object ref_Object_Lock_ArrayDeque_Input = new Object();
    final static private int ref_Int_MaxSize_ArrayDeque = 5;
    private final ArrayDeque <String> ref_ArrayDeque_KeyboardEntry = new ArrayDeque<>();
    private final ThreadEngine_KeyboardContext ref_ThreadEngine_KeyboardContext = new ThreadEngine_KeyboardContext();

    // -- STATUS ENGINE
    private static final String ref_String_Status_Start = UUID.randomUUID().toString();
    private static final String ref_String_Status_Stop = UUID.randomUUID().toString();
    private static final String ref_String_Status_Kill = UUID.randomUUID().toString();

    private String ref_String_EngineStatus = Keyboard_Context.ref_String_Status_Stop;

    // -- STREAM
    private InputStream ref_InputStream = System.in;
    private OutputStream ref_OutputStream = System.out;

    // -- PROTOCOLE SPECIFICATION
    private static Pattern ref_Pattern_InputVerification
                = Pattern.compile("^CustomerFirstName=([A-Z]{1}[a-z]{1,20}),CustomerName=([A-Z]{1}[a-z]{1,20})$");

    private static String ref_String_Response_Ok = "Ack receive";
    private static String ref_String_Response_Nok_WrongSentence = "Ack error, wrong sentence";
    private static String ref_String_Response_Nok_Busy = "Ack error - To busy, please retry again";



    // -- CONSTRUCTOR --------------------------------------------------------------------

    public Keyboard_Context (){

        // -- Start engines thread
        this.ref_ThreadEngine_KeyboardContext.start();

    }



    // -- INNER CALLBACK -----------------------------------------------------------------

    private String readInputStream(){

        // -- Init
        String ref_String_Input = null;

        // -- Work
        byte ref_Array_Byte [] = new byte [128];
        int ref_Int_ByteRead = -1;

        try {


        // -- Check
        if(ref_InputStream.available() == 0){

            // -- return nothing
            return null;

        }


            while ((ref_Int_ByteRead = ref_InputStream.read(ref_Array_Byte)) != -1){

                // -- Extract
                String ref_String_Receive = new String(ref_Array_Byte, 0, (ref_Int_ByteRead - 1));

                // -- Push
                ref_ArrayDeque_KeyboardEntry.add(ref_String_Receive);

                // -- Commit
                ref_String_Input = ref_String_Receive;

                // -- Break
                break;

            }

        } catch (IOException ref_IOException) {

            ref_IOException.printStackTrace();
        }

        // -- Commit
        return ref_String_Input;

    }

    private void writeOutputStream(String ref_StringToWrite){

        try {

            ref_OutputStream.write(ref_StringToWrite.getBytes());

        } catch (IOException ref_IOException) {
            ref_IOException.printStackTrace();
        }

    }

    private boolean verifyInput(String ref_String_ToCheck){

        // -- Check & commit
        return ref_Pattern_InputVerification.matcher(ref_String_ToCheck).find();

    }

    private void add_UserInput(String ref_String_Read){

        // -- Sync
        synchronized (ref_Object_Lock_ArrayDeque_Input){

            // -- Commit
            ref_ArrayDeque_KeyboardEntry.add(ref_String_Read);

        }

    }


    // -- OUTER CALLBACK -----------------------------------------------------------------

    public void setEngine(String ref_Instruction){

        ref_String_EngineStatus = ref_Instruction;

    }

    public String get_UserInput(){

        // -- Init
        String ref_String_ToCommit = null;

        // -- Sync
        synchronized (ref_Object_Lock_ArrayDeque_Input){

            // -- Work
            ref_String_ToCommit = ref_ArrayDeque_KeyboardEntry.poll();

        }

        // -- Commit
        return ref_String_ToCommit;

    }


    // -- UTILITARY ----------------------------------------------------------------------

    private boolean sizeStack_NotReach(){

        return Keyboard_Context.this.ref_ArrayDeque_KeyboardEntry.size() < Keyboard_Context.ref_Int_MaxSize_ArrayDeque;

    };

    public void sleep () {

        this.sleep(ref_Int_TimeToSleep_Engine);

    }

    public void sleep (int ref_Int_Time_To_Sleep) {

        try {

            Thread.sleep(ref_Int_Time_To_Sleep);

        } catch (InterruptedException ref_InterrupedException) {

            ref_InterrupedException.printStackTrace();

        }

    }



    // -- CLASS --------------------------------------------------------------------------

    class ThreadEngine_KeyboardContext extends Thread{


     // -- OVERRIDE ----------------------

     @Override
     public void run(){

         // -- Work
         while(ref_String_EngineStatus.equals(ref_String_Status_Kill) != Boolean.TRUE){

             // -- Sleep
            Keyboard_Context.this.sleep(ref_Int_TimeToSleep_Engine);

             // -- Log
             System.err.println("ThreadEngine_KeyboardContext - IDLE");

             // -- LOOP START STOP
             while(ref_String_EngineStatus.equals(ref_String_Status_Stop) != Boolean.TRUE){

                 // -- Sleep
                 Keyboard_Context.this.sleep(ref_Int_TimeToSleep_Engine);

                 // -- Work
                 String ref_String_Read = Keyboard_Context.this.readInputStream();

                 // -- Check
                 if(ref_String_Read == null) { continue; }


                 // -- Build response
                 String ref_String_ResponseToCommit = Keyboard_Context.ref_String_Response_Ok;

                 String [] ref_Array_String_Response_Error_ToCommit
                        = { ref_String_Response_Nok_WrongSentence
                            , ref_String_Response_Nok_Busy};


                 // -- Boolean for verif
                 boolean ref_Boolean_Global_Verification = Boolean.TRUE;

                 int ref_Int_Offset_SentenceVerification = 0;
                 int ref_Int_Offset_BusyVerification = 1;

                 boolean [] ref_Array_Boolean_IsOk = new boolean[2];
                     ref_Array_Boolean_IsOk[ref_Int_Offset_SentenceVerification] = (Keyboard_Context.this.verifyInput(ref_String_Read));
                     ref_Array_Boolean_IsOk[ref_Int_Offset_BusyVerification] = (Keyboard_Context.this.sizeStack_NotReach());

                 // -- Set global
                 for(boolean ref_Boolean_Unit : ref_Array_Boolean_IsOk){

                     ref_Boolean_Global_Verification &= ref_Boolean_Unit;

                 }


                 // -- Check & commit
                 if(ref_Boolean_Global_Verification == Boolean.FALSE){

                    // -- Loop over test
                   for(int ref_Int_Offset_A = 0; ref_Int_Offset_A < ref_Array_Boolean_IsOk.length; ref_Int_Offset_A++){

                       // -- Check
                       if(ref_Array_Boolean_IsOk[ref_Int_Offset_A] != Boolean.TRUE){

                           // -- Set
                           ref_String_ResponseToCommit = ref_Array_String_Response_Error_ToCommit[ref_Int_Offset_A];

                           // -- Stop
                           break;

                       }

                   }

                 }



                 // -- Commit response to remote end
                 Keyboard_Context.this.writeOutputStream(ref_String_ResponseToCommit);



                 // -- Check & add stack
                 if(ref_String_ResponseToCommit.equals(Keyboard_Context.ref_String_Response_Ok)){

                     // -- Commit
                     Keyboard_Context.this.add_UserInput(ref_String_Read);

                 }

             }


         }

     }


    }


}

