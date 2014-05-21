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
	// Implement each test before going to the next one; then refactor.

	public void testThatManyCellsExist() {
	  Sheet sheet = new Sheet();
	  sheet.put("A1", "First");
	  sheet.put("X27", "Second");
	  sheet.put("ZX901", "Third");

	  assertEquals("A1", "First", sheet.get("A1"));
	  assertEquals("X27", "Second", sheet.get("X27"));
	  assertEquals("ZX901", "Third", sheet.get("ZX901"));

	  sheet.put("A1", "Fourth");
	  assertEquals("A1 after", "Fourth", sheet.get("A1"));
	  assertEquals("X27 same", "Second", sheet.get("X27"));
	  assertEquals("ZX901 same", "Third", sheet.get("ZX901"));
	}
	
}
