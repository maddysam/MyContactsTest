package com.test.mycontacts.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetConection {

    public static boolean isInternetConnection(Context mContext) {
        try {
            final ConnectivityManager connMgr = (ConnectivityManager) mContext
                    .getSystemService(Context.CONNECTIVITY_SERVICE);

            final NetworkInfo wifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            final NetworkInfo mobile = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (wifi.isAvailable()
                    && wifi.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
            if (mobile.isAvailable()
                    && mobile.getState() == NetworkInfo.State.CONNECTED) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }

    public static boolean isWifi(Context mContext) {
        final ConnectivityManager connMgr = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo wifi = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (wifi.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isOtherNetwork(Context mContext) {
        final ConnectivityManager connMgr = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        final NetworkInfo mobile = connMgr
                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (mobile.isAvailable()) {
            return true;
        } else {
            return false;
        }
    }
}
