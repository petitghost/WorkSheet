import junit.framework.TestCase;

public class Part1 extends TestCase {
	public void testThatCellsAreEmptyByDefault() {
		Sheet sheet = new Sheet();
		assertEquals("", sheet.get("A1"));
		assertEquals("", sheet.get("ZX347"));
	}
}
