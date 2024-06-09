package game.objects;

import java.awt.*;

public abstract class GameObject {
    /**
     * render object
     * @param g the Graphics context in which to render
     */
    public abstract void render(Graphics2D g);

    @Override
    public String  toString() {
        return "GameObject{}";
    }
}
