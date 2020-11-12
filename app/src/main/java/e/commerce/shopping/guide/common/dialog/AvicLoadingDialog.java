package e.commerce.shopping.guide.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import e.commerce.shopping.guide.R;


/**
 * Created by Administrator on 2017/3/28.
 */

public class AvicLoadingDialog {

    private AvicCircularRing mLoadingView;
    private Dialog mLoadingDialog;
    private TextView loadingText;

    public AvicLoadingDialog(Context context) {
        avicLoadingDialog(context, "");
    }

    public AvicLoadingDialog(Context context, String msg) {
        avicLoadingDialog(context, msg);
    }

    public void avicLoadingDialog(Context context, String msg) {
        //首先得到整个View
        View view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_view, null);
        LinearLayout layout = view.findViewById(R.id.dialog_view);
        //页面中的LoadingView
        mLoadingView = view.findViewById(R.id.lv_circularring);
        //页面中是否显示文本
        loadingText = view.findViewById(R.id.loading_text);
        if (!TextUtils.isEmpty(msg)) {
            loadingText.setVisibility(View.VISIBLE);
            loadingText.setText(msg);
        }
        //创建自定义样式的Dialog
        mLoadingDialog = new Dialog(context, R.style.loading_dialog);
        //设置返回键有效
        mLoadingDialog.setCancelable(true);
        mLoadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
    }

    public void show() {
//        mLoadingDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.show();
        mLoadingView.startAnim();
    }

    /**
     * create by whb on 2018.06.06
     * 设置loadingDialog不能被取消掉
     *
     * @param canCelable
     */
    public void show(boolean canCelable) {
        mLoadingDialog.setCancelable(canCelable);
        mLoadingDialog.show();
        mLoadingView.startAnim();
    }

    public boolean isLoading() {
        if (mLoadingDialog != null) {
            return mLoadingDialog.isShowing();
        } else {
            return false;
        }
    }

    public void setMessage(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            loadingText.setVisibility(View.VISIBLE);
            loadingText.setText(msg);
        } else {
            loadingText.setText("");
            loadingText.setVisibility(View.GONE);
        }
    }

    /**
     * 关闭dialog
     */
    public void close() {
        if (mLoadingDialog != null) {
            if (isMainThread()) {
                closeDialog();
            } else {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        closeDialog();
                    }
                });
            }
        }
    }

    /**
     * 关闭dialog
     */
    private void closeDialog() {
        loadingText.setText("");
        loadingText.setVisibility(View.GONE);
        //默认是允许取消的，所以在使用完不可取消的时候，恢复默认
        mLoadingDialog.setCancelable(true);
        mLoadingView.stopAnim();
        mLoadingDialog.dismiss();
    }

    public void setCanceledOnTouchOutside(boolean cancel){
        mLoadingDialog.setCanceledOnTouchOutside(cancel);
    }

    private Handler handler = new Handler();

    /**
     * 获取当前线程是在主线程还是子线程
     *
     * @return
     */
    private boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
