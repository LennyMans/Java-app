package com.formation.tp.test;


import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestStream {

    public static void main (String [] ref_String_Arg){

        test1();

    }

    public static void test1(){
        // <>
        String [] ref = {"aomme","diwi","coire","123456","belon","Brique","Pasteque","Cerise","Zarotte","Kubergine"};

        List<String> ref_list = Stream.of(ref)
                .limit(7)
                .filter((ref_element)-> (Pattern.compile("[0-9]").matcher(ref_element).find() == Boolean.FALSE))
                .map((a)-> a.toUpperCase())
                .sorted(new ComparatorForCartoucheStandard (ComparatorForCartoucheStandard.CONS_ORDERING_STATE_ASC))
                .collect(Collectors.toList());

        System.out.println("Tableau sous forme de liste modifié");

        for(String s : ref_list){

            System.out.println(s);

        }

    }




}

 class ComparatorForCartoucheStandard implements Comparator<String>{

    int returnValA = -1;
    int returnValB = 0;
    int returnValC = 1;

    // -- Partie ordering du JTable
    public final static String CONS_ORDERING_STATE_ASC = "0";
    public final static String CONS_ORDERING_STATE_DSC = "1";



    public ComparatorForCartoucheStandard(String ascendancy){



        if(ascendancy.equals(ComparatorForCartoucheStandard.CONS_ORDERING_STATE_ASC)){

        }else{

            returnValA = 1;
            returnValB = 0;
            returnValC = -1;
        }
    }

    @Override
    public int compare(String s1, String s2) {

        // -- Verif

        if(s1 == null){s1 = "";}
        if(s2 == null){s2 = "";}


        // -- Work

        if(s1.length() == 0 && s2.length() == 0){


            return returnValB;


        }else if(s1.length() == 0 || s2.length() == 0){


            return s1.length()==0?returnValA:returnValC;


        }else if(s1.length() == s2.length()){


            for(int a = 0; a<s1.length(); a++){

                if(s1.codePointAt(a) == s2.codePointAt(a)){

                    continue;

                }else if(s1.codePointAt(a) > s2.codePointAt(a)){

                    return returnValC;

                }else{

                    return returnValA;
                }
            }

            return returnValB;

        }else{


            int intIterator = s1.length() > s2.length()?s2.length():s1.length();

            for(int a =0; a<intIterator; a++){

                if(s1.codePointAt(a) == s2.codePointAt(a)){

                    continue;

                }else if(s1.codePointAt(a) > s2.codePointAt(a)){

                    return returnValC;

                }else{

                    return returnValA;
                }
            }


            return s1.length()>s2.length()?returnValC:returnValA;

        }



    }


 }