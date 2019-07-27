
//Description: This class contains instances and methods that pertain to checking and getting any achievements and bonuses the player can obtain.

import java.util.Random;

public class Achievement {
	/* Private access level variables for data hiding. */
	private int experience = 0;
	private int level = 1;
	private int bossProgress;
	private int goldDrop;

	/* Constructor */
	public Achievement() {
	}

	/*
	 * Modifier method: Gets exp argument from JavaGame class and adds 1 to running count of level if it exceeds 100. 
	 * If level up, the experience reset is handled in the JavaGame class where an argument of -100 is given to this modifier.
	 */

	public void setExperience(int exp) {
		experience = exp;
		if (experience >= 100) {
			level++;
		}
	}

	/*
	 * Boolean methods to check if user has reached a certain achievement. 
	 * The return value is passed to conditional statements in JavaGame class.
	 */
	public boolean checkLevelUp() {
		if (level > 1) {
			return true;
		} else {
			return false;
		}
	}

	/* Modifier method */
	public void setProgress(int bossesDefeated) {
		bossProgress = bossesDefeated;
	}

	public boolean finalBoss() {
		if (bossProgress > 1) {
			return true;
		} else {
			return false;
		}
	}

	/* Modifier method: handles random gold drop after defeating an enemy. */
	public void setGoldDrop() {
		Random rand = new Random();
		goldDrop = rand.nextInt(10) + 20;
	}

	/* Accessor method */
	public int getGoldDrop() {
		return goldDrop;
	}
}
