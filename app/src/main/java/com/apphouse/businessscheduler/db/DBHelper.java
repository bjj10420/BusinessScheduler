package com.apphouse.businessscheduler.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
}
