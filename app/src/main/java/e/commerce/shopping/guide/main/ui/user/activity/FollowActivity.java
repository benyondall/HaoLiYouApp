package e.commerce.shopping.guide.main.ui.user.activity;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import e.commerce.shopping.guide.R;
import e.commerce.shopping.guide.common.adapter.BaseRecycleAdapter;
import e.commerce.shopping.guide.common.adapter.BaseRecycleHolder;
import e.commerce.shopping.guide.main.ui.user.model.BeanRespFollow;
import e.commerce.shopping.guide.main.ui.user.presenter.FollowP;
import e.commerce.shopping.guide.main.ui.user.view.FollowV;
import e.commerce.shopping.guide.mvp.BaseActivity;

public class FollowActivity extends BaseActivity<FollowP> implements FollowV {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    BaseRecycleAdapter<BeanRespFollow> recycleAdapter;
    List<BeanRespFollow> data = new ArrayList<>();

    @Override
    protected void init(int layoutId, Class<FollowP> clazz) throws Exception {
        super.init(R.layout.activity_follow, FollowP.class);
        setTitle("Following");
        data.add(new BeanRespFollow("1111"));
        data.add(new BeanRespFollow("2222"));
        data.add(new BeanRespFollow("3333"));
        data.add(new BeanRespFollow("4444"));
        data.add(new BeanRespFollow("5555"));
        data.add(new BeanRespFollow("6666"));
        data.add(new BeanRespFollow("7777"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(oThis);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycleAdapter = new BaseRecycleAdapter<BeanRespFollow>(oThis,R.layout.item_follow,data) {
            @Override
            public void convert(BaseRecycleHolder helper, BeanRespFollow item, final int position) {
                    helper.setText(R.id.tv_name,item.getName());
                    helper.setOnClickListener(R.id.bt_following, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showFollowTip(position);
                        }
                    });
            }
        };
        recyclerView.setAdapter(recycleAdapter);

        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                data.clear();
                data.add(new BeanRespFollow("7777"));
                data.add(new BeanRespFollow("6666"));
                data.add(new BeanRespFollow("5555"));
                data.add(new BeanRespFollow("4444"));
                data.add(new BeanRespFollow("3333"));
                data.add(new BeanRespFollow("2222"));
                data.add(new BeanRespFollow("1111"));
                recycleAdapter.notifyDataSetChanged();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                data.add(new BeanRespFollow("1111"));
                data.add(new BeanRespFollow("2222"));
                data.add(new BeanRespFollow("3333"));
                data.add(new BeanRespFollow("4444"));
                data.add(new BeanRespFollow("5555"));
                data.add(new BeanRespFollow("6666"));
                data.add(new BeanRespFollow("7777"));
                recycleAdapter.notifyDataSetChanged();
            }
        });
    }

    public WindowManager.LayoutParams lp;
    private void  showFollowTip(final int position){
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        View contentView = LayoutInflater.from(oThis).inflate(R.layout.pop_bottom_tip, null);
        final PopupWindow popupWindow = new PopupWindow(contentView, width, height);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        Button bt_confirm = contentView.findViewById(R.id.bt_confirm);
        Button bt_cancel = contentView.findViewById(R.id.bt_cancel);

        bt_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(position);
                recycleAdapter.notifyDataSetChanged();
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
        popupWindow.showAtLocation(refreshLayout, Gravity.BOTTOM, 0, 0);
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
