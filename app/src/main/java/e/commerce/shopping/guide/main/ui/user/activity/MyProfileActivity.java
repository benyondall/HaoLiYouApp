package e.commerce.shopping.guide.main.ui.user.activity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import e.commerce.shopping.guide.R;
import e.commerce.shopping.guide.main.ui.user.presenter.MyProfileP;
import e.commerce.shopping.guide.main.ui.user.view.MyProfileV;
import e.commerce.shopping.guide.mvp.BaseActivity;

public class MyProfileActivity extends BaseActivity<MyProfileP> implements MyProfileV {


    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.rl_profile_picture)
    RelativeLayout rlProfilePicture;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.rl_username)
    RelativeLayout rlUsername;
    @BindView(R.id.rl_change_password)
    RelativeLayout rlChangePassword;
    @BindView(R.id.ll_main)
    LinearLayout llMain;

    @Override
    protected void init(int layoutId, Class<MyProfileP> clazz) throws Exception {
        super.init(R.layout.activity_my_profile, MyProfileP.class);
        setTitle("My Profile");

    }


    @OnClick({R.id.rl_profile_picture, R.id.rl_username, R.id.rl_change_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_profile_picture://set head
                showTakePhotoTip();
                break;
            case R.id.rl_username://set username

                break;
            case R.id.rl_change_password://change password

                break;
        }
    }


    public WindowManager.LayoutParams lp;
    private void showTakePhotoTip() {
        int width = getResources().getDisplayMetrics().widthPixels;//activity width
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;//view height
        View contentView = LayoutInflater.from(oThis).inflate(R.layout.pop_take_photo_bottom_tip, null);
        final PopupWindow popupWindow = new PopupWindow(contentView, width, height);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        Button bt_take = contentView.findViewById(R.id.bt_take);
        Button bt_album = contentView.findViewById(R.id.bt_album);
        Button bt_cancel = contentView.findViewById(R.id.bt_cancel);

        bt_take.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                popupWindow.dismiss();
            }
        });
        bt_album.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                popupWindow.dismiss();
            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDismiss();
                popupWindow.dismiss();
            }
        });
        lp = getWindow().getAttributes();
        lp.alpha = 0.6f;
        getWindow().setAttributes(lp);
        popupWindow.showAtLocation(llMain, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                popDismiss();
            }
        });
    }

    private void popDismiss() {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 1f;
        getWindow().setAttributes(lp);
    }


}
