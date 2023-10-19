import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Product>products=new ArrayList<>();
        products.addAll(Arrays.asList(new Product("Book",250.00,true,
                LocalDate.of(2022, 03,01)),new Product("Book",300.00,true,
                        LocalDate.of(2020,01,10)),
                new Product("Book",350.00,false,
                        LocalDate.of(2021,01,20)),
                new Product("Notebook",70.00,false,
                        LocalDate.of(2022,01,15)),
                new Product("Notebook",55.00,true,
                        LocalDate.of(2022,10,10))));

        List<Product>book=products.stream()
                .filter(product -> "Book".equals(product.getType())&& (product.getPrice()>250))
                .toList();
        book.forEach(b-> System.out.println(b.getType()+"-"+b.getPrice()));


        List<Product>discountedProducts=products.stream()
                .filter(product -> "Book".equals(product.getType()))
                .map(product -> new Product(product.getType(), product.getPrice(), product.withDiscount(), product.getDateAdded()))
                .toList();
        discountedProducts.forEach(dp-> System.out.println("Discounted Products: "+dp.getType()+" -price with discount: "
                +(dp.getPrice() * 0.9)));


        Optional<Product>cheapestProduct=products.stream()
                .filter(product -> "Book".equals(product.getType()))
                .min(Product::compare);
        cheapestProduct.ifPresentOrElse(
                cp-> System.out.println("Cheapest "+cp.getType()+" costs "+cp.getPrice()),
                ()-> System.out.println("Product not found")
        );

        List<Product>lastThreeAddedProducts=products.stream()
                .sorted(Comparator.comparing(Product::getDateAdded).reversed())
                .limit(3)
                .collect(Collectors.toList());
        lastThreeAddedProducts.forEach(lap-> System.out.println(lap.getDateAdded()));
























    }
}