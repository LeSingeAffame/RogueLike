package roguelike.creatures;

import roguelike.entities.Creature;

public class BatAi extends CreatureAi {

    public BatAi(Creature creature) {
        super(creature);
    }

    public void onUpdate(){
        wander();
        wander();
    }
    
    public void wander() {
    	wanderTowardsPlayer();
    }
}