package com.formation.tp.keyboard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.UUID;

public class Keyboard_Context {

    // -- VARS
    private final ArrayDeque <String> ref_ArrayDeque_KeyboardEntry = new ArrayDeque<>();
    private final ThreadEngine_KeyboardContext ref_ThreadEngine_KeyboardContext = new ThreadEngine_KeyboardContext();

    // -- STATUS ENGINE
    private static final String ref_String_Status_Start = UUID.randomUUID().toString();
    private static final String ref_String_Status_Stop = UUID.randomUUID().toString();
    private static final String ref_String_Status_Kill = UUID.randomUUID().toString();

    private String ref_String_EngineStatus = Keyboard_Context.ref_String_Status_Stop;

    // -- INSTRUCTION
    public static final String ref_String_Instruction_Start = UUID.randomUUID().toString();
    public static final String ref_String_Instruction_Stop = UUID.randomUUID().toString();
    private String ref_String_InstructionHolder = null;

    // -- STREAM
    private InputStream ref_InputStream = System.in;
    private OutputStream ref_OutputStream = System.out;



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


    // -- OUTER CALLBACK -----------------------------------------------------------------

    public void setEngine(String ref_Instruction){

        ref_String_EngineStatus = ref_Instruction;

    }


    // -- CLASS --------------------------------------------------------------------------

    class ThreadEngine_KeyboardContext extends Thread{


     // -- CONSTRUCTOR --------------------

     public ThreadEngine_KeyboardContext(){


     }

     // -- OVERRIDE ----------------------

     @Override
     public void run(){

         // -- Work
         while(ref_String_EngineStatus.equals(ref_String_Status_Kill) != Boolean.TRUE){

             // -- Sleep
             try {  Thread.sleep(1000);
             } catch (InterruptedException ref_InterruptedException) { ref_InterruptedException.printStackTrace(); }

             System.err.println("I AM IDLE");


             // -- LOOP START STOP
             while(ref_String_EngineStatus.equals(ref_String_Status_Stop) != Boolean.TRUE){

                 // -- Sleep
                 try {  Thread.sleep(1000);
                 } catch (InterruptedException ref_InterruptedException) { ref_InterruptedException.printStackTrace(); }

                 // -- Work
                 String ref_String_Read = Keyboard_Context.this.readInputStream();

                 // -- Check
                 if(ref_String_Read == null) {

                     // -- Continue
                     continue;

                 }

                 if(ref_String_Read.equals("STOP")){

                     Keyboard_Context.this.writeOutputStream("Goodbye");
                     System.exit(0);

                 }

                 // -- Commit
                 Keyboard_Context.this.writeOutputStream("Hello, tu m'as envoyé:" + ref_String_Read);

             }



         }




     }



    }



}

