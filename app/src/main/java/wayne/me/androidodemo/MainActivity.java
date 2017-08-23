package wayne.me.androidodemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import wayne.me.androidodemo.autofill.StandardSignInActivity;
import wayne.me.androidodemo.notification.NotificationActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final int INDEX_TEST_NOTIFY = 0;
    private static final int INDEX_TEST_AUTOFILL = 1;

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
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case INDEX_TEST_AUTOFILL:
                startActivity(StandardSignInActivity.getStartActivityIntent(this));
                break;
        }
    }
}
