package com.apphouse.businessscheduler.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public abstract class CoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTransition();
    }

    void activityTransition(){
        Log.d("순서테스트", "CoreActivity activityTransition");
        loadDBData();
        loadCurrentPageDisplay();
    }

    final void loadDBData(){
        Log.d("loadDBData", "DB를 로딩합니다");
    }

    // 데이터를 가지고 현재 화면구성
    public abstract void loadCurrentPageDisplay();

}
