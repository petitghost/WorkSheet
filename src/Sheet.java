import java.util.Map;
import java.util.TreeMap;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Sheet {
	private Map<String, String> h = new TreeMap<String, String>();
	
	public String get(String position) {
		String text=h.get(position);
		if(text==null){
			return "";
		}else if(isNumber(text)){
			return text.trim();
		}else{
			return isFormula(text); 
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
	
	private String isFormula(String input){
		Pattern pattern=Pattern.compile("=");
		Matcher matcher=pattern.matcher(input);
		if(matcher.lookingAt()){
			String value=input.substring(1);
			if(value.indexOf("(")==value.indexOf(")")){
				return value;
			}else{				
				return value.substring(value.lastIndexOf("(")+1, value.indexOf(")"));
			}
		}else{
			return input;
		}
	}
	
	public void put(String position, String value) {
		h.put(position,value);		
	}

	public String getLiteral(String theCell) {
		return h.get(theCell);
	}

}