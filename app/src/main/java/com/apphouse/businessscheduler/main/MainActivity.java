package com.apphouse.businessscheduler.main;

import android.os.Bundle;
import android.util.Log;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.common.CoreActivity;
import com.apphouse.businessscheduler.data.DBHelper;
import com.apphouse.businessscheduler.data.DataHelper;
import com.apphouse.businessscheduler.main.drawer.MainDrawer;
import com.apphouse.businessscheduler.util.Util;

import static com.apphouse.businessscheduler.data.DataHelper.dataHelper;

public class MainActivity extends CoreActivity {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("순서테스트", "MainActivity onCreate");
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initData();
        MainFragment mainFragment = initMainFragment();
        initMainPresenter(mainFragment);
        initDrawer();
        initBottomBar();
        dataHelper.insertTest();
    }

    private void initData() {
        new DataHelper().init(this);
    }

    private MainFragment initMainFragment() {
        MainFragment MainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentLayout);
        if (MainFragment == null) {
            MainFragment = MainFragment.newInstance();
            Util.addFragmentToActivity(
                    getSupportFragmentManager(), MainFragment, R.id.contentLayout);
        }
        return MainFragment;
    }

    private void initDrawer() {
        MainDrawer mainDrawer = new MainDrawer();
        mainDrawer.init(this);
    }

    private void initBottomBar() {
        MainBottomBar mainBottomBar = new MainBottomBar();
        mainBottomBar.init(this);
    }

    private void initMainPresenter(MainFragment mainFragment) {
        mainPresenter = new MainPresenter(mainFragment, this);
    }

    @Override
    public void loadCurrentPageDisplay() {
        Log.d("순서테스트", "MainActivity loadCurrentPageDisplay");
    }
}
