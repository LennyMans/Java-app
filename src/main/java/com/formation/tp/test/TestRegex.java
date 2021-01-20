package com.formation.tp.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {

    public static void main (String [] args){
        /*

            String [] str = new String[6];
                str[0] = "";
                str [1] = "";

         */

        // -- REGEX
        String ref_String_Regex_InputVerification
                = new String("^CustomerFirstName=([A-Z]{1}[a-z]{1,20}),CustomerName=([A-Z]{1}[a-z]{1,20})$");

        // -- INPUT
       String [] ref_Array_String_Input =
               {"CustomerFirstName=Mans,CustomerName=Lenny"
               ,"customerFirstName=Mans,CustomerName=Lenny"
               ,"CustomerFirstName=1Mans,CustomerName=Lenny"
               ,"CustomerFirstName=MansCustomerName=Lenny"
               ,"CustomerFirstName=,CustomerName=Lenny"
               ,"CustomerFirstName=Mans,CustomerName=Lenny9"};


       // -- VERIF
        /*
       for(int ref_Int_Offset_A = 0; ref_Int_Offset_A > ref_Array_String_Input.length; ref_Int_Offset_A++){

           String ref_String_InputToCheck = ref_Array_String_Input[ref_Int_Offset_A];

       }
         */

        // -- INIT
        Pattern ref_Pattern  = Pattern.compile(ref_String_Regex_InputVerification);
        Matcher ref_Match = null;

        // -- WORK
        for(String ref_String_InputToCheck : ref_Array_String_Input){

           // -- Do
           ref_Match = ref_Pattern.matcher(ref_String_InputToCheck);

           // -- Commit
           System.out.println("IS MATCH ? " + ref_Match.find());

        }

    }

}
