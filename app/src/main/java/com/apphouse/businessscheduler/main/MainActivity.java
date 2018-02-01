package com.apphouse.businessscheduler.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.common.CoreActivity;
import com.apphouse.businessscheduler.databinding.ActivityMainBinding;
import com.apphouse.businessscheduler.util.Util;

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
        MainFragment mainFragment = initMainFragment();
        initMainPresenter(mainFragment);
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

    private void initMainPresenter(MainFragment mainFragment) {
        mainPresenter = new MainPresenter(mainFragment);
    }

    @Override
    public void loadCurrentPageDisplay() {
        Log.d("순서테스트", "MainActivity loadCurrentPageDisplay");
    }
}
