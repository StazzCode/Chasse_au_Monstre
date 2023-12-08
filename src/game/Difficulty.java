package game;

/**
 * Cette énumération représente les différents niveaux de difficulté du jeu
 */
public enum Difficulty {
    TRES_FACILE, FACILE, MOYEN, DIFFICILE, TRES_DIFFICILE;
    
	public static Difficulty fromInt(int i){
		return Difficulty.values()[i];
	}
}
