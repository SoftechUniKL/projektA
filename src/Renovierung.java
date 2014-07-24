package malerarbeit;

public class Renovierung {
	
	/* Bundesländer
	 * 
	 * Malerkosten:
	 * http://malerundlackiererinfo.de/so-viel-gehalt-bekommen-maler-und-lackierer/
	   (Annahme: 22 Arbeitstage/Monat)
	 */
	final double Sachsen_Anhalt = 28.66;
	final double Saarland = 29.18;
	final double Brandenburg = 29.59;
	final double Niedersachsen = 30.02; 
	final double Thueringen = 30.04;
	final double Berlin = 30.09;
	final double Hamburg = 30.23;
	final double Mecklenburg_Vorpommern = 30.85;
	final double Sachsen = 31.36;
	final double Schleswig_Holstein = 31.36;
	final double Nordrhein_Westfalen = 31.58;
	final double Bayern = 31.72;
	final double Bremen = 32.10;
	final double Hessen = 32.15;
	final double Baden_Wuerttemberg = 32.80;
	final double Rheinland_Pfalz = 34.20;

	// Berechnung
	final double preis_dispersionsfarbe = 4.0; // jeweils pro Liter Farbe
	final double preis_latex_seidenglanz = 5.5;
	final double preis_schadstofffarbe = 7.0;
	final double deckkraft = 7.0; // Quadratmeter pro Liter (für alle Farben gleich)
	final double arbeitsleistung = 10.0; // Quadratmeter pro Stunde
	
	double farbpreis;
	double benötigte_farbe;
	double stundenlohn;
	double abdeckungskosten;
	double materialkosten_abdeckung;
	double endergebnis;
	double sqrmt; // Quadratmeter der Wohnung
	

	
	// Objekte erzeugen
	Swing_View view2;
	Wohnung w2;
	
	
	
	Renovierung(Swing_View view2) {
		this.view2 = view2;
	}
	Renovierung(Wohnung w2) {
		this.w2 = w2;
	}
	
	
	
	public void setFarbpreis(double farbpreis){
		this.farbpreis = farbpreis;
	}
	
	public void setBenötigteFarbe(double benötigte_farbe){
		this.benötigte_farbe = benötigte_farbe;
	}
	
	
	public void setStundenlohn(double stundenlohn){
		this.stundenlohn = stundenlohn;
	}
	
	
	public void setAbdeckungskosten(double abdeckungskosten){
		this.abdeckungskosten = abdeckungskosten;
	}
	
	public void setQuadratmeter(double sqrmt){
		this.sqrmt = sqrmt;
	}
	
	public void setMaterialkosten(double materialkosten_abdeckung){
		this.materialkosten_abdeckung = materialkosten_abdeckung;
	}
	
	public void setEndergebnis(double endergebnis){
		this.endergebnis = endergebnis;
	}
	
	
	public void abzudeckende_fläche(){
		abdeckungskosten = this.materialkosten_abdeckung * this.sqrmt;
		view2.setKostenFürAbdeckung(abdeckungskosten);
	}
	
	public void stundenlohn_bundesland(){
		
		switch (view2.bundesland()) {

		case 1:		view2.set_tf_Stundenlohn(Baden_Wuerttemberg);
		break;
		case 2:		view2.set_tf_Stundenlohn(Bayern); 
		break;
		case 3:		view2.set_tf_Stundenlohn(Berlin);
		break;
		case 4:		view2.set_tf_Stundenlohn(Brandenburg);
		break;
		case 5:		view2.set_tf_Stundenlohn(Bremen);
		break;
		case 6:		view2.set_tf_Stundenlohn(Hamburg);
		break;
		case 7:		view2.set_tf_Stundenlohn(Hessen);
		break;
		case 8:		view2.set_tf_Stundenlohn(Mecklenburg_Vorpommern);
		break;
		case 9:		view2.set_tf_Stundenlohn(Niedersachsen);
		break;
		case 10:	view2.set_tf_Stundenlohn(Nordrhein_Westfalen);
		break;
		case 11:	view2.set_tf_Stundenlohn(Rheinland_Pfalz);
		break;
		case 12:	view2.set_tf_Stundenlohn(Saarland);
		break;
		case 13:	view2.set_tf_Stundenlohn(Sachsen);
		break;
		case 14:	view2.set_tf_Stundenlohn(Sachsen_Anhalt);
		break;
		case 15:	view2.set_tf_Stundenlohn(Schleswig_Holstein);
		break;
		case 16:	view2.set_tf_Stundenlohn(Thueringen);
		break;
		}
	}

	public void dispersionsfarbe(){
		view2.setFarbpreis(preis_dispersionsfarbe);
	}
	public void latexfarbe(){
		view2.setFarbpreis(preis_latex_seidenglanz);
	}
	public void schadstofffarbe(){
		view2.setFarbpreis(preis_schadstofffarbe);
	}

	public void benötigte_farbe(double liter){
		view2.setFarbmenge(liter);
	}
	
	
	/*	 Gesamtkosten = 
	 * 			(	gesamt benötigte Farbe
	 * 				* Preis/l Farbe
	 * 				+ Abdeckungskosten
	 * 				+ regionaler Stundenlohn * benötigte Zeit für die Streicharbeit   )
	 * 				
	 * 			* 1,1 (wenn Raucherwohnung)
	 */
	public void gesamtkosten(){
		
		endergebnis = 
				(int)
				(Double.parseDouble(view2.getbenötigtefarbe())
				* Double.parseDouble(view2.getpreisproliter())
				+ Double.parseDouble(view2.getabdeckungskosten())
				+ Double.parseDouble(view2.getregionalerStundenlohn())
				* (Double.parseDouble(view2.getzustreichendeFläche()) / arbeitsleistung));
		
		// 10 % Extra-Farbe
		if(view2.raucher == true){
			
			endergebnis = 
					(int)
					(Double.parseDouble(view2.getbenötigtefarbe()) * 1.1
					* Double.parseDouble(view2.getpreisproliter())
					+ Double.parseDouble(view2.getabdeckungskosten())
					+ Double.parseDouble(view2.getregionalerStundenlohn())
					* (Double.parseDouble(view2.getzustreichendeFläche()) / arbeitsleistung));
		}
		view2.setEndergebnis(endergebnis);
	}
	
}
