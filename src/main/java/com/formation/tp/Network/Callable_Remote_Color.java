package com.formation.tp.Network;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Callable_Remote_Color implements Callable<String> {

    // -- VARS
    public static String ref_String_IP = "";
    private static int ref_String_Port;

    private Socket ref_Socket;

    // -- CONSTRUCTOR

    public Callable_Remote_Color () {

        // -- Init
        ref_String_IP = "216.58.213.132";
        ref_String_Port = 80;

        System.out.println("weeesh");
    }

    @Override
    public String call() throws Exception {

        // -- Init
        String ref_String_To_Return = null;

        System.out.println("roooh");

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

        try {
            ref_Socket = new Socket(ref_String_IP, ref_String_Port);

            BufferedReader ref_Socket_Input = new BufferedReader(new InputStreamReader(ref_Socket.getInputStream()));
            PrintStream ref_Socket_Output = new PrintStream(ref_Socket.getOutputStream());

            System.out.println(ref_Socket_Input.readLine());

        } catch (Exception ref_Exception) {
            ref_Exception.printStackTrace();
        }


         */



        // -- Commit
        System.out.println(ref_String_To_Return);
        return ref_String_To_Return;

    }


    public static FutureTask<String> get_FutureTask_Execute_Resquest () {

        return new FutureTask<String>(new Callable_Remote_Color());

    }
}
