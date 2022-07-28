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
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class TimerFragmentAlt extends Fragment {

    private static final String EXTRA_MESSAGE = "extra message";
    private static final String DATA = "data";
    private static boolean alt = false;
    int h,m,data,message;

    public static void setAlt(boolean alt) {
        TimerFragmentAlt.alt = alt;
    }

    public static boolean getAlt(){
        return  alt;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = requireActivity().getIntent();
        if(intent.getExtras()!=null) {
            data = intent.getIntExtra(EXTRA_MESSAGE, 22);
        }

        if(data == 22) {
            requireContext().getTheme().applyStyle(R.style.default_title_text, true);
        }else if(data == 28){
            requireContext().getTheme().applyStyle(R.style.medium_title_text, true);
        }else if(data == 34){
            requireContext().getTheme().applyStyle(R.style.large_title_text, true);
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer_alt, container, false);

        return view;
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        if(MainActivity.getDark()){
            ImageView img = requireActivity().findViewById(R.id.informationT2);
            img.setImageResource(R.drawable.info_icon_dark);
        }

        TextView hours = (TextView)view.findViewById(R.id.hours);
        TextView minutes = (TextView)view.findViewById(R.id.minutes);
        LinearLayout l = requireActivity().findViewById(R.id.linearLayoutTimer);
        Button b = requireActivity().findViewById(R.id.Cancel2);

        if(MainActivity.getPower()){
            if(requireActivity().findViewById(R.id.linearLayoutTimer).getVisibility() != View.VISIBLE){
                b.setVisibility(View.INVISIBLE);
            }else{
                b.setVisibility(View.VISIBLE);
            }
        }

        if(MainActivity.getDark()) {
            TextView t = (TextView) view.findViewById(R.id.mode);
            t.setTextColor(getResources().getColor(R.color.gray));
            t = (TextView) view.findViewById(R.id.text);
            t.setTextColor(getResources().getColor(R.color.gray));
            t = hours;
            t.setTextColor(getResources().getColor(R.color.gray));
            t = minutes;
            t.setTextColor(getResources().getColor(R.color.gray));
            t = (TextView) view.findViewById(R.id.double_dots);
            t.setTextColor(getResources().getColor(R.color.gray));

            if(MainActivity.getPower()){
                if(requireActivity().findViewById(R.id.linearLayoutTimer).getVisibility() != View.VISIBLE){
                    b.setVisibility(View.INVISIBLE);
                }else{
                    b.setVisibility(View.VISIBLE);
                }
            }
        }

        if(MainActivity.getHelp()) {
            Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), R.string.Timer_pop_up_alt, Snackbar.LENGTH_LONG);
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

        try{

            h = Integer.parseInt((String) hours.getText());
            m = Integer.parseInt((String) minutes.getText());

            view.findViewById(R.id.time_from_now).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    NavHostFragment.findNavController(TimerFragmentAlt.this)
                            .navigate(R.id.action_TimerFragmentAlt_to_TimerFragment);
                }
            });

             view.findViewById(R.id.time_choice).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {}
            });

            view.findViewById(R.id.upBtn1).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if(h < 23) {
                        h++;
                        if(h < 10) {
                            hours.setText("0" + Integer.toString(h));
                        }
                        else hours.setText(Integer.toString(h));
                    }
                    else if(h == 23){
                        h = 0;
                        hours.setText("0" + Integer.toString(h));
                    }
                }

            });

            view.findViewById(R.id.downBtn1).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if(h > 0) {
                        h--;
                        if(h < 10) {
                            hours.setText("0" + Integer.toString(h));
                        }
                        else hours.setText(Integer.toString(h));
                    }
                    else if(h == 0){
                        h = 23;
                        hours.setText(Integer.toString(h));
                    }
                }
            });

            view.findViewById(R.id.upBtn2).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if(m < 59) {
                        m++;
                        if(m < 10) {
                            minutes.setText("0" + Integer.toString(m));
                        }
                        else minutes.setText(Integer.toString(m));
                    }
                    else if(m == 59){
                        m = 0;
                        minutes.setText("0" + Integer.toString(m));
                    }
                }

            });

            view.findViewById(R.id.downBtn2).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    if(m > 0) {
                        m--;
                        if(m < 10) {
                            minutes.setText("0" + Integer.toString(m));
                        }
                        else minutes.setText(Integer.toString(m));
                    }
                    else if(m == 0){
                        m = 59;
                        minutes.setText(Integer.toString(m));
                    }
                }
            });


            view.findViewById(R.id.BackButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavHostFragment.findNavController(TimerFragmentAlt.this)
                            .navigate(R.id.action_TimerFragmentAlt_to_MainFragment);
                }
            });

            view.findViewById(R.id.Cancel2).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Button b = requireActivity().findViewById(R.id.Cancel2);
                    LinearLayout l = requireActivity().findViewById(R.id.linearLayoutTimer);
                    l.setVisibility(View.INVISIBLE);
                    MainActivity.setTime(null);
                    if (TimerFragment.getCounter() != null) TimerFragment.getCounter().cancel();
                    if (MainActivity.getCounter() != null) MainActivity.getCounter().cancel();
                    b.setVisibility(View.INVISIBLE);

                    if(MainActivity.getHelp()) {
                        Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), "Ο χρονοδιακόπτης ακυρώθηκε επιτυχώς!" , Snackbar.LENGTH_LONG);
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
                }
            });

            view.findViewById(R.id.submit_btn).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    NavHostFragment.findNavController(TimerFragmentAlt.this)
                            .navigate(R.id.action_TimerFragmentAlt_to_MainFragment);
                    String temp;
                    if(h > 1 && m == 0){
                        temp = getContext().getString(R.string.timer_alt_submit) + " " + h + " η ώρα";
                        MainActivity.setTime(String.valueOf(h) + " : 00");
                    }
                    else if(h == 1 && m == 0){
                        temp = getContext().getString(R.string.timer_alt_submit2) + " " + h + " η ώρα";
                        MainActivity.setTime(String.valueOf(h) + " : 00");
                    }
                    else if(h == 0 && m == 0){
                        temp = getContext().getString(R.string.timer_alt_submit) + " " + 12 + " το βράδυ ";
                        MainActivity.setTime("00 : 00");
                    }
                    else if(h == 0 && m > 1){
                        temp = getContext().getString(R.string.timer_alt_submit) + " " + 12 + " το βράδυ και " + m + " λεπτά";
                        MainActivity.setTime("00 : " + String.valueOf(m));
                    }
                    else if(h == 0 && m == 1){
                        temp = getContext().getString(R.string.timer_alt_submit) + " " +  12 + " το βράδυ και " + m + " λεπτό";
                        MainActivity.setTime("00 : 01");
                    }
                    else if(h > 1 && m > 1){
                        temp = getContext().getString(R.string.timer_alt_submit) + " " + h + " και " + m ;
                        MainActivity.setTime(String.valueOf(h) + " : " + String.valueOf(m));
                    }
                    else if(h == 1 && m > 1){
                        temp = getContext().getString(R.string.timer_alt_submit2) + " " + h + " και " + m ;
                        MainActivity.setTime(String.valueOf(h) + " : " + String.valueOf(m));
                    }
                    else if(h == 1 && m == 1){
                        temp = getContext().getString(R.string.timer_alt_submit2) + " " + h + " και " + m ;
                        MainActivity.setTime(String.valueOf(h) + " : " + String.valueOf(m));
                    }
                    else if(h > 1 && m == 1){
                        temp = getContext().getString(R.string.timer_alt_submit) + " " + h + " και " + m ;
                        MainActivity.setTime(String.valueOf(h) + " : " + String.valueOf(m));
                    }
                    else if(h < 0 || m < 0){
                        temp = "Δεν επιλέχθηκε κάποια διαθέσιμη ώρα.Ο χρονοδιακόπτης δεν τέθηκε σε λειτουργία.";
                    }
                    else{
                        temp = "Δεν επιλέχθηκε κάποια διαθέσιμη ώρα.Ο χρονοδιακόπτης δεν τέθηκε σε λειτουργία.";
                    }

                    if(h<10 && m<10){
                        MainActivity.setTime("0" + String.valueOf(h) + " : 0" + String.valueOf(m));
                    }else if(h<10){
                        MainActivity.setTime("0" + String.valueOf(h) + " : " + String.valueOf(m));
                    }else if(m<10){
                        MainActivity.setTime(String.valueOf(h) + " : 0" + String.valueOf(m));
                    }

                    if(MainActivity.getHelp() && MainActivity.getPower()) {
                        Snackbar mySnackbar = Snackbar.make(view.findViewById(R.id.pop_up_message), temp, Snackbar.LENGTH_LONG);
                        mySnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                        final View view = mySnackbar.getView();
                        TextView tv = (TextView) view.findViewById(R.id.snackbar_text);
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
                    if(MainActivity.getPower()){
                        showTime();
                    }else {
                        Toast.makeText(requireActivity().getApplicationContext(),"Πρέπει να ξεκινήσει η λειτουργία του κλιματιστικού προκειμένου να επιτραπεί η ανάθεση χρονοδιακόπτη!",Toast.LENGTH_LONG).show();
                    }
                }

            });

            final TextView hoverText = (TextView) requireActivity().findViewById(R.id.hoverTextT2);

            view.findViewById(R.id.informationT2).setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @SuppressLint({"StringFormatInvalid", "SetTextI18n", "UseCompatLoadingForDrawables"})
                @Override
                public void onClick(View v) {
                    hoverText.setText("\nΔιευκρινήσεις : \n" +
                            "\n 1) Στην λειτουργία 'Χρόνος από τώρα' ο χρήστης καθορίζει σε πόση ώρα θέλει να σταματήσει η λειτουργία του κλιματιστικού." +
                            "Δηλαδή θέτει έναν μετρητή αντίστροφης μέτρησης ο οποίος εμφανίζεται κάτω δεξιά της οθόνης." +
                            "Στο πρώτο πεδίο με αριθμούς ο χρήστης ρυθμίζει σε πόσες ώρες θέλει να σταματήσει η λειτουργία,ενώ στο δεύτερο πεδίο ο χρήστης ρυθμίζει τα λεπτά." +
                            "\n 2) Στην λειτουργία 'Επιλογή συγκεκριμένης ώρας' ο χρήστης καθορίζει την ακριβής ώρα που θέλει να σταματήσει η λειτουργία του κλιματιστικού." +
                            "Δηλαδή θέτει έναν ειδοποιητή για την ώρα που έχει ρυθμιστεί να τερματίσει την λειτουργία,ο οποίος εμφανίζεται κάτω δεξιά της οθόνης." +
                            "Στο πρώτο πεδίο με αριθμούς ο χρήστης ρυθμίζει σε πόσες ώρες θέλει να σταματήσει η λειτουργία,ενώ στο δεύτερο πεδίο ο χρήστης ρυθμίζει τα λεπτά." +
                            "Στο πρώτο πεδίο με αριθμούς ο χρήστης ρυθμίζει σε πόσες ώρες θέλει να σταματήσει η λειτουργία,ενώ στο δεύτερο πεδίο ο χρήστης ρυθμίζει τα λεπτά." +
                            "\n 3) Όταν είναι σε λειτουργία το κλιματιστικό και έχει οριστεί χρονοδιακόπτης,εμφανίζεται ένα πλήκτρο 'Ακύρωσης' όπου ακυρώνει τον χρονοδιακόπτη," +
                            " ανεξάρτητα από τον τρόπο λειτουργίας του που είχατε επιλέξει.\n" );
                    hoverText.setTextSize(26);
                    hoverText.setTextColor(requireActivity().getResources().getColor(R.color.white));
                    hoverText.setBackground(requireActivity().getDrawable(R.drawable.hovertextschema));
                    hoverText.setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.nav_default_enter_anim));
                    requireActivity().findViewById(R.id.x_buttonT2).setBackgroundColor(requireActivity().getResources().getColor(R.color.NavyBlue));
                    requireActivity().findViewById(R.id.hoverTextLayoutT2).setVisibility(View.VISIBLE);
                }

            });

            view.findViewById(R.id.x_buttonT2).setOnClickListener(new View.OnClickListener() {
                @SuppressLint({"StringFormatInvalid", "ResourceType"})
                @Override
                public void onClick(View v) {
                    requireActivity().findViewById(R.id.hoverTextLayoutT2).setAnimation(AnimationUtils.loadAnimation(getContext(),R.anim.nav_default_exit_anim));
                    requireActivity().findViewById(R.id.hoverTextLayoutT2).setVisibility(View.INVISIBLE);
                }

            });

        } catch(NumberFormatException ex){
            // handle your exception
            ex.printStackTrace();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void showTime(){

        LinearLayout l = requireActivity().findViewById(R.id.linearLayoutTimer);

        if(MainActivity.getTime() != null && MainActivity.getPower()){
            if(TimerFragment.getCounter() != null){
                TimerFragment.getCounter().cancel();
            }
            setAlt(true);
            if(MainActivity.getCounter() != null){
                MainActivity.getCounter().cancel();
            }
            l.setVisibility(View.VISIBLE);
            TextView time = (TextView) requireActivity().findViewById(R.id.time);
            TextView icon = (TextView) requireActivity().findViewById(R.id.Icon);

            icon.setBackground(requireActivity().getDrawable(R.drawable.timer_ic));
            time.setText(MainActivity.getTime());
            if(MainActivity.getDark()){
                time.setTextColor(getResources().getColor(R.color.gray));
            }

        }
    }
}