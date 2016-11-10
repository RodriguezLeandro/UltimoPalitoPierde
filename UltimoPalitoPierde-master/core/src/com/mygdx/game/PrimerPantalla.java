package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Leandro on 16/10/2016.
 */
public class PrimerPantalla extends PantallaBase{

    Stage stage;
    Finger finger;
    Conexion conexion;
    Socket socket;
    Boolean miturno;
    Palito [][]palito = new Palito[10][10];
    int maximo = 4;
    OutputStream dataOutputStream;
    InputStream dataInputStream;
    int[] response = new int[4];

    Texture texturePalito;
    Texture textureFinger;

    PrimerPantalla(MyGdxGame gameController, Socket socket, Boolean miturno,OutputStream dataOutputStream,InputStream dataInputStream){
        super(gameController);
        super.render(60);

        this.socket = socket;
        this.miturno = miturno;
        this.dataOutputStream = dataOutputStream;
        this.dataInputStream = dataInputStream;

        texturePalito = new Texture("palito.png");
        textureFinger = new Texture("finger.png");
    }

    public void show(){

        stage = new Stage();

        finger = new Finger(textureFinger,Gdx.input.getX(),Gdx.input.getY(),miturno);

        CrearArbol();

        stage.addActor(finger);

    }

    public void CrearArbol(){
        int ibase;
        int jbase = 100;

        for(int j = 0; j < 10; j++) {
            ibase = 350+((j-10)*20);
            for (int i = 0; i < 10 - j; i++) {
                palito[i][j] = new Palito(texturePalito, ibase + 40, jbase);
                stage.addActor(palito[i][j]);
                ibase = ibase + 40;
            }
            jbase = jbase + 40;
        }
    }

    public void render(float delta){
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();

        comprobarMovimiento();

        stage.draw();

    }

    public void comprobarMovimiento(){
        if (finger.hasEnded()){

            //Si es mi turno, le mando al servidor la posicion de los palitos a borrar
            if (miturno) {
                try {
                    dataOutputStream.write((int) finger.getVectorini().x);
                    dataOutputStream.write((int) finger.getVectorini().y);
                    dataOutputStream.write((int) finger.getVectorfin().x);
                    dataOutputStream.write((int) finger.getVectorfin().y);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            conexion = new Conexion(finger.getVectorini(),finger.getVectorfin(),palito,maximo);
            finger.setEsperarMovimiento(true);
        }
        else{

            try {

                dataOutputStream.write(-2);
                response[0] =  dataInputStream.read();
                response[1] =  dataInputStream.read();
                response[2] =  dataInputStream.read();
                response[3] =  dataInputStream.read();

                Vector2 vector1aux = null, vector2aux = null;

                vector1aux.set(response[0],response[1]);
                vector2aux.set(response[2],response[3]);

                conexion = new Conexion(vector1aux,vector2aux,palito,maximo);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
