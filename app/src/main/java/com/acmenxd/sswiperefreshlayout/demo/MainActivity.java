package com.acmenxd.sswiperefreshlayout.demo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.acmenxd.sswiperefreshlayout.SSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private SSwipeRefreshLayout srl;
    private RecyclerView rv;
    private MyAdapter mAdapter;
    private List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("SSwipeRefreshLayout示例");
        setContentView(R.layout.activity_main);

        srl = (SSwipeRefreshLayout) findViewById(R.id.srl);
        rv = (RecyclerView) findViewById(R.id.rv);

        // 设置SSwipeRefreshLayout样式
        setSSwipeRefreshLayoutStyle(this, srl, 0xfff2f2f2, 0xff000000, R.drawable.progress_indeterminate, R.drawable.down_arrow_black);

        // 设置数据
        refreshDatas();

        //设置布局管理器
        LinearLayoutManager linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(OrientationHelper.VERTICAL);
        rv.setLayoutManager(linearManager);
        //设置增加或删除条目的动画
        rv.setItemAnimator(new DefaultItemAnimator());
        //添加分割线
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //设置adapter
        mAdapter = new MyAdapter(this, datas);
        rv.setAdapter(mAdapter);
    }

    /**
     * 刷新数据
     */
    public void refreshDatas() {
        datas.clear();
        for (int i = 0; i < 10; i++) {
            datas.add("当前位置:" + (i + 1));
        }
    }

    /**
     * 添加数据
     */
    public void addDatas() {
        int count = datas.size();
        for (int i = count; i < count + 10; i++) {
            datas.add("当前位置:" + (i + 1));
        }
    }

    /**
     * 设置SSwipeRefreshLayout样式
     */
    public void setSSwipeRefreshLayoutStyle(Context context, final SSwipeRefreshLayout srl, int bgColor, int textColor, int progressResId, int arrowResId) {
        srl.setHeaderBackgroundColor(bgColor);
        srl.setTargetScrollWithLayout(true);
        // 设置下拉视图
        {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_sswiperefresh, null);
            final TextView tvContent = (TextView) view.findViewById(R.id.layout_header_tvContent);
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.layout_header_ivLoading);
            final ImageView ivArrow = (ImageView) view.findViewById(R.id.layout_header_ivArrow);
            tvContent.setTextColor(textColor);
            ivArrow.setImageResource(arrowResId);
            progressBar.setIndeterminateDrawable(context.getResources().getDrawable(progressResId));
            srl.setHeaderView(view);
            srl.setOnRefreshListener(new SSwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    tvContent.setText("正在刷新..");
                    progressBar.setVisibility(View.VISIBLE);
                    ivArrow.setVisibility(View.GONE);
                    // 刷新
                    srl.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refreshDatas();
                            mAdapter.notifyDataSetChanged();
                            srl.setRefreshing(false);
                            Toast.makeText(MainActivity.this, "刷新成功", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }

                @Override
                public void onReach(boolean enable) {
                    tvContent.setText(enable ? "松开立即刷新" : "下拉可以刷新");
                    progressBar.setVisibility(View.GONE);
                    ivArrow.setVisibility(View.VISIBLE);
                    ivArrow.setRotation(enable ? 180 : 0);
                }

                @Override
                public void onPullDistance(int distance) {
                }
            });
        }
        // 设置上拉视图
        {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_sswiperefresh, null);
            final TextView tvContent = (TextView) view.findViewById(R.id.layout_header_tvContent);
            final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.layout_header_ivLoading);
            final ImageView ivArrow = (ImageView) view.findViewById(R.id.layout_header_ivArrow);
            tvContent.setTextColor(textColor);
            ivArrow.setImageResource(arrowResId);
            progressBar.setIndeterminateDrawable(context.getResources().getDrawable(progressResId));
            srl.setFooterView(view);
            srl.setOnLoadMoreListener(new SSwipeRefreshLayout.OnLoadMoreListener() {
                @Override
                public void onLoadMore() {
                    tvContent.setText("正在加载..");
                    progressBar.setVisibility(View.VISIBLE);
                    ivArrow.setVisibility(View.GONE);
                    // 加载
                    srl.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            addDatas();
                            mAdapter.notifyDataSetChanged();
                            srl.setLoadMore(false);
                            Toast.makeText(MainActivity.this, "加载成功", Toast.LENGTH_SHORT).show();
                        }
                    }, 2000);
                }

                @Override
                public void onReach(boolean enable) {
                    tvContent.setText(enable ? "松开立即加载" : "上拉可以加载");
                    progressBar.setVisibility(View.GONE);
                    ivArrow.setVisibility(View.VISIBLE);
                    ivArrow.setRotation(enable ? 0 : 180);
                }

                @Override
                public void onPushDistance(int distance) {
                }
            });
        }
    }

    /**
     * 自定义Adapter
     */
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private List<String> mDatas;
        private Context mContext;

        public MyAdapter(Context context, List<String> datas) {
            this.mContext = context;
            this.mDatas = datas;
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.tv.setText(mDatas.get(position));
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.activity_recycler_item, parent, false);
            return new MyViewHolder(view);
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView tv;

            public MyViewHolder(View view) {
                super(view);
                tv = (TextView) view.findViewById(R.id.activity_recycler_item_tv);
            }
        }
    }

}
