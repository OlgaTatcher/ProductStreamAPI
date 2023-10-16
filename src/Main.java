import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Stream<Product>products=Stream.of(new Product("Book",250.00,true,
                LocalDate.of(2022, 03,01)),
                new Product("Book",300.00,true,
                        LocalDate.of(2020,01,10)),
                new Product("Book",350.00,false,
                        LocalDate.of(2021,01,20)),
                new Product("Notebook",70.00,false,
                        LocalDate.of(2022,01,15)),
                new Product("Notebook",55.00,true,
                        LocalDate.of(2022,10,10)));
        List<Product>book=products.filter(product -> "Book".equals(product.getType())&& (product.getPrice()>250))
                .toList();
        book.forEach(b-> System.out.println(b.getType()+"-"+b.getPrice()));

        Stream<Product>discountedProducts=Stream.of(new Product("Book",250.00,true,
                        LocalDate.of(2022, 03,01)),
                new Product("Book",300.00,true,
                        LocalDate.of(2020,01,10)),
                new Product("Book",350.00,false,
                        LocalDate.of(2021,01,20)),
                new Product("Notebook",70.00,false,
                        LocalDate.of(2022,01,15)),
                new Product("Notebook",55.00,true,
                        LocalDate.of(2022,10,10)));
        discountedProducts.filter(product -> "Book".equals(product.getType()))
                .flatMap(p1->Stream.of(
                        String.format("Price without discount:"+p1.getType()+" "+p1.getPrice()),
                        String.format("Discounted price: "+p1.getType()+" "+(p1.getPrice()-(int)(p1.getPrice()*0.1)))
                ))
                .forEach(pwd-> System.out.println(pwd));


        Stream<Product>cheapestProduct=Stream.of(new Product("Book",250.00,true,
                        LocalDate.of(2022, 03,01)),
                new Product("Book",300.00,true,
                        LocalDate.of(2020,01,10)),
                new Product("Book",350.00,false,
                        LocalDate.of(2021,01,20)),
                new Product("Notebook",70.00,false,
                        LocalDate.of(2022,01,15)),
                new Product("Notebook",55.00,true,
                        LocalDate.of(2022,10,10)));

        Product min = cheapestProduct.filter(product -> "Book".equals(product.getType()))
                .min(Product::compare).get();
        System.out.println("Cheapest product:type- "+min.getType()+"- price-"+min.getPrice());















    }
}