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
import com.example.c196ilee23.Entity.Course;
import com.example.c196ilee23.Entity.Term;
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
    EditText editTermTitle;
    String title;
    Integer startDate;
    Integer endDate;
    String status;
    String courseInstructor;
    String instructorPhone;
    String instructorEmail;
    String termTitle;
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
        editTermTitle = findViewById(R.id.editTermTitle);
        editCourseNotes = findViewById(R.id.editCourseNotes);
        courseID = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        startDate = getIntent().getIntExtra("startDate", -1);
        endDate = getIntent().getIntExtra("endDate", -1);
        status = getIntent().getStringExtra("status");
        courseInstructor = getIntent().getStringExtra("instructor");
        instructorPhone = getIntent().getStringExtra("phone");
        instructorEmail = getIntent().getStringExtra("email");
        termTitle = getIntent().getStringExtra("termTitle");
        courseNotes = getIntent().getStringExtra("notes");
        editTitle.setText(title);
        editStartDate.setText(Integer.toString(startDate));
        editEndDate.setText(Integer.toString(endDate));
        editStatus.setText(status);
        editCourseInstructor.setText(courseInstructor);
        editInstructorPhone.setText(instructorPhone);
        editInstructorEmail.setText(instructorEmail);
        editTermTitle.setText(termTitle);
        editCourseNotes.setText(courseNotes);
        repository = new Repository(getApplication());
        // Populate Assessment List
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recyclerview5);
        Repository repo = new Repository(getApplication());
        List<Assessment> assessments = repo.getAllAssessments();
        List<Assessment> filteredAssessments = new ArrayList<>();
        for(Assessment eachAssessment : assessments){
            if(eachAssessment.getCourseTitle().equals(title)){
                filteredAssessments.add(eachAssessment);
            }
        }
        final AssessmentAdapter adapter = new AssessmentAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(filteredAssessments);
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
        Course course;
        if (courseID == -1){
            int newId = repository.getAllCourse().get(repository.getAllCourse().size() - 1).getCourseID() + 1;
            course = new Course(newId,
                    editTitle.getText().toString(),
                    Integer.parseInt(editStartDate.getText().toString()),
                    Integer.parseInt(editEndDate.getText().toString()),
                    editStatus.getText().toString(),
                    editCourseInstructor.getText().toString(),
                    editInstructorPhone.getText().toString(),
                    editInstructorEmail.getText().toString(),
                    editCourseNotes.getText().toString(),
                    editTermTitle.getText().toString());
            repository.insert(course);
            setContentView(R.layout.activity_course_list);
            RecyclerView recyclerView = findViewById(R.id.recyclerview4);
            Repository repo = new Repository(getApplication());
            List<Course> courses = repo.getAllCourse();
            final CourseAdapter adapter = new CourseAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setCourses(courses);

        } else {
            course = new Course(courseID,
                    editTitle.getText().toString(),
                    Integer.parseInt(editStartDate.getText().toString()),
                    Integer.parseInt(editEndDate.getText().toString()),
                    editStatus.getText().toString(),
                    editCourseInstructor.getText().toString(),
                    editInstructorPhone.getText().toString(),
                    editInstructorEmail.getText().toString(),
                    editCourseNotes.getText().toString(),
                    editTermTitle.getText().toString());
            repository.insert(course);
            setContentView(R.layout.activity_course_list);
            RecyclerView recyclerView = findViewById(R.id.recyclerview4);
            Repository repo = new Repository(getApplication());
            List<Course> courses = repo.getAllCourse();
            final CourseAdapter adapter = new CourseAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setCourses(courses);
        }
    }

    public void deleteCourse(View view) {
        Course course;
        course = new Course(courseID,
                editTitle.getText().toString(),
                Integer.parseInt(editStartDate.getText().toString()),
                Integer.parseInt(editEndDate.getText().toString()),
                editStatus.getText().toString(),
                editCourseInstructor.getText().toString(),
                editInstructorPhone.getText().toString(),
                editInstructorEmail.getText().toString(),
                editCourseNotes.getText().toString(),
                editTermTitle.getText().toString());
        repository.delete(course);
        setContentView(R.layout.activity_course_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerview4);
        Repository repo = new Repository(getApplication());
        List<Course> courses = repo.getAllCourse();
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);
    }

    public void goToAllAssessment(View view) {
        Intent intent=new Intent(CourseDetails.this, AssessmentList.class);
        startActivity(intent);
    }

    public void goToAssessmentDetails(View view) {
        Intent intent=new Intent(CourseDetails.this, AssessmentDetails.class);
        startActivity(intent);
    }
}
