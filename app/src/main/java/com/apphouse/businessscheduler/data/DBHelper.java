package com.apphouse.businessscheduler.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.apphouse.businessscheduler.util.Util;
import com.apphouse.businessscheduler.vo.Schedule;
import java.util.HashMap;
import java.util.Locale;

public class DBHelper extends SQLiteOpenHelper {

    // DB NAME
    public static final String DATABASE_NAME = "business_scheduler.sqlite";
    // DB VERSION ( 숫자가 변하면 업그레이드 ( onUpgrade ) 됨 ) ( 새로 설치하면 onCreate )
    public static final int DB_VERSION_1 = 1;
    public static SQLiteDatabase DB;
    public static DBHelper dbHelper;
    private final Context context;

    // 스케쥴 관리 테이블
    public String scheduleTableName = "schedule";
    // 스케쥴 관리 테이블 컬럼명
    String no_colum = "no";
    String dateValue_colum = "dateValue";
    String scheduleName_colum = "scheduleName";
    String orderValue_colum = "orderValue";
    String timeValue_colum = "timeValue";
    String memoValue_colum = "memoValue";
    String colorValue_colum = "colorValue";


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DB_VERSION_1);
        dbHelper = this;
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 트랜잭션 시작
        db.beginTransaction();
        // 1번 쿼리: 스케쥴 테이블 생성
        String sql = new StringBuilder("CREATE TABLE ").append(scheduleTableName).append(" (")
                .append(no_colum).append(" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ")
                .append(dateValue_colum).append(" TEXT NOT NULL, ")
                .append(scheduleName_colum).append(" TEXT NOT NULL, ")
                .append(orderValue_colum).append(" INTEGER, ")
                .append(timeValue_colum).append(" TEXT, ")
                .append(memoValue_colum).append(" TEXT, ")
                .append(colorValue_colum).append(" TEXT)")
                .toString();
        db.execSQL(sql);

        // 트랜잭션 성공
        db.setTransactionSuccessful();
        // 트랜잭션 종료
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long insertSchedule(Schedule schedule){
        ContentValues values = new ContentValues();
        values.clear();
        values.put(dateValue_colum, schedule.getDate());
        values.put(scheduleName_colum, schedule.getScheduleName());
        values.put(orderValue_colum, schedule.getOrder());
        values.put(timeValue_colum, schedule.getTime());
        values.put(memoValue_colum, schedule.getMemo());
        values.put(colorValue_colum, schedule.getColor());
        Log.d("인설트스케쥴 체크", String.valueOf(schedule.getOrder()));
        long result = DB.insert(scheduleTableName, null, values);
        return result;
    }

    public void selectAllSchedule(HashMap<Integer, HashMap<Integer, Schedule>> allScheduleMap){
        DB = getWritableDatabase();

        String sql = String.format(Locale.getDefault(),
                "SELECT * FROM %s order by " + orderValue_colum + " asc ",
                scheduleTableName
        );

        Cursor c = DB.rawQuery(sql, null);
        if(c != null) {
            while (c.moveToNext()) {
                Schedule schedule = new Schedule();
                String scheduleDate = c.getString(c.getColumnIndex(dateValue_colum));
                schedule.setDate(scheduleDate);
                schedule.setScheduleName(c.getString(c.getColumnIndex(scheduleName_colum)));
                schedule.setOrder(c.getInt(c.getColumnIndex(orderValue_colum)));
                schedule.setMemo(c.getString(c.getColumnIndex(memoValue_colum)));
                schedule.setTime(c.getString(c.getColumnIndex(timeValue_colum)));
                schedule.setColor(c.getString(c.getColumnIndex(colorValue_colum)));
                Log.d("셀렉트 확인", String.valueOf(schedule.getDate()));
                String scheduleYearMonth = Util.getYearMonthFromDate(scheduleDate);
                // 스케쥴 추가
                addScheduleToMapByMonth(allScheduleMap, Integer.parseInt(scheduleYearMonth), schedule);
            }
        }
    }

    private void addScheduleToMapByMonth(HashMap<Integer, HashMap<Integer, Schedule>> allScheduleMap, int scheduleYearMonth, Schedule schedule) {
        HashMap<Integer, Schedule> scheduleMap = allScheduleMap.get(scheduleYearMonth);
        if(scheduleMap == null){
            scheduleMap = new HashMap<Integer, Schedule>();
            allScheduleMap.put(scheduleYearMonth, scheduleMap);
        }

        scheduleMap.put(Integer.parseInt(schedule.getDate().substring(6,8)), schedule);
        Log.d("해당 월의 스케쥴맵에 추가할 때 키값 체크", String.valueOf(Integer.parseInt(schedule.getDate().substring(6,8) + "000" + schedule.getOrder())));
    }

}