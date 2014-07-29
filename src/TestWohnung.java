package malerarbeit;

import java.rmi.RemoteException;
import junit.framework.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestWohnung extens TestCase {
	
	private Swing_View swing;
	private Renovierung reno;
	private Wohnung wohnung;
	private double h�he;
	
	protected void setUp() throws RemoteException {
		swing = new Swing_View;
		reno = new Renovierung(swing);
		wohnung = new Wohnung(swing);
		h�he = 2.3;
	}
	
	protected void tearDown() throws RemoteException {
		swing = Null;
		reno = Null;
		wohnung = Null;
		h�he = Null;
	}
	
	@Test
	public void testsetH�he() {
		setUp();
		wohnung.setH�he(h�he);
		assertEquals(h�he,getH�he());
		tearDown();		
	}
}
