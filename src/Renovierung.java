package malerarbeit;

public class Renovierung {
	
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

	final double preis_dispersionsfarbe = 4.0; 
	final double preis_latex_seidenglanz = 5.5;
	final double preis_schadstofffarbe = 7.0;
	final double deckkraft_dispersionsfarbe = 8.0;
	final double deckkraft_seidenglanz = 7.0;
	final double deckkraft_schadstofffarbe = 6.0;
	final double arbeitsleistung = 10.0; // in m²/h
	
	double farbpreis;
	double farbe;
	double deckkraft;
	double benoetigte_farbe;
	double stundenlohn;
	double abdeckungskosten;
	double materialkosten_abdeckung;
	double endergebnis;
	double sqrmt;
	
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
	
	public void setBenoetigteFarbe(double benötigte_farbe){
		this.benoetigte_farbe = benötigte_farbe;
	}
	
	public void setStundenlohn(double stundenlohn){
		this.stundenlohn = stundenlohn;
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
	
	
	public void setDeckkraft(double deckkraft){
		this.deckkraft = deckkraft;
	}
	public double getDeckkraft(){
		return this.deckkraft;
	}
	public void deckkraft_dispersionsfarbe() {
		setDeckkraft(deckkraft_dispersionsfarbe);	
	}
	public void deckkraft_seidenglanz() {
		setDeckkraft(deckkraft_seidenglanz);
	}
	public void deckkraft_schadstofffarbe() {
		setDeckkraft(deckkraft_seidenglanz);
	}
	
	
	
	public void setAbdeckungskosten(double abdeckungskosten){
		this.abdeckungskosten = abdeckungskosten;
	}
	public void abdeckungskosten(){
		abdeckungskosten = Math.round(100.0 * this.materialkosten_abdeckung * this.sqrmt) / 100.0;
		view2.setKostenFuerAbdeckung(abdeckungskosten);
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
	
	


	public void dispersionsfarbe(){
		view2.setFarbpreis(preis_dispersionsfarbe);
	}
	public void latexfarbe(){
		view2.setFarbpreis(preis_latex_seidenglanz);
	}
	public void schadstofffarbe(){
		view2.setFarbpreis(preis_schadstofffarbe);
	}

	
	
	public void benötigte_farbe(double deckkraft){
		farbe = Math.round(100.0 * Double.parseDouble(view2.getzustreichendeFläche()) / deckkraft) / 100.0;
		view2.setFarbmenge(farbe);
	}
	
	public void gesamtkosten(){
		
		endergebnis = 
				(int)
				(Double.parseDouble(view2.getbenoetigtefarbe())
				* Double.parseDouble(view2.getpreisproliter())
				+ Double.parseDouble(view2.getabdeckungskosten())
				+ Double.parseDouble(view2.getregionalerStundenlohn())
				* (Double.parseDouble(view2.getzustreichendeFläche()) / arbeitsleistung));
		
		if(view2.raucher == true){
			
			endergebnis = 
					(int)
					(Double.parseDouble(view2.getbenoetigtefarbe()) * 1.1
					* Double.parseDouble(view2.getpreisproliter())
					+ Double.parseDouble(view2.getabdeckungskosten())
					+ Double.parseDouble(view2.getregionalerStundenlohn())
					* (Double.parseDouble(view2.getzustreichendeFläche()) / arbeitsleistung));
		}
		view2.setEndergebnis(endergebnis);
	}
	
}
