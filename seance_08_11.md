# Groupe H3 SAE Développement d'application (Chasse au monstre)

## Compte rendu de la séance du mercredi 08 Novembre 2023


### Ulysse Couliou

- Modifications de la classe Maze et IHM afin de pouvoir générer un labyrinthe dont la taille et le nombre d'obstacles varient en fonction d'un niveau de difficulté choisi. 
- Ajout d'un nouveau type de cellule (Hole), un trou dans lequel si le monstre tombe dedans, il perd la partie, évènement à programmer par la suite
- Commencement d'une méthode qui utilise l'algorithme de Dijstra pour vérifier qu'un chemin existe entre l'entrée et la sortie du labyrinthe