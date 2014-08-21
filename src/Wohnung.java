package malerarbeit;

public class Wohnung {

	int anzahl_raeume;
	double zu_streichende_flaeche;
	double sqrmt; 
	double hoehe; 

	Swing_View view1;

	Wohnung (Swing_View view1){
		this.view1 = view1;
	}

	/**
	 * setzt die Variable <b>hoehe</b> auf einen bestimmten Wert
	 * @param hoehe = übergebener Wert für die Höhe
	 */
	public void setHoehe(double hoehe){
		this.hoehe = hoehe;
	}
	/**
	 * 	
	 * @return gibt den Wert für die Höhe als Double Wert zurück, der in {@link setHoehe} gesetzt wurde
	 */
	public Double getHoehe(){
		return this.hoehe;
	}

	
	/**
	 * setzt die Variable <b>sqrmt</b> auf einen bestimmten Wert
	 * @param sqrmt = übergebener Wert für die Quadratmeter
	 */
	public void setQuadratmeter(double sqrmt){
		this.sqrmt = sqrmt;
	}
	/**
	 * @return gibt den Wert für die Quadratmeter als Double Wert zurück, der in {@link setQuadratmeter} gesetzt wurde
	 */
	public Double getQuadratmeter(){
		return this.sqrmt;
	}

	
	/**
	 * setzt die Variable <b>anzahl_raueme</b> auf einen bestimmten Wert
	 * @param anzahl_raeume = übergebener Wert für die Anzahl der Räume
	 */
	public void setAnzahlRaeume(int anzahl_raeume){
		this.anzahl_raeume = anzahl_raeume;
	}


	/**
	 * <b> Gesamtfläche </b> = 
	 * <p> sqrt(Quadratmeter/Anzahl Räume) * Höhe * 4 * Anzahl Räume <p>
	 * 
	 * der errechnete Wert wird an {@link Swing_View.setZuStreichendeFlaeche} übergeben
	 */
	public void zuStreichendeFlaeche(){
		
		if (isPositive(this.sqrmt) && isPositive(this.anzahl_raeume) && isPositive(this.hoehe)){
			
		zu_streichende_flaeche = Math.round(100.0 * (Math.sqrt(this.sqrmt/this.anzahl_raeume) * this.hoehe * 4 * this.anzahl_raeume)) / 100.0;
		view1.setZuStreichendeFlaeche(zu_streichende_flaeche);
		}
		
		else view1.setZuStreichendeFlaeche(-1);
	}

	/** 
	 * Zum errechnete Wert aus {@link zuStreichendeFlaeche} wird die Quadratmeteranzahl der Wohnung addiert und gerundet an {@link Swing_View.setZuStreichendeFlaeche} übergeben
	 */
	public void addDeckenflaeche(){
		zu_streichende_flaeche += this.sqrmt;
		view1.setZuStreichendeFlaeche(Math.round(100.0 * zu_streichende_flaeche) / 100.0);
	}

	public boolean isPositive(double d) {
		if (d >= 0) {
			return true;
		}
		else return false;
	}

}
