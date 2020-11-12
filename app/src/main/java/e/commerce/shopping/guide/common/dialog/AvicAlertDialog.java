package e.commerce.shopping.guide.common.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import e.commerce.shopping.guide.R;


/**
 * Created by Administrator on 2017/3/25.
 */
public class AvicAlertDialog {

    private Button positive;
    private Button negative;
    private InterceptEvent interceptEvent;
    private OkListener okListener;

    public void setOkListener(OkListener okListener) {
        this.okListener = okListener;
    }

    public void setInterceptEvent(InterceptEvent interceptEvent) {
        this.interceptEvent = interceptEvent;
    }

    public interface InterceptEvent {
        void PositiveButton(View view, String yes);
        void NegativeButton(View view, String no);
    }

    public interface OkListener{
        void PositiveButton(View view, String yes);
    }

    public AvicAlertDialog(final Context context, int icon , String message, int type) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert,null);

        StringBuilder start = new StringBuilder();
        String result = null;
        String[] mes = message.split("@");
        if (mes != null){
            for (int i =0 ;i<mes.length;i++) {
                start.append(mes[i]).append("\n");
            }
            result = start.substring(0,start.length()-1);
        } else {
            result = message;
        }

        ImageView imageView = view.findViewById(R.id.customAlert_title_icon);
        if (-1 == icon) {
            imageView.setVisibility(View.INVISIBLE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(icon);
        }
        TextView textView = view.findViewById(R.id.customAlert_message);
        textView.setText(result);
        final AlertDialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        dialog = builder.create();
        //只有一个按键的Dialog
        if (type == 1) {
            dialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
            positive = dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
            positive.setTextColor(0xff45c01a);

            positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (okListener != null) {
                        okListener.PositiveButton(v,"确定");
                    }
                    dialog.dismiss();
                }
            });

            // 有两个按键的Dialog
        } else if (type == 2){
            dialog.setButton(android.app.AlertDialog.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.setButton(android.app.AlertDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dialog.show();
            positive = dialog.getButton(android.app.AlertDialog.BUTTON_POSITIVE);
            negative = dialog.getButton(android.app.AlertDialog.BUTTON_NEGATIVE);
            positive.setTextColor(0xff45c01a);
            negative.setTextColor(0xff45c01a);

            positive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (interceptEvent != null) {
                        interceptEvent.PositiveButton(v,"确定");
                    }
                    dialog.dismiss();
                }
            });
            negative.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (interceptEvent != null) {
                        interceptEvent.NegativeButton(v,"取消");
                    }
                    dialog.dismiss();
                }
            });


        }

    }

}
