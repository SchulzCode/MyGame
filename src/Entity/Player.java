package Entity;

import GameEngine.GamePanel;
import GameEngine.KeyHandler;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

        GamePanel gamePanel;
        KeyHandler keyH;
        public Player(GamePanel gamePanel, KeyHandler keyH){

            this.gamePanel = gamePanel;
            this.keyH = keyH;
            setDefaultValues();
            getPlayerImage();
            direction = "down";
        }
        public void setDefaultValues(){
            x = 384;
            y = 260;
            speed = 4;
        }

        public void getPlayerImage(){

            try{

                up1= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png")));
                up2= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png")));
                down1= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png")));
                down2= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png")));
                right1= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png")));
                right2= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png")));
                left1= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png")));
                left2= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png")));
                x1= ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("player/xd.png")));


            }catch(IOException e){
                e.printStackTrace();
            }

        }

        public void update(){

            if(keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){

            if (keyH.upPressed){
                direction = "up";
                y -= speed;
            }
            else if (keyH.downPressed){
                direction = "down";
                y += speed;
            }
            else if (keyH.leftPressed){
                direction = "left";
                x -= speed;
            }
            else if (keyH.rightPressed){
                direction = "right";
                x += speed;
            }

            else if (keyH.escapePressed) {
                System.exit(0);
            }

            spriteCounter++;
            if(spriteCounter > 13){
                if(spriteNum == 1){
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter =0;
            }
        }}
        public void draw(Graphics2D g2){
            BufferedImage bufferedImage = null;

            switch (direction) {
                case "up" -> {
                    if (spriteNum == 1) {
                        bufferedImage = up1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = up2;
                    }
                }
                case "down" -> {
                    if (spriteNum == 1) {
                        bufferedImage = down1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = down2;
                    }
                }
                case "left" -> {
                    if (spriteNum == 1) {
                        bufferedImage = left1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = left2;
                    }
                }
                case "right" -> {
                    if (spriteNum == 1) {
                        bufferedImage = right1;
                    }
                    if (spriteNum == 2) {
                        bufferedImage = right2;
                    }
                }
            }
            g2.drawImage(bufferedImage,x,y, gamePanel.tileSize,gamePanel.tileSize,null);
        }
    }