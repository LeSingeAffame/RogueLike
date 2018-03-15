package roguelike.creatures;

import java.util.List;

import asciiPanel.AsciiPanel;

import roguelike.world.World;
import roguelike.entities.Creature;
import roguelike.vision.*;

public class CreatureFactory {
    private World world;

    public CreatureFactory(World world){
        this.world = world;
    }
    
    public Creature newPlayer(List<String> messages, FieldOfView fov){
        Creature player = new Creature(world, "player", '@', AsciiPanel.brightWhite,
        								100, 20, 5, 9,
        								5, 5, 5, 5, 5, 5, 5,
        								0);
        world.addAtEmptyLocation(player, 0);
        new PlayerAi(player, messages, fov);
        return player;
    }
    
    public Creature newFungus(int depth){
        Creature fungus = new Creature(world, "fungus", 'F', AsciiPanel.yellow,
        								25, 0, 0, 3,
        								5, 5, 7, 5, 5, 2, 5,
        								10);
        world.addAtEmptyLocation(fungus, depth);
        new FungusAi(fungus, this);
        return fungus;
    }
    
    public Creature newFungusYoung(int depth){
        Creature fungus = new Creature(world, "young fungus", 'f', AsciiPanel.yellow,
        								20, 2, 1, 3,
        								5, 5, 7, 5, 5, 2, 5,
        								10);
        world.addAtEmptyLocation(fungus, depth);
        new FungusYoungAi(fungus, this);
        return fungus;
    }
    
    public Creature newBat(int depth){
        Creature bat = new Creature(world, "small bat", 'b', AsciiPanel.red,
        							10, 5, 0, 5,
        							5, 7, 5, 5, 5, 8, 5,
        							20);
        world.addAtEmptyLocation(bat, depth);
        new BatAi(bat);
        return bat;
    }
    
    public Creature newSkeleton(int depth) {
    	Creature skeleton = new Creature(world, "skeleton", 's', AsciiPanel.red,
    										30, 7, 2, 6,
    										6, 5, 7, 5, 5, 2, 5,
    										30);
    	world.addAtEmptyLocation(skeleton, depth);
    	new SkeletonAi(skeleton);
    	return skeleton;
    }
}