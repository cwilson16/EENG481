package com.company;

import java.net.*;
import java.io.*;
public class UDPServer {
    public static void main(String[] args) {
        int serverPort;
        DatagramSocket serverSocket;
        DatagramPacket query, response;
        serverPort = 12000;
        try {
            serverSocket = new DatagramSocket(serverPort);
            while (true) {
                query = new DatagramPacket(new byte[1024], 1024);
                serverSocket.receive(query);
                InetAddress clientIP = query.getAddress();
                System.out.println("Query received from this IP address: " + clientIP);
                int clientPort = query.getPort();
                String message = new String(query.getData(), query.getOffset(), query.getLength());
                message = message.toUpperCase();
                byte[] byteArray = message.getBytes();
                response = new DatagramPacket(byteArray, 0, byteArray.length, clientIP, clientPort);
                serverSocket.send(response);
            }
        }
        catch (IOException e)
        {
            System.out.println("Port Unavailable.");
        }
    }
}