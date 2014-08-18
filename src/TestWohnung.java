package malerarbeit;

import java.rmi.RemoteException;


import junit.framework.TestCase;



public class TestWohnung extends TestCase {

	
	private Swing_View swing;
	private Wohnung wohnung;
	private double höhe;
	private double fläche;
	private double decke;

	protected void setUp() throws RemoteException {
		swing = new Swing_View();
		wohnung = new Wohnung(swing);
		
		höhe = 2.40;
		
		fläche = (Math.sqrt(30.6/4.0) * 2.40 * 4.0 * 4.0);
		wohnung.setHöhe(höhe);
		wohnung.setAnzahlRäume(4);
		wohnung.setQuadratmeter(30.6);
		wohnung.zu_streichende_fläche();	
		
		decke = fläche + wohnung.getQuadratmeter();
		
	}


	protected void tearDown() throws RemoteException {
		swing = null;
		wohnung = null;
		höhe = 0;
		decke = 0;
		fläche = 0;
	}

	
	// Asserts testen, ob eine Bedingung erfüllt ist, andernfalls wird der Test nicht bestanden
	// assertEquals(expected value, real value)
	
	public void testsetHöhe() throws RemoteException {
		setUp();
		assertEquals(höhe, wohnung.getHöhe(), 0.001);
		tearDown();	
	}
	
	public void testzu_streichende_fläche() throws RemoteException {
		setUp();	
		assertEquals(Double.parseDouble(swing.getzustreichendeFläche()), fläche, 0.001);
		tearDown();
	}
	
	public void testaddDeckenfläche () throws RemoteException {
		setUp();
		wohnung.addDeckenfläche();
		assertEquals(Double.parseDouble(swing.getzustreichendeFläche()), decke, 0.001);
		tearDown();
	}
}