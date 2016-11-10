package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Leandro on 16/10/2016.
 */
public class Palito extends Actor {

    Texture texture;
    float positionx;
    float positiony;
    boolean muerto;

    Palito(Texture texture,float positionx, float positiony){
        this.texture = texture;
        this.positionx = positionx;
        this.positiony = positiony;
    }

    public float getPositionx(){
        return positionx;
    }
    public float getPositiony(){
        return positiony;
    }
    public void setMuerto(boolean muerto){
        this.muerto = muerto;
    }

    public boolean getVida(){
        if (muerto){
            return false;
        }
        else{
            return true;
        }
    }

    public void act(float delta){

    }
    public void draw(Batch batch,float parenthalpha){
        if (muerto){
            //No hago nada porque el palito esta muerto
        }
        else{
            batch.draw(texture, positionx, positiony);
        }
    }
}
