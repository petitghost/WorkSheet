import java.util.Map;
import java.util.TreeMap;
public class Sheet {
	Map<String, String> h = new TreeMap<String, String>();
	Map<String, String> literal=new TreeMap<String, String>();
	
	String text="";
	String word="";
	public String get(String position) {
		text=h.get(position);
		if(h.isEmpty()){
			text="";
		}
		return text; 
	}

	public void put(String position, String value) {
		literal.put(position, value);
		if(value!="" && value!=" "){
		  int vlength=value.length();
		  char first=value.charAt(0);
		  char last=value.charAt(vlength-1);
		  if(first==' ' && last==' '){
		    value=value.trim();
		  }
		}
		h.put(position,value);		
	}

	public String getLiteral(String theCell) {
		word=literal.get(theCell);
		return word;
	}

}