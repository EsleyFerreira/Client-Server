package com.esley.server;

import com.esley.client_service.ClientService;

import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Servidor iniciado...");

            while (true) {
                ClientService cs = new ClientService(server.accept());
                cs.start();
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro!");
        }
    }
}