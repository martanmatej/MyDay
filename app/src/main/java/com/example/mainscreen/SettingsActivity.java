package com.example.mainscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toolbar;

public class SettingsActivity extends AppCompatActivity {
    protected Button button10, button11;

    /**
     * Metoda pro spouštění url jako ACTION_VIEW
     * @param url
     */
    private void openUrl(String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        setTitle(Html.fromHtml("<b>Nastavení</b>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#080808"));

        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        button10=findViewById(R.id.button10);
        button11=findViewById(R.id.button11);

        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://www.instagram.com/myday.mentalhealth.app/");
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openUrl("https://mydayandroid.tode.cz/");
            }
        });
    }

}