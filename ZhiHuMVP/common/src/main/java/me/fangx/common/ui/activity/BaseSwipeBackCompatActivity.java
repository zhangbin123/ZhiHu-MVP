package me.fangx.common.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import me.fangx.common.R;
import me.fangx.common.widget.swipeback.SwipeBackActivityBase;
import me.fangx.common.widget.swipeback.SwipeBackActivityHelper;
import me.fangx.common.widget.swipeback.SwipeBackLayout;
import me.fangx.common.widget.swipeback.Utils;

/**
 * Created by fangxiao on 15/12/18.
 */
public abstract class BaseSwipeBackCompatActivity extends BaseAppCompatActivity implements SwipeBackActivityBase {
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    private void initView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.sr_color_primary);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}