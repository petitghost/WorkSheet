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
			while(m.find()){	
				String position=m.group();
				if(getLiteral(position)==null){
					value=value.replaceAll(position, "0");
				}else if(position.equals(getLiteral(position).substring(1))){ //null.substring is error
					value=value.replaceAll(position, "#Circular");
				}else{
					value=value.replaceAll(position, "("+ get(position) +")");
				}
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
		Pattern p=Pattern.compile("#Circular");
		Matcher m=p.matcher(input);
		if(m.find()){
			return "#Circular";
		}
		try {
			
			DoubleEvaluator engine = new DoubleEvaluator();
			int result = (int) engine.evaluate(input);
			if(result==2147483647)
				return "#Error";
			else
				return String.valueOf(result);
		} catch (SyntaxError e) {		
				return "#Error";
		}
		
	}


}