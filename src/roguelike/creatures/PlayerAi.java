package roguelike.creatures;

import java.util.List;

import roguelike.entities.Creature;
import roguelike.vision.FieldOfView;
import roguelike.world.Tile;

public class PlayerAi extends CreatureAi {
	private FieldOfView fov;

    private List<String> messages;

    public PlayerAi(Creature creature, List<String> messages, FieldOfView fov) {
        super(creature);
        this.messages = messages;
        isPlayer = true;
        this.fov = fov;
    }

    public void onNotify(String message){
        messages.add(message);
    }
    
    public boolean canSee(int wx, int wy, int wz) {
        return fov.isVisible(wx, wy, wz);
    }
    
    public void onEnter(int x, int y, int z, Tile tile){
        if (tile.isGround()){
            creature.x = x;
            creature.y = y;
            creature.z = z;
        }
    }
}