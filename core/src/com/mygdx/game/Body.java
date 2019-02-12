package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Body extends Actor {

    public Snake snake = null;
    public Body body = null;

    public Game mainsnake;

    public Rectangle rectangleBody;
    Sprite sprite = new Sprite(new Texture(Gdx.files.internal("snakebody.png")));

    float ExpectedDistance = 12f;

    public Body() {

        rectangleBody = new Rectangle(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
    }

    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    int n = 1;

    @Override
    public void act(float delta) {
        super.act(delta);


        float x0, y0, x1, y1;
        double dist = ExpectedDistance;


        if (snake != null) {

            x0 = sprite.getX();
            x1 = snake.sprite.getX();

            y0 = sprite.getY();
            y1 = snake.sprite.getY();


            dist = Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
        }
        else {
            x0 = sprite.getX();
            x1 = body.sprite.getX();

            y0 = sprite.getY();
            y1 = body.sprite.getY();


            dist = Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
        }


        if (dist > ExpectedDistance) {

            double angle = (float) Math.toDegrees(Math.atan2(y0 - y1, x0 - x1));

            double usableAngle = Math.toRadians(angle);

            double nx = Math.cos(usableAngle) * ExpectedDistance;
            double ny = Math.sin(usableAngle) * ExpectedDistance;

            sprite.setPosition((float) nx + x1, (float) ny + y1);
            rectangleBody.setPosition(sprite.getX(),sprite.getY());
        }

    }
}

