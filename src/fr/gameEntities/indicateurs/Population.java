package fr.indicateur;

public class Population extends Thread {
	private static int[][] popTab = new int[130][12];
	private static int fertilite = 29;		// fertilite x 10 pour rester en int
	private static int attractivite = 0;
	
// Entree	: nombre total de la population
// Fonction : reparti aleatoirement la population dans les differentes tranches d'age
// Remarque : Version basique a detailler
// Verifiee : oui
	public Population (int a){
		int adulte = a/5*2;
		int b = a-adulte;
		ajoutPopulation(adulte, 18, 42);
		ajoutPopulation(b, 0, 80);
	}	
	public static void ajoutPopulation (int a, int agemin, int agemax){
		int i, col, lig;
		for (i=a; i>0; i--){
			int random = (int)(Math.random() * (agemax-agemin)*12); // permet de mettre al�atoirement la population dans la tranche d'age choisie.
			col = random/12;
			lig = random%12;
			popTab[col][lig] ++;
		}
	}
	public static void retraitPopulation (int a, int agemin, int agemax){
		int col, lig;
		while(a > 0){
			int random = (int)(Math.random() * (agemax-agemin+1)*12); // permet de retirer al�atoirement la population dans la tranche d'age choisie.
			col = random/12;
			lig = random%12;
			if (popTab[col][lig] > 0){
				popTab[col][lig] --;
				a--;
			}
		}
	}

// Entree	: le nombre d'individus a evaluer. L'age des sujets
// Fonction	: determine le nombre de personnes qui ne survivront pas
// Remarque	: 1 Les variables devront pouvoir etre influencee par differents facteurs (hygiene, avancee biologique, etc...)
//			  2 Formule basique, a voir pour la rendre plus precise (actuellement, 24 personnes arrivent a 130 ans sur une population de 10M)
//			  3 Le nombre de mort comprends une legere variable aleatoire pour eviter le systematisme
//	  		  4 La fonction est public, peut-etre la rendre private
//			  5 implementer comme facteur de mortalite : la quantite de population (et le taux de criminalite) 
// Verifiee	: Oui
	public static int morts(int nbIndiv, int age){
		int nbMort;
		if (age < 3){
			nbMort = (age-3)*(age-3);
		} else {
			if (age < 10)
				return 0;
			else
				nbMort = (age-10)*(age-10)/400;
		}
		//System.out.println("1 - nbMort = " + nbMort);
		nbMort -= nbMort*((int)(Math.random()*100)-50)/100;
		//System.out.println("2 - nbMort = " + nbMort);
		if (nbMort < 0 || nbMort > nbIndiv){
			return 0;
		}
		return nbMort;
	}

	public static void fertilite(){
		fertilite = (int)(30+Math.sqrt(attractivite/4)-20);
	}
// Entree	: / - La fonction regroupe toutes les naissances de la population et les regroupe dans la case des nouveaux nes
// Fonction : Definit le nombre de naissance en fonction du nombre de personne en age de reproduction de la cite et du taux de fertilite
// Remarque : 1 - Le taux de fertilite est multiple par 10 pour rester en INT
//			  2 - La fonction calcul les naissances mensuelle, peut facilement calculer pour differentes echelles de temps
//			  3 - La fonction renvoie un entier, utile pour le developpement, possibilite de ne rien renvoyer
//			  4 - La fonction est public, peut-etre la rendre private
// Verifiee :
	public static int naissances (){
		int x, y, n = 0;
		int num = nbIndiv(17, 42) * fertilite;
		int denom = 2*12*25*10;							// Nb indiviu par couple * nb mois * nb annee * equilibrateur fertilite
		while (num > denom){
			num -= denom;
			n++;
		}
		x = (int) (Math.random()*num);
		y = (int) (Math.random()*denom);
		if (x > y){
			n++;
		}
		return n;
	}

// Entree	: les ages limitant de la tranche d'age desiree
// Fonction : Renvoie le nombre d'individu de la tranche d'age demandee
// Remarque :
// Verifiee : Oui
	public static int nbIndiv (int min, int max){
		int i, j, nb = 0;
		for (i = min; i <= max; i++){
			for (j=0; j < 12; j++)
				nb += popTab[i][j];
		}
		return nb;
	}
	public static int nbIndiv(){
		return nbIndiv(0, 129);
	}

// Entree	: / Agit directement sur toute la population
// Fonction	: Effectue les differentes operations de naissance, de mort et de vieillissement de la population (fait appel aux fonctions 'naissance' et 'mort'
// Remarque	: Fonction centrale de l'evolution de la population.
// Verifiee	: Oui
	public static int vieillissementM (){
		int i, j, nbN;
		nbN = naissances();
		for (i = 129; i >= 0; i--){
			for (j = 11; j >= 0; j--){
				if (i == 0 && j == 0){
					popTab[i][j] = nbN;
				} else {
					if (j == 0){
						popTab[i][j] = popTab[i-1][11]-morts(popTab[i-1][11], i);//vieillissement
					} else {
						popTab[i][j] = popTab[i][j-1]-morts(popTab[i][j-1], i); //vieillissement
					}
				}
			}
		}
		return nbN;
	}

// Entree	: /
// Fonction : Affiche tout le tableau
// Remarque : L'affichage se fait a l'echelle annuelle
// Verifiee : Oui
	public static void affichePop (){
		int i, j, nb;
		for (i = 0; i < 130; i++){
			nb=0;
			for (j=0; j < 12; j++)
				nb += popTab[i][j];
			System.out.println("Il y a " + nb + " individus de " + i + " an(s)");
		}
	}
	
// Entree 	: Les limitants de la tranche d'age desiree
// Fonction	: Affiche la tranche d'age souhaitee
// Remarque	: L'affichage se fait a l'echelle annuelle
// Verifiee	: Oui
	public static void affichePop (int min, int max){
		int i, j, nb;
		if (min > max){
			int r = min;
			min = max;
			max = r;
		}			
		if (max > 129){
			max = 129;
			System.out.println("La limite d'age est 129 ans");
		}
		for (i = min; i <= max; i++){
			nb=0;
			for (j=0; j < 12; j++)
				nb += popTab[i][j];
			System.out.println("Il y a " + nb + " individus de " + i + " an(s)");
		}
	}
	
// Entree 	: Le nombre d'entree ou sortie de la 
// Fonction	: reparti les migrants dans des tranches d'age coherentes et appelle les fonction pour modifier le tableau de population
// Remarque	: 
// Verifiee	: Oui
	public static int flux (int a){
		if (a>=0){
			int adulte = a/5*2;	// oblige les nouveaux arrivant a etre coherent
			int b = a-adulte;
			ajoutPopulation(adulte, 18, 42);
			ajoutPopulation(b, 0, 80);
		} else {
			//System.out.println(this.nbIndiv(18, 42));
			if (-a > nbIndiv(0, 80)){
				a = -nbIndiv(0, 80);
			}
			int adulte = a/5*2;	// Les departs ne se font avec des adultes
			if (-adulte > nbIndiv(18, 42))
				adulte = nbIndiv(18, 42);
			int b = a+adulte;
			retraitPopulation(-adulte, 18, 42);
			retraitPopulation(-b, 0, 80);
		}
		return a;
	}
	public static int migration(){
		return (int)((Math.random()*nbIndiv()/5+attractivite)-nbIndiv()/10);
	}
	public static void modAttraction(int mod){
		attractivite += mod;
	}
}