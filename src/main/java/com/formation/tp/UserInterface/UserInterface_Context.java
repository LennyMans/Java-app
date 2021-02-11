package com.formation.tp.UserInterface;

import com.formation.tp.Network.Callable_Remote_GetDate;
import com.formation.tp.app.Master_Context;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;


public class UserInterface_Context {
    
    // -- CONS
    public final static Color COLOR_PRIMARY = new Color(131, 131, 131);
    public final static Color COLOR_PRIMARY_HL = new Color(151, 151, 151);

    public final static Color COLOR_PRIMARYB = new Color(111, 111, 111);
    public final static Color COLOR_PRIMARYB_HL = new Color(131, 131, 131);

    public final static Color COLOR_PRIMARYC = new Color(91, 91, 91);

    public final static Color COLOR_BUTTON__HEADER = new Color(161, 161, 161);
    public final static Color COLOR_BUTTON__HEADER_HL = new Color(191, 191, 191);


    // -- REFS
    JFrame ref_Jframe;
        JPanel ref_Jpanel_Dashboard;
            JPanel ref_Jpanel_Container_A;
                JScrollPane ref_JscrollPane;
                JTextArea ref_JtextArea;
            JPanel ref_Jpanel_Container_B;
                JButton ref_Jbutton;
                JButton ref_Jbutton_Persist_Db;
                JButton ref_Jbutton_Show_Db_Data;
    
                
                
    // -- CONSTRUCTOR --------------------------------------------------
    
    public UserInterface_Context(){

        this.init_Ui_Properties();
        this.init_UI();
        this.link_UI();
        this.genFrame();
        
    }
    
    
    
    // -- INITILISATOR -------------------------------------------------

    private void init_Ui_Properties(){

        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");


        try {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {


                    if ("Nimbus".equals(info.getName())) {

                        try {
                            UIManager.setLookAndFeel(info.getClassName());
                        } catch (Exception ref_Exception) {
                            ref_Exception.printStackTrace();
                        }
                        break;
                    }

                }

            }catch(Exception e){}

            // Table
            UIManager.put("nimbusBase", COLOR_PRIMARY); // ??
            UIManager.put("nimbusFocus", new Color(0, 0, 0, 0));
            UIManager.put("info", Color.WHITE);

            UIManager.put("Table[Enabled+Selected].textForeground", Color.WHITE);
            UIManager.put("Table[Enabled+Selected].textBackground", COLOR_PRIMARY);

            UIManager.put("ComboBox:\"ComboBox.listRenderer\"[Selected].background", COLOR_PRIMARY);

            UIManager.put("MenuBar.foreground", Color.PINK);
            UIManager.put("TextArea[Selected].textForeground", Color.BLACK);

    }

    private void init_UI(){

       // -- GLOBAL DASHBOARD 
       this.ref_Jpanel_Dashboard = new JPanel();
           this.ref_Jpanel_Dashboard.setMinimumSize(new Dimension(800, 600));
           this.ref_Jpanel_Dashboard.setPreferredSize(new Dimension(800, 600));
           this.ref_Jpanel_Dashboard.setLayout(new GridBagLayout());

        // -- CONTAINER A
        this.ref_Jpanel_Container_A = new JPanel();
            this.ref_Jpanel_Container_A.setMinimumSize(new Dimension(800, 400));
            this.ref_Jpanel_Container_A.setPreferredSize(new Dimension(800, 400));
            this.ref_Jpanel_Container_A.setBackground(COLOR_PRIMARY);
            this.ref_Jpanel_Container_A.setOpaque(true);
            this.ref_Jpanel_Container_A.setLayout(new GridBagLayout());
            
                    this.ref_JtextArea = new JTextArea();
                        this.ref_JtextArea.setFont(new Font("Lato", Font.PLAIN, 11));
                        this.ref_JtextArea.setBorder(BorderFactory.createMatteBorder(6, 6, 6, 6, Color.WHITE));
                        this.ref_JtextArea.setLineWrap(true);
                        this.ref_JtextArea.setWrapStyleWord(true);
                        this.ref_JtextArea.setEditable(false);
                    
                    this.ref_JscrollPane = new JScrollPane(this.ref_JtextArea);
                        this.ref_JscrollPane.setMinimumSize(new Dimension(400, 300));
                        this.ref_JscrollPane.setPreferredSize(new Dimension(400, 300));
                        this.ref_JscrollPane.setMaximumSize(new Dimension(400, 300));
                        this.ref_JscrollPane.setBorder(BorderFactory.createEmptyBorder());
                        this.ref_JscrollPane.setWheelScrollingEnabled(true);


        // -- CONTAINER B
        this.ref_Jpanel_Container_B = new JPanel();
            this.ref_Jpanel_Container_B.setMinimumSize(new Dimension(800, 200));
            this.ref_Jpanel_Container_B.setPreferredSize(new Dimension(800, 200));
            this.ref_Jpanel_Container_B.setBackground(COLOR_PRIMARY);
            this.ref_Jpanel_Container_B.setOpaque(true);
            this.ref_Jpanel_Container_B.setLayout(new GridBagLayout());
       
      
       this.ref_Jbutton = new JButton("Get a date");
            this.ref_Jbutton.setFont(new Font("Lato", Font.PLAIN, 13));
            this.ref_Jbutton.setBorder(BorderFactory.createEmptyBorder());
            this.ref_Jbutton.setPreferredSize(new Dimension(120,30));
            this.ref_Jbutton.setMinimumSize(new Dimension(120,35));
            this.ref_Jbutton.setMaximumSize(new Dimension(120,35));
            this.ref_Jbutton.addMouseListener(new MouseListener_GetDate());
            this.ref_Jbutton.addMouseListener(new ChangeButtonColourListener());

        this.ref_Jbutton_Persist_Db = new JButton("Persist data in db");
            this.ref_Jbutton_Persist_Db.addMouseListener(new MouseListener_Persist_Db());

        this.ref_Jbutton_Show_Db_Data = new JButton("Show data in db");
            this.ref_Jbutton_Show_Db_Data.addMouseListener(new MouseListener_Show_Db_Data());
/*
            MouseListener[] ref_Array_MousListener =  this.ref_Jbutton.getMouseListeners();
            
            for(MouseListener ref_MousListener_Unit : ref_Array_MousListener){
            
            this.ref_Jbutton.removeMouseListener(ref_MousListener_Unit);
            
            }

*/


    }
    
    private void link_UI(){

        // -- Init
        GridBagConstraints gbc = new GridBagConstraints();
        
        // -- Set pane a & b
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.CENTER;

        ref_Jpanel_Dashboard.add( ref_Jpanel_Container_A, gbc);

        gbc.gridy = 1;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        
        ref_Jpanel_Dashboard.add( ref_Jpanel_Container_B, gbc);

        // -- Set pane a inner
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridheight= GridBagConstraints.REMAINDER;
        gbc.weightx = 0;
        gbc.weighty = 0;
        
        
        ref_Jpanel_Container_A.add(ref_JscrollPane, gbc);

        // -- Set pane b inner
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight= GridBagConstraints.REMAINDER;
        gbc.weightx = 0;
        gbc.weighty = 0;

        // -- Btn 1
        gbc.gridx = 0;
        ref_Jpanel_Container_B.add(ref_Jbutton, gbc);

        // -- Btn 2
        gbc.gridx = 5;
        ref_Jpanel_Container_B.add(ref_Jbutton_Persist_Db, gbc);

        // -- Btn 3
        gbc.gridx = 10;
        ref_Jpanel_Container_B.add(ref_Jbutton_Show_Db_Data, gbc);

    }
    
    private void genFrame(){

        ref_Jframe = new JFrame("Github Sample");
        ref_Jframe.setContentPane(this.ref_Jpanel_Dashboard);
        ref_Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ref_Jframe.setAlwaysOnTop(true);
        ref_Jframe.pack();


        ref_Jframe.setVisible(true);
        ref_Jframe.setResizable(true);

    }


    // -- OUTER CALLBACK -----------------------------------------------

    public void commit_Date (String ref_String_Value){

        SwingUtilities.invokeLater(()->{

            this.ref_JtextArea.append(ref_String_Value);

        });

    }

    public void commit_DB (ArrayList<String> ref_ArrayList_Content) {
        for (String ref_String_Db_Data_Unit : ref_ArrayList_Content) {

            SwingUtilities.invokeLater(()->{

                this.ref_JtextArea.append(ref_String_Db_Data_Unit);

            });
        }
    }


    // -- MOUSE LISTENER ----------------------------------------------------------

    class MouseListener_GetDate implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

           // -- Build
           Runnable ref_Runnable = new Runnable() {
               @Override
               public void run() {

                   // -- Retrieve
                   FutureTask<String> ref_Future_Task = Callable_Remote_GetDate.get_FutureTask_Execute_Resquest();

                   // -- Execute
                   Executors.defaultThreadFactory()
                           .newThread(ref_Future_Task)
                           .start();

                   // -- Retrieve
                   String ref_String_Date = null;

                   try {

                       ref_String_Date = ref_Future_Task.get();

                   } catch (InterruptedException | ExecutionException ref_Exception) {

                       ref_Exception.printStackTrace();
                   }

                   // -- Output
                   Master_Context.ref_UserInterface_Context.commit_Date("Date Collected From Goole=" +  ((ref_String_Date != null )? ref_String_Date:"Unable to retrieve a date"));
               }
           };

            // -- Eexcute
            Executors.defaultThreadFactory().newThread(ref_Runnable).start();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class ChangeButtonColourListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            final JButton jb = (JButton)e.getSource();

            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {

                    jb.setBackground(COLOR_PRIMARY_HL);

                }


            });




        }

        @Override
        public void mouseExited(MouseEvent e) {
            final JButton jb = (JButton)e.getSource();


            SwingUtilities.invokeLater(new Runnable(){

                @Override
                public void run() {

                    jb.setBackground(COLOR_PRIMARY);


                }


            });




        }


        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }


    }

    class MouseListener_Persist_Db implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

            // -- Get the contents of the JTextArea component.
            String ref_String_Content = ref_JtextArea.getText();

            // -- Split all string content into list
            String [] ref_String_Array_Content = ref_String_Content.split("\n");
            ArrayList<String> ref_ArrayList_Content = new ArrayList<String>();

            for (String ref_String_Line : ref_String_Array_Content) {
                // -- Log
                System.out.println(ref_String_Line);

                // -- Commit in DB
                ref_ArrayList_Content.add(ref_String_Line);
            }

            // -- Loop into ArrayList and call insert method
            for (String ref_String_Unit : ref_ArrayList_Content) {

                // -- Work
                Master_Context.ref_Persistence_Context.insert(ref_String_Unit);
            }

            // -- Clear Jtexarea
            ref_JtextArea.selectAll();
            ref_JtextArea.replaceSelection("");

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class MouseListener_Show_Db_Data implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {

            // Get content from database and set content into Jtexarea
            for (String ref_String_Line : Master_Context.ref_Persistence_Context.select()) {
                System.out.println(ref_String_Line);
                ref_JtextArea.append(ref_String_Line + "\n");
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

}
