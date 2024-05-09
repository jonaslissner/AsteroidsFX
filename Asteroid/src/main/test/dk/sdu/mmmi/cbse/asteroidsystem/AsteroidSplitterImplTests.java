package dk.sdu.mmmi.cbse.asteroidsystem;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.AsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.Random;

/**
 *
 * @author corfixen
 */
public class AsteroidSplitterImplTests implements AsteroidSplitter {
    private static int SPLIT_SIZE = 3;

    @Override
    public void splitAsteroid(Entity e, World w) {
        System.out.println("Splitting asteroid");
        Random rand = new Random();

        for (int i = 0; i < SPLIT_SIZE; i++) {
            Asteroid newAsteroid = new Asteroid();
            int size = (int) e.getRadius()/2;
            newAsteroid.setRadius(size);
            newAsteroid.setPolygonCoordinates(size, -size, -size, -size, -size, size, size, size);
            newAsteroid.setX(e.getX());
            newAsteroid.setY(e.getY());

            double newRotation = e.getRotation() + (rand.nextInt(121) - 60);
            System.out.println("New rotation: " + newRotation);
            newAsteroid.setRotation(newRotation);
            w.addEntity(newAsteroid);
        }
    }
}