package malerarbeit;

import java.rmi.RemoteException;


import junit.framework.TestCase;


public class TestWohnung extends TestCase {

	
	private Swing_View swing;
	private Wohnung wohnung;
	private double hoehe;
	private double flaeche;
	private double decke;

	protected void setUp() throws RemoteException {
		swing = new Swing_View();
		wohnung = new Wohnung(swing);
		
		hoehe = 2.40;
		
		flaeche = (Math.sqrt(30.6/4.0) * 2.40 * 4.0 * 4.0);
		wohnung.setHoehe(hoehe);
		wohnung.setAnzahlRaeume(4);
		wohnung.setQuadratmeter(30.6);
		wohnung.zu_streichende_flaeche();	
		
		decke = flaeche + wohnung.getQuadratmeter();
		
	}


	protected void tearDown() throws RemoteException {
		swing = null;
		wohnung = null;
		hoehe = 0;
		decke = 0;
		flaeche = 0;
	}

	
	// Asserts testen, ob eine Bedingung erfüllt ist, andernfalls wird der Test nicht bestanden
	public void testsetHoehe() throws RemoteException {
		setUp();
		assertEquals(hoehe, wohnung.getHoehe(), 0.001);
		tearDown();	
	}
	
	public void testzu_streichende_flaeche() throws RemoteException {
		setUp();	
		assertEquals(Double.parseDouble(swing.getzustreichendeFläche()), flaeche, 0.001);
		tearDown();
	}
	
	public void testaddDeckenflaeche () throws RemoteException {
		setUp();
		wohnung.addDeckenflaeche();
		assertEquals(Double.parseDouble(swing.getzustreichendeFläche()), decke, 0.001);
		tearDown();
	}
}