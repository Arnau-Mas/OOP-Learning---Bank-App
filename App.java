import java.util.Scanner;
import java.util.ArrayList;

public class App{
	static ArrayList<Client> ddbb = new ArrayList<Client>(); 
	//Menu inicial
	public static void main(String[] args) {
		welcomeText();
		gestionsBanc();
	}
	
	static void welcomeText() {
		System.out.println("Benvingut al Banc d'IT Academy, aqui podràs dur a terme les següents funcions:");
		System.out.println("1.Crear client \n2.Eliminar client \n3.Crear compta d'un client \n4.Mostrar comptes d'un client \n5.Eliminar compta d'un client \n6.Ingressar € en una compta d'un client \n7.Retirar € d'una compta d'un client \n0.Sortir del programa \n");
	}
	
	static void gestionsBanc() {
		Scanner input = new Scanner(System.in);
		boolean sortir = false;
		int index = 0;
		do {
			System.out.println("Siusplau, escriu el número de la funció que vols realitzar: 1.Crear client / 2.Eliminar client / 3.Crear compta client / 4.Mostrar comptes client / 5.Eliminar compta client / 6.Ingressar € en una compta de client / 7.Retirar € d'una compta de client / 0.Sortir del programa");
			int funcio = input.nextInt();
			switch(funcio) {
			case 1: //CREAR CLIENT
				String nom = demanaString("nom"); //Al case 1 he de crear aquests Strings perquè la funció crearClient requereix d'aquests.
				String cognom = demanaString("cognom");
				index = trobarClient(nom,cognom);
				if(index!=-1) {
					System.out.println("Ja hi ha un client amb aquest nom i cognom. Siusplau, torna a intentar-ho.\n");
				}else {
					crearClient(nom,cognom);
				}
			break;
			case 2: //ELIMINAR CLIENT
				index = trobarClient(demanaString("nom"),demanaString("cognom"));
				if(index==-1) {
					System.out.println("No s'ha trobat cap client amb aquest nom i cognom. Sisupalu, torna a intentar-ho. \n");
				}else {
					eliminarClient(index);
				}	
			break;
			case 3: //CREAR COMPTA CLIENT
				index = trobarClient(demanaString("nom"),demanaString("cognom"));
				if(index==-1) {
					System.out.println("No s'ha trobat cap client amb aquest nom i cognom. Sisupalu, torna a intentar-ho. \n");
				}else {
					crearComptaClient(index);
				}	
			break;
			case 4: //MOSTRAR COMPTES CLIENT
				index = trobarClient(demanaString("nom"),demanaString("cognom"));
				//optimizar---> condicional(index, mostrarComptesClient(index));
				if(index==-1) {
					System.out.println("No s'ha trobat cap client amb aquest nom i cognom. Sisupalu, torna a intentar-ho. \n");
				}else if(ddbb.get(index).getComptes().size()==0){
					System.out.println("Aquest client no té comptes.Siusplau, crea'n una per interactuar. \n");
				}else {
					mostrarComptesClient(index);
				}
				
			break;
			case 5: //ELIMINAR COMPTA CLIENT
				index = trobarClient(demanaString("nom"),demanaString("cognom"));
				if(index==-1) {
					System.out.println("No s'ha trobat cap client amb aquest nom i cognom. Sisupalu, torna a intentar-ho. \n");
				}else if(ddbb.get(index).getComptes().size()==0){
					System.out.println("Aquest client no té comptes.Siusplau, crea'n una per interactuar. \n");
				}else {
					eliminarComptaClient(index, demanaInt("de compta"));
				}
			break;
			case 6: //INGRESSAR $ COMPTA CLIENT
				index = trobarClient(demanaString("nom"),demanaString("cognom"));
				if(index==-1) {
					System.out.println("No s'ha trobat cap client amb aquest nom i cognom. Sisupalu, torna a intentar-ho. \n");
				}else if(ddbb.get(index).getComptes().size()==0){
					System.out.println("Aquest client no té comptes.Siusplau, crea'n una per interactuar. \n");
				}else {
					ingressarComptaClient(index, demanaInt("de compta"));
				}
			break;
			case 7: //RETIRAR $ COMPTA CLIENT
				index = trobarClient(demanaString("nom"),demanaString("cognom"));
				if(index==-1) {
					System.out.println("No s'ha trobat cap client amb aquest nom i cognom. Sisupalu, torna a intentar-ho. \n");
				}else if(ddbb.get(index).getComptes().size()==0){
					System.out.println("Aquest client no té comptes.Siusplau, crea'n una per interactuar. \n");
				}else{
					retirarComptaClient(index, demanaInt("de compta"));
				}
				
			break;
			case 0: System.out.println("Has sortit del programa. Vagi bé.");
			break;
			default: System.out.println("Aquesta opció no és vàlida.");
			}
		}
		while(sortir!=true);
	}
	//Funcionalidades del programa
	static void crearClient(String nom, String cognom) {
			Client nouClient = new Client(nom,cognom);
			ddbb.add(nouClient);
			System.out.println("Enhorabona! El client "+nom+" "+cognom+" s'ha creat perfectament!\n");	
	}
	
	static void eliminarClient(int index) {
			ddbb.remove(index);
			System.out.println("El client s'ha eliminat perfectament.\n");
	}
	
	static void crearComptaClient(int index) {
		Client clientTrobat = ddbb.get(index);
		ArrayList<Compta> comptes = clientTrobat.getComptes();
		int numComptes = comptes.size();
		//aquest condicional és per assignar-li automàticament un numero de compta sense que es repeteixi
		if(numComptes!=0) { 
			int numComptaFinal = comptes.get(numComptes-1).getNumCompta();
			clientTrobat.afegirCompta(new Compta(numComptaFinal+1));
			System.out.println("S'ha creat la compta (número "+(numComptes+1)+") per l'usuari "+clientTrobat.getNom()+" "+clientTrobat.getCognom()+"\n");
		}else {
			clientTrobat.afegirCompta(new Compta(numComptes+1));
			System.out.println("S'ha creat la compta (número "+(numComptes+1)+") per l'usuari "+clientTrobat.getNom()+" "+clientTrobat.getCognom()+"\n");
		}
	}
	
	static void mostrarComptesClient(int index) {
		Client clientTrobat = ddbb.get(index);
		ArrayList<Compta> comptes = clientTrobat.getComptes();
		for(int i=0; i<comptes.size(); i++) {
			System.out.println(comptes.get(i).toString()+"\n");
		}
	}
	
	static void eliminarComptaClient(int index, int numComptaIntroduit) {
		Client clientTrobat = ddbb.get(index);
		int comptaTrobada = trobarCompta(clientTrobat, numComptaIntroduit);
			if(comptaTrobada!=-1) {
				clientTrobat.eliminarCompta(comptaTrobada);
				System.out.println("La compta ha estat eliminada.\n");
			}else{
				System.out.println("No s'ha trobat cap compta amb aquest número.\n");
			}
	}
	
	static void ingressarComptaClient(int index, int numComptaIntroduit) {
		Client clientTrobat = ddbb.get(index);
		int indexCompta = trobarCompta(clientTrobat, numComptaIntroduit);
		Compta comptaTrobada;
		if(indexCompta!=-1) {
			comptaTrobada = clientTrobat.getComptes().get(indexCompta);
			int diners = demanaInt("de diners");
			comptaTrobada.ingressar(diners);
			System.out.println("S'han ingressat "+diners+"€ al compte "+numComptaIntroduit+". El saldo actual és de: "+comptaTrobada.getSaldo()+"€.\n");
		}else {
			System.out.println("No s'ha trobat cap compta amb aquest número. Torna a intentar-ho.\n");
		}
	}
	
	static void retirarComptaClient(int index, int numComptaIntroduit) { //agrupar en un mètode el retirar i ingressar
		Client clientTrobat = ddbb.get(index);
		int diners = 0;
		int saldoFinal = 0;
		int indexCompta = trobarCompta(clientTrobat, numComptaIntroduit);
		Compta comptaTrobada = null;
		if(indexCompta!=-1) {
			diners = demanaInt("de diners");
			comptaTrobada = clientTrobat.getComptes().get(indexCompta);
			saldoFinal = comptaTrobada.getSaldo()-diners;
		}
		
		if(indexCompta!=-1 && saldoFinal>=0) { //fer això en el mètode retirar de l'objecte compta
			comptaTrobada.retirar(diners);
			System.out.println("S'han retirat "+diners+"€ al compte "+numComptaIntroduit+". El saldo actual és de: "+comptaTrobada.getSaldo()+"€.\n");
		}else if(indexCompta!=-1 && saldoFinal<0) {
			System.out.println("Error. No tens saldo suficient al compte, pel que no pots retirar diners. Torna a intentar-ho.\n");
		}else {
			System.out.println("No s'ha trobat cap compta amb aquest número. Torna a intentar-ho.\n");
		}
	}
	
	//Herramientas pedida datos
	static String demanaString(String frase) {
		Scanner input = new Scanner(System.in);
		System.out.println("Siusplau, afegeix el/la "+frase);
		String paraula = input.nextLine();
		return paraula;
	}
	
	static int demanaInt(String frase) {
		Scanner input = new Scanner(System.in);
		System.out.println("Introdueix el número "+frase);
		int numInt = input.nextInt();
		return numInt;
	}
	//Herramientas de búsqueda
	static int trobarClient(String nom, String cognom) {
		boolean clientTrobat = false;
		int i = 0;
		int index = -1;
		
		while(clientTrobat!=true && i<ddbb.size()) {
			if(nom.equals(ddbb.get(i).getNom()) && cognom.equals(ddbb.get(i).getCognom())) {
				index = i;
				clientTrobat = true;
			}else {
				i++;
			}
		}
		return index;
	}
	
	static int trobarCompta(Client client, int numCompta) { //Ficar aquest mètode en la pròpia classe de client
		boolean comptaTrobada = false;
		int i = 0;
		int indexCompta = -1;
		ArrayList<Compta> comptes = client.getComptes();
		while(comptaTrobada!=true && i<comptes.size()) {
			if(numCompta==comptes.get(i).getNumCompta()) {
				comptaTrobada=true;	
				indexCompta=i;
			}
				i++;
		}
		return indexCompta;
	}
	
}