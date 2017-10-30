package wayne.me.app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import wayne.me.common.CommonConstants;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.send_broadcast).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBroadcasts();
            }
        });
    }

    private void sendBroadcasts() {
        Intent intent = new Intent(CommonConstants.ACTION_APP2_TEST_TARGET);
        intent.setPackage("wayne.me.androidodemo");
        sendBroadcast(intent);

        intent = new Intent(CommonConstants.ACTION_APP2_TEST_NORMAL);
        sendBroadcast(intent);

        intent = new Intent(CommonConstants.ACTION_APP2_TEST_PERM_NORMAL);
        sendBroadcast(intent, CommonConstants.PERM_NORMAL);

        intent = new Intent(CommonConstants.ACTION_APP2_TEST_PERM_SIGN);
        sendBroadcast(intent, CommonConstants.PERM_SIGN);

        intent = new Intent(CommonConstants.ACTION_APP2_TEST_PERM_SIGN_OR_SYSTEM);
        sendBroadcast(intent, CommonConstants.PERM_SIGN_OR_SYSTEM);
    }
}