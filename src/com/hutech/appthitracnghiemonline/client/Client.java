/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hutech.appthitracnghiemonline.client;

import com.hutech.appthitracnghiemonline.server.model.DeThi;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author TADA
 */
public class Client {
    public static Client c = new Client();
    public static Socket client = null;
    public static int port = 8888;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        client = new Socket("localhost", port);              
        ObjectOutputStream dout = new ObjectOutputStream(client.getOutputStream());
        ObjectInputStream din = new ObjectInputStream(client.getInputStream());
        
        
        System.out.println("Nội dung bài thi: 10 câu trắc nghiệm - mỗi câu 30 giây làm bài");
        DeThi test = (DeThi)din.readObject();
        System.out.println(test);
        
    }
}
