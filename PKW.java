package at.ac.univie.swe2016.fm.fahrzeuge;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Die Klasse PKW erweitert eines Fahrzeugs (diese Klasse ist nicht abstrakt, man
 * kann somit ein PKW Objekt erzeugen)
 * 
 * @author Axinya Tokareva, 1368965
 * @version 1.1; 07/11/2016
 */
public class PKW extends Fahrzeug {
	
	/** eindeutige Fahrzeugnummer*/
	private static final long serialVersionUID = 1L;
	/**das Datum der letzten Fahrzeug-Ueberpruefung*/
	private Date ueberpruefungDatum;
	
	/**
	* Konstruktor (es wird vom PKW-Konstruktor der Konstruktor in der abstrakte Klasse Fahrzeug aufgerufen)
	* 
	* @param marke die Marke des Fahrzeugs
	* @param modell das Modell des Fahrzeugs
	* @param baujahr Jahr, wann ein Fahrzeug erzeugt wurde
	* @param grundpreis der unspruengliche Preis
	* @param id Id des Fahrzeugs
	* @param ueberpruefungDatum das Datum der letzten Fahrzeug-Ueberpruefung
	* @see java.util.Date
	 */
	
	public PKW(String marke, String modell, int baujahr, double grundpreis, int id, Date ueberpruefungDatum) {
		super(marke, modell, baujahr, grundpreis, id);
		this.setUeberpruefungDatum(ueberpruefungDatum);

	}

	/**
	 * {@inheritDoc}
	 * @throws IllegalArgumentException wenn das Rabatt negativ ist
	 */
	
	@Override
	public int getRabatt() {
		int grundRabatt = 5 ; 
		@SuppressWarnings("deprecation")
		int diff = this.getNow().getYear() - this.ueberpruefungDatum.getYear();
		int rabatt = (grundRabatt + diff * 2 ) ; 
		if (rabatt > 15)  rabatt = 15; 
		if (rabatt < 0 ) throw new IllegalArgumentException("Rabatt kann nicht negativ sein!");
		else return rabatt;
	}
	
/**
 * Gibt das Ueberpruefungsdatum zurueck	
 * 
 * @return ueberpruefungDatum
 */

	

	
	public Date getUeberpruefungDatum() {
		return ueberpruefungDatum;
	}

	
	
	
	/**
	 * Speichert das Ueberpruefungsdatum
	 * @param ueberpruefungDatum das Datum der letzten Fahrzeug-Ueberpruefung
	 * @throws IllegalArgumentException wenn das Ueberpruefungsdatum groesser als das Baujahr ist
	 */


	public void setUeberpruefungDatum(Date ueberpruefungDatum) {
		Calendar myCal = new GregorianCalendar();
		myCal.setTime(ueberpruefungDatum);
		int year = myCal.get(Calendar.YEAR);
		if (getBaujahr()>year) throw new IllegalArgumentException("Das Ueberpruefungsdatum ist groesser als das Baujahr!");
		else this.ueberpruefungDatum = ueberpruefungDatum;
	}
	
	/**Gibt alle Fahrzeugdaten als String im bestimmten Format ueber PKW zurueck 
	 * 
	 * @see java.text.DecimalFormat
     * @see java.text.DecimalFormatSymbols
	 * @see java.text.SimpleDateFormat
	 * @return Typ, Id, Marke, Modell, Baujahr, Grundpreis, Ueberpruefungsdatum, Preis
	 */
	
	public String toString() {
		DecimalFormat decf = new DecimalFormat("#.00");
		DecimalFormatSymbols symbol = new DecimalFormatSymbols();
		symbol.setDecimalSeparator('.');
		decf.setDecimalFormatSymbols(symbol);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		

		return "Typ:                   " + "PKW" + "\n" + 
		       "Id:                    " + getId() + "\n" + 
		       "Marke:                 " + getMarke() + "\n" + 
		       "Modell:                " + getModell() + "\n" + 
		       "Baujahr:               " + getBaujahr() + "\n" + 
		       "Grundpreis:            " + decf.format(getGrundpreis()) + "\n" + 
		       "Überprüfungsdatum:     " + sdf.format(ueberpruefungDatum.getTime()) + "\n" + 
		       "Preis:                 " + decf.format(getPreis()) + "\n";	
	}
}
