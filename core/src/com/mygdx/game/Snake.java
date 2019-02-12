package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Snake  extends Actor {

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("snakebody.png")));
    float speed = 200;
    public Rectangle rectangleSnake;

    public Snake(){

        rectangleSnake = new Rectangle(sprite.getX(), sprite.getY(),sprite.getWidth(),sprite.getHeight());

        setBounds(sprite.getX(), sprite.getY(),sprite.getWidth(),sprite.getHeight());
        sprite.setPosition(Gdx.graphics.getWidth()/2f - sprite.getWidth()/2f, Gdx.graphics.getHeight()/2f - sprite.getHeight()/2f);

   }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        rectangleSnake.setPosition(sprite.getX(),sprite.getY());

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && sprite.getRotation() !=90){
            sprite.setRotation(270f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && sprite.getRotation() !=270){
            sprite.setRotation(90f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && sprite.getRotation() !=360){
            sprite.setRotation(180f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && sprite.getRotation() !=180){
            sprite.setRotation(360f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ALT_LEFT)){
            sprite.setRotation(0f);
        }


        if(sprite.getRotation() == 0f) {}
        if(sprite.getRotation() == 180f) {
            sprite.translateY(speed * delta);    //up
        }
        if(sprite.getRotation() == 360f) {               //down
            sprite.translateY(-1 * speed *delta);
        }
        if(sprite.getRotation() == 270f) {               //left
            sprite.translateX(-1 * speed *delta);
        }
        if(sprite.getRotation() == 90f) {               // right
            sprite.translateX(speed *delta);
        }
    }
}

