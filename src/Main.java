import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws RuntimeException {
        List<Product>products=new ArrayList<>();
        products.addAll(Arrays.asList(
                new Product("Book",100.00,true,
                LocalDate.of(2022, 03,01)),
                new Product("Book",250.00,true,
                        LocalDate.of(2020, 03,01)),
                new Product("Book",300.00,true,
                        LocalDate.of(2023, 03,01)),
                new Product("Book",70.00,false,
                        LocalDate.of(2023,01,10)),
                new Product("Book",75.00,false,
                        LocalDate.of(2023,01,20)),
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
                .min(Comparator.comparingDouble(Product::getPrice));
        cheapestProduct.ifPresentOrElse(
                cp-> System.out.println(cp.getType()+" "+cp.getPrice()),
                ()-> System.out.println("Product not found")
        );

        List<Product>lastThreeAddedProducts=products.stream()
                .sorted(Comparator.comparing(Product::getDateAdded).reversed())
                .limit(3)
                .toList();
        lastThreeAddedProducts.forEach(lap-> System.out.println(lap.getDateAdded()));

        double totalCostOfProduct=products.stream()
                .filter(product -> "Book".equals(product.getType())
                        && product.getDateAdded().getYear() == LocalDate.now().getYear()
                        && product.getPrice() <= 75)
                .mapToDouble(Product::getPrice)
                .sum();
        System.out.println(totalCostOfProduct);

        double totalCostOfProduct2=products.stream()
                .filter(product -> "Book".equals(product.getType())
                        && product.getDateAdded().getYear() == LocalDate.now().getYear()
                        && product.getPrice() <= 75)
                .reduce(0,(x,y)->{
                    if (y.getPrice()<=75)
                        return (int) ((double)x+y.getPrice());
                    else return x;
                },
                        Integer::sum);
        System.out.println(totalCostOfProduct2);


        Map<String, List<Product>> productsByType = products.stream()
                .collect(Collectors.groupingBy(Product::getType));
        for (Map.Entry<String,List<Product>>item: productsByType.entrySet()){
            for (Product product:item.getValue()){
                System.out.println("Type: "+product.getType()+"price: "+product.getPrice()+
                        "discount: "+product.withDiscount()+"createDate: "+product.getDateAdded());
            }
            System.out.println();
        }


    }
}