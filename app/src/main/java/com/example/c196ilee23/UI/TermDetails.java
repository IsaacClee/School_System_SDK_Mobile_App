package com.example.c196ilee23.UI;

import android.app.DatePickerDialog;
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
import com.example.c196ilee23.Entity.Course;
import com.example.c196ilee23.Entity.Term;
import com.example.c196ilee23.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TermDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    String title;
    String startDate;
    String endDate;
    int termID;
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
        setContentView(R.layout.activity_term_detail);
        // Populate Term information
        editTitle = findViewById(R.id.editTermTitle);
        editStartDate = findViewById(R.id.editTermStartDate);
        editEndDate = findViewById(R.id.editTermEndDate);
        termID = getIntent().getIntExtra("id", -1);
        title = getIntent().getStringExtra("title");
        startDate = getIntent().getStringExtra("startDate");
        endDate = getIntent().getStringExtra("endDate");
        editTitle.setText(title);
        editStartDate.setText((startDate));
        editEndDate.setText(endDate);
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
                        TermDetails.this,
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
                        TermDetails.this,
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

        // Populate Course List
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recyclerview2);
        Repository repo = new Repository(getApplication());
        List<Course> courses = repo.getAllCourse();
        List<Course> filteredCourses = new ArrayList<>();
        for(Course eachCourse : courses){
            if(eachCourse.getTermTitle().equals(title)){
                filteredCourses.add(eachCourse);
            }
        }
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(filteredCourses);
    }

    private void updateLabelStart() {
        editStartDate.setText(sdf.format(myCalenderStart.getTime()));
    }

    private void updateLabelEnd() {
        editEndDate.setText(sdf.format(myCalenderEnd.getTime()));
    }


    public void saveButton(View view) {
        Term term;
        if (termID == -1) {
            int newId = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() + 1;
            term = new Term(newId,
                    editTitle.getText().toString(),
                    editStartDate.getText().toString(),
                    editEndDate.getText().toString());
            repository.insert(term);
            setContentView(R.layout.activity_term_list);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            RecyclerView recyclerView = findViewById(R.id.recyclerview3);
            Repository repo = new Repository(getApplication());
            List<Term> terms = repo.getAllTerms();
            final TermAdapter adapter = new TermAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setTerms(terms);
        } else {
            term = new Term(termID,
                    editTitle.getText().toString(),
                    editStartDate.getText().toString(),
                    editEndDate.getText().toString());
            repository.update(term);
            setContentView(R.layout.activity_term_list);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            RecyclerView recyclerView = findViewById(R.id.recyclerview3);
            Repository repo = new Repository(getApplication());
            List<Term> terms = repo.getAllTerms();
            final TermAdapter adapter = new TermAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter.setTerms(terms);
        }

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_termdetails, menu);
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

    public void goToCourseDetail(View view) {
        Intent intent=new Intent(TermDetails.this, CourseDetails.class);
        startActivity(intent);
    }

    public void deleteTerm(View view) {
        Term term;
        term = new Term(termID,
                editTitle.getText().toString(),
                editStartDate.getText().toString(),
                editEndDate.getText().toString());
        repository.delete(term);
        setContentView(R.layout.activity_term_list);
        RecyclerView recyclerView = findViewById(R.id.recyclerview3);
        Repository repo = new Repository(getApplication());
        List<Term> terms = repo.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
    }

    public void goToAllCourses(View view) {
        Intent intent=new Intent(TermDetails.this, CourseList.class);
        startActivity(intent);
    }
}
