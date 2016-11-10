package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Leandro on 16/10/2016.
 */
public class Finger extends Actor {

    Texture texture;
    float positionx;
    float positiony;
    float positionyaux;

    boolean slice = false;
    boolean muerto = true;

    Vector2 vectorini;
    Vector2 vectorfin;

    Finger(Texture texture,float positionx, float positiony){
        this.texture = texture;
        this.positionx = positionx;
        this.positiony = positiony;
    }

    public boolean isSlice() {
        return slice;
    }

    public void setSlice(boolean slice) {
        this.slice = slice;
    }

    float InvertirPosicion(float position){
        return position = Gdx.graphics.getHeight()-position;
    }

    public boolean hasEnded(){
        if (positionyaux == -1){
            return true;
        }
        else{
            return  false;
        }
    }
    public void setEsperarMovimiento(boolean movimiento){
        if (movimiento) {
            positionyaux = -2;
        }
        else{

        }
    }

    public Vector2 getVectorini(){
        return vectorini;
    }

    public Vector2 getVectorfin(){
        return vectorfin;
    }

    public void act(float delta){
        if (Gdx.input.isTouched()) {
            if (isSlice()) {
                positionx = Gdx.input.getX();
                setSlice(true);
            }
            else{
                positionx = Gdx.input.getX();
                positiony = InvertirPosicion(Gdx.input.getY());
                positionyaux = positiony;
                vectorini = new Vector2(positionx,positiony);
                setSlice(true);
            }
            setMuerte(false);
        }

        else{
            if (isSlice()) {
                vectorfin = new Vector2(positionx,positionyaux);
                positionyaux = -1;
                setMuerte(true);
                setSlice(false);
            }
            else{
                setMuerte(true);
                setSlice(false);
            }
        }

    }

    public void setMuerte(boolean muerte){
        muerto = muerte;
    }

    public boolean getMuerte(){
        if (muerto){
            return true;
        }
        else{
            return false;
        }
    }

    public void draw(Batch batch, float parenthalpha){
        if (getMuerte()) {
            //No lo dibujo nada porque esta muerto el finger.
        }
        else{
            if (isSlice()){
                batch.draw(texture, positionx, positionyaux);

            }
            else {
                batch.draw(texture, positionx, positiony);
            }
        }
    }

}
