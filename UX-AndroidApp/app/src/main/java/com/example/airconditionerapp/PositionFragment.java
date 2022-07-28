package com.example.airconditionerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

public class PositionFragment extends Fragment {

    private static final String EXTRA_MESSAGE = "extra message";
    private static final String DATA = "data";
    int data,message;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        Intent intent = requireActivity().getIntent();
        if(intent.getExtras()!=null) {
            data = intent.getIntExtra(DATA,20);
            message = intent.getIntExtra(EXTRA_MESSAGE,20);
        }

        if(data == 22) {
            requireContext().getTheme().applyStyle(R.style.default_title_text, true);
        }else if(data == 28){
            requireContext().getTheme().applyStyle(R.style.medium_title_text, true);
        }else if(data == 34){
            requireContext().getTheme().applyStyle(R.style.large_title_text, true);
        }
        View view = inflater.inflate(R.layout.fragment_position, container, false);
        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_position, container, false);
    }

    @SuppressLint("ResourceAsColor")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(MainActivity.getDark()) {
            TextView t = (TextView) view.findViewById(R.id.status);
            t.setTextColor(getResources().getColor(R.color.gray));
        }

        if (MainActivity.getHelp()) {
            Snackbar mySnackbar = Snackbar.make(requireActivity().findViewById(R.id.pop_up_message), R.string.positions_pop_up, Snackbar.LENGTH_LONG);
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
        view.findViewById(R.id.BackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(PositionFragment.this)
                        .navigate(R.id.action_PositionFragment_to_MainFragment);
            }
        });

        view.findViewById(R.id.edges).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.getHelp() && MainActivity.getPower()) {
                    Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.position_settled, Snackbar.LENGTH_LONG);
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
                }else {
                    Toast.makeText(requireActivity().getApplicationContext(),"Πρέπει να ξεκινήσει η λειτουργία του κλιματιστικού προκειμένου να επιτραπεί η ανάθεση των θέσης φτερών!",Toast.LENGTH_LONG).show();
                }
                NavHostFragment.findNavController(PositionFragment.this)
                        .navigate(R.id.action_PositionFragment_to_MainFragment);
            }
        });

        view.findViewById(R.id.middle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.getHelp() && MainActivity.getPower()) {
                    Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.position_settled, Snackbar.LENGTH_LONG);
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
                }else {
                    Toast.makeText(requireActivity().getApplicationContext(),"Πρέπει να ξεκινήσει η λειτουργία του κλιματιστικού προκειμένου να επιτραπεί η ανάθεση των θέσης φτερών!",Toast.LENGTH_LONG).show();
                }
                NavHostFragment.findNavController(PositionFragment.this)
                        .navigate(R.id.action_PositionFragment_to_MainFragment);
            }
        });

        view.findViewById(R.id.multiple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.getHelp() && MainActivity.getPower()) {
                    Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.position_multiple, Snackbar.LENGTH_LONG);
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
                }else {
                    Toast.makeText(requireActivity().getApplicationContext(),"Πρέπει να ξεκινήσει η λειτουργία του κλιματιστικού προκειμένου να επιτραπεί η ανάθεση των θέσης φτερών!",Toast.LENGTH_LONG).show();
                }
                NavHostFragment.findNavController(PositionFragment.this)
                        .navigate(R.id.action_PositionFragment_to_MainFragment);
            }
        });

    }
}