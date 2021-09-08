package com.myproject.xcelacadstask.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import java.text.DecimalFormat;

public class Utils {

    public static void showToast(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String format(String number) {
        char[] suffix = {' ', 'k', 'M', 'B', 'T', 'P', 'E'};
        long numValue = Long.parseLong(number);
        int value = (int) Math.floor(Math.log10(numValue));
        int base = value / 3;
        if (value >= 3 && base < suffix.length) {
            return new DecimalFormat("#0.0").format(numValue / Math.pow(10, base * 3)) + suffix[base];
        } else {
            return new DecimalFormat("#,##0").format(numValue);
        }
    }

    public static void shareLink(Context context, String videoId) {

        String link = "https://www.youtube.com/watch?v="+videoId;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share Youtube Video");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, link);
        try {
            context.startActivity(Intent.createChooser(intent, "Share Youtube Video using"));
        } catch (ActivityNotFoundException e) {
        }
    }
}
