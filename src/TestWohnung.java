package malerarbeit;

import java.rmi.RemoteException;
import junit.framework.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestWohnung extens TestCase {
	
	private Swing_View swing;
	private Renovierung reno;
	private Wohnung wohnung;
	private double höhe;
	
	protected void setUp() throws RemoteException {
		swing = new Swing_View;
		reno = new Renovierung(swing);
		wohnung = new Wohnung(swing);
		höhe = 2.3;
	}
	
	protected void tearDown() throws RemoteException {
		swing = Null;
		reno = Null;
		wohnung = Null;
		höhe = Null;
	}
	
	@Test
	public void testsetHöhe() {
		setUp();
		wohnung.setHöhe(höhe);
		assertEquals(höhe,getHöhe());
		tearDown();		
	}
}
