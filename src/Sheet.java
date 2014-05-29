import java.util.Map;
import java.util.TreeMap;

import org.matheclipse.parser.client.eval.DoubleEvaluator;

public class Sheet {
	private Map<String, String> h = new TreeMap<String, String>();
	
	public String get(String position){
		String text=h.get(position);
		if(text==null){
			return "";
		}else if(isNumber(text)){
			return text.trim();
		}else{
			if(text.startsWith("=")){
				return evaluate(text); 				
			}else{
				return text;
			}
		}
		
	}

	private boolean isNumber(String input) {
		input=input.trim();
		if("".equals(input)){
			return false;
		}
		for(char c: input.toCharArray()){
			if(!Character.isDigit(c)){
				return false;
			}
		}
		return true;
	}
	
	private String evaluate(String input){
		String value = input.substring(1);
		try {
			DoubleEvaluator engine = new DoubleEvaluator();
			int result = (int) engine.evaluate(value);
			return String.valueOf(result);
		} catch (Exception e) {
			return "#Error";
		}
		
	}
	
	public void put(String position, String value) {
		h.put(position,value);		
	}

	public String getLiteral(String theCell) {
		return h.get(theCell);
	}

}