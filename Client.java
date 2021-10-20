import java.util.ArrayList;

public class Client{
	
	//Atributs
	private String nom;
	private String cognom;
	private ArrayList<Compta> comptes;
	
	//Constructors
	public Client(String nom, String cognom) {
		this.nom=nom;
		this.cognom=cognom;
		comptes= new ArrayList<Compta>();
	}
	
	//getters
	public String getNom() {
		return nom;
	}
	
	public String getCognom() {
		return cognom;
	}
	
	public ArrayList<Compta> getComptes() {
		return comptes;
	}
	
	//setters
	public void setNom(String nom) {
		this.nom=nom;
	}
	
	public void setCognom(String cognom) {
		this.cognom=cognom;
	}
	
	//de classe
	public void afegirCompta(Compta compta) {
		comptes.add(compta);
	}
	
	public void eliminarCompta(int indexCompta) {
		comptes.remove(indexCompta);
	}
	
	//altri
	public String toString() {
		return "El nom del client és: "+nom+", el cognom és: "+cognom+" i els comptes són"+comptes;
	}
}