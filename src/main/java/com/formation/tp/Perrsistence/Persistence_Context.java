package com.formation.tp.Perrsistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Executors;

public class Persistence_Context {


    // -- CALL BACK -------------------------------------------------------

    private void persistInFile(StringBuffer ref_String_Buffer){

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


}
