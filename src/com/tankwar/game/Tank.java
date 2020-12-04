package com.tankwar.game;

import com.tankwar.utilis.Constant;
import com.tankwar.utilis.MyUtil;

import javax.swing.*;
import java.awt.*;
import java.lang.invoke.SwitchPoint;
import java.util.ArrayList;
import java.util.List;


/**
 * 坦克相關class
 */
public class Tank {
    //坦克圖片定義
    private static Image[] p1_tankImg;
    private static Image[] p2_tankImg;
    //圖片初始化
    static { //p1
        p1_tankImg = new Image[4];
        p1_tankImg[0] = Toolkit.getDefaultToolkit().createImage("res/image/p1tankU.gif");
        p1_tankImg[1] = Toolkit.getDefaultToolkit().createImage("res/image/p1tankD.gif");
        p1_tankImg[2] = Toolkit.getDefaultToolkit().createImage("res/image/p1tankL.gif");
        p1_tankImg[3] = Toolkit.getDefaultToolkit().createImage("res/image/p1tankR.gif");
    }
    static { //p2
        p2_tankImg = new Image[4];
        p2_tankImg[0] = Toolkit.getDefaultToolkit().createImage("res/image/p2tankU.gif");
        p2_tankImg[1] = Toolkit.getDefaultToolkit().createImage("res/image/p2tankD.gif");
        p2_tankImg[2] = Toolkit.getDefaultToolkit().createImage("res/image/p2tankL.gif");
        p2_tankImg[3] = Toolkit.getDefaultToolkit().createImage("res/image/p2tankR.gif");
    }
    //四個方向
    public static final int DIR_UP = 0;
    public static final int DIR_DOWN = 1;
    public static final int DIR_LEFT = 2;
    public static final int DIR_RIGHT = 3;
    //坦克半徑
    public static final int RADIUS = 30;
    //默認速度
    public static final int DEFAULT_SPEED = 6;
    //坦克狀態
    public static final int STATE_STAND = 0;
    public static final int STATE_MOVE = 1;
    public static final int STATE_DIE = 2;
    //坦克初始血量
    public static final int DEFAULT_HP = 1000;


    //座標
    private int x,y;
    private int atk;
    private int hp = DEFAULT_HP;
    private int speed = DEFAULT_SPEED;
    private int dir;
    private int status = STATE_STAND;
    private Color color;


    //TODO 砲彈
    private List<Bullet> bullets = new ArrayList();

    public Tank(int x,int y,int dir){
        this.x = x;
        this.y = y;
        this.dir = dir;
        color = MyUtil.getRandomColor();
    }


    public void draw(Graphics g){
        /**
         * 每楨都要執行
         */
        logic();
        drawTank(g);
        drawBullets(g);
    }
    /**
     * 繪製坦克
     * @param g
     */
    private void drawTank(Graphics g){
//        g.drawImage(p1_tankImg[0],x-RADIUS,y-RADIUS,null );

        switch (dir){
            case DIR_UP:
                g.drawImage(p1_tankImg[0],x-RADIUS,y-RADIUS,null );
                break;
            case DIR_DOWN:
                g.drawImage(p1_tankImg[1],x-RADIUS,y-RADIUS,null );
                break;
            case DIR_LEFT:
                g.drawImage(p1_tankImg[2],x-RADIUS,y-RADIUS,null );
                break;
            case DIR_RIGHT:
                g.drawImage(p1_tankImg[3],x-RADIUS,y-RADIUS,null );
                break;
        }
    }

    //坦克邏輯處理
    private void logic(){
        switch (status){
            case STATE_STAND:
                break;
            case STATE_MOVE:
                move();
                break;
            case STATE_DIE:
                break;
        }
    }

    //坦克移動
    private void move(){
        switch (dir){
            case DIR_UP:
                y -= speed;
                if(y < RADIUS + GameFrame.titleBarH){
                    y = RADIUS + GameFrame.titleBarH;
                }
                break;
            case DIR_DOWN:
                y += speed;
                if(y > Constant.FRAME_HEIGHT - RADIUS*2){
                    y = Constant.FRAME_HEIGHT - RADIUS*2;
                }
                break;
            case DIR_LEFT:
                x -= speed;
                if(x < RADIUS){
                    x = RADIUS;
                }
                break;
            case DIR_RIGHT:
                x += speed;
                if(x > Constant.FRAME_WIDTH - RADIUS*2){
                    x = Constant.FRAME_WIDTH - RADIUS*2;
                }
                break;
        }
    }

    public static int getDirUp(){
        return DIR_UP;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getHp(){
        return hp;
    }
    public void setHp(int hp){
        this.hp = hp;
    }
    public int getAtk(){
        return atk;
    }
    public void setAtk(int atk){

    }
    public int getSpeed(){
        return speed;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List getBullets() {
        return bullets;
    }

    public void setBullets(List bullets) {
        this.bullets = bullets;
    }

    /**
     * 坦克開火
     */
    public void fire(){
        int bulletX = x;
        int bulletY = y;
        switch (dir){
            case DIR_UP:
                bulletY -= 1.5*RADIUS;
                break;
            case DIR_DOWN:
                bulletY += 1.5*RADIUS;
                break;
            case DIR_LEFT:
                bulletX -= 1.5*RADIUS;
                break;
            case DIR_RIGHT:
                bulletX += 1.5*RADIUS;
                break;
        }
        Bullet bullet =  new Bullet(bulletX,bulletY,dir,atk);
        bullets.add(bullet);
    }

    /**
     * 繪製坦克的子彈
     * @param g
     */
    private void drawBullets(Graphics g){
        for (Bullet bullet: bullets){
            bullet.draw(g);
        }
    }
}
