package com.apphouse.businessscheduler.main.drawer;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.apphouse.businessscheduler.R;
import com.apphouse.businessscheduler.main.MainActivity;
import com.apphouse.businessscheduler.main.MainFragment;
import com.apphouse.businessscheduler.main.MainPresenter;
import com.apphouse.businessscheduler.week.WeekFragment;
import com.apphouse.businessscheduler.week.WeekPresenter;
import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainDrawer {

    public static Drawer drawer;

    public MainDrawer() {

    }

    public void init(final Context context){

        PrimaryDrawerItem item0 = createPrimaryItem(0, "TASKS", Ionicons.Icon.ion_ios_list);
        PrimaryDrawerItem item1 = createPrimaryItem(1, "DAY", Ionicons.Icon.ion_ios_time);
        PrimaryDrawerItem item2 = createPrimaryItem(2, "WEEK", Ionicons.Icon.ion_ios_barcode);
        PrimaryDrawerItem item3 = createPrimaryItem(3, "MONTH", Ionicons.Icon.ion_ios_calendar);
        PrimaryDrawerItem item4 = createPrimaryItem(4, "YEAR", Ionicons.Icon.ion_ios_grid_view);
        PrimaryDrawerItem item5 = createPrimaryItem(5, "SEARCH", Ionicons.Icon.ion_ios_search_strong);
        PrimaryDrawerItem item6 = createPrimaryItem(6, "CONFIGURE", Ionicons.Icon.ion_ios_gear);

        drawer = new DrawerBuilder()
                .withActivity((Activity) context)
                .addDrawerItems(
                        item0,
                        item1,
                        item2,
                        item3,
                        item4,
                        new DividerDrawerItem(),
                        item5,
                        item6
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {


                        switch (position){
                            case 2 :
                                WeekFragment weekfragment = new WeekFragment();
                                new WeekPresenter(weekfragment, context);
                                changeFragment(weekfragment,context);
                                break;

                            case 3 :
                                MainFragment fragment = new MainFragment();
                                new MainPresenter(fragment, context);
                                changeFragment(fragment,context);
                                break;

                        }
                        return false;
                    }
                })
                .build();
        drawer.setSelection(3, false);
//        drawer.setSelection(3);
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Business Scheduler"));
    }

    private void changeFragment(Fragment fragment, Context context) {
        FragmentManager fragmentManager = ((MainActivity) context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace( R.id.contentLayout, fragment );
        fragmentTransaction.commit();
    }

    private PrimaryDrawerItem createPrimaryItem(int identifier, String year, Ionicons.Icon iconView) {
        return new PrimaryDrawerItem().withIdentifier(identifier).withName(year).withIcon(iconView);
    }

    private SecondaryDrawerItem createSecondaryItem(int identifier, String year) {
        return new SecondaryDrawerItem().withIdentifier(identifier).withName(year);
    }

}
