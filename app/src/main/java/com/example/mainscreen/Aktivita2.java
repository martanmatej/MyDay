package com.example.mainscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;

public class Aktivita2 extends AppCompatActivity  {
    static int i=0;
    private int CALL_PERMISSION_CODE = 1;

    public void navigateBack(){
        Intent intent = new Intent(this, momentalniProblem.class);
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
        setContentView(R.layout.activity_aktivita2);

        String PERMISSIONS = Manifest.permission.CALL_PHONE;

        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button button12 = findViewById(R.id.button12);//transparent button soustřeď se na jiné objekty
        Button button13 = findViewById(R.id.button13);//transparent button vyjmenuj co vidíš a slyšíš
        Button button14 = findViewById(R.id.button14);//transparent button kontrolované dýchání
        Button button15 = findViewById(R.id.button15);//transparent button citáty které nakopnou


        ImageButton buttonBack = (ImageButton) findViewById(R.id.imageButton2);
        ImageButton buttonCall = (ImageButton) findViewById(R.id.imageButton);


        /*
        openAktivita7 - jiné objekty
        openAktivita6 - co vidíš
        openAktivita4 - kontrolované dýchání approved
        openAktivita5 - citáty approved
         */

        System.out.println(i);

        //tlačítko pro návrat zpět
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //tlačítko pro otevření telefonu s předvolbou
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhoneApp();
            }
        });
        //jiné objekty
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAktivita7();
            }
        });
        //co vidíš
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAktivita6();
            }
        });
        //kontrolované dýchání
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAktivita4();
            }
        });
        //citáty
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAktivita5();
            }
        });






    }

    private void hasPermission(){
        if(ContextCompat.checkSelfPermission(Aktivita2.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            Log.i("PERMISSION","HAVE ALREADY");
        }
        else{
            requestCallPermissions();
        }
    }
    private void requestCallPermissions(){
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CALL_PHONE}, CALL_PERMISSION_CODE);
    }

    public void openPhoneApp(){
        hasPermission();

        AlertDialog.Builder question = new AlertDialog.Builder(this);
        question.create();
        question.setTitle("Chceš si promluvit s nějakým odborníkem?");
        question.setMessage("Chceš si promluvit s nějakým odborníkem? Kliknutím ANO budeš telefonicky spojen.");
        //Vyvolává telefonický hovor s nouzovou linkou
        question.setPositiveButton("ANO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:116123"));
                startActivity(intent);
            }
        });
        question.setNegativeButton("NE", null);
        question.show();
    }


    public void openAktivita6(){
        Intent intent = new Intent(this, Aktivita6.class);
        startActivity(intent);
    }
    public void openAktivita7(){
        Intent intent = new Intent(this, Aktivita7.class);
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

    }
