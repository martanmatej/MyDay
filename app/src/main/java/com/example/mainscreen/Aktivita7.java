package com.example.mainscreen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Aktivita7 extends AppCompatActivity {
    Button button;
    EditText text, Title;
    int count = 1;
    ArrayList<Editable> popisData = new ArrayList<Editable>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_aktivita7);

        text = (EditText) findViewById(R.id.input20);
        Title = (EditText) findViewById(R.id.Title);


        Aktivita2.increaseActivityCounter();
        control();


        /**
         * Event listener, měnící názvy a ukládající data do listu podle počtu stisknutí tlačítka
         */
        button = (Button) findViewById(R.id.button17);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                Log.i("countCase: ", Integer.toString(count));
                switch (count) {
                    case 1:
                        Title.setText("Popiš objekt vedle tebe!");
                        popisData.add(text.getText());
                        text.setText("");
                        break;
                    case 2:
                        Title.setText("Popiš vysněné místo!");
                        popisData.add(text.getText());
                        text.setText("");
                        break;
                    case 3:
                        Title.setText("Popiš oblíbenou vzpomínku!");
                        popisData.add(text.getText());
                        text.setText("");
                        break;
                    case 4:
                        Title.setText("Popiš nejoblíbenější aktivitu!");
                        popisData.add(text.getText());
                        text.setText("");
                        break;
                    case 5:
                        Title.setText("Popiš svůj vnitřní pocit!");
                        popisData.add(text.getText());
                        text.setText("");
                        break;
                    case 6:
                        count = 1;
                        Title.setText("Popiš objekt vedle tebe!");
                        popisData.add(text.getText());
                        text.setText("");
                        showDialogDone();
                        break;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        count = 1;
    }

    /**
     * Metoda, zobrazující dialog, zda se uživatel cítí lépe a reaguje na odpovědi
     */
    public void showDialogDone() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.create();
        dialog.setTitle("Skvělá práce!");
        dialog.setMessage("Cítíš se lépe?");
        dialog.setPositiveButton("Ano", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogAgain();
            }
        });
        dialog.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showDialogWorse();
            }
        });
        dialog.show();
    }

    /**
     * Pomocná metoda pro @showDialog metodu. Zobrazí jiný dialog v případě potvrzeného zlepšení s možnostmi návratu nebo opakování
     */
    public void showDialogAgain() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.create();
        dialog.setMessage("Skvěle, to jsme moc rádi :)");
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();
            }
        });
        dialog.setNeutralButton("Zkusit znovu", null);
        dialog.show();
    }

    /**
     * Pomocná metoda pro showDialog. Zobrazuje další dialog, který se zobrazí v případě, že se uživatel cítí lépe a zvolil tuto možnost
     */
    public void showDialogWorse() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.create();
        dialog.setMessage("To nás mrzí :(");
        dialog.setNeutralButton("Zkusit znovu", null);
        dialog.setPositiveButton("Jiné cvičení", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HandleMove();
            }
        });
        dialog.setNegativeButton("Odejít", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                handleGoChoose();
            }
        });
        dialog.show();
    }

    /**
     * Metoda, která kontroluje kolik aktivit uživatel vyzkoušel a ptá se ho zda, chce pokračovat
     * @count je nastaven po 4 opakováních
     */
    public void control() {
        if (Aktivita2.getActivityCounter() > 4) {
            Aktivita2.setActivityCounter(1);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.create();
            dialog.setTitle("Upozornění");
            dialog.setMessage("Už jsi vyzkoušel většinu aktivit. Chceš pokračovat?");
            dialog.setPositiveButton("Ano", null);
            dialog.setNegativeButton("Ne", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    handleGoHome();
                }
            });
            dialog.show();
        }
    }

    /**
     * Navigator na screen @Aktivita4 - styly dýchání
     */
    public void HandleMove() {
        Intent intent = new Intent(this, Aktivita4.class);
        startActivity(intent);
    }

    /**
     * Navigator na domovsky screen
     */
    public void handleGoHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Navigator na screen s vybery pomoci
     */
    public void handleGoChoose() {
        Intent intent = new Intent(this, Aktivita2.class);
        startActivity(intent);
    }
}