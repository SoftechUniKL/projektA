package malerarbeit;

public class Wohnung {

	int anzahl_raeume;
	double gesamtflaeche; // zu streichende Fl�che
	double sqrmt; // Quadratmeter der Wohnung
	double hoehe; // Deckenh�he


	// Objekt erzeugen
	Swing_View view1;


	Wohnung (Swing_View view1){
		this.view1 = view1;
	}



	public void setH�he(double hoehe){
		this.hoehe = hoehe;
	}
	
	public Double getHoehe(){
		return this.hoehe;
	}

	public void setQuadratmeter(double sqrmt){
		this.sqrmt = sqrmt;
	}
	public Double getQuadratmeter(){
		return this.sqrmt;
	}

	public void setAnzahlRaeume(int anzahl_raeume){
		this.anzahl_raeume = anzahl_raeume;
	}

	public double getFlaeche (double flaeche){
		return flaeche;	
	}


	/*
	 * Gesamtfl�che = sqrt(Quadratmeter/AnzahlR�ume)* H�he * 4 * AnzahlR�ume
	 */
	public void zu_streichende_flaeche(){
		gesamtflaeche = Math.sqrt(this.sqrmt/this.anzahl_raeume) * this.hoehe * 4 * this.anzahl_raeume;
		view1.setZuStreichendeFl�che(gesamtflaeche);
	}

	public void addDeckenflaeche(){
		gesamtflaeche += this.sqrmt;
		view1.setZuStreichendeFl�che(gesamtflaeche);
	}


}
