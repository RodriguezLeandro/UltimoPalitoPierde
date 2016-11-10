package com.example.leandro.ultimopalitopierdeserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    final int port = 8888;
    Protocolo protocolo;
    ServerSocket serverSocket;
    Socket socket;
    Socket socket2;
    Thread1 thread1;
    Thread2 thread2;

    public Server(){
        try {

            serverSocket = new ServerSocket(port);

            protocolo = new Protocolo();

            socket = new Socket();
            socket2 = new Socket();

            thread1 = new Thread1(socket,serverSocket,protocolo);
            thread2 = new Thread2(socket2,serverSocket,protocolo);

            thread1.start();
            thread2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
