package com.example.homework31;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textonscreen;
    private TextView counttext;
    private Button countbutton;
    private int count = 0;
    private final static String KEY_COUNT = "key";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textonscreen = findViewById(R.id.textView);
        counttext = findViewById(R.id.textCount);
        countbutton = findViewById(R.id.button);
    }

    public void OnClick(View v){
        count++;
        counttext.setText(String.valueOf(count));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_COUNT, count);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count = savedInstanceState.getInt(KEY_COUNT);
        counttext.setText(Integer.toString(count));
    }
}