package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    @Override
    public void start(GameData gameData, World world) {
        Entity asteroid = createAsteroid(gameData);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity e : world.getEntities(Asteroid.class)) {
            world.removeEntity(e);
        }
    }

}
