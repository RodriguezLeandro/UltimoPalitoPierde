package com.example.leandro.ultimopalitopierdeserver;

/**
 * Created by Leandro on 10/11/2016.
 */

public class Protocolo{

    byte[] movimiento1 = new byte[4];
    byte[] movimiento2 = new byte[4];

    public Protocolo(){
        for(int k = 0; k < 4; k++){
            movimiento1[k] = 0;
            movimiento2[k] = 0;
        }
    }


    public void setNullMovimiento1(){
        for(int k = 0; k < 4; k++){
            movimiento1[k] = 0;
        }
    }
    public void mostrarDatos(byte[] show){
        for(int k = 0; k < 4; k++){
            System.out.println(show[k]);
        }
    }

    public void setNullMovimiento2(){
        for(int k = 0; k < 4; k++){
            movimiento2[k] = 0;
        }
    }
    public boolean isNullMovimiento1(){
        if ((movimiento1[0] == 0)&&(movimiento1[1] == 0)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isNullMovimiento2(){
        if ((movimiento2[0] == 0)&&(movimiento2[1] == 0)){
            return true;
        }
        else{
            return false;
        }
    }
    public void setDatos1(byte[] datos){
        for (int k = 0; k < 4; k++){
            movimiento1[k] = datos[k];
        }
    }

    public void setDatos2(byte[] datos){
        for (int k = 0; k < 4; k++){
            movimiento2[k] = datos[k];
        }
    }

    public byte[] getDatos1(){
        return movimiento1;
    }

    public byte[] getDatos2(){
        return movimiento2;
    }

}

