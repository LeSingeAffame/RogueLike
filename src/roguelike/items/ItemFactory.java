package roguelike.items;

import asciiPanel.AsciiPanel;
import roguelike.entities.Item;
import roguelike.world.*;

public class ItemFactory {
    private World world;

    public ItemFactory(World world){
        this.world = world;
    }

    public Item newHealthPotion(int depth) {
    	Item healthPotion = new Item(world, "health potion", 'P', AsciiPanel.green);
    	world.addAtEmptyLocation(healthPotion, depth);
    	new HealthPotionEffect(healthPotion);
    	return healthPotion;
    }
}