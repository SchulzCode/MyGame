package GameEngine;

import Entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

// Diese Klasse ist für das Zeichen des Spielfensters da
public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;
    KeyHandler keyH = new KeyHandler();

    Player player = new Player(this,keyH);
    TileManager tileManager = new TileManager(this);

    int FPS = 60;

    int test =1234;


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Die Run Methode stellt den Internen Gameloop dar. Solange ein Gamethread existiert wird diese Methode ausgeführt.
    // Da wir die Berechnung mit 60 FPS ausführen und in Nanosekunden gerechnet wird, rendert die Methode circa alle 0,01667 Sekunden einen Frame -> 60 FPS
    // Das Spielfenster wird daher alle 0,001667 Sekunden geupdated
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;
        double delta = 0.0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime-lastTime) /drawInterval;
            lastTime = currentTime;

            if (delta >= 1){
                // Update Game information and Character Position
                update();
                repaint();
                delta--;
            }
        }
    }

    private void update() {
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();

    }
}
