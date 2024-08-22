import java.util.Arrays;
import java.util.Comparator;

public class SearchAlgos {
    public static Product linearSearch(Product[] products, int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                return product;
            }
        }
        return null;
    }

    public static Product binarySearch(Product[] products, int productId) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (products[mid].getProductId() == productId) {
                return products[mid];
            } else if (products[mid].getProductId() < productId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Smartphone", "Electronics"),
            new Product(3, "Tablet", "Electronics"),
            new Product(4, "Headphones", "Accessories"),
            new Product(5, "Keyboard", "Accessories")
        };

        // Sorting the array for binary search
        Arrays.sort(products, Comparator.comparingInt(Product::getProductId));

        // Linear Search
        Product result1 = linearSearch(products, 3);
        System.out.println(result1);

        // Binary Search
        Product result2 = binarySearch(products, 3);
        System.out.println(result2);
    }
}
