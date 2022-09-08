package org.doronco.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout tasks ;
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
    }

    @Override
    protected void onStart() {
        super.onStart();
        tasks = findViewById(R.id.taskList);
        if(tasks.getChildCount() == 0)
            for(int i=0;i<tasksList.length;i++){
                Button button = new Button(this);
                button.setText(tasksList[i]);
                button.setWidth(tasks.getWidth());
                button.setOnClickListener(this);
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