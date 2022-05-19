package Projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kemokoba BAYO
 *
 */
public class Navire {

	int nbrpirates; // nombre de pirate dans le navire.
	int[][] relations; // matrice des relations entre les pirates 
	Pirate[] pirates; // Liste des pirates.
	Map<String, Integer> PirateName = new HashMap<String, Integer>(); // Une Map entre les noms des pirates et l'ensembles des entiers.
	Map<String, Integer> ObjectName = new HashMap<String, Integer>(); // Une Map entre la liste des objets et l'ensembles des entiers.

	/**
	 * Constructeur avec le nombre de pirates 
	 * @param nbrpirates 
	 */
	public Navire(int nbrpirates) {
		// TODO Auto-generated constructor stub
		this.nbrpirates = nbrpirates;
		relations = new int[nbrpirates][nbrpirates];
		pirates = new Pirate[nbrpirates];

		for (int i = 0; i < nbrpirates; i++) {
			pirates[i] = new Pirate(nbrpirates);

			for (int j = 0; j < nbrpirates; j++) {
				relations[i][j] = 0;
			}
		}

	}

	/**
	*Constructeur à partir du chemin absolu d'un fichier de configuration
	*@param fileName chemin absolu du fichier de configuration.
	*/
	public Navire(String fileName) {
		this.nbrpirates = 0;
		int nbrObject = 0;
		try {
			// Le fichier d'entrée
			File file = new File(fileName);
			// Créer l'objet File Reader
			FileReader fr = new FileReader(file);
			// Créer l'objet BufferedReader
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				// ajoute la ligne au buffer
				// permet de lire le contenu de la parenthèse.
				String[] parts = line.split("\\(");
				String[] parts2 = parts[1].split("\\)");

				if (parts[0].compareTo("pirate") == 0) {
					PirateName.put(parts2[0], this.nbrpirates);
					this.nbrpirates++;

				}

				if (parts[0].compareTo("objet") == 0) {
					if (relations == null) {
						relations = new int[nbrpirates][nbrpirates];
						pirates = new Pirate[nbrpirates];

						for (int i = 0; i < nbrpirates; i++) {
							pirates[i] = new Pirate(nbrpirates);

							for (int j = 0; j < nbrpirates; j++) {
								relations[i][j] = 0;
							}
						}
					}

					System.out.println(parts2[0]);
					this.ObjectName.put(parts2[0], nbrObject);

					nbrObject++;

				}

				if (parts[0].compareTo("deteste") == 0) {
					String[] parts3 = parts2[0].split(",");

					this.relations[PirateName.get(parts3[0])][PirateName.get(parts3[1])] = 1;
					this.relations[PirateName.get(parts3[1])][PirateName.get(parts3[0])] = 1;
				}

				if (parts[0].compareTo("preferences") == 0) {
					String[] parts3 = parts2[0].split(",");
					int pn = this.PirateName.get(parts3[0]);
					for (int i = 1; i < parts3.length; i++) {
						this.pirates[pn].preferences[i - 1] = this.ObjectName.get(parts3[i]);
					}
				}

			}
			fr.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	*Ajouter une relation entre deux pirates
	*@param p1 nom du premier pirate
	*@param p2 nom du deuxième pirate 
	*/
	void addRelation(String p1, String p2) {
		int n1 = PirateName.get(p1);
		int n2 = PirateName.get(p2);
		this.relations[n1][n2] = 1;
		this.relations[n2][n1] = 1;

	}

	/**
	*A enlever
	*/
	void addPreferences(String pirate, int[] preferences) {
		int n = PirateName.get(pirate);
		this.pirates[n].setPreferences(preferences);

	}

	/**
	*Echanger les objets entre deux pirates 
	*@param p1 nom premier pirate
	*@param p2 nom deuxieme pirate
	*/
	void swapObject(String p1, String p2) {
		int n1 = PirateName.get(p1);
		int n2 = PirateName.get(p2);
		;
		int tmp = this.pirates[n1].getObjet();
		this.pirates[n1].setObjet(this.pirates[n2].getObjet());
		this.pirates[n2].setObjet(tmp);
	}

	/**
	*Calculer le cout d'une solution actuelle.
	*@return un entier correspondant au cout d'une solution actuelle.
	*/
	int cout() {
		int cout = 0;
		for (int i = 0; i < this.nbrpirates; i++) {
			int obj = 0;
			boolean test = true;
			while (this.pirates[i].getObjet() != this.pirates[i].preferences[obj] && test) {
				for (int j = 0; j < this.nbrpirates; j++) {
					if (this.relations[i][j] == 1 && this.pirates[j].getObjet() == this.pirates[i].preferences[obj]) {
						cout++;
						test = false;
						break;
					}

				}
				obj++;
			}

		}
		return cout;

	}
	/**
	*Trouver le nom d'un objet à partir de son index
	*@param index de l'objet à chercher
	*@return le nom de l'objet recherché
	*/
	String objectName(int index) {
		for (Map.Entry<String, Integer> entry : this.ObjectName.entrySet()) {
			if (entry.getValue() == index) {
				return entry.getKey();
			}
		}

		return "";

	}

	/**
	*Afficher la solution sur la console.
	*/
	void printSolution() {

		for (Map.Entry<String, Integer> entry : this.PirateName.entrySet()) {
			System.out.println(entry.getKey() + ":" + this.objectName(this.pirates[entry.getValue()].getObjet()));
		}
	}

	/**
	*Calculer une solution naive.
	*/
	void solutionNaive() {

		for (int i = 0; i < this.nbrpirates; i++) {

			for (int j = 0; j < this.pirates[i].preferences.length; j++) {
				boolean test = true;

				for (int u = 0; u < i; u++) {
					if (this.pirates[u].objet == this.pirates[i].preferences[j]) {
						test = false;
						break;
					}
				}

				if(test){
					this.pirates[i].objet = this.pirates[i].preferences[j];
					break;
				}
				
			}
		}
	}

	/**
	*Vérifier l'existence d'un nom d'un pirate
	*@param p nom du pirate
	*@return un boolean true si le nom du pirate existe false sinon.
	*/
	boolean isPirateIn(String p){
		return this.PirateName.containsKey(p);
	}

	/**
	*Vérifier l'existence d'un nom d'un objet
	*@param o nom du objet
	*@return un boolean true si le nom d'un objet existe false sinon.
	*/

	boolean isOjbectIn(String o){
		return this.ObjectName.containsKey(o);
	}

	/**
	*Sauvegarder la solution actuelle dans un fichier
	*@param filename nom du fichier où la solution sera sauvegardé 
	*/
	void save(String filename){
		
		File outFile = new File(filename);
		outFile.getParentFile().mkdirs();
		try {
			FileWriter fileWriter = new FileWriter(outFile);
			for (Map.Entry<String, Integer> entry : this.PirateName.entrySet()) {
				fileWriter.write(entry.getKey() + ":" + this.objectName(this.pirates[entry.getValue()].getObjet()));
				fileWriter.write("\n");
			}
			fileWriter.close();
			
		} catch (Exception e) {
			e.getStackTrace();
		}

	}

}
