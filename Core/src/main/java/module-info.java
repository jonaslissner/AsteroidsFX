module Core {
    requires spring.core;
    requires spring.context;
    requires spring.beans;
    requires spring.aop;
    requires spring.expression;
    requires Common;
    requires CommonBullet;
    requires CommonAsteroid;
    requires javafx.graphics;
    exports dk.sdu.mmmi.cbse.main;
    opens dk.sdu.mmmi.cbse.main to spring.core;
    uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
    uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
    uses dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
}


