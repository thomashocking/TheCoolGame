package TheMainGame.Classes;

import TheMainGame.Classes.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


interface GameConstants {
	public static final long DEFAULT_SEED = 1;
	public static final int EXP_CAP = 100;
	public static final int HEALTH_POTION_COST = 15;
	public static final int MANA_POTION_COST = 20;
}

public class GameControl implements GameConstants {
	HumanCharacter theHumanCharacter;
	Random generator;
	Shop theShop;
	TreasureChest treasureChest;
	MonsterBoss boss;
	File characterFile;
	Scanner input;
	
	public GameControl() {
		this(DEFAULT_SEED);
	}
	
	public GameControl(long seed){
		generator = new Random(seed);
		theHumanCharacter = new HumanCharacter();	
		theShop = new Shop();
		treasureChest = new TreasureChest(seed);
		input = new Scanner(System.in);
	}
	
	public void startGame(){
		System.out.println("Welcome to Dungeons and Danger v1");
		System.out.println("Do you have a saved game?");
		System.out.println("'yes' for Yes");
		System.out.println("'no' for No");
		String choice = input.next();
		if(choice.compareTo("yes") == 0){
			this.loadGame();
			this.characterChoice();
		}
		else{
		this.characterStart(theHumanCharacter);
		this.characterChoice();
		}
	}
	
	private void characterStart(HumanCharacter theHumanCharacter){
		System.out.println("What is your name?");
		System.out.print("Name:");
		String humanName = input.next();
		theHumanCharacter.setName(humanName);
		theHumanCharacter.description();
	}
	
	
	//Prompt Choice from user...
	private void characterChoice(){
		boolean endGame = false;
		while(!endGame){
		Scanner input = new Scanner(System.in);
		String userInput;
		this.levelCheck(theHumanCharacter);
		System.out.println("What would you like to do?");
		System.out.println("1 = Stats");
		System.out.println("2 = Dungeon");
		System.out.println("3 = Shop");
		System.out.println("4 = Save Game");
		System.out.print("Command:");
	
		userInput = input.next();
		
		if(userInput.compareTo("1") == 0){
			theHumanCharacter.description();
		}
		else if(userInput.compareTo("2") == 0){
			System.out.println("You've entered in a dungeon");
			if(theHumanCharacter.getLevel() == 5){
				System.out.println("The Monster Boss waits...");
				this.monsterBossEncounter(theHumanCharacter);
			}
			this.enterDungeon(theHumanCharacter);
			}
		else if(userInput.compareTo("3") == 0){
			System.out.println("You've entered the shop!");
			theShop.enterShop(theHumanCharacter);
			}
		else if(userInput.compareTo("4") == 0){
			String choice;
			System.out.println("Would you like to save?");
			System.out.println("Saving will overwrite data...");
			System.out.println("Type 'save' to save your data.");
			System.out.println("Type 'no' to exit the menu.");
			System.out.print("Command: ");
			choice = input.next();
			if(choice.compareTo("save") == 0){
				this.saveGame(theHumanCharacter);
			}
			else if(choice.compareTo("no") == 0){
				System.out.println("Save Cancled.");
			}
		}
		else{
			
			System.out.println("You didn't enter in a valid command");
			while((userInput.compareTo("1") == 1 )||( userInput.compareTo("2") == 1 )||( userInput.compareTo("3") == 1)){
				System.out.println("What would you like to do?");
				System.out.println("1 = Stats");
				System.out.println("2 = Dungeon");
				System.out.println("3 = Shop");
				System.out.println("4 = Save Game");
				userInput = input.next();
			}
			
		}
	}
}
	//Create dungeon code
	private void enterDungeon(HumanCharacter theHumanCharacter){
		
		int randNum = generator.nextInt(100) + 1;
		if(randNum >= 100){
			theHumanCharacter.openChest(treasureChest.open());
		}
		else{
			this.monsterEncouter(theHumanCharacter);
		}
		//Enter in treasure and monster code here.
	}
	
	private void enterShop(HumanCharacter theHumanCharacter){
		System.out.println("Welcome to the shop!");
		//Enter in shop code.
	}
	
	private void monsterEncouter(HumanCharacter theHumanCharacter){
		//monster encounter code done.
		Monster generatedMonster = this.monsterArrayGenerator();
		System.out.println("You've encountered a " + generatedMonster.getName() + "!");
		this.humanAction(theHumanCharacter, generatedMonster);
	}
	
	private Monster monsterArrayGenerator(){
		Monster[] monsterArray = new Monster[3];
		monsterArray[0] = new Zombie();
		monsterArray[1] = new Ghost();
		monsterArray[2] = new SerialKiller();
		
		int randNum = generator.nextInt(3) + 0;
		return (monsterArray[randNum]);
	}
	
	private void humanAction(HumanCharacter theHumanCharacter,Monster theMonster){
		System.out.println("What would you like to do?");
		System.out.println("Type 'melee' to use melee attack");
		System.out.println("Type 'magic' to use magic attack");
		System.out.println("Type 'potion' to use a health, or mana potion.");
		System.out.println("Type 'info' followed by either 'me' or 'monster' to see stats");
		String choice;
		Scanner input = new Scanner(System.in);
		boolean isDead = false;
		boolean combatDone = false;
		while(!combatDone){
			theHumanCharacter.deadCheck(isDead);
			if(isDead){
				System.exit(0);
			}
			System.out.print("Command: ");
			choice = input.nextLine();
			if(choice.compareTo("melee") == 0){
				//Code is complete.
				combatDone = theHumanCharacter.meleeAttack(theMonster);
				isDead = this.willMonsterAttack(theHumanCharacter, theMonster);
			}
			else if(choice.compareTo("magic") == 0){
				
				//Code is complete.
				combatDone = theHumanCharacter.magicAttack(theMonster);
				isDead = this.willMonsterAttack(theHumanCharacter, theMonster);
			}
			else if(choice.compareTo("potion") == 0){
				theHumanCharacter.usePotion();
			}
			else if(choice.compareTo("info me") == 0){
				theHumanCharacter.description(); 
			}
			else if(choice.compareTo("info monster") == 0){
				theMonster.description();
			}
			else{
				System.out.println("You've entered in a invalid command");
			}
		}
		if(combatDone){
			this.distributeLoot(theHumanCharacter, theMonster);
		}
		
	}
	
	public void distributeLoot(HumanCharacter theCharacter, Monster theMonster){
		this.distributeLootOnKill(theCharacter, theMonster);
	}
	
	private void distributeLootOnKill(HumanCharacter theCharacter, Monster theMonster){
		int newExp = theMonster.getExp();
		int newGold = theMonster.getGold();
		theCharacter.setExpOnLoot(newExp);
		theCharacter.setGoldOnLoot(newGold);
		System.out.println("You've looted " + newGold + " gold!");
		System.out.println("You've gained " + newExp + " exp!");
	}
	
	private boolean willMonsterAttack(HumanCharacter theCharacter,Monster theMonster){
		boolean isCharacterDead;
		int willMonsterAttack;
		willMonsterAttack = generator.nextInt(100) + 1;
		if(willMonsterAttack >= 45){
			isCharacterDead = theMonster.attack(theCharacter);
			return isCharacterDead;
		}
		else {
			System.out.println("You've dodged the monsters attack!");
			return false;
		}
	}
	
	private void levelCheck(HumanCharacter theHumanCharacter){
		if(theHumanCharacter.getExp() >= EXP_CAP){
			System.out.println("LEVEL UP!!!");
				theHumanCharacter.levelUp();
		}
	}
	
	public void monsterBossEncounter(HumanCharacter theHuman){
		boss = new MonsterBoss("The boss");
		this.humanAction(theHumanCharacter, boss);
	}
	
	private void saveGame(HumanCharacter theCharacter){
		try{
		FileOutputStream characterInfo =new FileOutputStream("theCharacter.sav");
	     ObjectOutputStream save = new ObjectOutputStream(characterInfo);

	      // Now we do the save.
	      save.writeObject(theCharacter.getName());
	      save.writeObject(theCharacter.getHealth());
	      save.writeObject(theCharacter.getAttack());
	      save.writeObject(theCharacter.getMana());
	      save.writeObject(theCharacter.getExp());
	      save.writeObject(theCharacter.getGold());
	      save.writeObject(theCharacter.getHealthPotion());
	      save.writeObject(theCharacter.getManaPotion());
	      save.writeObject(theCharacter.getLevel());
	      System.out.println("Saved!");
	      save.close();
		}
		catch(Exception exc){
		   exc.printStackTrace(); // If there was an error, print the info.
		}
	
	}
	
	private void loadGame(){
		try{
		FileInputStream saveFile = new FileInputStream("theCharacter.sav");
		ObjectInputStream restore = new ObjectInputStream(saveFile);
		theHumanCharacter.setName((String)restore.readObject());
		theHumanCharacter.setHealth((Integer)restore.readObject());
		theHumanCharacter.setAttack((Integer)restore.readObject());
		theHumanCharacter.setMana((Integer)restore.readObject());
		theHumanCharacter.setExp((Integer)restore.readObject());
	    theHumanCharacter.setGold((Integer)restore.readObject());
	    theHumanCharacter.setHealthPotion((Integer)restore.readObject());
	    theHumanCharacter.setManaPotion((Integer)restore.readObject());
	    theHumanCharacter.setLevel((Integer)restore.readObject());
		restore.close();
		System.out.println("loaded!");
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}

	
}
