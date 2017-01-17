package net.Andrewcpu.client.render.utils;

import java.awt.*;

/**
 * Created by stein on 1/13/2017.
 */
public class Action {
    private Point point;
    private int button;

    public Action(Point point, int button) {
        this.point = point;
        this.button = button;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public int getButton() {
        return button;
    }

    public void setButton(int button) {
        this.button = button;
    }
}
