package com.company;

import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class UDPPingClient {

    public static void main(String[] args) {
        DatagramSocket clientSocket;
        DatagramPacket query, response;
        Scanner input = new Scanner(System.in); // couldn't import textIO library...
        //String message = input.nextLine(); // so i'm trying to do it with scanner.
        //System.out.println(message.toUpperCase(Locale.ROOT));

        for(int i = 0; i < 10; i++) {
            try {
                Date now = new Date();
                String message = "Ping " + i + " " + now.toString();
                int serverPort = 12000;
                //byte[] ethanIP = new byte[]{byte 147, 222, 178, 209};
                //InetAddress serverIP = InetAddress.getByName("147.222.178.209");
                InetAddress serverIP = InetAddress.getByName("localhost");
                clientSocket = new DatagramSocket();
                clientSocket.setSoTimeout(1000);
                query = new DatagramPacket (message.getBytes(), message.getBytes().length, serverIP, serverPort);
                double timeSent = System.nanoTime();
                clientSocket.send(query);
                response = new DatagramPacket(new byte[1024], 1024);
                try{
                    clientSocket.receive(response);
                    double timeReceived = System.nanoTime();
                    double roundTripTime = (timeReceived - timeSent) / 1000000;
                    String testResponse = new String(response.getData(), response.getOffset(), response.getLength());
                    System.out.println("Response: " + testResponse);
                    System.out.println("Round Trip time was: " + roundTripTime + " milliseconds.");
                } catch(IOException SoTimeout){
                    System.out.println("Packet: "+ i + " timed out");
                }
                clientSocket.close();
            }
            catch (IOException e) {
                System.out.println("Failed to communicate with server.");
            }
        }





    }

}
