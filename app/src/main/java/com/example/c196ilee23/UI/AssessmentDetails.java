package com.example.c196ilee23.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196ilee23.DataBase.Repository;
import com.example.c196ilee23.Entity.Assessment;
import com.example.c196ilee23.R;

import java.util.ArrayList;
import java.util.List;


public class AssessmentDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editType;
    EditText editStartDate;
    EditText editEndDate;
    EditText editCourseTitle;
    String title;
    String type;
    Integer startDate;
    Integer endDate;
    String courseTitle;
    int assessmentId;
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        editTitle = findViewById(R.id.editAssessmentTitle);
        editType = findViewById(R.id.editAssessmentType);
        editStartDate = findViewById(R.id.editAssessmentStartDate);
        editEndDate = findViewById(R.id.editAssessmentEndDate);
        editCourseTitle = findViewById(R.id.editCourseTitle);
        assessmentId = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        type = getIntent().getStringExtra("type");
        startDate = getIntent().getIntExtra("startDate", -1);
        endDate = getIntent().getIntExtra("endDate", -1);
        courseTitle = getIntent().getStringExtra("courseTitle");
        editTitle.setText(title);
        editType.setText(type);
        editStartDate.setText(Integer.toString(startDate));
        editEndDate.setText(Integer.toString(endDate));
        editCourseTitle.setText(courseTitle);
        repository = new Repository(getApplication());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Log.d("here", String.valueOf(assessmentId));
        Log.d("here", String.valueOf(title));
        Log.d("here", String.valueOf(type));
        Log.d("here", String.valueOf(startDate));
        Log.d("here", String.valueOf(endDate));
        Log.d("here", String.valueOf(courseTitle));

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_assessmentdetails, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void deleteAssessment(View view) {
    }

    public void saveButton(View view) {
    }
}
