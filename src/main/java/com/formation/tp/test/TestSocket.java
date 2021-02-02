package com.formation.tp.test;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;

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

        // -- Init
        this.load_Conf();

    }


    // -- INNER CALLBACK ---------------------------------------------------------------------------

    private void load_Conf(){

    /*  this.ref_String_Hostname = "www.google.com";
        this.ref_String_IP = "216.58.213.132";
        this.ref_int_Port = 80;

        1) Recuperer le flux du fichier suivante CONNECTION_CONFIGURATION.txt
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

    private void persistInFile(StringBuffer ref_String_Buffer){

        /*
        1) Crée le repertoire a la racine du projet
        2) Crée me fichier ds le repertoire
        3) Ecrire les donnée reçu de google dedans

         */

        // -- Cons
        String ref_String_File_ToPersist = "./PERSISTENCE/dataFromGoogle.txt";


        // -- Init
        File ref_File = new File(ref_String_File_ToPersist);

        // Gestion de l'arboresence
        ref_File.getParentFile().mkdirs();

        // Create file
        try {
            ref_File.createNewFile();
        } catch (IOException ref_Exception) {
            ref_Exception.printStackTrace();
        }

        // Write data from google in the file
        try {
            FileWriter ref_File_Writer = new FileWriter(ref_File, Boolean.TRUE);
            ref_File_Writer.write(ref_String_Buffer.toString());
            ref_File_Writer.flush();

        } catch (IOException ref_Exception) {
            ref_Exception.printStackTrace();
        }


    }


    // -- MAIN -------------------------------------------------------------------------------------

    public static void main(String[] args) {

        // -- Init class
        TestSocket ref_TestSocket = new TestSocket();

        // -- Retrieve data from google
        StringBuffer ref_StringBuffer_Data_From_Google = ref_TestSocket.get_Data_From_Google();

        // -- Persist data in file system
        ref_TestSocket.persistInFile(ref_StringBuffer_Data_From_Google);

    }


}