package com.example.leandro.ultimopalitopierdeserver;

/**
 * Created by Leandro on 10/11/2016.
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Thread2 extends Thread{

    Socket socket;
    ServerSocket serverSocket;

    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    byte dato[] = new byte[4];
    byte response[];
    Protocolo protocolo;
    int i = 0;

    public Thread2(Socket socket, ServerSocket serverSocket,Protocolo protocolo){

        this.protocolo = protocolo;
        this.serverSocket = serverSocket;
        this.socket = socket;
    }

    public void run(){
        try {

            socket = serverSocket.accept();
            System.out.println("Otro cliente se ha conectado");


            dataInputStream = new DataInputStream(socket.getInputStream());

            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            dataOutputStream.write(8);

            while((dato[i] = dataInputStream.readByte()) != -1){

                if (dato[i] == -2){

                    //Protocolo.isNull devuelve true si los datos del movimiento 1 estan vacios
                    while(protocolo.isNullMovimiento1()){
                        System.out.println("Esperando que el jugador 1 mueva");
                    }

                    response = protocolo.getDatos1();

                    for(int k = 0; k < 4; k ++){
                        dataOutputStream.writeByte(response[k]);
                    }

                    protocolo.setNullMovimiento1();
                    i = -1;
                }
                if (i == 1){
                    protocolo.setDatos2(dato);
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
