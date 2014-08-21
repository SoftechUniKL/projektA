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
	final double arbeitsleistung = 10.0; // in m² pro h
	
	double farbpreis;
	double farbe;
	double deckkraft;
	double benoetigte_farbe;
	double stundenlohn;
	double abdeckungskosten;
	double materialkosten_abdeckung;
	double endergebnis;
	double sqrmt;
	
	Swing_View swing_view;
	Wohnung wohnung;
	
	Renovierung(Swing_View swing_view) {
		this.swing_view = swing_view;
	}
	
	Renovierung(Wohnung wohnung) {
		this.wohnung = wohnung;
	}
	
	
	/**
	 * setzt die Variable <b>farbpreis</b> auf einen bestimmten Wert
	 * @param farbpreis = Preis für 1 Liter Farbe
	 */
	public void setFarbpreis(double farbpreis){
		this.farbpreis = farbpreis;
	}
	/**
	 * @return gibt den Wert für den Farbpreis als Double Wert zurück, der in {@link setFarbpreis} gesetzt wurde
	 */
	public double getFarbpreis (){
		return this.farbpreis;
	}
	/**
	 * setzt die Variable <b>benoetigte_farbe</b> auf einen bestimmten Wert
	 * @param benoetigte_farbe = Farbe, die für gesamte Renovierung benötigt wird
	 */
	public void setBenoetigteFarbe(double benoetigte_farbe){
		this.benoetigte_farbe = benoetigte_farbe;
	}
	/**
	 * setzt die Variable <b>stundenlohn</b> auf einen bestimmten Wert
	 * @param stundenlohn = Lohn der Maler pro Stunde
	 * <p>
	 * hängt ab vom Bundesland
	 */
	public void setStundenlohn(double stundenlohn){
		this.stundenlohn = stundenlohn;
	}
	/**
	 * Zuweisung der Stundenlöhne der einzelnen Bundesländer zu den Indexpostitionen des Arrays durch Aufruf von {@link Swing_View.setStundenlohn}
	 */
	public void stundenlohnBundesland(){
		
		switch (swing_view.getIndexBundesland()) {

		case 0:	
		case -1:	swing_view.setStundenlohnString("Bitte auswählen");
		break;
		case 1:		swing_view.setStundenlohn(Baden_Wuerttemberg);
		break;
		case 2:		swing_view.setStundenlohn(Bayern); 
		break;
		case 3:		swing_view.setStundenlohn(Berlin);
		break;
		case 4:		swing_view.setStundenlohn(Brandenburg);
		break;
		case 5:		swing_view.setStundenlohn(Bremen);
		break;
		case 6:		swing_view.setStundenlohn(Hamburg);
		break;
		case 7:		swing_view.setStundenlohn(Hessen);
		break;
		case 8:		swing_view.setStundenlohn(Mecklenburg_Vorpommern);
		break;
		case 9:		swing_view.setStundenlohn(Niedersachsen);
		break;
		case 10:	swing_view.setStundenlohn(Nordrhein_Westfalen);
		break;
		case 11:	swing_view.setStundenlohn(Rheinland_Pfalz);
		break;
		case 12:	swing_view.setStundenlohn(Saarland);
		break;
		case 13:	swing_view.setStundenlohn(Sachsen);
		break;
		case 14:	swing_view.setStundenlohn(Sachsen_Anhalt);
		break;
		case 15:	swing_view.setStundenlohn(Schleswig_Holstein);
		break;
		case 16:	swing_view.setStundenlohn(Thueringen);
		break;
		}
	}
	
	
	/**
	 * setzt die Variable <b>deckkraft</b> auf einen bestimmten Wert
	 * @param deckkraft = Fläche in Quadratmetern, die mit 1L Farbe gestrichen werden kann
	 */
	public void setDeckkraft(double deckkraft){
		this.deckkraft = deckkraft;
	}
	/**
	 * @return gibt den Wert für die Deckkraft als Double Wert zurück, der in {@link setDeckkraft} gesetzt wurde
	 */
	public double getDeckkraft(){
		return this.deckkraft;
	}
	
	
	
	/**
	 * bewirkt, dass der Wert für die Deckkraft auf 8.0 gesetzt wird
	 *
	 */
	public void deckkraftDispersionsfarbe() {
		setDeckkraft(deckkraft_dispersionsfarbe);	
	}
	/**
	 * bewirkt, dass der Wert für die Deckkraft auf 7.0 gesetzt wird 
	 */
	public void deckkraftSeidenglanz() {
		setDeckkraft(deckkraft_seidenglanz);
	}
	/**
	 * bewirkt, dass der Wert für die Deckkraft auf 6.0 gesetzt wird 
	 */
	public void deckkraftSchadstofffarbe() {
		setDeckkraft(deckkraft_seidenglanz);
	}
	
	
	
	
	/**
	 * setzt die Variable <b>abdeckungskosten</b> auf einen bestimmten Wert
	 * @param abdeckungskosten = Gesamtkosten für die Abdeckung
	 * <p>
	 * hängt ab von:
	 * <ul>
	 * <li> Fläche
	 * <li> Materialkosten der Abdeckung pro Quadratmeter
	 */
	public void setAbdeckungskosten(double abdeckungskosten){
		this.abdeckungskosten = abdeckungskosten;
	}
	/**
	 * Abdeckungskosten = Fläche * Materialkosten der Abdeckung pro Quadratmeter  <p>
	 * errechneter Wert wird an {@link setAbdeckungskosten} übergeben
	 */
	public void abdeckungskosten(){
		abdeckungskosten = Math.round(100.0 * this.materialkosten_abdeckung * this.sqrmt) / 100.0;
		swing_view.setAbdeckungskosten(abdeckungskosten);
	}
	/**
	 * setzt die Variable <b>sqrmt</b> auf einen bestimmten Wert
	 * @param sqrmt = Quadratmeteranzahl der Wohnung
	 */
	public void setQuadratmeter(double sqrmt){
		this.sqrmt = sqrmt;
	}
	/**
	 * setzt die Variable <b>materialkosten_abdeckung</b> auf einen bestimmten Wert
	 * @param materialkosten_abdeckung = Materialkosten für die Abdeckung
	 */
	public void setMaterialkosten(double materialkosten_abdeckung){
		this.materialkosten_abdeckung = materialkosten_abdeckung;
	}
	
	
	
	/**
	 * setzt die Variable <b>endergebnis</b> auf einen bestimmten Wert
	 * @param endergebnis = Gesamtkosten der Renovierung
	 * <p>
	 * hängt ab von: 
	 * <ul>
	 * <li> Fläche
	 * <li> Höhe
	 * <li> Abdeckungskosten
	 * <li> Bundesland
	 * <li> Farbe
	 * <li> Raucherwohnung
	 */
	public void setEndergebnis(double endergebnis){
		this.endergebnis = endergebnis;
	}
	
	

	/**
	 * Aufruf der Methode {@link setFarbpreis} mit dem Preis für Dispersionsfarbe (4.0€)
	 */
	public void dispersionsfarbe(){
		swing_view.setFarbpreis(preis_dispersionsfarbe);
	}
	/**
	 * Aufruf der Methode {@link setFarbpreis} mit dem Preis für Latex Seidenglanz (5.5€)
	 */
	public void latexfarbe(){
		swing_view.setFarbpreis(preis_latex_seidenglanz);
	}
	/**
	 * Aufruf der Methode {@link setFarbpreis} mit dem Preis für Schadstofffarbe (7.0€)
	 */
	public void schadstofffarbe(){
		swing_view.setFarbpreis(preis_schadstofffarbe);
	}


	/**
	 * Aufruf der Methode {@link setFarbmenge} mit der insgesamt benötigten Farbe<p>
	 * benötigte Farbe = zu streichende Fläche / Deckkraft
	 * @param deckkraft = Menge der Farbe in Litern
	 */
	public void benoetigteFarbe(double deckkraft){
		farbe = Math.round(100.0 * Double.parseDouble(swing_view.getZuStreichendeFlaeche()) / deckkraft) / 100.0;
		swing_view.setFarbmenge(farbe);
	}
	
	
	/**	 
	 * 			Berechnet die Gesamtkosten der Renovierung<p>
	 * 			<b> Gesamtkosten </b> = 
	 * 			<p> gesamt benötigte Farbe * Preis/l Farbe
	 * 			<p>	+ Abdeckungskosten + regionaler Stundenlohn * benötigte Zeit für die Streicharbeit	
	 * 			<p> * 110% (wenn Raucherwohnung)
	 */
	public void gesamtkosten(){
		
		endergebnis = 
				(int)
				(Double.parseDouble(swing_view.getBenoetigteFarbe())
				* Double.parseDouble(swing_view.getPreisProLiter())
				+ Double.parseDouble(swing_view.getAbdeckungskosten())
				+ Double.parseDouble(swing_view.getStundenlohn())
				* (Double.parseDouble(swing_view.getZuStreichendeFlaeche()) / arbeitsleistung));
		
		if(swing_view.raucher == true){
			
			endergebnis = 
					(int)
					(Double.parseDouble(swing_view.getBenoetigteFarbe()) * 1.1
					* Double.parseDouble(swing_view.getPreisProLiter())
					+ Double.parseDouble(swing_view.getAbdeckungskosten())
					+ Double.parseDouble(swing_view.getStundenlohn())
					* (Double.parseDouble(swing_view.getZuStreichendeFlaeche()) / arbeitsleistung));
		}
		swing_view.setEndergebnis(endergebnis);
	}
	
}
