package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(newDelimiter(text)){
			text = standardizeDelimiter(text);
		}
		if(contansNewLine(text)){
			text = replaceNewLine(text);
		}
		if(text.equals("")){
			return 0;
		}else if(text.contains(",")){
			int[] array = toInt(splitNumbers(text));
			checkNegative(array);
			return sum(array);
		}else
			return 1;
	}

	private static int[] toInt(String[] numbers){
		int[] array = new int[numbers.length];
		for(int i = 0; i < numbers.length; i++){
			array[i] = Integer.parseInt(numbers[i]);
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

    private static boolean contansNewLine(String numbers){
    	return numbers.contains("\n");
    }

    private static boolean newDelimiter(String numbers){
    	return numbers.startsWith("//");
    }

    private static String standardizeDelimiter(String numbers){
    		numbers = numbers.substring(2);
			numbers = numbers.replace(numbers.charAt(0), ',');
			numbers = numbers.substring(2);
			return numbers;
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