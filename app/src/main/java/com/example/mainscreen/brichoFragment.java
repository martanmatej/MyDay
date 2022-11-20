package com.example.mainscreen;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link brichoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class brichoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public brichoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment brichoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static brichoFragment newInstance(String param1, String param2) {
        brichoFragment fragment = new brichoFragment();
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
        View screen = inflater.inflate(R.layout.fragment_bricho, container, false);
        long durationStart = TimeUnit.SECONDS.toMillis(20);

        image = (ImageView) screen.findViewById(R.id.imageView5);
        counter = (TextView) screen.findViewById(R.id.textView112);
        title = (TextView) screen.findViewById(R.id.textView111);
        counter.setVisibility(screen.GONE);
        image.setVisibility(screen.GONE);
        helpText = (TextView) screen.findViewById(R.id.textView113);



        startProcess = (Button) screen.findViewById(R.id.button18);
        startProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                title.setText("Následuj instrukce");
                counter.setVisibility(screen.VISIBLE);
                image.setVisibility(screen.VISIBLE);
                image.setImageResource(R.drawable.inhale);
                helpText.setText("Pomalý lehký nádech");
                startProcess.setVisibility(screen.GONE);

                new CountDownTimer(durationStart, 1000) {
                    long separateCounter = 5000; /** POČÍTÁ PO 5 VTEŘINÁCH **/
                    @Override
                    public void onTick(long millisUntilFinished) {
                        String sDuration = Long.toString(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished));
                        String finalDuration = Long.toString(TimeUnit.MILLISECONDS.toSeconds(separateCounter));
                        separateCounter = separateCounter - 1000;
                        switch (sDuration) {
                            case "15" :
                            case "5" :
                                helpText.setText("Pomalý lehký výdech");
                                image.setImageResource(R.drawable.exhale);
                                separateCounter = 5000;
                                break;
                            case "10" :
                                helpText.setText("Pomalý lehký nádech");
                                image.setImageResource(R.drawable.inhale);
                                separateCounter = 5000;
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
                        ((Aktivita4)getActivity()).showDialog();// ZOBRAZUJE DIALOG PO DOKONČENÍ

                    }
                }.start();
            }
        });
        return screen;
    }
}