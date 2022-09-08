package org.doronco.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView titleD;
    LinearLayout tasks ;
    String title = "" ;
    boolean validate ;
    String[] tasksList = new String[]{
            "Learn Android basics",
            "Learn Android advanced",
            "Learn React Native",
            "Learn Flutter"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        if(intent!=null) {
            validate = intent.getBooleanExtra("validate", false);
            title = intent.getStringExtra("title");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        tasks = findViewById(R.id.taskList);
        titleD = findViewById(R.id.title);
        if(tasks.getChildCount() == 0)
            for(int i=0;i<tasksList.length;i++){
                Button button = new Button(this);
                button.setText(tasksList[i]);
                button.setWidth(tasks.getWidth());
                button.setOnClickListener(this);
                if(title!=null && title.equals(tasksList[i])){
                    button.setPaintFlags(button.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                tasks.addView(button);
            }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        Intent intent = new Intent(this,TaskDetails.class);
        intent.putExtra("title",button.getText());
        startActivity(intent);
    }
}