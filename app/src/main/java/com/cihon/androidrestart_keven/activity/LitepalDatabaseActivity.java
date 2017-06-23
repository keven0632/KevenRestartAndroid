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
 * �������ݿ�
 */
        mBt_create = (Button) findViewById(R.id.bt_create);
        mBt_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
                Log.e("tag", "�����ڴ������ݿ�");
            }
        });
/**
 * ������ݿ���Ϣ
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
            Log.e("tag", "���ݿ��������");

        });
/**
 * �������ݿ���Ϣ
 */
        mBt_update = (Button) findViewById(R.id.bt_update);
        mBt_update.setOnClickListener(v -> {
            Book book = new Book();
            book.setPrice(18.88);
            book.setPress("���������");
            book.updateAll("name = ? and author = ?", "The Da Vinci Code", "keven");

//            Book b = new Book();
//            b.setToDefault("pages");
//            b.updateAll();
        });
/**
 * ɾ�����ݿ���Ϣ
 */
        mBt_delete = (Button) findViewById(R.id.bt_delete);
        mBt_delete.setOnClickListener(v -> DataSupport.deleteAll(Book.class, "price < ?", "18"));
/**
 * ��ѯ���ݿ���Ϣ
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

            //��ѯ���ݿ��һ������
            Book firstBook = DataSupport.findFirst(Book.class);
            //��ѯ���ݿ����һ������
            Book lastBook = DataSupport.findLast(Book.class);
            //select()���������ƶ���ѯ�Ǽ�������
            List<Book> bookList = DataSupport.select("name", "author").find(Book.class);
            //where()��������ָ����ѯ��Լ������
            List<Book> books = DataSupport.where("pages > ?", "400").find(Book.class);
            //order()��������ָ�����������ʽ
            List<Book> books1 = DataSupport.order("price desc").find(Book.class);
            //limit()��������ָ����ѯ���������  ����ǰ��������
            List<Book> books2 = DataSupport.limit(3).find(Book.class);
            //offset()����Ϊ����ƫ����������Ϊ��ѯ��2,3,4������
            List<Book> books3 = DataSupport.offset(1).limit(3).find(Book.class);

            //litepal��Ȼ֧��ԭ��sql
            Cursor c=DataSupport.findBySQL("");
        });
    }

}
