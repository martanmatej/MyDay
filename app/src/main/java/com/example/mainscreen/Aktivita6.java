package com.example.mainscreen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Třída, která zpracovává logiku pro screen s vypsání informací, co uživatel vidí
 */
public class Aktivita6 extends AppCompatActivity {

    EditText input1,input2,input3,input4,input5;
    Button confirm;
    ArrayList<Editable> slova = new ArrayList<android.text.Editable>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_aktivita6);

        /**
         * Nastavení prvotního stavu + inicializace
         */
        Aktivita2.i++; //Vlastnost ukazující počet aktuálních kliknutí. Na 4 se resetuje
        System.out.println(Aktivita2.i);
        control(); //počítá do 4 a když vetší shodí na 1
        confirm = (Button) findViewById(R.id.button16);
        input1 = (EditText) findViewById(R.id.input20);
        input2 = (EditText) findViewById(R.id.input7);
        input3 = (EditText) findViewById(R.id.input8);
        input4 = (EditText) findViewById(R.id.input9);
        input5 = (EditText) findViewById(R.id.input10);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slova.add(input1.getText());
                slova.add(input2.getText());
                slova.add(input3.getText());
                slova.add(input4.getText());
                slova.add(input5.getText());
                showDialog();
            }
        });
    }

    /**
     * Metoda, která kontroluje kolik aktivit uživatel vyzkoušel a ptá se ho zda, chce pokračovat
     * @count je nastaven po 4 opakováních
     */
    public void control(){
        if(Aktivita2.i>4){
            Aktivita2.i=1;
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
     * Metoda, zobrazovající screen popisu vysněných objektů (@Aktivita7)
     */
    public void transfareNext(){
        Intent intent = new Intent(this, Aktivita7.class);
        startActivity(intent);
    }

    /**
     * Metoda, zobrazující dialog, zda se uživatel cítí lépe a reaguje na odpovědi
     */
    public void showDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.create();
        dialog.setTitle("MyMind");
        dialog.setMessage("Cítíš se lépe?");
        dialog.setPositiveButton("Lépe", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogBetter();
            }
        });
        dialog.setNegativeButton("Stále ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogWorse();
            }
        });
        dialog.show();
    }

    /**
     * Pomocná metoda pro showDialog. Zobrazuje další dialog, který se zobrazí v případě, že se uživatel cítí lépe a zvolil tuto možnost
     */
    public void showDialogBetter(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.create();
        dialog.setMessage("To jsme moc rádi :)");
        dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        dialog.setPositiveButton("Zkusit znovu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                input1.setText("");
                input2.setText("");
                input3.setText("");
                input4.setText("");
                input5.setText("");
            }
        });
        dialog.show();
    }

    /**
     * Pomocná metoda pro showDialog. Zobrazuje další dialog, který se zobrazí v případě, že se uživatel cítí hůře a zvolil tuto možnost.
     * Nabízí možnosti pro zlepšení pomoci
     */
    public void showDialogWorse(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.create();
        dialog.setMessage("To nás mrzí :(");
        dialog.setNeutralButton("Odejít", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                onBackPressed();
            }
        });
        dialog.setNegativeButton("Jiné cvičení", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                transfareNext();
            }
        });
        dialog.setPositiveButton("Zkusit znovu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                input1.setText("");
                input2.setText("");
                input3.setText("");
                input4.setText("");
                input5.setText("");
            }
        });
        dialog.show();
    }

    /**
     * Navigator pro přesun na domovský screen
     */
    public void HandleGoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}