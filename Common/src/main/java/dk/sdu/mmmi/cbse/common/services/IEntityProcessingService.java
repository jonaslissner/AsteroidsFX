package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for processing entities in the game world.
 */
public interface IEntityProcessingService {

    /**
     * Processes entities in the game world based on the provided game data.
     * Implementing classes should define the logic for updating entity states
     * and performing actions on entities.
     *
     * @param gameData The current game data containing information about the game state.
     * @param world    The game world containing entities to be processed.
     */
    void process(GameData gameData, World world);
}
