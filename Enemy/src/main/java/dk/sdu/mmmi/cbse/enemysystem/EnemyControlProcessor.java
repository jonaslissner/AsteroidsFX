package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;
import java.util.Random;

public class EnemyControlProcessor implements IEntityProcessingService {
    private static final int MAX_ENEMIES = 2;
    private static final int SPAWN_CHANCE = 1;
    private static final int RANDOM_DIRECTION_THRESHOLD = 25;
    private static final int STOP_TURNING_THRESHOLD = 25;
    private static final int SHOOT_THRESHOLD = 150;
    private static final Random random = new Random();

    @Override
    public void process(GameData gameData, World world) {
        if (world.getEntities(Enemy.class).size() < MAX_ENEMIES && random.nextInt(100) >= SPAWN_CHANCE) {
            world.addEntity(createEnemyShip(gameData));
        }

        for (Entity e : world.getEntities(Enemy.class)) {
            Enemy enemy = (Enemy) e;

            moveEnemy(enemy);
            shootIfPossible(enemy, world, gameData);
            adjustRotation(enemy);
            ensureEnemyBounds(enemy, gameData);

        }
    }

    private Entity createEnemyShip(GameData gameData) {

        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-7,-7,14,0,-7,7);
        enemyShip.setRotation(Math.random()*360);
        enemyShip.setRadius(14);
        enemyShip.setX(Math.random() * gameData.getDisplayHeight());
        enemyShip.setY(Math.random() * gameData.getDisplayWidth());
        return enemyShip;
    }

    private void moveEnemy(Enemy enemy) {
        double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
        double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
        enemy.setX(enemy.getX() + changeX);
        enemy.setY(enemy.getY() + changeY);
    }

    private void shootIfPossible(Enemy enemy, World world, GameData gameData) {
        if (random.nextInt(SHOOT_THRESHOLD) == 0) {
            getBulletSPIs().stream().findFirst().ifPresent(
                    bulletSPI -> world.addEntity(bulletSPI.createBullet(enemy, gameData)));
        }
    }

    private void adjustRotation(Enemy enemy) {
        if (enemy.isTurningLeft() || enemy.isTurningRight()) {
            if (random.nextInt(STOP_TURNING_THRESHOLD) == 0) {
                if (enemy.isTurningLeft()) {
                    enemy.setTurningLeft(false);
                } else {
                    enemy.setTurningRight(false);
                }
            } else {
                int rotationIncrement = enemy.isTurningLeft() ? -2 : 2;
                enemy.setRotation(enemy.getRotation() + rotationIncrement);
            }
        } else {
            switch (random.nextInt(RANDOM_DIRECTION_THRESHOLD)) {
                case 0:
                    enemy.setTurningLeft(true);
                    break;
                case 1:
                    enemy.setTurningRight(true);
                    break;
            }
        }
    }

    private void ensureEnemyBounds(Enemy enemy, GameData gameData) {
        if (enemy.getX() < 0) {
            enemy.setX(enemy.getX() + gameData.getDisplayWidth());
        }

        if (enemy.getX() > gameData.getDisplayWidth()) {
            enemy.setX(enemy.getX() % gameData.getDisplayWidth());
        }

        if (enemy.getY() < 0) {
            enemy.setY(enemy.getY() + gameData.getDisplayHeight());
        }

        if (enemy.getY() > gameData.getDisplayHeight()) {
            enemy.setY(enemy.getY() % gameData.getDisplayHeight());
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
