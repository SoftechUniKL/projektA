package malerarbeit;

import java.rmi.RemoteException;


import junit.framework.TestCase;



public class TestWohnung extends TestCase {

	
	private Swing_View swing;
	private Renovierung reno;
	private Wohnung wohnung;
	private double h�he;

	protected void setUp() throws RemoteException {
		swing = new Swing_View();
		reno = new Renovierung(swing);
		wohnung = new Wohnung(swing);
		h�he = 2.3;
	}


	protected void tearDown() throws RemoteException {
		swing = null;
		reno = null;
		wohnung = null;
		h�he = 0;
	}

	
	public void testsetH�he() throws RemoteException {
		setUp();
		wohnung.setH�he(h�he);
		// Asserts testen, ob eine Bedingung erf�llt ist, andernfalls wird der Test nicht bestanden
		// assertEquals(expected value, real value)
		assertEquals(h�he, swing.getH�he());
		tearDown();	
	}
}