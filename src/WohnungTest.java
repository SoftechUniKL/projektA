package malerarbeit;

import junit.framework.TestCase;

public class WohnungTest extends TestCase {
	private Swing_View swing;
	private Wohnung wohnung;
	private double höhe;
	
	protected void setUp() {
		swing = new Swing_View();
		new Renovierung(swing);
		wohnung = new Wohnung(swing);
		höhe = 2.3;
	}
	
	protected void tearDown() {
		swing = null;
		wohnung = null;
		höhe = 0.0;
	}
	

	public void testsetHöhe() {
		setUp();
		wohnung.setHöhe(höhe);
		assertEquals(höhe,wohnung.getHöhe());
		tearDown();		
	}
	
	public void testzu_streichende_fläche() {
		setUp();
		wohnung.setHöhe(2.5);
		wohnung.setAnzahlRäume(4);
		wohnung.setQuadratmeter(30.6);
		wohnung.zu_streichende_fläche();
		assertEquals(swing.getzustreichendeFläche(),Double.toString(Math.sqrt(30.6/4) * 2.5 * 4 * 4));
		tearDown();
		
	}
	
	
}
