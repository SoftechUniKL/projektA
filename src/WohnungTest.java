package malerarbeit;

import junit.framework.TestCase;

public class WohnungTest extends TestCase {
	private Swing_View swing;
	private Wohnung wohnung;
	private double h�he;
	
	protected void setUp() {
		swing = new Swing_View();
		new Renovierung(swing);
		wohnung = new Wohnung(swing);
		h�he = 2.3;
	}
	
	protected void tearDown() {
		swing = null;
		wohnung = null;
		h�he = 0.0;
	}
	

	public void testsetH�he() {
		setUp();
		wohnung.setH�he(h�he);
		assertEquals(h�he,wohnung.getH�he());
		tearDown();		
	}
	
	public void testzu_streichende_fl�che() {
		setUp();
		wohnung.setH�he(2.5);
		wohnung.setAnzahlR�ume(4);
		wohnung.setQuadratmeter(30.6);
		wohnung.zu_streichende_fl�che();
		assertEquals(swing.getzustreichendeFl�che(),Double.toString(Math.sqrt(30.6/4) * 2.5 * 4 * 4));
		tearDown();
		
	}
	
	
}
