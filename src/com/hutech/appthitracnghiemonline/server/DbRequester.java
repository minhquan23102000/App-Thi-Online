/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server;

import com.hutech.appthitracnghiemonline.server.model.*;
import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author PC
 */
public class DbRequester {

    private Connection con;
    private Statement stmt;

    public DbRequester() {
        try {
            DbConnector connector = new DbConnector();
            con = connector.getConnection();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public DbRequester(String username, String password) {
        try {
            DbConnector connector = new DbConnector();
            con = connector.getConnection(username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Hàm lấy đề thi, truyền vào tham số mã đề, kết quả trả về đối tượng DeThi.
     *
     */
    public DeThi getDeThi(String maDe) {
        DeThi dethi = new DeThi();
        try {
            stmt = con.createStatement();
            String query = "SELECT * FROM DeThi WHERE maDe = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, maDe);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                //Get de thi
                dethi.maDe = rs.getString("maDe");
                dethi.tenDe = rs.getNString("tenDe");
                System.out.println(dethi.toString());

                //Get danh sach cau hoi cua de thi
                String cauhoiQuery = "SELECT * FROM CauHoi WHERE maDe = '" + maDe + "'";
                ResultSet newrs = stmt.executeQuery(cauhoiQuery);
                while (newrs.next()) {
                    int maCauHoi = newrs.getInt("maCauHoi");
                    String noiDung = newrs.getString("noiDung");
                    String cauA = newrs.getString("cauA");
                    String cauB = newrs.getString("cauB");
                    String cauC = newrs.getString("cauC");
                    String cauD = newrs.getString("cauD");
                    int cauDung = newrs.getInt("cauDung");

                    CauHoi cauhoi = new CauHoi(maCauHoi, noiDung, cauA, cauB, cauC, cauD, cauDung);
                    dethi.dsCauHoi.add(cauhoi);
                }

            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(DbRequester.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dethi;
    }

    public int ktTonTaiTrongBD(String ma) {
        con = DbConnector.getConnection();
        String sql = "SELECT * FROM SINHVIEN WHERE MASSV='" + ma + "'";
        int tonTai = 0;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tonTai = 1;
            }
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tonTai;
    }
    //

    public boolean insertsv(SinhVien sinhvien) throws Exception {
        con = DbConnector.getConnection();
        String sql = "insert into sinhvien(ho, ten, sdt, mssv) values(?,?,?,?)";
        try (
                PreparedStatement ps = con.prepareStatement(sql);) {
            ps.setString(1, sinhvien.ho);
            ps.setString(2, sinhvien.ten);
            ps.setString(3, sinhvien.sdt);
            ps.setString(4, sinhvien.mssv);

            return ps.executeUpdate() > 0;
        }
    }

    /**
     * Hàm lấy danh sách mã đề thi trong db;
     *
     */
    public List<String> getListMaDe() {
        List<String> ds = new ArrayList<>();
        String query = "SELECT * FROM DeThi";
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                ds.add(rs.getString("maDe").trim());
            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(DbRequester.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return ds;
    }

    /**
     * Hàm lưu kết quả bài thi, nhận một đối tượng BaiThi;
     *
     */
    public void luuBaiLam(BaiThi baiThi) {
        try {

            //Init statement
            stmt = con.createStatement();
            //Init query
            String query = "INSERT INTO BaiThi (mssv, maDe, ketQua)  VALUES (?, ? ,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, baiThi.mssv);
            preparedStatement.setString(2, baiThi.maDe);
            preparedStatement.setFloat(3, baiThi.ketQua);
            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected != -1) {
                Logger.getLogger(DbRequester.class.getName()).log(Level.SEVERE, null, "Luu data thanh cong");
                //Luu cau lam
//                for (CauLam c : baiThi.dsCauLam) {
//                    query = "INSERT INTO CauLam (maBaiThi, maCauHoi, cauChon)  VALUES (?, ? ,?)";
//                    preparedStatement = con.prepareStatement(query);
//                    preparedStatement.setInt(1, baiThi.maBaiThi);
//                    preparedStatement.setInt(2, c.cauHoi.maCauHoi);
//                    preparedStatement.setInt(3, c.cauChon);
//                    preparedStatement.executeUpdate();
//                }

            } else {
                Logger.getLogger(DbRequester.class.getName()).log(Level.SEVERE, null, "Luu data khong thanh cong");
            }

            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
<<<<<<< Updated upstream
    
    public String Login(String UserName, String Password) throws SQLException, ClassNotFoundException, Exception {
        stmt = con.createStatement();
        String query = "SELECT mssv, password FROM SinhVien where [mssv] =? and [password] =?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, UserName);
            preparedStatement.setString(2, Password);

            ResultSet rs = preparedStatement.executeQuery();

            String result = "";
            if (rs.next()) {
                return result = "1";
            } else {
                return result = "0";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
=======

>>>>>>> Stashed changes
    public void closeConn() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
