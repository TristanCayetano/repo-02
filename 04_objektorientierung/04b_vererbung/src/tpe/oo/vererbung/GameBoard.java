package tpe.oo.vererbung;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import de.smits_net.games.framework.board.Board;
import de.smits_net.games.framework.image.ImageBase;
import de.smits_net.games.framework.sprite.Sprite;


/**
 * Spielfeld.
 */
public class GameBoard  extends Board {
// TODO: Von Board ableiten

    /** Alien, das durch das Bild läuft. */
    private AlienSprite alien, alien2;

    /** Asteroid. */
    private Sprite asteroid;

    /** Hintergrundbild. */
    private Image background;


    /**
     * Erzeugt ein neues Board.
     */
    public GameBoard(){
       super(10, new Dimension(800, 600), Color.BLACK);

        background = ImageBase.loadImage("assets/background");
        asteroid = new Asteroid(this, new Point(40, 600));
        alien = new AlienSprite(this, new Point(10, 200));
        alien2 = new AlienSprite(this, new Point(10, 210));
    }

    /**
     * Hintergrund zeichnen.
     */
    protected void drawBackground(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }

    /**
     * Spielfeld neu zeichnen. Wird vom Framework aufgerufen.
     *
     * @param g Der Grafik-Kontext
     */
    public void drawGame(Graphics g) {
       
        asteroid.draw(g);
        alien.draw(g);
        alien2.draw(g);
      
    }

    /**
     * Spielsituation updaten. Wird vom Framework aufgerufen.
     *
     * @return Spiel läuft weiter, solange diese methode <code>true</code>
     *      zurück gibt
     */
    public boolean updateGame() {
        alien.move();
        asteroid.move();

       if (alien.intersects(asteroid) && alien.isActive()) {
            alien.explode();
            alien2.move();
       }

        return true;
    }
}
