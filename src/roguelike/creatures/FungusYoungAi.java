package roguelike.creatures;

import roguelike.entities.Creature;

public class FungusYoungAi extends CreatureAi {
    private CreatureFactory factory;
    private int age;
 
    public FungusYoungAi(Creature creature, CreatureFactory factory) {
        super(creature);
        this.factory = factory;
    }

    public void onUpdate(){
        if (Math.random() < (0.10 * (int)(Math.log(age) / Math.log(20))))
            transform();
        age++;
    }
 
    private void transform(){
        Creature otherFungi = factory.newFungus(creature.z);
        creature.replaceBy(creature.x, creature.y, creature.z, otherFungi);
        
        creature.doAction("transforms");
    }
    
    /* AI overload */
    public void onAttacked(Creature other) {
    	creature.attack(other);
    }
}