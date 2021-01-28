package com.formation.tp.color;

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

    }


    // -- OUTER CALLBACK ---------------------------------------------------------------------

    public void start_Context_Engine () {

        // -- Start
        this.ref_Color_Ticket_Engine.start();

    }

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


    // -- INNER CALLBACK ---------------------------------------------------------------------

    protected Color_Ticket poll_ColorTicket () {

        synchronized (this.ref_Object_Lock) {

            return ref_ArrayDeque_Color_Ticket.poll();

        }
    }

    protected FutureTask<String> get_FutureTask_Execute_Resquest () {

        return new FutureTask<String>(new Callable_Remote_Color());

    }


    // -- ENGINE -----------------------------------------------------------------------------

    private class Color_Ticket_Engine extends Thread {

        // -- VARS
        public final String ref_String_Engine_State_Enabled = UUID.randomUUID().toString();
        public final String ref_String_Engine_State_Disabled = UUID.randomUUID().toString();
        public String ref_String_Engine_State;

        public final String ref_String_Engine_State_Alive = UUID.randomUUID().toString();
        public final String ref_String_Engine_State_Dead = UUID.randomUUID().toString();
        public String ref_String_Engine_State_Life;

        // -- VARS ENGINE
        private int ref_Time_To_Sleep = 500;


        // -- CONSTRUCTOR ---------------------------------------------------------------------

        public Color_Ticket_Engine () {

            this.init();

        }


        // -- INIT ----------------------------------------------------------------------------

        public void init () {


            // -- Set engine
            ref_String_Engine_State_Life = ref_String_Engine_State_Alive;
            ref_String_Engine_State = ref_String_Engine_State_Enabled;
        }


        // -- IMPLEMENTATION ------------------------------------------------------------------

        @Override
        public void run () {

            // -- Life Loop
            while (ref_String_Engine_State_Life.equals(ref_String_Engine_State_Alive)) {

                // -- State Loop
                while (ref_String_Engine_State.equals(ref_String_Engine_State_Enabled)) {

                    // -- Retrieve
                    Color_Ticket ref_Color_Ticket_Unit = Color_Context.this.get_ColorTicket();

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

            this.sleep(ref_Time_To_Sleep);

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

            // -- Create
            FutureTask<String> ref_Future_Task_Unit = Color_Context.this.get_FutureTask_Execute_Resquest();

            // -- Start
            try {

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

    private class Callable_Remote_Color implements Callable<String> {



        @Override
        public String call() throws Exception {
            return "black";
        }
    }

}
