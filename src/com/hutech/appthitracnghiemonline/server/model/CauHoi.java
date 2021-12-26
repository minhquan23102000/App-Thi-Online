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
public class CauHoi implements  Serializable{
    public static final int CAU_A = 1;
    public static final int CAU_B = 2;
    public static final int CAU_C = 3;
    public static final int CAU_D = 4;
    
    public int maCauHoi;
    public String noiDung;
    public String cauA;
    public String cauB;
    public String cauC;
    public String cauD;
    public int cauDung;

    public CauHoi(int maCauHoi, String noiDung, String cauA, String cauB, String cauC, String cauD, int cauDung) {
        this.maCauHoi = maCauHoi;
        this.noiDung = noiDung;
        this.cauA = cauA;
        this.cauB = cauB;
        this.cauC = cauC;
        this.cauD = cauD;
        this.cauDung = cauDung;
    }
    
    public CauHoi(){}

    @Override
    public String toString() {
        return this.noiDung + "\n" + "Câu A: " + this.cauA + "\n" + "Câu B: " + this.cauB
                + "\n" + "Câu C: " + this.cauC + "\n" + "Câu D: " + this.cauD + "\n";
    }
    
    
}
