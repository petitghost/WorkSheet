import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.matheclipse.parser.client.SyntaxError;
import org.matheclipse.parser.client.eval.DoubleEvaluator;

public class Sheet {
	private static final Pattern TableCellName=Pattern.compile("[A-Z]+\\d+");
	public Map<String, String> h = new TreeMap<String, String>();
	
	public String get(String key){
		String text=getLiteral(key);
		
		if(text==null){
			return "";
		}else if(isNumber(text)){
			return text.trim();
		}else if(text.startsWith("=")){
			String value=text.substring(1);
			Matcher m=TableCellName.matcher(value);
			if(key.equals(value)){
				return "#Circular";
			}
			while(m.find()){	
				String position=m.group();
				value=value.replaceAll(position, "("+ get(position) +")");
			}
			return evaluate(value);
		}else{
			return text;
		}
	}
		
	public void put(String position, String value) {
			h.put(position,value);
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