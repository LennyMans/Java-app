package com.formation.tp.Perrsistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;


public class Persistence_Context {


    // -- CALL BACK -------------------------------------------------------

    public void persistInFile(StringBuffer ref_String_Buffer){

        // -- Init
        Runnable ref_Runnable = new Runnable() {
            @Override
            public void run() {

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
        };


        // -- Execute
        Executors.defaultThreadFactory().newThread(ref_Runnable).start();


    }

    public static void insert(String ref_String_DateToInsert){

        // -- CONS
        final String ref_String_Skeleton_Query = "INSERT INTO google_date2 (date_retrieve) VALUES ('%s')";
        String ref_String_Builder_Query = String.format( ref_String_Skeleton_Query,  ref_String_DateToInsert);


        // -- VARS
        Connection ref_Connexion = null;
        Statement ref_Statement = null;

        // -- Work

        try {

            // -- Init
            ref_Connexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/google_date?user=root&password=lenny1234");
            ref_Statement = ref_Connexion.createStatement();
            ref_Statement.executeQuery(ref_String_Builder_Query);

            // -- Close connection
            ref_Connexion.close();


        } catch (SQLException ref_SQLException_a) {
            ref_SQLException_a.printStackTrace();
            try {

                if (ref_Connexion != null) { ref_Connexion.close(); }

            } catch (SQLException ref_SQLException_b) {ref_SQLException_b.printStackTrace(); }
        }

    }

    public static ArrayList<String> select(){

        // -- CONS
        final String designedQuery = "select * from google_date2";

        // -- VARS
        Connection ref_Connexion = null;
        Statement ref_Statement = null;
        ResultSet resultSet = null;

        // -- Parse result
        ArrayList<String> arrayListUnitQueryResponse = new ArrayList<>();

        // -- Work
        try {

            // -- Init
            ref_Connexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/google_date?user=root&password=lenny1234");
            ref_Statement = ref_Connexion.createStatement();
            resultSet = ref_Statement.executeQuery(designedQuery);


            // -- Work
            while (resultSet.next()) {

                // -- Retrieve
                String ref_String_Date = resultSet.getString("date_retrieve");

                // -- Commit
                arrayListUnitQueryResponse.add(ref_String_Date);

            }

            // -- Close connection
            ref_Connexion.close();


        } catch (SQLException ref_SQLException_a) {

            // -- First log
            ref_SQLException_a.printStackTrace();

            try {

                if (ref_Connexion != null) { ref_Connexion.close(); }

            } catch (SQLException ref_SQLException_b) {

                // -- Second log
                ref_SQLException_b.printStackTrace();
            }
        }

        // -- Commit
        return arrayListUnitQueryResponse;


    }


}
