package com.example.airconditionerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import com.google.android.material.snackbar.Snackbar;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "extra_message";
    private static final String DATA = "data";
    private static final String MODE = "mode";
    private static final String POWER = "power";
    private static final String STATUS = "status";
    private static final String INTESITY = "intensity";
    private static final String TEMP = "temperature";
    private static final String TIME = "time";
    private static final String TIME_LEFT = "time left";
    private static CountDownTimer counter;
    private static Boolean help=false,dark=false,power=false;
    private static int fontSize = 20,temp = 0;
    public static String status = null,intensity = null,time = null;
    int data = 22,id = 0,message = 0,mode = 0;
    String modeCheck = null;
    ImageButton logo;
    Toolbar toolbar;
    androidx.appcompat.widget.SwitchCompat sw;
    Menu m;

    public static void setHelp(Boolean help) {
        MainActivity.help = help;
    }

    public void setFontSize(int fontSize) {
        MainActivity.fontSize = fontSize;
    }

    public void setM(Menu m) {
        this.m = m;
    }

    public static void setDark(Boolean dark) {
        MainActivity.dark = dark;
    }

    public static void setPower(Boolean power) {
        MainActivity.power = power;
    }

    public static void setTemp(int temp) {
        MainActivity.temp = temp;
    }

    public static void setStatus(String status) {
        MainActivity.status = status;
    }

    public static void setIntensity(String intensity) {
        MainActivity.intensity = intensity;
    }

    public static void setTime(String time) {
        MainActivity.time = time;
    }

    public static Boolean getHelp() {
        return help;
    }

    public static int getFontSize() {
        return fontSize;
    }

    public Menu getM() {
        return m;
    }

    public static Boolean getDark() {
        return dark;
    }

    public static Boolean getPower() {
        return power;
    }

    public static int getTemp() {
        return temp;
    }

    public static String getStatus() {
        return status;
    }

    public static String getIntensity() {
        return intensity;
    }

    public static String getTime() {
        return time;
    }

    public static CountDownTimer getCounter() {
        return counter;
    }

    void refreshView(){

        if (id == R.id.Dark_mode && !getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.defaultSize).isChecked()){
            message = R.id.defaultSize;
            mode = 0;
        }else if (id == R.id.Dark_mode && !getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.mediumSize).isChecked()){
            message = R.id.mediumSize;
            mode = 0;
        }else if (id == R.id.Dark_mode && !getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.largeSize).isChecked()){
            message = R.id.largeSize;
            mode = 0;
        }else if (id == R.id.Dark_mode && getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.mediumSize).isChecked()){
            message = R.id.mediumSize;
            mode = R.id.Dark_mode;
        }else if (id == R.id.Dark_mode && getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.largeSize).isChecked()){
            message = R.id.largeSize;
            mode = R.id.Dark_mode;
        }else if (id == R.id.mediumSize && !getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.mediumSize).isChecked()){
            message = R.id.mediumSize;
            mode = 0;
        }else if (id == R.id.largeSize && !getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.largeSize).isChecked()){
            message = R.id.largeSize;
            mode = 0;
        }else if (id == R.id.mediumSize && getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.mediumSize).isChecked()){
            message = R.id.mediumSize;
            mode = R.id.Dark_mode;
        }else if (id == R.id.largeSize && getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.largeSize).isChecked()) {
            message = R.id.largeSize;
            mode = R.id.Dark_mode;
        }else if (id == R.id.Dark_mode && getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.defaultSize).isChecked()){
            message = R.id.defaultSize;
            mode = R.id.Dark_mode;
        }else if (id == R.id.Dark_mode && getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.mediumSize).isChecked()){
            message = R.id.mediumSize;
            mode = R.id.Dark_mode;
        }else if (id == R.id.Dark_mode && getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.largeSize).isChecked()) {
            message = R.id.largeSize;
            mode = R.id.Dark_mode;
        }else if (id == R.id.defaultSize && !getM().findItem(R.id.Dark_mode).isChecked() && getM().findItem(R.id.defaultSize).isChecked()){
            message = R.id.defaultSize;
            mode = 0;
        }else if (id == R.id.mediumSize && !getM().findItem(R.id.mediumSize).isChecked() && getM().findItem(R.id.Dark_mode).isChecked()){
            message = R.id.defaultSize;
            mode = R.id.Dark_mode;
        }else if (id == R.id.largeSize && !getM().findItem(R.id.largeSize).isChecked() && getM().findItem(R.id.Dark_mode).isChecked()){
            message = R.id.defaultSize;
            mode = R.id.Dark_mode;
        }
        
        Intent intent = getIntent();
        Integer data = getFontSize();
        intent.putExtra(EXTRA_MESSAGE,message);
        intent.putExtra(DATA,data);
        intent.putExtra(MODE,mode);
        intent.putExtra(POWER,power);
        intent.putExtra(STATUS,getStatus());
        intent.putExtra(INTESITY,getIntensity());
        intent.putExtra(TEMP,getTemp());
        intent.putExtra(TIME,getTime());
        if(TimerFragment.getCounter() != null){
            intent.putExtra(TIME_LEFT,TimerFragment.getTleft());
        }
        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    @SuppressLint({"ResourceType", "CutPasteId", "UseCompatLoadingForDrawables", "SetTextI18n"})
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long time_left = 0;
        Intent intent = getIntent();
        if(intent.getExtras()!=null) {
            data = intent.getIntExtra(DATA,22);
            message = intent.getIntExtra(EXTRA_MESSAGE,22);
            mode = intent.getIntExtra(MODE,0);
            power = intent.getBooleanExtra(POWER,false);
            time_left = intent.getLongExtra(TIME_LEFT,0);

            if(modeCheck != null) {
                if (!modeCheck.equals(intent.getStringExtra(STATUS))) {
                    setStatus(modeCheck);
                } else {
                    status = intent.getStringExtra(STATUS);
                }
            }else status = intent.getStringExtra(STATUS);
            intensity = intent.getStringExtra(INTESITY);
            temp = intent.getIntExtra(TEMP,25);
            time = intent.getStringExtra(TIME);
        }

        if(message == R.id.defaultSize || data == 22){
            message = R.id.defaultSize;
            setTheme(R.style.default_title_text);
        }else if(message == R.id.mediumSize || data == 28){
            setTheme(R.style.medium_title_text);
        }else if (message == R.id.largeSize || data == 34){
            setTheme(R.style.large_title_text);
        }

        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_company_logo_foreground);
//        toolbar.setLogo(R.mipmap.ic_company_logo_foreground);
        toolbar.setOverflowIcon(getDrawable(R.drawable.ic_settings_icon_both));

        LinearLayout l = findViewById(R.id.linearLayoutInfo);
        LinearLayout l2 = findViewById(R.id.linearLayoutTimer);
        TextView temperature = (TextView) findViewById(R.id.Temp);
        TextView s = (TextView) findViewById(R.id.Status_Type);
        TextView inten = (TextView) findViewById(R.id.intensity);
        TextView t = findViewById(R.id.textOnOff);
        TextView temp = findViewById(R.id.Temp);
        TextView status = findViewById(R.id.Status_Type);
        TextView intensity = findViewById(R.id.intensity);
        sw = findViewById(R.id.PowerSwitch);

        if(mode == R.id.Dark_mode){
            setTheme(R.style.Theme_AirConditionerApp_MyCustomThemeDark);
            toolbar.setBackgroundColor(R.color.NavyBlue);
            TextView tv = (TextView) findViewById(R.id.textOnOff);
            tv.setTextColor(getResources().getColor(R.color.gray));

            if(getIntensity() !=null && getPower()) {
                l.setVisibility(View.VISIBLE);
                if(getTime() != null){
                    l2.setVisibility(View.VISIBLE);
                }

                temperature.setText(getTemp() + getResources().getString(R.string.celcius));
                if (getStatus().equals("heat")) {
                    s.setBackground(getDrawable(R.drawable.heat_ic));
                } else if (getStatus().equals("cold")) {
                    s.setBackground(getDrawable(R.drawable.cold_ic));
                } else if (getStatus().equals("humid")) {
                    s.setBackground(getDrawable(R.drawable.humid_ic));
                } else if (getStatus().equals("air")) {
                    s.setBackground(getDrawable(R.drawable.air_ic));
                }

                if (getIntensity().equals("high")) {
                    inten.setText("Υ");
                } else if (getIntensity().equals("medium")) {
                    inten.setText("M");
                } else if (getIntensity().equals("low")) {
                    inten.setText("Χ");
                } else if (getIntensity().equals("auto")) {
                    inten.setText("Α");
                }
                temperature.setTextColor(getResources().getColor(R.color.gray));
                s.setTextColor(getResources().getColor(R.color.gray));
                inten.setTextColor(getResources().getColor(R.color.gray));
            }

            if(getTime() != null && getPower()){
                l2.setVisibility(View.VISIBLE);
                TextView time = (TextView) findViewById(R.id.time);
                TextView icon = (TextView) findViewById(R.id.Icon);

                icon.setBackground(getDrawable(R.drawable.timer_ic));
                time.setText(MainActivity.getTime());
                time.setTextColor(getResources().getColor(R.color.gray));
            }

            if(time_left != 0  && getPower()){
                l2.setVisibility(View.VISIBLE);
                TextView time = (TextView) findViewById(R.id.time);
                TextView icon = (TextView) findViewById(R.id.Icon);

                counter = new CountDownTimer(time_left, 1000) {

                    @SuppressLint("SetTextI18n")
                    public void onTick(long millisUntilFinished) {
                        long Hours = millisUntilFinished / (60 * 60 * 1000) % 24;
                        long Minutes = millisUntilFinished / (60 * 1000) % 60;
                        long Seconds = millisUntilFinished / 1000 % 60;

                        time.setText(Hours + " : " + Minutes + " : " + Seconds);
                        time.setTextColor(getResources().getColor(R.color.gray));
                        TimerFragment.setTleft(millisUntilFinished);

                        icon.setBackground(getDrawable(R.drawable.timer_ic));

                        if(MainActivity.getDark()){
                            time.setTextColor(getResources().getColor(R.color.gray));
                        }
                    }

                    public void onFinish() {
                        time.setText("");
                        icon.setBackground(null);
                    }

                }.start();
            }

        }else{
            setTheme(R.style.Theme_AirConditionerApp_MyCustomTheme);
            if(getIntensity() != null && getPower()) {
                l.setVisibility(View.VISIBLE);
                if(getTime() != null){
                    l2.setVisibility(View.VISIBLE);
                }

                temperature.setText(String.valueOf(getTemp()) + getResources().getString(R.string.celcius));
                if (getStatus().equals("heat")) {
                    s.setBackground(getDrawable(R.drawable.heat_ic));
                } else if (getStatus().equals("cold")) {
                    s.setBackground(getDrawable(R.drawable.cold_ic));
                } else if (getStatus().equals("humid")) {
                    s.setBackground(getDrawable(R.drawable.humid_ic));
                } else if (getStatus().equals("air")) {
                    s.setBackground(getDrawable(R.drawable.air_ic));
                }

                if (getIntensity().equals("high")) {
                    inten.setText("Υ");
                } else if (getIntensity().equals("medium")) {
                    inten.setText("M");
                } else if (getIntensity().equals("low")) {
                    inten.setText("Χ");
                } else if (getIntensity().equals("auto")) {
                    inten.setText("Α");
                }
            }

            if(getTime() != null && getPower()){
                l2.setVisibility(View.VISIBLE);
                TextView time = (TextView) findViewById(R.id.time);
                TextView icon = (TextView) findViewById(R.id.Icon);

                icon.setBackground(getDrawable(R.drawable.timer_ic));
                time.setText(MainActivity.getTime());
            }

            if(time_left != 0 && getPower()){
                l2.setVisibility(View.VISIBLE);
                TextView time = (TextView) findViewById(R.id.time);
                TextView icon = (TextView) findViewById(R.id.Icon);

                counter = new CountDownTimer(time_left, 1000) {

                    @SuppressLint("SetTextI18n")
                    public void onTick(long millisUntilFinished) {
                        long Hours = millisUntilFinished / (60 * 60 * 1000) % 24;
                        long Minutes = millisUntilFinished / (60 * 1000) % 60;
                        long Seconds = millisUntilFinished / 1000 % 60;

                        time.setText(Hours + " : " + Minutes + " : " + Seconds);
                        TimerFragment.setTleft(millisUntilFinished);

                        icon.setBackground(getDrawable(R.drawable.timer_ic));

                        if(MainActivity.getDark()){
                            time.setTextColor(getResources().getColor(R.color.gray));
                        }
                    }

                    public void onFinish() {
                        time.setText("");
                        icon.setBackground(null);
                    }

                }.start();
            }
        }

        if(getPower()){
            sw.setChecked(true);
        }else sw.setChecked(false);

        if(!sw.isChecked()) {
            t.setText("Εκκίνηση λειτουργίας");
            sw.setBackground(getDrawable(R.drawable.power_off));
        }else{
            t.setText("Τερματισμός λειτουργίας");
            sw.setBackground(getDrawable(R.drawable.power_on));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"ResourceAsColor", "SetTextI18n", "ResourceType", "UseCompatLoadingForDrawables","WrongViewCast", "CutPasteId"})
    @Override
    public void onStart() {

        super.onStart();
        TextView t = findViewById(R.id.textOnOff);
        TextView icon = (TextView) findViewById(R.id.Icon);
        Button b = findViewById(R.id.Cancel);
        LinearLayout l = findViewById(R.id.linearLayoutInfo);
        LinearLayout l2 = findViewById(R.id.linearLayoutTimer);

        if(getTime() != null){
            TimerFragmentAlt.setAlt(true);
        }
        if(TimerFragmentAlt.getAlt()){
            if(counter != null){
                counter.cancel();
            }
            if(counter != null) {
                TimerFragment.getCounter().cancel();
            }
        }

        sw.setOnClickListener(new View.OnClickListener(){
            @SuppressLint("UseCompatLoadingForDrawables")
            @Override
            public void onClick(View view) {

                if(!sw.isChecked()) {

                    t.setText("Εκκίνηση λειτουργίας");
                    sw.setBackground(getDrawable(R.drawable.power_off));
                    icon.setBackground(null);
                    setPower(false);
                    setTime(null);

                    l.setVisibility(View.INVISIBLE);
                    l2.setVisibility(View.INVISIBLE);

                    if(getCounter() != null){
                        getCounter().cancel();
                    }
                    if(TimerFragment.getCounter() != null){
                        TimerFragment.getCounter().cancel();
                        TimerFragment.setTleft(0);
                    }

                    if(MainActivity.getHelp()) {
                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.pop_up_message), R.string.main_off_pop_up, Snackbar.LENGTH_LONG);
                        mySnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                        final View v = mySnackbar.getView();
                        TextView tv = (TextView) v.findViewById(R.id.snackbar_text);
                        tv.setMaxLines(10);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, getFontSize());
                        if(MainActivity.getDark()) {
                            mySnackbar.setBackgroundTint(getResources().getColor(R.color.NavyBlueDark));
                            ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.NavyBlueDark));
                            tv.setBackground(cd);
                        }
                        mySnackbar.setText(tv.getText());
                        mySnackbar.show();
                    }

                }else{
                    TextView temperature = (TextView) findViewById(R.id.Temp);
                    @SuppressLint("ResourceType") TextView temperatureUnit = (TextView) findViewById(R.string.celcius);
                    TextView status = (TextView) findViewById(R.id.Status_Type);
                    TextView intensity = (TextView) findViewById(R.id.intensity);

                    t.setText("Τερματισμός λειτουργίας");
                    sw.setBackground(getDrawable(R.drawable.power_on));
                    setPower(true);
                    l.setVisibility(View.VISIBLE);

                    System.out.println(l.getVisibility() + "mphka");
                    if(b != null){
                        b.setVisibility(View.VISIBLE);
                    }
                    System.out.println(getStatus() + getTemp() + getIntensity());
                    if (getStatus() == null) {
                        status.setBackground(getResources().getDrawable(R.drawable.cold_ic));
                        temperature.setText(getResources().getString(R.integer.default_temp_cold) + getResources().getString(R.string.celcius));
                        setTemp(26);
                        setStatus("cold");

                        intensity.setText("Α");
                        MainActivity.setIntensity("auto");

                    }else if((getStatus().equals("cold") && getIntensity().equals("auto") && getTemp() == 26)){
                        System.out.println("why not");
                        status.setBackground(getResources().getDrawable(R.drawable.cold_ic));
                        temperature.setText(getResources().getString(R.integer.default_temp_cold) + getResources().getString(R.string.celcius));
                        intensity.setText("Α");
                    }

                    if(getDark()){
                        temperature.setTextColor(getResources().getColor(R.color.gray));
                        status.setTextColor(getResources().getColor(R.color.gray));
                        intensity.setTextColor(getResources().getColor(R.color.gray));
                    }

                    if(MainActivity.getHelp()) {
                        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.pop_up_message), R.string.main_on_pop_up, Snackbar.LENGTH_LONG);
                        mySnackbar.setAnimationMode(Snackbar.ANIMATION_MODE_SLIDE);
                        final View v = mySnackbar.getView();
                        TextView tv = (TextView) v.findViewById(R.id.snackbar_text);
                        tv.setMaxLines(10);
                        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, getFontSize());
                        if(MainActivity.getDark()) {
                            mySnackbar.setBackgroundTint(getResources().getColor(R.color.NavyBlueDark));
                            ColorDrawable cd = new ColorDrawable(getResources().getColor(R.color.NavyBlueDark));
                            tv.setBackground(cd);
                        }
                        mySnackbar.setText(tv.getText());
                        mySnackbar.show();
                    }

                }
            }
        });
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        m = menu;
        getMenuInflater().inflate(R.menu.menu_main, menu);
        if(getIntent().getExtras()!=null) {
            if(mode == R.id.Dark_mode){
                getM().findItem(mode).setChecked(true);
            }else{
                getM().findItem(R.id.Dark_mode).setChecked(false);
            }

            if(getHelp()){
                getM().findItem(R.id.HelpingMess).setChecked(true);
            }else getM().findItem(R.id.HelpingMess).setChecked(false);

            if (message != R.id.defaultSize && !getM().findItem(message).isChecked()) {
                getM().findItem(message).setChecked(true);
                getM().findItem(R.id.defaultSize).setChecked(false);
            }else if(message != R.id.defaultSize && getM().findItem(message).isChecked()){
                getM().findItem(R.id.message).setChecked(false);
                getM().findItem(R.id.defaultSize).setChecked(true);
            }else if(message != R.id.defaultSize){
                getM().findItem(R.id.defaultSize).setChecked(true);
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint({"NonConstantResourceId", "ResourceType"})
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        id = item.getItemId();

        switch (id){
            case android.R.id.home:
                toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // If condition returns the current fragment
                        if(getSupportFragmentManager().getFragments().get(0).isMenuVisible()) {
                            TextView temperature = (TextView) findViewById(R.id.Temp);
                            TextView s = (TextView) findViewById(R.id.Status_Type);
                            TextView inten = (TextView) findViewById(R.id.intensity);

                            String temp = (String) temperature.getText();
                            temp = temp.replace("°C", "");

                            if(!temp.equals(String.valueOf(getTemp()))){
                                setTemp(Integer.parseInt(temp));
                            }
//                            else if(!modeCheck.equals(String.valueOf(getStatus()))){
//                                setStatus(modeCheck);
//                            }

                            NavHostFragment.findNavController(getSupportFragmentManager().getFragments().get(0))
                                    .navigate(R.id.action_Home_Button);
                        }
                    }
                });
                return true;

            case R.id.action_settings:
                return true;

            case R.id.Dark_mode:
                if(item.isChecked()){
                    item.setChecked(false);
                    setDark(false);
                    Toast.makeText(getApplicationContext(),"Απενεργοποιήθηκε το σκούρο θέμα",Toast.LENGTH_SHORT).show();
                    refreshView();
                }else {
                    item.setChecked(true);
                    setDark(true);
                    Toast.makeText(getApplicationContext(),"Επιλέχθηκε το σκούρο θέμα",Toast.LENGTH_SHORT).show();
                    refreshView();
                }return true;

            case R.id.HelpingMess:
                if(item.isChecked()){
                    setHelp(false);
                    item.setChecked(false);
                    Toast.makeText(getApplicationContext(),"Απενεργοποιήθηκαν τα βοηθητικά μηνύματα",Toast.LENGTH_SHORT).show();
                }else {
                    item.setChecked(true);
                    setHelp(true);
                    Toast.makeText(getApplicationContext(),"Επιλέχθηκαν τα βοηθητικά μηνύματα",Toast.LENGTH_SHORT).show();
                }return true;

            case R.id.defaultSize:
                if(item.isChecked()){
                    item.setChecked(true);
                    Toast.makeText(getApplicationContext(),"Δεν γίνεται να απενεργοποιηθεί η προδιαγεγραμμένη γραμματοσειρά",Toast.LENGTH_SHORT).show();
                }else {
                    item.setChecked(true);
                    getM().findItem(R.id.mediumSize).setChecked(false);
                    getM().findItem(R.id.largeSize).setChecked(false);
                    setFontSize(22);
                    Toast.makeText(getApplicationContext(),"Επιλέχθηκε η προδιαγεγραμμένη γραμματοσειρά",Toast.LENGTH_SHORT).show();
                    refreshView();
                } return true;

            case R.id.mediumSize:
                if(item.isChecked()){
                    item.setChecked(false);
                    getM().findItem(R.id.defaultSize).setChecked(true);
                    setFontSize(22);
                    Toast.makeText(getApplicationContext(),"Απενεργοποιήθηκε η μεσαίου μεγέθους γραμματοσειρά",Toast.LENGTH_SHORT).show();
                    refreshView();
                }else {
                    item.setChecked(true);
                    getM().findItem(R.id.defaultSize).setChecked(false);
                    getM().findItem(R.id.largeSize).setChecked(false);
                    setFontSize(28);
                    Toast.makeText(getApplicationContext(),"Επιλέχθηκε η μεσαίου μεγέθους γραμματοσειρά",Toast.LENGTH_SHORT).show();
                    refreshView();
                }return true;

            case R.id.largeSize:
                if(item.isChecked()){
                    item.setChecked(false);
                    getM().findItem(R.id.defaultSize).setChecked(true);
                    setFontSize(22);
                    Toast.makeText(getApplicationContext(),"Απενεργοποιήθηκε η μεγάλου μεγέθους γραμματοσειρά",Toast.LENGTH_SHORT).show();
                    refreshView();
                }else {
                    item.setChecked(true);
                    getM().findItem(R.id.defaultSize).setChecked(false);
                    getM().findItem(R.id.mediumSize).setChecked(false);
                    setFontSize(34);
                    Toast.makeText(getApplicationContext(),"Επιλέχθηκε η μεγάλου μεγέθους γραμματοσειρά",Toast.LENGTH_SHORT).show();
                    refreshView();
                }return true;
        }

        return super.onOptionsItemSelected(item);
    }

}