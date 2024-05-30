package com.esley.client_service;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientService extends Thread {
    Socket client;

    public ClientService(Socket cli) {
        this.client = cli;
    }

    @Override
    public void run() {

        System.out.println("Cliente conectado com thread (" + this.getId() + ") : " + client.getInetAddress());
        Scanner keyBoard = new Scanner(System.in); //scanner do meu servidor
        Scanner inputClient; // cliente

        try {
            inputClient = new Scanner(client.getInputStream()); // recebe a mgs do cliente;
            PrintStream output = new PrintStream(client.getOutputStream());

            while (inputClient.hasNextLine()) {

                String msgInputClient = inputClient.nextLine();
                System.out.println("Informe a resposta para o cliente (" + msgInputClient + "):");

                String msgAnswer = keyBoard.nextLine();
                output.println(msgAnswer);
                System.out.println("/-----------------------------------/");

                if (msgInputClient.equalsIgnoreCase("Exit")) {
                    client.close();
                    System.out.println("Cliente Finalizado");
                    break;
                }
            }
            Thread.sleep(15000);

            System.out.println("Cliente Finalizado: " + client.getInetAddress() + " da thread (" + this.getId() + ")");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}