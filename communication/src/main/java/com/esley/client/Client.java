package com.esley.client;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try {
            //Declarar o socket cliente
            Socket client = new Socket("127.0.0.1", 5000);

            Scanner keyBoard = new Scanner(System.in);
            Scanner inputServer = new Scanner(client.getInputStream()); //recebe resposta do servidor

            // Fluxo de dados pra envio
            PrintStream outputCliente = new PrintStream(client.getOutputStream());

            String msg;

            do {
                System.out.println("Informe a mensagem para o servidor");
                msg = keyBoard.nextLine();
                outputCliente.println(msg); //Mandei a mensagem para o servidor

                String answer = inputServer.nextLine(); // Recebendo a msg do servidor
                System.out.println("Cliente: " + msg);
                System.out.println("Echo: " + answer);
                System.out.println("/-------------------------------");

                if (msg.equalsIgnoreCase("Exit")) {
                    client.close();
                    System.out.println("Cliente Finalizado !");
                    break;
                }

            } while (true);

        } catch (Exception e) {
            System.out.println("Ocorreu um erro durante a conexao");
        }
    }
}