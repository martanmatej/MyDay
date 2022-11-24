package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;

/**
 * Třída, která informuje uživatele o typech poruch, na které se aplikace zaměřuje
 * Zobrazí se po kliknutí v aktuálním problému na možnost NE
 */
public class coToVystihuje extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_co_to_vystihuje);

    }
}