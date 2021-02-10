package com.formation.tp.test;

import java.sql.*;
import java.util.ArrayList;


public class TestDb {
    
    
    public static void main (String [] args) {

        example_insert("TEST INSERT Lenny");
        example_select();

    }

    public static void example_insert(String ref_String_DateToInsert){

        // -- CONS
        final String ref_String_Skeleton_Query = "INSERT INTO google_date2 (date_retrieve) VALUES ('%s')";
        String ref_String_Builder_Query = String.format( ref_String_Skeleton_Query,  ref_String_DateToInsert);


        // -- VARS
        Connection ref_Connexion = null;
        Statement ref_Statement = null;
        ResultSet resultSet = null;
        ArrayList<String> arrayListUnitQueryResponse = null;

        // -- Work

        try {

            // -- Init
            ref_Connexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/google_date?user=root&password=lenny1234");
            ref_Statement = ref_Connexion.createStatement();
            resultSet = ref_Statement.executeQuery(ref_String_Builder_Query);

            // -- Close connection
            ref_Connexion.close();


        } catch (SQLException ref_SQLException_a) {
            ref_SQLException_a.printStackTrace();
            try {

                if (ref_Connexion != null) { ref_Connexion.close(); }

            } catch (SQLException ref_SQLException_b) {ref_SQLException_b.printStackTrace(); }
        }

    }

    public static void example_select(){

        // -- CONS
        final String designedQuery = "select * from google_date2";

        // -- VARS
        Connection ref_Connexion = null;
        Statement ref_Statement = null;
        ResultSet resultSet = null;

        // -- Parse les resultats du statment
        ArrayList<String> arrayListUnitQueryResponse = null;

        // -- Work

        try {

            // -- Init
            ref_Connexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/google_date?user=root&password=lenny1234");
            ref_Statement = ref_Connexion.createStatement();
            resultSet = ref_Statement.executeQuery(designedQuery);
            arrayListUnitQueryResponse = new ArrayList<String>();


            // -- Work
            while (resultSet.next()) {

                // -- Retrieve
                String ref_String_Date = resultSet.getString("date_retrieve");

                // -- Commit
                arrayListUnitQueryResponse.add(ref_String_Date);

            }

            // -- Log
            for(String ref_String_Unit :  arrayListUnitQueryResponse){

                System.out.println("ROW VALUE FINDED:" + ref_String_Unit);

            }

            // -- Close connection
            ref_Connexion.close();


        } catch (SQLException ref_SQLException_a) {
            ref_SQLException_a.printStackTrace();
            try {

                if (ref_Connexion != null) { ref_Connexion.close(); }

            } catch (SQLException ref_SQLException_b) {ref_SQLException_b.printStackTrace(); }
        }
    }


}
