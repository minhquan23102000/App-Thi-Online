/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hutech.appthitracnghiemonline.server;
import com.hutech.appthitracnghiemonline.server.model.BaiThi;
import com.hutech.appthitracnghiemonline.server.model.CauHoi;
import com.hutech.appthitracnghiemonline.server.model.CauLam;
import com.hutech.appthitracnghiemonline.server.model.DeThi;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author PC
 */
public class ServerThread implements Runnable {
    private Socket s;
    private String name;
    private ObjectInputStream din;
    private ObjectOutputStream dout;
    
    public ServerThread(Socket clientSocket, String clientName) throws IOException {
        this.s = clientSocket;
        this.name = clientName;
        din = new ObjectInputStream(s.getInputStream());
        dout = new ObjectOutputStream(s.getOutputStream());
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Server đã được kết nối!!!");

                DbRequester db = new DbRequester("yup", "123456");
                DeThi dethi = db.getDeThi("T001");

                System.out.println(db.getListMaDe().toString());
                System.out.println(dethi.toString());
                BaiThi baiThi = new BaiThi("1443", dethi.maDe);
                for (CauHoi c : dethi.dsCauHoi) {
                    CauLam cauLam = new CauLam(c, 1);
                    baiThi.dsCauLam.add(cauLam);
                }

                dout.writeObject(dethi);
//                db.luuBaiLam(baiThi);
                db.closeConn();
            }
        } catch (Exception e) {
            System.out.println(name + "has departed");
        } finally {
            try {
                s.close();
            } catch (IOException e) {
            }
        }
    }
}
