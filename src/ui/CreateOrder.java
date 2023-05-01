package ui;

import model.UpdateStock;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.String.format;

public class CreateOrder extends JFrame implements ActionListener, ItemListener , Printable {

     int srNo = 1;
    int quantity;
    String medicineName ;
    double p,totalAmount;
    static int cnt =0;



//SELECT CUSTOMER_ID from sell_log ORDER by  CUSTOMER_ID DESC LIMIT 1;

    UpdateStock[] ups =new UpdateStock[10];




    JComboBox cb;
    String customerName;

    DefaultComboBoxModel Dcb;

    String[] columns = {
            "SR.No", "Name", "Expiry Date", "Quantity", "Price"
    };

    DefaultTableModel model = new DefaultTableModel(columns, 0);
    JTable table = new JTable(model);
    JPanel medicineDetailsPanel = new JPanel(new BorderLayout(0, 10));
    JPanel medicineAddPanel;
    JTextField medicineSearchField ,quantityField,totalPriceField,customerNameField,customerPhoneField,customerAddressField,customerAgeField,pricribedByField;
    // ImageIcon image = new ImageIcon("C:\\Users\\OMKAR\\Desktop\\F Y Project\\APP008\\src\\ImageLogo.jpeg");

//    JTextField medicineSearchField ,quantityField,totalPriceField,customerNameField;


    private CreateOrder() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JPanel labelPanel = new JPanel();
        JLabel mainLabel = new JLabel();

        ImageIcon img = new ImageIcon("src/Images/TitleLogo.png");
        Image img1 = img.getImage();
        Image img2 = img1.getScaledInstance(900,200,Image.SCALE_SMOOTH);
        ImageIcon Img = new ImageIcon(img2);
        mainLabel.setIcon(Img);
//        mainLabel.setFont(new Font("Arial", 0, 65));
        labelPanel.add(mainLabel);

        // Adding Label Panel
        add(labelPanel);

        JPanel customerDetailsPanel = new JPanel();
        customerDetailsPanel.setBorder(BorderFactory.createTitledBorder("Customer Details   "));
        customerDetailsPanel.setLayout(new FlowLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(3,2,0,5));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(3,2,0,5));

        customerNameField = new JTextField(20);
        leftPanel.add(new JLabel("Customer Name : "));
        leftPanel.add(customerNameField);

         customerPhoneField = new JTextField(20);
        rightPanel.add(new JLabel("             Mobile Number : "));
        rightPanel.add(customerPhoneField);



         customerAddressField = new JTextField(20);
        leftPanel.add(new JLabel("Address : "));
        leftPanel.add(customerAddressField);

        customerAgeField = new JTextField(1);
        rightPanel.add(new JLabel("             Age : "));
        rightPanel.add(customerAgeField);

        JLabel pricribedBy = new JLabel("Doctor's Name :");
        pricribedByField = new JTextField(15);
        leftPanel.add(pricribedBy);
        leftPanel.add(pricribedByField);


        JLabel datelable = new JLabel("            Date :");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        JLabel todayDate = new JLabel(formatter.format(date));
        rightPanel.add(datelable);
        rightPanel.add(todayDate);


        customerDetailsPanel.add(leftPanel);
        customerDetailsPanel.add(rightPanel);

        add(customerDetailsPanel);





        medicineDetailsPanel.setBorder(BorderFactory.createTitledBorder("Bill No: " + cnt));

        medicineAddPanel = new JPanel();
        //medicineAddPanel.setLayout(new GridLayout(1,7));
        medicineAddPanel.setLayout(new FlowLayout());

        JLabel medicineSearch = new JLabel("Medicine Name :");
        medicineSearchField = new JTextField(20);

        JPanel a = new JPanel(new BorderLayout());


        Dcb = new DefaultComboBoxModel();

        Dcb.addElement("SELECT");
        cb = new JComboBox(Dcb);
        cb.addItemListener(this);


        //sp = new JScrollPane(list);

        a.setSize(40,100);
       a.add(cb,BorderLayout.CENTER);


        medicineAddPanel.add(medicineSearch);
        medicineAddPanel.add(medicineSearchField);
        medicineAddPanel.add(a);

        JLabel quantity = new JLabel("Enter Quantity :");
        quantityField = new JTextField(10);
        medicineAddPanel.add(quantity);
        medicineAddPanel.add(quantityField);

        JButton addButton = new JButton("  + Add  ");

        JButton removeButton = new JButton("Remove Selected Row");
        removeButton.addActionListener(this);

        addButton.addActionListener(this);
        addButton.setBackground(Color.BLUE);
        addButton.setFocusable(false);
        addButton.setForeground(Color.WHITE);
        removeButton.setBackground(Color.BLUE);
        removeButton.setForeground(Color.WHITE);

        medicineAddPanel.add(addButton);
        medicineAddPanel.add(removeButton);

        JPanel totalPanel = new JPanel();
        totalPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton printButton = new JButton("  Print  ");
        printButton.addActionListener(this);
        printButton.setBackground(Color.BLUE);
        printButton.setForeground(Color.WHITE);
//        printButton.setBorder(null);
        printButton.setFocusable(false);

        JButton setButton = new JButton("  Save  ");
        setButton.addActionListener(this);
        setButton.setBackground(Color.BLUE);
        setButton.setForeground(Color.WHITE);
//        printButton.setBorder(null);
        setButton.setFocusable(false);

        ImageIcon home = new ImageIcon("src/Images/home.png");
        Image home1 = home.getImage();
        Image home2 = home1.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon Home = new ImageIcon(home2);

        JButton cancelButton = new JButton("");
        totalPanel.add(cancelButton);
        cancelButton.addActionListener(this);
//        cancelButton.setBorder(null);
        cancelButton.setIcon(Home);
        cancelButton.setBackground(Color.WHITE);
        totalPanel.add(printButton);
        totalPanel.add(setButton);
        JLabel totalPrice = new JLabel("      Total Price : ");
        totalPanel.add(totalPrice);

        totalPriceField = new JTextField(10);
        totalPriceField.setFont(new Font("Arial", Font.BOLD, 20));
        totalPriceField.setText("0.0");
        totalPriceField.setEditable(false);
        totalPanel.add(totalPriceField);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial",Font.BOLD,16));
        table.getTableHeader().setReorderingAllowed(false);

        table.setFont(new Font("Arial", 0, 15));
        table.setRowHeight(22);
        medicineDetailsPanel.add(medicineAddPanel, BorderLayout.NORTH);
        medicineDetailsPanel.add(new JScrollPane(table), BorderLayout.CENTER);
        medicineDetailsPanel.add(totalPanel, BorderLayout.SOUTH);
        medicineDetailsPanel.setBorder(BorderFactory.createTitledBorder("Bill No: " + getBillNo() + "   "));

        add(medicineDetailsPanel);


        customerNameField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char ch = ke.getKeyChar();


                if (Character.isLetter(ch) || ch == ' ')
                {
                    return;
                }
                ke.setKeyChar('\b');
            }
        });






        customerPhoneField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char ch = ke.getKeyChar();
                String ph = customerPhoneField.getText();

                if (Character.isDigit(ch))
                {
                    if(ph.length()>=10) {
                        ke.setKeyChar('\b');
                    }
                    return;
                }

            }
        });


        customerAgeField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char ch = ke.getKeyChar();

                if (Character.isDigit(ch))
                {
                    return;
                }
                ke.setKeyChar('\b');
            }
        });

        pricribedBy.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char ch = ke.getKeyChar();

                if (Character.isLetter(ch) || ch == ' ')
                {
                    return;
                }
                ke.setKeyChar('\b');
            }
        });

        quantityField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent ke) {
                char ch = ke.getKeyChar();

                if (Character.isDigit(ch))
                {
                    return;
                }
                ke.setKeyChar('\b');
            }
        });

        medicineSearchField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                String s = medicineSearchField.getText();
               // sp.setVisible(true);

                try
                {
                    Connection con = SecondScreen.getConnection();

                    Statement smt = con.createStatement();

                    String qry = "SELECT `MEDICINE_NAME` FROM `medical_stock` WHERE `MEDICINE_NAME` LIKE '"+s+"%'";
                    ResultSet rs = smt.executeQuery(qry);

                    Dcb.removeAllElements();
                    Dcb.addElement("          ");
                    while (rs.next())
                    {
                        Dcb.addElement(rs.getString(1));
                       // System.out.println(rs.getString(1));
                    }


                    smt.close();
                    con.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null,"! UNKNOWN PROBLEM : .." + e);
                    return ;
                }


            }
        });

        setLocation(250,150);
        setSize(1300,1000);
     //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
       // this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        ImageIcon titleicon = new ImageIcon("src/Images/MainLogo.png");
        setIconImage(titleicon.getImage());

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if(e.getButton() == 1)
               {
                   String Phno = customerPhoneField.getText();
                   Connection con = SecondScreen.getConnection();
                   PreparedStatement ps = null;
                   ResultSet rs = null;


                   try {
                       ps = con.prepareStatement("select * from customerdetail where MobileNumber = ?");
                       ps.setString(1, Phno);

                       rs = ps.executeQuery();

                       boolean flag = rs.next();

                       if (flag == true) {


                           customerNameField.setText(rs.getString(2));
                           customerAddressField.setText(rs.getString(3));
                           pricribedByField.setText(rs.getString(4));
                           customerAgeField.setText(rs.getString(6));

                       } else
                       {
                           JOptionPane.showMessageDialog(null,"Customer Not Present For Given Mobile Number Enter All Details!!");
//                           customerNameField.setText("");
//                           customerAddressField.setText("");
//                           pricribedByField.setText("");
//                           customerAgeField.setText("");
//                           customerPhoneField.setText("");

                       }
                       rs.close();
                       con.close();
                   }
                   catch (Exception ex) {
                       ex.printStackTrace();
                   }

               }
            }
        });


    }

    ImageIcon logins = new ImageIcon("out/production/MAIN PROJECT/Images/Logins.png");
    Image logins1 = logins.getImage();
    Image logins2 = logins1.getScaledInstance(60,60,Image.SCALE_SMOOTH);
    ImageIcon Logins = new ImageIcon(logins2);

    public void actionPerformed(ActionEvent ae){

        String s = ae.getActionCommand();

        if(s.equals("  + Add  ")) {

           if(medicineSearchField.getText().isEmpty() || quantityField.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null,"! Please Enter Valid Medicine Name & quantity .","Valid Detalis",JOptionPane.INFORMATION_MESSAGE,Logins);
                return;
            }

            quantity = Integer.parseInt(quantityField.getText());
            if(quantity == 0)
            {
                JOptionPane.showMessageDialog(null,"! Invalid quantity .","Invalid Details",JOptionPane.INFORMATION_MESSAGE,Logins);
                return;
            }

            if(srNo >=2 )
            {
                int match = 1;
                while(match != srNo)
                {
                    String tempmedName = model.getValueAt(match-1,1).toString();
                    System.out.println(tempmedName);

                    if(medicineName.equals(tempmedName) )
                    {
                        JOptionPane.showMessageDialog(null,"! duplicate Entry of "+medicineName+"can not add .","Error",JOptionPane.INFORMATION_MESSAGE,Logins);
                        return;
                    }
                    match++;

                }
            }



            Statement smt = null;
            ResultSet rs = null;
            try
            {
                Connection con = SecondScreen.getConnection();

                String qry = "SELECT `MEDICINE_NAME`, `MRP`, `UNIT`, `EXP_DATE`, `QUANTITY` FROM `medical_stock` WHERE `MEDICINE_NAME` = '" +medicineName+"'" ;

                smt = con.createStatement();
                rs = smt.executeQuery(qry);



                while (rs.next())
                {
                    String str = rs.getString(1);
                    int availableQuantity = rs.getInt(5);
                    if(medicineName.equals(str))
                    {
                        if(availableQuantity >= quantity)
                        {
                            float mrp = rs.getFloat(2);
                            int unit = rs.getInt(3);
                            p = ((mrp/unit)*quantity);


                            totalAmount = Double.parseDouble(totalPriceField.getText());
                            totalAmount += p;
                            totalPriceField.setText(format("%.2f",totalAmount));

                            medicineSearchField.setText("");
                            quantityField.setText("");


                            ups[srNo-1] = new UpdateStock(medicineName,quantity,availableQuantity);
                            model.addRow(
                                    new Object[]{
                                            ""+srNo++,
                                            medicineName,
                                            rs.getDate(4),
                                            quantity,
                                            format("%05.2f",p)
                                    }
                            );
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Required Quantity of medicine is not available..");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Medicine is not available in store");
                    }
                }

                smt.close();
                con.close();
                return;
            }
            catch (Exception e)
            {
//                e.printStackTrace();
                JOptionPane.showMessageDialog(null,"! UNKNOWN PROBLEM : .." + e);
                return ;
            }
        }
            if(s.equals("Remove Selected Row"))
        {
            if(table.getSelectionModel().isSelectionEmpty() == true )
            {
                JOptionPane.showMessageDialog(null,"! Please Select Row from Table ....","Remove Row",JOptionPane.INFORMATION_MESSAGE,Logins);
                return ;
            }

            int n = Integer.parseInt(model.getValueAt(table.getSelectedRow(),0).toString());

            ups[n-1] = null;

            while(ups[n]!= null)
            {
                ups[n-1] = ups[n];
                n++;
            }

            double rprice = Double.parseDouble(model.getValueAt(table.getSelectedRow(),4).toString()) ;
            model.removeRow(table.getSelectedRow());
            srNo--;
            while(n!=srNo)
            {
                model.setValueAt((Object) n,n-1,0);
                n++;
            }

            Double d = Double.parseDouble(totalPriceField.getText());
            d = d - rprice;
            totalPriceField.setText(format(d.toString().format("%.2f",d)));
        }

        if(s.equals(""))
        {
            dispose();
            obj = null;

        }

        Random rand = new Random();

        if(s.equals("  Print  "))
        {
            System.out.println("print Button clicked");
/*
//            UpdateStock.updateQuantity(ups);

            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();

            //JsonParser jsonParser = new JsonParser();
           // jsonArray.();
           // jsonObject.put("total amount",totalPriceField.getText());
           // jsonObject.put("MEDICINES",model.getDataVector());

            Connection  con = SecondScreen.getConnection();

            PreparedStatement smt = null;
            Statement stmt = null;
            ResultSet rs = null;


            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            java.sql.Date date = new java.sql.Date(0000-00-00);
            Date now = new Date();
            String vDateYMD = formatter.format(now);

            customerName = customerNameField.getText();



            try
            {
//                stmt = con.createStatement();
//                rs = stmt.executeQuery("SELECT BILL_NO from sell_log ORDER by BILL_NO DESC LIMIT 1");
//
//               while(rs.next())
//               {
//                   cnt = rs.getInt(1);
//                   cnt++;
//               }




                String qry1 = "INSERT INTO `sell_log`(`DATE`, `BILL_NO`,`CUSTOMER_ID`, `LOG`) VALUES (?,?,?,?)";
               smt = con.prepareStatement(qry1);
//


                smt.setDate(1, java.sql.Date.valueOf(vDateYMD.toString()));
                smt.setInt(2,cnt);
                smt.setInt(3,1);
                //smt.setString(2,customerName);
                //smt.setFloat(4, (float) totalAmount);



               // JsonParser parser = new JsonParser(jsonObject);
                smt.setObject(4,jsonObject.toJSONString());

               // smt.executeUpdate();
              int i= smt.executeUpdate();

              System.out.println(i);

                smt.close();
                con.close();
            }catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,"! UNKNOWN PROBLEM : .." + e);
                return ;
//                e.printStackTrace();
            }

*/

//            System.out.println(jsonObject.toJSONString());
//            System.out.println(jsonObject);
            medicineAddPanel.setVisible(false);
            PrinterJob pjob = PrinterJob.getPrinterJob();
            PageFormat preformat = pjob.defaultPage();
            preformat.setOrientation(PageFormat.PORTRAIT);
            PageFormat postformat = pjob.pageDialog(preformat);
//If user does not hit cancel then print.
            if (preformat != postformat) {
                //Set print component
                pjob.setPrintable(this, postformat);
                if (pjob.printDialog()) {
                    try {
                        pjob.print();
                    } catch (PrinterException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        if(s.equals("  Save  "))
        {
            Connection con = SecondScreen.getConnection();
            PreparedStatement ps = null;
            Statement stmt = null;
            ResultSet rs = null;
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();


            UpdateStock.updateQuantity(ups);











































            jsonObject.put("total amount",totalPriceField.getText());
            jsonObject.put("MEDICINES",model.getDataVector());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


            java.sql.Date date = new java.sql.Date(0000-00-00);
            Date now = new Date();
            String vDateYMD = formatter.format(now);

            String CustomerName = customerNameField.getText();
            String CustomerAddress = customerAddressField.getText();
            String DrName = pricribedByField.getText();
            String PhoneNumber = customerPhoneField.getText();
            String CustomerAge = customerAgeField.getText();
            float totalprice = Float.parseFloat(totalPriceField.getText());

            System.out.println(totalprice);


            boolean status = false;


            try
            {
                String qry1 = "INSERT INTO `sell_log`(`DATE`, `BILL_NO`,`CUSTOMER_ID`,`TOTAL` ,`LOG`) VALUES (?,?,?,?,?)";
                ps = con.prepareStatement(qry1);

                ps.setDate(1, java.sql.Date.valueOf(vDateYMD.toString()));
                ps.setInt(2,cnt);
                ps.setInt(3,1);
                ps.setFloat(4, totalprice);

                // JsonParser parser = new JsonParser(jsonObject);
                ps.setObject(5,jsonObject.toJSONString());

                // smt.executeUpdate();
                int i= ps.executeUpdate();

                System.out.println(i);

               // ps.close();
                //con.close();
            }
            catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,"! UNKNOWN PROBLEM : .." + e);
                //return ;

            }


                try
                {
                    ps = con.prepareStatement("select * from customerdetail where MobileNumber = ?");
                    ps.setString(1,PhoneNumber);

                    rs = ps.executeQuery();

                   status = rs.next();

                    if(status == true)
                    {
                        JOptionPane.showMessageDialog(null,"Information Saved Successfully...");
                        return;
                    }

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }






            try
            {
                ps = con.prepareStatement("insert into customerdetail(CustomerName,Address,DoctorName,MobileNumber,Age) values(?,?,?,?,?)");

                ps.setString(1, CustomerName);
                ps.setString(2, CustomerAddress);
                ps.setString(3, DrName);
                ps.setString(4, PhoneNumber);
                ps.setString(5, CustomerAge);

                int i = ps.executeUpdate();

                if(i > 0)
                {
                    JOptionPane.showMessageDialog(null,"Information Saved Successfully...");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Failed to Save..");
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }


    }

    public static CreateOrder getCreateOrder(){                     //Singletone Method
        if (obj == null){
            synchronized(CreateOrder.class){
                if (obj == null){
                    obj = new CreateOrder();//instance will be created at request time
                }
            }
        }
        return obj;
    }

    private static CreateOrder obj = new CreateOrder();







    @Override
    public void itemStateChanged(ItemEvent e) {


        medicineName = (String)cb.getSelectedItem();
        //medicineSearchField.setText(str);
        //cb.setSelectedItem("");
    }





    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return Printable.NO_SUCH_PAGE;
        }

        // get the bounds of the component
        Dimension dim = this.getSize();
        double cHeight = dim.getHeight();
        double cWidth = dim.getWidth();

        // get the bounds of the printable area
        double pHeight = pageFormat.getImageableHeight();
        double pWidth = pageFormat.getImageableWidth();

        double pXStart = pageFormat.getImageableX();
        double pYStart = pageFormat.getImageableY();

        double xRatio = pWidth / cWidth;
        double yRatio = pHeight / cHeight;


        Graphics2D g2 = (Graphics2D) graphics;
        g2.translate(pXStart, pYStart);
        g2.scale(xRatio, yRatio);
        this.paint(g2);

        return Printable.PAGE_EXISTS;
    }

    static int getBillNo()
    {
        Connection con = SecondScreen.getConnection();
        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT BILL_NO from sell_log ORDER by BILL_NO DESC LIMIT 1");

            while (rs.next()) {
                cnt = rs.getInt(1);
                cnt++;
                System.out.println(cnt);

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e)
            {
                e.printStackTrace();
            }

        return cnt;





    }


}
