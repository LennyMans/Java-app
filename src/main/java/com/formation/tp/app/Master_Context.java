package com.formation.tp.app;

import com.formation.tp.color.Color_Context;
import com.formation.tp.keyboard.Keyboard_Context;

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
        this.ref_KeyBoardContext = new Keyboard_Context();
        this.ref_ColorContext = new Color_Context();

        // -- Start engines
        this.ref_KeyBoardContext.setEngine(Keyboard_Context.ref_String_Instruction_Start);
        //this.ref_ColorContext.setEngine(Color_Context)

    }


    // -- MAIN -------------------------------------

    public static void main (String [] args){

            // -- Funny start
            new Master_Context();

    }

}
