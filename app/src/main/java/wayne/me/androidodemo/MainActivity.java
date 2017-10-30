package wayne.me.androidodemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import wayne.me.androidodemo.broadcast.DynamicReceiver;
import wayne.me.androidodemo.notification.NotificationActivity;
import wayne.me.common.CommonConstants;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int INDEX_TEST_NOTIFY = 0;
    private static final int INDEX_TEST_BROADCAST = 1;

    private Context mContext;

    private ListView mListView;

    private BroadcastReceiver mReceiver = new DynamicReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterDynamicReceiver();
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
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case INDEX_TEST_BROADCAST:
                registerDynamicReceiver();
                break;
        }
    }

    private void registerDynamicReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        filter.addAction(Intent.ACTION_MY_PACKAGE_REPLACED);

        filter.addAction(CommonConstants.ACTION_APP2_TEST_TARGET);
        filter.addAction(CommonConstants.ACTION_APP2_TEST_NORMAL);
        filter.addAction(CommonConstants.ACTION_APP2_TEST_PERM_NORMAL);
        filter.addAction(CommonConstants.ACTION_APP2_TEST_PERM_SIGN);
        filter.addAction(CommonConstants.ACTION_APP2_TEST_PERM_SIGN_OR_SYSTEM);

        registerReceiver(mReceiver, filter);
        Toast.makeText(this, "registerReceiver success", Toast.LENGTH_LONG).show();
    }

    private void unregisterDynamicReceiver() {
        unregisterReceiver(mReceiver);
    }
}
