package com.example.airconditionerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class MainFragment extends Fragment {

    private static final String EXTRA_MESSAGE = "extra message";
    private static final String DATA = "data";
    int data = 0,message = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = requireActivity().getIntent();
        if(intent.getExtras()!=null) {
            data = intent.getIntExtra(DATA,22);
            message = intent.getIntExtra(EXTRA_MESSAGE, 22);
        }

        if(data == 22) {
            requireContext().getTheme().applyStyle(R.style.default_title_text, true);
        }else if(data == 28){
            requireContext().getTheme().applyStyle(R.style.medium_title_text, true);
        }else if(data == 34){
            requireContext().getTheme().applyStyle(R.style.large_title_text, true);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        if(MainActivity.getDark()){
            ImageView img = requireActivity().findViewById(R.id.information);
            img.setImageResource(R.drawable.info_icon_dark);
        }

        view.findViewById(R.id.ModesBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(MainFragment.this)
                    .navigate(R.id.action_MainFragment_to_ModesFragment);
            }
        });

        view.findViewById(R.id.TimerBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_MainFragment_to_TimerFragment);
            }
        });

        view.findViewById(R.id.PositionBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MainFragment.this)
                        .navigate(R.id.action_MainFragment_to_PositionFragment);
            }
        });

        view.findViewById(R.id.DefaultBtn).setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"ResourceAsColor", "ResourceType"})
            @Override
            public void onClick(View view) {
                if(MainActivity.getPower()){
                    if (MainActivity.getHelp()) {
                        Snackbar mySnackbar = Snackbar.make(requireActivity().findViewById(R.id.pop_up_message), R.string.default_pop_up, Snackbar.LENGTH_LONG);
                        mySnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                        final View v = mySnackbar.getView();
                        TextView tv = (TextView) v.findViewById(R.id.snackbar_text);
                        tv.setMaxLines(10);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, MainActivity.getFontSize());
                        if (MainActivity.getDark()) {
                            mySnackbar.setBackgroundTint(getResources().getColor(R.color.NavyBlueDark));
                            ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.NavyBlueDark));
                            tv.setBackground(cd);
                        }
                        mySnackbar.setText(tv.getText());
                        mySnackbar.show();
                    }
                }else {
                    Toast.makeText(requireActivity().getApplicationContext(),"Πρέπει να ξεκινήσει η λειτουργία του κλιματιστικού προκειμένου να επιτραπεί η ανάθεση της προδιαγεγραμμένης λειτουργίας!",Toast.LENGTH_LONG).show();
                }

                if (MainActivity.getStatus() != null) {
                    LinearLayout l = requireActivity().findViewById(R.id.linearLayoutInfo);
                    TextView temperature = (TextView) requireActivity().findViewById(R.id.Temp);
                    @SuppressLint("ResourceType") TextView temperatureUnit = (TextView) requireActivity().findViewById(R.string.celcius);
                    TextView status = (TextView) requireActivity().findViewById(R.id.Status_Type);
                    TextView intensity = (TextView) requireActivity().findViewById(R.id.intensity);

                    if (MainActivity.getStatus().equals("heat")) {
                        status.setBackground(getResources().getDrawable(R.drawable.heat_ic));
                        temperature.setText(getResources().getString(R.integer.default_temp_heat) + getResources().getString(R.string.celcius));
                    } else if (MainActivity.getStatus().equals("cold")) {
                        status.setBackground(getResources().getDrawable(R.drawable.cold_ic));
                        temperature.setText(getResources().getString(R.integer.default_temp_cold) + getResources().getString(R.string.celcius));
                    } else if (MainActivity.getStatus().equals("humid")) {
                        status.setBackground(getResources().getDrawable(R.drawable.humid_ic));
                        temperature.setText(getResources().getString(R.integer.default_temp_humid) + getResources().getString(R.string.celcius));
                    } else if (MainActivity.getStatus().equals("air")) {
                        status.setBackground(getResources().getDrawable(R.drawable.air_ic));
                        temperature.setText(getResources().getString(R.integer.default_temp_air) + getResources().getString(R.string.celcius));
                    }

                    intensity.setText("Α");
                    MainActivity.setIntensity("auto");

                }
            }
        });

        final TextView hoverText = (TextView) requireActivity().findViewById(R.id.hoverText);

        view.findViewById(R.id.information).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("StringFormatInvalid")
            @Override
            public void onClick(View v) {
                hoverText.setText("\nΔιευκρινήσεις : \n" +
                        "\n 1)Το εικονίδιο υποδεικνύει την κατάσταση λειτουργίας του κλιματιστικού." +
                        "\n 2)Ο αριθμός υποδεικνύει την θερμοκρασία που έχετε ορίσει(ή έχει οριστεί κατά την προδιαγεγραμμένη λειτουργία) για την κατάσταση λειτουργίας που έχει επιλεγεί." +
                        "\n 3)Ο χαρακτήρας υποδεικνύει την ένταση που έχει επιλεγεί,κατά το αρχικό του κάθε τρόπου λειτουργίας(π.χ. Αυτόματο -> Α)" +
                        "\n 4)Το εικονίδιο του ρολογιού υποδεικνύει οτι έχει τεθεί ο χρονοδιακόπτης σε λειτουργία και ακόλουθα δίπλα εμφανίζεται " +
                        "είτε η αντίστροφη μέτρηση αν πρόκειται για την πρώτη επιλογή του χρονοδιακόπτη είτε η ακριβής ώρα που έχει οριστεί να τερματίσει η λειτουργία του κλιματιστικού.\n");
                hoverText.setTextSize(26);
                hoverText.setTextColor(requireActivity().getResources().getColor(R.color.white));
                hoverText.setBackground(requireActivity().getDrawable(R.drawable.hovertextschema));
                hoverText.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.nav_default_enter_anim));
                requireActivity().findViewById(R.id.x_button).setBackgroundColor(requireActivity().getResources().getColor(R.color.NavyBlue));
                requireActivity().findViewById(R.id.hoverTextLayout).setVisibility(View.VISIBLE);
            }

        });

        view.findViewById(R.id.x_button).setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"StringFormatInvalid", "ResourceType"})
            @Override
            public void onClick(View v) {
                requireActivity().findViewById(R.id.hoverTextLayout).setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.nav_default_exit_anim));
                requireActivity().findViewById(R.id.hoverTextLayout).setVisibility(View.INVISIBLE);
            }

        });
    }
}
