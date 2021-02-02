package com.formation.tp.test;

import java.net.*;
import java.io.*;

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

public class TestSocket {

    // -- CONS
    public final static String ref_String_Command = "/getMyFuckingColor";

    // -- VARS
    private String ref_String_Hostname;
    private String ref_String_IP;
    private int ref_int_Port;
    private byte[] ref_Byte_Array;


    // -- CONSTRUCTOR ------------------------------------------------------------------------------

    public TestSocket() {

        this.ref_String_Hostname = "www.google.fr";
        this.ref_String_IP = "216.58.213.132";
        this.ref_int_Port = 80;

    }

    public void test(){

        Socket ref_Socket;
        DataInputStream userInput;
        PrintStream theOutputStream;

        try {
            // -- 1

            ref_Socket = new Socket(ref_String_IP, ref_int_Port);

            // Lire des données
            BufferedReader ref_Socket_Input = new BufferedReader(new InputStreamReader(ref_Socket.getInputStream()));

            // Envoyer des données
            PrintStream ref_Socket_Output = new PrintStream(ref_Socket.getOutputStream());


            ref_Socket_Output.println(args[1]);
            System.out.println(ref_Socket_Input.readLine());

            // -- 2

            // Envoie de la commande sur le flux output
            ObjectOutputStream ref_ObjectOutputStream = new ObjectOutputStream();
            ref_ObjectOutputStream.writeObject(ref_String_Command);

            // -- 3

            // On simule la réponse du flux ici
            String ref_String_Fake_Result = "Black";
            ref_Byte_Array.add(ref_String_Fake_Result);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    // -- MAIN -------------------------------------------------------------------------------------

    public static void main(String[] args) {

        TestSocket ref_TestSocket = new TestSocket();
        ref_TestSocket.test();

    }



}