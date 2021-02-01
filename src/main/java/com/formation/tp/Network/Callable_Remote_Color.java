package com.formation.tp.Network;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Callable_Remote_Color implements Callable<String> {

    @Override
    public String call() throws Exception {

        // IMAGINONS APPL RESEAU OU BDD
        String [] ref_Array_String = {"black","yellow","green","purple","ping","red"};
        int ref_Int_Random = (int)(Math.random() * ref_Array_String.length);

        // -- Commit
        return ref_Array_String[ref_Int_Random];

    }


    public static FutureTask<String> get_FutureTask_Execute_Resquest () {

        return new FutureTask<String>(new Callable_Remote_Color());

    }
}
