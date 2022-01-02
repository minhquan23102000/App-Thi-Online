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

                //TADA xử lý đăng nhập----------------------------------------
                String[] arr = {null};
                while (din.available() != 0) {
                    String str = din.readUTF();
                    System.out.println("Client gui:" + str);
                    arr = str.split("#");
                    String result = "";

                    if (null != arr && arr.length == 2) {
                        String sst = "";
                        try {
                            sst = db.Login(arr[0], arr[1]);
//                            System.out.println(sst);
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Loi ket noi database: " + e);
                        }
                        if ((null == sst || sst.isEmpty())
                                && !result.equals("DBError")) {
                            result = "OK";
                        } else if ((null != sst && sst.isEmpty())
                                && !result.equals("DBError")) {
                            result = sst;
                        }
//                        System.out.print(sst);
                        dout.writeUTF(sst);
                        dout.flush();
                    }
                }

                //TADA xử lý đề thi---------------------------------------------------------------
                DeThi dethi = null;
                String made = din.readUTF();              
                String[] a = {null};
                while (din.available() != 0) {
                    String st = din.readUTF();
                    st = st.trim();
                    System.out.println("Client gửi:" + st);
                    a = st.split("#");
                    String result = "";

                    if (null != arr && arr.length == 1) {
                        
                        try {
                            dethi = db.getDeThi(a[0]);
//                            System.out.println(sst);
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Loi ket noi database: " + e);
                        }
                        if ((null == dethi)
                                && !result.equals("DBError")) {
                            result = "OK";
                        } else if ((null != dethi)
                                && !result.equals("DBError")) {
                            result = (String)dethi.toString();
                        }
//                        System.out.print(sst);
                        dout.writeObject(dethi);
                        dout.flush();
                    }
                }  
                System.out.println(db.getListMaDe().toString());
                System.out.println(dethi.toString());
//                BaiThi baiThi = new BaiThi("1443", dethi.maDe);
//                for (CauHoi c : dethi.dsCauHoi) {
//                    CauLam cauLam = new CauLam(c, 1);
//                    baiThi.dsCauLam.add(cauLam);
//                }

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
