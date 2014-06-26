package com.kevmayo.chalkie.base.stroke;

import com.kevmayo.chalkie.android.processing.PAndroidGraphics;
import com.kevmayo.chalkie.base.math.Point;
import com.kevmayo.chalkie.interfaces.Graphics;
import com.kevmayo.chalkie.interfaces.IEdge;
import com.kevmayo.chalkie.view.MainView;

import java.util.List;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;
import processing.opengl.PShader;

/**
 * Created by Kev on 26/06/2014.
 */
public class PStrokeRenderer extends StrokeRendererBase<PGraphics> {
    PShader shader;
    public PStrokeRenderer(PGraphics graphics) {
        super(graphics, "PStrokeRenderer");

        shader = MainView.instance.loadShader("frag.glsl");
        //shader.set("resolution", (float)(_rect.width()), (float)(_rect.height()));
    }

    @Override
    public void start() {

    }

    @Override
    public void dispose() {

    }

    @Override
    public void draw(Graphics g) {
        if (_invalidated) {
            _invalidated = false;

            if(_currentIndex > 1) {
                PGraphics mainBuffer = ((PAndroidGraphics) g).g;
                drawStroke(mainBuffer, null, _stroke.getPoints());
            }

            _currentIndex = _stroke.getPoints().size() - 1;

            if (_stroke.isClosed()) {
                strokePool.free((StrokeVertex) _stroke);
            }
        }
        // g.drawBuffer(graphics);
    }

    @Override
    public void drawStroke(PGraphics buffer, IEdge<Point> edge, List<StrokePoint> points) {

        PVector direction = ((StrokeVertex) _stroke).direction;
        PVector d1 = new PVector(-direction.y, direction.x);
        PVector d2 = d1.get();

        d1.mult(5);
        d2.mult(-5);

        PVector p1, p2, p3, p4;

        shader.set("time", MainView.TIME_ELAPSED/1000);

        //buffer.background(0xffffffff);
        //buffer.noStroke();
        //buffer.fill(0xff000000);
        buffer.beginShape(PApplet.TRIANGLE_STRIP);
        buffer.shader(shader);

        int size = points.size();
       // log("current : " + _currentIndex +  " : " + size);

        for (int i = 0; i < size - 1; i++) {

            StrokePoint a = points.get(i);
            StrokePoint b = points.get(i + 1);

            buffer.vertex(a.x + d1.x, a.y + d1.y);
            buffer.vertex(a.x + d2.x, a.y + d2.y);
            buffer.vertex(b.x + d1.x, b.y + d1.y);
            buffer.vertex(b.x + d2.x, b.y + d2.y);


        }

        buffer.endShape(PConstants.CLOSE);

    }
}
