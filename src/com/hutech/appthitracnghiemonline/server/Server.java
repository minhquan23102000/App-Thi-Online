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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Server {

    public static Scanner sc = new Scanner(System.in);
    static final int port = 8888;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        //System.out.println("Server đang đợi kết nối!!!");
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);

            // running infinite loop for getting
            // client request
            System.out.println("Listening .........................");
            while (true) {

                // socket object to receive incoming client
                // requests
                Socket client = serverSocket.accept();

                // Displaying that new client is connected
                // to server
                //System.out.println("New client connected" + client.getInetAddress().getHostAddress());
                // create a new thread object
                ClientHandler clientSock = new ClientHandler(client);

                // This thread will handle the client
                // separately    
                new Thread(clientSock).start();

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        /*
        Socket sktg = null;
        int i = 1;
        while ((sktg = serverSocket.accept()) != null) {
            ServerThread serverThread = new ServerThread(sktg, "Client#" + i);
            System.out.printf("Thread for client$%d generating...", i++);
        }
         */
    }

    private static class ClientHandler implements Runnable {

        private final Socket clientSocket;
        private String name;
        DbRequester db = null;
        DeThi dethi = null;

        String[] arr = {null};
        String str = "";

        // Constructor
        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            ObjectOutputStream dout = null;
            ObjectInputStream din = null;

            try {
                //Tạo input stream, nối tới Socket
                din = new ObjectInputStream(clientSocket.getInputStream());
                //Tạo outputStream, nối tới socket
                dout = new ObjectOutputStream(clientSocket.getOutputStream());
                //System.out.println("" + in.available());

                //Đọc thông tin từ socket
                System.out.println("Server đã được kết nối!!!");

                str = din.readUTF();
                System.out.println(str);// In chuỗi gửi từ Client
//            while (din.available() > 0) {
//            System.out.println("Client gửi: " + str);
                arr = str.split("#");// Cắt được 3 chuỗi arr[1] = USERNAME, arr[2] = PASSWORD, arr[3] = CHUỖI SO SÁNH VÀO CHỨC NĂNG
                String result = "";

                if (null != arr && arr.length == 3) {
                    if (arr[2].equalsIgnoreCase("login")) {
                        try {
                            db = new DbRequester(arr[0], arr[1]);
                            System.out.println("ĐÃ KẾT NỐI");
                        } catch (Exception e) {
                            result = "DBError";
                            System.out.println("Loi ket noi database: " + e);
                        }
//                        if ((null == db) && !result.equals("DBError")) {
//                            result = "OK";
//                        } else if ((null != db) && !result.equals("DBError")) {
//                            result = db.toString();
//                        }

                        dout.writeUTF("1"); // Gửi kết quả về cho Client
                        dout.flush();
                    } else if (arr[0].equalsIgnoreCase("layDeThi")) {
                        String made = arr[1].trim();
                        
                        db = new DbRequester();
                        dethi = db.getDeThi(made);
                        System.out.println(dethi.tenDe);
                        dout.writeObject(dethi);
                        dout.flush();
                    }

                }
            while(true){
            //=====================================================
                int diem = 0;
                BaiThi bt = (BaiThi) din.readObject();
                System.out.print(bt.maBaiThi);
//for (int i = 0; i < dethi.dsCauHoi.size(); i++)
//    if (bt.dsCauLam.get(i).cauHoi.maCauHoi == dethi.dsCauHoi.get(i).maCauHoi && bt.dsCauLam.get(i).cauChon == dethi.dsCauHoi.get(i).cauDung)
//    {
//        diem ++;        
//    }
                bt.ketQua = bt.tinhDiem();
                System.out.print(bt.ketQua);
                db.luuBaiLam(bt);
//                dout.writeObject(bt);
                
//=====================================================
                db.closeConn();
                break;    }
/*
                if (null != arr && arr.length == 3) {
                    if (arr[0].equalsIgnoreCase("layDeThi")) {
                        dethi = db.getDeThi(arr[1]);
                        System.out.println(dethi.tenDe);
                        dout.writeObject(dethi);
                        dout.flush();
                    }
                }
*/
//            System.out.println(db.getListMaDe().toString());
//            System.out.println(dethi.toString());
//                BaiThi baiThi = new BaiThi("1443", dethi.maDe);
//                for (CauHoi c : dethi.dsCauHoi) {
//                    CauLam cauLam = new CauLam(c, 1);
//                    baiThi.dsCauLam.add(cauLam);
//                }
//                    dout.writeObject(dethi);
//                db.luuBaiLam(baiThi);
//                }
//            }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (dout != null) {
                        dout.close();
                    }
                    if (din != null) {
                        din.close();
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
