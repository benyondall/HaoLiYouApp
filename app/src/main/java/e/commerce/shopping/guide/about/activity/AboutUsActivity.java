package e.commerce.shopping.guide.about.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import e.commerce.shopping.guide.R;
import e.commerce.shopping.guide.common.BaseNoPluginActivity;

public class AboutUsActivity extends BaseNoPluginActivity {

    @BindView(R.id.tv_service_policy)
    TextView tvServicePolicy;

    @BindView(R.id.tv_privacy_policy)
    TextView tvPrivacyPolicy;

    @Override
    protected void init(int layoutId) throws Exception {
        super.init(R.layout.activity_about_us);
        setTitle("About Us");

    }

    @OnClick({R.id.tv_service_policy, R.id.tv_privacy_policy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_service_policy:
                startActivity(new Intent(AboutUsActivity.this, ServicePolicyActivity.class));
                break;
            case R.id.tv_privacy_policy:
                startActivity(new Intent(AboutUsActivity.this, PrivacyPolicyActivity.class));
                break;
        }
    }
}
