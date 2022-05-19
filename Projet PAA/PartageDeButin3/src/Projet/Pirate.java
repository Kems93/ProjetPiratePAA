package Projet;

/**
 * @author Kemokoba BAYO
 *
 */
public class Pirate {

	
	static int numero = 0;
	String name;
	int objet;
	int[]  preferences;

	/**
	 * 
	 */
	public Pirate(int nbrObjects) {
		// TODO Auto-generated constructor stub
		preferences = new int[nbrObjects];
		//this.name = (char)('A'+numero);
		objet=numero+1;
		for(int i=0;i<nbrObjects;i++) preferences[i]=-1;
		Pirate.numero++;
	}

	/**
	 * @return the numero
	 */
	public static int getNumero() {
		return numero;
	}

	/**
	 * @param numero le numero à modifier
	 */
	public static void setNumero(int numero) {
		Pirate.numero = numero;
	}

	/**
	 * @return l'objet
	 */
	public int getObjet() {
		return objet;
	}

	/**
	 * @param objet l'objet à modifier
	 */
	public void setObjet(int objet) {
		this.objet = objet;
	}

	/**
	 * @return le nom
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name le nom à modifier 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return les preferences
	 */
	public int[] getPreferences() {
		return preferences;
	}

	/**
	 * @param preferences les preferences à modifier
	 */
	public void setPreferences(int[] preferences) {
		this.preferences = preferences;
	}

}
