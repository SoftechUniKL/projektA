package malerarbeit;

import java.rmi.RemoteException;


import junit.framework.TestCase;



public class TestWohnung extends TestCase {

	
	private Swing_View swing;
	private Renovierung reno;
	private Wohnung wohnung;
	private double höhe;

	protected void setUp() throws RemoteException {
		swing = new Swing_View();
		reno = new Renovierung(swing);
		wohnung = new Wohnung(swing);
		höhe = 2.3;
	}


	protected void tearDown() throws RemoteException {
		swing = null;
		reno = null;
		wohnung = null;
		höhe = 0;
	}

	
	public void testsetHöhe() throws RemoteException {
		setUp();
		wohnung.setHöhe(höhe);
		// Asserts testen, ob eine Bedingung erfüllt ist, andernfalls wird der Test nicht bestanden
		// assertEquals(expected value, real value)
		assertEquals(höhe, swing.getHöhe());
		tearDown();	
	}
}