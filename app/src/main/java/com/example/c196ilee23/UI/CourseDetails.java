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
import com.example.c196ilee23.Entity.Course;
import com.example.c196ilee23.R;

import java.util.ArrayList;
import java.util.List;

public class CourseDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    EditText editStatus;
    EditText editCourseInstructor;
    EditText editInstructorPhone;
    EditText editInstructorEmail;
    EditText editCourseNotes;
    String title;
    Integer startDate;
    Integer endDate;
    String status;
    String courseInstructor;
    String instructorPhone;
    String instructorEmail;
    String courseNotes;
    int courseID;
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        // Populate Course Information
        editTitle = findViewById(R.id.editCourseTitle);
        editStartDate = findViewById(R.id.editCourseStartDate);
        editEndDate = findViewById(R.id.editCourseEndDate);
        editStatus = findViewById(R.id.editCourseStatus);
        editCourseInstructor = findViewById(R.id.editCourseInstructor);
        editInstructorPhone = findViewById(R.id.editInstructorPhone);
        editInstructorEmail = findViewById(R.id.editInstructorEmail);
        editCourseNotes = findViewById(R.id.editCourseNotes);
        courseID = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        startDate = getIntent().getIntExtra("startDate", -1);
        endDate = getIntent().getIntExtra("endDate", -1);
        status = getIntent().getStringExtra("status");
        courseInstructor = getIntent().getStringExtra("instructor");
        instructorPhone = getIntent().getStringExtra("phone");
        instructorEmail = getIntent().getStringExtra("email");
        courseNotes = getIntent().getStringExtra("notes");
        editTitle.setText(title);
        editStartDate.setText(Integer.toString(startDate));
        editEndDate.setText(Integer.toString(endDate));
        editStatus.setText(status);
        editCourseInstructor.setText(courseInstructor);
        editInstructorPhone.setText(instructorPhone);
        editInstructorEmail.setText(instructorEmail);
        editCourseNotes.setText(courseNotes);
        repository = new Repository(getApplication());
        // Populate Assessment List
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_coursedetails, menu);
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


    public void saveButton(View view) {
    }

    public void deleteCourse(View view) {
    }

    public void goToAllAssessment(View view) {
        Intent intent=new Intent(CourseDetails.this, AssessmentList.class);
        startActivity(intent);
    }

    public void goToAssessmentDetails(View view) {
    }
}
