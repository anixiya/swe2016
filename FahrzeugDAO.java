package swe2016.fm.fahrzeuge.dao;

import java.util.ArrayList;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;

/**
 * Dieses Interface definiert Methoden des Entwurfsmusters Data Access Object
 * zur Erstellung persistenter Fahrzeugobjekte (nur Deklarierung, keine Implementierung)
 * 
 * @author Axinya Tokareva, 1368965
 * @version 1.1; 07/11/2016
 *
 */

public interface FahrzeugDAO {
	/**
	 * Benutzt eine Liste, um alle persistent gespeicherten Fahrzeugobjekte zurückzugeben
	 *   
	 * @return alle gespeicherte Fahrzeugobjekte
	 */
	public ArrayList <Fahrzeug> getFahrzeugList();
	
	/**
	 * Gibt ein Fahrzeug-Objekt, anhand der Fahrzeugnummer
	 *  
	 * @param id Id des Fahrzeugs
	 * @return Fahrzeug-Objekt 
	 */
	public Fahrzeug getFahrzeugbyId(int id);
	
	/**
	 * Speichert ein Fahrzeugobjekt 
	 * @param fahrzeug ein Fahrzeug-Object
	 */
	public void speichereFahrzeug(Fahrzeug fahrzeug);
	
	/**
	 * Loescht ein bestehendes Fahrzeug von der persistenten Datenquelle
	 * 
	 * @param fahrzeug ein Fahrzeug-Object
	 */
	public void loescheFahrzeug(Fahrzeug fahrzeug);
}
