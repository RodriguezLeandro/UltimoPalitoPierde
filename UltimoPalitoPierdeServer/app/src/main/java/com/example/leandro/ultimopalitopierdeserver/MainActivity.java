package com.example.leandro.ultimopalitopierdeserver;

/**
 * Created by Leandro on 10/11/2016.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.button1);

        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == boton.getId()){
            Server server = new Server();
            Intent intent = new Intent(getApplicationContext(),serverPrendidoActivity.class);
            startActivity(intent);

        }
    }
}
