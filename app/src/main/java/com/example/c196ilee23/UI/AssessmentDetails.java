package com.example.c196ilee23.UI;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.c196ilee23.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


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
    DatePickerDialog.OnDateSetListener startDateListener;
    DatePickerDialog.OnDateSetListener endDateListener;
    final Calendar myCalenderStart = Calendar.getInstance();
    final Calendar myCalenderEnd= Calendar.getInstance();
    String myFormat;
    SimpleDateFormat sdf;

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
                        AssessmentDetails.this,
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
                        AssessmentDetails.this,
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
            case R.id.notifyStartDate2:
                String startDateFromScreen = editStartDate.getText().toString();
                Date myStartDate = null;
                try {
                    myStartDate = sdf.parse(startDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myStartDate.getTime();
                Intent startIntent = new Intent( AssessmentDetails.this, DateReceiver.class );
                startIntent.putExtra("courseStartNotification", "Alert: " +  editTitle.getText().toString() + " starts today! " + "Type: " + editType.getText().toString());
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetails.this, MainActivity.numAlert++ , startIntent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.notifyEndDate2:
                String endDateFromScreen = editEndDate.getText().toString();
                Date myEndDate = null;
                try {
                    myEndDate = sdf.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger2 = myEndDate.getTime();
                Intent endIntent = new Intent( AssessmentDetails.this, DateReceiver.class );
                endIntent.putExtra("courseStartNotification", "Alert: " +  editTitle.getText().toString() + " ends today! " + "Type: " + editType.getText().toString());
                PendingIntent sender2 = PendingIntent.getBroadcast(AssessmentDetails.this, MainActivity.numAlert++ , endIntent, 0);
                AlarmManager alarmManager2 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager2.set(AlarmManager.RTC_WAKEUP, trigger2, sender2);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private void updateLabelStart() {
        editStartDate.setText(sdf.format(myCalenderStart.getTime()));
    }

    private void updateLabelEnd() {
        editEndDate.setText(sdf.format(myCalenderEnd.getTime()));
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
