package example.com.bookstorepractice;

import org.litepal.crud.DataSupport;

public class Book extends DataSupport {
    private String bookId;
    private String bookName;
    private String bookPrice;
    private String bookAuthor;
    private String bookPages;
    private String bookVersion;

    public String getRetail(){

        String retail = "书籍编号：" + bookId + "\n名称：" + bookName + "\n价格：" + bookPrice
                +"\n作者：" +  bookAuthor + "\n页数：" + bookPages + "\n版本号：" + bookVersion;

        return retail;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookPages() {
        return bookPages;
    }

    public void setBookPages(String bookPages) {
        this.bookPages = bookPages;
    }

    public String getBookVersion() {
        return bookVersion;
    }

    public void setBookVersion(String bookVersion) {
        this.bookVersion = bookVersion;
    }

}
