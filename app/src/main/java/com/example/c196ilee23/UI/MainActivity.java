package com.example.c196ilee23.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196ilee23.DataBase.Repository;
import com.example.c196ilee23.Entity.Assessment;
import com.example.c196ilee23.Entity.Course;
import com.example.c196ilee23.Entity.Part;
import com.example.c196ilee23.Entity.Product;
import com.example.c196ilee23.Entity.Term;
import com.example.c196ilee23.R;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void EnterHere(View view){
        Intent intent= new Intent(MainActivity.this, TermList.class );
        startActivity(intent);
        Repository repo = new Repository(getApplication());
        Term term = new Term(1, "Term 1", 1661271686, 1661271686);
        Term term2 = new Term(2, "Term 2", 1661271686, 1661271686);
        Term term3 = new Term(3, "Term 3", 1661271686, 1661271686);
        Term term4 = new Term(4, "Term 4", 1661271686, 1661271686);
        repo.insert(term);
        repo.insert(term2);
        repo.insert(term3);
        repo.insert(term4);
        Course course = new Course(
                1,
                "English",
                1661271686,
                1661271686,
                "Enrolled",
                "Dr. Martin Jones",
                "982-765-4839",
                "skeoxdr@gmail.com",
                "N/A",
                "Term 1" );
        Course course2 = new Course(
                2,
                "History",
                1661271686,
                1661271686,
                "Enrolled",
                "Dr. Emma Johnson",
                "982-765-4839",
                "johnsomE@gmail.com",
                "N/A",
                "Term 1" );
        Course course3 = new Course(
                3,
                "Math",
                1661271686,
                1661271686,
                "Plan to Take",
                "Dr. Sam Sampson",
                "982-765-4839",
                "lasampsondr@gmail.com",
                "N/A",
                "Term 2" );
        repo.insert(course);
        repo.insert(course2);
        repo.insert(course3);
        Assessment assessment = new Assessment(1,"Math - Task 1", "performance", 1661271686, 1661271686, "Math");
        Assessment assessment2 = new Assessment(2,"History - Task 1", "Objective", 1661271686, 1661271686, "History");
        Assessment assessment3 = new Assessment(3,"English - Task 1", "performance", 1661271686, 1661271686, "English");
        Assessment assessment4 = new Assessment(4,"Math - Task 2", "Objective", 1661271686, 1661271686, "Math");
        repo.insert(assessment);
        repo.insert(assessment2);
        repo.insert(assessment3);
        repo.insert(assessment4);
        Product product = new Product(2, "Bike", 200.00);
        repo.insert(product);
        Part part = new Part(2,"Brake", 35.00, 2);
        repo.insert(part);

    }

}