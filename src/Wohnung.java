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



	public void setHoehe(double hoehe){
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


	public void zuStreichendeFläche(){
		zu_streichende_flaeche = Math.round(100.0 * (Math.sqrt(this.sqrmt/this.anzahl_raeume) * this.hoehe * 4 * this.anzahl_raeume)) / 100.0;
		view1.setZuStreichendeFlaeche(zu_streichende_flaeche);
	}

	public void addDeckenflaeche(){
		zu_streichende_flaeche += this.sqrmt;
		view1.setZuStreichendeFlaeche(Math.round(100.0 * zu_streichende_flaeche) / 100.0);
	}


}
