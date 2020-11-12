package e.commerce.shopping.guide.common.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.annotation.ColorInt;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import e.commerce.shopping.guide.R;

public class PopTopTip extends PopupWindow {

    private Context mContext;
    private View mPopWindow;
    private TextView textView;

    @SuppressLint("ResourceType")
    public PopTopTip(Context context, String text, @ColorInt int backgroundColor) {
        this.mContext = context;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mPopWindow = inflater.inflate(R.layout.pop_top_tip, null);
        if (backgroundColor != 0){
            mPopWindow.setBackgroundColor(context.getResources().getColor(backgroundColor));
        }
        textView = (TextView) mPopWindow.findViewById(R.id.tv_tip_message);
        setPopWindow(text);
    }

    public void setPopWindow(String text) {
        textView.setText(text);
        // 把View添加到PopWindow中
        this.setContentView(mPopWindow);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(dip2px(mContext, 60));
        //  设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        //   设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));
    }

    public void show(final View parent){
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                showAtLocation(parent,
                        Gravity.TOP, 0, 0);
            }

            @Override
            public void onFinish() {
                dismiss();
            }
        }.start();
    }


    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
