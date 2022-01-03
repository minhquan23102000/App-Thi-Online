/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server.model;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class SinhVien implements Serializable{
    public String mssv;
    public String sdt;
    public String password;
    public String ho;
    public String ten;

    public SinhVien(String mssv, String sdt, String password, String ho, String ten) {
        this.mssv = mssv;
        this.sdt = sdt;
        this.password = password;
        this.ho = ho;
        this.ten = ten;
    }

    public SinhVien() {
    }

    public String getMssv() {
        return mssv;
    }

    public void setMssv(String mssv) {
        this.mssv = mssv;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    
    
}
