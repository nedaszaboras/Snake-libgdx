package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Food extends Actor {

    public Rectangle rectangleFood;

    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("pineapple.png")));

    int randomX = (int)(Math.random() * 500 + 1);
    int randomY = (int)(Math.random() * 350 + 1);

    public Food(){

        rectangleFood = new Rectangle(sprite.getX(), sprite.getY(),sprite.getWidth(),sprite.getHeight());

        setBounds(sprite.getX(), sprite.getY(),sprite.getWidth(),sprite.getHeight());
        sprite.setPosition(randomX,randomY);
        rectangleFood.setPosition(sprite.getX(),sprite.getY());

    }

    protected void positionChanged() {}


    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }


    @Override
    public void act(float delta) {}
}
