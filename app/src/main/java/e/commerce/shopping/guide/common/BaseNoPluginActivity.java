package e.commerce.shopping.guide.common;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;
import e.commerce.shopping.guide.R;
import e.commerce.shopping.guide.common.dialog.AvicAlertDialog;
import e.commerce.shopping.guide.common.dialog.AvicLoadingDialog;
import e.commerce.shopping.guide.common.tools.L;
import e.commerce.shopping.guide.common.tools.ToastUtil;

/**
 * 所有Acitivity鸡肋 实现了 对应的View方法和注入等
 */
public abstract class BaseNoPluginActivity extends AppCompatActivity{

    protected Context oThis;
    protected AvicAlertDialog avicAlertDialog;
    protected AvicAlertDialog avicAlertPosCancle;
    protected AvicLoadingDialog avicLoadingDialog;
    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);  //默认没有阴影
            actionBar.setDisplayHomeAsUpEnabled(true); //默认显示按钮
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_app_back);
        }

        L.TAG = this.getClass().getName();
        L.v("onCreate");

        try {
            init(0);
        } catch (Exception e) {
            e.printStackTrace();
            L.e(e.getMessage());
            L.e("视图注入出现问题");
        }

    }

    /**
     * 隐藏左上返回按钮
     */
    protected void hideBackButton() {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    /**
     * 隐藏上面的ActionBar
     **/
    protected void hideActionBar() {
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    protected void showActionBar() {
        if (actionBar != null) {
            actionBar.show();
        }
    }

    /**
     * 设置页面标题
     */
    protected void setTitle(Character character) {
        if (actionBar != null) {
            actionBar.setTitle(character);
        }
    }

    protected void init(int layoutId) throws Exception {
        if (layoutId == 0 ) {
            throw new Exception("this activity must be init!");
        }

        setContentView(layoutId);

        ButterKnife.bind(this);

        oThis = this;
        avicLoadingDialog = new AvicLoadingDialog(oThis);
    }

    /**
     * 显示进度条
     */
    public void showLoading() {
        avicLoadingDialog.show();
    }

    /**
     * 显示进度条
     */
    public void showLoading(boolean cancel) {
        this.avicLoadingDialog.setCanceledOnTouchOutside(cancel);
        avicLoadingDialog.show();
    }

    /**
     * 关闭进度条
     */
    public void closeLoading() {
        avicLoadingDialog.close();
    }

    /**
     * 显示吐司
     *
     * @param msg
     */
    public void showToast(String msg) {
        ToastUtil.showToastCenter(oThis, msg);
    }

    /**
     * 显示一个需要确认的提示
     */
    public void showAlertDialog(String msg, AvicAlertDialog.OkListener listener) {
        avicAlertDialog = new AvicAlertDialog(oThis, android.R.drawable.ic_dialog_alert, msg, 1);
        avicAlertDialog.setOkListener(listener);
    }

    /**
     * 显示一个需要确认和取消的提示
     *
     * @param msg      需要显示的信息
     * @param listener 弹窗确定键和取消键的监听
     * @author: whb
     * @date: 2019/1/4 15:37
     **/
    public void showAlertDialogPosCancle(String msg, AvicAlertDialog.InterceptEvent listener) {
        avicAlertPosCancle = new AvicAlertDialog(oThis, android.R.drawable.ic_dialog_alert, msg, 2);
        avicAlertPosCancle.setInterceptEvent(listener);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finishActivity();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void finishActivity() {
        finish();
    }

}