/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PC
 */
public class DeThi implements  Serializable {
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
        str += "Đề Thi: " + this.tenDe + "\n";
        int i = 1;
        for (CauHoi cauHoi : dsCauHoi) {
            str+= "Câu " + i + ": ";
            str += cauHoi.toString();
            str += "\n";
            i++;
        }
        return str;
    }
    
}
