package roguelike.creatures;

import roguelike.entities.Creature;

public class SkeletonAi extends CreatureAi {
	public SkeletonAi(Creature creature) {
        super(creature);
    }

    public void onUpdate(){
        wander();
    }
    
    public void wander() {
    	wanderTowardsPlayer();
    }
}
