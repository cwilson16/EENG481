package com.company;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket clientSocket;
        DatagramPacket query, response;
        Scanner input = new Scanner(System.in); // couldn't import textIO library...
        String message = input.nextLine(); // so i'm trying to do it with scanner.
        //System.out.println(message.toUpperCase(Locale.ROOT));

        try {
            int serverPort = 12000;
            InetAddress serverIP = InetAddress.getByName("localhost");
            clientSocket = new DatagramSocket();
            query = new DatagramPacket (message.getBytes(), message.getBytes().length, serverIP, serverPort);
            clientSocket.send(query);
            response = new DatagramPacket(new byte[1024], 1024);
            clientSocket.receive(response);
            String testResponse = new String(response.getData(), response.getOffset(), response.getLength());
            System.out.println(testResponse);
            //System.out.println(new String(response.getData()));
            clientSocket.close();
        }
        catch (IOException e) {
            System.out.println("Failed to communicate with server.");
        }
    }
}