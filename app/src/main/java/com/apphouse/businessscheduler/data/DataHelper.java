package com.apphouse.businessscheduler.data;

import android.content.Context;
import com.apphouse.businessscheduler.vo.Schedule;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import static com.apphouse.businessscheduler.data.DBHelper.dbHelper;

public class DataHelper {

    public static DataHelper dataHelper;
    private HashMap<Integer, HashMap<Integer, Schedule>> scheduleMapByMonth;
    private Calendar calendar;

    public void init(Context context){
        dataHelper = this;
        calendar = Calendar.getInstance(Locale.getDefault());
        new DBHelper(context);
        scheduleMapByMonth = new HashMap<Integer, HashMap<Integer, Schedule>>();
        dbHelper.selectAllSchedule(scheduleMapByMonth);
        insertTest();
    }

    private void insertTest() {
        Schedule schedule = new Schedule();
        schedule.setScheduleName("test schedule2");
        schedule.setColor("blue");
        schedule.setDate("20180208");
        schedule.setMemo("Hello");
        dbHelper.insertSchedule(schedule);

        Schedule schedule2 = new Schedule();
        schedule2.setScheduleName("test schedule2");
        schedule2.setColor("blue");
        schedule2.setDate("20180210");
        schedule2.setMemo("Hello");
        dbHelper.insertSchedule(schedule2);
    }

    public HashMap<Integer, HashMap<Integer, Schedule>> getScheduleMapByMonth() {
        return scheduleMapByMonth;
    }

    public HashMap<Integer, Schedule> getScheduleMapForAMonth(int month){
        return scheduleMapByMonth.get(month);
    }

    public void addMonthToCalendar(){
        calendar.add(Calendar.MONTH, 1);
    }

    public void minusMonthToCalendar(){
        calendar.add(Calendar.MONTH, -1);
    }

}
