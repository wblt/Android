数据的存储


#1.测试的相关概念 (了解)
	
		SUV  好的软件不是开发出来的是测试出来的
	
		jd 黑客
		当当： -10 

		1.测试是否知道源代码
			黑盒测试  不知道代码
			白盒测试  知道代码
		2.按照测试的粒度
			方法测试
			单元测试 Junit
			集成测试
			系统测试
		3.按照测试的暴力程度
			冒烟测试  硬件
			压力测试 12306

		monkey测试： adb shell下的一个测试指令。 adb shell +　monkey -p packagename count;

#2.单元测试(了解，会用即可)
	1.创建一个类集成AndroidTestCase，那么该类就具备单元测试的功能。

	2.需要在androidmanifest.xml中的application节点下配置一个uses-library;
		<uses-library android:name="android.test.runner" />
	3.需要在androidmanifest.xml中的manifest节点下配置一个instrumentation;targetPackage：需要测试的工程的包名。
		    <instrumentation
        android:name="android.test.InstrumentationTestRunner"
        android:targetPackage="com.itheima.junit" />

	4.如果不知道怎么配置androidmanifest.xml,可以新建一个android test project工程，会自动配置.
#3.Logcat日志猫工具的使用 (会用即可)

		包括五种级别，可以添加过滤器过滤日志信息。能够帮助我们观察程序运行的状态。
		e:
		w:
		i:
		d:
		v:

		在公司开发中一般打印日志用Log类，通常会封装一个LogUtils，通过开关来控制日志信息的打印。
	
#4.把数据存储到文件(login案例)  android 下的数据存储 

	1.写布局
		LinearLayout + RelativeLayout
	2.写业务逻辑
		a.找到相应控件

		b.设置按钮的点击事件

		c.在onclick方法中，获取用户输入的用户名密码和是否记住密码

		d.判断用户名密码是否为空，不为空请求服务器（省略，默认请求成功）

		e.判断是否记住密码，如果记住，将用户名密码保存本地。???? 

		f.回显用户名密码 ??


		//通过context对象获取私有目录，/data/data/packagename/filse
		context.getFileDir().getPath()

	
#5.存储到SD卡,获取SD的大小及可用空间  （重点）
	
	使用Sdcard注意事项：

	1.权限问题：	
		<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
	2.硬性编码问题：通过 Environment可以获取sdcard的路径
		 Environment.getExternalStorageDirectory().getPath();
	3.使用前需要判断sdcard状态
			if(!Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED)){
					//sdcard状态是没有挂载的情况
					Toast.makeText(mContext, "sdcard不存在或未挂载", Toast.LENGTH_SHORT).show();
					return ;
				}
	4.需要判断sdcard剩余空间
					//判断sdcard存储空间是否满足文件的存储
				File sdcard_filedir = Environment.getExternalStorageDirectory();//得到sdcard的目录作为一个文件对象
				long usableSpace = sdcard_filedir.getUsableSpace();//获取文件目录对象剩余空间
				long totalSpace = sdcard_filedir.getTotalSpace();
				//将一个long类型的文件大小格式化成用户可以看懂的M，G字符串
				String usableSpace_str = Formatter.formatFileSize(mContext, usableSpace);
				String totalSpace_str = Formatter.formatFileSize(mContext, totalSpace);
				if(usableSpace < 1024 * 1024 * 200){//判断剩余空间是否小于200M
					Toast.makeText(mContext, "sdcard剩余空间不足,无法满足下载；剩余空间为："+usableSpace_str, Toast.LENGTH_SHORT).show();
					return ;	
				}


		/data/data: context.getFileDir().getPath();
					是一个应用程序的私有目录，只有当前应用程序有权限访问读写，其他应用无权限访问。一些安全性要求比较高的数据存放在该目录，一般用来存放size比较小的数据。
		/sdcard:  Enviroment.getExternalStorageDirectory().getPath();
					是一个外部存储目录，只用应用声明了<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>的一个权限，就可以访问读写sdcard目录；所以一般用来存放一些安全性不高的数据，文件size比较大的数据。
		
#7.文件的权限概念 (了解)

	//通过context对象获取一个私有目录的文件读取流  /data/data/packagename/files/userinfoi.txt
	FileInputStream fileInputStream = context.openFileInput("userinfo.txt");

	//通过context对象得到私有目录下一个文件写入流； name : 私有目录文件的名称    mode： 文件的操作模式， 私有，追加，全局读，全局写
		FileOutputStream fileOutputStream = context.openFileOutput("userinfo.txt", Context.MODE_PRIVATE);	



	linux下一个文件的权限由10位标示：
	1位：文件的类型，d：文件夹 l:快捷方式  -:文件
	2-4： 该文件所属用户对本文件的权限 ， rwx ：用二进制标示，如果不是-就用1标示，是-用0标示；chmod指令赋权限。
	5-7：该文件所属用户组对本文件的权限
	8-10：其他用户对该文件的权限。
	
#8.SharedPreferences介绍  (重点) 用来做数据存储

		sharedPreferences是通过xml文件来做数据存储的。
		一般用来存放一些标记性的数据，一些设置信息。


		*********使用sharedPreferences存储数据

				
			1.通过Context对象创建一个SharedPreference对象
				//name:sharedpreference文件的名称    mode:文件的操作模式
				SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo.txt", Context.MODE_PRIVATE);
			2.通过sharedPreferences对象获取一个Editor对象
				Editor editor = sharedPreferences.edit();
			3.往Editor中添加数据
				editor.putString("username", username);
				editor.putString("password", password);
			4.提交Editor对象
				editor.commit();

		*********使用sharedPreferences读取数据

			1.通过Context对象创建一个SharedPreference对象
				SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo.txt", Context.MODE_PRIVATE);
				
			2.通过sharedPreference获取存放的数据
				//key:存放数据时的key   defValue: 默认值,根据业务需求来写
				String username = sharedPreferences.getString("username", "");
				String password = sharedPreferences.getString("password", "");
				


		通过PreferenceManager可以获取一个默认的sharepreferences对象		
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
#9 生成xml的2种方式 

	
#10.使用pull解析xml格式的数据


