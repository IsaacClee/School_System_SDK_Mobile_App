package com.example.c196ilee23.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.c196ilee23.DataBase.Repository;
import com.example.c196ilee23.Entity.Course;
import com.example.c196ilee23.Entity.Term;
import com.example.c196ilee23.R;

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
        editTitle = findViewById(R.id.editTermTitle);
        editStartDate = findViewById(R.id.editTermStartDate);
        editEndDate = findViewById(R.id.editTermEndDate);
        termID = getIntent().getIntExtra("id", 01);
        title = getIntent().getStringExtra("title");
        startDate = getIntent().getIntExtra("startDate", -1);
        endDate = getIntent().getIntExtra("endDate", -1);
        editTitle.setText(title);
        editStartDate.setText(Integer.toString(startDate));
        editEndDate.setText(Integer.toString(endDate));
        repository = new Repository(getApplication());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.recyclerview2);
        Repository repo = new Repository(getApplication());
        List<Course> courses = repo.getAllCourse();
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);
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
        } else {
            term = new Term(termID,
                    editTitle.getText().toString(),
                    Integer.parseInt(editStartDate.getText().toString()),
                    Integer.parseInt(editEndDate.getText().toString()));
            repository.update(term);
        }

    }

    public void goToCourseDetail(View view) {
    }

    public void deleteTerm(View view) {
        Term term;
        term = new Term(termID,
                editTitle.getText().toString(),
                Integer.parseInt(editStartDate.getText().toString()),
                Integer.parseInt(editEndDate.getText().toString()));
        repository.delete(term);
    }

}
