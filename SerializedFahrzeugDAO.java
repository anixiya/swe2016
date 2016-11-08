package swe2016.fm.fahrzeuge.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import at.ac.univie.swe2016.fm.fahrzeuge.Fahrzeug;
//import at.ac.univie.swe2016.fm.fahrzeuge.IlegalArgumentException;

/**
 * Die Klasse SerializedFahrzeugDAO implementiert das Interface FahrzeugDAO
 * 
 * @author Axinya Tokareva, 1368965
 * @version 1.1; 07/11/2016
 */
public class SerializedFahrzeugDAO implements FahrzeugDAO,Serializable {
	/** Parameter des Typs ArrayListes*/
	private ArrayList <Fahrzeug> leerlist = new ArrayList <Fahrzeug>();
	
	/** Name einer Datei als String */
	private String dateiname; 
	
	/**
	 * Konstruktor (setzt Instanzvariablen direkt)
	 * @param dateiname Name einer Datei
	 * */
	public SerializedFahrzeugDAO(String dateiname) {
		this.dateiname=dateiname;
	}
	
	/** eindeutige Fahrzeugnummer*/
	private static final long serialVersionUID = 1L;


	@SuppressWarnings("unchecked")
	@Override
	/**
	 * {@inheritDoc}
	 * 
	 * @param file File des Types File
	 * @param fileis FileInput
	 * @param obis ObjectInput
	 * @exception IOException Wenn eine Datei konnte nicht erstellt werden
	 * @exception ClassNotFoundException wenn ClassNotFoundException vorkommt
	 * @see java.io.File
	 * @see java.io.FileInputStream
	 * @see java.io.ObjectInputStream
	 * @see java.io.IOException
	 */
	public ArrayList<Fahrzeug> getFahrzeugList() {
		
		File file=new File(dateiname);
		if (file.exists() && file.canRead()) {
		try{
			FileInputStream fileis = new FileInputStream(dateiname);
			ObjectInputStream obis= new ObjectInputStream(fileis);
			if (obis!=null) {
				leerlist=(ArrayList<Fahrzeug>) obis.readObject();			
				obis.close();
				fileis.close();
			}
		}
			catch(IOException e){
				System.out.println("Datei kann nicht erstellt werden! Problem mit Input!");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				System.out.println("Erforderliche Klasse ist nicht in der Lage, wo classloader sucht!");
				e.printStackTrace();
			}
			
		}
		
		return leerlist;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see java.util.ArrayList
	 */
	@Override
	public Fahrzeug getFahrzeugbyId(int id) {
		/** eine Liste*/
		ArrayList <Fahrzeug> suchlist= this.getFahrzeugList();
		for(int i=0; i<suchlist.size(); i++) {
			if (suchlist.get(i).getId()==id) {
				return suchlist.get(i); 
			}

		}
		System.out.println("Fahrzeug mit diesem Id konnte nicht gefunden werden!");
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalArgumentException wenn es schon ein Fahrzeug mit dem vorgegebenen Id gibt
	 * @see java.io.FileOutputStream
	 * @see java.io.ObjectOutputStream
	 * @see java.io.IOException
	 */
	@Override
	public void speichereFahrzeug(Fahrzeug fahrzeug) {
		if (this.getFahrzeugbyId(fahrzeug.getId())!=null) 
			throw new IllegalArgumentException("Tut mir leid! Es gibt schon ein Fahrzeug mit diesem Id!");
		ArrayList <Fahrzeug> newlist= this.getFahrzeugList();
		newlist.add(fahrzeug);
		try {
			/** FileOutput */
			FileOutputStream fileos = new FileOutputStream(dateiname);
			/** ObjectOutput*/
			ObjectOutputStream obos= new ObjectOutputStream(fileos);
			if (obos!=null) {
				obos.writeObject(newlist);			
				obos.close();
				fileos.close();
			}
			System.out.println("Deshalb hinzugefuegt");
		}
		/** Wenn eine Datei konnte nicht erstellt werden */
		catch(IOException e){
			System.out.println("Datei kann nicht erstellt werden! Problem mit Output!");
			e.printStackTrace();
		}
		
	}

	/**
	 * {@inheritDoc}
	 *  
	 * @throws IllegalArgumentException wenn es kein Fahrzeug mit dem vorgegebenen Id gibt
	 * @see java.io.FileOutputStream
	 * @see java.io.ObjectOutputStream
	 * @see java.io.IOException
	 * 
	 */
	
	@Override
    public void loescheFahrzeug(Fahrzeug fahrzeug) {
        if (fahrzeug != null) {
        	/**	speicherliste {@link #getFahrzeugList()}*/
            ArrayList<Fahrzeug> speicherliste = this.getFahrzeugList();
            for (Fahrzeug fahrzeug2 : speicherliste) {
                if (fahrzeug.getId() == fahrzeug2.getId()) {
                    fahrzeug = fahrzeug2;
                    break;
                }
            }

            while (speicherliste.contains(fahrzeug)) {
                speicherliste.remove(fahrzeug);
            }

            try {
            	/** FileOutput */
                FileOutputStream fileos = new FileOutputStream(dateiname);
                /** ObjectOutput*/
                ObjectOutputStream obos = new ObjectOutputStream(fileos);
                obos.writeObject(speicherliste);
                fileos.close();
                obos.close();
                System.out.println("Geloescht");
                /** Wenn eine Datei konnte nicht erstellt werden */
            } catch (IOException e) {
            	System.out.println("Datei kann nicht erstellt werden! Problem mit Output!");
                e.printStackTrace();
            }
        }
        else throw new IllegalArgumentException("Tut mir leid! Es gibt kein Fahrzeug mit diesem Id!");
    }
}

