package com.formation.tp.test;

import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

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
    public final static Path ref_Path_File_Conf = FileSystems.getDefault().getPath("PERSISTANCE", "CONNECTION8CONFIGURATION.txt");

    String ref_String_Delimiter_Comma = ",";
    String ref_String_Delimiter_Equals = "=";
    String ref_String_Delimiter_Dot = "\\.";

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


        TIPS

        // -- UTILS
        public boolean verifyIp(String ip){}
        public boolean verifyPort(String port){}

        */

        // -- Vars
        ArrayList<String> ref_ArrayList_Data_Conf = new ArrayList<>();

        // -- Vars - Declare path of the conf file
        String ref_String_File_Conf_Path = "./PERSISTENCE/CONNECTION_CONFIGURATION.txt";

        // -- Regex IP
        final String ref_String_Regex_Verification =
                "^KEY_IP=([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3}),KEY_PORT=([0-9]{1,5})$";

        // -- Init
        Pattern ref_Pattern  = Pattern.compile(ref_String_Regex_Verification);
        Matcher ref_Match = null;

        // -- Init conf file
        File ref_File_Conf = new File(ref_String_File_Conf_Path);

        // -- Init frame split
        String [] ref_Array_String_Array_Frame_Splitted; // KEY_IP=216.58.213.132,KEY_PORT=80

        // -- Init final frame split
        String [] ref_String_Array_Final_Frame;

        String ref_String_Ip_Frame_Final;
        String ref_String_Port_Frame_Final;

        String [] ref_String_Final_Data_IP;
        String [] ref_String_Final_Data_Port;

        try {
            // -- Init Buffred reader
            BufferedReader ref_Buffered_Reader_File_Conf = new BufferedReader(new FileReader(ref_File_Conf));

            // -- Declare variable string read line
            String ref_String_Read_Line;

            // -- Loop process
            while((ref_String_Read_Line = ref_Buffered_Reader_File_Conf.readLine()) != null) {

                // -- Verif if the line match with the pattern
                ref_Match = ref_Pattern.matcher(ref_String_Read_Line);

                if (ref_Match.find() == Boolean.TRUE) {
                    System.out.println("Regex okay");

                    // -- Log current line
                    //System.out.println(ref_String_Read_Line);

                    // Ici faire la vérif de l'ip puis du port en appelant les utils
                    ref_Array_String_Array_Frame_Splitted = ref_String_Read_Line.split(ref_String_Delimiter_Comma);

                    // Call verifyIp and verifyPort utils
                    // -- Commit si les verif sont ok
                    if ( verifyIp(ref_Array_String_Array_Frame_Splitted[0]) == true && verifyPort(ref_Array_String_Array_Frame_Splitted[1]) == true) {
                        ref_ArrayList_Data_Conf.add(ref_String_Read_Line);
                    }

                } else {
                    System.out.println("Regex : Nooope");
                }
            }

            // -- Close buffer
            ref_Buffered_Reader_File_Conf.close();

            // -- Log
            System.out.println("Data conf contient : " + ref_ArrayList_Data_Conf);

        } catch (IOException ref_Exeception) {
            System.out.println("ouuups, Il y a un problème");
            ref_Exeception.printStackTrace();
        }

        // -- Take first good conf line and split it
        ref_String_Array_Final_Frame = ref_ArrayList_Data_Conf.get(0).split(ref_String_Delimiter_Comma);

        // -- Get IP and port frame
        ref_String_Ip_Frame_Final = ref_String_Array_Final_Frame[0];
        ref_String_Port_Frame_Final = ref_String_Array_Final_Frame[1];

        // -- Split frame to get raw data
        ref_String_Final_Data_IP = ref_String_Ip_Frame_Final.split(ref_String_Delimiter_Equals);
        ref_String_Final_Data_Port = ref_String_Port_Frame_Final.split(ref_String_Delimiter_Equals);

        // -- Init final IP && port
        this.ref_String_IP = ref_String_Final_Data_IP[1];
        this.ref_int_Port = Integer.parseInt(ref_String_Final_Data_Port[1]);

    }

    private StringBuffer get_Data_From_Google(){

        Socket ref_Socket;
        StringBuffer ref_StringBuffer_Data = new StringBuffer();

        try {

            // -- INIT Socket
            ref_Socket = new Socket(ref_String_IP, ref_int_Port);

            // Init input stream
            InputStream ref_Input_Stream_Socket = ref_Socket.getInputStream();

            // Mise en place du filtre
            InputStreamReader ref_Input_Stream_Socket_Filter = new InputStreamReader(ref_Input_Stream_Socket);

            // ajout du filtre buffer
            BufferedReader ref_Buffered_Reader_Socket = new BufferedReader(ref_Input_Stream_Socket_Filter);

            // -- Envoie d'un random au serveur
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

    // -- UTILS
    public boolean verifyIp(String ref_String_Ip_To_Check){

        // -- Cons
        boolean ref_Boolean_isIpgood;

        // -- Init frame split
        String [] ref_Array_String_Array_Frame_Splitted; // KEY_IP=216.58.213.132,KEY_PORT=80

        // -- Init key/value split ip
        String ref_Array_String_KeyValue_Ip; // KEY_IP=216.58.213.132

        // -- Init split ip [xxx][xxx][xxx][xxx]
        String [] ref_Array_String_Ip_Splitted; // 216.58.213.132 soit ref_Array_String_KeyValue_Ip[1]

        // -- Init Raw IP
        String ref_String_Raw_Ip;

        // Init split ip numbers only
        String [] ref_Array_String_Raw_Ip_Split;

        // Init array of Ip numbers
        int [] ref_Array_Int_Ip;

        // -- Work
        ref_Array_String_Ip_Splitted = ref_String_Ip_To_Check.split(ref_String_Delimiter_Equals);

        ref_String_Raw_Ip = ref_Array_String_Ip_Splitted[1]; // 216.58.213.132 => l'offset 1

        // -- Log
        //System.out.println("Raw IP = " + ref_String_Raw_Ip);

        // -- Split all numbers of ip and remove dots
        ref_Array_String_Raw_Ip_Split = ref_String_Raw_Ip.split(ref_String_Delimiter_Dot); // 216.58.213.132 devient [216][58][213][132]

        // -- Log
        for (String ref_String_Unit : ref_Array_String_Raw_Ip_Split) {
            //System.out.println("Raw IP Split" + ref_String_Unit);
        }

        ref_Array_Int_Ip = Stream.of(ref_Array_String_Raw_Ip_Split).mapToInt((e) -> Integer.valueOf(e)).toArray();

        // -- Log
        for (int ref_Int_Unit : ref_Array_Int_Ip) {
            //System.out.println("Raw IP Split number" + ref_Int_Unit);
        }

        // -- Check
        ref_Boolean_isIpgood = true;

        for(int u :  ref_Array_Int_Ip){

            //System.out.println(u);

            if(u > 255  || u < 1) {

                //System.out.println("Noooope");
                ref_Boolean_isIpgood = false;
                break;

            } else {
                //System.out.println("IP OK !");
            }

        }
        System.out.println(ref_Boolean_isIpgood);
        return ref_Boolean_isIpgood;
    }

    public boolean verifyPort(String ref_String_Port_To_Check){
        // -- Cons
        boolean ref_Boolean_isPortGood;

        // -- Vars
        String [] ref_String_Array_Port; // ref_Array_String_KeyValue_Port[1]

        // Split
        ref_String_Array_Port = ref_String_Port_To_Check.split(ref_String_Delimiter_Equals);

        // -- Log
        for (String ref_String_Unit : ref_String_Array_Port) {
            //System.out.println("Port = " + ref_String_Unit);
        }

        // -- Get Port
        int ref_String_Port_Number = Integer.parseInt(ref_String_Array_Port[1]);

        // -- Check
        ref_Boolean_isPortGood = true;

        if(ref_String_Port_Number < 1  || ref_String_Port_Number > 1023) {

            //System.out.println("Noooope");
            ref_Boolean_isPortGood = false;

            return false;

        } else {

            //System.out.println("Port OK !");
            System.out.println(ref_Boolean_isPortGood);
            return ref_Boolean_isPortGood;
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