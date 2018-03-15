package roguelike.creatures;

import roguelike.world.*;

import roguelike.entities.*;

public class CreatureAi {
    protected Creature creature;
    protected boolean isPlayer = false;

    public CreatureAi(Creature creature){
        this.creature = creature;
        this.creature.setCreatureAi(this);
    }
    
    public void onEnter(int x, int y, int z, Tile tile){
        if (tile.isGround()){
             creature.x = x;
             creature.y = y;
             creature.z = z;
        } else {
             creature.doAction("bump into a wall");
        }
    }
    
    public void onUpdate(){
    	// Nothing generic but still needed
    }
    
    public void onDeath() {
    	// Nothing generic but still needed
    }
    
    public void onNotify(String message) {
    	// Nothing generic but still needed
    }
    
    public void onAttacked(Creature other) {
    	// Nothing generic but still needed
    }
    
    public boolean isPlayer() {
    	return isPlayer;
    }
    
    public boolean canSee(int wx, int wy, int wz) {
        if (creature.z != wz)
            return false;
    
        if ((creature.x-wx)*(creature.x-wx) + (creature.y-wy)*(creature.y-wy) > creature.visionRadius()*creature.visionRadius())
            return false;
    
        for (Point p : new Line(creature.x, creature.y, wx, wy)){
            if (creature.tile(p.x, p.y, wz).isGround() || p.x == wx && p.y == wy)
                continue;
        
            return false;
        }
    
        return true;
    }
    
    public boolean canSee(Point p) {
        return canSee(p.x, p.y, p.z);
    }
    
    public boolean canSeePlayer() {
    	if (isPlayer())
    		return true;
    	
    	return creature.canSeePlayer();
    }
    
    public void wander(){
        int mx = (int)(Math.random() * 3) - 1;
        int my = (int)(Math.random() * 3) - 1;
        
        Entity other = creature.entity(creature.x + mx, creature.y + my, creature.z);
        
        if (other != null && other.glyph() == creature.glyph() && creature.tile(creature.x + mx, creature.y + my, creature.z).isGround())
            return;
        else
            creature.moveBy(mx, my, 0);
    }
    
    public void wanderRandom(){
        int mx = (int)(Math.random() * 3) - 1;
        int my = (int)(Math.random() * 3) - 1;
        
        Entity other = creature.entity(creature.x + mx, creature.y + my, creature.z);
        
        if (other != null && other.glyph() == creature.glyph() && creature.tile(creature.x + mx, creature.y + my, creature.z).isGround())
            return;
        else
            creature.moveBy(mx, my, 0);
    }
    
    public void wanderTowardsPlayer() {
    	if(!canSeePlayer())
    		wanderRandom();
    	else {
    		int creaturePlayerX = this.creature.x - this.creature.playerPosition().x;
    		int creaturePlayerY = this.creature.y - this.creature.playerPosition().y;
    		
    		boolean hasToGoUp = false;
    		boolean hasToGoDown = false;
    		boolean hasToGoLeft = false;
    		boolean hasToGoRight = false;
    		
    		if(creaturePlayerY < 0)
				hasToGoDown = true;
			else if(creaturePlayerY > 0)
				hasToGoUp = true;
    		
    		if(creaturePlayerX < 0)
				hasToGoRight = true;
			else if(creaturePlayerX > 0)
				hasToGoLeft = true;
    		
    		if(hasToGoDown) {
    			if(creature.tile(creature.x, creature.y + 1).isGround()) {
	    			creature.goDown();
	    			return;
	    		} else {
	    			if(hasToGoLeft)
	    				creature.goLeft();
	    			else
	    				creature.goRight();
	    			return;
	    		}
    		}
    		
    		if(hasToGoUp) {
    			if(creature.tile(creature.x, creature.y - 1).isGround()) {
	    			creature.goUp();
	    			return;
	    		} else {
	    			if(hasToGoLeft)
	    				creature.goLeft();
	    			else
	    				creature.goRight();
	    			return;
	    		}
    		}
    		
    		if(hasToGoRight) {
    			if(creature.tile(creature.x + 1, creature.y).isGround()) {
	    			creature.goRight();
	    			return;
	    		} else {
	    			if(hasToGoUp)
	    				creature.goUp();
	    			else
	    				creature.goDown();
	    			return;
	    		}
    		}
    		
    		if(hasToGoLeft) {
    			if(creature.tile(creature.x - 1, creature.y).isGround()) {
	    			creature.goLeft();
	    			return;
	    		} else {
	    			if(hasToGoUp)
	    				creature.goUp();
	    			else
	    				creature.goDown();
	    			return;
	    		}
    		}
    	}
    }
}
