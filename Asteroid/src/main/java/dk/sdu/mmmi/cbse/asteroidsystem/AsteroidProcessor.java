package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidProcessor implements IEntityProcessingService {

    private AsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();
    private Random rand = new Random();

    @Override
    public void process(GameData gameData, World world) {
        if(world.getEntities(Asteroid.class).size() < 10) {
            world.addEntity(createAsteroid(gameData));
        }

        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
            double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

            asteroid.setX(asteroid.getX() + changeX * 0.5);
            asteroid.setY(asteroid.getY() + changeY * 0.5);

            if (asteroid.getX() < 0) {
                asteroid.setX(asteroid.getX() + gameData.getDisplayWidth());
            }

            if (asteroid.getX() > gameData.getDisplayWidth()) {
                asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
            }

            if (asteroid.getY() < 0) {
                asteroid.setY(asteroid.getY() + gameData.getDisplayHeight());
            }

            if (asteroid.getY() > gameData.getDisplayHeight()) {
                asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
            }
            if(asteroid.getIsHit()) {
                if(asteroid.getRadius() > 8) {
                    asteroidSplitter.splitAsteroid(asteroid, world);
                } else {
                    gameData.incrementScore();
                }
                world.removeEntity(asteroid);
            }
        }

    }
    public Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        int size = this.rand.nextInt(20) + 7;
        asteroid.setRadius(size);
        asteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
        asteroid.setX(this.rand.nextInt(gameData.getDisplayWidth()));
        asteroid.setY(this.rand.nextInt(gameData.getDisplayHeight()));
        asteroid.setRotation(rand.nextInt(360));

        return asteroid;
    }

    /**
     * Dependency Injection using OSGi Declarative Services
     */
    public void setAsteroidSplitter(AsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = asteroidSplitter;
    }

    public void removeAsteroidSplitter(AsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = null;
    }
}
