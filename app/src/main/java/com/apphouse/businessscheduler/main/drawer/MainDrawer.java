package com.apphouse.businessscheduler.main.drawer;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.mikepenz.ionicons_typeface_library.Ionicons;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class MainDrawer {

    public MainDrawer(Context context) {
//        new DrawerBuilder().withActivity((Activity) context).build();

        PrimaryDrawerItem item0 = createPrimaryItem(0, "TASKS", Ionicons.Icon.ion_ios_list);
        PrimaryDrawerItem item1 = createPrimaryItem(1, "DAY", Ionicons.Icon.ion_ios_time);
        PrimaryDrawerItem item2 = createPrimaryItem(2, "WEEK", Ionicons.Icon.ion_ios_barcode);
        PrimaryDrawerItem item3 = createPrimaryItem(3, "MONTH", Ionicons.Icon.ion_ios_calendar);
        PrimaryDrawerItem item4 = createPrimaryItem(4, "YEAR", Ionicons.Icon.ion_ios_grid_view);
        PrimaryDrawerItem item5 = createPrimaryItem(5, "SEARCH", Ionicons.Icon.ion_ios_search_strong);
        PrimaryDrawerItem item6 = createPrimaryItem(6, "CONFIGURE", Ionicons.Icon.ion_ios_gear);


//create the drawer and remember the `Drawer` result object
        Drawer drawer = new DrawerBuilder()
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
                        // do something with the clicked item :D
                        return false;
                    }
                })
                .build();
        drawer.setSelection(3);
        drawer.addStickyFooterItem(new PrimaryDrawerItem().withName("Business Scheduler"));
    }

    private PrimaryDrawerItem createPrimaryItem(int identifier, String year, Ionicons.Icon iconView) {
        return new PrimaryDrawerItem().withIdentifier(identifier).withName(year).withIcon(iconView);
    }

    private SecondaryDrawerItem createSecondaryItem(int identifier, String year) {
        return new SecondaryDrawerItem().withIdentifier(identifier).withName(year);
    }
}
