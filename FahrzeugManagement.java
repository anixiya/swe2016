package at.ac.univie.swe2016.fm;

import swe2016.fm.fahrzeuge.dao.FahrzeugDAO;
import swe2016.fm.fahrzeuge.dao.SerializedFahrzeugDAO;
import java.util.ArrayList;
import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;
import at.ac.univie.swe2016.fm.fahrzeuge.LKW;
import at.ac.univie.swe2016.fm.fahrzeuge.PKW;


/**
* Die Klasse FahrzeugManagement implementiert die Logik der Fahrzeugverwaltung
* 
* @author Axinya Tokareva, 1368965
* @version 1.1; 07/11/2016
*/
public class FahrzeugManagement {
	/**eine Instanzvariable fahrzeugDAO*/
	private FahrzeugDAO farzeugDao; 
	
	/**
	 * Konstruktor (setzt Instanzvariablen direkt)
	 * @param dateiname Name einer Datei
	 * */
	public FahrzeugManagement(String dateiname){
		this.farzeugDao = new SerializedFahrzeugDAO(dateiname);
	}
	
	/**
	 * Benutzt eine Liste, um alle persistent gespeicherten Fahrzeugobjekte zurückzugeben
	 *   
	 * @see swe2016.fm.fahrzeuge.dao.SerializedFahrzeugDAO#getFahrzeugList()   
	 * @return alle gespeicherte Fahrzeugobjekte
	 */
	public ArrayList <Fahrzeug> getFahrzeugListe(){
		return this.farzeugDao.getFahrzeugList();
	}
	
	/**
	 * Gibt ein Fahrzeug-Objekt, anhand der Fahrzeugnummer
	 *  
	 * @param id Id des Fahrzeugs
	 * @see swe2016.fm.fahrzeuge.dao.SerializedFahrzeugDAO#getFahrzeugbyId(int id)   
	 * @return Fahrzeug-Objekt 
	 */
	
	public Fahrzeug getFahrzeugbyId(int id){
		return this.farzeugDao.getFahrzeugbyId(id);
	}
	
	/**
	 * Speichert ein Fahrzeugobjekt 
	 * @param fahrzeug ein Fahrzeug-Object
	 * @see swe2016.fm.fahrzeuge.dao.SerializedFahrzeugDAO#speichereFahrzeug(Fahrzeug fahrzeug)
	 */
	
	public void FahrzeugHinzufuegen(Fahrzeug fahrzeug){
		this.farzeugDao.speichereFahrzeug(fahrzeug);
	}
	
	/**
	 * Loescht ein bestehendes Fahrzeug von der persistenten Datenquelle
	 * 
	 * @param fahrzeug ein Fahrzeug-Object
	 * @see swe2016.fm.fahrzeuge.dao.SerializedFahrzeugDAO#loescheFahrzeug(Fahrzeug fahrzeug)
	 */
	public void FahrzeugLoeschen(Fahrzeug fahrzeug){
		this.farzeugDao.loescheFahrzeug(fahrzeug); 
	}
	
	/**
	 * Gibt alle Daten eines Fahrzeug als String im bestimmten Format zurueck
	 * 
	 * @param id ID eines Fahrzeugs
	 * @return Fahrzeug
	 */
	public String getFahrzeugDaten(int id){
		
		Fahrzeug fahrzeug = this.farzeugDao.getFahrzeugbyId(id); 
		return fahrzeug.toString();
	}
	
	/**
	 * Berechnet Gesamtzahl der erfassten Fahrzeuge 
	 * 
	 * @return Gesamtzahl
	*/
	public int gesamtzahlFahrzeuge(){
		return this.getFahrzeugListe().size();
	}
	
	/**
	 * Berechnet Gesamtzahl der PKW 
	 * 
	 * @return Gesamtzahl der PKW 
	 */	
	public int gesamtzahlPKW(){
		/** counter des Typs Integer*/
		int counter= 0 ;
		/**	speicherliste {@link #swe2016.fm.fahrzeuge.dao.FahrzeugDAO.getFahrzeugList()}*/
		ArrayList <Fahrzeug> fahrzeugListe = this.farzeugDao.getFahrzeugList();
		for(int i = 0 ; i < gesamtzahlFahrzeuge(); i++){
			if (fahrzeugListe.get(i) instanceof PKW){
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * Berechnet Gesamtzahl der LKW 
	 * 
	 * @return Gesamtzahl der LKW
	 */
	public int gesamtzahlLKW(){
		/** counter des Typs Integer*/
		int counter= 0 ;
		/**	speicherliste {@link #swe2016.fm.fahrzeuge.dao.FahrzeugDAO.getFahrzeugList()}*/
		ArrayList <Fahrzeug> fahrzeugListe = this.farzeugDao.getFahrzeugList();
		for(int i = 0 ; i < gesamtzahlFahrzeuge(); i++){
			if (fahrzeugListe.get(i) instanceof LKW){
				counter++;
			}
		}
		return counter;
	}
	
	/**
	 * Berechnet Durchschnittspreis aller Fahrzeuge
	 * 
	 * @return Durchschnittspreis aller Fahrzeuge
	 */
	public double durchschnittPreisAllerFahrzeuge (){
		/** sum des Typs double*/
		double sum= 0 ;
		/**	fahrzeugListe {@link #swe2016.fm.fahrzeuge.dao.FahrzeugDAO.getFahrzeugList()}*/
		ArrayList <Fahrzeug> fahrzeugListe = this.farzeugDao.getFahrzeugList();
		for(int i = 0 ; i <gesamtzahlFahrzeuge(); i++){
				sum+= fahrzeugListe.get(i).getPreis() ;
		}
		return sum/this.gesamtzahlFahrzeuge();
		
		
	}
	
	
	/**
	 * Berechnet Durchschnittspreis aller PKW 
	 * 
	 * @return Durchschnittspreis aller PKW 
	 */
	public double durchschnittPreisAllerPKW (){
		/** sum des Typs double*/
		double sum= 0 ;
		/**	fahrzeugListe {@link #swe2016.fm.fahrzeuge.dao.FahrzeugDAO.getFahrzeugList()}*/
		ArrayList <Fahrzeug> fahrzeugListe = this.farzeugDao.getFahrzeugList();
		for(int i = 0 ; i <gesamtzahlFahrzeuge(); i++){
			if (fahrzeugListe.get(i) instanceof PKW){
				sum+= fahrzeugListe.get(i).getPreis() ;
			}
		}
		return sum/this.gesamtzahlPKW();
		
	}
	

	/**
	 * Berechnet Durchschnittspreis aller LKW 
	 * 
	 * @return Durchschnittspreis aller LKW
	 */
	public double durchschnittPreisAllerLKW (){
		/** sum des Typs double*/
		double sum= 0 ;
		/**	fahrzeugListe {@link #swe2016.fm.fahrzeuge.dao.FahrzeugDAO.getFahrzeugList()}*/
		ArrayList <Fahrzeug> fahrzeugListe = this.farzeugDao.getFahrzeugList();
		for(int i = 0 ; i <gesamtzahlFahrzeuge(); i++){
			if (fahrzeugListe.get(i) instanceof LKW){
				sum+= fahrzeugListe.get(i).getPreis() ;
			}
		}

		return sum/this.gesamtzahlLKW();
		
	}
	
	
	
	/**
	 * Berechnet Durchschnittsalter aller Fahrzeuge
	 * 
	 * @return Durchschnittsalter aller Fahrzeuge
	 */
	public double durchschnittAlterAllerFahrzeuge (){
		/** sum des Typs double*/
		double sum= 0 ;
		/**	fahrzeugListe {@link #swe2016.fm.fahrzeuge.dao.FahrzeugDAO.getFahrzeugList()}*/
		ArrayList <Fahrzeug> fahrzeugListe = this.farzeugDao.getFahrzeugList();
		for(int i = 0 ; i < gesamtzahlFahrzeuge(); i++){
				sum+= fahrzeugListe.get(i).getAlter() ;
		}
		return sum/this.gesamtzahlFahrzeuge();
		
	}
	
	
	/**
	 * Sucht Aelteste(s) Fahrzeug(e) 
	 * 
	 * @return m - ein ArrayList mit dem Id des gefundenen Farzeugs(en)
	 */
	public ArrayList<String> aeltesteFahrzeugSuche () {
		/**	@param fahrzeugListe {@link #swe2016.fm.fahrzeuge.dao.FahrzeugDAO.getFahrzeugList()}*/
	    ArrayList <Fahrzeug> fahrzeugListe = this.farzeugDao.getFahrzeugList();
	    /** max des Typs Integer*/
		int max= fahrzeugListe.get(0).getAlter();
		
		/**ein ArrayList*/
		ArrayList<String> m = new ArrayList<String>();
		
        for(int i = 0; i < gesamtzahlFahrzeuge(); i ++){
            if(max<=fahrzeugListe.get(i).getAlter()) {
            	max= fahrzeugListe.get(i).getAlter();
            }
            
        }
       
         for(int j = 0; j < gesamtzahlFahrzeuge(); j ++){
             if(max==fahrzeugListe.get(j).getAlter()) {
            	m.add("Id: "+ fahrzeugListe.get(j).getId());	
            	
             }
            
         }
         
		return m;
		}
	
	
}
	