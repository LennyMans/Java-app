package com.formation.tp.app;

import com.formation.tp.Network.Callable_Remote_GetDate;
import com.formation.tp.UserInterface.UserInterface_Context;
import com.formation.tp.date.Date_Context;
import com.formation.tp.keyboard.Keyboard_Context;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import java.util.concurrent.FutureTask;


public class Master_Context {

    // -- CONTEXT
    public static Master_Context ref_MasterContext;

    public static Keyboard_Context ref_KeyBoardContext;
    public static Date_Context ref_ColorContext;
    public static UserInterface_Context ref_UserInterface_Context;



    // -- CONSTRUCTOR ------------------------------

    public Master_Context(){

        // -- Auto inject
        Master_Context.ref_MasterContext = this;

        // -- Inject engines
        Master_Context.ref_KeyBoardContext = new Keyboard_Context();
        Master_Context.ref_ColorContext = new Date_Context();
        Master_Context. ref_UserInterface_Context = new UserInterface_Context();

        // -- Start engines
        Master_Context.ref_KeyBoardContext.setEngine(Keyboard_Context.ref_String_Status_Kill);
        Master_Context.ref_ColorContext.setEngine(Date_Context.ref_String_Status_Start);

    }

    // -- MAIN -------------------------------------

    public static void main (String [] args){

            // -- Funny start
            new Master_Context();

            // -- Call
            serverCall();
    }


    // -- CALLBACKS --------------------------------

    public static void serverCall() {

        // -- Retrieve
        FutureTask<String> ref_Future_Task = Callable_Remote_GetDate.get_FutureTask_Execute_Resquest();

        // -- Execute
        Executors.defaultThreadFactory()
                .newThread(ref_Future_Task)
                        .start();

        // -- Retrieve
        String ref_String_Date = null;

        try {

            ref_String_Date = ref_Future_Task.get();

        } catch (InterruptedException | ExecutionException ref_Exception) {

            ref_Exception.printStackTrace();
        }

        // -- Output
        System.out.println("Date Collected From Goole=" +  ((ref_String_Date != null )? ref_String_Date:"Unable to retrieve a date"));

    }

}
