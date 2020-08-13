package example.com.bookstorepractice;

import android.content.ContentValues;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class BookManager {

    //通过查找书籍编号，查看书籍是否存在
    public boolean findBookById(String bookId){
        List<Book> books = DataSupport.findAll(Book.class);
        for (Book mBook: books){
            if (bookId.trim().equals(mBook.getBookId())){
                return true;
            }
        }
        return false;
    }

    //通过查找书籍名称，查看书籍是否存在
    public boolean findBookByName(String bookName){
        List<Book> books = DataSupport.findAll(Book.class);
        for (Book mBook: books){
            if (bookName.trim().equals(mBook.getBookName())){
                return true;
            }
        }
        return false;
    }

    //添加书籍信息
    public boolean insertBook(String bookId, String bookName, String bookPrice, String bookAuthor, String bookPages, String bookVersion){
        Book book = new Book();
        book.setBookId(bookId);
        book.setBookName(bookName);
        book.setBookPrice(bookPrice);
        book.setBookAuthor(bookAuthor);
        book.setBookPages(bookPages);
        book.setBookVersion(bookVersion);

        //Connector.getDatabase();
        return book.save();
    }

    //根据书籍编号删除书籍信息
    public int deleteByBookId(String bookId){
        int flag;
        flag = DataSupport.deleteAll(Book.class, "bookid = ?", bookId);
        return flag;
    }

    //根据书籍名称删除书籍信息
    public int deleteByBookName(String bookName){
        int flag;
        flag = DataSupport.deleteAll(Book.class, "bookname = ?", bookName);
        return flag;
    }

    //根据书籍编号更新书籍信息
    public int updateByBookId(String bookId, String bookName, String bookPrice, String bookAuthor, String bookPages, String bookVersion){
        int flag;
        ContentValues values = new ContentValues();
        values.put("bookname", bookName);
        values.put("bookprice", bookPrice);
        values.put("bookauthor", bookAuthor);
        values.put("bookpages", bookPages);
        values.put("bookversion", bookVersion);
        flag = DataSupport.updateAll(Book.class, values, "bookid = ?", bookId);
        return flag;
        /*
        List<Book> books = DataSupport.where("bookid = ?", bookId).find(Book.class);
        Book book;
        if (books != null && books.size()>0){
            book = books.get(0);
            if (!bookName.equals("")){
                book.setBookName(bookName);
            }
            if (!bookPrice.equals("")){
                book.setBookPrice(bookPrice);
            }
            if (!bookAuthor.equals("")){
                book.setBookAuthor(bookAuthor);
            }
            if (!bookPages.equals("")){
                book.setBookPages(bookPages);
            }
            if (!bookVersion.equals("")){
                book.setBookVersion(bookVersion);
            }
            return book.save();
        }
        return false;
        */

    }


    //查找满足指定书籍编号和书籍名称的书籍
    public String queryBookByIdAndName(String bookId, String bookName){
        List<Book> books = DataSupport.where("bookid = ? and bookname = ?", bookId, bookName).find(Book.class);
        Book book;
        String bookMessage = "";
        if (books != null && books.size()>0){
            book = books.get(0);
            bookMessage = "书籍编号：" + book.getBookId() + "\n书籍名称：" + book.getBookName() + "\n书籍价格："
                    + book.getBookPrice() + "\n书籍作者：" + book.getBookAuthor() + "\n书籍页数：" + book.getBookPages() + "\n书籍版本：" + book.getBookVersion();
            return bookMessage;
        }
        return bookMessage;
    }

    //查找满足指定书籍编号的书籍
    public String queryBookById(String bookId){
        List<Book> books = DataSupport.where("bookid = ?", bookId).find(Book.class);
        Book book;
        String bookMessage = "";
        if (books != null && books.size()>0){
            book = books.get(0);
            bookMessage = "书籍编号：" + book.getBookId() + "\n书籍名称：" + book.getBookName() + "\n书籍价格："
                    + book.getBookPrice() + "\n书籍作者：" + book.getBookAuthor() + "\n书籍页数：" + book.getBookPages() + "\n书籍版本：" + book.getBookVersion();
            return bookMessage;
        }
        return bookMessage;
    }

    //查找满足指定书籍名称的书籍
    public ArrayList<String> queryBookByName(String bookName){
        ArrayList<Book> books = (ArrayList<Book>) DataSupport.where("bookname = ?", bookName).find(Book.class);
        ArrayList<String> bookMessages = new ArrayList<String> ();
        if (books != null && books.size()>0){
            int i = 0;
            for (Book mBook: books){
                String mBookMessage = "书籍编号：" + mBook.getBookId() + "\n书籍名称：" + mBook.getBookName() + "\n书籍价格："
                        + mBook.getBookPrice() + "\n书籍作者：" + mBook.getBookAuthor() + "\n书籍页数：" + mBook.getBookPages() + "\n书籍版本：" + mBook.getBookVersion() + "\n";
                bookMessages.add(mBookMessage);
                i++;
            }
        }
        return bookMessages;
    }
}
