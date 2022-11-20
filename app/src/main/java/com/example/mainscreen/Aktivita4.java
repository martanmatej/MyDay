package com.example.mainscreen;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class Aktivita4 extends AppCompatActivity {

    boolean dialogShouldShow = false;
    Button bricho, cisla, roll;
    public void navigateBack(){
        Intent intent = new Intent(this, Aktivita2.class);
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
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#180e2b"));
        getSupportActionBar().hide();
        System.out.println("SHOWW:: " + dialogShouldShow);
        setContentView(R.layout.activity_aktivita4);

        control(); //Dotaz, po využití 5. funkce aplikace
        if(dialogShouldShow){
            showDialog();
            dialogShouldShow = false;
        }

        //FragmentManager meni fragmentContainerView na urceny fragment
        bricho = (Button) findViewById(R.id.button21);
        bricho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Zvolil jsi DÝCHÁNÍ DO BŘICHA", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, brichoFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });

        cisla = (Button) findViewById(R.id.button20);
        cisla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Zvolil jsi 4-7-8", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, numberFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });

        roll = (Button) findViewById(R.id.button22);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Zvolil jsi ROLL", Toast.LENGTH_SHORT).show();
                FragmentManager fragmentManager = getSupportFragmentManager();

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, RollFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name")
                        .commit();
            }
        });
        if(dialogShouldShow){
            showDialog();
            dialogShouldShow = false;
        }

    }

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

    public void transfareNext(){
        Intent intent = new Intent(this, Aktivita5.class);
        startActivity(intent);
    }

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
                //PO DOKONCENI
            }
        });
        dialog.show();
    }
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
                //PO DOKONCENI
            }
        });
        dialog.show();
    }
    public void HandleGoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("HEY");
    }
}
