package at.ac.univie.swe2016.fm.fahrzeuge;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;

/**
 * Die Klasse LKW erweitert eines Fahrzeugs (diese Klasse ist nicht abstrakt, man
 * kann somit ein LKW Objekt erzeugen)
 * 
 * @author Axinya Tokareva, 1368965
 * @version 1.1; 07/11/2016
 */

public class LKW extends Fahrzeug{

	/** eindeutige Fahrzeugnummer*/
	private static final long serialVersionUID = 1L;

	/**
	* Konstruktor (es wird vom LKW-Konstruktor der Konstruktor in der abstrakte Klasse Fahrzeug aufgerufen)
	* @param marke die Marke des Fahrzeugs
	* @param modell das Modell des Fahrzeugs
	* @param baujahr Jahr, wann ein Fahrzeug erzeugt wurde
	* @param grundpreis der unspruengliche Preis
	* @param id Id des Fahrzeugs
	 */
	
	public LKW(String marke, String modell, int baujahr, double grundpreis, int id) {
		super(marke, modell, baujahr, grundpreis, id);
	}

	/**
	 * {@inheritDoc}
	 * @throws IllegalArgumentException wenn das Rabatt negativ ist
	 *
	 */
	
	@Override
	public int getRabatt() {
		int rabatt = this.getAlter() * 5 ; 
		if (rabatt > 20) rabatt=20;
		if (rabatt < 0 ) throw new IllegalArgumentException("Rabatt kann nicht negativ sein!");
		else return rabatt;
	}

	/**Gibt alle Fahrzeugdaten als String im bestimmten Format eueber LKW zurueck
	 * 
	 * @see java.text.DecimalFormat
     * @see java.text.DecimalFormatSymbols
	 * @see java.text.SimpleDateFormat 
	 * @return Typ, Id, Marke, Modell, Baujahr, Grundpreis, Preis
	 */
	@SuppressWarnings("unused")
	public String toString() {
		DecimalFormat decf = new DecimalFormat("#.00");
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator('.');
		decf.setDecimalFormatSymbols(symbol);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		

		return "Typ:                   " + "LKW" + "\n" + 
		       "Id:                    " + getId() + "\n" + 
		       "Marke:                 " + getMarke() + "\n" + 
		       "Modell:                " + getModell() + "\n" + 
		       "Baujahr:               " + getBaujahr() + "\n" + 
		       "Grundpreis:            " + decf.format(getGrundpreis()) + "\n" + 
		       "Preis:                 " + decf.format(getPreis()) + "\n";	
	}
}
