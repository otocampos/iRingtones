package com.gpaddy.ringtones.iphone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.Locale;

public class S3PagerAdapter extends FragmentPagerAdapter {

    public S3PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                Fragment ringtones = new P21Ringtone();
                return ringtones;
            case 1:
                Fragment alert = new P22Alert();
                return alert;
            case 2:
                Fragment favorite = new P23Favorite();
                return favorite;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Locale locale = Locale.getDefault();

        switch (position) {
            case 0:
                return "Ringtones";
            case 1:
                return "Alert";
            case 2:
                return "Favorite";
        }
        return null;
    }

}


