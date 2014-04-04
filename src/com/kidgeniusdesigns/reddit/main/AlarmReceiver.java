package com.kidgeniusdesigns.reddit.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent downloader = new Intent(context, RedditService.class);
        context.startService(downloader);
    }

}
