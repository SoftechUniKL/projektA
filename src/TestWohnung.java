package malerarbeit;

import java.rmi.RemoteException;


import junit.framework.TestCase;



public class TestWohnung extends TestCase {

	
	private Swing_View swing;
	private Wohnung wohnung;
	private double höhe;
	private String fläche;
	private String decke;

	protected void setUp() throws RemoteException {
		swing = new Swing_View();
		wohnung = new Wohnung(swing);
		
		höhe = 2.40;
		
		fläche = Double.toString(Math.sqrt(30.6/4.0) * 2.40 * 4.0 * 4.0);
		wohnung.setHöhe(höhe);
		wohnung.setAnzahlRäume(4);
		wohnung.setQuadratmeter(30.6);
		wohnung.zu_streichende_fläche();	
		
		decke = Double.toString(Double.parseDouble(fläche) + wohnung.getQuadratmeter());
		
	}


	protected void tearDown() throws RemoteException {
		swing = null;
		wohnung = null;
		höhe = 0;
		decke = null;
		fläche = null;
	}

	
	public void testsetHöhe() throws RemoteException {
		setUp();
		// Asserts testen, ob eine Bedingung erfüllt ist, andernfalls wird der Test nicht bestanden
		// assertEquals(expected value, real value)
		assertEquals(höhe, wohnung.getHöhe());
		tearDown();	
	}
	
	public void testzu_streichende_fläche() throws RemoteException {
		setUp();	
		assertEquals(swing.getzustreichendeFläche(), fläche);
		tearDown();
	}
	
	public void testaddDeckenfläche () throws RemoteException {
		setUp();
		wohnung.addDeckenfläche();
		assertEquals(swing.getzustreichendeFläche(), decke);
		tearDown();
	}
}