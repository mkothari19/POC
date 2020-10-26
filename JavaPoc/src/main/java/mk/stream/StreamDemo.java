package mk.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamDemo {
	public static void main(String[] args) {

		List<Product> productList = new ArrayList<Product>();

		productList.add(new Product(50000f, "Ipphone 11", 1));
		productList.add(new Product(120000f, "MacPro", 1));
		productList.add(new Product(80000f, "MacAir", 1));
		productList.add(new Product(150000f, "Ipphone 12", 1));
		productList.add(new Product(450000f, "Apple Watch", 1));
		
		System.out.println("LIST    "+productList.get(0));

		List<String> result = productList.stream().filter(p -> p.getPrice() > 60000).map(p -> p.getProductName())
				.collect(Collectors.toList());

		System.out.println(result);

		/*
		 * Iterating Stream
		 */

		productList.stream().filter(p -> p.getPrice() == 50000).forEach(p -> System.out.println(p.getProductName()));

		/*
		 * Reduce
		 */
		Float totalPrice = productList.stream().map(p -> p.getPrice()).reduce(0.0f, Float::sum);
		System.out.println(totalPrice);

		/*
		 * Using Collectors's method to sum the prices.
		 */

		Double total = productList.stream().collect(Collectors.summingDouble(p -> p.getPrice()));
		System.out.println(total);

		/*
		 * Max Product Price
		 */

		Product productA = productList.stream().max((p1, p2) -> p1.getPrice() > p2.getPrice() ? 1 : -1).get();
		System.out.println("MAX: " + productA.getPrice());

		/*
		 * Min Product Price
		 */
		Product productB = productList.stream().max((p1, p2) -> p1.getPrice() < p2.getPrice() ? 1 : 1).get();
		System.out.println("MIN :" + productB.getPrice());

		/*
		 * Count
		 * 
		 */
		long count = productList.stream().filter(p -> p.getPrice() > 30000).count();

		System.out.println(count);
		/*
		 * Covert list to set
		 */

		Set<Float> set = productList.stream().filter(p -> p.getPrice() > 30000).map(p -> p.getPrice())
				.collect(Collectors.toSet());

		System.out.println(set);

		Map<String, Float> map = productList.stream().filter(p -> p.getPrice() > 30000)
				.collect(Collectors.toMap(p -> p.getProductName(), p -> p.getPrice()));

		System.out.println(map);

		/*
		 * Method reference in Stream
		 */

		List<Float> priceList = productList.stream().filter(p -> p.getPrice() > 30000).map(Product::getPrice)
				.collect(Collectors.toList());
		System.out.println(priceList);

	}

}
