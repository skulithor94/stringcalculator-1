package is.ru.stringcalculator;
import java.util.Comparator;
import java.util.Arrays;

public class Calculator {

	public static int add(String text){
		if(text.equals("")){
			return 0;
		}
		if(containsNewDelimiter(text)){
			String[] delimiters = newDelimiter(text);
			text = sanitizeString(text, delimiters);
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

    private static String[] newDelimiter(String numbers){
    	String tempDelimiter = "";
    	String[] delimiters;
    	int delIndex = 0;
    	int delCount = numberOfDelimiters(numbers);
    	if(numbers.startsWith("//[")){	
			delimiters = new String[delCount];
    		for (int i = 3; i < numbers.length(); i++) {
    			if(numbers.charAt(i) != ']'){
    				tempDelimiter += numbers.charAt(i);
    			}else{
    				i++;
    				delimiters[delIndex] = tempDelimiter;
    				tempDelimiter = "";
    				delIndex++;
    				if(delIndex == delCount)
    					break;
    			}
    		}
    	}else{
    		delimiters = new String[1];
    		delimiters[0] = "" + numbers.charAt(2);
    		return delimiters;
    	}
    	return delimiters;
    }

    private static int numberOfDelimiters(String numbers){
    	int delCount = 0;
    	boolean delStart = false;
    	boolean delEnd = false;
    	for(int k = 0; k < numbers.length(); k++){
			if(numbers.charAt(k) == '[')
				delStart = true;
			if(numbers.charAt(k) == ']')
				delEnd = true;
    		if (delStart && delEnd) {
    			delCount++;
                delStart = false;
				delEnd = false;
    		}
			if(numbers.charAt(k) == '\n')
    			break;
		}
		return delCount;
    }

    private static String sanitizeString(String numbers, String[] delimiters){
    	if (delimiters.length == 1 && delimiters[0].length() == 1) {
    		numbers = numbers.substring(2);
			numbers = numbers.replace(numbers.charAt(0), ',');
			numbers = numbers.substring(2);
			return numbers;
		}else{
            Arrays.sort(delimiters, new comp());
			numbers = numbers.replace("//", "");
			for (int i = 0; i < delimiters.length ; i++) {
				numbers = numbers.replace("[" + delimiters[i] +"]", "");
				numbers = numbers.replace(delimiters[i], ",");
			}
			numbers = numbers.substring(1);
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

    static class comp implements Comparator<String> {
        public int compare(String str1, String str2){
            if(str1.length() < str2.length())
                return 1;
            else if(str1.length() == str2.length())
                return 0;
            else
                return -1;
        }
    }
}