package com.apphouse.businessscheduler.data;

import android.content.Context;

import com.apphouse.businessscheduler.util.Util;
import com.apphouse.businessscheduler.vo.Schedule;
import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.util.ArrayList;
import java.util.HashMap;

import static com.apphouse.businessscheduler.data.DBHelper.dbHelper;

public class DataHelper {

    public static DataHelper dataHelper;
    private HashMap<Integer, HashMap<Integer,  ArrayList<Schedule>>> scheduleMapByMonth;
    private ArrayList<Schedule> schedulsForADay;

    public void init(Context context){
        dataHelper = this;
        new DBHelper(context);
        scheduleMapByMonth = new HashMap<Integer, HashMap<Integer, ArrayList<Schedule>>>();
        dbHelper.selectAllSchedule(scheduleMapByMonth);
    }

    public void insertTest() {

        Schedule schedule0 = new Schedule();
        schedule0.setScheduleName("test schedule1");
        schedule0.setColor("blue");
        schedule0.setTime("05:00 ~ 10:00");
        schedule0.setFromTime("5");
        schedule0.setToTime("6");
        schedule0.setDate("20180207");
        schedule0.setMemo("Hello");
        dbHelper.insertSchedule(schedule0);

        Schedule schedule = new Schedule();
        schedule.setScheduleName("test schedule2");
        schedule.setColor("blue");
        schedule.setTime("05:00 ~ 10:00");
        schedule.setFromTime("5");
        schedule.setToTime("8");
        schedule.setDate("20180208");
        schedule.setMemo("Hello");
        dbHelper.insertSchedule(schedule);

        Schedule schedule2 = new Schedule();
        schedule2.setScheduleName("test schedule5");
        schedule2.setColor("blue");
        schedule2.setTime("05:00 ~ 10:00");
        schedule2.setFromTime("3");
        schedule2.setToTime("9");
        schedule2.setDate("20180210");
        schedule2.setMemo("Hello");
        dbHelper.insertSchedule(schedule2);

    }

    public ArrayList<Schedule> getSchedulesForADay(Integer day, String selectedDate) {
        HashMap<Integer, ArrayList<Schedule>> schedulesForAMonth =
                getScheduleMapForAMonth(Integer.parseInt(Util.getYearMonthFromDate(selectedDate)));
        ArrayList<Schedule> schedulesForADay = schedulesForAMonth.get(day);
        return  schedulesForADay;
    }

    public HashMap<Integer, HashMap<Integer, ArrayList<Schedule>>> getScheduleMapByMonth() {
        return scheduleMapByMonth;
    }

    public void setScheduleMapByMonth(HashMap<Integer, HashMap<Integer, ArrayList<Schedule>>> scheduleMapByMonth) {
        this.scheduleMapByMonth = scheduleMapByMonth;
    }

    public HashMap<Integer, ArrayList<Schedule>> getScheduleMapForAMonth(int month){
        return scheduleMapByMonth.get(month);
    }

    public ArrayList<Schedule> getSchedulesForADay() {
        return schedulsForADay;
    }

    public void setSchedulsForADay(ArrayList<Schedule> schedulsForADay) {
        this.schedulsForADay = schedulsForADay;
    }
}
