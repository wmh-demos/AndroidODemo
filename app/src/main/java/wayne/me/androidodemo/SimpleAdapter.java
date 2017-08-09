package wayne.me.androidodemo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SimpleAdapter extends BaseAdapter {

    private Context mCxt;
    private String[] mStrArr;

    SimpleAdapter(Context cxt) {
        mCxt = cxt;
    }

    void setStrArr(String[] strArr) {
        mStrArr = strArr;
    }

    @Override
    public int getCount() {
        if (mStrArr != null) {
            return mStrArr.length;
        } else {
            return 0;
        }
    }

    @Override
    public String getItem(int position) {
        if (mStrArr != null) {
            return mStrArr[position];
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCxt);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.main_activity_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.item_textview)).setText(mStrArr[position]);
        convertView.setTag(position);
        return convertView;
    }
}
