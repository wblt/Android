## Android基础第三天


# 1 Android下数据库创建 
	
	什么情况下我们才用数据库做数据存储？ 大量数据结构相同的数据需要存储时。
	mysql sqlserver2000  sqlite 嵌入式 轻量级

	SqliteOpenHelper

	创建数据库步骤：
	1.创建一个类集成SqliteOpenHelper，需要添加一个构造方法，实现两个方法oncreate ,onupgrade
		构造方法中的参数介绍：

		//context :上下文   ， name：数据库文件的名称    factory：用来创建cursor对象，默认为null 
		//version:数据库的版本号，从1开始，如果发生改变，onUpgrade方法将会调用,4.0之后只能升不能将
		super(context, "info.db", null,1);
		

	2.创建这个帮助类的一个对象，调用getReadableDatabase()方法，会帮助我们创建打开一个数据库

	3.复写oncreate和onupgrdate方法：
		oncreate方法是数据库第一次创建的时候会被调用;  特别适合做表结构的初始化,需要执行sql语句；SQLiteDatabase db可以用来执行sql语句
		
		//onUpgrade数据库版本号发生改变时才会执行； 特别适合做表结构的修改



	帮助类对象中的getWritableDatabase 和 getReadableDatabase都可以帮助我们获取一个数据库操作对象SqliteDatabase.

	区别：
	getReadableDatabase:
		先尝试以读写方式打开数据库，如果磁盘空间满了，他会重新尝试以只读方式打开数据库。
	getWritableDatabase:
		直接以读写方式打开数据库，如果磁盘空间满了，就直接报错。
# 2 Android下数据库第一种方式增删改查


	1.创建一个帮助类的对象，调用getReadableDatabase方法，返回一个SqliteDatebase对象

	2.使用SqliteDatebase对象调用execSql()做增删改,调用rawQuery方法做查询。

	******特点:增删改没有返回值，不能判断sql语句是否执行成功。sql语句手动写，容易写错



	private MySqliteOpenHelper mySqliteOpenHelper;
	public InfoDao(Context context){
		//创建一个帮助类对象
		mySqliteOpenHelper = new MySqliteOpenHelper(context);

		
	}

	public void add(InfoBean bean){

		//执行sql语句需要sqliteDatabase对象
		//调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase 	db = mySqliteOpenHelper.getReadableDatabase();
		//sql:sql语句，  bindArgs：sql语句中占位符的值
		db.execSQL("insert into info(name,phone) values(?,?);", new Object[]{bean.name,bean.phone});
		//关闭数据库对象
		db.close();
	}

	public void del(String name){


		//执行sql语句需要sqliteDatabase对象
		//调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
		//sql:sql语句，  bindArgs：sql语句中占位符的值
		db.execSQL("delete from info where name=?;", new Object[]{name});
		//关闭数据库对象
		db.close();

	}
	public void update(InfoBean bean){

		//执行sql语句需要sqliteDatabase对象
		//调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
		//sql:sql语句，  bindArgs：sql语句中占位符的值
		db.execSQL("update info set phone=? where name=?;", new Object[]{bean.phone,bean.name});
		//关闭数据库对象
		db.close();

	}
	public void query(String name){
		
		//执行sql语句需要sqliteDatabase对象
		//调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
		//sql:sql语句，  selectionArgs:查询条件占位符的值,返回一个cursor对象
		Cursor cursor = db.rawQuery("select _id, name,phone from info where name = ?", new String []{name});
		//解析Cursor中的数据
		if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据
			
			//循环遍历结果集，获取每一行的内容
			while(cursor.moveToNext()){//条件，游标能否定位到下一行
				//获取数据
				int id = cursor.getInt(0);
				String name_str = cursor.getString(1);
				String phone = cursor.getString(2);
				System.out.println("_id:"+id+";name:"+name_str+";phone:"+phone);
			}
			cursor.close();//关闭结果集
			
		}
		//关闭数据库对象
		db.close();

	}
	
# 3 Android下另外一种增删改查方式 
	
	1.创建一个帮助类的对象，调用getReadableDatabase方法，返回一个SqliteDatebase对象

	2.使用SqliteDatebase对象调用insert,update,delete ,query方法做增删改查。

	******特点:增删改有了返回值，可以判断sql语句是否执行成功，但是查询不够灵活，不能做多表查询。所以在公司一般人增删改喜欢用第二种方式，查询用第一种方式。

			private MySqliteOpenHelper mySqliteOpenHelper;
	public InfoDao(Context context){
		//创建一个帮助类对象
		mySqliteOpenHelper = new MySqliteOpenHelper(context);
	}

	public boolean add(InfoBean bean){

		//执行sql语句需要sqliteDatabase对象
		//调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase 	db = mySqliteOpenHelper.getReadableDatabase();
		
		
		ContentValues values = new ContentValues();//是用map封装的对象，用来存放值
		values.put("name", bean.name);
		values.put("phone", bean.phone);
		
		//table: 表名 , nullColumnHack：可以为空，标示添加一个空行, values:数据一行的值 , 返回值：代表添加这个新行的Id ，-1代表添加失败
		long result = db.insert("info", null, values);//底层是在拼装sql语句
	
		//关闭数据库对象
		db.close();
		
		if(result != -1){//-1代表添加失败
			return true;
		}else{
			return false;
		}
	}

	public int del(String name){

		//执行sql语句需要sqliteDatabase对象
		//调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
		
		//table ：表名, whereClause: 删除条件, whereArgs：条件的占位符的参数 ; 返回值：成功删除多少行
		int result = db.delete("info", "name = ?", new String[]{name});
		//关闭数据库对象
		db.close();
		
		return result;

	}
	public int update(InfoBean bean){

		//执行sql语句需要sqliteDatabase对象
		//调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
		ContentValues values = new ContentValues();//是用map封装的对象，用来存放值
		values.put("phone", bean.phone);
		//table:表名, values：更新的值, whereClause:更新的条件, whereArgs：更新条件的占位符的值,返回值：成功修改多少行
		int result = db.update("info", values, "name = ?", new String[]{bean.name});
		//关闭数据库对象
		db.close();
		return result;

	}
	public void query(String name){
	
		//执行sql语句需要sqliteDatabase对象
		//调用getReadableDatabase方法,来初始化数据库的创建
		SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
		
		//table:表名, columns：查询的列名,如果null代表查询所有列； selection:查询条件, selectionArgs：条件占位符的参数值,
		//groupBy:按什么字段分组, having:分组的条件, orderBy:按什么字段排序
		Cursor cursor = db.query("info", new String[]{"_id","name","phone"}, "name = ?", new String[]{name}, null, null, "_id desc");
		//解析Cursor中的数据
		if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据
			
			//循环遍历结果集，获取每一行的内容
			while(cursor.moveToNext()){//条件，游标能否定位到下一行
				//获取数据
				int id = cursor.getInt(0);
				String name_str = cursor.getString(1);
				String phone = cursor.getString(2);
				System.out.println("_id:"+id+";name:"+name_str+";phone:"+phone);
				
				
			}
			cursor.close();//关闭结果集
			
		}
		//关闭数据库对象
		db.close();

	}

		

# 4 数据库的事务 
		
	事务： 执行多条sql语句，要么同时执行成功，要么同时执行失败，不能有的成功，有的失败

	银行转账


	//点击按钮执行该方法
	public void transtation(View v){
		//1.创建一个帮助类的对象
		BankOpenHelper bankOpenHelper = new BankOpenHelper(this);
		//2.调用数据库帮助类对象的getReadableDatabase创建数据库，初始化表数据，获取一个SqliteDatabase对象去做转账（sql语句）
		SQLiteDatabase db = bankOpenHelper.getReadableDatabase();
		//3.转账,将李四的钱减200，张三加200
		db.beginTransaction();//开启一个数据库事务
		try {
			db.execSQL("update account set money= money-200 where name=?",new String[]{"李四"});
			int i = 100/0;//模拟一个异常
			db.execSQL("update account set money= money+200 where name=?",new String[]{"张三"});

			db.setTransactionSuccessful();//标记事务中的sql语句全部成功执行
		} finally {
			db.endTransaction();//判断事务的标记是否成功，如果不成功，回滚错误之前执行的sql语句 
		}
	}


# 5 listview 入门
		
		ListView 是一个控件,一个在垂直滚动的列表中显示条目的一个控件，这些条目的内容来自于一个ListAdapter 。EditText Button TextView ImageView Checkbox 五大布局。


		1.布局添加Listview
		
		2.找到listview

		3.创建一个Adapter适配器继承BaseAdapter，封装4个方法，其中getcount,getview必须封装
			getcount:告诉listview要显示的条目数
			getview：告诉listview每个条目显示的内容。
		4.创建Adapter的一个对象，设置给listview。
				listview.setAdapter(ListAdapter adapter);
	
#6 listview优化 
	
	adapter中getview方法会传进来一个convertView，convertView是指曾经使用过的view对象，可以被重复使用，但是在使用前需要判断是否为空，不为空直接复用，并作为getview方法的返回对象。
			TextView view = null;
			if(convertView != null){//判断converView是否为空，不为空重新使用
				view = (TextView) convertView;
			}else{
				view = new TextView(mContext);//创建一个textView对象
			}
			return view；

#7 listview---老虎机
	
	javaweb mvc
	m....mode....javabean
	v....view....jsp
	c....control...servlet
	
	listview mvc
	m....mode....Bean
	v....view....listview
	c....control...adapter
 	

#8 listview显示原理 (了解)
	1.要考虑listview显示的条目数    getcount
	2.考虑listview每个条目显示的内容   getview
	3.考虑每个item的高度，因为屏幕的多样化
	4.还要考虑listview的滑动，监听一个旧的条目消失，一个新的条目显示。


#9 复杂listview界面显示 ,黑马新闻（***********重要***********）

	1.布局写listview

	2.找到listview

	3.获取新闻数据封装到list集合中(才用模拟数据)，作为adapter的显示数据,怎么将获取的新闻数据给adapter???

	4.创建一个adapter继承BaseAdapter，实现4个方法
		getcount: 有多少条新闻数据，就有多少个条目。
		getView:将返回一个复杂的布局作为条目的内容展示；并且显示的数据是新闻的信息。 ？？？？？
		
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		//1.复用converView优化listview,创建一个view作为getview的返回值用来显示一个条目
		if(convertView != null){
			view = convertView;
		}else {
			//context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为getview的返回值,一般传null
			view = View.inflate(context, R.layout.item_news_layout, null);//将一个布局文件转换成一个view对象
		}
		//2.获取view上的子控件对象
		ImageView item_img_icon = (ImageView) view.findViewById(R.id.item_img_icon);
		TextView item_tv_des = (TextView) view.findViewById(R.id.item_tv_des);
		TextView item_tv_title = (TextView) view.findViewById(R.id.item_tv_title);
		//3.获取postion位置条目对应的list集合中的新闻数据，Bean对象
		NewsBean newsBean = list.get(position);
		//4.将数据设置给这些子控件做显示
		item_img_icon.setImageDrawable(newsBean.icon);//设置imageView的图片
		item_tv_title.setText(newsBean.title);
		item_tv_des.setText(newsBean.des);
		
		return view;
	}
		
	5.创建一个adapter对象设置给listview

	6.设置listview的条目的点击事件，并封装点击事件,去查看新闻详情。 ?????????
		//设置listview条目的点击事件
		lv_news.setOnItemClickListener(this);
	
			//listview的条目点击时会调用该方法 parent:代表listviw  view:点击的条目上的那个view对象   position:条目的位置  id： 条目的id

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		
		//需要获取条目上bean对象中url做跳转
		NewsBean bean = (NewsBean) parent.getItemAtPosition(position);
		
		String url = bean.news_url;
		
		//跳转浏览器
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.setData(Uri.parse(url));
		startActivity(intent);
	}

		

	1.布局写listview ok

	2.找到listview ok 
	
	3.封装新闻数据到list集合中 ，目的是为adapter提供数据展示。 ok 

	4.封装一个Adapter类继承BaseAdatper，写一个构造方法接受list集合数据，复写四个方法
		a.创建一个构造方法  ok 
		b.封装getCount方法   ok 
		c.getView方法：   不ok
			1.复用convertview，模板代码,如果不都能空，需要将一个布局文件转换为view对象作为getview的返回对象。
				view = View.inflater(Context context, int resuorceId,ViewGroup root)
			2.找到view上的这些子控件，目的是将list集合中的bean数据一一对应设置给这些子控件

			3.从list集合中获取postion条目上要显示的数据Bean
			
			4.将获取的bean中的数据设置给这些子控件
		d.getItem方法：将list集合中指定postion上的bean对象返回
		e.getItemId,直接返回postion

	5.创建一个封装的Adapter对象，设置给listview   ok
	6.设置listview条目的点击事件  ok
		listview.setOnItem....

	7.复写OnItemClicklistener方法，获取相应条目上的bean对象，最终获取到url，做Intent跳转;  不ok


#10 常用获取inflate的写法 

			1.
			//context:上下文, resource:要转换成view对象的layout的id, root:将layout用root(ViewGroup)包一层作为codify的返回值,一般传null
				//view = View.inflate(context, R.layout.item_news_layout, null);//将一个布局文件转换成一个view对象

			2.
			//通过LayoutInflater将布局转换成view对象
			//view =  LayoutInflater.from(context).inflate(R.layout.item_news_layout, null);
			
			3.
			//通过context获取系统服务得到一个LayoutInflater，通过LayoutInflater将一个布局转换为view对象
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = layoutInflater.inflate(R.layout.item_news_layout, null);
	
#11 arrayadapter  (不用看，知道有这个玩意就行)
		//找到控件
		ListView lv_array = (ListView) findViewById(R.id.lv_array);
		ListView lv_simple = (ListView) findViewById(R.id.lv_simple);
		
		//创建一个arrayAdapter
	//context  , resource:布局id, textViewResourceId：条目布局中 textview控件的id, objects:条目上texitview显示的内容
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.item_listview_layout, R.id.item_tv_class, classz);
		lv_array.setAdapter(arrayAdapter);
		
		
#12 simpleadapter   (不用看，知道有这个玩意就行)


		//创建一个simpleAdapter,封装simpleAdapter的数据
		ArrayList<Map<String, String>> arrayList = new ArrayList<Map<String,String>>();
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("class", "C++");
		arrayList.add(hashMap);
		
		HashMap<String, String> hashMap1 = new HashMap<String, String>();
		hashMap1.put("class", "android");
		arrayList.add(hashMap1);
		
		
		HashMap<String, String> hashMap2 = new HashMap<String, String>();
		hashMap2.put("class", "javaEE");
		arrayList.add(hashMap2);
		
		//context, data:显示的数据, resource:item布局id, from: map中的key, to:布局中的控件id
		SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.item_listview_layout, new String[]{"class"}, new int[]{R.id.item_tv_class});
		
		lv_simple.setAdapter(simpleAdapter);

#13 数据库的listview的界面显示 (新闻会了，这个就会了)


