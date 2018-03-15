package roguelike.weapons;

import roguelike.entities.Item;
import roguelike.entities.Weapon;

public class NoWeaponEffect extends WeaponEffect {
	Item item;
	
	public NoWeaponEffect(Weapon weapon){
		super(weapon);
		
		damageMin 	= 0;
		damageMax 	= 0;
		critChance 	= 0;
	}
}
