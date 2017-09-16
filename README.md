# SSwipeRefreshLayout

如要了解功能实现,请运行app程序查看控制台日志和源代码!
* 源代码 : <a href="https://github.com/AcmenXD/SSwipeRefreshLayout">AcmenXD/SSwipeRefreshLayout</a>
* apk下载路径 : <a href="https://github.com/AcmenXD/Resource/blob/master/apks/SSwipeRefreshLayout.apk">SSwipeRefreshLayout.apk</a>

### 依赖
---
- AndroidStudio
```
	allprojects {
            repositories {
                ...
                maven { url 'https://jitpack.io' }
            }
	}
```
```
	com.github.AcmenXD:SSwipeRefreshLayout:1.0
```
### 功能
---
- 自定义下拉刷新 | 上拉加载 视图
- 支持侵入式 & 非侵入式模式

### 使用 -> 以下代码 注释很详细、很重要很重要很重要!!!
---
- xml布局
```java
// 定义下拉刷新控件SSwipeRefreshLayout 及 RecyclerView列表控件
<com.acmenxd.sswiperefreshlayout.SSwipeRefreshLayout
    android:id="@+id/srl"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

</com.acmenxd.sswiperefreshlayout.SSwipeRefreshLayout>
```
- 设置SSwipeRefreshLayout样式
```java
// 设置头部背景色
srl.setHeaderBackgroundColor(bgColor); 
// 设置为侵入式
srl.setTargetScrollWithLayout(true);
// 设置下拉视图
{
    // 初始化头部视图
    View view = LayoutInflater.from(context).inflate(R.layout.layout_sswiperefresh, null);
    final TextView tvContent = (TextView) view.findViewById(R.id.layout_header_tvContent);
    final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.layout_header_ivLoading);
    final ImageView ivArrow = (ImageView) view.findViewById(R.id.layout_header_ivArrow);
    tvContent.setTextColor(textColor);
    ivArrow.setImageResource(arrowResId);
    progressBar.setIndeterminateDrawable(context.getResources().getDrawable(progressResId));
    srl.setHeaderView(view);
    // 设置监听事件
    srl.setOnRefreshListener(new SSwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            tvContent.setText("正在刷新..");
            progressBar.setVisibility(View.VISIBLE);
            ivArrow.setVisibility(View.GONE);
            // 模拟刷新过程
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
    // 初始化底部视图
    View view = LayoutInflater.from(context).inflate(R.layout.layout_sswiperefresh, null);
    final TextView tvContent = (TextView) view.findViewById(R.id.layout_header_tvContent);
    final ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.layout_header_ivLoading);
    final ImageView ivArrow = (ImageView) view.findViewById(R.id.layout_header_ivArrow);
    tvContent.setTextColor(textColor);
    ivArrow.setImageResource(arrowResId);
    progressBar.setIndeterminateDrawable(context.getResources().getDrawable(progressResId));
    srl.setFooterView(view);
    // 设置监听事件
    srl.setOnLoadMoreListener(new SSwipeRefreshLayout.OnLoadMoreListener() {
        @Override
        public void onLoadMore() {
            tvContent.setText("正在加载..");
            progressBar.setVisibility(View.VISIBLE);
            ivArrow.setVisibility(View.GONE);
            // 模拟加载过程
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
```
### 打个小广告^_^
**gitHub** : https://github.com/AcmenXD   如对您有帮助,欢迎点Star支持,谢谢~

**技术博客** : http://blog.csdn.net/wxd_beijing
# END
