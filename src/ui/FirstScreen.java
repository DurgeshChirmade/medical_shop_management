package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

import static ui.com.fonts.Font.*;

public class FirstScreen extends JFrame implements ActionListener
{

    JLabel login,password;
    JTextField loginField,passwordField;
    JButton loginButton,cancelButton,register,home;
    JToggleButton iconButton;

    String s="";
    boolean flag=true;
    Icon showIcon = new ImageIcon("src/Images/eye.png");
    Icon hideIcon = new ImageIcon("src/Images/hide.png");



    FirstScreen()
    {

        setLayout(new BorderLayout(0,0));
        JPanel mainPanel = new JPanel(new BorderLayout(0,0));
        JPanel logoPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        JPanel leftPanel = new JPanel();


        ImageIcon med = new ImageIcon("src/Images/background.png");
        Image med1 = med.getImage();
        Image med2 = med1.getScaledInstance(1820,740,Image.SCALE_SMOOTH);
        ImageIcon meds = new ImageIcon(med2);
//        JLabel main = new JLabel(med);
//        main.add(gf);
        JLabel gf = new JLabel(meds);
        gf.setLayout(new GridLayout(5,1,2,2));
        JLabel fl = new JLabel();
        fl.setLayout(new FlowLayout());


        JLabel sl = new JLabel();
        sl.setLayout(new FlowLayout());

        JLabel tl = new JLabel();
        tl.setLayout(new FlowLayout());

        JLabel cl = new JLabel();
        cl.setLayout(new FlowLayout());

        JLabel hl = new JLabel();
        hl.setLayout(new FlowLayout());

        ImageIcon img = new ImageIcon("src/Images/TitleLogo.png");
        Image img1 = img.getImage();
        Image img2 = img1.getScaledInstance(900,200,Image.SCALE_SMOOTH);
        ImageIcon Img = new ImageIcon(img2);

        JLabel logoLable = new JLabel();

        logoLable.setIcon(Img);
        logoPanel.add(logoLable);

        add(logoPanel,BorderLayout.NORTH);

        /* Label UserName */
        login  = new JLabel("UserName :     ");
//        login.setForeground(Color.GREEN);
        login.setFont(text);                                        //(Login.getFont().deriveFont(25.0f));
        fl.add(login);

        /*TextField UserName */
        loginField = new JTextField(15);
        loginField.setFont(field);
        fl.add(loginField);

        /* Label Password */
        JPanel passwordPanel = new JPanel();
        password  = new JLabel("    Password :     ");
//        password.setForeground(Color.RED);
        password.setFont(text);                                         //(Login.getFont().deriveFont(25.0f));
        sl.add(password);

        /* PasswordField Password */
        passwordField = new JTextField(15);
        passwordField.setFont(field);
        sl.add(passwordField);
        passwordField.addKeyListener(new KeyAdapter()
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

                s = s + ch;


                if(flag == true)
                {
                    ke.setKeyChar('*');
                }
                else
                {
                    ke.setKeyChar(ch);
                }

                if (!Character.isLetterOrDigit(ch))
                {
                    ke.consume();
                }
            }


        });

        loginField.addKeyListener(new KeyAdapter() {
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

        iconButton = new JToggleButton(hideIcon);
        iconButton.setBackground(new Color(255,255,255));
        iconButton.setBorder(null);
        iconButton.addActionListener(this);
        iconButton.setFocusable(false);                                  //unable to focus when clicked
        iconButton.setBackground(Color.YELLOW);                         //COLOR BUTTOn
        sl.add(iconButton);


        /* Button Login */
        loginButton = new JButton("Sign in");
        loginButton.setFont(button); //(new Font("Cambria Math", 0, 20));
        loginButton.setBackground(Color.BLUE);        /* Color of LoginButton */
        loginButton.setOpaque(true);                  /* LoginButton Opaque */
        loginButton.setForeground(Color.WHITE);       /* Color of LoginButton Text */
        loginButton.setFocusable(false);               //unable to focus when clicked
        loginButton.addActionListener(this);
        tl.add(loginButton);



        register = new JButton("Sign up");
        register.setFont(button);
        register.addActionListener(this);
        register.setOpaque(true);
        register.setFocusable(false);
        register.setBackground(Color.BLUE);
        register.setForeground(Color.WHITE);
        tl.add(register);

        /* Button Cancel */
        cancelButton = new JButton("CANCEL");
        cancelButton.setFont(button);        //(new Font("Cambria Math", 0, 20));
        cancelButton.addActionListener(this);
        cancelButton.setBackground(Color.YELLOW);        /* Color of CancelButton */
        cancelButton.setOpaque(true);                  /* LoginButton Opaque */
        cancelButton.setForeground(Color.BLACK);	   /* Color of CancelButton Text */
        cancelButton.setFocusable(false);               //unable to focus when clicked
        tl.add(cancelButton);


        JLabel nl = new JLabel("");
        nl.setLayout(new FlowLayout());

        gf.add(nl);
        gf.add(fl);
        gf.add(sl);
        gf.add(tl);
        gf.add(cl);


        mainPanel.add(gf,BorderLayout.CENTER);
        add(mainPanel,BorderLayout.CENTER);
        add(bottomPanel,BorderLayout.SOUTH);
        add(rightPanel,BorderLayout.EAST);
        add(leftPanel,BorderLayout.WEST);
        /* Main Frame */
        setTitle("Login Screen");
//        setSize(1650,1080);                    //screen size
//        setLocation(0,0);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon titleicon = new ImageIcon("src/Images/MainLogo.png");
        setIconImage(titleicon.getImage());
    }


    ImageIcon logins = new ImageIcon("src/Images/AlertLogo.png");
    Image logins1 = logins.getImage();
    Image logins2 = logins1.getScaledInstance(60,60,Image.SCALE_SMOOTH);
    ImageIcon Logins = new ImageIcon(logins2);


    public void actionPerformed(ActionEvent ae)
    {
       String listener = ae.getActionCommand();

       if(listener.equals("Sign in"))
       {
           if(RegistrationForm.checkUsername(loginField.getText(),s))
          {
              JOptionPane.showMessageDialog(null,"Login Successfully","Successfull",JOptionPane.INFORMATION_MESSAGE,Logins);
              dispose();
              SecondScreen.getSecondScreen();
              return;
          }
          else
          {
               JOptionPane.showMessageDialog(null,"Login Failed....","failed",JOptionPane.INFORMATION_MESSAGE,Logins);
               loginField.setText("");
               passwordField.setText("");
               s="";
          }
           return;
       }

       if(listener.equals("Sign up"))
       {
           new RegistrationForm();
           return;
       }

       if(listener.equals("CANCEL"))
       {
           loginField.setText("");
           passwordField.setText("");
           s="";
           return;
       }

        if(flag==true)
        {
            passwordField.setText(s);
            iconButton.setIcon(showIcon);
            // iconButton.setBackground(Color.ORANGE);
            flag=false;
        }
        else
        {
            String str ="";

            for(int i=0; i<s.length();i++)
            {
                str = str + "*";
            }

            passwordField.setText(str);
            iconButton.setIcon(hideIcon);
            flag=true;
        }



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
            JOptionPane.showMessageDialog(null,"! Connection failed with database Please Start xampp server .." + e);
            return null;
            // e.printStackTrace();
        }

    }

    public static void main(String args[])
    {

        FirstScreen ob = new FirstScreen();

    }

}

