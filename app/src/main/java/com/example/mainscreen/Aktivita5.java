package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

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

public class Aktivita5 extends AppCompatActivity {

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


        String helper;
        mainText = findViewById(R.id.textView120);
        authorText = findViewById(R.id.textView121);
        before = findViewById(R.id.button24);
        after = findViewById(R.id.button25);
        indicator = 0;

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
}