package e.commerce.shopping.guide.about.activity;

import android.widget.CompoundButton;
import android.widget.Switch;

import butterknife.BindView;
import e.commerce.shopping.guide.R;
import e.commerce.shopping.guide.common.BaseNoPluginActivity;
import e.commerce.shopping.guide.common.tools.SPUtils;

/**
 * TODO 是否调用接口  是否接收1.推送促销消息2.站内消息
 */
public class NotificationSettingsActivity extends BaseNoPluginActivity {

    @BindView(R.id.s_promotion)
    Switch sPromotion;
    @BindView(R.id.s_messages)
    Switch sMessages;

    boolean isPromotion;

    boolean isMessages;
    @Override
    protected void init(int layoutId) throws Exception {
        super.init(R.layout.activity_notification_settings);
        setTitle("Settings");

        isPromotion = (boolean) SPUtils.get(this, "get_promotion", false);
        sPromotion.setChecked(isPromotion);
        sPromotion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPUtils.put(NotificationSettingsActivity.this, "get_promotion", isChecked);
            }
        });


        isMessages = (boolean) SPUtils.get(this, "get_messages", false);
        sMessages.setChecked(isMessages);
        sMessages.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPUtils.put(NotificationSettingsActivity.this, "get_messages", isChecked);
            }
        });
    }




}
