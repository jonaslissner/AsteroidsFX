package dk.sdu.mmmi.cbse.common.astroid

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

public interface AsteroidSplitter {
    void splitAsteroid(Entity e, World w);
}
