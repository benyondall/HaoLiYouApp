package e.commerce.shopping.guide.main.ui.collection;

import android.graphics.Paint;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.codercf.loadinglayout.LoadingLayout;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import e.commerce.shopping.guide.R;
import e.commerce.shopping.guide.common.adapter.BaseRecycleAdapter;
import e.commerce.shopping.guide.common.adapter.BaseRecycleHolder;
import e.commerce.shopping.guide.main.ui.user.model.BeanRespRecentlyViewed;
import e.commerce.shopping.guide.mvp.BaseFragment;

/**
 * 第二页
 */
public class CollectionFragment extends BaseFragment<CollectionP> implements CollectionV {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.loading_layout)
    LoadingLayout loadingLayout;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    BottomNavigationView nav_view;
    BaseRecycleAdapter<BeanRespRecentlyViewed> recycleAdapter;
    List<BeanRespRecentlyViewed> data = new ArrayList<>();

    boolean isEdit;
    boolean isAllSelect;


    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.bt_select_type)
    Button btSelectType;
    @BindView(R.id.bt_delete)
    Button btDelete;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;

    @Override
    protected void init(int layoutId, Class<CollectionP> clazz) throws Exception {
        super.init(R.layout.fragment_collection, CollectionP.class);
//        setTitle("Recently Viewed");
        loadingLayout.showEmpty();
        nav_view = getActivity().findViewById(R.id.nav_view);
//        data.add(new BeanRespRecentlyViewed("1111"));
//        data.add(new BeanRespRecentlyViewed("2222"));
//        data.add(new BeanRespRecentlyViewed("3333"));
//        data.add(new BeanRespRecentlyViewed("4444"));
//        data.add(new BeanRespRecentlyViewed("5555"));
//        data.add(new BeanRespRecentlyViewed("6666"));
//        data.add(new BeanRespRecentlyViewed("7777"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(oThis);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(oThis, DividerItemDecoration.VERTICAL));
        recycleAdapter = new BaseRecycleAdapter<BeanRespRecentlyViewed>(oThis, R.layout.item_collection, data) {
            @Override
            public void convert(BaseRecycleHolder helper, final BeanRespRecentlyViewed item, final int position) {
                final RadioButton rb_edit = helper.getView(R.id.rb_edit);
                if (isEdit) {
                    rb_edit.setVisibility(View.VISIBLE);
                    if (isAllSelect){
                        rb_edit.setChecked(true);
                    }else{
                        rb_edit.setChecked(item.isChecked());
                    }



                } else {
                    rb_edit.setVisibility(View.GONE);
                }

                rb_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.isChecked()) {
                            item.setChecked(false);
                            rb_edit.setChecked(false);
                            isAllSelect = false;
                            btSelectType.setText("Select all");
                        } else {
                            item.setChecked(true);
                            rb_edit.setChecked(true);
                        }
                    }
                });
                helper.setText(R.id.tv_name, item.getName());
                TextView tv_money_other = helper.getView(R.id.tv_money_other);
                tv_money_other.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG); //中划线

            }
        };

        recycleAdapter.setOnItemClickListener(new BaseRecycleAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position) {
                if (!isEdit) {//编辑状态不生效

                }
            }
        });

        recyclerView.setAdapter(recycleAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadingLayout.showMain();
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
                loadingLayout.showMain();
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


    @OnClick({R.id.iv_back, R.id.tv_edit, R.id.bt_select_type, R.id.bt_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                //TODO back to homefragment
                break;
            case R.id.tv_edit:
                isEdit = !isEdit;
                recycleAdapter.notifyDataSetChanged();
                if (isEdit) {
                    tvEdit.setText("Finish");
                    nav_view.setVisibility(View.GONE);
                    llBottom.setVisibility(View.VISIBLE);
                } else {
                    tvEdit.setText("Edit");
                    nav_view.setVisibility(View.VISIBLE);
                    llBottom.setVisibility(View.GONE);
                }
                break;
            case R.id.bt_select_type:
                setBtSelectType();
                break;
            case R.id.bt_delete:


                break;
        }
    }


    private void setBtSelectType(){

        if (isAllSelect){
            btSelectType.setText("Select all");
        }else{
            btSelectType.setText("Select none");
        }
        isAllSelect = !isAllSelect;
        for (int i = 0; i < data.size(); i++) {
            data.get(i).setChecked(isAllSelect);
        }
        recycleAdapter.notifyDataSetChanged();
    }
}