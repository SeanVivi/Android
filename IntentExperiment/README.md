# 实验四

## 获取URL地址并启动隐式Intent的调用

### 实验过程
在输入框中输入网址，获取输入的字符串，将字符串解析为Uri对象，然后将Uri传入setData，设置intent的Action
启动隐式Intent
'''
	//获取网址
	String url = test.getText().toString();
	Intent intent = new Intent();
	//将url字符串解析为uri对象
	Uri uri = Uri.parse(url);
	//设置data
	intent.setData(uri);
	//设置动作
	intent.setAction(Intent.ACTION_VIEW);
	startActivity(intent);
'''

### 结果
![image](https://github.com/SeanVivi/Android/blob/master/images/url_intent1.png)
![image](https://github.com/SeanVivi/Android/blob/master/images/url_intent2.png)

## 自定义WebView来加载URL
### 实验过程
自己定义WebView，然后修改MainActivity，创建一个URL对象，传入协议，主机名，路径，然后用WebView加载web资源，
覆盖默认使用第三方系统默认浏览器打开网页的行为，使网页用WebView打开。在打开网址的时候，选择浏览器

WebView
'''
	<WebView
		android:id="@+id/WV"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
	></WebView>
'''

'''
	webView = findViewById(R.id.WV);
	webView.getSettings().setJavaScriptEnabled(true);

	Intent intent = getIntent();
	Uri data = intent.getData();
	URL url = null;
	try{
		//创建一个URL对象，参数分别是协议，主机名，路径
		url = new URL(data.getScheme(), data.getHost(), data.getPath());
	}catch (Exception e){
		e.printStackTrace();
	}

	//WebView加载web资源
	webView.loadUrl(url.toString());
	//覆盖默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
	webView.setWebViewClient(new WebViewClient(){
		@Override
		public boolean shouldOverrideUrlLoading(WebView view,  String url) {
			view.loadUrl(url);
			return true;
		}
	});
'''

'''
	//选择浏览器
	switch (v.getId()){
		case R.id.bt:
		   Intent intent = new Intent(Intent.ACTION_VIEW);
		   intent.setData(Uri.parse(test.getText().toString()));
		   Intent choose = Intent.createChooser(intent,"使用一下方式打开");
		   startActivity(choose);
		   break;
	}
'''

### 在本题中遇到的问题
在设置完activity后，可能会遇到如下问题
![image](https://github.com/SeanVivi/Android/blob/master/images/mybrowser_intent3.png)

解决方法
![image](https://github.com/SeanVivi/Android/blob/master/images/mybrowser_intent4.png)


由于现在由于API升级，不允许明文的HTTP，所以调用的自定义webview浏览器的时候会出现页面错误
解决办法
在AndroidManifest中设置 android:usesCleartextTraffic="true"

### 结果
![image](https://github.com/SeanVivi/Android/blob/master/images/mybrowser_intent1.png)
![image](https://github.com/SeanVivi/Android/blob/master/images/mybrowser_intent2.png)



