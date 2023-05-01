package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static java.lang.String.format;
import static ui.com.fonts.Font.field;

public class ViewSell extends JFrame implements ActionListener
{
    int srNo = 1;
    ImageIcon img = new ImageIcon("src/Images/TitleLogo.png");
    Dimension dim;
    double total = 0.0;
    String[] columns = {
            "Sr. No","Date","Bill No","Customer ID","Total Amount"
    };

     DefaultTableModel modelview = new DefaultTableModel(columns, 0);
    JTable table = new JTable(modelview);
    JPanel medicineDetailsPanel = new JPanel(new BorderLayout(5, 10));
    JTextField dateTextField,totalSell;
    private ViewSell()
    {

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        Image img1 = img.getImage();
        Image img2 = img1.getScaledInstance(1100,200,Image.SCALE_SMOOTH);
        ImageIcon Img = new ImageIcon(img2);
        JPanel labelPanel = new JPanel();
        JLabel mainLabel = new JLabel(Img);
        //mainLabel.setFont(heading);
        labelPanel.add(mainLabel);
        add(labelPanel);

        JPanel datePanel = new JPanel();
        JLabel date = new JLabel("Enter Date :");

        dateTextField = new JTextField(20);

        JButton getTable = new JButton("Get Table");
        getTable.addActionListener(this);
        getTable.setBackground(Color.BLUE);
        getTable.setForeground(Color.WHITE);
        getTable.setFocusable(false);
        datePanel.add(date);
        datePanel.add(dateTextField);
        datePanel.add(getTable);
        add(datePanel,BorderLayout.EAST);

        JTableHeader header = table.getTableHeader();
        header.setFont(field);
        table.getTableHeader().setReorderingAllowed(false);

        medicineDetailsPanel.setBorder(BorderFactory.createTitledBorder("Table"));

        medicineDetailsPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        add(medicineDetailsPanel);
        JPanel totalPanel = new JPanel();
        JLabel total = new JLabel("Total cell of the day :  ");

        totalSell = new JTextField(20);
        totalSell.setEditable(false);
        totalPanel.add(total);
        totalPanel.add(totalSell);

        add(totalPanel,BorderLayout.SOUTH);

        ImageIcon home = new ImageIcon("src/Images/home.png");
        Image home1 = home.getImage();
        Image home2 = home1.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon Home = new ImageIcon(home2);

        JPanel homePanel = new JPanel();
        JButton homeButton = new JButton("");
        homeButton.addActionListener(this);
        homeButton.setIcon(Home);
        homeButton.setBackground(Color.WHITE);
        homePanel.add(homeButton);
        add(homePanel,BorderLayout.SOUTH);

        setSize(1300,1000);
  //     dim = Toolkit.getDefaultToolkit().getScreenSize();
//        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ImageIcon titleicon = new ImageIcon("src/Images/MainLogo.png");
        setIconImage(titleicon.getImage());

    }

    ImageIcon logins = new ImageIcon("src/Images/AlertLogo.png");
    Image logins1 = logins.getImage();
    Image logins2 = logins1.getScaledInstance(60,60,Image.SCALE_SMOOTH);
    ImageIcon Logins = new ImageIcon(logins2);

    public void actionPerformed(ActionEvent ae)
    {
        String s = ae.getActionCommand();

        if(s.equals("Get Table"))
        {

            String date = dateTextField.getText();

            Connection con = SecondScreen.getConnection();

            Statement smt = null;
            ResultSet rs = null;
            try
            {
                String qry = "SELECT `DATE`, `BILL_NO`, `CUSTOMER_ID`, `TOTAL` FROM `sell_log` WHERE `DATE` LIKE '"+date+"'";

                smt = con.createStatement();

                rs = smt.executeQuery(qry);

                while(rs.next())
                {
                    modelview.addRow(new Object[]{
                            srNo,
                            rs.getDate(1),
                            rs.getString(2),
                             rs.getInt(3),
                            rs.getFloat(4)
                    });
                    total = total + rs.getFloat(4);
                    srNo++;
                }
                totalSell.setText( format("%05.2f",total));
                smt.close();
                con.close();

//                if(total == 00.00)
//                {
//                    JOptionPane.showMessageDialog(null,"No Record Found Of Given Data","Error",JOptionPane.INFORMATION_MESSAGE,Logins);
//                }

            }

            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }


        }
        if(s.equals(""))
        {
            obj = null;
            dispose();

        }
    }

    public static ViewSell getViewSell(){                      //Singletone Method
        if (obj == null){
            synchronized(ViewSell.class){
                if (obj == null){
                    obj = new ViewSell();//instance will be created at request time
                }
            }
        }
        return obj;
    }



    private static  ViewSell obj = new ViewSell();

}

