package com.example.c196ilee23.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196ilee23.DataBase.Repository;
import com.example.c196ilee23.Entity.Assessment;
import com.example.c196ilee23.Entity.Course;
import com.example.c196ilee23.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    String startDate;
    String endDate;
    String status;
    String courseInstructor;
    String instructorPhone;
    String instructorEmail;
    String termTitle;
    String courseNotes;
    int courseID;
    Repository repository;
    DatePickerDialog.OnDateSetListener startDateListener;
    DatePickerDialog.OnDateSetListener endDateListener;
    final Calendar myCalenderStart = Calendar.getInstance();
    final Calendar myCalenderEnd= Calendar.getInstance();
    String myFormat;
    SimpleDateFormat sdf;

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
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        status = getIntent().getStringExtra("status");
        courseInstructor = getIntent().getStringExtra("instructor");
        instructorPhone = getIntent().getStringExtra("phone");
        instructorEmail = getIntent().getStringExtra("email");
        termTitle = getIntent().getStringExtra("termTitle");
        courseNotes = getIntent().getStringExtra("notes");
        editTitle.setText(title);
        editStartDate.setText(startDate);
        editEndDate.setText(endDate);
        editStatus.setText(status);
        editCourseInstructor.setText(courseInstructor);
        editInstructorPhone.setText(instructorPhone);
        editInstructorEmail.setText(instructorEmail);
        editTermTitle.setText(termTitle);
        editCourseNotes.setText(courseNotes);
        repository = new Repository(getApplication());
        // Handles Date Fields + DatePicker
        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        editStartDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Date date;
                String info = editStartDate.getText().toString();
                if(info.equals("")) info = "08/25/2022";
                try {
                    myCalenderStart.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(
                        CourseDetails.this,
                        startDateListener,
                        myCalenderStart.get(Calendar.YEAR),
                        myCalenderStart.get(Calendar.MONTH),
                        myCalenderStart.get(Calendar.DAY_OF_MONTH)
                ).show();

            }
        });
        editEndDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Date date;
                String info = editEndDate.getText().toString();
                if(info.equals("")) info = "08/25/2022";
                try {
                    myCalenderEnd.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(
                        CourseDetails.this,
                        endDateListener,
                        myCalenderStart.get(Calendar.YEAR),
                        myCalenderStart.get(Calendar.MONTH),
                        myCalenderStart.get(Calendar.DAY_OF_MONTH)
                ).show();

            }
        });

        startDateListener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalenderStart.set(Calendar.YEAR, year);
                myCalenderStart.set(Calendar.MONTH, monthOfYear);
                myCalenderStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        endDateListener = new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalenderEnd.set(Calendar.YEAR, year);
                myCalenderEnd.set(Calendar.MONTH, monthOfYear);
                myCalenderEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };

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

    private void updateLabelStart() {
        editStartDate.setText(sdf.format(myCalenderStart.getTime()));
    }

    private void updateLabelEnd() {
        editEndDate.setText(sdf.format(myCalenderEnd.getTime()));
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
            case R.id.share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,editCourseNotes.getText().toString());
                sendIntent.putExtra(Intent.EXTRA_TITLE, editTitle.getText().toString() + " Notes:");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notifyStartDate:
                String startDateFromScreen = editStartDate.getText().toString();
                Date myStartDate = null;
                try {
                    myStartDate = sdf.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myStartDate.getTime();
                Intent startIntent = new Intent( CourseDetails.this, DateReceiver.class );
                startIntent.putExtra("courseStartNotification", "Alert: " +  editTitle.getText().toString() + " starts today! " + "Instructor: " + editCourseInstructor.getText().toString());
                PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, MainActivity.numAlert++ , startIntent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notifyEndDate:
                String endDateFromScreen = editEndDate.getText().toString();
                Date myEndDate = null;
                try {
                    myEndDate = sdf.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger2 = myEndDate.getTime();
                Intent endIntent = new Intent( CourseDetails.this, DateReceiver.class );
                endIntent.putExtra("courseStartNotification", "Alert: " +  editTitle.getText().toString() + " ends today! " + "Instructor: " + editCourseInstructor.getText().toString());
                PendingIntent sender2 = PendingIntent.getBroadcast(CourseDetails.this, MainActivity.numAlert++ , endIntent, 0);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);
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
                    editStartDate.getText().toString(),
                    editEndDate.getText().toString(),
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
                    editStartDate.getText().toString(),
                    editEndDate.getText().toString(),
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
                editStartDate.getText().toString(),
                editEndDate.getText().toString(),
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
