package com.example.c196ilee23.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessments")
public class Assessment {
    @PrimaryKey(autoGenerate = true)
    private int assessmentID;
    private String assessmentTitle;
    private String type;
    private String startDate;
    private String endDate;
    private String courseTitle;

    public Assessment(int assessmentID, String assessmentTitle, String type, String startDate, String endDate, String courseTitle) {
        this.assessmentID = assessmentID;
        this.assessmentTitle = assessmentTitle;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.courseTitle = courseTitle;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentID=" + assessmentID +
                ", assessmentTitle='" + assessmentTitle + '\'' +
                ", type='" + type + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", courseTitle='" + courseTitle + '\'' +
                '}';
    }

    public int getAssessmentID() {
        return assessmentID;
    }

    public String getAssessmentTitle() {
        return assessmentTitle;
    }

    public String getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setAssessmentID(int assessmentID) {
        this.assessmentID = assessmentID;
    }

    public void setAssessmentTitle(String assessmentTitle) {
        this.assessmentTitle = assessmentTitle;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}
