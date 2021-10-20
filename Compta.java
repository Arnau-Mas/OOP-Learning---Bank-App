public class Compta{
	
	//Atributs
	int numCompta;
	int saldo;
	
	//Constructors
	public Compta(int numCompta){
		this.numCompta = numCompta;
		saldo = 0;
	}
	
	//metodes getters
	public int getNumCompta(){
		return numCompta;
	}
	
	public int getSaldo() {
		return saldo;
	}
	
	//metodes setters
	public void setNumCompta(int numCompta) {
		this.numCompta = numCompta;
	}
	
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	
	//metodes de classe
	public void ingressar(int quantitat) {
		saldo+=quantitat;
	}
	
	public void retirar(int quantitat) {
		//if(quantitat>saldo) no 
		saldo-=quantitat;
	}
	
	//altri
	public String toString() {
		return "/Número de compta: "+numCompta+". Saldo actual és de: "+saldo;
	}
}