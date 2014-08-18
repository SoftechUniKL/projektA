package malerarbeit;

import java.rmi.RemoteException;


import junit.framework.TestCase;



public class TestWohnung extends TestCase {

	
	private Swing_View swing;
	private Wohnung wohnung;
	private double h�he;
	private double fl�che;
	private double decke;

	protected void setUp() throws RemoteException {
		swing = new Swing_View();
		wohnung = new Wohnung(swing);
		
		h�he = 2.40;
		
		fl�che = (Math.sqrt(30.6/4.0) * 2.40 * 4.0 * 4.0);
		wohnung.setH�he(h�he);
		wohnung.setAnzahlR�ume(4);
		wohnung.setQuadratmeter(30.6);
		wohnung.zu_streichende_fl�che();	
		
		decke = fl�che + wohnung.getQuadratmeter();
		
	}


	protected void tearDown() throws RemoteException {
		swing = null;
		wohnung = null;
		h�he = 0;
		decke = 0;
		fl�che = 0;
	}

	
	// Asserts testen, ob eine Bedingung erf�llt ist, andernfalls wird der Test nicht bestanden
	// assertEquals(expected value, real value)
	
	public void testsetH�he() throws RemoteException {
		setUp();
		assertEquals(h�he, wohnung.getH�he(), 0.001);
		tearDown();	
	}
	
	public void testzu_streichende_fl�che() throws RemoteException {
		setUp();	
		assertEquals(Double.parseDouble(swing.getzustreichendeFl�che()), fl�che, 0.001);
		tearDown();
	}
	
	public void testaddDeckenfl�che () throws RemoteException {
		setUp();
		wohnung.addDeckenfl�che();
		assertEquals(Double.parseDouble(swing.getzustreichendeFl�che()), decke, 0.001);
		tearDown();
	}
}