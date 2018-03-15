package roguelike.weapons;

import asciiPanel.AsciiPanel;
import roguelike.entities.Weapon;
import roguelike.world.World;

public class WeaponFactory {
	private World world;

    public WeaponFactory(World world){
        this.world = world;
    }
    
    public Weapon noWeapon(int depth) {
    	Weapon noWeapon = new Weapon(world, "no weapon", 'X', AsciiPanel.blue);
    	world.addAtEmptyLocation(noWeapon, depth);
    	new NoWeaponEffect(noWeapon);
    	return noWeapon;
    }
    
    public Weapon newFists(int depth) {
    	Weapon fists = new Weapon(world, "fists", 'F', AsciiPanel.blue);
    	world.addAtEmptyLocation(fists, depth);
    	new FistsEffect(fists);
    	return fists;
    }
}
