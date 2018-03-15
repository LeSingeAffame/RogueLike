package roguelike.entities;

import java.awt.Color;

import roguelike.world.Point;
import roguelike.world.Tile;
import roguelike.world.World;

public class Entity {
	protected World world;

    public int x;
    public int y;
    public int z;
    
    protected String name;
    public String name() { return name; }
    
    protected boolean canBeAttacked = false;
    public boolean canBeAttacked() { return canBeAttacked; }
    
    protected boolean canBeUsed = false;
    public boolean canBeUsed() { return canBeUsed; }
    
    protected boolean canBeEquipped = false;
    public boolean canBeEquipped() { return canBeEquipped; }
    
    protected boolean isWeapon = false;
    public boolean isWeapon() { return isWeapon; }

    protected char glyph;
    public char glyph() { return glyph; }

    protected Color color;
    public Color color() { return color; }
    
    public Entity(World world, String name, char glyph, Color color) {
    	this.world = world;
    	this.name = name;
    	this.glyph = glyph;
    	this.color = color;
    }
    
    public Tile tile(int wx, int wy, int wz) {
        return world.tile(wx, wy, wz);
    }
    
    public Tile tile(int wx, int wy) {
        return world.tile(wx, wy, z);
    }
    
    public Point playerPosition() {
    	return world.playerPosition();
    }
    
    public boolean sameDepthAsPlayer() {
    	return world.playerPosition().z == this.z;
    }
    
    public void update(){
    }
    
    public void replaceBy(int x, int y, int z, Entity entity) {
    	world.replaceEntity(x, y, z, entity);
    }
    
    public void moveTo(int x, int y, int z) {
    }
    
    public void notify(String message, Object ... params){
    }
    
    public boolean canSee(int wx, int wy, int wz){
        return false;
    }
    
    public void remove() {
    	this.world.remove(this);
    }
    
    public void doAction(String message, Object ... params){
        int r = 9;
        for (int ox = -r; ox < r+1; ox++){
        	for (int oy = -r; oy < r+1; oy++){
        		if (ox*ox + oy*oy > r*r)
        			continue;
         
        		Entity other = world.entity(x+ox, y+oy, z);
         
	            if (other == null)
	            	continue;
	         
	            if (other == this)
	                 other.notify("You " + message + ".", params);
	            else if(other.canSee(x,  y,  z))
	                 other.notify(String.format("The '%s' %s.", name, makeSecondPerson(message)), params);
	         }
	    }
	}
    
    private String makeSecondPerson(String text){
        String[] words = text.split(" ");
        words[0] = words[0] + "s";
        
        StringBuilder builder = new StringBuilder();
        for (String word : words){
            builder.append(" ");
            builder.append(word);
        }
        
        return builder.toString().trim();
    }
}
