//Description: This class contains instances and methods that pertain to the user input at the menu selection stage. 

import java.util.Random;

public class ActionSelector {
	// Private access level variables for data hiding. 
	private String prompt;
	private String enemy;
	private int enemyAttack;
	private int enemyHealth = 0;
	private int choice;
	private int quantity;
	
	// Constructor 
	public ActionSelector() {
		this.choice = 0;
	}
	
	/* 
	 * Modifier methods:
	 * Outputs a different prompt depending on the user choice at the menu selection. 
	 * Sets values for instances that can be used by calling the respective accessor methods
	 * For choice 1 and 2, a random String is generated from an array to randomize the monster/boss the player faces
	 */
	public void setPrompts(int choice) {
		Random rand = new Random();
		this.choice = choice;
		if(choice == 1) {
			String[] enemies = { "Tricky Goblin", "Wild Baboon", "Hairy Beast" };
			enemy = enemies[rand.nextInt(enemies.length)];
			prompt = "\t# " + enemy + " has appeared! #\n";
		
		}
		else if(choice == 2) {
			String[] enemies = { "Mystical Dragon", "Red-Eyed Demon", "Dark Witch", "Nightmare Clown", "Dark Knight"};	
			enemy = enemies[rand.nextInt(enemies.length)];
			prompt = "\t# You have challenged boss " + enemy + "! #\n";
		}		
		else if(choice == 3) {
			prompt = "\t# You have opened your inventory/profile. You can check your stats and potions here. #\n";
		}
		else if(choice == 4) {
			prompt = "\t# Hello there! My name is Bob the Merchant. What can I do for you today? #\n";
		}
		else if(choice == 5) {
			enemy = "Lord Drakkar";
			prompt = "";
		}
	}
	
	/* 
	 * Sets different enemy stats based on if the user chooses a regular monster, boss, or final boss to fight. 
	 * Chooses a random value for each, but outputs a different base and max value depending on user input. 
	 */ 
	
	public void setEnemyStats(int choice) {
		Random rand = new Random();
		if(choice == 1) {
			enemyAttack = rand.nextInt(10) + 5;
			enemyHealth = rand.nextInt(30) + 10;
		}
		else if(choice == 2) {
			enemyAttack = rand.nextInt(30) + 10;
			enemyHealth = rand.nextInt(100) + 80;
		}
		
		else if(choice == 5) {
			enemyAttack = rand.nextInt(40) + 20;
			enemyHealth = 220 ;
		}
		else {
			enemyAttack = 1;
			enemyHealth = 1;
		}
	}
	
	public void setQuantity(int quant) {
		quantity = quant;
	}
	
	// Accessor methods
	public int getTotal() {
		return quantity * 50;
	}
		
	public int getChoice() {
		return choice;
	}
	
	public int getEnemyAttack() {
		return enemyAttack;
	}
	
	public int getEnemyHealth() {
		return enemyHealth;
	}
		
	public int getQuantity() {
		return quantity;
	}
	
	public String getEnemyName() {
		return enemy;
	}
	
	public String toString() {
		String prompters;
		prompters = prompt;
		return prompters;
	}
}
