import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.matheclipse.parser.client.SyntaxError;
import org.matheclipse.parser.client.eval.DoubleEvaluator;

public class Sheet {
	public Map<String, String> h = new TreeMap<String, String>();
	
	public String get(String position){
		String text=h.get(position);
		
		if(text==null){
			return "";
		}else if(isNumber(text)){
			return text.trim();
		}else if(text.startsWith("=")){
			String sub=text.substring(1);
			return evaluate(CellValue(sub));
			
		}else{
			return text;
		}
	}
		
	public void put(String position, String value) {
			h.put(position,value);
	}

	private String CellValue(String input){
		String replace="";
		Pattern p=Pattern.compile("[A-Z]+\\d+");
		Matcher m=p.matcher(input);
		while(m.find()){	
			replace=input.replaceAll(m.group(), h.get(m.group()));
			input=replace;
		}
		if(m.matches()){
			return replace;
		}else
			return input;
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