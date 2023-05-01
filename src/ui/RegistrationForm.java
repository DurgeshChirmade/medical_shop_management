package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

import static ui.com.fonts.Font.*;

class RegistrationForm extends JFrame implements ActionListener
{
    JLabel customername,username,password,confirmpassword,mobileno,gender,toplabel;
    JTextField customernamefield,usernamefield,passwordfield,confirmpasswordfield,mobilenofield;
    JRadioButton male,female;
    JToggleButton iconButton,iconButton1;
    JButton register,login;
    String s="",c="";
    boolean flag=true, cnts =true;
    Dimension dim;

    Icon showicon = new ImageIcon("src/Images/eye.png");
    Icon hideicon = new ImageIcon("src/Images/hide.png");
//    private ActionEvent be;
//    private ActionEvent ae;

    RegistrationForm()
    {
        setLayout(new BorderLayout(0,0));

        JPanel mainPanel = new JPanel(new BorderLayout(200,100));
        JPanel logoPanel = new JPanel();
        JPanel bottompanel = new JPanel();
        JPanel rightpanel = new JPanel();
        JPanel leftpanel = new JPanel();

        ImageIcon img = new ImageIcon("src/Images/TitleLogo.png");
        Image img1 = img.getImage();
        Image img2 = img1.getScaledInstance(900,150,Image.SCALE_SMOOTH);
        ImageIcon Img = new ImageIcon(img2);

        JLabel logoLable = new JLabel();

        logoLable.setIcon(Img);
        logoPanel.add(logoLable);

        add(logoPanel,BorderLayout.NORTH);


        ImageIcon med = new ImageIcon("src/Images/background.png");
        Image med1 = med.getImage();
        Image med2 = med1.getScaledInstance(900,590,Image.SCALE_SMOOTH);
        ImageIcon meds = new ImageIcon(med2);
//        JLabel main = new JLabel(med);
//        main.add(gf);
        JLabel gf = new JLabel(meds);
        gf.setLayout(new GridLayout(9,1,1,1));
        gf.setBorder(BorderFactory.createTitledBorder(""));

        JLabel ll = new JLabel();
        ll.setLayout(new FlowLayout());

        JLabel fl = new JLabel();
        fl.setLayout(new FlowLayout());

        JLabel sl = new JLabel();
        sl.setLayout(new FlowLayout());

        JLabel tl = new JLabel();
        tl.setLayout(new FlowLayout());

        JLabel al = new JLabel();
        al.setLayout(new FlowLayout());

        JLabel bl = new JLabel();
        bl.setLayout(new FlowLayout());

        JLabel cl = new JLabel();
        cl.setLayout(new FlowLayout());

        JLabel dl = new JLabel();
        dl.setLayout(new FlowLayout());

        JLabel el = new JLabel();
        el.setLayout(new FlowLayout());

        toplabel = new JLabel("      Registration Form   ");
        toplabel.setFont(text);
        ll.add(toplabel);

        /*Label customername*/
        customername = new JLabel("Customer Name :       ");
        customername.setFont(text);
        fl.add(customername);

        customernamefield = new JTextField(15);
        customernamefield.setFont(field);
        fl.add(customernamefield);

        username = new JLabel(" Username :                ");
        username.setFont(text);
        sl.add(username);

        usernamefield = new JTextField(15);
        usernamefield.setFont(field);
        sl.add(usernamefield);
        usernamefield.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke)
            {
                char ch = ke.getKeyChar();

                if(Character.isLetter(ch) || ch == ' ' || ch == '@' || ch == '$' || ch == '#' || ch == '.')
                {
                    if(s.contains(".") && ch == '.')
                    {
                        ke.setKeyChar('\b');
                    }
                    return;

                }
                ke.setKeyChar('\b');

            }

        });

        password = new JLabel("    Password :                ");
        password.setFont(text);
        tl.add(password);

        passwordfield = new JTextField(15);
        passwordfield.setFont(field);
        tl.add(passwordfield);


        passwordfield.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent ke)
            {
                char ch = ke.getKeyChar();


                if(ch=='\b')
                {
                    if(s.length()>0)
                    {
                        s = s.substring(0, s.length()-1);
                    }

                    return;
                }

                if (!Character.isLetterOrDigit(ch))
                {
                    ke.consume();
                    return;
                }

                s = s + ch;


                if(flag == true)
                {
                    ke.setKeyChar('*');
                }
                else
                {
                    ke.setKeyChar(ch);
                }

            }


        });

        iconButton = new JToggleButton(hideicon);
//        iconButton.setSize(30,25);
//        iconButton.setLocation(805,475);
        iconButton.setBackground(new Color(255,255,255));
        iconButton.setBorder(null);
        iconButton.addActionListener(this);
        iconButton.setFocusable(false);                                  //unable to focus when clicked
        iconButton.setBackground(Color.ORANGE);                         //COLOR BUTTOn
        tl.add(iconButton);

        confirmpassword = new JLabel("  Confirm Password :    ");
        confirmpassword.setFont(text);
        al.add(confirmpassword);

        confirmpasswordfield = new JTextField(15);
        confirmpasswordfield.setFont(field);
        al.add(confirmpasswordfield);
        confirmpasswordfield.addKeyListener(new KeyAdapter()
        {
            public void keyTyped(KeyEvent ke)
            {
                char ch = ke.getKeyChar();

                if(ch=='\b')
                {
                    if(c.length()>0)
                    {
                        c = c.substring(0, c.length()-1);
                    }

                    return;
                }

                if (!Character.isLetterOrDigit(ch))
                {
                    ke.consume();
                    return;
                }

                c = c + ch;


                if(cnts == true)
                {
                    ke.setKeyChar('*');
                }
                else
                {
                    ke.setKeyChar(ch);
                }

            }


        });


        iconButton1 = new JToggleButton(hideicon);
//        iconButton.setSize(30,25);
//        iconButton.setLocation(805,475);
        iconButton1.setBackground(new Color(255,255,255));
        iconButton1.setBorder(null);
        iconButton1.addActionListener(this);
        iconButton1.setFocusable(false);                                  //unable to focus when clicked
        iconButton1.setBackground(Color.YELLOW);                         //COLOR BUTTOn
        al.add(iconButton1);

        mobileno = new JLabel(" Mobile No :               ");
        mobileno.setFont(text);
        bl.add(mobileno);


        mobilenofield = new JTextField(15);
        mobilenofield.setFont(field);
        bl.add(mobilenofield);
        mobilenofield.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke)
            {
                char ch = ke.getKeyChar();
                if (!Character.isDigit(ch))
                {
                    ke.consume();
                }
//                ke.consume();
            }


        });


        gender = new JLabel(" Gender :   ");
        gender.setFont(text);
        cl.add(gender);

        male = new JRadioButton(" Male ");
        male.setBackground(Color.ORANGE);
        female = new JRadioButton(" Female ");
        female.setBackground(Color.ORANGE);
        cl.add(male);
        cl.add(female);

        ButtonGroup bg = new ButtonGroup();
        bg.add(male);           //Male radio button
        bg.add(female);           //Female radio button

        register = new JButton(" Register ");
        register.setFont(button);
        register.setFocusable(false);
        register.setBackground(Color.BLUE);
        register.setForeground(Color.WHITE);
        register.addActionListener(this);
        dl.add(register);

        login = new JButton(" Click to Login ");
        login.setFont(button);
        login.addActionListener(this);
        login.setBackground(Color.BLUE);
        login.setForeground(Color.WHITE);
        el.add(login);

        gf.add(ll);
        gf.add(fl);
        gf.add(sl);
        gf.add(tl);
        gf.add(al);
        gf.add(bl);
        gf.add(cl);
        gf.add(dl);
        gf.add(el);

        mainPanel.add(gf,BorderLayout.CENTER);
        add(mainPanel,BorderLayout.CENTER);
        add(bottompanel,BorderLayout.SOUTH);
        add(rightpanel,BorderLayout.EAST);
        add(leftpanel,BorderLayout.WEST);

        setSize(900,800);
        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        ImageIcon titleicon = new ImageIcon("src/Images/MainLogo.png");
        setIconImage(titleicon.getImage());
    }

    public static ImageIcon logins = new ImageIcon("src/Images/AlertLogo.png");
    public static Image logins1 = logins.getImage();
    public static Image logins2 = logins1.getScaledInstance(60,60,Image.SCALE_SMOOTH);
    public static ImageIcon Logins = new ImageIcon(logins2);

    public boolean verifyField()
    {
        String fname = customernamefield.getText();
        String uname = usernamefield.getText();
        String phone = mobilenofield.getText();
        String pass1 = passwordfield.getText();
        String pass2 = confirmpasswordfield.getText();

        if(fname.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please Enter Your Name!!!","Empty Field Alert",JOptionPane.INFORMATION_MESSAGE,Logins);
            return false;
        }
        if(uname.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please Enter Valid Username!!!","Empty Field Alert",JOptionPane.INFORMATION_MESSAGE,Logins);
            return false;
        }
        if(phone.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please Enter Valid Mobile Number!!","Empty Field Alert",JOptionPane.INFORMATION_MESSAGE,Logins);
            return false;
        }
        if(pass1.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please Enter Password!!!","Empty Field Alert",JOptionPane.INFORMATION_MESSAGE,Logins);
            return false;
        }
        if(pass2.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null,"Please Enter Password for confirmation!!!","Empty Fields",JOptionPane.INFORMATION_MESSAGE,Logins);
            return false;
        }
        else if(pass1.equals(pass2))
        {
            JOptionPane.showMessageDialog(null,"Password  Match","Confirm Password",JOptionPane.INFORMATION_MESSAGE,Logins);
            return true;
        }
        else
        {
            return true;
        }

    }

    public static boolean checkUsername(String name,String password)
    {
        Statement st;
        ResultSet rs;


        try
        {
            Connection con = FirstScreen.getConnection();

            String qry = "SELECT `username`, `password` FROM `users` WHERE `username` = '" + name +"' AND `password` = '"+password+"'";

            st = con.createStatement();
            rs = st.executeQuery(qry);


               if(rs != null)
               {
                   rs.next();


                   if(name.equals(rs.getString(1)) && password.equals(rs.getString(2)))
                   {
                       return true;
                   }
                   else
                   {
                       return false;
                   }
               }




        }
        catch (Exception e)
        {
//               e.printStackTrace();
            //JOptionPane.showMessageDialog(null,"! first PROBLEM : .." + e);

            return false;

        }

        return false;

    }

    public static boolean checkUsername(String name)
    {
        Statement st = null;
        ResultSet rs = null;


        try
        {
            Connection con = FirstScreen.getConnection();

            String qry = "SELECT * FROM `users` WHERE `username` = '" + name +"'";

            st = con.createStatement();
            rs = st.executeQuery(qry);

            while(rs.next())
            {
                String str = rs.getString(3);
                if(name.equals(str))
                {
                    JOptionPane.showMessageDialog(null,"!!!  User is Already exists","Username Exist",JOptionPane.INFORMATION_MESSAGE,Logins);
                    return true;
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"!!!  User is not exists","Username Failed",JOptionPane.INFORMATION_MESSAGE,Logins);
                    return false;
                }

            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"! first PROBLEM : .." + e);
            return false;
        }


        return false;
    }

    public void actionPerformed(ActionEvent ae)
    {
        String btnaction = ae.getActionCommand();

        if (btnaction.equals(" Register "))
        {
            String fname = customernamefield.getText();
            String uname = usernamefield.getText();
            String pass1 = s;
//            String pass2 = confirmpassword.getText();
            String mnum = mobilenofield.getText();
            String gender = male.getActionCommand();

            if(female.isSelected())
            {
                gender = "Female";
            }
            if (!verifyField())
            {

            }
            else
            {
                if(!checkUsername(usernamefield.getText()))
                {
                    Statement s;

                   // PreparedStatement s;
                    ResultSet rs = null;


                    try
                    {
                        Connection con = FirstScreen.getConnection();
                        //String registerUserQuery = "INSERT INTO `users`(`CUSTOMER_NAME`, `username`, `password`, `MOBILE_NUMBER`, `Gender`) VALUES ('?','?','?','?','?')";
                           // s = con.prepareStatement(registerUserQuery);
                        String registerUserQuery = "INSERT INTO `users`(`CUSTOMER_NAME`, `username`, `password`, `MOBILE_NUMBER`, `Gender`) VALUES ('"+fname+"','"+uname+"','"+pass1+"','"+mnum+"','"+gender+"')";
                       s = con.createStatement();

                       int i = s.executeUpdate(registerUserQuery);


//                        s = FirstScreen.getConnection().prepareStatement(registerUserQuery);




                        if(i != 0)
                        {
                            JOptionPane.showMessageDialog(null,"New Account is Created!!!","New Account",JOptionPane.INFORMATION_MESSAGE,Logins);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Error","Error",JOptionPane.INFORMATION_MESSAGE,Logins);
                        }

                    }
                    catch (NullPointerException ne)
                    {
                        JOptionPane.showMessageDialog(null,"! second PROBLEM : .." + ne);
                        return ;
                    }
                    catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(null,"! second PROBLEM : .." + e);
                        return ;
                    }
                }
            }

            customernamefield.setText("");
            usernamefield.setText("");
            passwordfield.setText("");
            confirmpasswordfield.setText("");
            mobilenofield.setText("");

            return;

        }

        if(btnaction.equals(" Click to Login "))
        {
            dispose();
//            new FirstScreen();
        }

        if (flag==true) {
            passwordfield.setText(s);
            iconButton.setIcon(showicon);
            // iconButton.setBackground(Color.ORANGE);
            flag = false;
        } else{
            String str = "";

            for (int i = 0; i < s.length(); i++) {
                str = str + "*";
            }

            passwordfield.setText(str);
            iconButton.setIcon(hideicon);
            flag = true;
        }
        if (cnts==true) {
                confirmpasswordfield.setText(c);
                iconButton1.setIcon(showicon);
                cnts = false;
            }
        else {
                String ctr="";

            for (int i = 0; i < c.length(); i++) {
                    ctr = ctr + "*";
                }
                confirmpasswordfield.setText(ctr);
                iconButton1.setIcon(hideicon);
                cnts = true;
            }


    }


//    public void actionPerformed(ActionEvent be)
//        {
//
//            if (flags == true) {
//                confirmpasswordfield.setText(c);
//                iconButton1.setIcon(showicon);
//                flags = false;
//            } else {
//                String ctr = "";
//
//                for (int i = 0; i < c.length(); i++) {
//                    ctr = ctr + "*";
//                }
//                confirmpasswordfield.setText(ctr);
//                iconButton1.setIcon(hideicon);
//                flags = true;
//            }
//        }


    public static void main(String args[])
    {
        RegistrationForm obj = new RegistrationForm();
    }

    public static Connection getConnection()
    {

        try
        {
            String driver = "com.mysql.cj.jdbc.Driver";
            String databaseurl = "jdbc:mysql://localhost:3306/medical";
            String username = "root";
            String password = "";
            Class.forName(driver);

            Connection con = DriverManager.getConnection(databaseurl,username,password);
            //System.out.println("Connected Successfully");
            return con;

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"! Connection failed with database Please Start xampp servaer .." + e);
            return null;
            // e.printStackTrace();
        }

    }

}