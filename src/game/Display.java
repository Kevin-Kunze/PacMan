package game;

import javax.swing.*;
import java.awt.*;

/**
 * used to render game
 */
public class Display extends JPanel {
    private final Game game;

    public Display(Game game) {
        super();
        this.game = game;
        game.add(this);
    }

    /**
     * render game
     * @param g  the <code>Graphics</code> context in which to paint
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        game.render(g2d);
    }

    @Override
    public String toString() {
        return "Display{}";
    }
}
