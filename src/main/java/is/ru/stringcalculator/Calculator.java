package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		if(containsNewDelimiter(text)){
			String delimiter = newDelimiter(text);
			text = sanitizeString(text, delimiter);
		}
		if(containsNewLine(text))
			text = replaceNewLine(text);
		int[] array = toInt(splitNumbers(text));
		checkNegative(array);
		return sum(array);
	}

	private static int[] toInt(String[] numbers){
		int[] array = new int[numbers.length];
		int temp;
		for(int i = 0; i < numbers.length; i++){
			temp = Integer.parseInt(numbers[i]);
			if(temp <= 1000)
				array[i] = temp;
		}
		return array;
	}

	private static String[] splitNumbers(String numbers){
	    return numbers.split(",");
	}
      
    private static int sum(int[] numbers){
 	    int total = 0;
        for(int number : numbers){
		    total += number;
		}
		return total;
    }

    private static String replaceNewLine(String numbers){
    	return numbers.replace('\n', ',');
    }

    private static boolean containsNewLine(String numbers){
    	return numbers.contains("\n");
    }
    private static boolean containsNewDelimiter(String numbers){
    	return numbers.startsWith("//");
    }
    private static String newDelimiter(String numbers){
    	String delimiter = "";
    		if(numbers.startsWith("//[")){
    			for (int i = 3; i < numbers.length(); i++) {
    				if(numbers.charAt(i) != ']'){
    					delimiter += numbers.charAt(i);
    				}else{
    					break;
    				}
    			}
    		}else{
    			return "" + numbers.charAt(2);
    		}
    	return delimiter;
    }

    private static String sanitizeString(String numbers, String delimiter){
    	if (delimiter.length() == 1) {
    		numbers = numbers.substring(2);
			numbers = numbers.replace(numbers.charAt(0), ',');
			numbers = numbers.substring(2);
			return numbers;
		}else{
			numbers = numbers.replace("//[" + delimiter +"]\n", "");
			numbers = numbers.replace(delimiter, ",");
			return numbers;
		}
    }

    private static void  checkNegative(int[] array){
    	boolean negCheck = false;
    	String throwString = "";
    	for(int number : array){
    		if(number < 0 ){
    			negCheck = true;
    			throwString += number + ",";
    		}
    	}if (negCheck) {
    		int end = throwString.length()-1;
    		throwString = throwString.substring(0, end);
    		throw new IllegalArgumentException("Negatives not allowed: " + throwString);
		}    
    }
}