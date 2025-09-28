package fit.se.librarysession.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {

    private List<CartItem> list = new ArrayList<>();

    public List<CartItem> getList() {
        return list;
    }

    public void setList(List<CartItem> list) {
        this.list = list;
    }

    public Cart() {
    }

    public Cart(List<CartItem> list) {
        this.list = list;
    }

    //
    public void addItem(Book book){
        for(CartItem a : list){
            if(a.getBook().getId()== book.getId()){
                a.setQty(a.getQty()+1);
                return;
            }
        }
        list.add(new CartItem(book,1));
    }
    //
    public void removeItem(int bookId){
        list.removeIf(cartItem -> cartItem.getBook().getId()== bookId);
    }
    //
    public void updateItem(int bookId, int qnyUp){
        for(CartItem a : list){
            if(a.getBook().getId()== bookId){
               if(qnyUp<=0){
                   removeItem(bookId);
               }else {
                   a.setQty(qnyUp);
               }
               return;
            }

        }
    }

    //
    public double totalCart(){
        return list.stream().mapToDouble(CartItem::getTotal).sum();
    }

    public void clear(){
        list.clear();
    }




}
