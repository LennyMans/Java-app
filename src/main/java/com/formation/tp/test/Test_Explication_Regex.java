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
        String ref_input = "WINNER-JesSuisUneLibellule-fWINNER_ViveLensWINNER54654654";
        String ref_String_Regex ="WINNER";
        StringBuilder ref_StringBuilder = new StringBuilder();

        // -- INIT REGEX
        Pattern ref_Pattern = Pattern.compile(ref_String_Regex);
        Matcher ref_Matcher = ref_Pattern.matcher(ref_input);

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
                ref_Array_Unit[1] = ref_input.length();

            // -- CASE MATCH ENTRE DEBUT ET FIN DE MATCH
            }else{

                ref_Array_Unit[0] =  ref_Array_ToRemoveUnit[1];
                ref_Array_Unit[1] = ref_ArrayList_PositionToRemove.get(ref_Int_A+1)[0];

            }

            ref_ArrayList_PositionToCopy.add(ref_Array_Unit);

        }


        // -- COPY ALL THE POSITION
        for(int[] ref_Int_Unit : ref_ArrayList_PositionToCopy){

            ref_StringBuilder.append(ref_input.substring(ref_Int_Unit[0], ref_Int_Unit[1]));

        }


        // -- COMMIT
        System.out.println("STRING REDUCED=" + ref_StringBuilder.toString());



    }

    public static void test2(){


       // -- INIT DATA
        String ref_input = "WINNER-JesSuisUneLibellule-fWINNER_ViveLensWINNER54654654";
        String ref_String_Regex ="WINNER";
        StringBuilder ref_StringBuilder = new StringBuilder();

        // -- INIT REGEX
        Pattern ref_Pattern = Pattern.compile(ref_String_Regex);
        Matcher ref_Matcher;

        // -- APPLY REGEX AND EXTRACT
        ref_StringBuilder.append(ref_input);

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
        String ref_input ="WINNER";
        String ref_String_Regex = "^(WINNER)$";


        // -- INIT REGEX
        Pattern ref_Pattern = Pattern.compile(ref_String_Regex);
        Matcher ref_Matcher =  ref_Pattern.matcher(ref_input);




    }

    public static void test4(){

        String input = "KEY_IP=216.58.213.132,KEY_PORT=80";
        String delimiter_a = ",";
        String delimitier_b = "=";
        String delimitier_c = ".";

        String ip;
        String port;

        final String ref_String_Regex_Verification =
                "^KEY_IP=([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3})\\.([0-9]{1,3}),KEY_PORT=([0-9]{1,5})$";

        Pattern p = Pattern.compile(ref_String_Regex_Verification);
        Matcher m = p.matcher(input);

        if(m.find()){
            System.out.println("Regex verif ok");

            // Decoupe 1 done ça KEY_IP=216.58.213.132
            String [] arr = input.split(delimiter_a);

            // Log découpe 1
            System.out.println(arr);

            String ip_dec = arr[0]; // KEY_IP=216.58.213.132
            String port_dec = arr[1]; // KEY_PORT=80

            // -- Traitement IP ----------------------------------------------------------------------------------

            // -- Decoupe ip donne ça 216.58.213.132
            String [] arr2 = ip_dec.split( delimitier_b);

            String raw_ip = arr2[1]; // 216.58.213.132     c'est loffset 1


            String [] arr_Ip = raw_ip.split(delimitier_c); // 216.58.213.132 devient [216][58][213][132]

            // -- Log
            System.out.println(arr_Ip);

            int [] ref_Array_Int_Ip = Stream.of(arr_Ip).mapToInt((e) -> Integer.valueOf(e)).toArray();

            // -- Log
            System.out.println(ref_Array_Int_Ip);

            boolean isIpgood = true;

            for(int u :  ref_Array_Int_Ip){

                System.out.println(u);

                if(u > 255  || u < 1) {

                    System.out.println("Noooope");
                    isIpgood = false;
                    break;

                } else {
                    System.out.println("IP OK !");
                }

            }


            // -- Traitement Port ----------------------------------------------------------------------------------

            // -- Decoupe ip donne ça 216.58.213.132
            String [] Ref_String_Array_Port = port_dec.split( delimitier_b);
        }



    }
}
