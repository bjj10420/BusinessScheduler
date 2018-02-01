package com.apphouse.businessscheduler.main;

import android.view.View;
import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.main.drawer.MainDrawer;

class MainBottomBar implements View.OnClickListener {

    public void init(MainActivity mainActivity) {
        View menuBtn = mainActivity.findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(this);
        View addBtn = mainActivity.findViewById(R.id.addBtn);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.menuBtn)
            openDrawer();
    }

    private void openDrawer() {
        MainDrawer.drawer.openDrawer();
    }
}
