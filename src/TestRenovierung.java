package malerarbeit;

import java.rmi.RemoteException;

import junit.framework.TestCase;

public class TestRenovierung extends TestCase {
	
	private Swing_View swing;
	private Renovierung renovierung;
	private double abdeckungskosten;
	
	
	protected void setUp() throws RemoteException {
		swing = new Swing_View();
		renovierung = new Renovierung(swing);
		
		renovierung.setMaterialkosten(0.7); // zB Kreppapier
		renovierung.setQuadratmeter(100);
		renovierung.abdeckungskosten();
		abdeckungskosten = (100 * 0.7);
		
		
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

}
