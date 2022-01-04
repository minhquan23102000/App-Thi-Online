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

    //Menu cac chuc nang
    public static final int LOGIN = 0;
    public static final int XACNHANSINHVIEN = 1;
    public static final int LAYDETHI = 2;
    public static final int GUIDETHI = 3;

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
            //Dong ket noi
//            if (serverSocket != null) {
//                try {
//                    serverSocket.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
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
        ObjectOutputStream dout = null;
        ObjectInputStream din = null;

        // Constructor
        public ClientHandler(Socket socket) throws IOException {
            this.clientSocket = socket;
            //Tạo input stream, nối tới Socket
            din = new ObjectInputStream(clientSocket.getInputStream());
            //Tạo outputStream, nối tới socket
            dout = new ObjectOutputStream(clientSocket.getOutputStream());
            System.out.println("Server đã được kết nối!!!");
        }

        public void run() {

            try {
                while (true) {
                    int choice = din.readInt();
                    switch (choice) {
                        case LOGIN:
                            String str = din.readUTF().trim();
                            String[] arr = str.split("#");
                            db = new DbRequester(arr[0], arr[1]);
                            if (db.isConnected()) {
                                dout.writeUTF("1");
                            } else {
                                dout.writeUTF("0");
                            }
                            dout.flush();
                            break;

                        case XACNHANSINHVIEN:
                            SinhVien sv = new SinhVien();
                            sv = (SinhVien) din.readObject();
                            int n = db.ktTonTaiTrongBD(sv.mssv);
     
                            dout.writeInt(n);
                            dout.flush();
                            //-------------------            
                            break;

                        case LAYDETHI:
                            String made = din.readUTF().trim();

                            dethi = db.getDeThi(made);
                            System.out.println(dethi.tenDe);
                            dout.writeObject(dethi);
                            dout.flush();
                            break;

                        case GUIDETHI:
                            BaiThi bt = (BaiThi) din.readObject();
                            System.out.print(bt.maBaiThi);
                            bt.ketQua = bt.tinhDiem();
                            System.out.print(bt.ketQua);
                            db.luuBaiLam(bt);
                            System.out.println("Luu bai thi thanh cong cho sinh vien: " +bt.mssv);
                            break;

                    }
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (db.isConnected()) {
                        db.closeConn();
                    }
                        
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
