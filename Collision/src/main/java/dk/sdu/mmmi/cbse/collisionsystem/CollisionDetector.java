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
        // TODO handle no asteroid collision
        if (e1 instanceof Asteroid && e2 instanceof Bullet || e1 instanceof Bullet && e2 instanceof Asteroid) {
            handleBulletAsteroidCollision(e1, e2, world);
        } else if (e1 instanceof Bullet || e2 instanceof Bullet) {
            handleBulletShipCollision(e1, e2, world);
        } else {
            world.removeEntity(e1);
            world.removeEntity(e2);
        }
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
            System.out.println(ship.getClass().getSimpleName() + " was hit!");
        } else {
            world.removeEntity(ship);
        }
        world.removeEntity(e1 instanceof Bullet ? e1 : e2);
    }
}
