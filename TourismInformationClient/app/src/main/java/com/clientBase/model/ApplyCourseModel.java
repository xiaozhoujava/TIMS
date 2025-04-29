package com.clientBase.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/8/31.
 */

public class ApplyCourseModel implements Serializable {




    private String choiceTime;
    private int choiceId;
    private int choiceCourseId;
    private int courseUserId;
    private String courseTime;
    private String courseInfor;
    private String courseUserName;
    private int courseId;
    private String courseType;
    private int choiceStuId;
    private String courseState;
    private String courseName;

    public String getChoiceTime() {
        return choiceTime;
    }

    public void setChoiceTime(String choiceTime) {
        this.choiceTime = choiceTime;
    }

    public int getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(int choiceId) {
        this.choiceId = choiceId;
    }

    public int getChoiceCourseId() {
        return choiceCourseId;
    }

    public void setChoiceCourseId(int choiceCourseId) {
        this.choiceCourseId = choiceCourseId;
    }

    public int getCourseUserId() {
        return courseUserId;
    }

    public void setCourseUserId(int courseUserId) {
        this.courseUserId = courseUserId;
    }

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

    public int getChoiceStuId() {
        return choiceStuId;
    }

    public void setChoiceStuId(int choiceStuId) {
        this.choiceStuId = choiceStuId;
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
