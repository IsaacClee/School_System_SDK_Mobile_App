package com.example.c196ilee23.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.c196ilee23.DAO.PartDAO;
import com.example.c196ilee23.DAO.ProductDAO;
import com.example.c196ilee23.Entity.Part;
import com.example.c196ilee23.Entity.Product;

@Database(entities ={Part.class, Product.class}, version = 1, exportSchema = false)
public abstract class BicycleDatabaseBuilder extends RoomDatabase {
    public abstract ProductDAO productDAO();
    public abstract PartDAO partDAO();

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