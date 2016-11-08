package at.ac.univie.swe2016.fm.fahrzeuge;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
* implementiert eine abstrakte Klasse, die zum Speichern von Informationen 
* ueber Fahrzeuge eines Online-Haendlers dient (kann kein Objekt erzeugt werden)
*
* @author Axinya Tokareva, 1368965
* @version 1.1; 07/11/2016
*/


public abstract class Fahrzeug implements Serializable {
	/** eindeutige Fahrzeugnummer*/
	private static final long serialVersionUID = 1L;
	/**die Marke des Fahrzeugs.*/
	private String marke ; 
	/**das Modell des Fahrzeugs.*/
	private String modell;
	/**Jahr, wann ein Fahrzeug erzeugt wurde.*/
	private int baujahr;
	/**der unspruengliche Preis.*/
	private double grundpreis;
	/**Id des Fahrzeugs.*/
	private int id;
	/** Parameter des Typs Date */
	private Date now = new Date(); 
	
/**
 * Konstruktor (ermöglicht, die entsprechenden Instanzvariablen direkt zu setzen)

* @param marke die Marke des Fahrzeugs.
* @param modell das Modell des Fahrzeugs.
* @param baujahr Jahr, wann ein Fahrzeug erzeugt wurde.
* @param grundpreis der unspruengliche Preis.
* @param id Id des Fahrzeugs.
 */
	public Fahrzeug (String marke, String modell, int baujahr, double grundpreis, int id ) {
		this.setMarke(marke);
		this.setModell(modell);
		this.setBaujahr(baujahr);
		this.setGrundpreis(grundpreis);
		this.setId(id);
		}
	
/**
 * Gibt die Marke in String zurueck
 * 	
 * @return Marke
 */
	
	
	public String getMarke() {
		return marke;
	}

/**
 * Verantwortlich fuer Speicherung der Marke
 * 
 * @param marke die Marke des Fahrzeugs.
 * @throws IllegalArgumentException wenn Argument-Exception vorkommt.
 */

	public void setMarke(String marke) {
		if (marke.isEmpty() ) throw new IllegalArgumentException("Marke is empty!");
		else this.marke = marke;
	}


/**
 * Gibt das Modell Zurueck
 * 
 * @return Modell
 */
	public String getModell() {
		return modell;
	}


/**
 * Verantwortlich fuer Speicherung des Modells
 * 
 * @param modell das Modell des Fahrzeugs.
 * @throws IllegalArgumentException wenn Argument-Exception vorkommt.
 */

	public void setModell(String modell) {
		if (modell.isEmpty() ) throw new IllegalArgumentException("Modell is empty!");
		else this.modell = modell;
	}



/**
 * Gibt das Jahr zurueck
 * 
 * @return Baujahr
 */
	public int getBaujahr() {
		return baujahr;
	}

/**
 * Verantwortlich fuer Speicherung des Baujahrs
 * 
 * @param baujahr Jahr, wann ein Fahrzeug erzeugt wurde.
 * @see java.util.GregorianCalendar
 * @see java.util.Calendar
 */


	public void setBaujahr(int baujahr) {
		if (baujahr!=(Integer) baujahr) throw new NumberFormatException("Das Baujahr muss Integer sein");
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(now);
		int year = calendar.get(Calendar.YEAR);
		/** IllegalArgumentException wenn das Baujahr in der Zukunft liegt*/
		if (baujahr > year) throw new IllegalArgumentException("Das Baujahr liegt in der Zukunft!");
		if (baujahr <=0) {
			System.err.println("Das Baujahr kann nicht null oder negativ sein!");
			System.exit(1);
		}
		else this.baujahr = baujahr;
	}


/**
 * Gibt das Grundpreis zurueck
 * @return Grundpreis
 */

	public double getGrundpreis() {
		return grundpreis;
	}

/**
 * Verantwortlich fuer Speicherung des Grundpreises.
 * 
 * @param grundpreis das unspruegliche Preis des Fahrzeugs.
 * @throws IllegalArgumentException wenn das Grundpreis negativ ist
 */

	public void setGrundpreis(double grundpreis) {
		/*** throws NumberFotmatException wenn das Grundpreis nicht double ist*/
		if (grundpreis!=(Double) grundpreis ) throw new NumberFormatException("Das Grundpreis muss Double sein");
		if (grundpreis <=0.0 ) throw new IllegalArgumentException("Preis kann nicht negativ oder null sein!");
		else this.grundpreis = grundpreis;
	}


/**
 * Gibt Id des Fahrzeugs zurueck
 * @return Id
 */

	public int getId() {
		return id;
	}


/**
 * Verantwortlich fuer Id-Speicherung 
 * 
 * @param id ID des Fahrzeugs
 * @throws IllegalArgumentException wenn Id negativ ist
 */

	public void setId(int id) {
		if (id!=(Integer) id ) throw new NumberFormatException("Id soll Integer sein!");
		if (id < 0 ) throw new IllegalArgumentException("Id kann nicht negativ sein!");
		else this.id = id;
	}


/**
 * Gibt der Alter zurueck
 * 
 * @see java.util.GregorianCalendar
 * @see java.util.Calendar
 * @return year
 */
	public int getAlter() {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(now);
		int year = calendar.get(Calendar.YEAR);
		return year - this.getBaujahr();
	}
	
/**
* Abstrakte Methode, die der Rabbat zurueck gibt (da die Berechnung des Rabattes fuer jedes Fahrzeug unterschiedlich ist)
* @return Rabatt
*/
	public abstract int getRabatt()  ;
	
/**
 * Gibt der Preis zurueck
 * 
 * @return Preis unter Berücksichtigung des Rabattes
 */
	public double getPreis() {
		return (this.getGrundpreis() - (this.getGrundpreis() * this.getRabatt()) /100 );
	
	}
	
/**
 * Gibt das heutige Datum zurueck
 * 
 * @see java.util.Date
 * @return Datum
 */
	public Date getNow() {
		return now;
	}


}
