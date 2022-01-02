/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server.model;

import java.util.*;
/**
 *
 * @author PC
 */
public class BaiThi {
    public int maBaiThi;
    public String mssv;
    public String maDe;
    public float ketQua;
    
    public List <CauLam> dsCauLam;
    
    public  BaiThi() {
        dsCauLam = new ArrayList<>();
    }

    public BaiThi(int maBaiThi, String mssv, String maDe, float ketQua, List<CauLam> dsCauLam) {
        this.maBaiThi = maBaiThi;
        this.mssv = mssv;
        this.maDe = maDe;
        this.ketQua = ketQua;
        this.dsCauLam = dsCauLam;
    }
    
     public BaiThi(String mssv, String maDe) {
        this.mssv = mssv;
        this.maDe = maDe;
        this.dsCauLam = new ArrayList<>();
    }
    
    public float tinhDiem() {
        float diem = 0;
        for (CauLam cauLam : dsCauLam) {
            if (cauLam.cauChon == cauLam.cauHoi.cauDung) {
                diem += 1;
            }
        }
        this.ketQua = diem;
        return diem;
    }
    
}
