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
 * Use the {@link RollFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RollFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RollFragment() {
        // Required empty public constructor
    }
    public static RollFragment newInstance(String param1, String param2) {
        RollFragment fragment = new RollFragment();
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
        View screen = inflater.inflate(R.layout.fragment_roll, container, false);
        long durationStart = TimeUnit.SECONDS.toMillis(52);

        image = (ImageView) screen.findViewById(R.id.imageView6);
        counter = (TextView) screen.findViewById(R.id.textView117);
        title = (TextView) screen.findViewById(R.id.textView119);
        counter.setVisibility(screen.GONE);
        image.setVisibility(screen.GONE);
        helpText = (TextView) screen.findViewById(R.id.textView118);

        startProcess = (Button) screen.findViewById(R.id.button23);
        startProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Následuj instrukce");
                counter.setVisibility(screen.VISIBLE);
                image.setVisibility(screen.VISIBLE);
                image.setImageResource(R.drawable.layingwoman);
                helpText.setText("Lehni si, levá ruka na břicho,\npravá na hruď");
                startProcess.setVisibility(screen.GONE);

                new CountDownTimer(durationStart, 1000){
                    long separateCounter = 4000; /** POČÍTÁ PO 5 VTEŘINÁCH **/

                    private void inhale(){
                        helpText.setText("Lehký nádech do břicha");
                        image.setImageResource(R.drawable.inhale);
                        separateCounter = 3000;
                    }

                    private void exhale(){
                        helpText.setText("Lehký výdech pusou");
                        image.setImageResource(R.drawable.exhale);
                        separateCounter = 3000;
                    }

                    private void inhaleLungs(){
                        helpText.setText("Lehký nádech do plic");
                        image.setImageResource(R.drawable.inhale);
                        separateCounter = 3000;
                    }
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String sDuration = Long.toString(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                        String finalDuration = Long.toString(TimeUnit.MILLISECONDS.toSeconds(separateCounter));
                        separateCounter = separateCounter - 1000;


                        switch (sDuration){
                            case "48":
                                inhale();
                                break;
                            case "45":
                                exhale();
                                break;
                            case "42":
                                inhale();
                                break;
                            case "39":
                                exhale();
                                break;
                            case "36":
                                inhale();
                                break;
                            case "33":
                                exhale();
                                break;
                            case "30":
                                inhale();
                                break;
                            case "27":
                                exhale();
                                break;
                            case "24":
                                inhaleLungs();
                                break;
                            case "21":
                                exhale();
                                break;
                            case "18":
                                inhaleLungs();
                                break;
                            case "15":
                                exhale();
                                break;
                            case "12":
                                inhaleLungs();
                                break;
                            case "9":
                                exhale();
                                break;
                            case "6":
                                inhaleLungs();
                                break;
                            case "3":
                                exhale();
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