package com.example.leandro.ultimopalitopierdeserver;

/**
 * Created by Leandro on 10/11/2016.
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Thread1 extends Thread{

    Socket socket;
    ServerSocket serverSocket;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    byte[] dato = new byte[4];
    byte[] response;
    Protocolo protocolo;
    int i = 0;

    public Thread1(Socket socket, ServerSocket serverSocket, Protocolo protocolo){
        this.protocolo = protocolo;
        this.serverSocket = serverSocket;
        this.socket = socket;
    }

    public void run(){
        try {

            socket = serverSocket.accept();

            dataInputStream = new DataInputStream(socket.getInputStream());

            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            //El jugador que se conecte primero va a comenzar entonces
            dataOutputStream.write(-1);

            //Si el dato es -1, entonces el juego termino.
            while((dato[i] = dataInputStream.readByte()) != -1){

                //Si el dato es -2, entonces el cliente nos pide que le mandemos la informacion del movimiento 2.
                if (dato[i] == -2){

                    //Protocolo.isNull devuelve true si los datos del movimiento 2 estan vacios
                    while(protocolo.isNullMovimiento2()){
                        System.out.println("Esperando que el jugador 2 mueva");
                    }
                    response = protocolo.getDatos2();

                    for(int k = 0; k < 4; k ++){
                        dataOutputStream.writeByte(response[k]);
                    }

                    protocolo.setNullMovimiento2();

                    i = -1;
                }

                if (i == 1){
                    protocolo.setDatos1(dato);
                    protocolo.mostrarDatos(dato);
                    i = -1;
                }

                i++;
            }

            socket.close();
            serverSocket.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
