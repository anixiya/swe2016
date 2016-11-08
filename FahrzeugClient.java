import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


import at.ac.univie.swe2016.fm.FahrzeugManagement;
import at.ac.univie.swe2016.fm.fahrzeuge.*;

/**
* Die Klasse FahrzeugClient, welches verschiedene Funktionalitäten der Fahrzeugverwaltung 
* (unter Verwendung der Klasse FahrzeugManagement) unterstützt
* 
* @author Axinya Tokareva, 1368965
* @version 1.1; 07/11/2016
*/

public class FahrzeugClient {
	/**static Variable des Typs FahrzeugManagement*/
	private static FahrzeugManagement fm ;
	
	/**
	 * Aendert das DecimalFromat (0,00000...) in Form 0.00
	 * 
	 * @param wert ausgegebener Parameter
	 * @return geaenderter wert
	 */
	public static String df(double wert) {
		DecimalFormat decim = new DecimalFormat("0.00");
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
	    dfs.setDecimalSeparator('.');
	    decim.setDecimalFormatSymbols(dfs);
		String decfor = decim.format(wert) ;
		return decfor;
	}
	
	/**
	 * Unterstuetzt solche Funktionalitaeten, wie: show, add, del, count, meanage, meanprice, oldest:
	 *
	 * show - Alle Daten aller Fahrzeuge ausgeben; 
	 *  show id - Alle Daten eines Fahrzeuges ausgeben ;
	 *  add lkw ... - LKW persistent hinzufügen ;
	 *  add pkw ... - PKW persistent hinzufügen;  
	 *  del id - Fahrzeug löschen  ;\n
	 *  count - Gesamtzahl der erfassten Fahrzeuge berechnen ;
	 * 	count lkw - Gesamtzahl der LKWs berechnen ;
	 * 	count pkw - Gesamtzahl der PKWs berechnen ; 
	 * 	meanage - Durchschnittsalter aller Fahrzeuge berechnen ; 
	 * 	meanprice - Durchschnittspreis aller Fahrzeuge berechnen;  
	 * 	meanprice LKW - Durchschnittspreis aller LKWs berechnen ;
	 * 	meanprice PKW - Durchschnittspreis aller LKWs berechnen ;
	 * 	oldest - aeltest(e) Fahrzeug(e) suchen ;
	 * 
	 * @param args die eingegebene Parameter
	 * @throws ParseException wenn ein ParseException vorkommt
	 * @throws ClassNotFoundException ein ClassNotFoundException vorkommt
	 */
	
	public static void main(String[] args) throws ParseException, ClassNotFoundException{
		
		int argsCount = args.length;
		
		if (argsCount < 2)  {
			System.err.println("Fehler! Es soll mindestens 2 Argumente gegeben werden!"); 
			System.exit(1);
		}
		
		fm = new FahrzeugManagement (args[0]);

		switch (args[1]) {
	   
		case "add": {
		try {
			if (argsCount < 8) {
				System.err.println("Es soll mindestens 8 Arguments gegeben werden!"); 
				System.exit(1);
			}
			
			if (args[2].equals("lkw") && argsCount > 8) {
				System.err.println("Anzahl der Arguments soll  8 Sein!"); 
				System.exit(1);
			}
			
			if (args[2].equals("pkw") && argsCount < 9 || argsCount>9) {
				System.err.println("Anzahl der Arguments soll  9 Sein!"); 
				System.exit(1);
			}
			
			if (args[2].equals("pkw") && !args[8].matches("([0-9]{4})-([0-1][0-9])-([0-3][0-9])")) {
				System.err.println("Das falsche Format des Datums!"); 
				System.exit(1);
			}
				switch(args[2]){
					case "lkw" : 
						Fahrzeug f = new LKW (args[4],  args[5], Integer.parseInt(args[6]) , Double.parseDouble(args[7]), Integer.parseInt(args[3]) );
						fm.FahrzeugHinzufuegen(f);
						break; 
					case "pkw" : 
						String string = args[8];
						DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
						Date date;
						
						try{ 
							date = format.parse(string);
							Fahrzeug f1 = new PKW (args[4],  args[5], Integer.parseInt(args[6]) , Double.parseDouble(args[7]), Integer.parseInt(args[3]), date );
							fm.FahrzeugHinzufuegen(f1);
							} catch (ParseException e) {
								System.err.println("Fehler bei Arguments!");
								e.printStackTrace();
								System.exit(1);
							}
							
							break;
							default: 
							{
								System.err.println("Das System kennt diese Typen nicht!"); 
								System.exit(1);
							}
				}
					
					break;
		}
				  catch(NumberFormatException NummerFehler) { 
					  System.err.println("Argument/-s wurde/-en falsch gegeben!");
					  System.exit(1);
				  } 
		}
					
				
	
	
		 
		case "show": {
			if (args.length == 2){
				ArrayList<Fahrzeug> fahrliste = fm.getFahrzeugListe();
						
				for(int i = 0 ; i < fahrliste.size(); i++ ){
					System.out.println(fahrliste.get(i).toString() );
				}
			}
			
		else if (args.length ==3){
			 try  {  
				 System.out.println(fm.getFahrzeugDaten(Integer.parseInt(args[2])));
			 }  
			  catch(NumberFormatException NummerFehler) { 
				  System.err.println("Id soll ein Integer sein!");
				  System.exit(1);
			  }  	
			
				}
		else {
			System.err.println("Anzahl der Parameter stimmt nicht!");
			System.exit(1);;
		}
		break;	
			}
		
				
		case "del": {
			if (args.length==3) {
				
				
				
				 try  {  
					 Fahrzeug x = fm.getFahrzeugbyId(Integer.parseInt( args[2]));
					 fm.FahrzeugLoeschen(x);
					  }  
					  catch(NumberFormatException NummerFehler) {  
						  System.out.println("Id soll ein Integer sein!");
						  System.exit(1);
					  }  
				
				}else if (args.length<3) {
					System.err.println("Id fehlt!"); 
					System.exit(1);
				}
				else {
					System.err.println("Anzahl der Parameter stimmt nicht!");
					System.exit(1);;
				}
			break;
				}
				
		
		case "count": {
			if (args.length==2) {
				System.out.println(fm.gesamtzahlFahrzeuge());	
			}
			
		else if (args[2].equals("lkw") && args.length==3) {
			System.out.println(fm.gesamtzahlLKW());	
					}
		else if (args[2].equals("pkw") && args.length==3) {
			System.out.println(fm.gesamtzahlPKW());	
					}
			else {
				System.err.println("Parametern stimmen nicht!"); 
				System.exit(1);
			}
				
			break;
			}
			
		
		case "meanage" :{
			if (args.length==2) {
				System.out.println(df(fm.durchschnittAlterAllerFahrzeuge()));	
			}
				
			else {
				System.err.println("Parametern stimmen nicht!"); 
				System.exit(1);
			}
			break;
				}
			
	
		case "meanprice" :{
			if (args.length==2) {
				System.out.println(df(fm.durchschnittPreisAllerFahrzeuge()));	
				}
			else if (args[2].equals("lkw") && args.length==3) {
				System.out.println(df(fm.durchschnittPreisAllerLKW()));
			}
			else if (args[2].equals("pkw") && args.length==3) {
				System.out.println(df(fm.durchschnittPreisAllerPKW()));
				}
			else {
				System.err.println("Parametern stimmen nicht!"); 
				System.exit(1);
			}
				
			break;
				}
			
		case"oldest":{
			if (args.length==2) {
			ArrayList<String> oldest= fm.aeltesteFahrzeugSuche();
			for (int l=0; l<oldest.size(); l++) {
				System.out.println(oldest.get(l));	
				}
			}
				
			else {
				System.err.println("Parametern stimmen nicht!"); 
				System.exit(1);
			}
			 break;	
			}	
		  
			
		
	case "modify": {
		try{
			if (argsCount < 8) {
				System.err.println("Es soll mindestens 8 Arguments gegeben werden!"); 
				System.exit(1);
			}
			
			if (args[2].equals("lkw") && argsCount > 8) {
				System.err.println("Anzahl der Arguments soll  8 Sein!"); 
				System.exit(1);
			}
			
			if (args[2].equals("pkw") && argsCount < 9 || argsCount>9) {
				System.err.println("Anzahl der Arguments soll  9 Sein!"); 
				System.exit(1);
			}
			
			if (args[2].equals("pkw") && !args[8].matches("([0-9]{4})-([0-1][0-9])-([0-3][0-9])")) {
				System.err.println("Das falsche Format des Datums!"); 
				System.exit(1);
			}
		Fahrzeug x = fm.getFahrzeugbyId(Integer.parseInt( args[3]));
		fm.FahrzeugLoeschen(x);
		switch(args[2]){
		case "lkw" : 
			Fahrzeug f = new LKW (args[4],  args[5], Integer.parseInt(args[6]) , Double.parseDouble(args[7]), Integer.parseInt(args[3]) );
			fm.FahrzeugHinzufuegen(f);
			break; 
		case "pkw" : 
			String string = args[8];
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
			Date date;
			
			try {
				date = format.parse(string);
				Fahrzeug f1 = new PKW (args[4],  args[5], Integer.parseInt(args[6]) , Double.parseDouble(args[7]), Integer.parseInt(args[3]), date );
				fm.FahrzeugHinzufuegen(f1);
				} catch (ParseException e) {
					System.err.println("Fehler bei Arguments!");
					e.printStackTrace();
					System.exit(1);
				}
				
				break;
				default: {
					System.err.println("Das System kennt diese Type nicht!"); 
					System.exit(1);
				}

		
	}
		break; 
	}
		  catch(NumberFormatException NummerFehler) { 
			  System.err.println("Argument/-s wurde/-en falsch gegeben!");
			  System.exit(1);
		  } 
	}
				
		default: {
			System.err.println("Bitte benutzen Sie die Parameter wie: \n show, add, del, count, meanage, meanprice, oldest, modify"); 
			System.exit(1);
		}

}

			
		
	} }
	


