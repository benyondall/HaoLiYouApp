package e.commerce.shopping.guide.main.ui.user.activity;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.codercf.loadinglayout.LoadingLayout;
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
import e.commerce.shopping.guide.main.ui.user.model.BeanRespNotification;
import e.commerce.shopping.guide.main.ui.user.presenter.FollowP;
import e.commerce.shopping.guide.main.ui.user.view.FollowV;
import e.commerce.shopping.guide.mvp.BaseActivity;

public class NotificationActivity extends BaseActivity<FollowP> implements FollowV {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.loading_layout)
    LoadingLayout loadingLayout;
    BaseRecycleAdapter<BeanRespNotification> recycleAdapter;
    List<BeanRespNotification> data = new ArrayList<>();

    @Override
    protected void init(int layoutId, Class<FollowP> clazz) throws Exception {
        super.init(R.layout.activity_notification, FollowP.class);
        setTitle("Notification");
//        data.add(new BeanRespNotification("1111"));
//        data.add(new BeanRespNotification("2222"));
//        data.add(new BeanRespNotification("3333"));
//        data.add(new BeanRespNotification("4444"));
//        data.add(new BeanRespNotification("5555"));
//        data.add(new BeanRespNotification("6666"));
//        data.add(new BeanRespNotification("7777"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(oThis);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycleAdapter = new BaseRecycleAdapter<BeanRespNotification>(oThis,R.layout.item_notification,data) {
            @Override
            public void convert(BaseRecycleHolder helper, BeanRespNotification item, final int position) {
                    helper.setText(R.id.tv_content,"\""+item.getContent()+"\"");
                    if (data.get(position).getIsRead()){
                        change(helper.getView(R.id.iv_head),0.5f);
                        helper.setTextColor(R.id.tv_name,getResources().getColor(R.color.content_pale));
                        helper.setTextColor(R.id.tv_replied,getResources().getColor(R.color.content_pale));
                        helper.setTextColor(R.id.tv_time,getResources().getColor(R.color.content_pale));
                        helper.setTextColor(R.id.tv_content,getResources().getColor(R.color.content_pale));
                    }else{
                        change(helper.getView(R.id.iv_head),1f);
                        helper.setTextColor(R.id.tv_name,getResources().getColor(R.color.title));
                        helper.setTextColor(R.id.tv_replied,getResources().getColor(R.color.title));
                        helper.setTextColor(R.id.tv_time,getResources().getColor(R.color.title));
                        helper.setTextColor(R.id.tv_content,getResources().getColor(R.color.title));
                    }
            }
        };

        recycleAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                data.get(position).setIsRead(true);
                recycleAdapter.notifyDataSetChanged();
                //TODO post info and intent to activity
            }
        });

        recyclerView.setAdapter(recycleAdapter);

        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                data.clear();
                data.add(new BeanRespNotification("7777"));
                data.add(new BeanRespNotification("6666"));
                data.add(new BeanRespNotification("5555"));
                data.add(new BeanRespNotification("4444"));
                data.add(new BeanRespNotification("3333"));
                data.add(new BeanRespNotification("2222"));
                data.add(new BeanRespNotification("1111"));
                recycleAdapter.notifyDataSetChanged();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                data.add(new BeanRespNotification("1111"));
                data.add(new BeanRespNotification("2222"));
                data.add(new BeanRespNotification("3333"));
                data.add(new BeanRespNotification("4444"));
                data.add(new BeanRespNotification("5555"));
                data.add(new BeanRespNotification("6666"));
                data.add(new BeanRespNotification("7777"));
                recycleAdapter.notifyDataSetChanged();
            }
        });
    }


    private void change(View view,float sat){
        Paint paint = new Paint();
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(sat);
        paint.setColorFilter( new ColorMatrixColorFilter(colorMatrix));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, paint);
    }
}
