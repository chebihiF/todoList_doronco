package org.doronco.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class TaskDetails extends AppCompatActivity {


    Map<String,String[]> details = new HashMap<>();
    String title ;
    LinearLayout taskDetails;
    TextView tasktitle ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
        initData();
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
    }

    @Override
    protected void onStart() {
        super.onStart();
        taskDetails = findViewById(R.id.taskDetails);
        tasktitle = findViewById(R.id.taskTitle);
        tasktitle.setText(title);
        String[] detailsTitle = details.get(title);
        for(int i=0;i<detailsTitle.length;i++){
            LinearLayout layout = new LinearLayout(this);
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layout.setMinimumWidth(taskDetails.getWidth());
            CheckBox checkBox = new CheckBox(this);
            TextView textView = new TextView(this);
            textView.setText(detailsTitle[i]);
            textView.setTextSize(20);
            layout.addView(checkBox);
            layout.addView(textView);
            taskDetails.addView(layout);
        }
    }

    private void initData(){
        details.put("Learn Android basics",new String[]{
                "Introduction to activity",
                "Layout Manager",
                "RecyclerView",
                "SqlLite"
        });
        details.put("Learn React Native",new String[]{
                "Introduction to React",
                "State Management",
                "Http request",
                "locale storage"
        });
    }

    public void validate(View view) {
        for(int i=0;i<taskDetails.getChildCount();i++){
            LinearLayout layout = (LinearLayout) taskDetails.getChildAt(i);
            CheckBox checkBox = (CheckBox) layout.getChildAt(0);
            if(!checkBox.isChecked())
                return;
        }
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("validate",true);
        intent.putExtra("title",title);
        startActivity(intent);
    }
}