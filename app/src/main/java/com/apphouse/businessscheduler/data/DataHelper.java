package com.apphouse.businessscheduler.data;

import android.content.Context;
import com.apphouse.businessscheduler.vo.Schedule;
import java.util.HashMap;
import static com.apphouse.businessscheduler.data.DBHelper.dbHelper;

public class DataHelper {

    public static DataHelper dataHelper;
    private HashMap<Integer, HashMap<Integer, Schedule>> scheduleMapByMonth;


    public void init(Context context){
        dataHelper = this;
        new DBHelper(context);
        scheduleMapByMonth = new HashMap<Integer, HashMap<Integer, Schedule>>();
        dbHelper.selectAllSchedule(scheduleMapByMonth);
        insertTest();
    }

    private void insertTest() {
        Schedule schedule = new Schedule();
        schedule.setScheduleName("test schedule");
        schedule.setColor("blue");
        schedule.setDate("20180208");
        schedule.setMemo("Hello");
        dbHelper.insertSchedule(schedule);
    }

    public HashMap<Integer, HashMap<Integer, Schedule>> getScheduleMapByMonth() {
        return scheduleMapByMonth;
    }

    public HashMap<Integer, Schedule> getScheduleMapForAMonth(int month){
        return scheduleMapByMonth.get(month);
    }
}
