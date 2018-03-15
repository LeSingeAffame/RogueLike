package roguelike.entities;

import java.awt.Color;

import roguelike.world.*;
import roguelike.items.ItemEffect;

public class Item extends Entity {
	private ItemEffect effect;
    public void setItemEffect(ItemEffect effect) { this.effect = effect; }
    
    private int nbUses;
    public int nbUses() { return this.nbUses; }

    public Item(World world, String name, char glyph, Color color, int nbUses) {
    	super(world, name, glyph, color);
    	canBeUsed = true;
    	this.nbUses = nbUses;
    }
    
    // Classic consumable, 1 use
    public Item(World world, String name, char glyph, Color color) {
    	this(world, name, glyph, color, 1);
    }
    
    public void changeUsesBy(int modification) {
    	this.nbUses = Math.max(0, this.nbUses + modification);
    	
    	if(this.nbUses() == 0) {
    		this.remove();
    	}
    }
    
    public void setUsesTo(int nbUses) {
    	this.nbUses = nbUses;
    }
    
    public void depleteUses(int depletion) {
    	this.changeUsesBy(-depletion);
    }
    
    public void depleteUses() {
    	this.depleteUses(1);
    }
    
    public void use(Creature c) {
    	this.effect.onUsage(c);
    	
    	if(this.nbUses() == -1) {
    		// Permanent item
    		return;
    	} else if (this.nbUses() > 1) {
    		// Some uses left
    		this.depleteUses();
    	} else {
    		// No use left
    		c.inventory().remove(this);
    		this.remove();
    	}
    	
    	System.out.println(this.name() + " : " + this.nbUses());
    }
}