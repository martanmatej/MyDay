package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class momentalniProblem extends AppCompatActivity {
     protected Button button8,button9;//tlacitka 8 - ano tlacitka 9 - ne
    static int clicked = MainActivity.clicked;
    public void navigateBack(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    /**
     * OVERRIDUJE SE Z DUVODU ZJEDNODUSSENI. UZIVATEL NEMUSI MACKAT XKRAT BACK TLACITKO JAKO NEGRAMOT
     * **/
    @Override
    public void onBackPressed(){
        navigateBack();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_momentalni_problem);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);



        button8.setOnClickListener(new View.OnClickListener() { //tlacitko ANO
            @Override
            public void onClick(View v) {
                Log.i("problemANO",Integer.toString(clicked));
                openAktivita2();
                /*
                switch(clicked){
                    case 1:
                        openAktivita2();
                        break;
                    case 2:
                        openAktivita3();
                        break;
                    case 3:
                        openAktivita4();
                        break;
                    case 4:
                        openAktivita5();
                        break;
                    case 5:
                        openAktivita6();
                        break;
                    case 6:
                        openAktivita7();
                        break;
                    case 7:
                        openAktivita8();
                        break;
                }

                 */
            }
        });
        button9.setOnClickListener(new View.OnClickListener() { //tlacitko NE
            @Override
            public void onClick(View v) {
                openCoToVysttihuje();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        clicked=MainActivity.clicked;
        Log.i("momentalniProblem","onStart");
        Log.i("momentalniProblem",Integer.toString(clicked));

    }
    @Override
    protected void onResume(){
        super.onResume();
        Aktivita2.i=0;
        Log.i("onResumeProblem",Integer.toString(Aktivita2.i));
    }

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
    public void openCoToVysttihuje(){
        Intent intent = new Intent(this,coToVystihuje.class);
        startActivity(intent);
    }
}