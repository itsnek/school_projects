package com.example.musico;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.musico.HelperClasses.recItem;

import MyPackage.*;

public class AsyncTaskActivity extends AppCompatActivity {

	private Button btn;
	private EditText editTxt;
	private String artist, song;
	private Switch swtch;
	boolean online;
	Consumer cons = new Consumer();
	recItem newSong;
	Intent inte;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_async_task);

		// Get the Intent that started this activity and extract the string
		Intent intent = getIntent();
		artist = intent.getStringExtra(ArtistSearchActivity.EXTRA_MESSAGE);
		TextView textView = findViewById(R.id.artist);
		textView.setText(artist);

		editTxt = findViewById(R.id.plain_text_input);
		swtch = findViewById(R.id.swtch);
		swtch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					online = true;
				} else {
					online = false;
				}
			}
		});
		btn = findViewById(R.id.searchBtn);
		btn.setOnClickListener(new View.OnClickListener() {

			@RequiresApi(api = Build.VERSION_CODES.O)
			@Override
			public void onClick(View v) {
				song = editTxt.getText().toString();
				Intent intent = getIntent();
				if(song.isEmpty()){
					Toast.makeText(AsyncTaskActivity.this, "Please Enter a Song", Toast.LENGTH_SHORT).show();
				}else {
					while(!Communicator.getEnd()){}

					if(online){
						//ONLINE MODE
						Communicator comm = new Communicator(cons, getApplicationContext(),4,artist,song);
						comm.start();

						inte = new Intent(AsyncTaskActivity.this, MusicPlayerActivity.class);
						inte.putExtra("file's name", song + ".mp3");
						inte.putExtra("online", true);
						startActivity(inte);

					}else{
						//OFFLINE MODE
						Communicator comm = new Communicator(cons, getApplicationContext(),3,artist,song);
						comm.start();

						Intent inte = new Intent(AsyncTaskActivity.this, LibraryActivity.class);
						newSong = new recItem(R.drawable.ic_headset_black_24dp, R.drawable.ic_delete, artist, song);
						inte.putExtra("item", newSong);
						startActivity(inte);
					}
				}
			}
		});
	}

}
