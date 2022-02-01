/**
 * 
 */
package Hello;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Rohan Gharat
 *
 */
public class OrderAnalyzer 
{
	static class Order {
		int orderId;
		LocalDateTime creationDate;
		List<OrderLine> orderLines;
	}

	static class OrderLine {
		int productId;
		String name;
		int quantity;
		BigDecimal unitPrice;
	}

	public Map<DayOfWeek, Integer> averageDailySales(List<Order> orders) {

		int dayCounter = 0;
		float result = 0;
		int averagePriceValue = 0;

		HashMap<DayOfWeek, Integer> hmap = new HashMap<DayOfWeek, Integer>();

		// iterate DayOfWeek
		for(DayOfWeek day: DayOfWeek.values()){
			//System.out.println(day);
			for(Order order: orders){
				if(day == order.creationDate.getDayOfWeek()){
					dayCounter++;
					for(OrderLine orderLine : order.orderLines){
						result += orderLine.quantity * orderLine.unitPrice.floatValue();
						//System.out.println("Result"+result);
					}
				}
			}
			//avoiding division by zero scenario
			if(dayCounter > 0){
				averagePriceValue = Math.round(result / dayCounter);
				//System.out.println("APV"+averagePriceValue);
			}
			else
			{
				averagePriceValue = 0;
			}
      			//make sure to update the hashmap
			hmap.put(day,averagePriceValue);
      			//reset variables
			dayCounter = 0;
			result = 0;
		}
		return hmap;
	}

}
