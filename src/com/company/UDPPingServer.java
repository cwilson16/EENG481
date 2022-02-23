package com.company;

import java.net.*;
import java.io.*;

public class UDPPingServer {

    public static void main(String[] args) {
        DatagramSocket serverPing; // testing netbeans/git in embedded lab
        DatagramPacket query, response;
        int serverPort = 12000;
        double rand;
        try {
            serverPing = new DatagramSocket(serverPort);
            while (true) {
                query = new DatagramPacket(new byte[128], 128);
                serverPing.receive(query);
                rand = 10*Math.random();
                if (rand<3) {
                    continue;
                }
                InetAddress clientIP = query.getAddress();
                int clientPort = query.getPort();
                System.out.println(new String(query.getData(), query.getOffset(), query.getLength()));
                response = new DatagramPacket(query.getData(), 0, query.getData().length, clientIP, clientPort);
                // constructor used above: DatagramPacket(byte[] buf, int offset, int length, InetAddress address, int port)
                serverPing.send(response);
            }
        } catch(IOException e) {
            System.out.println("Could not establish server!");
        }

    }

}
// does adding another line make a difference?
// we'll see