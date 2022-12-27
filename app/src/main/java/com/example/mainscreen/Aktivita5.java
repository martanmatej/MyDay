package com.example.mainscreen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Třída, zpracovávájící logiku pro screen s citáty
 * 4. metoda pomoci
 */
public class Aktivita5 extends AppCompatActivity {

    /**
     * Při zmáčknutí tlačítka zpět naviguje rovnou na screen se všemi možnostmi pomoci
     */
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Aktivita2.class);
        startActivity(intent);
    }

    TextView mainText, authorText;
    Button before, after;
    int indicator;
    ArrayList<String> quotesList = new ArrayList<String>();
    ArrayList<String> authorList = new ArrayList<String>();

    /**
     * Metoda, nastavující text s citátem + autora citátu
     */
    private void setBothText(){
        mainText.setText(quotesList.get(indicator));
        authorText.setText(authorList.get(indicator));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_aktivita5);


        Aktivita2.increaseActivityCounter();
        control();
        String helper;
        mainText = findViewById(R.id.textView120);
        authorText = findViewById(R.id.textView121);
        before = findViewById(R.id.button24);
        after = findViewById(R.id.button25);
        indicator = 0; //ukazatel na jakém jsme indexu v listu citátů a autorů

        /**
         * Načítání citátů ze souboru
         */
        try {
            //Načítá z assets složky txt file... FileReader nefunguje
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(getAssets().open("Citaty.txt")));

            String corrector[];
            helper = bufferedReader.readLine();
            while(helper != null){
                corrector = helper.split(";");
                quotesList.add(corrector[0]);
                authorList.add(corrector[1]);
                helper = bufferedReader.readLine();
            }
            bufferedReader.close();
            setBothText();
        }catch (FileNotFoundException e404){
            mainText.setText("Omlouváme se, citáty momentálně nejsou k dispozici...");
            authorText.setText("");
            System.out.println(e404);
        }
        catch (IOException e) {
            mainText.setText("Omlouváme se, citáty momentálně nejsou k dispozici...");
            authorText.setText("");
            e.printStackTrace();
        }
        before.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indicator>=1){
                    indicator--;
                    setBothText();
                }
            }
        });
        after.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(indicator<quotesList.size()-1){
                    indicator++;
                    setBothText();
                }
            }
        });

    }
    /**
     * Metoda, která kontroluje kolik aktivit uživatel vyzkoušel a ptá se ho zda, chce pokračovat
     * @count je nastaven po 4 opakováních
     */
    public void control(){
        if(Aktivita2.getActivityCounter()>4){
            Aktivita2.setActivityCounter(1);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.create();
            dialog.setTitle("Upozornění");
            dialog.setMessage("Už jsi vyzkoušel většinu aktivit. Chceš pokračovat?");
            dialog.setPositiveButton("Ano", null);
            dialog.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    HandleGoHome();
                }
            });
            dialog.show();
        }
    }

    /**
     * Navigator pro přesun na domovský screen
     */
    public void HandleGoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}