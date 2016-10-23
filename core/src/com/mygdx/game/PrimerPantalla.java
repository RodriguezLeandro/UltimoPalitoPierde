package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Created by Leandro on 16/10/2016.
 */
public class PrimerPantalla extends PantallaBase{

    Stage stage;
    Finger finger;
    Conexion conexion;
    Palito [][]palito = new Palito[10][10];
    int maximo = 4;

    Texture texturePalito;
    Texture textureFinger;

    PrimerPantalla(MyGdxGame gameController){
        super(gameController);
        super.render(60);

        texturePalito = new Texture("palito.png");
        textureFinger = new Texture("finger.png");
    }

    public void show(){

        stage = new Stage();

        finger = new Finger(textureFinger,Gdx.input.getX(),Gdx.input.getY());

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

            conexion = new Conexion(finger.getVectorini(),finger.getVectorfin(),palito,maximo);
            finger.setEsperarMovimiento(true);
        }
        else{

        }

    }

}
