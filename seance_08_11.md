# Groupe H3 SAE Développement d'application (Chasse au monstre)

## Compte rendu de la séance du mercredi 08 Novembre 2023


### Ulysse Couliou

- Modifications de la classe Maze et IHM afin de pouvoir générer un labyrinthe dont la taille et le nombre d'obstacles varient en fonction d'un niveau de difficulté choisi. 
- Ajout d'un nouveau type de cellule (Hole), un trou dans lequel si le monstre tombe dedans, il perd la partie, évènement à programmer par la suite
- Commencement d'une méthode qui utilise l'algorithme de Dijstra pour vérifier qu'un chemin existe entre l'entrée et la sortie du labyrinthe

### Eddy Vantard

- Ajout d'un affichage propre au chasseur et d'un autre propre au monstre (le monstre voit dans un rayon de n cases à déterminer, le chasseur voit les cases sur lesquelles il a déjà tiré).
- Refactoring de plusieurs méthodes dans la classe implémentant le JavaFX .
- Corrections de quelques bugs d'affichage et d'un bug sur la boucle de jeu principale.
- Ajout d'un retour textuel sur les actions des joueurs (sur quoi le chasseur vient de tirer ou si le monstre a tenté un déplacement impossible).
- Ajout d'une énumération Difficulty.
