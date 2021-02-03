package com.formation.tp.test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test_Explication_Regex {


    public static void main (String [] ref_Array_String_Arg){

     test2();



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

}
