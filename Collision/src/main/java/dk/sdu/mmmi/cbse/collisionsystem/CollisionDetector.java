package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;

public class CollisionDetector implements IPostEntityProcessingService {
    public CollisionDetector() {}
    @Override
    public void process(GameData gameData, World world) {
        for (Entity e1 : world.getEntities()) {
            for (Entity e2 : world.getEntities()) {
                if (e1.getID().equals(e2.getID())) {
                    continue;
                }

                if (isColliding(e1, e2)) {
                    handleCollision(e1, e2, world);
                }
            }
        }
    }

    private boolean isColliding(Entity e1, Entity e2) {
        float dx = (float) e1.getX() - (float) e2.getX();
        float dy = (float) e1.getY() - (float) e2.getY();
        float distance = (float) Math.sqrt(dx * dx + dy * dy);
        return distance < e1.getRadius() + e2.getRadius();
    }

    private void handleCollision(Entity e1, Entity e2, World world) {
        if (e1 instanceof Asteroid && e2 instanceof Asteroid) {
            return; // Ignore asteroid-asteroid collisions
        }

        if (e1 instanceof Asteroid && e2 instanceof Bullet || e1 instanceof Bullet && e2 instanceof Asteroid) {
            handleBulletAsteroidCollision(e1, e2, world);
            return;
        }

        if (e1 instanceof Bullet || e2 instanceof Bullet) {
            handleBulletShipCollision(e1, e2, world);
            return;
        }

        handleShipAsteroidCollision(e1, e2, world);
        handleShipShipCollision(e1, e2, world);
    }
    private void handleBulletAsteroidCollision(Entity e1, Entity e2, World world) {
        Asteroid asteroid = e1 instanceof Asteroid ? (Asteroid) e1 : (Asteroid) e2;
        asteroid.setIsHit(true);
        world.removeEntity(e1 instanceof Bullet ? e1 : e2);
    }

    private void handleBulletShipCollision(Entity e1, Entity e2, World world) {
        Entity ship = e1 instanceof Bullet ? e2 : e1;
        if (!ship.getIsHit()) {
            ship.setIsHit(true);
        } else {
            world.removeEntity(ship);
        }
        world.removeEntity(e1 instanceof Bullet ? e1 : e2);
    }

    private void handleShipAsteroidCollision(Entity e1, Entity e2, World world) {
        Entity asteroid = e1 instanceof Asteroid ? e1 : e2;
        Entity ship = e1 instanceof Asteroid ? e2 : e1;

        asteroid.setIsHit(true);
        world.removeEntity(ship);
    }

    private void handleShipShipCollision(Entity e1, Entity e2, World world) {
        if((e1.getClass().getSimpleName().equals("Player") || e2.getClass().getSimpleName().equals("Enemy")) ||
                (e1.getClass().getSimpleName().equals("Enemy") || e2.getClass().getSimpleName().equals("Player"))) {
            world.removeEntity(e1);
            world.removeEntity(e2);
        }
    }
}
