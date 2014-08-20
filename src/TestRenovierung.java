package malerarbeit;

import java.rmi.RemoteException;

import junit.framework.TestCase;

public class TestRenovierung extends TestCase {
	
	private Swing_View swing;
	private Renovierung renovierung;
	private Wohnung wohnung;
	
	private double abdeckungskosten_expected, abdeckungskosten_actual;
	private double flaeche_expected;
	private double benoetigtefarbe_expected, benoetigtefarbe_actual;
	private double stundenlohn_expected, stundenlohn_actual;
	private int gesamt_expected, gesamt_actual;

	
	protected void setUp() throws RemoteException {
		
		swing = new Swing_View();
		renovierung = new Renovierung(swing);
		wohnung = new Wohnung(swing);
		
		renovierung.setMaterialkosten(0.7);
		renovierung.setQuadratmeter(100);
		renovierung.abdeckungskosten();
		abdeckungskosten_expected = (100 * 0.7);
		abdeckungskosten_actual = Double.parseDouble(swing.getAbdeckungskosten());
		
		wohnung.setHoehe(2.4);
		wohnung.setAnzahlRaeume(4);
		wohnung.setQuadratmeter(130.6);
		wohnung.zuStreichendeFläche();
		renovierung.deckkraftDispersionsfarbe();
		flaeche_expected = Math.sqrt(130.6/4.0) * 2.40 * 4.0 * 4.0; // hier: ohne Decke
		benoetigtefarbe_expected = Math.round(100.0 * (flaeche_expected) / 8.0) / 100.0;
		benoetigtefarbe_actual = 100.0 * (Double.parseDouble(swing.getZuStreichendeFlaeche())/renovierung.getDeckkraft()) / 100.0;
		
		swing.setIndexBundesland(5);
		renovierung.stundenlohnBundesland();
		stundenlohn_expected = 32.10;
		stundenlohn_actual = Double.parseDouble(swing.getStundenlohn());
		
		
		renovierung.dispersionsfarbe();
		renovierung.benoetigteFarbe(renovierung.deckkraft_dispersionsfarbe);
		swing.setRaucherFalse();
		renovierung.gesamtkosten();
		
		gesamt_expected = (int)
				(benoetigtefarbe_expected
				*4.0
				+ abdeckungskosten_expected
				+ 32.10
				* flaeche_expected / 10.0);
		
		gesamt_actual = swing.getEndergebnis();
		
	}
	
	protected void tearDown() throws RemoteException {
		swing = null;
		renovierung = null;
		abdeckungskosten_expected = 0;
	}
	
	public void testabdeckungskosten() throws RemoteException {
		setUp();
		assertEquals(abdeckungskosten_expected, abdeckungskosten_actual, 0.001);
		tearDown();	
	}
	
	public void testbenötigte_farbe() throws RemoteException {
		setUp();
		assertEquals(benoetigtefarbe_actual, benoetigtefarbe_expected, 0.005);
		tearDown();	
	}
	
	public void teststundenlohn_bundesland() throws RemoteException {
		setUp();
		assertEquals(stundenlohn_actual, stundenlohn_expected, 0.001);
		tearDown();	
	}
	
	public void testgesamtkosten() throws RemoteException {
		setUp();
		assertEquals(gesamt_expected, gesamt_actual);
		tearDown();	
	}

}
