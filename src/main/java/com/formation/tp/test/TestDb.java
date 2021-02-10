package com.formation.tp.test;

import java.sql.*;
import java.util.ArrayList;


public class TestDb {
    
    
    public static void main (String [] args) {

        // -- CONS
        final String designedQuery = "select * from google_date2";

        // -- VARS
        Connection ref_Connexion = null;
        Statement ref_Statement = null;
        ResultSet resultSet = null;
        ArrayList<String> arrayListUnitQueryResponse = null;

        // -- Work

        try {

            // -- Init
            ref_Connexion = DriverManager.getConnection("JDBC_ref_Connexion_JNDI_PATH");
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


        } catch (SQLException e) {

            try {

                if (ref_Connexion != null) { ref_Connexion.close(); }

            } catch (SQLException ref_SQLException) {ref_SQLException.printStackTrace(); }
        }

    }
}
