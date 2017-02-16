##Android基础网络第二天

# 1 post方式提交数据乱码的解决

	一般在公司开发客户端和服务端的编码要保持一致。
	android端的默认编码是utf-8;

	做url请求时需要对参数进行URLEncode编码.

	URL url = new URL("http://192.168.13.83:8080/itheima74/servlet/LoginServlet?username="+URLEncoder.encode(username)+"&pwd="+URLEncoder.encode(password));
	

	connection.setDoOutput(true);
	connection.getOutputStream().write(parmes.getBytes());
	

# 2 get方式提交数据乱码解决
	
	URLEncode

# 3 httpclient方式提交数据到服务器

	 HttpClient:

	get方式:
			//使用HttpClient请求服务器将用户密码发送服务器验证
				try{
				String path = "http://192.168.13.83:8080/itheima74/servlet/LoginServlet?username="+URLEncoder.encode(username,"utf-8")+"&pwd="+URLEncoder.encode(password,"utf-8");
				//1.创建一个httpClient对象
				HttpClient httpclient = new DefaultHttpClient();
				
				//2.设置请求的方式
				HttpGet httpget = new HttpGet(path);
				//3.执行一个http请求
				HttpResponse response = httpclient.execute(httpget);
				//4.获取请求的状态码，
				StatusLine statusLine = response.getStatusLine();
				int code = statusLine.getStatusCode();
				
				//5.判断状态码后获取内容
				if(code == 200){
					HttpEntity entity = response.getEntity();//获取实体内容，中封装的有http请求返回的流信息
					InputStream inputStream = entity.getContent();
					//将流信息转换成字符串
					String result = StreamUtils.streamToString(inputStream);
					
					Message msg = Message.obtain();
					msg.what = 1;
					msg.obj = result;
					handler.sendMessage(msg);
				}
				
				}catch (Exception e) {
					e.printStackTrace();
				}
	post方式：


			//使用UrlConncetion请求服务器将用户密码发送服务器验证
				try{
						String path = "http://192.168.13.83:8080/itheima74/servlet/LoginServlet";
						//1.创建一个httpclient对象
						HttpClient httpclient = new DefaultHttpClient();
						//2.创建一个请求方式
						HttpPost httppost = new HttpPost(path);
						//创建集合封装数据
						ArrayList<BasicNameValuePair> arrayList = new ArrayList<BasicNameValuePair>();
						BasicNameValuePair nameValuePair = new BasicNameValuePair("username",username);
						arrayList.add(nameValuePair);
						BasicNameValuePair nameValuePair1 = new BasicNameValuePair("pwd",password);
						arrayList.add(nameValuePair1);
						
						//创建一个Entity
						UrlEncodedFormEntity entity = new UrlEncodedFormEntity(arrayList, "utf-8");
						//设置请求时的内容
						httppost.setEntity(entity);
						
						//3.执行一个请求,返回一个response对象
						HttpResponse response = httpclient.execute(httppost);
						//4.获取状态码
						int code = response.getStatusLine().getStatusCode();
						//5.判断并获取内容
						if(code == 200){
							HttpEntity entity1 = response.getEntity();//获取实体内容，中封装的有http请求返回的流信息
							InputStream inputStream = entity1.getContent();
							//将流信息转换成字符串
							String result = StreamUtils.streamToString(inputStream);
							Message msg = Message.obtain();
							msg.what = 2;
							msg.obj = result;
							handler.sendMessage(msg);
						}

				}catch (Exception e) {
					e.printStackTrace();
				}


						    
# 4开源项目get post 方式提交 (asyncHttpClient)  


		get方式：


					public static void requestNetForGetLogin(final Context context,final Handler handler ,final String username, final String password) {
				//使用HttpClient请求服务器将用户密码发送服务器验证
				try{
				String path = "http://192.168.13.83:8080/itheima74/servlet/LoginServlet?username="+URLEncoder.encode(username,"utf-8")+"&pwd="+URLEncoder.encode(password,"utf-8");
		
				//创建一个AsyncHttpClient对象
				AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
				asyncHttpClient.get(path, new AsyncHttpResponseHandler() {
					
					@Override
					public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
						//statusCode:状态码    headers：头信息  responseBody：返回的内容，返回的实体
						//判断状态码
						if(statusCode == 200){
							//获取结果
							try {
								String result = new String(responseBody,"utf-8");
								Toast.makeText(context, result, 0).show();
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					
					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						
						System.out.println("...............onFailure");
						
					}
				});
				}catch (Exception e) {
					e.printStackTrace();
				}
	}


		post方式：


					String path = "http://192.168.13.83:8080/itheima74/servlet/LoginServlet";
	
			AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
			RequestParams params = new RequestParams();
			params.put("username", username);
			params.put("pwd", password);
			
			//url:   parmas：请求时携带的参数信息   responseHandler：是一个匿名内部类接受成功过失败
			asyncHttpClient.post(path, params, new AsyncHttpResponseHandler() {
				
				@Override
				public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
					//statusCode:状态码    headers：头信息  responseBody：返回的内容，返回的实体
					
					//判断状态码
					if(statusCode == 200){
						//获取结果
						try {
							String result = new String(responseBody,"utf-8");
							Toast.makeText(context, result, 0).show();
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
				
				@Override
				public void onFailure(int statusCode, Header[] headers,
						byte[] responseBody, Throwable error) {
					
				}
			});

# 5 文件上传的操作 
		
		使用第三方utils做文件上传。
			
	public void fileupload(View v){
		try{
		EditText et_filepath = (EditText) findViewById(R.id.et_filepath);
		//获取输入的文件地址
		String filepath = et_filepath.getText().toString().trim();
		
		//使用开源Utils做上传操作
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		
		RequestParams params = new RequestParams();
		params.put("filename", new File(filepath));
		//url : 请求服务器的url
		asyncHttpClient.post("http://192.168.13.83:8080/itheima74/servlet/UploaderServlet", params, new AsyncHttpResponseHandler() {
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
				if(statusCode == 200){
					Toast.makeText(MainActivity.this, "上传成功", 0).show();
				}
			}
			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
			}
		});
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

# 6 多线程加速下载的原理

		1.线程越多下载越快？？ 不是。 与 本地网络带宽， 服务器资源的带宽 有关
		2.迅雷：3-5个。

		多线程下载的步骤：

		1.要知道服务端资源的大小。
		
			通过URLConnection请求服务器url获取。
			UrlConnection.getContentLength();//资源的大小
			
		2.在本地创建一个与服务端资源同样大小的一个文件（占位）
				//file :  文件； mode:文件的模式，rwd：直接写到底层设备，硬盘
				RandomAccessFile randomfile =new RandomAccessFile(File file,String mode)
					
				randomfile.setLength(long size);//创建一个文件和服务器资源一样大小
				
		3.要分配每个线程下载文件的开始位置和结束位置。
				
		4.开启线程去执行下载
				通过UrlConnection下载部分资源。
				注意：
				 1.需要Range头，key：Range   value：bytes:0-499 
						urlconnection.setRequestPropety("Range","bytes:0-499")
				 2.需要设置每个线程在本地文件的保存的开始位置
						RandomAccessFile randomfile =new RandomAccessFile(File file,String mode)
						randomfile.seek(int startPostion);//本次线程下载保存的开始位置。
						
		5.要知道每个线程下载完毕。

# 7 javase 多线程下载

# 8 多线程断点续传实现

# 9 Android版本多线程下载

			安智： sdcard没有判断。uc


# 10 开源项目实现多线程下载 (xutils)

		public void download(View v){
		EditText et_url = (EditText) findViewById(R.id.et_url);
		String url = et_url.getText().toString().trim();
		//1.创建httpUtils对象
		HttpUtils httpUtils = new HttpUtils();
		//2.调用download方法  url:下载的地址  target：下载的目录   callback：回调 
		httpUtils.download(url, "/sdcard/feiqiu/feiq.exe", new RequestCallBack<File>() {
			@Override
			public void onLoading(long total, long current, boolean isUploading) {
				System.out.println("total:"+total+";current:"+current);
				super.onLoading(total, current, isUploading);
			}
			@Override
			public void onSuccess(ResponseInfo<File> responseInfo) {
				System.out.println(responseInfo.result);
			}
			@Override
			public void onFailure(HttpException error, String msg) {
				// TODO Auto-generated method stub
				
			}
		});
	}

##  2天网络内容大复习





