package malerarbeit;

import junit.framework.TestCase;

public class WohnungTest extends TestCase {
	private Swing_View swing;
	private Wohnung wohnung;
	private double höhe;
	private String fläche;
	
	protected void setUp() {
		swing = new Swing_View();
		new Renovierung(swing);
		wohnung = new Wohnung(swing);
		höhe = 2.5;
		fläche = Double.toString(Math.sqrt(30.6/4) * 2.5 * 4 * 4);
		wohnung.setHöhe(höhe);
		wohnung.setAnzahlRäume(4);
		wohnung.setQuadratmeter(30.6);
		wohnung.zu_streichende_fläche();
	}
	
	protected void tearDown() {
		swing = null;
		wohnung = null;
		höhe = 0.0;
		fläche = null;
		
	}
	

	public void testsetHöhe() {
		setUp();
		assertEquals(höhe,wohnung.getHöhe());
		tearDown();		
	}
	
	public void testzu_streichende_fläche() {
		setUp();
		assertEquals(swing.getzustreichendeFläche(),fläche);
		tearDown();
		
	}
	
	
}
