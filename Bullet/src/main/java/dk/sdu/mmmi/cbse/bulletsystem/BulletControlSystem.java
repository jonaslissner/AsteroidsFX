package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            double changeX = Math.cos(Math.toRadians(bullet.getRotation()) + 2);
            double changeY = Math.sin(Math.toRadians(bullet.getRotation()) + 2);
            bullet.setX(bullet.getX() + changeX);
            bullet.setY(bullet.getY() + changeY);
            System.out.println(bullet.getX() + " " + bullet.getY());
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity newBullet = new Bullet();
        setShape(newBullet);
        newBullet.setY(shooter.getY());
        newBullet.setX(shooter.getX());
        newBullet.setRotation(shooter.getRotation());
        return newBullet;
    }

    private void setShape(Entity entity) {
        entity.setPolygonCoordinates(-5,-5,10,0,-5,5);
    }

}
