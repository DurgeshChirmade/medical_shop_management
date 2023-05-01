package ui;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Search extends JFrame implements ListSelectionListener,ActionListener
{
    JScrollPane sp;
    DefaultListModel DList;
    JList list;
    JTextField textField;
    JButton okbutton,removebutton,homeButton;



    private Search()
    {
        String s[] = new String[]{"C","Cpp","Java","Os","PHP","Python"};
        setLayout(null);

        DefaultListModel DList = new DefaultListModel();

        DList.addElement("SELECT");

        list = new JList(DList);
        //list.add(0,"omkar");
        list.addListSelectionListener(this);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//        list.add(sp);
        sp = new JScrollPane(list);
        sp.setSize(200,100);
        sp.setLocation(100,150);
        add(sp);


        textField = new JTextField();
        textField.setSize(130,25);
        textField.setLocation(100,100);
        add(textField);

        okbutton = new JButton(" OK ");
        okbutton.setSize(80,30);
        okbutton.addActionListener(this);
        okbutton.setLocation(100,300);
        add(okbutton);

        removebutton = new JButton(" Remove ");
        removebutton.setSize(90,30);
        removebutton.addActionListener(this);
        removebutton.setLocation(250,300);
        add(removebutton);

        homeButton = new JButton(" Home ");
        homeButton.setSize(85,30);
        homeButton.addActionListener(this);
        homeButton.setLocation(172,340);
        add(homeButton);

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String s = textField.getText();


                try
                {
                    Connection con = getConnection();

                    Statement smt = con.createStatement();

                    String qry = "SELECT `MEDICINE_NAME` FROM `medical_stock` WHERE `MEDICINE_NAME` LIKE '"+s+"%'";
                    ResultSet rs = smt.executeQuery(qry);

                    //String[] temp = new String[10];
                    DList.removeAllElements();
                    while (rs.next())
                    {
                        //temp[rs.getRow()] =rs.getString(1);

                     DList.addElement(rs.getString(1));
                        System.out.println(rs.getString(1));
                    }

                    //list = new JList(temp);

                    smt.close();
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


            }
        });


        setSize(900,500);
        setLocation(100,30);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ImageIcon titleicon = new ImageIcon("out\\production\\MAIN PROJECT\\ui\\logo.png");
        setIconImage(titleicon.getImage());
    }

    public void actionPerformed(ActionEvent ae)
    {
        String s = ae.getActionCommand();

        if(s.equals(" OK "))
        {

        }
        if(s.equals(" Remove "))
        {

        }
        if(s.equals(" Home "))
        {
            dispose();
            obj = null;
        }
    }
    public void valueChanged(ListSelectionEvent ise)
    {
       String s = (String) list.getSelectedValue();
        textField.setText(s);
    }

    static Connection getConnection()
    {

        try
        {
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseurl = "jdbc:mysql://localhost:3306/medical";
            String username = "root";
            String password = "";
            Class.forName(driver);

            Connection con = DriverManager.getConnection(databaseurl,username,password);
            System.out.println("Connected Successfully");
            return con;

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
    public static Search getSearch(){                     //Singletone Method
        if (obj == null){
            synchronized(Search.class){
                if (obj == null){
                    obj = new Search();//instance will be created at request time
                }
            }
        }
        return obj;
    }


    private static Search obj = new Search();


}
