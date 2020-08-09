package example.com.bookstorepractice;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LibraryFunctionActivity extends AppCompatActivity{

    BookManager bookManager = new BookManager();
    private Context context;

    private Button addBt;
    private Button deleteBt;
    private Button updateBt;
    private Button queryBt;

    //定义添加布局控件
    private EditText addBookId;
    private EditText addBookName;
    private EditText addBookPrice;
    private EditText addBookAuthor;
    private EditText addBookPages;
    private EditText addBookVaersion;

    //定义删除布局控件
    private EditText deleteByBookId;
    private EditText deleteByBookName;

    //定义修改布局控件
    private EditText updateByBookId;
    private EditText updateByBookName;
    private EditText newBookName;
    private EditText newBookPrice;
    private EditText newBookAuthor;
    private EditText newBookPages;
    private EditText newBookVersion;

    //定义查询布局控件
    private EditText queryByBookName;
    private EditText queryResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_function);

        context = LibraryFunctionActivity.this;

        init();
    }

    private void init(){
        //初始化主控件
        addBt = (Button)findViewById(R.id.add_button);
        deleteBt = (Button)findViewById(R.id.delete_button);
        updateBt = (Button)findViewById(R.id.update_button);
        queryBt = (Button)findViewById(R.id.query_button);

        addBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder addBuilder = new AlertDialog.Builder(context);
                final AlertDialog addAlertDialog;
                //创建对话框，添加一个布局
                View view1 = View.inflate(context, R.layout.add_book, null);
                //承载对话框
                addBuilder.setView(view1);
                //设置标题
                addBuilder.setTitle("添加书籍");
                //对对话框的控件实例化
                addBookId = (EditText)view1.findViewById(R.id.add_book_id);
                addBookName = (EditText)view1.findViewById(R.id.add_book_name);
                addBookPrice = (EditText)view1.findViewById(R.id.add_book_price);
                addBookAuthor = (EditText)view1.findViewById(R.id.add_book_author);
                addBookPages = (EditText)view1.findViewById(R.id.add_book_pages);
                addBookVaersion = (EditText)view1.findViewById(R.id.add_book_version);

                //确定
                addBuilder.setPositiveButton("确定", null);

                //取消
                addBuilder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //dialogInterface.dismiss();
                    }
                });

                //创建数据库并show出来
                addAlertDialog = addBuilder.create();
                addAlertDialog.show();

                //根据实际情况，设计普通的按钮点击监听事件，实现手动调用addAlertDialog.dismiss()关闭对话框
                addAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bookId = addBookId.getText().toString().trim();
                        String bookName = addBookName.getText().toString().trim();
                        String bookPrice = addBookPrice.getText().toString().trim();
                        String bookAuthor = addBookAuthor.getText().toString().trim();
                        String bookPages = addBookPages.getText().toString().trim();
                        String bookVersion = addBookVaersion.getText().toString().trim();
                        boolean addFlag = addBookHandle(bookId, bookName, bookPrice, bookAuthor, bookPages, bookVersion);
                        if (addFlag){
                            addAlertDialog.dismiss();//关闭对话框
                        }
                    }
                });
            }
        });

        deleteBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(context);
                //创建对话框，添加一个布局
                View view2 = View.inflate(context, R.layout.delete_book, null);
                //承载对话框
                deleteBuilder.setView(view2);
                //设置标题
                deleteBuilder.setTitle("删除书籍");
                //对对话框的控件实例化
                deleteByBookId = (EditText)view2.findViewById(R.id.delete_book_id);
                deleteByBookName = (EditText)view2.findViewById(R.id.delete_book_name);

                //确定
                deleteBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                //取消
                deleteBuilder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                //删除
                deleteBuilder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                //创建数据库并show出来
                deleteBuilder.create().show();
            }
        });
        updateBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder updateBuilder = new AlertDialog.Builder(context);
                final AlertDialog updateAlertDialog;
                //创建对话框，添加一个布局
                View view3 = View.inflate(context, R.layout.update_book, null);
                //承载对话框
                updateBuilder.setView(view3);
                //设置标题
                updateBuilder.setTitle("修改书籍");
                //对对话框的控件实例化
                updateByBookId = (EditText)view3.findViewById(R.id.update_by_book_id);
                updateByBookName = (EditText)view3.findViewById(R.id.update_by_book_name);
                newBookName = (EditText)view3.findViewById(R.id.new_book_name);
                newBookPrice = (EditText)view3.findViewById(R.id.new_book_price);
                newBookAuthor = (EditText)view3.findViewById(R.id.new_book_author);
                newBookPages = (EditText)view3.findViewById(R.id.new_book_pages);
                newBookVersion = (EditText)view3.findViewById(R.id.new_book_version);

                //确定
                updateBuilder.setPositiveButton("确定", null);
                //取消
                updateBuilder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                //创建数据库并show出来
                updateAlertDialog = updateBuilder.create();
                updateAlertDialog.show();

                //根据实际情况，设计普通的按钮点击监听事件，实现手动调用addAlertDialog.dismiss()关闭对话框
                updateAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bookId = updateByBookId.getText().toString().trim();
                        String oldName = updateByBookName.getText().toString().trim();
                        String newName = newBookName.getText().toString().trim();
                        String newPrice = newBookPrice.getText().toString().trim();
                        String newAuthor = newBookAuthor.getText().toString().trim();
                        String newPages = newBookPages.getText().toString().trim();
                        String newVersion = newBookVersion.getText().toString().trim();
                    }
                });
                /*
                addAlertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String bookId = addBookId.getText().toString().trim();
                        String bookName = addBookName.getText().toString().trim();
                        String bookPrice = addBookPrice.getText().toString().trim();
                        String bookAuthor = addBookAuthor.getText().toString().trim();
                        String bookPages = addBookPages.getText().toString().trim();
                        String bookVersion = addBookVaersion.getText().toString().trim();
                        boolean addFlag = addBookHandle(bookId, bookName, bookPrice, bookAuthor, bookPages, bookVersion);
                        if (addFlag){
                            addAlertDialog.dismiss();//关闭对话框
                        }
                    }
                });
                */

            }
        });
        queryBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder queryBuilder = new AlertDialog.Builder(context);
                //创建对话框，添加一个布局
                View view4 = View.inflate(context, R.layout.query_book, null);
                //承载对话框
                queryBuilder.setView(view4);
                //设置标题
                queryBuilder.setTitle("查询书籍");
                //对对话框的控件实例化
                queryByBookName = (EditText)view4.findViewById(R.id.query_book_name);
                queryResult = (EditText)view4.findViewById(R.id.query_result_text);
                //确定
                queryBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                //取消
                queryBuilder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                //删除
                queryBuilder.setNegativeButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                //创建数据库并show出来
                queryBuilder.create().show();
            }
        });
    }

    //对添加的书籍信息进行处理
    private boolean addBookHandle(String bookId, String bookName, String bookPrice, String bookAuthor, String bookPages, String bookVersion){

        //判断书籍信息是否填写完整
        if (bookId.equals("")){
            Toast.makeText(this, "书籍编号栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (bookName.equals("")){
            Toast.makeText(this, "书籍名称栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (bookPrice.equals("")){
            Toast.makeText(this, "书籍价格栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (bookAuthor.equals("")){
            Toast.makeText(this, "书籍作者栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (bookPages.equals("")){
            Toast.makeText(this, "书籍页数栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (bookVersion.equals("")){
            Toast.makeText(this, "书籍版本栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            //判断添加的书籍是否已存在
            boolean flag = bookManager.findBookById(bookId);
            if (flag){
                Toast.makeText(this, "该书籍已存在", Toast.LENGTH_SHORT).show();
                return false;
            }else {
                flag = bookManager.insertBook(bookId, bookName, bookPrice, bookAuthor, bookPages, bookVersion);
                if (flag){
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                    return true;
                }else {
                    Toast.makeText(this, "添加失败！", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
    }

    //对更新的书籍信息进行处理
    private boolean updateBookHandle(String bookId, String oldBookName, String newBookName, String newBookPrice, String newBookAuthor, String newBookPages, String newBookVersion){

        //判断书籍信息是否填写完整
        if (bookId.equals("")){
            Toast.makeText(this, "书籍编号栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (oldBookName.equals("")){
            Toast.makeText(this, "书籍名称栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            //判断更新的书籍是否已存在
            boolean flag = bookManager.findBookById(bookId);
            if (!flag){
                Toast.makeText(this, "该书籍不存在", Toast.LENGTH_SHORT).show();
                return false;
            }else {
                /*
                flag = bookManager.insertBook(bookId, bookName, bookPrice, bookAuthor, bookPages, bookVersion);
                if (flag){
                    Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
                    return true;
                }else {
                    Toast.makeText(this, "添加失败！", Toast.LENGTH_SHORT).show();
                    return false;
                }
                */
            }
        }
    }

    /*
    //判断添加的书籍信息是否填写完整
    private boolean isAddBookMessagesValid(){
        if (addBookId.getText().toString().trim().equals("")){
            Toast.makeText(this, "书籍编号栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (addBookName.getText().toString().trim().equals("")){
            Toast.makeText(this, "书籍名称栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (addBookPrice.getText().toString().trim().equals("")){
            Toast.makeText(this, "书籍价格栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (addBookAuthor.getText().toString().trim().equals("")){
            Toast.makeText(this, "书籍作者栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (addBookPages.getText().toString().trim().equals("")){
            Toast.makeText(this, "书籍页数栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else if (addBookVaersion.getText().toString().trim().equals("")){
            Toast.makeText(this, "书籍版本栏不能为空", Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }
    */

}
