package com.example.c196ilee23.DataBase;

import android.app.Application;

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

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private PartDAO mPartDAO;
    private ProductDAO mProductDAO;
    private List<Product> mAllProducts;
    private List<Part> mAllParts;
    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private AssessmentDAO mAssessmentDAO;
    private List<Term> mAllTerms;
    private List<Course> mAllCourses;
    private List<Assessment> mAllAssessments;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        BicycleDatabaseBuilder db=BicycleDatabaseBuilder.getDatabase(application);
        mPartDAO = db.partDAO();
        mProductDAO = db.productDAO();
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDAO();
    }

    // Products
    public List<Product> getAllProducts(){
        databaseExecutor.execute(()->{
            mAllProducts = mProductDAO.getAllProducts();
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllProducts;
    }
    public void insert(Product product){
        databaseExecutor.execute(()->{
            mProductDAO.insert(product);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Product product){
        databaseExecutor.execute(()->{
            mProductDAO.update(product);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Product product){
        databaseExecutor.execute(()->{
            mProductDAO.delete(product);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Parts
    public List<Part> getAllParts(){
        databaseExecutor.execute(()->{
            mAllParts = mPartDAO.getAllParts();
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllParts;
    }
    public void insert(Part part){
        databaseExecutor.execute(()->{
            mPartDAO.insert(part);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Part part){
        databaseExecutor.execute(()->{
            mPartDAO.update(part);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Part part){
        databaseExecutor.execute(()->{
            mPartDAO.delete(part);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Terms
    public List<Term> getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms = mTermDAO.getAllTerms();
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }
    public void insert(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.insert(term);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.update(term);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.delete(term);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }


    // Courses
    public List<Course> getAllCourse(){
        databaseExecutor.execute(()->{
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }
    public void insert(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.insert(course);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.update(course);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.delete(course);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Assessments
    public List<Assessment> getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }
    public void insert(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.insert(assessment);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.update(assessment);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.delete(assessment);
        });
        try{
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

}
