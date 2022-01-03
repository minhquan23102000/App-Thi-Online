/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class DbConnector {
    public static Connection getConnection() {
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;"
                        + "database=BAITHI;"
                        + "user=VANHA;"
                        + "password=123456;"
                        + "loginTimeout=30;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Start to connect to database");
            Connection con = DriverManager.getConnection(connectionUrl);
            System.out.println("Get connection successfully");
            return con;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
      public Connection getConnection(String username, String password) {
        String connectionUrl =
                "jdbc:sqlserver://localhost:1433;"
                        + "database=BAITHI;"
                        + "user=" +username + ";"
                        + "password=" + password + ";"
                        + "loginTimeout=30;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Start to connect to database");
            Connection con = DriverManager.getConnection(connectionUrl);
            System.out.println("Get connection successfully");
            return con;
        } catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.toString(),"Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
            return null;
        }
        
    }
}
