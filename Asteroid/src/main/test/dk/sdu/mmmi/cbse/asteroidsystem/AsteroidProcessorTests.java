package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class AsteroidProcessorTests {

    private GameData gameData;
    private World world;
    private Asteroid asteroid;
    private AsteroidProcessor asteroidProcessor;
    private int asteroidsSpawnLimit;
    private int asteroidSplitMinSize;

    @BeforeEach
    public void testSetup() {
        this.gameData = new GameData();
        this.world = new World();
        this.asteroid = new Asteroid();
        this.asteroidProcessor = new AsteroidProcessor();
        this.asteroidsSpawnLimit = asteroidProcessor.getAsteroidsSpawnLimit();
        this.asteroidSplitMinSize = asteroidProcessor.getAsteroidSplitMinSize();
        world.addEntity(asteroid);
    }

    @Test
    public void shouldMoveAsteroids() {
        double initialPosX = asteroid.getX();
        double initialPosY = asteroid.getY();
        double expectedPosX = (initialPosX + Math.cos(Math.toRadians(asteroid.getRotation())) * 0.5) % gameData.getDisplayWidth();
        double expectedPosY = (initialPosY + Math.sin(Math.toRadians(asteroid.getRotation())) * 0.5) % gameData.getDisplayHeight();

        asteroidProcessor.process(gameData, world);

        assertTrue(asteroid.getX() == expectedPosX && asteroid.getY() == expectedPosY, "Asteroid should move according to its rotation.");
    }

    @Test
    public void shouldSpawnNewAsteroidIfUnderLimit() {
        // Arrange
        for (int i = 0; i < asteroidsSpawnLimit - 1; i++) {
            world.addEntity(new Asteroid());
        }
        int initialCount = world.getEntities(Asteroid.class).size();

        // Act
        asteroidProcessor.process(gameData, world);

        // Assert
        assertEquals(initialCount + 1, world.getEntities(Asteroid.class).size(), "Should spawn one new asteroid when under limit.");
    }

    @Test
    public void shouldNotSpawnNewAsteroidIfAtLimit() {
        // Arrange
        for (int i = 0; i < asteroidsSpawnLimit; i++) {
            world.addEntity(new Asteroid());
        }
        int initialCount = world.getEntities(Asteroid.class).size();

        // Act
        asteroidProcessor.process(gameData, world);

        // Assert
        assertEquals(initialCount, world.getEntities(Asteroid.class).size(), "Should not spawn new asteroids when at limit.");
    }

    @Test
    public void shouldSplitLargeAsteroidsIfHit() {
        // Arrange
        int amountOfAsteroids = world.getEntities(Asteroid.class).size();
        this.asteroid.setRadius(asteroidSplitMinSize);

        // Act
        this.asteroid.setIsHit(true);
        asteroidProcessor.process(gameData, world); // one new is also created as total amount of asteroids is below 10

        // Assert
        assertEquals(amountOfAsteroids + 3, world.getEntities(Asteroid.class).size(), "Should split large asteroids when hit.");
    }

    @Test
    public void shouldRemoveSmallAsteroidsIfHit() {
        // Arrange
        int amountOfAsteroids = world.getEntities(Asteroid.class).size();
        this.asteroid.setRadius(asteroidSplitMinSize - 1);

        // Act
        this.asteroid.setIsHit(true);
        asteroidProcessor.process(gameData, world); // one new is also created as total amount of asteroids is below 10
        System.out.println(world.getEntities(Asteroid.class).size());

        // Assert
        assertEquals(amountOfAsteroids, world.getEntities(Asteroid.class).size(), "Should remove small asteroids when hit.");
    }

    /**
     * Dependency Injection using OSGi Declarative Services
     */
}
