package roguelike.screens;

import java.awt.event.KeyEvent;

import asciiPanel.AsciiPanel;

import roguelike.creatures.*;
import roguelike.entities.Creature;

public class LevelUpScreen implements Screen {
	private Creature player;
	private PlayScreen playScreen;
	
	public LevelUpScreen(Creature player, PlayScreen playScreen) {
		this.player = player;
		this.playScreen = playScreen;
	}
	
	public void displayOutput(AsciiPanel terminal) {
		int screenBegin = 5;
		terminal.writeCenter("Level up! Choose an attribute to increase", screenBegin + 3);
		terminal.writeCenter("<S> Strength", screenBegin + 5);
		terminal.writeCenter("<P> Perception", screenBegin + 6);
		terminal.writeCenter("<E> Endurance", screenBegin + 7);
		terminal.writeCenter("<C> Charisma", screenBegin + 8);
		terminal.writeCenter("<I> Intelligence", screenBegin + 9);
		terminal.writeCenter("<A> Agility", screenBegin + 10);
        terminal.writeCenter("<L> Luck", screenBegin + 11);
        terminal.writeCenter("-- press <Key> to upgrade this attribute --", screenBegin + 22);
    }

    public Screen respondToUserInput(KeyEvent key) {
    	do {
	    	switch (key.getKeyCode()){
		    	/* Stat */
		    	case KeyEvent.VK_S: player.increaseStat('s', 		1); 	return this.playScreen;
		    	case KeyEvent.VK_P: player.increaseStat('p', 		1); 	return this.playScreen;
		    	case KeyEvent.VK_E: player.increaseStat('e', 		1); 	return this.playScreen;
		    	case KeyEvent.VK_C: player.increaseStat('c', 		1); 	return this.playScreen;
		    	case KeyEvent.VK_I: player.increaseStat('i', 		1); 	return this.playScreen;
		    	case KeyEvent.VK_A: player.increaseStat('a', 		1); 	return this.playScreen;
		        case KeyEvent.VK_L: player.increaseStat('l', 		1); 	return this.playScreen;
	    	}
    	} while(player.hasToLevelUp());
    	
    	return this;
    }
}
