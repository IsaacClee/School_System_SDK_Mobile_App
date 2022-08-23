package com.example.c196ilee23.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.c196ilee23.DataBase.Repository;
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
        repo.insert(term);
        repo.insert(term2);
        repo.insert(term3);
        Product product = new Product(2, "Bike", 200.00);
        repo.insert(product);
        Part part = new Part(2,"Brake", 35.00, 2);
        repo.insert(part);

    }

}