package model;

import ui.CreateOrder;
import ui.SecondScreen;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.LinkedTransferQueue;

public class UpdateStock  extends JOptionPane {

    int srNo;
    String medicineName;

    int quantity;
    int avaliableQuantity;


    public UpdateStock(String medicineName,  int quantity,int avalableQuantity)
    {
        this.srNo = srNo;
        this.medicineName=medicineName;
        this.quantity = quantity;
        this.avaliableQuantity = avalableQuantity;
    }


    public static void updateQuantity(UpdateStock ar[])
    {
        Connection con = SecondScreen.getConnection();

        for(int i = 0 ; i < ar.length; i++)
        {

            if(ar[i] != null)
            {
                Statement smt = null;

                try
                {
                    int tempqt = ar[i].avaliableQuantity-ar[i].quantity;

                    String uqry = "UPDATE `medical_stock` SET `QUANTITY`='"+tempqt+"' WHERE `MEDICINE_NAME` = '" +ar[i].medicineName +"'";

                    smt = con.createStatement();
                    smt.executeUpdate(uqry);
                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"! UNKNOWN PROBLEM : .." + e);
                    return ;
                }

            }
            else
            {
                continue;
            }


        }



    }





}
