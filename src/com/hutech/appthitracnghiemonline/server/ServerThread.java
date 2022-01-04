///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.hutech.appthitracnghiemonline.server;
//
//import com.hutech.appthitracnghiemonline.server.model.BaiThi;
//import com.hutech.appthitracnghiemonline.server.model.CauHoi;
//import com.hutech.appthitracnghiemonline.server.model.CauLam;
//import com.hutech.appthitracnghiemonline.server.model.DeThi;
//import java.net.*;
//import java.io.*;
//import java.util.*;
//
///**
// *
// * @author PC
// */
//public class ServerThread implements Runnable {
//
//    private Socket s;
//    private String name;
//    private ObjectInputStream din;
//    private ObjectOutputStream dout;
//
//    public ServerThread(Socket clientSocket, String clientName) throws IOException {
//        this.s = clientSocket;
//        this.name = clientName;
//        din = new ObjectInputStream(s.getInputStream());
//        dout = new ObjectOutputStream(s.getOutputStream());
//        new Thread(this).start();
//    }
//
//    @Override
//    public void run() {
//        DbRequester db = null;
//        DeThi dethi = null;
//        try {
////            while (true) {
//            System.out.println("Server đã được kết nối!!!");
//            String[] arr = {null};
//            String str = "";
//            str = din.readUTF();
//            System.out.println(str);// In chuỗi gửi từ Client
////            while (din.available() > 0) {
////            System.out.println("Client gửi: " + str);
//                arr = str.split("#");// Cắt được 3 chuỗi arr[1] = USERNAME, arr[2] = PASSWORD, arr[3] = CHUỖI SO SÁNH VÀO CHỨC NĂNG
//                String result = "";
//
//                if (null != arr && arr.length == 3) {
//                    if (arr[2].equalsIgnoreCase("login")) {
//                        try {
//                            db = new DbRequester(arr[0], arr[1]);
//                            System.out.println("ĐÃ KẾT NỐI");
//                        } catch (Exception e) {
//                            result = "DBError";
//                            System.out.println("Loi ket noi database: " + e);
//                        }
////                        if ((null == db) && !result.equals("DBError")) {
////                            result = "OK";
////                        } else if ((null != db) && !result.equals("DBError")) {
////                            result = db.toString();
////                        }
//
//                        dout.writeUTF("1"); // Gửi kết quả về cho Client
//                        dout.flush();
//                    }
//                }
//
//                if (null != arr && arr.length == 3) {
//                    if (arr[0].equalsIgnoreCase("layDeThi")) {
//                        dethi = db.getDeThi(arr[1]);
//                        dout.writeObject(dethi);
//                        dout.flush();
//                    }
//                }
//
////            System.out.println(db.getListMaDe().toString());
////            System.out.println(dethi.toString());
////                BaiThi baiThi = new BaiThi("1443", dethi.maDe);
////                for (CauHoi c : dethi.dsCauHoi) {
////                    CauLam cauLam = new CauLam(c, 1);
////                    baiThi.dsCauLam.add(cauLam);
////                }
////                    dout.writeObject(dethi);
////                db.luuBaiLam(baiThi);
////                }
////            }
//
//        } catch (Exception e) {
//            System.out.println(name + "has departed");
//        }
////        finally {
////            try {
////                s.close();
////            } catch (IOException e) {
////            }
////        }
////                    db.closeConn();
//    }
//
//}
