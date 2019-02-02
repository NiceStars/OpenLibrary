## OpenLibrarya有哪些功能？ ##

* 自定义描边View
* 仿DW表盘的表盘View
* 带渐变色边框的CircleImageView
* 优化版电商购物车加减按钮
* 新增圆形渐变进度控件

## 例子 ##

**样式**


<img src="https://github.com/NiceStars/OpenLibrary/blob/master/image/WechatIMG7.jpeg"/>
<img width="540" height="1130" src="https://github.com/NiceStars/OpenLibrary/blob/master/image/WechatIMG6.jpeg"/>


## 用法 ##
### 集成方法 ###
* Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
```
* Add the dependency
```
dependencies {
	        implementation 'com.github.NiceStars:OpenLibrary:V1.0.7'
	}
```

**自定义圆形渐变进度条view**

1.在XML文件设置好布局

```
<com.view.GradientProgressBarView
        android:id="@+id/view"
        android:layout_width="100dp"
        android:layout_height="100dp"
        android:layout_centerInParent="true"/>
```
2.Java代码里面设置必要参数

```
  view = findViewById(R.id.view);
        view.setBackgroundShader("#FFD0C4", "#FFECE6");
        view.setAngleShader("#FFBF8D", "#FF7753");
        view.setProgress((float) 0.4);
        view.setTextAndColor("#FF7753", "40%");
        view.setTextSize(15);
```


## 作者 ##
作者：nicestars.cn@gmail.com</br>作者：wangyujiew@sina.com
