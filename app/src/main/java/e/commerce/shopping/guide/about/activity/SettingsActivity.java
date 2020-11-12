package e.commerce.shopping.guide.about.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.OnClick;
import e.commerce.shopping.guide.R;
import e.commerce.shopping.guide.common.BaseNoPluginActivity;
import e.commerce.shopping.guide.common.tools.SPUtils;
import e.commerce.shopping.guide.main.ui.user.activity.MyProfileActivity;

/**
 *
 */
public class SettingsActivity extends BaseNoPluginActivity {
    @BindView(R.id.rl_my_profile)
    RelativeLayout rlMyProfile;
    @BindView(R.id.s_video_auto_play)
    Switch sVideoAutoPlay;
    @BindView(R.id.rl_video_autoplay)
    RelativeLayout rlVideoAutoPlay;
    @BindView(R.id.rl_notification_setting)
    RelativeLayout rlNotificationSetting;
    @BindView(R.id.rl_do_not_disturb)
    RelativeLayout rlDoNotDisturb;
    boolean video_auto_play;

    @Override
    protected void init(int layoutId) throws Exception {
        super.init(R.layout.activity_setting);
        setTitle("Settings");

        video_auto_play = (boolean) SPUtils.get(this, "video_auto_play", false);
        sVideoAutoPlay.setChecked(video_auto_play);
        sVideoAutoPlay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPUtils.put(SettingsActivity.this, "video_auto_play", isChecked);
            }
        });
    }


    @OnClick({R.id.rl_my_profile, R.id.rl_notification_setting, R.id.rl_do_not_disturb})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_my_profile:
                startActivity(new Intent(oThis, MyProfileActivity.class));
                break;
            case R.id.rl_notification_setting:
                startActivity(new Intent(oThis, NotificationSettingsActivity.class));
                break;
            case R.id.rl_do_not_disturb:

                break;
        }
    }
}
