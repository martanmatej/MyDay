package com.example.mainscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link numberFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class numberFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public numberFragment() {
        // Required empty public constructor
    }

    public static numberFragment newInstance(String param1, String param2) {
        numberFragment fragment = new numberFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Button startProcess;
    TextView counter, helpText, title;
    ImageView image;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View screen = inflater.inflate(R.layout.fragment_number, container, false);
        long durationStart = TimeUnit.SECONDS.toMillis(42);

        image= (ImageView) screen.findViewById(R.id.imageView8);
        counter = (TextView) screen.findViewById(R.id.textView114);
        title = (TextView) screen.findViewById(R.id.textView115);
        counter.setVisibility(screen.GONE);
        image.setVisibility(screen.GONE);
        helpText = (TextView) screen.findViewById(R.id.textView116);

        startProcess = (Button) screen.findViewById(R.id.button19);
        startProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Následuj instrukce");
                counter.setVisibility(screen.VISIBLE);
                image.setVisibility(screen.VISIBLE);
                startProcess.setVisibility(screen.GONE);
                image.setImageResource(R.drawable.tonguebehindteeth);
                helpText.setText("Jazyk za horní část zubů.");

                new CountDownTimer(durationStart, 1000){
                    long seperateCounter = 4000; /** POČÍTÁ PO 10 VTEŘINÁCH **/
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String sDuration = Long.toString(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                        String finalDuration = Long.toString(TimeUnit.MILLISECONDS.toSeconds(seperateCounter));
                        seperateCounter = seperateCounter - 1000;
                        /**
                         * PRO PŘEHLEDNOST:
                         * Je to takhle rozkrokované pro lepší orientaci. Konec značí začátek následujícího kroku..
                         * tzn. Končí přemístění jazyku za zuby, následuje výdech, který se v CASE provádí.
                         * **/
                        switch (sDuration) {
                            case "38":
                                seperateCounter = 8000;
                                helpText.setText("Pomalý celý výdech");
                                image.setImageResource(R.drawable.exhale);
                                //Konec špička za zuby
                                break;
                            case "30":
                                seperateCounter = 4000;
                                helpText.setText("Pomalý nádech");
                                image.setImageResource(R.drawable.inhale);
                                //Konec výdechu
                                break;
                            case "26":
                                seperateCounter = 7000;
                                helpText.setText("Zadrž dech");
                                image.setImageResource(R.drawable.pause);
                                //Konec nádechu
                                break;
                            case "19":
                                seperateCounter = 8000;
                                helpText.setText("Pomalý celý výdech");
                                image.setImageResource(R.drawable.exhale);
                                //Konec zadržení dechu
                                break;
                            case "11":
                                seperateCounter = 4000;
                                helpText.setText("Pomalý nádech");
                                image.setImageResource(R.drawable.inhale);
                                //Konec výdechu
                                break;
                            case "7":
                                seperateCounter = 7000;
                                helpText.setText("Zadrž dech");
                                image.setImageResource(R.drawable.pause);
                                //Konec zadržení dechu
                                break;
                        }
                        counter.setText(finalDuration);
                    }

                    @Override
                    public void onFinish() {
                        counter.setVisibility(screen.GONE);
                        image.setVisibility(screen.GONE);
                        startProcess.setVisibility(screen.VISIBLE);
                        title.setText("Chceš to zkusit znovu?");
                        helpText.setText("Skvělá práce");
                        ((Aktivita4)getActivity()).showDialog(); // ZOBRAZUJE DIALOG PO DOKONČENÍ
                    }
                }.start();
            }
        });

        return screen;
    }
}