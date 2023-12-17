# Groupe H3 SAE Développement d'application (Chasse au monstre)

## Compte rendu du jalon 1 au jalon 2


### Ulysse Couliou

### Semaine du lundi 06 novembre 2023

- Modifications de la classe Maze et IHM afin de pouvoir générer un labyrinthe dont la taille et le nombre d'obstacles varient en fonction d'un niveau de difficulté choisi.
- Ajout d'un nouveau type de cellule (Hole), un trou dans lequel si le monstre tombe dedans, il perd la partie, évènement à programmer par la suite
- Commencement d'une méthode qui utilise l'algorithme de Dijkstra pour vérifier qu'un chemin existe entre l'entrée et la sortie du labyrinthe

### Semaine du lundi 04 décembre 2023

- Enumeration Difficulty qui permet de gérer différents niveau de difficulté pour le jeu.
- Ecriture et génération de la Javadoc
- Refactorisation
  
### Semaine du lundi 11 décembre 2023
- Enumeration Difficulty qui permet de gérer différents niveau de difficulté pour le jeu.
- IA simple pour le chasseur
- Ecriture et génération de la Javadoc
- Modifications dans Maze et IHM
- Ecriture du Readme.md

### Eddy Vantard

#### Semaine du lundi 06 novembre 2023

- Ajout d'un affichage propre au chasseur et d'un autre propre au monstre (le monstre voit dans un rayon de n cases à déterminer, le chasseur voit les cases sur lesquelles il a déjà tiré).
- Refactoring de plusieurs méthodes dans la classe implémentant le JavaFX.
- Corrections de quelques bugs d'affichage et d'un bug sur la boucle de jeu principale.
- Ajout d'un retour textuel sur les actions des joueurs (sur quoi le chasseur vient de tirer ou si le monstre a tenté un déplacement impossible).
- Ajout d'une énumération Difficulty.

#### Semaine du lundi 04 décembre 2023

- Ajout des options de fin de jeu (relancer, retour au menu..)
- Refactoring multiples
- Séparation en deux fenêtres des vues du monstre et du chasseur

#### Semaine du lundi 11 décembre 2023

- Structure MVC pour le projet
- Réécriture de toute la partie vue du projet
- Résolutions de bugs liés à la restructuration


### Kellian Mirey

#### Semaine du lundi 06 novembre 2023

- Implémentation de la dernière apparition du Monstre sur une case (LastAppearance) dans le jeu.
- Affichage de la dernière apparition du Monstre sur case découverte par le Chasseur, pour la vue du Chasseur.
- Ajout et correction de la méthode isDistanceMoreThan1(Coordinate c) dans la classe Monster.
- Ajout de la méthode canMoveWithDiagonals(Coordinate c) dans la classe Monster.

#### Semaine du lundi 04 décembre 2023

- Correction de bugs liés à l'affichage de la dernière apparition du monstre.
- Ajout d'une gestion des touches pour MacOs.

#### Semaine du lundi 11 décembre 2023

- Implémentation du menu de configuration de partie dans le MainMenu.
- Ajout d'un bouton pour activer la configuration de la partie.
- Ajout de la configuration de la taille du labyrinthe.
