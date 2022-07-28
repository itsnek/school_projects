package com.example.airconditionerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class IntesityFragment extends Fragment {

    private static final String EXTRA_MESSAGE = "extra message";
    private static final String DATA = "data";
    int data,message;

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
        View view = inflater.inflate(R.layout.fragment_intesity, container, false);
        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_intesity, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);

        if(MainActivity.getDark()) {
            TextView t = (TextView) view.findViewById(R.id.mode);
            t.setTextColor(getResources().getColor(R.color.gray));
        }

        if(MainActivity.getHelp()) {
            Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.intensity_pop_up, Snackbar.LENGTH_LONG);
            mySnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
            final View v = mySnackbar.getView();
            TextView tv = (TextView) v.findViewById(R.id.snackbar_text);
            tv.setMaxLines(10);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,MainActivity.getFontSize());
            if(MainActivity.getDark()) {
                mySnackbar.setBackgroundTint(getResources().getColor(R.color.NavyBlueDark));
                ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.NavyBlueDark));
                tv.setBackground(cd);
            }
            mySnackbar.setText(tv.getText());
            mySnackbar.show();
        }

        //  Back Button
        view.findViewById(R.id.BackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(IntesityFragment.this)
                        .navigate(R.id.action_IntensityFragment_to_ColdFragment);
            }
        });

        //  Auto Button
        view.findViewById(R.id.auto).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setIntensity("auto");

                if(MainActivity.getHelp() && MainActivity.getPower()) {
                    Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.last_pop_up, Snackbar.LENGTH_LONG);
                    mySnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                    final View view = mySnackbar.getView();
                    TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
                    tv.setMaxLines(10);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,MainActivity.getFontSize());
                    if(MainActivity.getDark()) {
                        mySnackbar.setBackgroundTint(getResources().getColor(R.color.NavyBlueDark));
                        ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.NavyBlueDark));
                        tv.setBackground(cd);
                    }
                    mySnackbar.setText(tv.getText());
                    mySnackbar.show();
                }
                showInfo();
                NavHostFragment.findNavController(IntesityFragment.this)
                        .navigate(R.id.action_IntensityFragment_to_MainFragment);
            }
        });

        //  Low Button
        view.findViewById(R.id.low).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setIntensity("low");

                if(MainActivity.getHelp() && MainActivity.getPower()) {
                    Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.last_pop_up, Snackbar.LENGTH_LONG);
                    mySnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                    final View view1 = mySnackbar.getView();
                    TextView tv = (TextView) view1.findViewById(R.id.snackbar_text);
                    tv.setMaxLines(10);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,MainActivity.getFontSize());
                    if(MainActivity.getDark()) {
                        mySnackbar.setBackgroundTint(getResources().getColor(R.color.NavyBlueDark));
                        ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.NavyBlueDark));
                        tv.setBackground(cd);
                    }
                    mySnackbar.setText(tv.getText());
                    mySnackbar.show();
                }
                showInfo();
                NavHostFragment.findNavController(IntesityFragment.this)
                        .navigate(R.id.action_IntensityFragment_to_MainFragment);
            }
        });

        //  Middle Button
        view.findViewById(R.id.middle).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setIntensity("medium");

                if(MainActivity.getHelp() && MainActivity.getPower()) {
                    Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.last_pop_up, Snackbar.LENGTH_LONG);
                    mySnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                    final View view2 = mySnackbar.getView();
                    TextView tv = (TextView) view2.findViewById(R.id.snackbar_text);
                    tv.setMaxLines(10);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,MainActivity.getFontSize());
                    if(MainActivity.getDark()) {
                        mySnackbar.setBackgroundTint(getResources().getColor(R.color.NavyBlueDark));
                        ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.NavyBlueDark));
                        tv.setBackground(cd);
                    }
                    mySnackbar.setText(tv.getText());
                    mySnackbar.show();
                }
                showInfo();
                NavHostFragment.findNavController(IntesityFragment.this)
                        .navigate(R.id.action_IntensityFragment_to_MainFragment);
            }
        });

        //  High Button
        view.findViewById(R.id.high).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                MainActivity.setIntensity("high");

                if(MainActivity.getHelp() && MainActivity.getPower()) {
                    Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.last_pop_up, Snackbar.LENGTH_LONG);
                    mySnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                    final View view3 = mySnackbar.getView();
                    TextView tv = (TextView) view3.findViewById(R.id.snackbar_text);
                    tv.setMaxLines(10);
                    tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,MainActivity.getFontSize());
                    if(MainActivity.getDark()) {
                        mySnackbar.setBackgroundTint(getResources().getColor(R.color.NavyBlueDark));
                        ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.NavyBlueDark));
                        tv.setBackground(cd);
                    }
                    mySnackbar.setText(tv.getText());
                    mySnackbar.show();
                }
                showInfo();
                NavHostFragment.findNavController(IntesityFragment.this)
                        .navigate(R.id.action_IntensityFragment_to_MainFragment);
            }
        });
    }

    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showInfo(){
        LinearLayout l = requireActivity().findViewById(R.id.linearLayoutInfo);

        if(MainActivity.getIntensity()!=null && MainActivity.getPower()){
            TextView temperature = (TextView) requireActivity().findViewById(R.id.Temp);
            @SuppressLint("ResourceType") TextView temperatureUnit = (TextView) requireActivity().findViewById(R.string.celcius);
            TextView status = (TextView) requireActivity().findViewById(R.id.Status_Type);
            TextView intensity = (TextView) requireActivity().findViewById(R.id.intensity);

            l.setVisibility(View.VISIBLE);

            temperature.setText(String.valueOf(MainActivity.getTemp()) + getResources().getString(R.string.celcius));
            if (MainActivity.getStatus().equals("heat")) {
                status.setBackground(requireActivity().getDrawable(R.drawable.heat_ic));
            } else if (MainActivity.getStatus().equals("cold")) {
                status.setBackground(requireActivity().getDrawable(R.drawable.cold_ic));
            } else if (MainActivity.getStatus().equals("humid")) {
                status.setBackground(requireActivity().getDrawable(R.drawable.humid_ic));
            } else if (MainActivity.getStatus().equals("air")) {
                status.setBackground(requireActivity().getDrawable(R.drawable.air_ic));
            }

            if (MainActivity.getIntensity().equals("high")) {
                intensity.setText("Υ");
            } else if (MainActivity.getIntensity().equals("medium")) {
                intensity.setText("M");
            } else if (MainActivity.getIntensity().equals("low")) {
                intensity.setText("Χ");
            } else if (MainActivity.getIntensity().equals("auto")) {
                intensity.setText("Α");
            }

            if(MainActivity.getDark()){
                temperature.setTextColor(getResources().getColor(R.color.gray));
                status.setTextColor(getResources().getColor(R.color.gray));
                intensity.setTextColor(getResources().getColor(R.color.gray));
            }
        }else {
            Toast.makeText(requireActivity().getApplicationContext(),"Πρέπει να ξεκινήσει η λειτουργία του κλιματιστικού προκειμένου να επιτραπεί η ανάθεση τρόπου λειτουργίας!",Toast.LENGTH_LONG).show();
        }

    }

}