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
    private String selectedData;

    public void init(Context context){
        dataHelper = this;
        new DBHelper(context);
        scheduleMapByMonth = new HashMap<Integer, HashMap<Integer, ArrayList<Schedule>>>();
        dbHelper.selectAllSchedule(scheduleMapByMonth);
    }

    public void insertTest() {
        Schedule schedule = new Schedule();
        schedule.setScheduleName("test schedule2");
        schedule.setColor("blue");
        schedule.setTime("05:00 ~ 10:00");
        schedule.setDate("20180208");
        schedule.setMemo("Hello");
        dbHelper.insertSchedule(schedule);

        Schedule schedule2 = new Schedule();
        schedule2.setScheduleName("test schedule5");
        schedule2.setColor("blue");
        schedule2.setTime("05:00 ~ 10:00");
        schedule2.setDate("20180210");
        schedule2.setMemo("Hello");
        dbHelper.insertSchedule(schedule2);

        Schedule schedule3 = new Schedule();
        schedule3.setScheduleName("real?!!!!");
        schedule3.setColor("blue");
        schedule3.setTime("05:00 ~ 10:00");
        schedule3.setDate("20180115");
        schedule3.setMemo("Hello");
        dbHelper.insertSchedule(schedule3);

        Schedule schedule4 = new Schedule();
        schedule4.setScheduleName("wow!!!!");
        schedule4.setColor("blue");
        schedule4.setDate("20171015");
        schedule4.setMemo("Hello");
        dbHelper.insertSchedule(schedule4);
    }

    public ArrayList<Schedule> getSchedulesForADay(CalendarDay date, String selectedDate) {
        HashMap<Integer, ArrayList<Schedule>> schedulesForAMonth =
                getScheduleMapForAMonth(Integer.parseInt(Util.getYearMonthFromDate(selectedDate)));
        ArrayList<Schedule> schedulesForADay = schedulesForAMonth.get(date.getDay());
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
