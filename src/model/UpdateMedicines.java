package model;


import ui.SecondScreen;

import javax.swing.*;
import java.sql.*;

public class UpdateMedicines {

    String medicine;
    int quantity;
    //Date expDate ;
    String expDate;
    int unit;
    double mrp;
    String agency;
    double price;
    int minimumQuantity;

    public UpdateMedicines(String medicine, int quantity, String expDate, int unit, double mrp, String agency, double price, int minimumQuantity) {
        this.medicine = medicine;
        this.quantity = quantity * unit;
//        try{
//            this.expDate =  new SimpleDateFormat("yyyy-MM-dd").parse(expDate.toString());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        this.expDate = expDate;

        this.unit = unit;
        this.mrp = mrp;
        this.agency = agency;
        this.price = price;
        this.minimumQuantity = minimumQuantity;

        System.out.println(expDate);
    }

    public boolean decide() {
        Connection con = SecondScreen.getConnection();

        Statement smt = null;
        ResultSet rs = null;
        try {

            String qry = "SELECT * FROM `medical_stock` WHERE `MEDICINE_NAME` = '" + medicine + "' ORDER BY `MEDICINE_NAME` ASC";
            smt = con.createStatement();
            rs = smt.executeQuery(qry);


            System.out.println("OKKK");

            while (rs.next()) {
                if (rs.getString(1).equals(medicine)) {
                    smt.close();
                    con.close();
                    return true;
                }
                // return false;

            }

//            if(rs.getString(1).equals(medicine)){
//                System.out.println("true");
//                return true;
//            }
            smt.close();
            con.close();
            return false;


        } catch (Exception e) {

            //System.out.println("False");
            JOptionPane.showMessageDialog(null,"! UNKNOWN PROBLEM : .." + e);

           // e.printStackTrace();
            //return false;
        }

        return false;
    }

    public void update() {
        Connection con = SecondScreen.getConnection();

        Statement smt = null;
       // ResultSet rs = null;

        try {
            int qt = getQuantity(con);

            System.out.println(qt);
           // System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            String qry = "UPDATE `medical_stock` SET `MRP` = '" + mrp + "', `QUANTITY` = '" + (quantity + qt) + "', `UNIT` = '" + unit + "', `EXP_DATE` = '" + expDate + "', `PRICE` = '" + price + "', `MINIMUM_QUANTITY` = '" + minimumQuantity + "' ,`AGENCY` = '" + agency+"' WHERE `medical_stock`.`MEDICINE_NAME` = '" + medicine + "'";



                smt = con.createStatement();
                smt.executeUpdate(qry);
           //PreparedStatement pr = con.prepareStatement(qry);
            JOptionPane.showMessageDialog(null,"!!!UPDATED SUCCESSFULLY");
           // System.out.println("!!!!updated row successfully");
            smt.close();
            con.close();

        }catch (SQLSyntaxErrorException sse) {
            JOptionPane.showMessageDialog(null,"! Wrong Sql Syntax  .." + sse);
            return ;

           // sse.printStackTrace();

        }
        catch (Exception e) {

            JOptionPane.showMessageDialog(null,"! UNKNOWN PROBLEM : .." + e);
            return ;
           //e.printStackTrace();
            //rs.close();
           // con.close();
        }
        finally {
          // rs.close();
           //con.close();
        }

        //con.close();
    }

    public void insert() {
        Connection con = SecondScreen.getConnection();

        Statement smt = null;
       // ResultSet rs = null;
        try {
            smt = con.createStatement();
            String qry = "INSERT INTO `medical_stock`  VALUES ('" + medicine + "', '" + mrp + "', '" + quantity + "', '" + unit + "', '"+expDate+"', '" + agency + "', '" + price + "', '" + minimumQuantity + "')";
            smt.executeUpdate(qry);

           // System.out.println("!!!INSERTED SUCCESSFULLY");
            JOptionPane.showMessageDialog(null,"!!!INSERTED SUCCESSFULLY");


           smt.close();
            con.close();
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"! UNKNOWN PROBLEM : .." + e);
            return ;
        }
    }


    int getQuantity(Connection con) {


        Statement smt = null;
        ResultSet rs = null;
        try {



            String qry = "SELECT `QUANTITY` FROM `medical_stock` WHERE `MEDICINE_NAME` = '" + medicine + "'";

            smt = con.createStatement();



            rs = smt.executeQuery(qry);



            while (rs.next()) {

                String str =  rs.getString(1);

                System.out.println(str);
               //smt.close();
                //con.close();
                return Integer.parseInt(str);
            }

            //String qry = "SELECT * FROM `medical_stock` WHERE `MEDICINE_NAME` LIKE `"+medicine+"` ORDER BY `MEDICINE_NAME` ASC";


        } catch (SQLException e) {


            JOptionPane.showMessageDialog(null,"! UNKNOWN PROBLEM : .." + e);
        }


        return 0;
    }


}

