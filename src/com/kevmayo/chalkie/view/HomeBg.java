package com.kevmayo.chalkie.view;

import android.graphics.Color;
import android.graphics.Path;

import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.interfaces.Graphics;

/**
 * Created by Kev on 19/06/2014.
 */
public class HomeBg extends DisplayObject {

    Point tL;
    Point tR;
    Point bL;
    Point bR;

    Point c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12;
    Point d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12;

    Point[] points;
    Point[] destPoints;

    int paddingLeft;
    int paddingRight;
    int paddingTop;
    int barWidth;
    int barLength;

    private float _ratio;
    private boolean _invalidated;

    public HomeBg() {
        super("homeBg");

        initPoints();

    }

    private void initPoints() {

        tL = new Point(0, 0);
        tR = new Point(_rect.width(), 0);
        bL = new Point(0, _rect.height());
        bR = new Point(_rect.width(), _rect.height());

        //clock wise from left-most point of cross
        c1 = new Point(paddingLeft - barLength, paddingTop);
        c2 = new Point(paddingLeft, paddingTop);
        c3 = new Point(paddingLeft, paddingTop - barLength);
        c4 = new Point(paddingRight, paddingTop - barLength);
        c5 = new Point(paddingRight, paddingTop);
        c6 = new Point(paddingRight + barLength, paddingTop);
        c7 = new Point(paddingRight + barLength, paddingTop + barWidth);
        c8 = new Point(paddingRight, paddingTop + barWidth);
        c9 = new Point(paddingRight, paddingTop + barWidth + barLength);
        c10 = new Point(paddingLeft, paddingTop + barWidth + barLength);
        c11 = new Point(paddingLeft, paddingTop + barWidth);
        c12 = new Point(paddingLeft - barLength, paddingTop + barWidth);

        //destination points
        d1 = new Point(0, paddingTop);
        d2 = new Point(0, 0);
        d3 = new Point(paddingLeft, 0);
        d4 = new Point(paddingRight, 0);
        d5 = new Point(_rect.width(), 0);
        d6 = new Point(_rect.width(), paddingTop);
        d7 = new Point(_rect.width(), paddingTop + barWidth);
        d8 = new Point(_rect.width(), _rect.height());
        d9 = new Point(paddingRight, _rect.height());
        d10 = new Point(paddingLeft, _rect.height());
        d11 = new Point(0, _rect.height());
        d12 = new Point(0, paddingTop + barWidth);

        points = new Point[]{c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12};
        destPoints = new Point[]{d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12};
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Path p = new Path();
        p.setFillType(Path.FillType.EVEN_ODD);
        p.moveTo(tL.x, tL.y);
        p.lineTo(tR.x, tR.y);
        p.lineTo(bR.x, bR.y);
        p.lineTo(bL.x, bL.y);
        p.close();

        for (int i = 0; i < points.length; i++) {
            if (i == 0)
                p.moveTo(points[i].x, points[i].y);
            else
                p.lineTo(points[i].x, points[i].y);
        }

        p.close();

        g.drawPath(p, Color.BLACK);
    }

    @Override
    public void update(float time) {
        super.update(time);

        //update animation
        if (_invalidated) {
            for (int i = 0; i < points.length; i++) {
                points[i].lerpTo(destPoints[i], _ratio);
            }
            _invalidated = false;
        }
    }

    public void resize() {
        barWidth = (int) (_rect.width() * .2);
        barLength = (int) (_rect.width() * .35);
        paddingLeft = (_rect.width() - barWidth) / 2;
        paddingRight = _rect.width() - paddingLeft;
        paddingTop = (int) ((_rect.height() * .4) - barWidth*.5);
        initPoints();
        _invalidated = true;
    }

    public void set_ratio(float value){
        _ratio = value;
        _invalidated = true;
    }

}
