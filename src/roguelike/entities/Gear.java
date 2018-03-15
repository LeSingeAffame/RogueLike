package roguelike.entities;

import java.awt.Color;

import roguelike.world.World;

public class Gear extends Item {
	public Gear(World world, String name, char glyph, Color color){
    	super(world, name, glyph, color);
    }
}
