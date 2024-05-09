package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AsteroidPluginTests{

    private AsteroidPlugin asteroidPlugin;
    private GameData gameData;
    private World world;

    @BeforeEach
    public void testSetup() {
        this.gameData = new GameData();
        this.world = new World();
        this.asteroidPlugin = new AsteroidPlugin();
    }

    @Test
    public void shouldHaveNoAsteroidsOnStart() {
        // Arrange
        int expectedAsteroidsCount = 0;

        // Act
        asteroidPlugin.start(gameData, world);
        int actualAsteroidsCount = world.getEntities(Asteroid.class).size();

        // Assert
        assertEquals(expectedAsteroidsCount, actualAsteroidsCount, "Asteroids should not be present after start.");
    }

    @Test
    public void shouldRemoveAllAsteroidsOnStop() {
        // Arrange
        world.addEntity(new Asteroid());
        world.addEntity(new Asteroid());
        int asteroidsCountBeforeStop = world.getEntities(Asteroid.class).size();

        // Act
        asteroidPlugin.stop(gameData, world);
        int asteroidsCountAfterStop = world.getEntities(Asteroid.class).size();

        // Assert
        assertEquals(2, asteroidsCountBeforeStop, "There should be two asteroids before stop is called.");
        assertEquals(0, asteroidsCountAfterStop, "All asteroids should be removed on stop.");
    }

}
