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

import com.google.android.material.snackbar.Snackbar;

public class HumidFragment extends Fragment {

    private static final String EXTRA_MESSAGE = "extra message";
    private static final String DATA = "data";
    int temperature,data,message;

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
        View view = inflater.inflate(R.layout.fragment_humid, container, false);
        return view;
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_humid, container, false);
    }

    @SuppressLint("CutPasteId")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        MainActivity.setStatus("humid");

        if(MainActivity.getDark()) {
            TextView t = (TextView) view.findViewById(R.id.mode);
            t.setTextColor(getResources().getColor(R.color.gray));
            t = (TextView) view.findViewById(R.id.temperatureText);
            t.setTextColor(getResources().getColor(R.color.gray));
            t = (TextView) view.findViewById(R.id.temperatureUnit);
            t.setTextColor(getResources().getColor(R.color.gray));
        }

        if(MainActivity.getHelp()) {
            Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.humid_pop_up, Snackbar.LENGTH_LONG);
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

        TextView text = view.findViewById(R.id.temperatureText);

        try{

            temperature = Integer.parseInt((String) text.getText());

            view.findViewById(R.id.upBtn).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    temperature++;
                    text.setText(Integer.toString(temperature));
                }

            });

            view.findViewById(R.id.downBtn).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    temperature--;
                    text.setText(Integer.toString(temperature));
                }
            });

            view.findViewById(R.id.BackButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavHostFragment.findNavController(HumidFragment.this)
                            .navigate(R.id.action_HumidFragment_to_ModesFragment);
                }
            });

            view.findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.setTemp(temperature);
                    NavHostFragment.findNavController(HumidFragment.this)
                            .navigate(R.id.action_HumidFragment_to_IntesityFragment);
                }
            });

        } catch(NumberFormatException ex){
            // handle your exception
            ex.printStackTrace();
        }
    }
}