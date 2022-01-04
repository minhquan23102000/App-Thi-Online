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
public class CauLam implements Serializable {
    
    //Possible value for cauChon
    public static final int CAU_A = 1;
    public static final int CAU_B = 2;
    public static final int CAU_C = 3;
    public static final int CAU_D = 4;
    
    //Class atribute;
    public CauHoi cauHoi;
    public int cauChon;
    
    public CauLam() {}

    public CauLam(CauHoi cauHoi, int cauChon) {
        this.cauHoi = cauHoi;
        this.cauChon = cauChon;
    }
    
    
}
