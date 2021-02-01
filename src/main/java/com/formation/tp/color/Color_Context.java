package com.formation.tp.color;

import com.formation.tp.Network.Callable_Remote_Color;
import com.formation.tp.keyboard.Keyboard_Context;

import java.util.ArrayDeque;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Color_Context {

    // -- REF TICKET
    private Object ref_Object_Lock = new Object();
    private ArrayDeque<Color_Ticket> ref_ArrayDeque_Color_Ticket = new ArrayDeque<>();

    // -- REF REMOTE
    private String ref_String_Ip;
    private String ref_String_Port;

    // -- REF ENGINE
    private Color_Ticket_Engine ref_Color_Ticket_Engine = null;

    // -- STATUS ENGINE
    private static int ref_Int_TimeToSleep_Engine = 500;
        public static final String ref_String_Status_Start = UUID.randomUUID().toString();
        public static final String ref_String_Status_Stop = UUID.randomUUID().toString();
        public static final String ref_String_Status_Kill = UUID.randomUUID().toString();

    private String ref_String_EngineStatus = Color_Context.ref_String_Status_Stop;



    // -- CONSTRUCTOR ------------------------------------------------------------------------

    public Color_Context () {

        this.init();

    }



    // -- INIT -------------------------------------------------------------------------------

    public void init () {

        // -- Init them all
        this.init_Remote();
        this.init_Engine();

    }

    public void init_Remote () {

        // -- Inject
        this.ref_String_Ip = "127.0.0.1";
        this.ref_String_Port = "6666";

    }

    public void init_Engine () {

        // -- Inject
        this.ref_Color_Ticket_Engine = new Color_Ticket_Engine();
        this.ref_Color_Ticket_Engine.start();

    }



    // -- OUTER CALLBACK ---------------------------------------------------------------------

    public Color_Ticket get_ColorTicket () {

        // -- Init
        final Color_Ticket ref_Color_Ticket = new Color_Ticket();

        // -- Work
        synchronized (this.ref_Object_Lock) {

            ref_ArrayDeque_Color_Ticket.add(ref_Color_Ticket);

        }

        // -- Commit
        return ref_Color_Ticket;

    }

    public void setEngine(String ref_Instruction){

        ref_String_EngineStatus = ref_Instruction;

    }



    // -- INNER CALLBACK ---------------------------------------------------------------------

    protected Color_Ticket poll_ColorTicket () {

        synchronized (this.ref_Object_Lock) {

            return ref_ArrayDeque_Color_Ticket.poll();

        }
    }



    // -- UTILS ------------------------------------------------------------------------------

    public void sleepMyFriend(int ref_Int_TimeToSleep){

        try {

            Thread.sleep(ref_Int_TimeToSleep);

        } catch (InterruptedException ref_InterruptedException) {

            ref_InterruptedException.printStackTrace();
        }

    }



    // -- ENGINE -----------------------------------------------------------------------------

    private class Color_Ticket_Engine extends Thread {

        // -- VARS ENGINE
        private int ref_Int_TimeToSleep_Engine = 500;


        // -- IMPLEMENTATION ------------------------------------------------------------------

        public void run(){

            // -- Work
            while(ref_String_EngineStatus.equals(ref_String_Status_Kill) != Boolean.TRUE){

                // -- Sleep
               Color_Context.this.sleepMyFriend(ref_Int_TimeToSleep_Engine);


                    // -- LOOP START STOP
                    while(ref_String_EngineStatus.equals(ref_String_Status_Stop) != Boolean.TRUE){

                                // -- Retrieve
                                Color_Ticket ref_Color_Ticket_Unit = Color_Context.this.poll_ColorTicket();

                                // -- Check && Set
                                if (ref_Color_Ticket_Unit != null) {

                                    new Thread(new Runnable_Build_Ticket(ref_Color_Ticket_Unit)).start();

                                }

                                // -- Sleep
                                this.sleep();

                    }

                // -- Sleep
                this.sleep();

            }

        }


        // -- UTILS ----------------------------------------------------------------------------

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


    }



    // -- CLASS ------------------------------------------------------------------------------

    private class Runnable_Build_Ticket implements Runnable {

        // -- VARS
        private final Color_Ticket ref_Color_Ticket;


        // -- CONSTRUCTOR ---------------------------------------------------------------------

        public Runnable_Build_Ticket (Color_Ticket ref_Color_Ticket) {

            this.ref_Color_Ticket = ref_Color_Ticket;

        }


        // -- IMPLEMENTATION ---------------------------------------------------------------------

        @Override
        public void run() {

            // -- Set
            ref_Color_Ticket.setValue(Color_Ticket.ref_String_Key_Status, Color_Ticket.ref_String_Value_Status_Treated);

            // -- Create
            FutureTask<String> ref_Future_Task_Unit = Callable_Remote_Color.get_FutureTask_Execute_Resquest();

            // -- Start
            try {

                // -- Execute FutureTask
                new Thread(ref_Future_Task_Unit).start();

                // -- Get
                String ref_String_Color = ref_Future_Task_Unit.get();

                // -- Check
                String ref_String_Processing_Result = (ref_String_Color == null)
                        ? Color_Ticket.ref_String_Value_Status_Refused
                        : Color_Ticket.ref_String_Value_Status_Complete;

                String ref_String_Processing_Refused_Reason = (ref_String_Processing_Result.equals(Color_Ticket.ref_String_Value_Status_Refused))
                        ? Color_Ticket.ref_String_Value_Refused_Reason_Default
                        : null;

                // -- Set
                switch (ref_String_Processing_Result) {

                    case Color_Ticket.ref_String_Value_Status_Complete:

                        // -- Set
                        this.ref_Color_Ticket.setValue(Color_Ticket.ref_String_Key_Color, ref_String_Color);
                        this.ref_Color_Ticket.setValue(Color_Ticket.ref_String_Key_Status, Color_Ticket.ref_String_Value_Status_Complete);

                        break;

                    case Color_Ticket.ref_String_Value_Status_Refused:

                        // -- Set
                        this.ref_Color_Ticket.setValue(Color_Ticket.ref_String_Key_Refused_Reason, ref_String_Processing_Refused_Reason);
                        this.ref_Color_Ticket.setValue(Color_Ticket.ref_String_Key_Status, Color_Ticket.ref_String_Value_Status_Refused);

                        break;
                }

            } catch (InterruptedException | ExecutionException ref_Exception) {

                ref_Exception.printStackTrace();

            }

        }
    }


}
