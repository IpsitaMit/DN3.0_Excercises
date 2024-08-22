import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Integer, Product> products;

    public Inventory() {
        this.products = new HashMap<>();
    }

    public void addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            System.out.println("Product already exists.");
        } else {
            products.put(product.getProductId(), product);
            System.out.println("Added: " + product);
        }
    }

    public void updateProduct(int productId, String productName, Integer quantity, Double price) {
        if (products.containsKey(productId)) {
            Product product = products.get(productId);
            if (productName != null) {
                product.setProductName(productName);
            }
            if (quantity != null) {
                product.setQuantity(quantity);
            }
            if (price != null) {
                product.setPrice(price);
            }
            System.out.println("Updated: " + product);
        } else {
            System.out.println("Product not found.");
        }
    }

    public void deleteProduct(int productId) {
        if (products.containsKey(productId)) {
            products.remove(productId);
            System.out.println("Deleted product with id " + productId);
        } else {
            System.out.println("Product not found.");
        }
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Product product1 = new Product(1, "Product1", 10, 100.0);
        Product product2 = new Product(2, "Product2", 5, 50.0);

        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.updateProduct(1, null, 20, 90.0);
        inventory.deleteProduct(2);
    }
}
