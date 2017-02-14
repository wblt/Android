

#1.1G-4G
	g:generation 代 3g：第三代移动通信技术

	1g: 大哥大
		特点：安全性差，容易受干扰，通话不稳定，不能发短信
	2g:小灵通等功能机
		特点：通话质量稳定，可以发短信彩信，简单wap上网，支持一些简单的java游戏
	3g:android ios 等智能手机
		特点：上网快了很多,能够处理图像，音乐，视频流等多种媒体形式.
	4g: lte , long time evolution
		特点： 上网速度更快，100M带宽。

	 区别：网速不同，处理的内容不同
	


#2.Android操作系统介绍 

	 android系统是由安迪鲁宾团队开发的，最初用于数码相机，2005.08被google收购

	android名字是因为安迪鲁宾喜欢一个游戏的人物--大瓢虫

	android图标：上厕所的灵感

	android应用范围：手机，平板，智能家居，穿戴设备。



#3.Android进化史
		
	2.3 比较稳定的一个版本 ，NFC 近场通信技术

	3.0 专为平板设计 

	4.1.2 	4.0后比较稳定的版本，4.*同时支持平板和手机

	5.0   新特性


#4.Android系统架构（重点）

	分层的架构

	JNI java native interface 
	1.application :应用层 ； java
	2.application framework :应用框架层  ， java+ＪＮＩ
	3.libraries 和 dalvik ： 函数库和虚拟机层，  c/c++ 
	4.linux kernel : linux 内核驱动层， c


#5.两种虚拟机的不同 (熟练了解)

	版权问题：
	jvm ： java虚拟机 sun
	dvm：  dalvik虚拟机  google

	区别：
		1.基于的架构不同，jvm 基于栈架构，栈是位于内存上的一个空间，执行指令操作，需要向cpu寻址； dvm 基于寄存器架构，寄存器是cpu的一个组成部分，执行指令操作无需寻址直接执行。
		2.执行文件的格式不同，jvm执行的是多个.class文件。 dvm执行的是一个.dex文件
	

#6.art 模式  android runtime
		空间换时间的概念。

		art:程序在安装时需要预编译读取，将代码转换为机器码，好处:程序运行时，无需时时转换，运行速度快 ； 缺点：安装时间稍长，由于转换机器码，所以占用略高的存储空间。

#7.开发环境的搭建 

	1.JDK 32 64
	2.开发工具，eclipse ， android studio
	3.android sdk , sdk: soft developer kit 

	adt : android develper tool  bundle:集


	apilevel : 19     4.4版本
			   18     4.3
			   11     3.0
			   10     2.3
				8     2.2


	aapt:android application package tool
	adb :  建立电脑与手机之间的链接
	dx.bat : 将多个.class 打包成一个.dex
	

	sdk下的目录：
	 
	add-ons:预留的一个附加目录
	build-tools:构建工具目录
	*****docs: 文档目录
	extras：开发中额外提供的一些工具及jar
	*****platforms: 不同版本android的核心jar包
	*****platforms-tools：平台一些相关的工具
	*****sources：源码
	system-images：系统镜像文件
	tools：开发中使用的一些工具，如9path，做图片拉伸适配的。

#8.Android SDKManager介绍



#9.模拟器的简介及创建 
	
	常用的屏幕分辨率：
	
	3.2  ----- QVGA ------320*480

	3.7 ----- WVGA ------480*800

	4.7 -----WXGA ------1280*720

#10.DDMS介绍
	
	ddms： dalvik debug manitor services		

	devices: 列出当前电脑所连接的所有android设备，及android设备运行的进程，结束一个进程，设置程序为debug模式，截屏。

	logcat: 会打印系统运行过程中所有日志信息。

	file explorer: 列出当前设备所有目录。
		/data/app:安装的第三方apk都在此目录
		 /system/app: 系统预装应用apk在此目录  
		/data/data:应用的私有目录，系统每安装一个新的应用程序，都会在此目录创建该应用包名的文件，用来存放该应用的私有数据，当应用卸载时，该包名的文件夹也会被删除。  	
		/sdcard :外部存储目录，一般会链接指向到另一个目录，用来存放大数据。
	
#11.创建HelloWorld工程

	部署运行的三种方式：

	１.右击工程，run as
	2.工具栏的按钮
	3.快捷键：ctrl+F11

#12.android工程目录结构

	img: ../img/a.jpg


	src: java 源码代码
	gen： 自动生成的文件目录，不需要修改； R类 ，是对资源文件的一个索引
	android核心jar包和第三方jar包
	assets：资产目录，用来存放程序运行过程中所需要的一些工具，数据库
	bin:编译打包过程中产生的目录
	libs:
	res:
			drawable:图片资源
			layout：布局资源
			menu：菜单资源
			values： demins 长度相关， string:字符串  style 样式
	androidManifest.xml:  清单文件， 包名，版本号，版本名称，最低运行版本，图标，应用名称，程序的入口activity, 还可以配置应用程序使用的权限信息。

#13.Android的打包过程 
			jdk			 dx.bat										aapt        签名jarsigner
	.java -----> .class ------>.dex(res,assets,androidmanifest.xml)------->.apk--------->final apk
	
#14.ADB指令练习  (重点) 

	ADB :android debug bridge 建立手机与电脑直接的连接  adb运行的端口号是5037

	环境变量的配置：C:\kaifa\adt-bundle-windows-x86_64_20140101\sdk\platform-tools

	1.adb devices :列出当前电脑所连接的android设备
	2.adb push pc_path  phone_path :将电脑端文件放到手机端
	3.adb pull phone_paht pc_path :将手机端文件拉到电脑端
	4.adb install [-r] apkpath ; 安装一个电脑端的apk文件。-r：强制安装
	5.adb uninstall packagename; 卸载一个应用
	
	
	//三个指令联合使用来解决adb被占用，或断开连接的情况
	6.adb kill-server : 结束adb服务的链接
	7.adb start-server ：开启adb服务的链接
	8.netstat -oan 查看端口: 查看端口  
	

	9.adb shell：进入当前设备linux环境下

	10.adb shell + ls -l ：查看当前设备的目录结构
	11.adb shell+ logcat :查看系统运行中的日志信息
	
	注意： 如果当前电脑链接的是多台android设备，需要指定操作的是哪台设备，需要在adb后加 -s 设备序列号。
	


#15.电话拨号器（重点）
		
	1.产品经理： 需求分析文档，设计原型图
	2.UI工程师： 设计UI界面
	3.架构师： 写架构，接口文档
	4.码农： 服务端，客户端
			1.写布局界面
			2.写业务逻辑

				1.通过布局文件中对控件配置的id，在activity中可以获取控件的对象，Edittext Button
					findViewById(int id);
				2.为按钮设置点击事件
					bt_callphone.setOnclickListener( OnclickListener listener);
					
				3.在点击事件的onclick方法中，找到用户输入的电话号码

				4.创建一个Intent对象
						Intent intent = new Intent();
				5.为Intent对象设置一个打电话的动作
						intent.setAciton(Intent.ACTION_CALL);
				6.位Intent对象设置一个数据
						intent.setData(Uri.parser("tel:"+number));
				7.启动Intent对象
						startActivity(intent);
				8.在androidmanifest.xml中设置一个打电话的权限
					 <uses-permission android:name="android.permission.CALL_PHONE"/>

	5.测试工程师： 测试应用
	6.运营人员： 写软文，上传应用
	7.商务合作： 买量，买广告位,卖广告位

	
#16.四种方法写按钮点击事件 

		1.匿名内部类的方式
		2. 创建一个类实现onclickListener，实现onclick方法，设置控件点击事件时传一个类的对象。
		3. 让当前类实现onclickListener，设置控件点击事件时传一个this。这种方式适合按钮比较多的情况，一般在公司采用该方式。
		4. 在布局文件中为控件添加一个onclick属性，在布局对应的Activity中写一个一onclick属性值为名的方法，要public,传一个View类型的参数。比较适合做简单的测试。



#17.Android中常用布局 (多练习) table div+css
	
	1.线性布局  LinearLayout
		orientation:方向；vertical，垂直；horizontal,水平
		gravity:对齐方式，子控件相对于当前控件的对齐方式
		layout_gravity：当前控件相对于父控件的对齐方式
		margin:当前控件相对于四周的间距。
		padding：当前控件中的子控件相对于当前控件四周的间距。

	2.相对布局  RelativeLayout 都是从左上角开始布局，要控制位置需要设置每个控件相对于其他控件的位置。
		layout_below:位于哪个控件的下方
		layout_above:位于哪个控件的上方
		layout_toLeftOf:指定当前控件位于哪个控件的左边
		layout_toRightOf：指定当前控件位于哪个控件的右边
		layout_alignParentRight:当前控件基于父窗体的对其方式
		layout_centerHorizontal:水平居中
		layout_centerVertical：垂直居中
		layout_centerInParent:位于父窗体的中间
		
 
	3.帧布局  FrameLayout

		帧布局中的子控件都是一层一层向上叠加的。

	4.表格布局  TableLayouts
			TableLayout 中一个TableRow代表一行，TableRow中的一个控件代表一列 
	5.绝对布局（被抛弃） AbsoluteLayout