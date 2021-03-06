package is.ru.stringcalculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

	public static void main(String args[]) {
      org.junit.runner.JUnitCore.main("is.ru.stringcalculator.CalculatorTest");
    }

	@Test
	public void testEmptyString() {
		assertEquals(0, Calculator.add(""));
	}

	@Test
	public void testOneNumber() {
		assertEquals(1, Calculator.add("1"));
	}

	@Test
	public void testTwoNumbers() {
		assertEquals(3, Calculator.add("1,2"));
	}	

	@Test
    public void testMultipleNumbers(){
    	assertEquals(6, Calculator.add("1,2,3"));
    }

    @Test 
    public void testTenNumbers(){
    	assertEquals(55, Calculator.add("1,2,3,4,5,6,7,8,9,10"));
    }

    @Test
    public void testNewLineDelimiter(){
    	assertEquals(6, Calculator.add("1\n2,3"));
    }
    @Test 
    public void testOnlyNewLine(){
    	assertEquals(6, Calculator.add("1\n2\n3"));
    }
    @Test
    public void testNewDelimiter(){
    	assertEquals(3, Calculator.add("//;\n1;2"));
    }

    @Test
    public void testAnotherDelimiter(){
    	assertEquals(5, Calculator.add("//?\n2?3"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testOneNegative(){
    	assertEquals("Negatives not allowed: -1",
    	 Calculator.add("-1,2"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void testTwoNegatives(){
    	assertEquals("Negatives not allowed: -4,-5",
    		Calculator.add("2,-4,3,-5"));
    }

    @Test
    public void testOverThousand(){
    	assertEquals(2, Calculator.add("1001,2"));
	}

    @Test
    public void testOverThousandRetZero(){
    	assertEquals(0, Calculator.add("1001,2000,3000"));
	}

	@Test
    public void testLongDelimiter(){
    	assertEquals(6, Calculator.add("//[***]\n1***2***3"));
	}	

	@Test
	public void testMultipleDelimiters(){
		assertEquals(6, Calculator.add("//[*][%]\n1*2%3"));
	}

	@Test
	public void testTwoLongDelimiters(){
		assertEquals(6, Calculator.add("//[**][%%%]\n1**2%%%3"));
	}

	@Test
	public void testThreeLongDelimiters(){
		assertEquals(10, Calculator.add("//[**][%%%][xzxz]\n1**2%%%3xzxz4"));
	}

    @Test
    public void testPrefixDelimiter(){
        assertEquals(10, Calculator.add("//[ss][ssfx][xzxz]\n1ss2ssfx3xzxz4"));
    }
}