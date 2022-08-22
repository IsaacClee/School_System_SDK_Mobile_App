package com.example.c196ilee23.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196ilee23.DAO.AssessmentDAO;
import com.example.c196ilee23.DAO.CourseDAO;
import com.example.c196ilee23.DAO.PartDAO;
import com.example.c196ilee23.DAO.ProductDAO;
import com.example.c196ilee23.DAO.TermDAO;
import com.example.c196ilee23.Entity.Assessment;
import com.example.c196ilee23.Entity.Course;
import com.example.c196ilee23.Entity.Part;
import com.example.c196ilee23.Entity.Product;
import com.example.c196ilee23.Entity.Term;

@Database(entities ={Part.class, Product.class, Term.class, Course.class, Assessment.class}, version = 2, exportSchema = false)
public abstract class BicycleDatabaseBuilder extends RoomDatabase {
    public abstract ProductDAO productDAO();
    public abstract PartDAO partDAO();
    public abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public abstract AssessmentDAO assessmentDAO();

    private static volatile BicycleDatabaseBuilder INSTANCE;

    static BicycleDatabaseBuilder getDatabase(final Context context) {
        if(INSTANCE==null) {
            synchronized (BicycleDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    BicycleDatabaseBuilder.class,
                                    "myBicycleDatabase")
                            .fallbackToDestructiveMigration()
                            .build();

                }
            }
        }
       return INSTANCE;
    }
}