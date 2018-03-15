package roguelike.items;

import roguelike.entities.Creature;
import roguelike.entities.Item;

public class HealthPotionEffect extends ItemEffect {
	Item item;
	
	public HealthPotionEffect(Item item){
		super(item);
	}
	
	public void onUsage(Creature c) {
		c.heal(5);
	}
}
