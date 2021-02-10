package com.formation.tp.app;


import com.formation.tp.Perrsistence.Persistence_Context;
import com.formation.tp.UserInterface.UserInterface_Context;
import com.formation.tp.date.Date_Context;
import com.formation.tp.keyboard.Keyboard_Context;



public class Master_Context {

    // -- CONTEXT
    public static Master_Context ref_MasterContext;

    public static Keyboard_Context ref_KeyBoardContext;
    public static Date_Context ref_ColorContext;
    public static UserInterface_Context ref_UserInterface_Context;
    public static Persistence_Context ref_Persistence_Context;


    // -- CONSTRUCTOR ------------------------------

    public Master_Context(){

        // -- Auto inject
        Master_Context.ref_MasterContext = this;

        // -- Inject engines
        Master_Context.ref_KeyBoardContext = new Keyboard_Context();
        Master_Context.ref_ColorContext = new Date_Context();
        Master_Context.ref_UserInterface_Context = new UserInterface_Context();
        Master_Context.ref_Persistence_Context = new Persistence_Context();


        // -- Start engines
        Master_Context.ref_KeyBoardContext.setEngine(Keyboard_Context.ref_String_Status_Kill);
        Master_Context.ref_ColorContext.setEngine(Date_Context.ref_String_Status_Start);

    }

    // -- MAIN -------------------------------------

    public static void main (String [] args){

            // -- Funny start
            new Master_Context();

    }



}
