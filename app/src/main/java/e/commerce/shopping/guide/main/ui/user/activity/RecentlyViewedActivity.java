package e.commerce.shopping.guide.main.ui.user.activity;

import android.graphics.Paint;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
import e.commerce.shopping.guide.main.ui.user.model.BeanRespRecentlyViewed;
import e.commerce.shopping.guide.main.ui.user.presenter.RecentlyViewedP;
import e.commerce.shopping.guide.main.ui.user.view.RecentlyViewedV;
import e.commerce.shopping.guide.mvp.BaseActivity;

public class RecentlyViewedActivity extends BaseActivity<RecentlyViewedP> implements RecentlyViewedV {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    BaseRecycleAdapter<BeanRespRecentlyViewed> recycleAdapter;
    List<BeanRespRecentlyViewed> data = new ArrayList<>();
    @Override
    protected void init(int layoutId, Class<RecentlyViewedP> clazz) throws Exception {
        super.init(R.layout.activity_recently_viewed, RecentlyViewedP.class);
        setTitle("Recently Viewed");

        data.add(new BeanRespRecentlyViewed("1111"));
        data.add(new BeanRespRecentlyViewed("2222"));
        data.add(new BeanRespRecentlyViewed("3333"));
        data.add(new BeanRespRecentlyViewed("4444"));
        data.add(new BeanRespRecentlyViewed("5555"));
        data.add(new BeanRespRecentlyViewed("6666"));
        data.add(new BeanRespRecentlyViewed("7777"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(oThis);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycleAdapter = new BaseRecycleAdapter<BeanRespRecentlyViewed>(oThis,R.layout.item_recently_viewed,data) {
            @Override
            public void convert(BaseRecycleHolder helper, BeanRespRecentlyViewed item, final int position) {
                helper.setText(R.id.tv_name,item.getName());
                TextView tv_money_other = helper.getView(R.id.tv_money_other);
                tv_money_other.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG); //中划线
            }
        };

        recycleAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {

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
                data.add(new BeanRespRecentlyViewed("7777"));
                data.add(new BeanRespRecentlyViewed("6666"));
                data.add(new BeanRespRecentlyViewed("5555"));
                data.add(new BeanRespRecentlyViewed("4444"));
                data.add(new BeanRespRecentlyViewed("3333"));
                data.add(new BeanRespRecentlyViewed("2222"));
                data.add(new BeanRespRecentlyViewed("1111"));
                recycleAdapter.notifyDataSetChanged();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshLayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
                data.add(new BeanRespRecentlyViewed("1111"));
                data.add(new BeanRespRecentlyViewed("2222"));
                data.add(new BeanRespRecentlyViewed("3333"));
                data.add(new BeanRespRecentlyViewed("4444"));
                data.add(new BeanRespRecentlyViewed("5555"));
                data.add(new BeanRespRecentlyViewed("6666"));
                data.add(new BeanRespRecentlyViewed("7777"));
                recycleAdapter.notifyDataSetChanged();
            }
        });
    }




}
