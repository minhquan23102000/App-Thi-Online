/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server;
import com.hutech.appthitracnghiemonline.server.model.*;
import java.util.*;
/**
 *
 * @author PC
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DbRequester db = new DbRequester();
        DeThi dethi = db.getDeThi("T001");
//        
        //System.out.println(db.getListMaDe().toString());
//        System.out.println(dethi.toString());

//        BaiThi baiThi = new BaiThi("1443", dethi.maDe );
//        
//        for (CauHoi c : dethi.dsCauHoi) {
//            CauLam cauLam = new CauLam(c, 1);
//            baiThi.dsCauLam.add(cauLam);
//        }
//        
//        baiThi.tinhDiem();
//        db.luuBaiLam(baiThi);
        
        
        db.closeConn();
    }
    
}
