package malerarbeit;

import java.rmi.RemoteException;

import junit.framework.TestCase;

public class TestRenovierung extends TestCase {
	
	private Swing_View swing;
	private Renovierung renovierung;
	private String abdeckungskosten;
	
	
	protected void setUp() throws RemoteException {
		swing = new Swing_View();
		renovierung = new Renovierung(swing);
		
		renovierung.setMaterialkosten(0.7); // zB Kreppapier
		renovierung.setQuadratmeter(100);
		renovierung.abdeckungskosten();
		abdeckungskosten = Double.toString(100 * 0.7);
	}
	
	protected void tearDown() throws RemoteException {
		swing = null;
		renovierung = null;
		abdeckungskosten = null;
	}
	
	public void testabdeckungskosten() throws RemoteException {
		setUp();
		assertEquals(abdeckungskosten, swing.getabdeckungskosten());
		tearDown();	
	}
	
	public void teststundenlohn_bundesland() throws RemoteException {
		setUp();
		tearDown();	
	}

}
