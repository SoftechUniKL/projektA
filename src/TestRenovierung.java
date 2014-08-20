package malerarbeit;

import java.rmi.RemoteException;

import junit.framework.TestCase;

public class TestRenovierung extends TestCase {
	
	private Swing_View swing;
	private Renovierung renovierung;
	private Wohnung wohnung;
	private double abdeckungskosten;
	private double flaeche;
	private double benoetigtefarbe;

	
	protected void setUp() throws RemoteException {
		
		swing = new Swing_View();
		renovierung = new Renovierung(swing);
		wohnung = new Wohnung(swing);
		
		renovierung.setMaterialkosten(0.7);
		renovierung.setQuadratmeter(100);
		renovierung.abdeckungskosten();
		abdeckungskosten = (100 * 0.7);
		
		
		wohnung.setHoehe(2.4);
		wohnung.setAnzahlRaeume(4);
		wohnung.setQuadratmeter(130.6);
		wohnung.zu_streichende_flaeche();
		renovierung.deckkraft_dispersionsfarbe();
		
		flaeche = Math.sqrt(130.6/4.0) * 2.40 * 4.0 * 4.0;
		benoetigtefarbe = Math.round(100.0 * (flaeche) / 8.0) / 100.0;
	}
	
	protected void tearDown() throws RemoteException {
		swing = null;
		renovierung = null;
		abdeckungskosten = 0;
	}
	
	public void testabdeckungskosten() throws RemoteException {
		setUp();
		assertEquals(abdeckungskosten, Double.parseDouble(swing.getabdeckungskosten()), 0.001);
		tearDown();	
	}
	
	public void teststundenlohn_bundesland() throws RemoteException {
		setUp();
		tearDown();	
	}
	
	public void testbenötigte_farbe() throws RemoteException {
		setUp();
		assertEquals(100.0* (Double.parseDouble(swing.getzustreichendeFläche())/renovierung.getDeckkraft()) / 100.0, benoetigtefarbe, 0.005);
		tearDown();	
	}
	
	public void testgesamtkosten() throws RemoteException {
		setUp();
		tearDown();	
	}

}
