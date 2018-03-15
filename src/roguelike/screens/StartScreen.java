package roguelike.screens;

import java.awt.event.KeyEvent;
import asciiPanel.AsciiPanel;
import roguelike.sound.*;


public class StartScreen implements Screen {
	private MusicPlayer musicPlayer;

    public void displayOutput(AsciiPanel terminal) {
    	musicPlayer = new MusicPlayer();
    	musicPlayer.playMusic("A_Knight_s_Bard_-_Majestic_Castle");
    	
        terminal.writeCenter("Welcome to my wonderful roguelike!", 1);
        
        int begin = 3;
        
        terminal.writeCenter("o    o", 					begin+1);
        terminal.writeCenter("oo    oo", 				begin+2);
        terminal.writeCenter("oo      oo", 				begin+3);
        terminal.writeCenter("oo  o   oo", 				begin+4);
        terminal.writeCenter("ooo oo o ooo", 			begin+5);
        terminal.writeCenter("ooo oooo  oo", 			begin+6);
        terminal.writeCenter("ooo  oooo  ooo", 			begin+7);
        terminal.writeCenter("ooo oo oo ooo", 			begin+8);
        terminal.writeCenter("ooo   o   o  ooo", 		begin+9);
        terminal.writeCenter("oooo      o oooo", 		begin+10);
        terminal.writeCenter("ooooo    ooo ooooo", 		begin+11);
        terminal.writeCenter("oooo   oooo   oooo", 		begin+12);
        terminal.writeCenter("ooooo   oo      oooo", 	begin+13);
        terminal.writeCenter("oooo  o ooo  o  oooo", 	begin+14);
        terminal.writeCenter("ooooo oo ooo  oo ooooo",	begin+15);
        terminal.writeCenter("ooooooooooooooooooo", 	begin+16);
        terminal.writeCenter("ooooooooooooooooo", 		begin+17);
        terminal.writeCenter("ooooooooooooooooo", 		begin+18);
        terminal.writeCenter("ooooooooooooooo", 		begin+19);
        terminal.writeCenter("ooooooooooooooo", 		begin+20);
        terminal.writeCenter("ooooooooooooo", 			begin+21);
        terminal.writeCenter("oooo oooooo oooo", 		begin+22);
        terminal.writeCenter("oo   ooo    oo", 			begin+23);
        terminal.writeCenter("ooo   oo   ooo", 			begin+24);
        terminal.writeCenter("ooo   ooo ooo", 			begin+25);
        terminal.writeCenter("ooo  ooo ooo", 			begin+26);
        terminal.writeCenter("oo  oo  oo", 				begin+27);
        terminal.writeCenter("oo oo   oo", 				begin+28);
        terminal.writeCenter("o oo   o", 				begin+29);
        terminal.writeCenter("o  oo   ", 				begin+30);
        terminal.writeCenter("oo ", 					begin+31);
        terminal.writeCenter("oo  ", 					begin+32);
        terminal.writeCenter("oo  o", 					begin+33);
        terminal.writeCenter("oo oo", 					begin+34);
        terminal.writeCenter("oooo", 					begin+35);
        terminal.writeCenter("oo", 						begin+36);
        terminal.writeCenter("o ", 						begin+37);
        
        terminal.writeCenter("-- press [enter] to start --", begin + 40);
    }

    public Screen respondToUserInput(KeyEvent key) {
        return key.getKeyCode() == KeyEvent.VK_ENTER ? startNewGame(musicPlayer) : this;
    }
    
    public Screen startNewGame(MusicPlayer musicPlayer) {
    	return new PlayScreen(musicPlayer);
    }
}