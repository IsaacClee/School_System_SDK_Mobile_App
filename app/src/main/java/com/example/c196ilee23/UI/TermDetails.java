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
import com.example.c196ilee23.Entity.Term;
import com.example.c196ilee23.R;

import java.util.ArrayList;
import java.util.List;

public class TermDetails extends AppCompatActivity {
    EditText editTitle;
    EditText editStartDate;
    EditText editEndDate;
    String title;
    Integer startDate;
    Integer endDate;
    int termID;
    Repository repository;
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
        startDate = getIntent().getIntExtra("startDate", -1);
        endDate = getIntent().getIntExtra("endDate", -1);
        editTitle.setText(title);
        editStartDate.setText(Integer.toString(startDate));
        editEndDate.setText(Integer.toString(endDate));
        repository = new Repository(getApplication());
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


    public void saveButton(View view) {
        Term term;
        if (termID == -1) {
            int newId = repository.getAllTerms().get(repository.getAllTerms().size() - 1).getTermId() + 1;
            term = new Term(newId,
                    editTitle.getText().toString(),
                    Integer.parseInt(editStartDate.getText().toString()),
                    Integer.parseInt(editEndDate.getText().toString()));
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
                    Integer.parseInt(editStartDate.getText().toString()),
                    Integer.parseInt(editEndDate.getText().toString()));
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
                Integer.parseInt(editStartDate.getText().toString()),
                Integer.parseInt(editEndDate.getText().toString()));
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
