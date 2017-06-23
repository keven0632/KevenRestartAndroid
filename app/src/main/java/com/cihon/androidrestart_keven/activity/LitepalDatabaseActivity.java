package com.cihon.androidrestart_keven.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.bean.Book;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class LitepalDatabaseActivity extends AppCompatActivity {

    private Button mBt_create;
    private Button mBt_add;
    private Button mBt_update;
    private Button mBt_delete;
    private Button mBt_query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal_database);
/**
 * 创建数据库
 */
        mBt_create = (Button) findViewById(R.id.bt_create);
        mBt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
                Log.e("tag", "现在在创建数据库");
            }
        });
/**
 * 添加数据库信息
 */
        mBt_add = (Button) findViewById(R.id.bt_add);
        mBt_add.setOnClickListener(v -> {
            Book book = new Book();
            book.setName("The Da Vinci Code");
            book.setAuthor("keven");
            book.setPages(456);
            book.setPrice(16.58);
            book.setPress("Unknow");
            book.save();
            Log.e("tag", "数据库添加数据");

        });
/**
 * 更新数据库信息
 */
        mBt_update = (Button) findViewById(R.id.bt_update);
        mBt_update.setOnClickListener(v -> {
            Book book = new Book();
            book.setPrice(18.88);
            book.setPress("理工大出版社");
            book.updateAll("name = ? and author = ?", "The Da Vinci Code", "keven");

//            Book b = new Book();
//            b.setToDefault("pages");
//            b.updateAll();
        });
/**
 * 删除数据库信息
 */
        mBt_delete = (Button) findViewById(R.id.bt_delete);
        mBt_delete.setOnClickListener(v -> DataSupport.deleteAll(Book.class, "price < ?", "18"));
/**
 * 查询数据库信息
 */
        mBt_query = (Button) findViewById(R.id.bt_query);
        mBt_query.setOnClickListener(v -> {
            List<Book> list = DataSupport.findAll(Book.class);
            for (Book book : list) {
                Log.e("Litepal", "Id--" + book.getId());
                Log.e("Litepal", "Name--" + book.getName());
                Log.e("Litepal", "Author--" + book.getAuthor());
                Log.e("Litepal", "Press--" + book.getPress());
                Log.e("Litepal", "Pages--" + book.getPages());
                Log.e("Litepal", "Price--" + book.getPrice());
            }

            //查询数据库第一条数据
            Book firstBook = DataSupport.findFirst(Book.class);
            //查询数据库最后一条数据
            Book lastBook = DataSupport.findLast(Book.class);
            //select()方法用于制定查询那几列数据
            List<Book> bookList = DataSupport.select("name", "author").find(Book.class);
            //where()方法用于指定查询的约束条件
            List<Book> books = DataSupport.where("pages > ?", "400").find(Book.class);
            //order()方法用于指定结果的排序方式
            List<Book> books1 = DataSupport.order("price desc").find(Book.class);
            //limit()方法用于指定查询结果的数量  例子前三条数据
            List<Book> books2 = DataSupport.limit(3).find(Book.class);
            //offset()方法为产生偏移量，例子为查询第2,3,4条数据
            List<Book> books3 = DataSupport.offset(1).limit(3).find(Book.class);

            //litepal仍然支持原生sql
            Cursor c=DataSupport.findBySQL("");
        });
    }

}
