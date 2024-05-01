package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for post-entity processing services that perform additional
 * processing after entities have been processed.
 */
public interface IPostEntityProcessingService {

    /**
     * Performs additional processing on the game data or world
     * after entities have been processed.
     * Implementing classes should define additional logic to be
     * executed after entity processing is complete.
     *
     * @param gameData The current game data containing information about the game state.
     * @param world    The game world containing entities and systems.
     */
    void process(GameData gameData, World world);
}
