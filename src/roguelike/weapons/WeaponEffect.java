package roguelike.weapons;

import roguelike.entities.Weapon;

public class WeaponEffect {
	private Weapon weapon;
	
	protected int damageMin;
	public int damageMin() { return damageMin(); }
	protected int damageMax;
	public int damageMax() { return damageMax(); }
	protected int critChance;
	public int critChance() { return critChance(); }
	
	public WeaponEffect(Weapon weapon) {
		this.weapon = weapon;
		this.weapon.setWeaponEffect(this);
	}
}
