package fit.se.cartshoping.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItem> items = new ArrayList<>();

    public List<CartItem> getItems() { return items; }

    // Thêm sản phẩm vào giỏ
    public void addItem(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(product, 1));
    }

    // Xóa sản phẩm
    public void removeItem(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    // Cập nhật số lượng
    public void updateQuantity(int productId, int quantity) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                if (quantity <= 0) {
                    removeItem(productId);
                } else {
                    item.setQuantity(quantity);
                }
                return;
            }
        }
    }

    // Tính tổng tiền
    public double getTotalCart() {
        return items.stream().mapToDouble(CartItem::getTotal).sum();
    }

    // Xóa toàn bộ giỏ hàng
    public void clear() {
        items.clear();
    }
}