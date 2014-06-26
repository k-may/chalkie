package com.kevmayo.chalkie.view;

import com.kevmayo.chalkie.base.DisplayObject;
import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.events.ChalkieEvent;
import com.kevmayo.chalkie.events.EventType;
import com.kevmayo.chalkie.interfaces.Graphics;

import easing.Expo;

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
    Point[] startPoints;
    Point[] destPoints;

    int paddingLeft;
    int paddingRight;
    int paddingTop;
    int barWidth;
    int barLength;

    private float _ratio = 0.f;
    private boolean _invalidated;

    private boolean _isAnimatingOut;
    private int _startTime;
    private int _duration = 2000;
    private boolean _isAnimatingIn;

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

        startPoints = new Point[]{c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12};
        destPoints = new Point[]{d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12};

        if (points == null)
            points = new Point[startPoints.length];

        for (int i = 0; i < startPoints.length; i++)
            points[i] = new Point(startPoints[i].x, startPoints[i].y);

        _invalidated = true;
    }


    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.fill(0xff000000);
        g.moveTo(tL.x, tL.y);
        g.lineTo(tR.x, tR.y);
        g.lineTo(bR.x, bR.y);
        g.lineTo(bL.x, bL.y);
        g.lineTo(tL.x, tL.y);
        // p.close();

        for (int i = 0; i < points.length; i++) {
            if (i == 0)
                g.moveTo(points[i].x, points[i].y);
            else
                g.lineTo(points[i].x, points[i].y);
        }

        g.close();

    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (_isAnimatingOut || _isAnimatingIn) {
            _invalidated = true;

            float elapsed = MainView.TIME_ELAPSED - _startTime;

            float r = Expo.easeIn(elapsed, 0.f, 1.f, _duration);
            r = Math.min(1.f, Math.max(0.f, r));

            if (r < 1.f)
                set_ratio(r);
            else
                aniComplete();
        }
        //update animation
        if (_invalidated) {
            if (_isAnimatingOut) {
                for (int i = 0; i < points.length; i++) {
                    points[i].lerpTo(destPoints[i], _ratio);
                }
            } else if (_isAnimatingIn) {
                for (int i = 0; i < points.length; i++) {
                    points[i].lerpTo(startPoints[i], _ratio);
                }
            }
            _invalidated = false;
        }
    }

    public void aniComplete() {
        if (_isAnimatingOut)
            new ChalkieEvent(EventType.LAUNCH_BUTTON_PRESSED, "launchPressed").dispatch();

        set_ratio(_isAnimatingIn ? 0.f : 1.f);

        _isAnimatingOut = _isAnimatingIn = false;
    }

    public void resize() {
        barWidth = (int) (_rect.width() * .2);
        barLength = (int) (_rect.width() * .35);
        paddingLeft = (_rect.width() - barWidth) / 2;
        paddingRight = _rect.width() - paddingLeft;
        paddingTop = (int) ((_rect.height() * .4) - barWidth * .5);
        initPoints();
        _invalidated = true;
    }

    public void set_ratio(float value) {
        _ratio = value;
        _invalidated = true;
    }

    public void start(int time) {
        if (!_isAnimatingOut) {
            _startTime = time;
            _isAnimatingIn = false;
            _isAnimatingOut = true;
        }
    }

    public void reset() {
        _startTime = (int) MainView.TIME_ELAPSED;
        _isAnimatingOut = false;
        _isAnimatingIn = true;
    }
}
