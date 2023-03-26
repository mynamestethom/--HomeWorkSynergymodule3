package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text;
    private String sMove;
    private float  x;
    private float y;
    private final  float xCat = 500;
    private final  float yCat = 500;
    private final  float deltaCat = 50;
    TextView text_coordinates;
    ImageView cat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        text_coordinates = findViewById(R.id.text_coordinates);
        cat = findViewById(R.id.image_cat);
        text_coordinates.setOnTouchListener(listener);


    }

    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            x = motionEvent.getX();
            y = motionEvent.getY();
            switch (motionEvent.getAction()){
                case MotionEvent.ACTION_MOVE:
                    sMove = "Движение: координата x: "+x+", координата y: "+y;
                    if( x < (deltaCat+xCat) && x > (xCat-deltaCat) && y<(deltaCat+yCat) && y>(yCat-deltaCat)) {
                        cat.setImageResource(R.drawable.cat);
                        Toast.makeText(getApplicationContext(), "Кот найден", Toast.LENGTH_LONG).show();
                    }
                    break;
            }
            text_coordinates.setText(sMove);
            return true;
        }
    };
}