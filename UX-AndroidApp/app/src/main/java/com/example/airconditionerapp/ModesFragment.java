package com.example.airconditionerapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class ModesFragment extends Fragment {

    private static final String EXTRA_MESSAGE = "extra message";
    private static final String DATA = "data";
    int data = 20,message;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
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
        View view = inflater.inflate(R.layout.fragment_modes, container, false);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(MainActivity.getDark()) {
            TextView t = (TextView) view.findViewById(R.id.status);
            t.setTextColor(getResources().getColor(R.color.gray));
        }

        if(MainActivity.getHelp()) {
            Snackbar mySnackbar = Snackbar.make(getActivity().findViewById(R.id.pop_up_message),R.string.modes_pop_up , Snackbar.LENGTH_LONG);
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
                NavHostFragment.findNavController(ModesFragment.this)
                        .navigate(R.id.action_ModesFragment_to_MainFragment);
            }
        });

        view.findViewById(R.id.Cold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModesFragment.this)
                        .navigate(R.id.action_ModesFragment_to_ColdFragment);

            }
        });

        view.findViewById(R.id.Heat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModesFragment.this)
                        .navigate(R.id.action_ModesFragment_to_HeatFragment);

            }
        });

        view.findViewById(R.id.Humid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModesFragment.this)
                        .navigate(R.id.action_ModesFragment_to_HumidFragment);

            }
        });

        view.findViewById(R.id.Air).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModesFragment.this)
                        .navigate(R.id.action_ModesFragment_to_AirFragment);

            }
        });
    }

}