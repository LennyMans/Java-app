package com.formation.tp.color;

import java.util.ArrayDeque;

public class Color_Context {

    // -- REF TICKET
    private Object ref_Object_Lock = new Object();
    private ArrayDeque<Color_Ticket> ref_ArrayDeque_Color_Ticket = new ArrayDeque<>();

    // -- REF REMOTE
    private String ref_String_Ip = "127.0.0.1";
    private String ref_String_Port = "6666";


    // -- CONSTRUCTOR ------------------------------------------------------------------------

    public Color_Context () {

    }

    // -- OUTER CALLBACK ---------------------------------------------------------------------

    public Color_Ticket get_ColorTicket () {
        return null;
    }


    // -- INNER CALLBACK ---------------------------------------------------------------------



}
