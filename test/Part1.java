import junit.framework.TestCase;

public class Part1 extends TestCase {
	public void testThatCellsAreEmptyByDefault() {
		Sheet sheet = new Sheet();
		assertEquals("", sheet.get("A1"));
		assertEquals("", sheet.get("ZX347"));
	}

	// Implement each test before going to the next one.

	public void testThatTextCellsAreStored() {
	  Sheet sheet = new Sheet();
	  String theCell = "A21";
	  
	  sheet.put(theCell, "A string");
	  assertEquals("A string", sheet.get(theCell));

	  sheet.put(theCell, "A different string");
	  assertEquals("A different string", sheet.get(theCell));

	  sheet.put(theCell, "");
	  assertEquals("", sheet.get(theCell));
	}
}
