package roguelike.items;

import roguelike.entities.Creature;
import roguelike.entities.Item;

public class ItemEffect {
	private Item item;
	
	public ItemEffect(Item item) {
		this.item = item;
		this.item.setItemEffect(this);
	}
	
	public void onUsage(Creature c) {
		
	}
}
