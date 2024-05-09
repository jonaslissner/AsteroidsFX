package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

public class AsteroidSplitterImplTests{
    private World world;
    private Asteroid asteroid;
    private AsteroidSplitterImpl asteroidSplitter;

    @BeforeEach
    public void testSetup() {
        this.world = new World();
        this.asteroid = new Asteroid();
        this.asteroidSplitter = new AsteroidSplitterImpl();

        world.addEntity(asteroid);
    }

    @Test
    public void shouldSplitAsteroid() {
        // Arrange
        int initialCount = world.getEntities(Asteroid.class).size();

        // Act
        asteroidSplitter.splitAsteroid(asteroid, world);

        // Assert
        assertEquals(initialCount + asteroidSplitter.getSplitSize(), world.getEntities(Asteroid.class).size(), "Should split asteroid into 3 new asteroids.");
    }

    @Test
    public void newAsteroidsShouldHaveCorrectProperties() {
        // Arrange
        asteroid.setRadius(20);
        asteroid.setX(100);
        asteroid.setY(100);
        asteroid.setRotation(180);

        // Act
        asteroidSplitter.splitAsteroid(asteroid, world);

        // Assert
        for (Entity entity : world.getEntities(Asteroid.class)) {
            Asteroid newAsteroid = (Asteroid) entity;
            if (newAsteroid != asteroid) { // Skip original asteroid
                assertEquals(10, newAsteroid.getRadius(), "New asteroids should have half the radius of the original.");
                assertEquals(100, newAsteroid.getX(), "New asteroids should have the same X coordinate as the original.");
                assertEquals(100, newAsteroid.getY(), "New asteroids should have the same Y coordinate as the original.");
            }
        }
    }
}