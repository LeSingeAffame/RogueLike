package roguelike.screens;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import asciiPanel.AsciiPanel;

/* World */
import roguelike.world.*;
import roguelike.creatures.*;
import roguelike.entities.Creature;
import roguelike.vision.*;

import roguelike.ApplicationMain;

import roguelike.items.*;

import roguelike.sound.*;

import roguelike.weapons.WeaponFactory;

public class PlayScreen implements Screen {
	private World world;
	private Creature player;
    private int screenWidth;
    private int screenHeight;
    
    private FieldOfView fov;
    
    private List<String> messages;
    
    private MusicPlayer musicPlayer;

    public PlayScreen(MusicPlayer musicPlayer){
        screenWidth = getPanelWidth();
        screenHeight = getPanelHeight() - 3;
        messages = new ArrayList<String>();
        
        createWorld();
        
        fov = new FieldOfView(world);
        
        CreatureFactory creatureFactory = new CreatureFactory(world);
        createCreatures(creatureFactory);
        
        ItemFactory itemFactory = new ItemFactory(world);
        createItems(itemFactory);
        
        WeaponFactory weaponFactory = new WeaponFactory(world);
        createWeapons(weaponFactory);
        
        this.musicPlayer = musicPlayer;
        
        musicPlayer.stopMusic();
        musicPlayer.playMusic("A_Knight_s_Bard_-_Battle_With_Fey_Creatures");
    }
    
    public PlayScreen(){
    	this(new MusicPlayer());
    }
    
    public int getPanelWidth() {
    	return ApplicationMain.panelWidth;
    }
    
    public int getPanelHeight() {
    	return ApplicationMain.panelHeight;
    }
    
    private void createCreatures(CreatureFactory creatureFactory){
        player = creatureFactory.newPlayer(messages, fov);
        world.addPlayer(player);
      
        for(int z = 0; z < world.depth(); z++) {
	        for (int i = 0; i < 2; i++){
	            creatureFactory.newFungus(z);
	        }
	        
	        for (int i = 0; i < 4; i++){
	            creatureFactory.newFungusYoung(z);
	        }
	        
	        for (int i = 0; i < 10; i++){
	            creatureFactory.newBat(z);
	        }
	        
	        for(int i = 0; i < 3; i++) {
	        	creatureFactory.newSkeleton(z);
	        }
    	}
    }
    
    private void createItems(ItemFactory itemFactory) {
    	for(int z = 0; z < world.depth(); z++) {
	        for (int i = 0; i < 5; i++){
	        	itemFactory.newHealthPotion(z);
	        }
    	}
    }
    
    private void createWeapons(WeaponFactory weaponFactory) {
    	for(int z = 0; z < world.depth(); z++) {
    		for(int i = 0; i < 10; i++) {
    			weaponFactory.newFists(z);
    		}
    	}
    }
    
    private void createWorld(){
        world = new WorldBuilder(screenWidth + 10, screenHeight + 10, 5)
              .makeCaves()
              .build();
    }

    public void displayOutput(AsciiPanel terminal) {
    	int left = getScrollX();
        int top = getScrollY();
   
        // Tiles
        displayTiles(terminal, left, top);
        
        // Player
        terminal.write(player.glyph(), player.x - left, player.y - top, player.color());
        
        // Messages
        displayMessages(terminal, messages);
        
        String stats1 = String.format("Level: %3d  -- Experience: %3d/%3d", player.level(), player.experience(), player.experienceNeeded());
        String stats2 = String.format(" HP: %3d/%3d  --  Damage: %3d -%3d  --  Hit chance: %3d  --  Crit chance: %3d",
        		player.hp(), player.maxHp(), 1, player.attackValue()+1, player.hitChance(), player.critChance());
        terminal.write(stats1, 1, screenHeight + 1);
        terminal.write(stats2, 1, screenHeight + 2);
    }
    
    public int getScrollX() {
        return Math.max(0, Math.min(player.x - screenWidth / 2, world.width() - screenWidth));
    }
    
    public int getScrollY() {
        return Math.max(0, Math.min(player.y - screenHeight / 2, world.height() - screenHeight));
    }
    
    /* Display Methods */
    
    private void displayTiles(AsciiPanel terminal, int left, int top) {
        fov.update(player.x, player.y, player.z, player.visionRadius());
        
        for (int x = 0; x < screenWidth; x++){
            for (int y = 0; y < screenHeight; y++){
                int wx = x + left;
                int wy = y + top;

                if (player.canSee(wx, wy, player.z))
                    terminal.write(world.glyph(wx, wy, player.z), x, y, world.color(wx, wy, player.z));
                else
                    terminal.write(fov.tile(wx, wy, player.z).glyph(), x, y, Color.darkGray);
            }
        }
    }
    
    private void displayMessages(AsciiPanel terminal, List<String> messages) {
        int top = screenHeight - messages.size();
        for (int i = 0; i < messages.size(); i++){
            terminal.writeCenter(messages.get(i), top + i);
        }
        messages.clear();
    }
    
    public Screen respondToUserInput(KeyEvent key) {
        switch (key.getKeyCode()){
        	/* Movement */
	        case KeyEvent.VK_LEFT:
	        case KeyEvent.VK_Q: player.moveBy(-1, 0, 0); break;
	        case KeyEvent.VK_RIGHT:
	        case KeyEvent.VK_D: player.moveBy( 1, 0, 0); break;
	        case KeyEvent.VK_UP:
	        case KeyEvent.VK_Z: player.moveBy( 0,-1, 0); break;
	        case KeyEvent.VK_DOWN:
	        case KeyEvent.VK_S: player.moveBy( 0, 1, 0); break;
	        
	        /* Action */
	        case KeyEvent.VK_ENTER: world.tile(player.x, player.y, player.z).activate(player); break;
	        case KeyEvent.VK_I: return new InventoryScreen(player, this);
	        case KeyEvent.VK_E: return new EquipmentScreen(player, this);
	        case KeyEvent.VK_SPACE: System.out.println(player.stats()); break;
	        
	        /* Other */
	        case KeyEvent.VK_R: return new PlayScreen(musicPlayer);
	        
        }
        
        /*switch(key.getKeyChar()) {
        	case '<': player.moveBy( 0,  0, -1); break;
        	case '>': player.moveBy( 0,  0,  1); break;
        }*/
    
        world.update();
        
        if (player.hp() < 1)
            return new LoseScreen();
        
        if(player.hasToLevelUp()) {
        	player.levelUp();
        	return new LevelUpScreen(this.player, this);
        }
        
        return this;
    }
}