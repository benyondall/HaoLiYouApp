package e.commerce.shopping.guide.main.ui.user.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import e.commerce.shopping.guide.R;
import e.commerce.shopping.guide.about.activity.AboutUsActivity;
import e.commerce.shopping.guide.about.activity.ContactUsActivity;
import e.commerce.shopping.guide.about.activity.SettingsActivity;
import e.commerce.shopping.guide.common.dialog.PopTopTip;
import e.commerce.shopping.guide.main.ui.user.activity.FollowActivity;
import e.commerce.shopping.guide.main.ui.user.activity.NotificationActivity;
import e.commerce.shopping.guide.main.ui.user.activity.RecentlyViewedActivity;
import e.commerce.shopping.guide.main.ui.user.presenter.UserPresenter;
import e.commerce.shopping.guide.main.ui.user.view.UserView;
import e.commerce.shopping.guide.mvp.BaseFragment;

/**
 * 第三页
 */
public class UserFragment extends BaseFragment<UserPresenter> implements UserView {

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_nickname)
    TextView tvNickname;
    @BindView(R.id.iv_mailbox)
    ImageView ivMailbox;
    @BindView(R.id.tv_mailbox)
    TextView tvMailbox;
    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.bt_logout)
    Button btLogout;
    @BindView(R.id.tv_follow_num)
    TextView tvFollowNum;
    @BindView(R.id.rl_follow)
    RelativeLayout rlFollow;
    @BindView(R.id.tv_recetly_num)
    TextView tvRecetlyNum;
    @BindView(R.id.tv_notification_num)
    TextView tvNotificationNum;
    @BindView(R.id.rl_notification)
    RelativeLayout rlNotification;
    @BindView(R.id.rl_contact_us)
    RelativeLayout rlContactUs;
    @BindView(R.id.rl_about_us)
    RelativeLayout rlAboutUs;
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;

    @Override
    protected void init(int layoutId, Class<UserPresenter> clazz) throws Exception {
        super.init(R.layout.fragment_user, UserPresenter.class);


    }


    @OnClick({R.id.iv_head, R.id.bt_login, R.id.bt_logout, R.id.rl_follow, R.id.rl_recetly, R.id.rl_notification, R.id.rl_contact_us, R.id.rl_about_us, R.id.rl_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                break;
            case R.id.bt_login: {
                @SuppressLint("ResourceAsColor") PopTopTip popTopTip = new PopTopTip(oThis, "Login Success", R.color.tip_login);
                popTopTip.show(oThis.findViewById(R.id.container));

                break;
            }
            case R.id.bt_logout:{
                @SuppressLint("ResourceAsColor") PopTopTip popTopTip = new PopTopTip(oThis, "Logout Success",R.color.tip_logout);
                popTopTip.show(oThis.findViewById(R.id.container));
                break;
            }

            case R.id.rl_follow:
                startActivity(new Intent(oThis, FollowActivity.class));
                break;
            case R.id.rl_recetly:
                startActivity(new Intent(oThis, RecentlyViewedActivity.class));
                break;
            case R.id.rl_notification:
                startActivity(new Intent(oThis, NotificationActivity.class));
                break;
            case R.id.rl_contact_us:
                startActivity(new Intent(oThis, ContactUsActivity.class));
                break;
            case R.id.rl_about_us:
                startActivity(new Intent(oThis, AboutUsActivity.class));
                break;
            case R.id.rl_setting:
                startActivity(new Intent(oThis, SettingsActivity.class));
                break;
            default:
                break;
        }
    }
}