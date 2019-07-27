//Description: This class contains instances and methods for anything related to updating or getting player attributes and stats. 

public class Player {
	// Private access level variables for data hiding. 
	private String name; 
	private String faction = "";
	private String weapon = "";
	private int maxDamage = 1;
	private int maxDefence = 1;
	private int maxAccuracy = 1;
	private int experience;
	private int gold;
	private int numHealthPotions;
	private final int healthPotionEffect;
	private int playerLvl;
	private int bossesDefeated;
	private int playerHealth;
	
	// Constructor 
	public Player() {
		this.name = "";
		this.experience = 0;
		this.gold = 0;
		this.playerLvl = 1;
		this.numHealthPotions = 2;
		this.healthPotionEffect = 30;
		this.bossesDefeated = 0;
	}
	/* 
	 * Modifier methods: 
	 * Takes arguments from JavaGame class 
	 * Sets player attributes and keeps running count of stats such as gold and level using assignment operator +=  
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLevel(int lvl) {
		playerLvl += lvl;
	}
	
	public void setExperience(int exp) {
		this.experience += exp;
	}
	
	public void setGold(int gold) {
		this.gold += gold;
	}
	
	public void setNumHealthPotions(int n) {
		this.numHealthPotions += n;
	}
	
	public void setBossesDefeated(int bossesDefeated) {
		this.bossesDefeated += bossesDefeated;
	}
	
	//Note: a higher accuracy is better, and lower defence is better based on the random generator when deciding for a missed attack. 
	public void setStats(int character) {
		if (character == 1) {
			maxDefence = 50;
			maxAccuracy = 25;
			maxDamage = 70;
			weapon = "Two-headed Axe";
			faction = "gladiator";
			playerHealth = 100;
			
		}
		
		else if (character == 2) {
			maxDefence = 25;
			maxAccuracy = 50;
			maxDamage = 40;
			weapon = "Magic Ball";
			faction = "mage";
			playerHealth = 100;
		}
		
		else if (character == 3) {
			maxDefence = 15;
			maxAccuracy = 15;
			maxDamage = 55;
			weapon = "Shuriken";
			faction = "ninja";
			playerHealth = 100;
		}
	}
	
	// Accessor methods: 
	public String getName() {
		return name;
	}
	
	public String getFaction() {
		return faction;
	}
	
	public String getWeapon() {
		return weapon;
	}
	
	public int getPlayerHealth() {
		return playerHealth;
	}

	public int getDefence() {
		return maxDefence;
	}
	
	public int getAccuracy() {
		return maxAccuracy;
	}
	
	public int getDamage() {
		return maxDamage;
	}	
	
	public int getExperience() {
		return experience;
	}
	
	public int getLevel() {
		return playerLvl;
	}
	
	public int getGold() {
		return gold;
	}
	
	public int getNumHealthPotions() {
		return numHealthPotions;
	}
	
	public int getHealthPotionEffect() {
		return healthPotionEffect;
	}
	
	public int getBossesDefeated() {
		return bossesDefeated;
	}
	
	public String toString() {
		String playerInfo;
		playerInfo = "\r\t Hello " + name + ". Welcome to the faction of the " + faction + ". " + " We are honoured to present to you the " + weapon + ".";
		return playerInfo;
	}
}
