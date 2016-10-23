package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game {

    PrimerPantalla primerPantalla;
	public void create () {
        primerPantalla = new PrimerPantalla(this);
        setScreen(primerPantalla);

	}

	public void dispose () {
        super.dispose();
        primerPantalla.dispose();
	}
}
