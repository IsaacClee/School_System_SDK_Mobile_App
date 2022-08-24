package com.example.c196ilee23.UI;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.c196ilee23.DataBase.Repository;
import com.example.c196ilee23.Entity.Term;
import com.example.c196ilee23.R;

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

}
