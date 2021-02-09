package com.formation.tp.UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

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
            this.ref_Jbutton.setBackground(new Color(132, 46, 27));
            this.ref_Jbutton.setForeground(Color.WHITE);
            this.ref_Jbutton.setBorder(BorderFactory.createEmptyBorder());
            this.ref_Jbutton.setPreferredSize(new Dimension(120,30));
            this.ref_Jbutton.setMinimumSize(new Dimension(120,35));
            this.ref_Jbutton.setMaximumSize(new Dimension(120,35));
            
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
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
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
        
        
        ref_Jpanel_Container_A.add(ref_JscrollPane);
        ref_Jpanel_Container_B.add(ref_Jbutton);
        

    }
    
    private void genFrame(){

        ref_Jframe = new JFrame("Github Sample");
        ref_Jframe.setContentPane(this.ref_Jpanel_Dashboard);
        ref_Jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ref_Jframe.setAlwaysOnTop(true);
        ref_Jframe.pack();


        int px= ref_Jframe.getX();
        int py= ref_Jframe.getY();
        int x = px + (ref_Jframe.getWidth()/2) - (ref_Jframe.getWidth()/2);
        int y= py + (ref_Jframe.getHeight()/2) - (ref_Jframe.getHeight()/2);

        ref_Jframe.setLocation(x, y);
        ref_Jframe.setVisible(true);
        ref_Jframe.setResizable(false);

    }
}
