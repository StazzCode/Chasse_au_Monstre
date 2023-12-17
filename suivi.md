# Groupe H3 SAE Développement d'application (Chasse au monstre)

## Compte rendu du jalon 1 au jalon 2


### Ulysse Couliou

- Enumeration Difficulty qui permet de gérer différents niveau de difficulté pour le jeu.
- IA simple pour le chasseur
- Ecriture et génération de la Javadoc
- Modifications dans Maze et IHM
- Ecriture du Readme.md

### Eddy Vantard

- Ajout d'un affichage propre au chasseur et d'un autre propre au monstre (le monstre voit dans un rayon de n cases à déterminer, le chasseur voit les cases sur lesquelles il a déjà tiré).
- Refactoring de plusieurs méthodes dans la classe implémentant le JavaFX.
- Corrections de quelques bugs d'affichage et d'un bug sur la boucle de jeu principale.
- Ajout d'un retour textuel sur les actions des joueurs (sur quoi le chasseur vient de tirer ou si le monstre a tenté un déplacement impossible).
- Ajout d'une énumération Difficulty.

### Kellian Mirey

- Implémentation de la dernière apparition du Monstre sur une case (LastAppearance) dans le jeu.
- Affichage de la dernière apparition du Monstre sur case découverte par le Chasseur, pour la vue du Chasseur.
- Ajout et correction de la méthode isDistanceMoreThan1(Coordinate c) dans la classe Monster.
- Ajout de la méthode canMoveWithDiagonals(Coordinate c) dans la classe Monster.