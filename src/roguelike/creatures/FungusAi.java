package roguelike.creatures;

import roguelike.entities.Creature;

public class FungusAi extends CreatureAi {
    private CreatureFactory factory;
    private int spreadcount;
 
    public FungusAi(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }

    public void onUpdate(){
    	double spreadChance;
    	double spreadChanceWithPlayer = 0.01;
    	double spreadChanceWithoutPlayer = 0.0001;
    	
    	if(creature.sameDepthAsPlayer())
    		spreadChance = spreadChanceWithPlayer;
    	else
    		spreadChance = spreadChanceWithoutPlayer;
    	
        if (spreadcount < 5 && Math.random() < spreadChance)
            spread();
    }
 
    private void spread(){
        int x = creature.x + (int)(Math.random() * 7) - 3;
        int y = creature.y + (int)(Math.random() * 7) - 3;
        int z = creature.z;
  
        if (!creature.canEnter(x, y, z))
            return;
  
        Creature child = factory.newFungusYoung(z);
        child.x = x;
        child.y = y;
        spreadcount++;
        
        creature.doAction("spawn a child");
    }
}