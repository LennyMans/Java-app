package com.formation.tp.app;

import com.formation.tp.color.Color_Context;
import com.formation.tp.color.Color_Ticket;
import com.formation.tp.keyboard.Keyboard_Context;
import com.formation.tp.test.Test_Session_10;

public class Master_Context {

    // -- CONTEXT
    public static Master_Context ref_MasterContext;

    public static Keyboard_Context ref_KeyBoardContext;
    public static Color_Context ref_ColorContext;



    // -- CONSTRUCTOR ------------------------------

    public Master_Context(){

        // -- Auto inject
        Master_Context.ref_MasterContext = this;

        // -- Inject engines
        Master_Context.ref_KeyBoardContext = new Keyboard_Context();
        Master_Context.ref_ColorContext = new Color_Context();

        // -- Start engines
        Master_Context.ref_KeyBoardContext.setEngine(Keyboard_Context.ref_String_Status_Start);
        Master_Context.ref_ColorContext.setEngine(Color_Context.ref_String_Status_Start);

    }


    // -- MAIN -------------------------------------

    public static void main (String [] args){

            // -- Funny start
            new Master_Context();

            // -- Faire no test
            Master_Context.test();
    }

    // -- TEST -------------------------------------

    public static void test(){

        new Test_Session_10().test();

    }
}
