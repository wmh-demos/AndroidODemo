package wayne.me.androidodemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int INDEX_TEST_NOTIFY = 0;

    private Context mContext;

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initData();
    }

    private void initView() {
        mListView = findViewById(R.id.list_view);
    }

    private void initData() {
        String[] strArr = getResources().getStringArray(R.array.activity_item);
        SimpleAdapter adapter = new SimpleAdapter(mContext);
        adapter.setStrArr(strArr);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        switch (position) {
            case INDEX_TEST_NOTIFY:
                notifyTest();
                break;
        }
    }

    private void notifyTest() {
        NotificationManager nm =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String[] channelIds = new String[]{"my_channel_01", "my_channel_02", "my_channel_03"};

        int importance = NotificationManager.IMPORTANCE_LOW;

        for (int index = 0; index < channelIds.length; index++) {
            String id = channelIds[index];
            CharSequence name = getResources().getString(R.string.notify_test, id);
            NotificationChannel channel = new NotificationChannel(id, name, importance);
            channel.setShowBadge(true);

            if (nm != null) {
                nm.createNotificationChannel(channel);
                Notification notification = new Notification.Builder(mContext, id)
                        .setContentTitle("New Messages")
                        .setContentText("This is notification " + (index + 1))
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .build();

                nm.notify(1000 + index, notification);
            }
        }
    }
}
