package malerarbeit;

import java.rmi.RemoteException;



import junit.framework.TestCase;


public class TestWohnung extends TestCase {

	private Swing_View swing;
	private Wohnung wohnung;
	private double hoehe_expected, hoehe_actual;
	private double flaeche_expected, flaeche_actual;
	private double decke_expected, decke_actual;

	 /**
	  * Initialisierung der Instanzvariablen (Klassenvariablen), um eine definierte Testumgebung zu schaffen
	  */
	protected void setUp() throws RemoteException {
		swing = new Swing_View();
		wohnung = new Wohnung(swing);
		
		hoehe_expected = 2.40;
		flaeche_expected = (Math.sqrt(30.6/4.0) * 2.40 * 4.0 * 4.0);
		wohnung.setHoehe(hoehe_expected);
		hoehe_actual = wohnung.getHoehe();
		wohnung.setAnzahlRaeume(4);
		wohnung.setQuadratmeter(30.6);
		wohnung.zuStreichendeFlaeche();	
		
		flaeche_actual = Double.parseDouble(swing.getZuStreichendeFlaeche());
		decke_expected = flaeche_expected + wohnung.getQuadratmeter();
		
	}
	
	 /**
	  * Initialisierung der Instanzvariablen (Klassenvariablen), um eine definierte Testumgebung für den Fall einer negativen Eingabe zu schaffen
	  */
	protected void setUp_negativ() throws RemoteException {
		
		hoehe_expected = -2.40;
		wohnung.setHoehe(hoehe_expected);
		hoehe_actual = wohnung.getHoehe();
		wohnung.setAnzahlRaeume(4);
		wohnung.setQuadratmeter(30.6);
		wohnung.zuStreichendeFlaeche();	
		flaeche_expected = -1;
		flaeche_actual = Double.parseDouble(swing.getZuStreichendeFlaeche());
	}
	
	 /**
	  * Initialisierung der Instanzvariablen (Klassenvariablen), um eine definierte Testumgebung für Randeingaben zu schaffen
	  */
	protected void setUp_rand() throws RemoteException {
		
		hoehe_expected = 2.40;
		wohnung.setHoehe(hoehe_expected);
		hoehe_actual = wohnung.getHoehe();
		wohnung.setAnzahlRaeume(4);
		wohnung.setQuadratmeter(0);
		wohnung.zuStreichendeFlaeche();	
		flaeche_expected = 0;
		flaeche_actual = Double.parseDouble(swing.getZuStreichendeFlaeche());
	}
	
	 /**
	  * Testressourcen werden wieder freigegeben
	  */
	protected void tearDown() throws RemoteException {
		swing = null;
		wohnung = null;
		hoehe_expected = 0;
		hoehe_actual = 0;
		decke_expected = 0;
		decke_actual = 0;
		flaeche_expected = 0;
		flaeche_actual = 0;
	}

	
	/**
	 * Testet, ob erwarteter und tatsächlicher Wert für die Höhe übereinstimmen
	 * @throws RemoteException
	 */
	public void testsetHoehe() throws RemoteException {
		setUp();
		assertEquals(hoehe_expected, hoehe_actual, 0.001);
		tearDown();	
	}
	
	
	
	/**
	 * Testet, ob erwarteter und tatsächlicher Wert für die zu streichende Fläche übereinstimmen
	 * @throws RemoteException
	 */
	public void testzu_streichende_flaeche() throws RemoteException {
		setUp();	
		assertEquals(flaeche_actual, flaeche_expected, 0.001);
		tearDown();
	}
	/**
	 * Testet, ob erwarteter und tatsächlicher Wert für die zu streichende Fläche übereinstimmen
	 * @throws RemoteException
	 */
	public void testzu_streichende_flaeche_negativ() throws RemoteException {
		setUp_negativ();	
		assertEquals(flaeche_actual, flaeche_expected, 0.001);
		tearDown();
	}
	/**
	 * Testet, ob erwarteter und tatsächlicher Wert für die zu streichende Fläche übereinstimmen
	 * @throws RemoteException
	 */
	public void testzu_streichende_flaeche_rand() throws RemoteException {
		setUp_rand();	
		assertEquals(flaeche_actual, flaeche_expected, 0.001);
		tearDown();
	}
	
	
	
	
	/**
	 * Testet, ob erwarteter und tatsächlicher Wert für die zu streichende Fläche inkl. Deckenanstrich übereinstimmen
	 * @throws RemoteException
	 */
	public void testaddDeckenflaeche () throws RemoteException {
		setUp();
		wohnung.addDeckenflaeche();
		decke_actual = Double.parseDouble(swing.getZuStreichendeFlaeche());
		assertEquals(decke_actual, decke_expected, 0.001);
		tearDown();
	}
}