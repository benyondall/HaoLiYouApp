package e.commerce.shopping.guide.common.tools;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * Created by Administrator on 2017/3/29.
 */

public class ToastUtil {
    private static Toast toast;

    public static void showToast(Context context,
                                 String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(content);
        }
        toast.show();
    }

    public static void showLongToast(Context context,
                                     String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_LONG);
        } else {
            toast.setText(content);
        }
        toast.show();
    }


    public static void showToastCenter(Context context,
                                       String content) {
//        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,300);
//        } else {
//            toast.setGravity(Gravity.CENTER,0,300);
//            toast.setText(content);
//        }
        toast.show();
    }

    public static void showLongToastCenter(Context context,
                                           String content) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,300);
        } else {
            toast.setGravity(Gravity.CENTER,0,300);
            toast.setText(content);
        }
        toast.show();
    }
}
