package roguelike.weapons;

import roguelike.entities.Item;
import roguelike.entities.Weapon;

public class FistsEffect extends WeaponEffect {
	Item item;
	
	public FistsEffect(Weapon weapon){
		super(weapon);
		
		damageMin 	= 2;
		damageMax 	= 5;
		critChance 	= 0;
	}
}
