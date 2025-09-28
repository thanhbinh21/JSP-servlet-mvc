package fit.se.librarysession.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Book book;
    private int qty;

    public CartItem() {
    }

    public CartItem(Book book, int qty) {
        this.book = book;
        this.qty = qty;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal(){
        return book.getPrice()*qty;
    }
}
