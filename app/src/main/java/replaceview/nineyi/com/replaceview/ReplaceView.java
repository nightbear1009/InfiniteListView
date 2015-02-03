package replaceview.nineyi.com.replaceview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tedliang on 15/2/3.
 */
public class ReplaceView extends LinearLayout {
    private ListView listView;

    public ReplaceView(Context context) {
        super(context);
    }

    public ReplaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReplaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        setOrientation(LinearLayout.VERTICAL);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.replaceview, this, true);
        listView = (ListView) view.findViewById(R.id.listview);
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            data.add("i = " + i);
        }
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                data);
        ReplaceViewHelper.getInstance().addView(arrayAdapter);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0 ) {
                    if(ReplaceViewHelper.getInstance().canBack()){
                        ReplaceView.this.removeAllViews();
                        BaseAdapter lastView = ReplaceViewHelper.getInstance().backView();
                        Log.d("Ted", "size " + lastView.getCount());
                        listView.setAdapter(lastView);
                        ReplaceView.this.addView(listView);
                    }
                } else {
                    ReplaceView.this.removeAllViews();
                    ListView nextListView = new ListView(getContext());
                    List<String> data = new ArrayList<String>();
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            getContext(),
                            android.R.layout.simple_list_item_1,
                            data);
                    for (int i = 0; i < 1000; i++) {
                        arrayAdapter.add(ReplaceViewHelper.getInstance().stack.size() + "j = " + i);
                    }
                    nextListView.setAdapter(arrayAdapter);
                    nextListView.setOnItemClickListener(this);
                    ReplaceViewHelper.getInstance().addView(arrayAdapter);
                    ReplaceView.this.addView(nextListView);
                }
            }
        });


    }
}
