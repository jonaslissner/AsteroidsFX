package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AsteroidPluginTests{

    private AsteroidPlugin asteroidPlugin;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void setUpTest() {
        this.gameData = new GameData();
        this.world = new World();
        this.asteroidPlugin = new AsteroidPlugin();
    }

    @Test
    public void testNoAsteroidsOnStart() {
        // Given
        int amountOfAsteroidsOnStart = 0;

        // When
        asteroidPlugin.start(gameData, world);

        // Then
        assert (world.getEntities(Asteroid.class).size() == amountOfAsteroidsOnStart);
    }

    @Test
    public void testAsteroidsRemovedOnStop() {
        // Given
        asteroidPlugin.start(gameData, world);

        // When
        world.addEntity(new Asteroid());
        assertTrue(world.getEntities(Asteroid.class).size() > 0);
        asteroidPlugin.stop(gameData, world);

        // Then
        assert (world.getEntities(Asteroid.class).size() == 0);
    }

}
