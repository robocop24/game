package com.example.suhail.jetfighter;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by Suhail on 14-06-2016.
 */
public class PlayerSprite {
    private static  int BMP_ROW=1;
    private static int BMP_COLUMNS=2;

    private int x=0;
    private int y=0;

    private GamePlay game;
    private Bitmap bmp;

    private  int currentFrame=0;

    private int width;
    private int hieght;

    public static boolean moveLeft=false;
    public static boolean moveRight=false;

    private int xSpeed=3;

    public PlayerSprite(GamePlay game,Bitmap bmp){
        this.game=game;
        this.bmp=bmp;
        this.width=bmp.getWidth()/BMP_COLUMNS;
        this.hieght=bmp.getHeight()/BMP_ROW;
    }
    public void update(){
        if (moveRight){
            if (x<game.getWidth()-width){
                x=x+xSpeed;
            }
        }
        if (moveLeft){
            if (x>0){
                x=x-xSpeed;
            }

        }
        y=game.getHeight()-hieght;
        currentFrame=++currentFrame%BMP_COLUMNS;
    }
    public void onDraw(Canvas canvas){
        update();
        int srcX=currentFrame*width;
        int srcY=0;

        Rect src=new Rect(srcX,srcY,srcX*width,srcY*hieght);
        Rect dst=new Rect(x,y,x+width,y+hieght);

        canvas.drawBitmap(bmp,src,dst,null);
    }
}
