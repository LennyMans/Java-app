package com.formation.tp.Network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;


public class Callable_Remote_Color implements Callable<String> {

    // -- CONS
    public final static String ref_String_Command = "/getMyFuckingColor";

    // -- VARS
    private String ref_String_Hostname;
    private String ref_String_IP;
    private int ref_int_Port;
    private byte[] ref_Byte_Array;


    // -- CONSTRUCTOR ---------------------------------------------------------------

    public Callable_Remote_Color () {

        // -- Init
        this.load_Conf();

    }


    // -- IMPLENTATION  ------------------------------------------------------------

    @Override
    public String call() throws Exception {

        // -- Init & Work
        StringBuffer ref_StringBuffer_Data_From_Google = this.get_Data_From_Google();

        // -- Commit
        return ref_StringBuffer_Data_From_Google.toString();

    }

    public static FutureTask<String> get_FutureTask_Execute_Resquest () {

        return new FutureTask<String>(new Callable_Remote_Color());

    }


    // -- INNER CALLBACK  ---------------------------------------------------------

    private void load_Conf(){

    /*    this.ref_String_Hostname = "www.google.com";
        this.ref_String_IP = "216.58.213.132";
        this.ref_int_Port = 80;

        1) Recuperer le flux du fichier
        2) Lire chaque ligne et extraite que celle qui respect le format KEY_IP=<IP>,KEY_PORT=<port>
        3) On stockera les bonnes phrase dans une arrayList<String>
        4) On prendra le premier venu qui est bon
        5) On extrait les données
        6) ont set les variables  this.ref_String_IP  et this.ref_int_Port avec
        7) et la suite du programme suit sont cours
        */


    }

    private StringBuffer get_Data_From_Google(){

        Socket ref_Socket;
        StringBuffer ref_StringBuffer_Data = new StringBuffer();

        try {
            // -- 1
            // -- INIT Socket
            ref_Socket = new Socket(ref_String_IP, ref_int_Port);

            // Init input
            InputStream ref_Input_Stream_Socket = ref_Socket.getInputStream();
            // Mise en place du filtre
            InputStreamReader ref_Input_Stream_Socket_Filter = new InputStreamReader(ref_Input_Stream_Socket);
            // ajout du filtre buffred
            BufferedReader ref_Buffered_Reader_Socket = new BufferedReader(ref_Input_Stream_Socket_Filter);

            // -- Envoyé un random au serveur
            ref_Socket.getOutputStream().write("HELLO\n".getBytes(StandardCharsets.UTF_8));
            ref_Socket.getOutputStream().flush();

            String ref_String_LineRead;
            while(( ref_String_LineRead = ref_Buffered_Reader_Socket.readLine()) != null) {

                ref_StringBuffer_Data.append(ref_String_LineRead).append("\n");

            }

            // Fermeture du Buffer
            ref_Buffered_Reader_Socket.close();

            // Fermeture de la socket
            ref_Socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        // -- Return
        return ref_StringBuffer_Data;

    }

}
