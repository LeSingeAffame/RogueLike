package roguelike.entities;

import java.awt.Color;

/* World */
import roguelike.world.*;
import roguelike.creatures.CreatureAi;
import roguelike.items.*;

public class Creature extends Entity {
	private CreatureAi ai;
    public void setCreatureAi(CreatureAi ai) { this.ai = ai; }
    
    /* SPECIAL */
    private Integer strength;
    public Integer strength() { return strength; }
    
    private Integer perception;
    public Integer perception() { return perception; }
    
    private Integer endurance;
    public Integer endurance() { return endurance; }
    
    private Integer charisma;
    public Integer charisma() { return charisma; }
    
    private Integer intelligence;
    public Integer intelligence() { return intelligence; }
    
    private Integer agility;
    public Integer agility() { return agility; }
    
    private Integer luck;
    public Integer luck() { return luck; }
    
    /* Other stats */
    private int baseHp;
    public int baseHp() { return baseHp; }
    
    private int maxHp;
    public int maxHp() { return maxHp; }

    private int hp;
    public int hp() { return hp; }
    
    private int baseHitChance;
    public int baseHitChance() { return baseHitChance; }
    
    private int hitChance;
    public int hitChance() { return hitChance; }
    
    private int baseEvasionChance;
    public int baseEvasionChance() { return baseEvasionChance; }
    
    private int evasionChance;
    public int evasionChance() { return evasionChance; }

    private int baseAttackValue;
    public int baseAttackValue() { return baseAttackValue; }
    
    private int attackValue;
    public int attackValue() { return attackValue; }

    private int baseDefenseValue;
    public int baseDefenseValue() { return baseDefenseValue; }
    
    private int defenseValue;
    public int defenseValue() { return defenseValue; }
    
    private int baseCritChance;
    public int baseCritChance() { return baseCritChance; }
    
    private int critChance;
    public int critChance() { return critChance; }
    
    private int baseVisionRadius;
    public int baseVisionRadius() { return baseVisionRadius; }
    
    private int visionRadius;
    public int visionRadius() { return visionRadius; }
    
    /* Experience and level */
    private int experience;
    public int experience() { return experience; }
    
    private int experienceNeeded = 25;
    public int experienceNeeded() { return experienceNeeded; } 
    
    private int level = 1;
    public int level() { return level; }
    
    /* Inventory */
    private Inventory inventory;
    public Inventory inventory() { return inventory; }
    
    /* Equipment */
    private Gear rightHand;
    public Gear rightHand() { return rightHand; }
    public void changeRightHand(Gear gear) { this.rightHand = gear; }
    
    private Gear leftHand;
    public Gear leftHand() { return leftHand; }
    public void changeLeftHand(Gear gear) { this.leftHand = gear; }
    
    /*
     private Armor head;
     public Armor head() { return head; }
     
     private Armor chest;
     public Armor chest() { return chest; }
     
     private Armor hands;
     public Armor hands() { return hands; }
     
     private Armor legs;
     public Armor legs() { return legs; }
     */

    public Creature(World world,
    				String name,
    				char glyph, Color color,
    				int baseHp, int attack, int defense, int visionRadius,
    				int strength, int perception, int endurance, int charisma, int intelligence, int agility, int luck,
    				int experience){
    	super(world, name, glyph, color);
        
        this.strength 				= 		strength;
        this.perception 			= 		perception;
        this.endurance 				= 		endurance;
        this.charisma 				= 		charisma;
        this.intelligence 			= 		intelligence;
        this.agility 				= 		agility;
        this.luck 					=		luck;
        
        this.baseHp 				=		baseHp;
        this.baseAttackValue 		= 		attack;
        this.baseDefenseValue 		= 		defense;
        this.baseCritChance 		= 		0;
        this.baseEvasionChance 		= 		25;
        this.baseHitChance 			= 		25;
        this.baseVisionRadius 		= 		visionRadius;
        
        this.experience 			= 		experience;
        
        this.hp = this.baseHp()	+ endurance * 5;
        
        updateStats();
        
        this.canBeAttacked = true;
        
        this.inventory = new Inventory(20);
    }
    
    public Entity entity(int wx, int wy, int wz) {
        return world.entity(wx, wy, wz);
    }
    
    public void dig(int wx, int wy, int wz) {
        world.dig(wx, wy, wz);
    }
    
    public void changeExperience(Creature other) {
    	this.experience += other.experience;
    }
    
    public void changeExperience(int amount) {
    	this.experience += amount;
    }
    
    public void giveExperience(Creature other) {
    	changeExperience(other);
    }
    
    public void giveExperience(int amount) {
    	changeExperience(amount);
    }
    
    public boolean hasToLevelUp() {
    	return this.experience >= this.experienceNeeded;
    }
    
    public void levelUp() {
    	this.giveExperience(-this.experienceNeeded());
    	this.level++;
    	this.experienceNeeded *= this.level;
    }
    
    public void increaseStrength(int modification) {
    	int value;
    	if(modification > 0) {
    		value = Math.min(10, this.strength + modification);
    		this.strength = value;
    	} else {
    		value = Math.max(0, this.strength - modification);
    		this.strength = value;
    	}
    }
    
    public void increasePerception(int modification) {
    	int value;
    	if(modification > 0) {
    		value = Math.min(10, this.perception + modification);
    		this.perception = value;
    	} else {
    		value = Math.max(0, this.perception - modification);
    		this.perception = value;
    	}
    }
    
    public void increaseEndurance(int modification) {
    	int value;
    	if(modification > 0) {
    		value = Math.min(10, this.endurance + modification);
    		this.endurance = value;
    	} else {
    		value = Math.max(0, this.endurance - modification);
    		this.endurance = value;
    	}
    }
    
    public void increaseCharisma(int modification) {
    	int value;
    	if(modification > 0) {
    		value = Math.min(10, this.charisma + modification);
    		this.charisma = value;
    	} else {
    		value = Math.max(0, this.charisma - modification);
    		this.charisma = value;
    	}
    }
    
    public void increaseIntelligence(int modification) {
    	int value;
    	if(modification > 0) {
    		value = Math.min(10, this.intelligence + modification);
    		this.intelligence = value;
    	} else {
    		value = Math.max(0, this.intelligence - modification);
    		this.intelligence = value;
    	}
    }
    
    public void increaseAgility(int modification) {
    	int value;
    	if(modification > 0) {
    		value = Math.min(10, this.agility + modification);
    		this.agility = value;
    	} else {
    		value = Math.max(0, this.agility - modification);
    		this.agility = value;
    	}
    }
    
    public void increaseLuck(int modification) {
    	int value;
    	if(modification > 0) {
    		value = Math.min(10, this.luck + modification);
    		this.luck = value;
    	} else {
    		value = Math.max(0, this.luck - modification);
    		this.luck = value;
    	}
    }
    
    public void increaseStat(char c, int modification) {
    	switch(c) {
	    	case 's' : increaseStrength(modification); 		break;
	    	case 'p' : increasePerception(modification); 	break;
	    	case 'e' : increaseEndurance(modification); 	break;
	    	case 'c' : increaseCharisma(modification); 		break;
	    	case 'i' : increaseIntelligence(modification); 	break;
	    	case 'a' : increaseAgility(modification); 		break;
    		case 'l' : increaseLuck(modification); 			break;
    	}
    	this.updateStats();
    }
    
    public void changeStat(Integer oldStat, Integer newStat) {
    	oldStat = newStat;
    }
    
    public void moveBy(int mx, int my, int mz){
    	if (mx==0 && my==0 && mz==0)
    	    return;
    	
        Entity other = world.entity(x+mx, y+my, z+mz);
      
        if (other == null)
            ai.onEnter(x+mx, y+my, z+mz, world.tile(x+mx, y+my, z+mz));
        else
            interact(other);
    }
    
    public void moveBy(int mx, int my){
    	moveBy(mx, my, 0);
    }
    
    public void goLeft() {
    	moveBy(-1, 0);
    }
    
    public void goRight() {
    	moveBy( 1, 0);
    }
    
    public void goUp() {
    	moveBy( 0,-1);
    }
    
    public void goDown() {
    	moveBy( 0, 1);
    }
    
    public void moveTo(int x, int y, int z) {
    	Creature other = world.creature(x, y, z);
    	
    	if(other == null) {
    		ai.onEnter(x,  y, z, world.tile(x, y, z));
    	}
    }
    
    public void update(){   
        ai.onUpdate();
        updateStats();
    }
    
    public void updateStats() {
    	this.maxHp 			= this.baseHp()				+ endurance * 5;
        this.attackValue 	= this.baseAttackValue()	+ strength;
        this.defenseValue 	= this.baseDefenseValue();
        this.hitChance		= this.baseHitChance() 		+ agility * 5	+ luck * 2;
        this.evasionChance	= this.baseEvasionChance() 	+ agility * 5	+ luck * 2;
        this.critChance		= this.baseCritChance() 	+ luck;
        this.visionRadius 	= this.baseVisionRadius() 	+ perception / 3;
    }
    
    public boolean canEnter(int wx, int wy, int wz) {
    	return world.tile(wx, wy, wz).isGround() && world.entity(wx, wy, wz) == null;
	}
    
    public void attack(Creature other){
    	int hitChance = (int)((double)this.hitChance()/ (double)other.evasionChance() * 100);
    	if(hitChance >= (int) (Math.random() * 100)) {
    		int amount = Math.max(0, attackValue() - other.defenseValue());
    	    
            amount = (int)(Math.random() * amount) + 1;
            
            if(this.critChance() >= (int) (Math.random() * 100)) {
            	amount *= 2;
            	
            	doAction("critically attack the '%s' for %d damage (%s) !!", other.name, amount, hitChance);
            } else {
            	doAction("attack the '%s' for %d damage (%s)", other.name, amount, hitChance);
            }
            
            if(other.modifyHp(-amount))
            	this.giveExperience(other);

            other.ai.onAttacked(this);
    	} else {
    		doAction("tried to attack the '%s' but failed (%s)", other.name, hitChance);
    	}
    }
    
    public void interact(Entity entity){
        if(entity.canBeAttacked())
        	attack((Creature)entity);
        
        if(entity.canBeUsed() && this.isPlayer()) {
        	Item item = (Item)entity;
        	pickup(item);
        }
        
        if(entity.canBeEquipped() && this.isPlayer()) {
        	Gear gear = (Gear)entity;
        	if(gear.isWeapon()) {
        		Weapon weapon = (Weapon)gear;
        		if(this.rightHand() == null)
        			weapon.equip(this);
        		else if(this.leftHand() == null)
        			weapon.equip(this, false);
        		else
        			this.pickup(weapon);
        	}
        }
    }
    
    public void pickup(Item item) {
    	if(inventory.isFull()) {
    		item.use(this);
    	} else {
    		doAction("pickup a %s", item.name());
    		inventory.add(item);
    		item.remove();
    	}
    }
    
    public void pickup(Gear gear) {
    	if(inventory.isFull()) {
    	} else {
    		doAction("pickup a %s", gear.name());
    		inventory.add(gear);
    		gear.remove();
    	}
    }

    public boolean modifyHp(int amount) {
        hp += amount;
    
        if (hp < 1) {
        	doAction("die");
         	world.remove(this);
         	
         	return true;
        }
        
        return false;
    }
    
    public void heal(int amount) {
    	int hp = Math.min(amount, this.maxHp - this.hp);
    	
    	modifyHp(hp);
    }
    
    public void notify(String message, Object ... params){
        ai.onNotify(String.format(message, params));
    }
    
    public boolean isPlayer() {
    	return ai.isPlayer();
    }
    
    public boolean canSee(int wx, int wy, int wz){
        return ai.canSee(wx, wy, wz);
    }
    
    public boolean canSee(Point p){
        return ai.canSee(p);
    }
    
    public boolean canSeePlayer() {
    	return canSee(world.playerPosition());
    }
    
    public String stats() {
    	String ret = "";
    	
    	ret += "Strength: " + this.strength() + "  -  ";
    	ret += "Perception: " + this.perception() + "  -  ";
    	ret += "Endurance: " + this.endurance() + "  -  ";
    	ret += "Charisma: " + this.charisma() + "  -  ";
    	ret += "Intelligence: " + this.intelligence() + "  -  ";
    	ret += "Agility: " + this.agility() + "  -  ";
    	ret += "Luck: " + this.luck();
    	
    	return ret;
    }
}