package com.example.mac.sqlitecrud1.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mac.sqlitecrud1.MysqliteOpenHelper;
import com.example.mac.sqlitecrud1.bean.InfoBean;

/**
 * Created by mac on 17/2/15.
 */

public class InfoDao {

    private MysqliteOpenHelper mysqliteOpenHelper;

    public InfoDao(Context context){
        mysqliteOpenHelper = new MysqliteOpenHelper(context);
    }

    public void add(InfoBean bean) {
        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mysqliteOpenHelper.getWritableDatabase();
        db.execSQL("insert into info(name,phone) values(?,?);",new Object[]{bean.name,bean.phone});
        db.close();
    }

    public void del(String name) {
        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mysqliteOpenHelper.getWritableDatabase();
        db.execSQL("delete from info where name=?;", new Object[]{name});
        db.close();
    }

    public void update(InfoBean bean) {
        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mysqliteOpenHelper.getReadableDatabase();
        //sql:sql语句，  bindArgs：sql语句中占位符的值
        db.execSQL("update info set phone=? where name=?;", new Object[]{bean.phone,bean.name});
        //关闭数据库对象
        db.close();
    }

    public void query(String name) {
        //执行sql语句需要sqliteDatabase对象
        //调用getReadableDatabase方法,来初始化数据库的创建
        SQLiteDatabase db = mysqliteOpenHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select _id, name,phone from info where name = ?;", new String []{name});
        if (cursor !=null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name_str = cursor.getString(1);
                String phone = cursor.getString(2);
                System.out.println("_id:"+id+";name:"+name_str+";phone:"+phone);
            }
            cursor.close();
        }
        db.close();
    }






}
