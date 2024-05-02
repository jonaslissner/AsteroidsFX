package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author Emil
 */
public class Enemy extends Entity {
    private boolean isTurningLeft = false;
    private boolean isTurningRight = false;

    public void setTurningLeft(boolean isTurningLeft){
        this.isTurningLeft = isTurningLeft;
    }
    public void setTurningRight(boolean isTurningRight){
        this.isTurningRight = isTurningRight;
    }
    public boolean isTurningRight(){
        return isTurningRight;
    }
    public boolean isTurningLeft(){
        return isTurningLeft;
    }
}
