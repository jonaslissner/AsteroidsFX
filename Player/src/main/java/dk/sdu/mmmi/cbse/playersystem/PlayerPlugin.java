package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
public class PlayerPlugin implements IGamePluginService {

    private Entity player;

    public PlayerPlugin() {
    }

    @Override
    public void start(GameData gameData, World world) {
        world.addEntity(createPlayerShip(gameData));
    }

    public Entity createPlayerShip(GameData gameData) {
        player = new Player();
        player.setPolygonCoordinates(-5,-5,10,0,-5,5);
        player.setX(gameData.getDisplayHeight()/2);
        player.setY(gameData.getDisplayWidth()/2);
        return player;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(player);
    }

}
