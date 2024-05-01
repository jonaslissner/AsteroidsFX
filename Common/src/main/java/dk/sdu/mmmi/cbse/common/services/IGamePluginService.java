package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Interface for game plugins that initialize and finalize game functionality.
 */
public interface IGamePluginService {

    /**
     * Initializes the game plugin and starts any necessary processes or systems.
     * Implementing classes should perform initialization tasks such as
     * setting up game systems, loading resources, or registering event listeners.
     *
     * @param gameData The current game data containing information about the game state.
     * @param world    The game world containing entities and systems.
     */
    void start(GameData gameData, World world);

    /**
     * Finalizes the game plugin and stops any running processes or systems.
     * Implementing classes should perform finalization tasks such as
     * cleaning up resources, unregistering event listeners, or stopping systems.
     *
     * @param gameData The current game data containing information about the game state.
     * @param world    The game world containing entities and systems.
     */
    void stop(GameData gameData, World world);
}
