package com.test.mycontacts.utils;


import android.content.Context;
import android.content.DialogInterface;

import com.test.mycontacts.R;

public class CustomDialogUtil {
    public static void showDialogOk(String msg, String ttl,
                                    final Context ctx) {
        try {
            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ctx);
            builder.setMessage(msg).setCancelable(false)
                    .setPositiveButton(ctx.getResources().getString(R.string.ok), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            if (ttl != null && !ttl.equals(""))
                builder.setTitle(ttl);
            android.app.AlertDialog alert = builder.create();
            alert.show();
            alert.setCancelable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
