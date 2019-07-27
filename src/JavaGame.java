//Name: Andy Chen
//Date: July 21, 2019
//Description: A text-based adventure Java game that has multiple characters, experience, game progress/bosses, currency and merchant, combat, character attributes (defence, damage, accuracy), inventory/profile checker, and ASCII Art.
//There is multiple classes for encapsulation and making important data private such as player attributes, player level/exp, player health, number of health potions, and gold.

import java.util.Scanner;
import java.util.Random;

public class JavaGame {

	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
	
		// Creates objects from the Player and Art classes
		Player objPlayerBuild = new Player();
		Art objArt = new Art();
		
		// Uses the accessor method in Player class as an argument for the modifier method in Art class. Prints ASCII art depending on user faction choice.
		objArt.setSetting("Welcome");
		System.out.println(objArt.getArtwork());
		
		System.out.print("\rPlease enter your character name: ");
		objPlayerBuild.setName(console.next());
		
		System.out.println("\rCHOOSE YOUR FACTION");
		System.out.println("(1) The Gladiator is a fierce warrior with the highest damage, average accuracy, and lowest defence.");
		System.out.println("(2) The Mage yields magic with the highest accuracy, average defence, and lowest damage.");
		System.out.println("(3) The Ninja is a sneaky fighter with the highest defence, average danage, and lowest accuracy.");
		System.out.print("Enter your selection: ");
		
		// Modifier method in Player class to set player stats based on faction choice. toString method prints user choices.
		objPlayerBuild.setStats(console.nextInt());	
		System.out.println(objPlayerBuild.toString());
		
		// Uses the accessor method in Player class as an argument for the modifier method in Art class. Prints ASCII art for weapon depending on user faction choice.
		objArt.setWeaponArt(objPlayerBuild.getFaction());
		System.out.println(objArt.getArtwork());
		
		System.out.println("\r\t Welcome to the kingdom of Torenia in the year 450 AD. It is dark times where monsters plague the land and the evil Lord Drakkar Grail has taken power."
				+ "\n\t Your job will be to defeat monsters, two bosses, and finally Lord Drakkar to free princess Adelia and restore peace. Good luck...");
		
		// Sends argument to modifier method to print the correct setting ASCII art with the accessor method in the print line. 
		objArt.setSetting("Gate");
		System.out.println(objArt.getArtwork()
				+ "\n\r\t Defeating monsters will give you gold and experience. With enough experience you will level up. Gold can be used to purchase health potions that can be extremely helpful in battle."
				+ "\n\t Note that bosses have more HP and damage than regular monsters so only challenge them when you are ready."); 
		
		// Accessor method to get starting player health. Purpose is for encapsulation so user cannot set their own health. 
		int playerHealth = objPlayerBuild.getPlayerHealth();
	
		boolean running = true;
		
		while(running) {
			System.out.println("---------------------------------------------------------------------------------------------");
			
			/* 
			 * Creates objects from the ActionSelector and Achievement class.
			 * Sends user input as an argument to the modifier method in ActionSelector class which then prints respective prompts with the toString method.
			 * User input is stored in ActionSelector class and taken as a an argument for the modifier method to set/generate random enemy stats.
			 * Random enemy health value generated from the modifier method is assigned using the accessor method in ActionSelector class. 
			 */
			
			ActionSelector objMenu = new ActionSelector();
			Achievement objAchievement = new Achievement();	
			System.out.println("\r(1) Find and attack a monster.");
			System.out.println("(2) Find and challenge a boss.");
			System.out.println("(3) Open player profile and inventory.");
			System.out.println("(4) Access the Merchant.");
			System.out.println("(5) Fight final boss Lord Drakkar.");
			System.out.print("What would you like to do? ");
			objMenu.setPrompts(console.nextInt());
			objMenu.setEnemyStats(objMenu.getChoice());
			int enemyHealth = objMenu.getEnemyHealth();
			System.out.println(objMenu.toString());
			
			
			if(objMenu.getChoice() == 1) {
				
				// Argument for Art class modifier is the randomly generated monster name from the ActionSelector Class. Prints ASCII art for the respective monster with accesor method in Art Class.
				objArt.setMonster(objMenu.getEnemyName());
				System.out.println(objArt.getArtwork());
				
				while(enemyHealth > 0) {	
					
					System.out.println("\tYour HP: " + playerHealth);
					System.out.println("\t" + objMenu.getEnemyName() + "'s HP: " + enemyHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t(1) Attack");
					System.out.println("\t(2) Drink Health Potion");
					int input = console.nextInt();
					Random rand = new Random();
					
					if(input == 1) {
						/* 
						 * Gets a random value for each player stat using the previously generated max values in Player class.
						 * playerMiss and enemyMiss is used to determine if zero damage is inflicted by either character.
						 * Damage inflicted by enemy and player are deducted from health each turn 
						 */
 
						int dmgDealt = rand.nextInt(objPlayerBuild.getDamage()) + 10;
						int enemyMiss = rand.nextInt(objPlayerBuild.getDefence());
						int playerMiss = rand.nextInt(objPlayerBuild.getAccuracy());
						int dmgTaken = objMenu.getEnemyAttack();
						
						
						if(enemyMiss < 5 && playerMiss < 5) {
							dmgTaken = 0; 
							dmgDealt = 0;
							enemyHealth -= dmgDealt;
							playerHealth -= dmgTaken;
							System.out.println("\t> Good Defence! The enemy has missed.");
							System.out.println("\t The enemy moved too quickly! You have missed.");
						}
						
						else if(enemyMiss < 5) {
							dmgTaken = 0; 
							enemyHealth -= dmgDealt;
							playerHealth -= dmgTaken;
							System.out.println("\t> Good Defence! The enemy has missed.");
							System.out.println("\t> You hit the " + objMenu.getEnemyName() + " for " + dmgDealt + " damage.");
						}
						else if(playerMiss < 5) {
							dmgDealt = 0;
							enemyHealth -= dmgDealt;
							playerHealth -= dmgTaken;
							System.out.println("\t The enemy moved too quickly! You have missed.");
							System.out.println("\t You recieve " + dmgTaken + " in retailiation");
						}
						else {
							enemyHealth -= dmgDealt;
							playerHealth -= dmgTaken;
					
							System.out.println("\t> You hit the " + objMenu.getEnemyName() + " for " + dmgDealt + " damage.");
							System.out.println("\t You recieve " + dmgTaken + " in retaliation");
						}
						
						/* Player loses. This prints ending sequence and ASCII art using the accesor and modifier methods in Art class. Ends the program and closes Scanner. */
						if(playerHealth < 1) {
							System.out.println("\t You have taken too much damage and succombed to your injuries. GAME OVER.");
							objArt.setSetting("sadEnding");
							System.out.println(objArt.getArtwork());
							console.close();
							System.exit(0);
						}
						
						/* 
						 * Player beats the enemy. Using modifier methods the experience of player increases and sends this value to the Achievement class modifier using accessor method of Player class.
						 * Player is awarded with a random gold drop with the modifier and accessor methods in Player class 
						 */
						if(enemyHealth < 1) {
							objPlayerBuild.setExperience(20);
							objAchievement.setExperience(objPlayerBuild.getExperience());
							objAchievement.setGoldDrop();
							objPlayerBuild.setGold(objAchievement.getGoldDrop());
							System.out.println("\r\t You have defeated the " + objMenu.getEnemyName()+ ". You have gained 20 exp points and " + objAchievement.getGoldDrop() + " gold!");
							
							/* Based on the updated experience value given to the Achievement class, this boolean method in Achievement class will check for level up.
							 * If true, instances in Player class are updated using appropriate modifier methods. */
							if(objAchievement.checkLevelUp()) {
								objPlayerBuild.setNumHealthPotions(2);
								objPlayerBuild.setExperience(-100); 
								objPlayerBuild.setLevel(1);
								objPlayerBuild.setGold(100);
								System.out.println("\r\t You have leveled up to level " + objPlayerBuild.getLevel() + "! Check your inventory for 2 health potions and 100 gold.");
									
							}
							System.out.println("\t Your new experience is " + objPlayerBuild.getExperience() +"/100.");
						}
					}
					// Handles user choice to drink health potion based on accessor methods in Player Class. Health and potion inventory is updated using appropriate modifier classes.
					else if(input == 2) {
						if(objPlayerBuild.getNumHealthPotions() > 0) {
							playerHealth += objPlayerBuild.getHealthPotionEffect();
							objPlayerBuild.setNumHealthPotions(-1);
							System.out.println("\t> You drank a health potion for " + objPlayerBuild.getHealthPotionEffect()
									         + "\n\t> You now have " + playerHealth + " HP.");
						}
						
						else {
							System.out.println("\t> You have no health potions left. Please level up or access the merchant to purchase more.");
						}
					}
				}	
			}
			
			if(objMenu.getChoice() == 2) {
				objArt.setMonster(objMenu.getEnemyName());
				System.out.println(objArt.getArtwork());
				while(enemyHealth > 0) {
					System.out.println("\tYour HP: " + playerHealth);
					System.out.println("\t" + objMenu.getEnemyName() + "'s HP: " + enemyHealth);
					System.out.println("\n\tWhat would you like to do?");
					System.out.println("\t(1) Attack");
					System.out.println("\t(2) Drink Health Potion");
			
					int input = console.nextInt();
					Random rand = new Random();
					if(input == 1) {
						int dmgDealt = rand.nextInt(objPlayerBuild.getDamage()) + 10;
						int dmgTaken = objMenu.getEnemyAttack();
						int enemyMiss = rand.nextInt(objPlayerBuild.getDefence());
						int playerMiss = rand.nextInt(objPlayerBuild.getAccuracy());
					
						if(enemyMiss < 5 && playerMiss < 5) {
							dmgTaken = 0; 
							dmgDealt = 0;
							enemyHealth -= dmgDealt;
							playerHealth -= dmgTaken;
							System.out.println("\t> Good Defence! The enemy has missed.");
							System.out.println("\t The enemy moved too quickly! You have missed.");
						}
					
						else if(enemyMiss < 5) {
							dmgTaken = 0; 
							enemyHealth -= dmgDealt;
							playerHealth -= dmgTaken;
							System.out.println("\t> Good Defence! The enemy has missed.");
							System.out.println("\t> You hit the " + objMenu.getEnemyName() + " for " + dmgDealt + " damage.");
						}
						else if(playerMiss < 5) {
							dmgDealt = 0;
							enemyHealth -= dmgDealt;
							playerHealth -= dmgTaken;
							System.out.println("\t The enemy moved too quickly! You have missed.");
							System.out.println("\t You recieve " + dmgTaken + " in retailiation");
						}
						else {
							enemyHealth -= dmgDealt;
							playerHealth -= dmgTaken;
				
							System.out.println("\t> You hit the " + objMenu.getEnemyName() + " for " + dmgDealt + " damage.");
							System.out.println("\t You recieve " + dmgTaken + " in retaliation");
						}
						
						if(playerHealth < 1) {
							System.out.println("\t You have taken too much damage and succombed to your injuries. GAME OVER.");
							objArt.setSetting("sadEnding");
							System.out.println(objArt.getArtwork());
							console.close();
							System.exit(0);					
						}
						
						// Player awarded different value of experience than a normal monster. Uses the modifier method in Player class to increase count of bosses defeated by 1. 
						if(enemyHealth < 1) {
							objPlayerBuild.setBossesDefeated(1);
							objPlayerBuild.setExperience(80);
							objAchievement.setExperience(objPlayerBuild.getExperience());		
							objAchievement.setGoldDrop();
							objPlayerBuild.setGold(objAchievement.getGoldDrop());
							System.out.println("\r\t You have defeated the boss " + objMenu.getEnemyName()+ ". You have gained 80 exp points and " + objAchievement.getGoldDrop() + " gold!");
							
							if(objAchievement.checkLevelUp()) {	
								objPlayerBuild.setNumHealthPotions(2);
								objPlayerBuild.setExperience(-100);
								objPlayerBuild.setLevel(1);
								objPlayerBuild.setGold(100);
								System.out.println("\r\t You have leveled up to level " + objPlayerBuild.getLevel() + "! Check your inventory for 2 health potions and 100 gold.");
								
							}
							/* 
							 * Achievement class modifier changes value for number of bosses defeated using accessor method in Player class. 
							 * Boolean method in Achievement class determines if player is eligible to face the final boss. 
							 */
							objAchievement.setProgress(objPlayerBuild.getBossesDefeated());
							if(objAchievement.finalBoss()) {
								System.out.println("\r\t You have defeated at least two bosses. You are now ready to challenge Lord Drakkar Grail! (Tip: Be well equipped with health potions!");
							}
							
						System.out.println("\t Your new experience is " + objPlayerBuild.getExperience() +"/100.");
							
						}		
					}
					else if(input == 2) {
						if(objPlayerBuild.getNumHealthPotions() > 0) {
							playerHealth += objPlayerBuild.getHealthPotionEffect();
							objPlayerBuild.setNumHealthPotions(-1);
							System.out.println("\t> You drank a health potion for " + objPlayerBuild.getHealthPotionEffect()
								         	+ "\n\t> You now have " + playerHealth + " HP.");
						}
					
						else {
							System.out.println("\t> You have no health potions left. Please level up or access the merchant to purchase more.");
						}
					}
				}
			}
			
			if(objMenu.getChoice() == 3) {
				System.out.println("\t**************************************************************************************"
							+   "\n\t Player Profile of the " + objPlayerBuild.getFaction() + " " + objPlayerBuild.getName()
							+   "\n\t Your weapon of choice is " + objPlayerBuild.getWeapon()
							+   "\n\t Your level is " + objPlayerBuild.getLevel() + " and your experience is " + objPlayerBuild.getExperience() + "/100."
							+   "\n\t Your total HP: " + playerHealth
							+   "\n\t Your total potions: " + objPlayerBuild.getNumHealthPotions()
							+   "\n\t Your total gold: " + objPlayerBuild.getGold() 
							+	"\n\t Progress: " + objPlayerBuild.getBossesDefeated() + " bosses defeated. "+ (2 - objPlayerBuild.getBossesDefeated()) + " left before final boss."
							+	"\n\t **************************************************************************************");
			}
			
			if(objMenu.getChoice() == 4) {
				// Creates new object from ActionSelector class to handle purchases. 
				ActionSelector objPurchase = new ActionSelector();
				
				//Modifier method in Art class used to determine and print correct ASCII art using accessor method. 
				objArt.setSetting("Merchant");
				System.out.println(objArt.getArtwork());
				System.out.print("\t**************************************************************************************"
							+	"\n\t Purchase a health potion for 50 gold each."
							+   "\n\t Enter quantity: ");
				objPurchase.setQuantity(console.nextInt());
				
				
				/* 
				 * Deducts gold and increases number of potions from Player class using a modifier method. 
				 * If player does not have enough gold, the purchase gold total is returned back to player using modifier method in Player Class. 
				 */
				objPlayerBuild.setGold(-objPurchase.getTotal());
				
				if(objPlayerBuild.getGold() < 0) {
					objPlayerBuild.setGold(objPurchase.getTotal());
					System.out.println("\t Sorry you do not have enough gold to make this purchase.");
				}
				
				else if(objPlayerBuild.getGold() >= 0) {
					System.out.println("\t Thank you for your business " + objPlayerBuild.getName() + "! Your total is " + objPurchase.getTotal() + " gold.");
					objPlayerBuild.setNumHealthPotions(objPurchase.getQuantity());
				}
			}
			
			if(objMenu.getChoice() == 5) {
				// Accessor method in Player class checks to see if player has defeated two bosses. If not, returns message and does not allow player to face Final boss.
				if(objPlayerBuild.getBossesDefeated() >= 2) {
					System.out.println("\t# After a long and bloody journey, you find yourself at Lord Drakkar's steps... #\n");
					objArt.setMonster(objMenu.getEnemyName());
					System.out.println(objArt.getArtwork());
					
					while(enemyHealth > 0) {
						
						System.out.println("\r\tYour HP: " + playerHealth);
						System.out.println("\t" + objMenu.getEnemyName() + "'s HP: " + enemyHealth);
						System.out.println("\n\tWhat would you like to do?");
						System.out.println("\t(1) Attack");
						System.out.println("\t(2) Drink Health Potion");
					
						int input = console.nextInt();
						Random rand = new Random();
						if(input == 1) {
							int dmgDealt = rand.nextInt(objPlayerBuild.getDamage()) + 10;
							int dmgTaken = objMenu.getEnemyAttack();
							int enemyMiss = rand.nextInt(objPlayerBuild.getDefence());
							int playerMiss = rand.nextInt(objPlayerBuild.getAccuracy());
							
							if(enemyMiss < 5 && playerMiss < 5) {
								dmgTaken = 0; 
								dmgDealt = 0;
								enemyHealth -= dmgDealt;
								playerHealth -= dmgTaken;
								System.out.println("\t> Good Defence! The enemy has missed.");
								System.out.println("\t The enemy moved too quickly! You have missed.");
							}
							
							else if(enemyMiss < 5) {
								dmgTaken = 0; 
								enemyHealth -= dmgDealt;
								playerHealth -= dmgTaken;
								System.out.println("\t> Good Defence! The enemy has missed.");
								System.out.println("\t> You hit the " + objMenu.getEnemyName() + " for " + dmgDealt + " damage.");
							}
							else if(playerMiss < 5) {
								dmgDealt = 0;
								enemyHealth -= dmgDealt;
								playerHealth -= dmgTaken;
								System.out.println("\t The enemy moved too quickly! You have missed.");
								System.out.println("\t You recieve " + dmgTaken + " in retailiation");
							}
							else {
								enemyHealth -= dmgDealt;
								playerHealth -= dmgTaken;
						
								System.out.println("\t> You hit the " + objMenu.getEnemyName() + " for " + dmgDealt + " damage.");
								System.out.println("\t You recieve " + dmgTaken + " in retaliation");
							}
							
							if(playerHealth < 1) {
								System.out.println("\t You have taken too much damage and succombed to your injuries. GAME OVER.");
								objArt.setSetting("sadEnding");
								System.out.println(objArt.getArtwork());
								console.close();
								System.exit(0);
							}
							
							/* 
							 * Player wins the game. Prints winning messages and ASCII art.
							 * Modifier method in Art Class is used to select the right setting and print it using the accessor method. 
							 * Close the console and exits the program. 
							 */
							if(enemyHealth < 1) {
								System.out.println("\r\t You have defeated the " + objMenu.getEnemyName() + " and freed the land!!!" 
													+"\n\t The entire kingdom thanks you Sir " + objPlayerBuild.getName() + ". You have rescued Princess Adelia and live happily ever after...");
								
								objArt.setSetting("Castle");
								System.out.println("\r" + objArt.getArtwork());
								objArt.setSetting("happyEnding");
								System.out.println(objArt.getArtwork());
								
								console.close();
								System.exit(0);
							}
						}
						else if(input == 2) {
							if(objPlayerBuild.getNumHealthPotions() > 0) {
								playerHealth += objPlayerBuild.getHealthPotionEffect();
								objPlayerBuild.setNumHealthPotions(-1);
								System.out.println("\t> You drank a health potion for " + objPlayerBuild.getHealthPotionEffect()
										         + "\n\t> You now have " + playerHealth + " HP.");
							}
							
							else {
								System.out.println("\t> You have no health potions left. Please level up or access the merchant to purchase more.");
							}
						}
					}
				}
				else {
					System.out.println("You are not worthy to face Lord Drakkar. Please beat at least two bosses first.");
				}
			}
		}
	}
}
