package com.formation.tp.test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Test_Explication_Regex {


    public static void main (String [] ref_Array_String_Arg){

    test4();

    }

    public static void test1(){

        // -- INIT DATA
        String ref_ref_String_Input = "WINNER-JesSuisUneLibellule-fWINNER_ViveLensWINNER54654654";
        String ref_String_Regex ="WINNER";
        StringBuilder ref_StringBuilder = new StringBuilder();

        // -- INIT REGEX
        Pattern ref_Pattern = Pattern.compile(ref_String_Regex);
        Matcher ref_Matcher = ref_Pattern.matcher(ref_ref_String_Input);

        // -- INIT LIST
        ArrayList<int[]> ref_ArrayList_PositionToRemove = new ArrayList<>();
        ArrayList<int[]> ref_ArrayList_PositionToCopy = new ArrayList<>();

        // -- APPLY REGEX AND EXTRACT
        while(ref_Matcher.find() == Boolean.TRUE){

            String ref_Find = ref_Matcher.group();
            int ref_Offset_Start = ref_Matcher.start();
            int ref_Offset_End = ref_Matcher.end();

            ref_ArrayList_PositionToRemove.add(new int[]{ref_Offset_Start, ref_Offset_End});

            System.out.println("MATCH EXTRACT=" + ref_Find + " start at:" + ref_Offset_Start + ", end at:" + ref_Offset_End);

        }

        // -- DEDUCE POSITION TO COPY
        for(int ref_Int_A = 0; ref_Int_A < ref_ArrayList_PositionToRemove.size(); ref_Int_A++){

            // -- RETRIEVE
            int [] ref_Array_ToRemoveUnit = ref_ArrayList_PositionToRemove.get(ref_Int_A);

            // -- INIT
            int[] ref_Array_Unit = new int[2];


            // -- CASE PREMIER MATCH
            if(ref_Int_A == 0){

                ref_Array_Unit[0] = ref_Array_ToRemoveUnit[0] == 0 ? ref_Array_ToRemoveUnit[1] : 0;
                ref_Array_Unit[1] = ref_ArrayList_PositionToRemove.get(ref_Int_A+1)[0];

            // -- CASE DERNIER MATCH
            }else if (ref_Int_A == ref_ArrayList_PositionToRemove.size() -1){

                ref_Array_Unit[0] = ref_Array_ToRemoveUnit[1];
                ref_Array_Unit[1] = ref_ref_String_Input.length();

            // -- CASE MATCH ENTRE DEBUT ET FIN DE MATCH
            }else{

                ref_Array_Unit[0] =  ref_Array_ToRemoveUnit[1];
                ref_Array_Unit[1] = ref_ArrayList_PositionToRemove.get(ref_Int_A+1)[0];

            }

            ref_ArrayList_PositionToCopy.add(ref_Array_Unit);

        }


        // -- COPY ALL THE POSITION
        for(int[] ref_Int_Unit : ref_ArrayList_PositionToCopy){

            ref_StringBuilder.append(ref_ref_String_Input.substring(ref_Int_Unit[0], ref_Int_Unit[1]));

        }


        // -- COMMIT
        System.out.println("STRING REDUCED=" + ref_StringBuilder.toString());



    }

    public static void test2(){


       // -- INIT DATA
        String ref_ref_String_Input = "WINNER-JesSuisUneLibellule-fWINNER_ViveLensWINNER54654654";
        String ref_String_Regex ="WINNER";
        StringBuilder ref_StringBuilder = new StringBuilder();

        // -- INIT REGEX
        Pattern ref_Pattern = Pattern.compile(ref_String_Regex);
        Matcher ref_Matcher;

        // -- APPLY REGEX AND EXTRACT
        ref_StringBuilder.append(ref_ref_String_Input);

        // -- WORk
        while(true){

            // -- Start match
            ref_Matcher = ref_Pattern.matcher(ref_StringBuilder.toString());

            // -- Check
            if(ref_Matcher.find() == Boolean.FALSE){

                // -- out of loop
                break;

            }

            // --  Set
            ref_StringBuilder.replace( ref_Matcher.start(), ref_Matcher.end(), "");

        }

        // -- COMMIT
        System.out.println("STRING REDUCED=" + ref_StringBuilder.toString());


    }

    public static void test3(){

        // -- INIT DATA
        String ref_ref_String_Input ="WINNER";
        String ref_String_Regex = "^(WINNER)$";


        // -- INIT REGEX
        Pattern ref_Pattern = Pattern.compile(ref_String_Regex);
        Matcher ref_Matcher =  ref_Pattern.matcher(ref_ref_String_Input);




    }

    public static void test4(){
        // -- Cons
        final String ref_String_Regex_Verification =
                "^KEY_IP=([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3}),KEY_PORT=([0-9]{1,5})$";

        boolean ref_Boolean_isIpgood;
        boolean ref_Boolean_isPortGood;

        String ref_String_Input = "KEY_IP=216.58.213.132,KEY_PORT=80";

        String ref_String_Delimiter_Comma = ",";
        String ref_String_Delimiter_Equals = "=";
        String ref_String_Delimiter_Dot = ".";

        // -- Init regex
        Pattern ref_Pattern = Pattern.compile(ref_String_Regex_Verification);
        Matcher ref_Matcher = ref_Pattern.matcher(ref_String_Input);


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

        // ... verif ip inside code


        // -- Init key/value split port
        String ref_Array_String_KeyValue_Port; // KEY_PORT=80

        // -- Init verif port xxxxx
        String [] ref_String_Port; // ref_Array_String_KeyValue_Port[1]


        // -- Check Ip
        if(ref_Matcher.find()){
            System.out.println("Regex verif ok");

            // -- Split IP adress and port

            // Découpe 1 : done ça KEY_IP=216.58.213.132 / KEY_PORT=80
            ref_Array_String_Array_Frame_Splitted = ref_String_Input.split(ref_String_Delimiter_Comma);

            // Log découpe 1
            for (String ref_String_Unit : ref_Array_String_Array_Frame_Splitted) {
                System.out.println(ref_String_Unit);
            }

            ref_Array_String_KeyValue_Ip = ref_Array_String_Array_Frame_Splitted[0]; // KEY_IP=216.58.213.132
            ref_Array_String_KeyValue_Port = ref_Array_String_Array_Frame_Splitted[1]; // KEY_PORT=80

            // -- Traitement IP ----------------------------------------------------------------------------------

            // -- Decoupe ip donne ça 216.58.213.132
            ref_Array_String_Ip_Splitted = ref_Array_String_KeyValue_Ip.split(ref_String_Delimiter_Equals);

            ref_String_Raw_Ip = ref_Array_String_Ip_Splitted[1]; // 216.58.213.132 => l'offset 1

            // -- Log
            System.out.println("Raw IP = " + ref_String_Raw_Ip);

            // -- Split all numbers of ip and remove dots
            ref_Array_String_Raw_Ip_Split = ref_String_Raw_Ip.split(ref_String_Delimiter_Dot); // 216.58.213.132 devient [216][58][213][132]

            // -- Log
            for (String ref_String_Unit : ref_Array_String_Raw_Ip_Split) {
                System.out.println("Raw IP Split" + ref_String_Unit);
            }

            ref_Array_Int_Ip = Stream.of(ref_Array_String_Raw_Ip_Split).mapToInt((e) -> Integer.valueOf(e)).toArray();

            // -- Log
            for (int ref_Int_Unit : ref_Array_Int_Ip) {
                System.out.println("Raw IP Split number" + ref_Int_Unit);
            }

            // -- Check
            ref_Boolean_isIpgood = true;

            for(int u :  ref_Array_Int_Ip){

                System.out.println(u);

                if(u > 255  || u < 1) {

                    System.out.println("Noooope");
                    ref_Boolean_isIpgood = false;
                    break;

                } else {
                    System.out.println("IP OK !");
                }

            }

            // -- Traitement Port ----------------------------------------------------------------------------------

            // -- Decoupe 1 : Donne le numéro du port
            ref_String_Port = ref_Array_String_KeyValue_Port.split(ref_String_Delimiter_Equals);

            // -- Log
            for (String ref_String_Unit : ref_String_Port) {
                System.out.println("Port = " + ref_String_Unit);
            }

            // -- Check
            ref_Boolean_isPortGood = true;

            for(int u :  ref_Array_Int_Ip){

                System.out.println(u);

                if(u > 0  || u < 1023) {

                    System.out.println("Noooope");
                    ref_Boolean_isPortGood = false;
                    break;

                } else {
                    System.out.println("Port OK !");
                }

            }

        }



    }
}
