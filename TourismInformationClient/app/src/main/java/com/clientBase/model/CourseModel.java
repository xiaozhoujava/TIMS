package com.clientBase.model;

import java.io.Serializable;

public class CourseModel implements Serializable {


    private String courseTime;
    private String courseInfor;
    private boolean choiceState;
    private String courseUserName;
    private int courseId;
    private String courseType;
    private String courseState;
    private String courseName;


    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseInfor() {
        return courseInfor;
    }

    public void setCourseInfor(String courseInfor) {
        this.courseInfor = courseInfor;
    }

    public boolean isChoiceState() {
        return choiceState;
    }

    public void setChoiceState(boolean choiceState) {
        this.choiceState = choiceState;
    }

    public String getCourseUserName() {
        return courseUserName;
    }

    public void setCourseUserName(String courseUserName) {
        this.courseUserName = courseUserName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseState() {
        return courseState;
    }

    public void setCourseState(String courseState) {
        this.courseState = courseState;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
