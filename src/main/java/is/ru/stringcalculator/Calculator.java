package is.ru.stringcalculator;

public class Calculator {

	public static int add(String text){
		if(contansNewLine(text)){
			text = replaceNewLine(text);
		}
		if(text.equals("")){
			return 0;
		}
		else if(text.contains(",")){
			return sum(splitNumbers(text));
		}
		else
			return 1;
	}

	private static int toInt(String number){
		return Integer.parseInt(number);
	}

	private static String[] splitNumbers(String numbers){
	    return numbers.split(",");
	}
      
    private static int sum(String[] numbers){
 	    int total = 0;
        for(String number : numbers){
		    total += toInt(number);
		}
		return total;
    }

    private static String replaceNewLine(String numbers){
    	return numbers.replace('\n', ',');
    }

    private static boolean contansNewLine(String numbers){
    	return numbers.contains("\n");
    }

}