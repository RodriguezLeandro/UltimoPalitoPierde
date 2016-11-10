package com.mygdx.game;

import com.badlogic.gdx.Game;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MyGdxGame extends Game {

    PrimerPantalla primerPantalla;
    Socket socket;
    OutputStream dataOutputStream;
    InputStream dataInputStream;
    boolean miturno = false;

	public void create () {
        try {
            //Aca rellenar con ip correcta y puerto.
            socket = new Socket("127.0.0.1",8888);
            System.out.println("Yayyy, hemos logrado la conexion.");

            //Le pido al servidor que me diga si es mi turno o no;

            dataOutputStream = socket.getOutputStream();
            dataInputStream = socket.getInputStream();

            //Le mando -3 preguntandole de quien es el turno;
            dataOutputStream.write(-3);

            if (dataInputStream.read() == -4){
                //Entonces es mi turno
                miturno = true;
            }
            else{
                miturno = false;
            }


        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("No se ha podido abrir el servidor porque...");
        }

        primerPantalla = new PrimerPantalla(this,socket,miturno,dataOutputStream,dataInputStream);
        setScreen(primerPantalla);

	}

	public void dispose () {
        super.dispose();
        primerPantalla.dispose();
	}
}
