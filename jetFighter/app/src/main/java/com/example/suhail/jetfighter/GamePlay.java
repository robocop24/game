package com.example.suhail.jetfighter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Suhail on १२-०६-२०१६.
 */
public class GamePlay extends SurfaceView{

    private Bitmap player;
    private SurfaceHolder holder;
    private GameThread gameThread;
    private PlayerSprite myPlayer;
    private Bitmap background;
    private Bitmap bmpLeft;
    private Bitmap bmpRight;
    private boolean moveLeft;
    private boolean moveRight;

    public GamePlay(Context context){
        super(context);
        gameThread=new GameThread(this);
        holder=getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {

            @SuppressLint("WrongCall")
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameThread.setRunning(true);
                gameThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });


        player= BitmapFactory.decodeResource(getResources(),R.drawable.combine_images);
        myPlayer=new PlayerSprite(this,player);
        bmpLeft=BitmapFactory.decodeResource(getResources(),R.drawable.arrow_left);
        bmpRight=BitmapFactory.decodeResource(getResources(),R.drawable.arrow_right);
        background=BitmapFactory.decodeResource(getResources(),R.drawable.index3);
    }

    @Override
    protected void onDraw(Canvas canvas){
        int x=getWidth()-bmpRight.getWidth();
        int y=(getHeight()/2)-(bmpRight.getHeight()/2);
        canvas.drawBitmap(background,0,0,null);
        canvas.drawBitmap(bmpRight,x,y,null);
        canvas.drawBitmap(bmpLeft,0,y,null);
        myPlayer.onDraw(canvas);
    }
    protected void checkMovement(float x2,float y2) {
        int xRight = getWidth() - bmpRight.getWidth();
        int xLeft = 0;

        int y = (getHeight() / 2)-(bmpRight.getHeight()/2);

        int width=bmpRight.getWidth();
        int hight=bmpRight.getHeight();

        if (x2>xRight && x2<xRight+width && y2>y && y2<y+hight){
            moveRight=true;
            moveLeft=false;
        }
        else if (x2>xLeft && x2<xLeft+width && y2>y && y2<y+hight){
            moveLeft=true;
            moveRight=false;
        }
        else {
            moveLeft=false;
            moveRight=false;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){
         super.onTouchEvent(event);
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            checkMovement(event.getX(),event.getY());
            if (moveRight){
                PlayerSprite.moveRight=true;
                PlayerSprite.moveLeft=false;
            }
            if (moveLeft){
                PlayerSprite.moveLeft=true;
                PlayerSprite.moveRight=false;
            }
        }
        if (event.getAction()==MotionEvent.ACTION_UP){
            PlayerSprite.moveLeft=false;
            PlayerSprite.moveRight=false;
        }
        return  true;
    }
}
