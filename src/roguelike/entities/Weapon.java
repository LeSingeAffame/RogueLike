package roguelike.entities;

import java.awt.Color;

import roguelike.weapons.WeaponEffect;
import roguelike.world.World;

public class Weapon extends Gear {
	private WeaponEffect weaponEffect;
	public void setWeaponEffect(WeaponEffect w) { weaponEffect = w; }
	
	public Weapon(World world, String name, char glyph, Color color){
    	super(world, name, glyph, color);
    	this.canBeEquipped = true;
    	this.isWeapon = true;
    }
	
	public void equip(Creature player, boolean rightHand) {
		if(rightHand) {
			if(player.rightHand() == null)
				player.changeRightHand(this);
		} else {
			if(player.leftHand() == null)
				player.changeLeftHand(this);
		}
	}
	
	public void use(Creature player) {
		equip(player);
	}
	
	public void equip(Creature player) {
		equip(player, true);
	}
}
