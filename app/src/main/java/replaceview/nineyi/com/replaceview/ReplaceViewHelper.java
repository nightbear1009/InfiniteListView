package replaceview.nineyi.com.replaceview;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.Stack;

/**
 * Created by tedliang on 15/2/3.
 */
public class ReplaceViewHelper {
    Stack<BaseAdapter> stack;
    static ReplaceViewHelper instance;

    private ReplaceViewHelper() {
        stack = new Stack<>();
    }

    public static ReplaceViewHelper getInstance() {
        if (instance == null) {
            instance = new ReplaceViewHelper();
        }
        return instance;
    }

    public void addView(BaseAdapter view) {
        stack.push(view);
        debug();
    }

    public BaseAdapter backView() {
        BaseAdapter view = stack.pop();
        debug();
        return view;
    }

    public boolean canBack(){
        return !stack.empty();
    }

    private void debug() {

    }


}
