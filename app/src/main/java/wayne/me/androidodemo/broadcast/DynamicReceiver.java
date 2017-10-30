package wayne.me.androidodemo.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DynamicReceiver extends BroadcastReceiver {
    private static final String TAG = "DynamicReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceiver: %s" + intent);
    }
}
