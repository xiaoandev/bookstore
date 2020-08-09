package example.com.bookstorepractice;

import org.litepal.crud.DataSupport;

import java.util.List;

public class BookManager {

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

    //更新书籍信息
    public boolean updateByBookId(String bookId, String bookName, String bookPrice, String bookAuthor, String bookPages, String bookVersion){
        List<Book> books =



    }

    //通过查找书籍编号，查看书籍是否存在 v
    public boolean findBookById(String id){
        List<Book> books = DataSupport.findAll(Book.class);
        for (Book mBook: books){
            if (id.trim().equals(mBook.getBookId())){
                return true;
            }
        }
        return false;
    }
}
