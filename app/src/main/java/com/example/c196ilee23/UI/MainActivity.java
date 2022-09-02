package com.example.c196ilee23.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.c196ilee23.DataBase.Repository;
import com.example.c196ilee23.Entity.Assessment;
import com.example.c196ilee23.Entity.Course;
import com.example.c196ilee23.Entity.Part;
import com.example.c196ilee23.Entity.Product;
import com.example.c196ilee23.Entity.Term;
import com.example.c196ilee23.R;

import java.util.Calendar;

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
        Term term = new Term(1, "Term 1", "08/18/22", "05/10/23");
        Term term2 = new Term(2, "Term 2", "08/18/22", "05/10/23");
        Term term3 = new Term(3, "Term 3", "08/18/22", "05/10/23");
        Term term4 = new Term(4, "Term 4", "08/18/22", "05/10/23");
        repo.insert(term);
        repo.insert(term2);
        repo.insert(term3);
        repo.insert(term4);
        Course course = new Course(
                1,
                "English",
                "08/18/2022",
                "05/10/2023",
                "Enrolled",
                "Dr. Martin Jones",
                "982-765-4839",
                "skeoxdr@gmail.com",
                "N/A",
                "Term 1" );
        Course course2 = new Course(
                2,
                "History",
                "08/18/22",
                "05/10/23",
                "Enrolled",
                "Dr. Emma Johnson",
                "982-765-4839",
                "johnsomE@gmail.com",
                "N/A",
                "Term 1" );
        Course course3 = new Course(
                3,
                "Math",
                "08/18/22",
                "05/10/23",
                "Plan to Take",
                "Dr. Sam Sampson",
                "982-765-4839",
                "lasampsondr@gmail.com",
                "N/A",
                "Term 2" );
        repo.insert(course);
        repo.insert(course2);
        repo.insert(course3);
        Assessment assessment = new Assessment(1,"Math - Task 1", "performance", "08/18/22", "05/10/23", "Math");
        Assessment assessment2 = new Assessment(2,"History - Task 1", "Objective", "08/18/22", "05/10/23", "History");
        repo.insert(assessment);
        repo.insert(assessment2);
        Product product = new Product(2, "Bike", 200.00);
        repo.insert(product);
        Part part = new Part(2,"Brake", 35.00, 2);
        repo.insert(part);
        Log.i( "EnterHere: ", String.valueOf(MainActivity.getTimeInMillis(8,8,2022)));
    }

    public static long getTimeInMillis(int day, int month, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTimeInMillis();
    }



}