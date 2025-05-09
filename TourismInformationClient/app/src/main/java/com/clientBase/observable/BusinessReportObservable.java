package com.clientBase.observable;

import java.util.Observable;

public class BusinessReportObservable extends Observable {

    //单例
    private static BusinessReportObservable instance = null;

    public static BusinessReportObservable getInstance() {

        if (null == instance) {
            instance = new BusinessReportObservable();
        }
        return instance;
    }

    //通知观察者更新数据
    public void notifyStepChange(String msg) {
        setChanged();//设置changeFlag
        notifyObservers(msg);//通知观察者
    }

}