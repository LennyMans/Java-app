package com.formation.tp.test;

import java.io.*;

public class TestFlux {


    public static void main (String [] args){


        test2();

    }

    public static void test1(){

        File ref_File = new File("./TEST_FICHIER.txt");
        try {

            // -- INIT BUFFER
            byte [] ref_Arry_Byte = new byte[8];
            int ref_Int_ByteRead = -1;

            // -- INIT CLASS TO READ
            FileInputStream ref_FileInputStream = new FileInputStream(ref_File);
            ByteArrayOutputStream ref_ByteArrayOutputStream = new ByteArrayOutputStream();


            // -- work
            while((ref_Int_ByteRead =  ref_FileInputStream.read(ref_Arry_Byte)) != -1){

                // -- commit
                ref_ByteArrayOutputStream.write(ref_Arry_Byte, 0, ref_Int_ByteRead );

            }


            // -- RETRIEVE RESULTE
            ref_ByteArrayOutputStream.flush();
            String str = new String(ref_ByteArrayOutputStream.toByteArray());

            // -- Close
            ref_FileInputStream.close();
            ref_ByteArrayOutputStream.close();

            // -- FIN
            System.out.println("DAT FILE=" + str);



        } catch ( IOException e) {
            e.printStackTrace();
        }


    }

    public static void test2(){

        File ref_File = new File("./TEST_FICHIER.txt");
        try {


            // -- INIT CLASS TO READ
            FileInputStream ref_FileInputStream = new FileInputStream(ref_File);
            InputStreamReader ref_InputStreamReader = new InputStreamReader(ref_FileInputStream);
            BufferedReader ref_BufferedReader = new BufferedReader(ref_InputStreamReader);


            // -- INIT BUFFER
            String ref_String_LineRead;
            StringBuilder ref_StringBuilder = new StringBuilder();

            // -- work
            while((ref_String_LineRead =  ref_BufferedReader.readLine()) != null){

                // -- commit
                ref_StringBuilder.append(ref_String_LineRead);
                ref_StringBuilder.append("\n");

            }

            // -- Close
            ref_BufferedReader.close();


            // -- FIN
            System.out.println("DATA FILE");
            System.out.println(ref_StringBuilder.toString());




        } catch ( IOException e) {
            e.printStackTrace();
        }


    }
}
