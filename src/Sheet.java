import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
public class Sheet {
	Map<String, String> h = new TreeMap<String, String>();
	String text=""; 
	public String get(String position) {
		text=h.get(position);
		if(h.isEmpty()){
			text="";
		}
		return text; 
	}

	public void put(String position, String value) {
		h.put(position,value);
	//text=value;
	}

}