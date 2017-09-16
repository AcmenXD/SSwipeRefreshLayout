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

####v1.0 支持功能如下
- 自定义下拉刷新 | 上拉加载 视图
- 支持侵入式 & 非侵入式模式

### 使用 -> 以下代码 注释很详细、很重要很重要很重要!!!
---
- xml布局
```java
// 定义下拉刷新控件SwipeRefreshLayout 及 RecyclerView列表控件
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
### 打个小广告^_^
**gitHub** : https://github.com/AcmenXD   如对您有帮助,欢迎点Star支持,谢谢~

**技术博客** : http://blog.csdn.net/wxd_beijing
# END
