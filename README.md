# SSwipeRefreshLayout

如要了解功能实现,请运行app程序查看控制台日志和源代码!
* 源代码 : <a href="https://github.com/AcmenXD/SSwipeRefreshLayout">AcmenXD/SSwipeRefreshLayout</a>
* apk下载路径 : <a href="https://github.com/AcmenXD/Resource/blob/master/apks/SSwipeRefreshLayout.apk">SSwipeRefreshLayout.apk</a>

![gif](https://github.com/AcmenXD/RecyclerView/blob/master/pic/1.gif)
![gif](https://github.com/AcmenXD/RecyclerView/blob/master/pic/2.gif)
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
	 // Android系统提供的recyclerview-v7包
	 compile 'com.android.support:recyclerview-v7:25.0.0'
	 compile 'com.github.AcmenXD:RecyclerView:2.1'
```
### 功能
---

####v1.0 支持功能如下
- 支持下拉刷新
- 支持LoadMore(上拉加载更多)
- 支持添加Header、Footer、Empty(头、尾、空)视图
- 支持一个Adapter自定义多种Item类型
- 简化RecyclerView.Adapter及ViewHolder的实现
- LoadMore 和 Empty支持点击回调
- Adapter链式调用，易读、易懂、易用
- 支持item事件：单击 & 长按 & 滑动删除 & 拖拽换位 & 侧滑菜单功能（事件无任何冲突）
- 此封装库未对RecyclerView进行任何更改,布局或代码中使用原生RecyclerView即可
### 使用 -> 以下代码 注释很详细、很重要很重要很重要!!!
---
- xml布局
```java
// 定义下拉刷新控件SwipeRefreshLayout 及 RecyclerView列表控件
<android.support.v4.widget.SwipeRefreshLayout
    android:id="@+id/srl"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"/>

</android.support.v4.widget.SwipeRefreshLayout>
```
### 打个小广告^_^
**gitHub** : https://github.com/AcmenXD   如对您有帮助,欢迎点Star支持,谢谢~

**技术博客** : http://blog.csdn.net/wxd_beijing
# END
