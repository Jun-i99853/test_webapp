package webapp;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {
	@Test
	public void testSum() {
		Calculator cal = new Calculator();
		double result = cal.sum(10, 100);
		assertEquals(110, result, 0);
	}
}
