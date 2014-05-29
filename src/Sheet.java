import java.util.Map;
import java.util.TreeMap;

import org.matheclipse.parser.client.SyntaxError;
import org.matheclipse.parser.client.eval.DoubleEvaluator;

public class Sheet {
	private Map<String, String> h = new TreeMap<String, String>();
	
	public String get(String position){
		String text=h.get(position);
		if(text==null){
			return "";
		}else if(isNumber(text)){
			return text.trim();
		}else if(text.startsWith("=")){
			String sub=text.substring(1);
				return evaluate(sub);
		}else{
			return text;
		}
	}
		
	public void put(String position, String value) {
		if(value.startsWith("=") && isCellValue(value)){			
			h.put(position, h.get(value.substring(1)));
		}else{
			h.put(position,value);
		}
	}

	private boolean isCellValue(String input){
		//String sub=input.substring(1);
		if(h.get(input.substring(1))!=null){
			return true;
		}else
			return false;
	}
	
	public String getLiteral(String position) {
		return h.get(position);
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
		try {
			DoubleEvaluator engine = new DoubleEvaluator();
			int result = (int) engine.evaluate(input);
			return String.valueOf(result);
		} catch (SyntaxError e) {
			return "#Error";
		}
		
	}
	

}