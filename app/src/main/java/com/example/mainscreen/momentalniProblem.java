package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Třída, kde uživatel rozhoduje, zda je to akutní problém, či nikoliv
 * ANO - otvírá @Aktivita2
 * NE - otvírá @coToVystihuje
 */

public class momentalniProblem extends AppCompatActivity {
    protected Button button8,button9;//tlacitka 8 - ano tlacitka 9 - ne
    static int clicked = MainActivity.clicked;

    /**
     * Navigátor zpět na domovský screen
     */
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

        /**
         * Listener, který rozhoduje o stisknutém tlačítku
         */
        button8.setOnClickListener(new View.OnClickListener() { //tlacitko ANO
            @Override
            public void onClick(View v) {
                Log.i("problemANO",Integer.toString(clicked));
                openAktivita2();
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

    /**
     * Navigator, který otvírá možnosti pomoci @Aktivita2
     */
    public void openAktivita2(){
        Intent intent = new Intent(this, Aktivita2.class);
        startActivity(intent);
    }

    /**
     * Navigator, který otvírá screen v případě, že není nutná závažná pomoc
     */
    public void openCoToVysttihuje(){
        Intent intent = new Intent(this,coToVystihuje.class);
        startActivity(intent);
    }
}