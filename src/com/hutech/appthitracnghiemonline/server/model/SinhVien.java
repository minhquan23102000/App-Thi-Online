/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server.model;

/**
 *
 * @author PC
 */
public class SinhVien {
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
    
    
}
