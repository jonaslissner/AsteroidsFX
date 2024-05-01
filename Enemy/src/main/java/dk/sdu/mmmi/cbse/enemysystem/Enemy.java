package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

/**
 *
 * @author Emil
 */
public class Enemy extends Entity {
    private boolean isTurningLeft = false;
    private boolean isTurningRight = false;

    public void setTurningLeft(boolean isTurning){
        this.isTurningLeft = isTurning;
    }
    public void setTurningRight(boolean isTurning){
        this.isTurningRight = isTurning;
    }
    public boolean isTurningRight(){
        return isTurningRight;
    }
    public boolean isTurningLeft(){
        return isTurningLeft;
    }
}
