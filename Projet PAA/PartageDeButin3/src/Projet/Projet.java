package Projet;

import java.util.Scanner;

public class Projet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Navire navire;
		String FileName,line;
		int choix = -1, choix2 = -1;
		String p1, p2;
		boolean inputNotNull;
		Scanner sc = new Scanner(System.in);

		
		System.out.print("Veuillez saisir le nom du ficher à lire : ");
		FileName = sc.nextLine();

		navire = new Navire(FileName); //Création d'un objet Navire à partir d'un fichier 
		
		//Menu principal
		while (choix != 4) {
			System.out.println("1) résolution automatique;");
			System.out.println("2) résolution manuelle;");
			System.out.println("3) sauvegarde;");
			System.out.println("4) fin.");
			inputNotNull = true;
			
			//lecture et vérification des choix de l'utilisateur
			while (inputNotNull) {
				System.out.print("Veuillez saisir le numéro de votre option : ");

			    line = sc.nextLine();
			    try {
			    	choix = Integer.parseInt(line);
			    } catch (NumberFormatException e) {
			        System.out.println("Mauvaise entrée ! Entrez uniquement des nombres entiers s'il vous plaît ..." + e.getMessage());
			        continue;
			    }
			    
				

					if (choix >= 1 && choix <= 4) {
						inputNotNull = false;
					} else {
						System.out.println("le numéro choisi n'est pas valide, merci de choisir un nombre entre 1 et 4");
					}

			}

			switch (choix) {
			case 1: // Résolution automatique avec l'algorithme naif.
				navire.solutionNaive();
				navire.printSolution();
				System.out.println("Le cout de cette solution est : " + navire.cout());
				break;
			case 2: // Résolution manuelle 
				choix2 = -1;
				//Menu pour la résolution manuelle.
				while (choix2 != 3) {
					System.out.println("1) échanger objets;");
					System.out.println("2) afficher coût;");
					System.out.println("3) fin.");
					System.out.print("Veuillez saisir le numéro de votre option :");
					inputNotNull = true;

					while (inputNotNull) {
						System.out.print("Veuillez saisir le numéro de votre option : ");

					    line = sc.nextLine();
					    try {
					    	choix2 = Integer.parseInt(line);
					    } catch (NumberFormatException e) {
					        System.out.println("Mauvaise entrée ! Entrez uniquement des nombres entiers s'il vous plaît ..." + e.getMessage());
					        continue;
					    }
					    
						

							if (choix2 >= 1 && choix2 <= 3) {
								inputNotNull = false;
							} else {
								System.out.println("le numéro choisi n'est pas valide, merci de choisir un nombre entre 1 et 3");
							}

					}

					switch (choix2) {
					case 1:
						p1 = sc.next();
						p2 = sc.next();
						if (navire.isPirateIn(p1) && navire.isPirateIn(p2)) {
							navire.swapObject(p1, p2);

						}

						if (navire.isPirateIn(p1) == false) {
							System.out.println("aucun pirate avec le nom : " + p1);
						}

						if (navire.isPirateIn(p2) == false) {
							System.out.println("aucun pirate avec le nom : " + p2);
						}

						break;
					case 2:
						int cout = navire.cout();
						System.out.println("Le cout actuelle est : " + cout);
						break;
					case 3:
						System.out.println("Fin de la résolution manuelle !");
						break;
					default:
						break;

					}

					if (choix != 3)
						navire.printSolution();
				}

				break;
			case 3: //Enregistrement des résultat dans un fichier
				System.out.print("Merci de choisir le nom du fichier à sauvgarder :");
				FileName = sc.next();
				navire.save(FileName);
				break;
			case 4: //Fin du programme.
				System.out.print("Fin du programme !");
				break;
			default:
				break;
			}

		}
		navire.printSolution();

		sc.close();

	}

}
