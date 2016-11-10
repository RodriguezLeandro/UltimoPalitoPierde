package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Leandro on 16/10/2016.
 */
public class Conexion{

    float posicioninicialx;
    float posicionfinalx;
    float posicionconstantey;

    Conexion(Vector2 vectorini, Vector2 vectorfin, Palito[][] palito,int maximo){

        posicionconstantey = vectorini.y;
        posicioninicialx = vectorini.x;
        posicionfinalx = vectorfin.x;

        for (int j = 0; j < 10; j++) {

            for (int i = 0; i < 10 - j; i++) {
                if (cantidadPalitosAMatar(posicionconstantey,palito,posicioninicialx,posicionfinalx)>maximo) {

                }
                else{
                    if (hayChoque(posicionconstantey, palito[i][j])) {
                        if (hayChoque(posicioninicialx, posicionfinalx, palito[i][j])) {
                            palito[i][j].setMuerto(true);
                        } else {
                            //No hago nada porque el usuario toco cualquier cosa.
                        }
                    } else {
                        //No hago nada porque el usuario toco cualquier cosa.
                    }
                }
            }

        }
    }

    public int cantidadPalitosAMatar(float posicionauxiliary,Palito palito[][],float posicionauxiliarinix,float posicionauxiliarfinx){
        int c = 0;

        for (int d = 0; d < 10; d++){
            for (int b = 0; b < 10-d ; b++){
                if (hayChoque(posicionauxiliary,palito[b][d])){
                    if (hayChoque(posicionauxiliarinix,posicionauxiliarfinx,palito[b][d])){
                        if (palito[b][d].getVida()) {
                            c++;
                        }
                        else{
                            //No hago nada porque el palito esta muerto.
                        }
                    }
                }
            }
        }
        return c;
    }

    public boolean hayChoque(float posicionauxiliary,Palito palito){
        if (palito.getPositiony() > posicionauxiliary){
            if (palito.getPositiony() + 20 < posicionauxiliary){
                return true;
            }
            else{
                return false;
            }

        }
        else if (palito.getPositiony() < posicionauxiliary){
            if (palito.getPositiony() > posicionauxiliary-20){
                return true;
            }
            else{
                return false;
            }
        }
            return false;
    }

    public boolean hayChoque(float posicionauxiliarxini,float posicionauxiliarxfin,Palito palito){
        if (palito.getPositionx() > posicionauxiliarxini){
            if (palito.getPositionx() < posicionauxiliarxfin){
                return true;
            }
            else{
                return false;
            }
        }
        else if(palito.getPositionx() < posicionauxiliarxini){
            if (palito.getPositionx() > posicionauxiliarxfin){
                return true;
            }
        }
        return false;
    }
}
