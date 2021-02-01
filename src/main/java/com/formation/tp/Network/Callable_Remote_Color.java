package com.formation.tp.Network;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Callable_Remote_Color implements Callable<String> {

    @Override
    public String call() throws Exception {

        // -- Init
        String ref_String_To_Return = null;

        // -- Work
        /*
       1)  Connection au serveur suivant:
         ip 216.58.213.132
         port 80

        2) envoyé via le flux ouput la commande "/getMyFuckingColor"

        3) stocké la reponse du flux input dans un tableau de byte

        4 ) convertir en tronquant et gardant que les 30 premiers byte

        5) les trandormer en string

        6) renvoyé la string

         */



        // -- Commit
        return ref_String_To_Return;

    }


    public static FutureTask<String> get_FutureTask_Execute_Resquest () {

        return new FutureTask<String>(new Callable_Remote_Color());

    }
}
