import java.util.Hashtable;
public class Sheet {
	Hashtable<String, String> h=new Hashtable<String, String>();
	String text=""; 
	public String get(String position) {
		text=h.get(position);
		if(h.get(position)==null){
			text="";
		}
		return text; 
	}

	public void put(String position, String value) {
		h.put(position,value);
	//text=value;
	}

}