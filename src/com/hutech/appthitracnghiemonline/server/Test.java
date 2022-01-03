/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server;

import com.hutech.appthitracnghiemonline.server.model.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class Test {

    public static Scanner sc = new Scanner(System.in);
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, Exception {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("Server đang đợi kết nối!!!");
        while (true) {
            try (Socket sktg = server.accept()) {
                System.out.println("Server đã được kết nối!!!");
                ObjectInputStream din = new ObjectInputStream(sktg.getInputStream());
                ObjectOutputStream dout = new ObjectOutputStream(sktg.getOutputStream());
//                DbRequester db = new DbRequester();
                int i;
                i = din.readInt();
                int n = 0;
                switch (i) {
                    case 1:
                        SinhVien sv = new SinhVien();
                        sv = (SinhVien)din.readObject();
                        System.out.println(sv);
                        DbRequester dao = new DbRequester();
                        dao.insertsv(sv);
                        System.out.println("đã lưu thành công");
                        dout.writeInt(n);
                        //-------------------            
                        break;
                             
                }
//                DeThi dethi = db.getDeThi("T001");
//                System.out.println(db.getListMaDe().toString());
//                System.out.println(dethi.toString());
//                BaiThi baiThi = new BaiThi("1443", dethi.maDe);
//                for (CauHoi c : dethi.dsCauHoi) {
//                    CauLam cauLam = new CauLam(c, 1);
//                    baiThi.dsCauLam.add(cauLam);
            }
//            dout.writeObject(dethi);
////                db.luuBaiLam(baiThi);
//            db.closeConn();
        }
    }  
}

