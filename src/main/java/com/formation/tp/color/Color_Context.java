package com.formation.tp.color;

import java.util.ArrayDeque;
import java.util.UUID;

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

    private Color_Ticket poll_ColorTicket () {

        synchronized (this.ref_Object_Lock) {

            return ref_ArrayDeque_Color_Ticket.poll();

        }
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

            while (ref_String_Engine_State_Life.equals(ref_String_Engine_State_Alive)) {


            }

        }



    }







}
