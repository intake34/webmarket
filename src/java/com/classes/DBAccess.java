package com.classes;
        
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author antonios
 */
public class DBAccess {

    String databaseName = "commerce";
    String databaseUsername = "root";
    String databasePassword = "1234";
    
    Connection con = null;
    ResultSet rst = null;

    public Connection StartConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, databaseUsername, databasePassword);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return con;
    }

    public Vector getAllProducts() throws SQLException {

        Vector<Product> products = new Vector<>();
        con = StartConnection();
        
        if (con != null) {

            try {
                PreparedStatement pst = con.prepareCall("SELECT * FROM product");
                rst = pst.executeQuery();

                if (rst.first()) {
                    do {
                        Product prod = new Product(rst.getInt("id"),
                                rst.getString("name"),
                                rst.getString("desc"),
                                rst.getFloat("price"),
                                rst.getInt("quantity"),
                                rst.getInt("c_id"),
                                rst.getString("picture")
                        );

                        products.add(prod);

                    } while (rst.next());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            con.close();
        }

        return products;
    }



public boolean deleteProduct(int id) throws SQLException
{
con = StartConnection();
        boolean is_deleted=true;
        if (con != null) {

            try {
                PreparedStatement pst = con.prepareCall("Delete from product  where id = ?");
                pst.setInt(1, id);
                is_deleted = pst.execute();

              
                   

                   
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            con.close();
        }

        return is_deleted;
    }

public static int getUserInfo()
{
   return 5;
}



}
