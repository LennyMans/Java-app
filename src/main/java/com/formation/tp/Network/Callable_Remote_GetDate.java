package com.formation.tp.Network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Callable_Remote_GetDate implements Callable<String> {

    // -- CONS
    String ref_String_Delimiter_Comma = ",";
    String ref_String_Delimiter_Equals = "=";
    String ref_String_Delimiter_Dot = "\\.";

    // -- VARS
    private String ref_String_IP;
    private int ref_int_Port;


    // -- CONSTRUCTOR ---------------------------------------------------------------

    public Callable_Remote_GetDate() {

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

        return new FutureTask<String>(new Callable_Remote_GetDate());

    }


    // -- INNER CALLBACK ---------------------------------------------------------

    private void load_Conf(){


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


                    // -- Log current line
                    //System.out.println(ref_String_Read_Line);

                    // Ici faire la vérif de l'ip puis du port en appelant les utils
                    ref_Array_String_Array_Frame_Splitted = ref_String_Read_Line.split(ref_String_Delimiter_Comma);

                    // Call verifyIp and verifyPort utils
                    // -- Commit si les verif sont ok
                    if ( verifyIp(ref_Array_String_Array_Frame_Splitted[0]) == true && verifyPort(ref_Array_String_Array_Frame_Splitted[1]) == true) {
                        ref_ArrayList_Data_Conf.add(ref_String_Read_Line);
                    }

                }
            }

            // -- Close buffer
            ref_Buffered_Reader_File_Conf.close();


        } catch (IOException ref_Exeception) {

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

                if(ref_String_LineRead.startsWith("Date")){

                    // -- Commit
                    ref_StringBuffer_Data.append(ref_String_LineRead).append("\n");

                    // -- Stop
                    break;

                }

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


    // -- UTILS ------------------------------------------------------------------

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

            if(u > 255  || u < 1) {

                ref_Boolean_isIpgood = false;
                break;

            }

        }

        return ref_Boolean_isIpgood;
    }

    public boolean verifyPort(String ref_String_Port_To_Check){
        // -- Cons
        boolean ref_Boolean_isPortGood;

        // -- Vars
        String [] ref_String_Array_Port; // ref_Array_String_KeyValue_Port[1]

        // Split
        ref_String_Array_Port = ref_String_Port_To_Check.split(ref_String_Delimiter_Equals);

        // -- Get Port
        int ref_String_Port_Number = Integer.parseInt(ref_String_Array_Port[1]);

        // -- Check
        ref_Boolean_isPortGood = true;

        if(ref_String_Port_Number < 1  || ref_String_Port_Number > 1023) {

            ref_Boolean_isPortGood = false;

            return false;

        } else {

            return ref_Boolean_isPortGood;
        }
    }


}
