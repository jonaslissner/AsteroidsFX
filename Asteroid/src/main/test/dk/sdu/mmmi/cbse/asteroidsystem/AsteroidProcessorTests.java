package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AsteroidProcessorTests {

    private GameData gameData;
    private World world;
    private Asteroid asteroid;
    private AsteroidProcessor asteroidProcessor;

    @BeforeEach
    public void testSetup() {
        this.gameData = new GameData();
        this.world = new World();
        this.asteroid = new Asteroid();
        this.asteroidProcessor = new AsteroidProcessor();
        world.addEntity(asteroid);
    }

    @Test
    public void testAsteroidMovement() {
        // Given
        double enemyPosX = asteroid.getX();
        double enemyPosY = asteroid.getY();

        // When
        asteroidProcessor.process(gameData, world);

        // Then
        boolean asteroidMoved = (enemyPosX != asteroid.getX()) || (enemyPosY != asteroid.getY());
        Assertions.assertTrue(asteroidMoved);
    }

    @Test
    public void testAsteroidsSpawn() {
        // Given
        int amountOfAsteroids = world.getEntities(Asteroid.class).size();

        // When
        world.addEntity(asteroidProcessor.createAsteroid(gameData));
        asteroidProcessor.process(gameData, world);

        // Then
        boolean asteroidsSpawned = amountOfAsteroids < world.getEntities(Asteroid.class).size();
        Assertions.assertTrue(asteroidsSpawned);
    }

    @Test
    public void testAsteroidCanSplit() {
        // Given
        int amountOfAsteroids = world.getEntities(Asteroid.class).size();
        this.asteroid.setRadius(10);
        System.out.println(amountOfAsteroids);

        // When
        this.asteroid.setIsHit(true);
        asteroidProcessor.process(gameData, world);
        System.out.println(world.getEntities(Asteroid.class).size());

        // Then
        Assertions.assertTrue(amountOfAsteroids + 3 == world.getEntities(Asteroid.class).size());
    }

    /**
     * Dependency Injection using OSGi Declarative Services
     */
}
