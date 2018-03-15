package roguelike.world;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import roguelike.items.*;
import roguelike.sound.MusicPlayer;
import roguelike.entities.*;

public class World {
	private Tile[][][] tiles;
	
	private int width;
	public int width() { return width; }
	
	private int height;
	public int height() { return height; }

	private int depth;
	public int depth() { return depth; }
	
	private List<Entity> entities;
	
	private Creature player;
	
	private MusicPlayer musicPlayer;
	
	public World(Tile[][][] tiles){
		this.tiles = tiles;
		this.width = tiles.length;
		this.height = tiles[0].length;
		this.depth = tiles[0][0].length;
		
		this.entities = new ArrayList<Entity>();
		
		musicPlayer = new MusicPlayer();
	}
	
	public void addPlayer(Creature player) {
		this.player = player;
	}

	public Entity entity(int x, int y, int z){
		for (Entity e : entities){
			if (e.x == x && e.y == y && e.z == z)
				return e;
		}
		return null;
	}
	
	public Creature creature(int x, int y, int z){
		return (Creature)entity(x, y, z);
	}
	
	public Item item(int x, int y, int z){
		return (Item)entity(x, y, z);
	}
	
	public Tile tile(int x, int y, int z){
		if (x < 0 || x >= width || y < 0 || y >= height || z < 0 || z >= depth)
			return Tile.BOUNDS;
		else
			return tiles[x][y][z];
	}
	
	public char glyph(int x, int y, int z){
	    Entity entity = entity(x, y, z);
	    return entity != null ? entity.glyph() : tile(x, y, z).glyph();
	}
	
	public Color color(int x, int y, int z){
		Entity entity = entity(x, y, z);
	    return entity != null ? entity.color() : tile(x, y, z).color();
	}

	public void dig(int x, int y, int z) {
		if (tile(x, y, z).isDiggable())
			tiles[x][y][z] = Tile.FLOOR;
	}
	
	public void addAtEmptyLocation(Entity entity, int z){
		int x;
		int y;
		
		do {
			x = (int)(Math.random() * width);
			y = (int)(Math.random() * height);
		} 
		while (!tile(x,y,z).isGround() || entity(x,y,z) != null);
		
		entity.x = x;
		entity.y = y;
		entity.z = z;
		entities.add(entity);
	}
	
	public void update(){
		List<Entity> toUpdate = new ArrayList<Entity>(entities);
		for (Entity entity : toUpdate){
			entity.update();
		}
	}

	public void remove(Entity other) {
		entities.remove(other);
	}
	
	public void replaceEntity(int x, int y, int z, Entity other) {
		Entity e = entity(x, y, z);
		
		if(e != null)
			entities.remove(e);
		
		other.moveTo(x, y, z);
	}
	
	public void replaceCreature(int x, int y, int z, Creature other) {
		replaceEntity(x, y, z, other);
	}
	
	public Point playerPosition() {
    	return new Point(player.x, player.y, player.z);
    }
}