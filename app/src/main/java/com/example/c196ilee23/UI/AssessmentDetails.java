package com.example.c196ilee23.UI;

import android.app.DatePickerDialog;
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
import java.util.Calendar;
import java.util.List;


public class AssessmentDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editType;
    EditText editStartDate;
    EditText editEndDate;
    EditText editCourseTitle;
    String title;
    String type;
    String startDate;
    String endDate;
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
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        courseTitle = getIntent().getStringExtra("courseTitle");
        editTitle.setText(title);
        editType.setText(type);
        editStartDate.setText(startDate);
        editEndDate.setText(endDate);
        editCourseTitle.setText(courseTitle);
        repository = new Repository(getApplication());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        Assessment assessment;
        assessment = new Assessment(assessmentId,
                editTitle.getText().toString(),
                editType.getText().toString(),
                editStartDate.getText().toString(),
                editEndDate.getText().toString(),
                editCourseTitle.getText().toString());
        repository.delete(assessment);
        setContentView(R.layout.activity_assessement_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerview6);
        Repository repo = new Repository(getApplication());
        List<Assessment> assessments = repo.getAllAssessments();
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);
        setContentView(R.layout.activity_course_detail);
    }

    public void saveButton(View view) {
        Assessment assessment;
        if (assessmentId == -1){
            int newId = repository.getAllAssessments().get(repository.getAllAssessments().size() - 1).getAssessmentID() + 1;
            assessment = new Assessment(newId,
                    editTitle.getText().toString(),
                    editType.getText().toString(),
                    editStartDate.getText().toString(),
                    editEndDate.getText().toString(),
                    editCourseTitle.getText().toString());
            repository.insert(assessment);
            setContentView(R.layout.activity_assessement_list);
            RecyclerView recyclerView = findViewById(R.id.recyclerview6);
            Repository repo = new Repository(getApplication());
            List<Assessment> assessments = repo.getAllAssessments();
            final AssessmentAdapter adapter = new AssessmentAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setAssessments(assessments);
        } else {
            assessment = new Assessment(assessmentId,
                    editTitle.getText().toString(),
                    editType.getText().toString(),
                    editStartDate.getText().toString(),
                    editEndDate.getText().toString(),
                    editCourseTitle.getText().toString());
            repository.insert(assessment);
            setContentView(R.layout.activity_assessement_list);
            RecyclerView recyclerView = findViewById(R.id.recyclerview6);
            Repository repo = new Repository(getApplication());
            List<Assessment> assessments = repo.getAllAssessments();
            final AssessmentAdapter adapter = new AssessmentAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setAssessments(assessments);
        }


    }
}
