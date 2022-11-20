package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public Button button1,button2,button3,button4,button5,button6,button7;
    public static int clicked;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        TextView settings = (TextView) findViewById(R.id.settingsID);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openSettingsActivity();
            }
        });

        button1 = (Button) findViewById(R.id.button); ;//tlačí mě na hrudi
        button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                openMomentalniProblem();
                clicked = 1;
            }
        });
        button2 = (Button) findViewById(R.id.button2);   //jsem k ničemu
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMomentalniProblem();
                clicked = 2;
            }
        });
        button3 = (Button) findViewById(R.id.button3); //jen si chci povídat
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMomentalniProblem();
                clicked = 3;
            }
        });

        button4 = (Button) findViewById(R.id.button4); //nemohu mluvit nebo se hnout
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMomentalniProblem();
                clicked = 4;
            }
        });
        button5 = (Button) findViewById(R.id.button5); //problemy se zavislosti
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMomentalniProblem();
                clicked = 5;
            }
        });
        button6 = (Button)  findViewById(R.id.button6); //trápí mě co bude dál
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMomentalniProblem();
                clicked = 6;
            }
        });
        button7 = (Button) findViewById(R.id.button7); //nemám důvod proč žít
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMomentalniProblem();
                clicked = 7;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("MainActivity","onStart");
        Log.i("MainActivity",Integer.toString(clicked));


    }

    /*
            public void openAktivita2(){
                Intent intent = new Intent(this, Aktivita2.class);
                startActivity(intent);
            }
            public void openAktivita3(){
                Intent intent = new Intent(this, Aktivita3.class);
                startActivity(intent);
            }

            public void openAktivita4(){
                Intent intent = new Intent(this, Aktivita4.class);
                startActivity(intent);
            }
            public void openAktivita5(){
                Intent intent = new Intent(this, Aktivita5.class);
                startActivity(intent);
            }
            public void openAktivita6(){
                Intent intent = new Intent(this, Aktivita6.class);
                startActivity(intent);
            }
            public void openAktivita7(){
                Intent intent = new Intent(this, Aktivita7.class);
                startActivity(intent);
            }
            public void openAktivita8(){
                Intent intent = new Intent(this, Aktivita8.class);
                startActivity(intent);
            }


             */
    public void openSettingsActivity(){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    public void openCoToVysttihuje(){
        Intent intent = new Intent(this,coToVystihuje.class);
        startActivity(intent);
    }
    public void openMomentalniProblem(){ //otevira momentalni problem ano/ne
        Intent intent = new Intent(this, momentalniProblem.class);
        startActivity(intent);
    }
}