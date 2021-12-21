/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class DeThi {
    public String maDe;
    public String tenDe;
    public List<CauHoi> dsCauHoi;

    public DeThi(String maDe, String tenDe, List<CauHoi> dsCauHoi) {
        this.maDe = maDe;
        this.tenDe = tenDe;
        this.dsCauHoi = dsCauHoi;
    }
    public DeThi() {
        this.dsCauHoi = new ArrayList<>();
    }

    @Override
    public String toString() {
        String str = "";
        str += "De Thi: " + this.tenDe + "\n";
        int i = 1;
        for (CauHoi cauHoi : dsCauHoi) {
            str+= "Cau " + i + ": ";
            str += cauHoi.toString();
            str += "\n";
            i++;
        }
        return str;
    }
    
}
