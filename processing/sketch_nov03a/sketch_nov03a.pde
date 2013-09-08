Stroke _stroke;
ArrayList _strokes;
Boolean mousePressed = false;
Edge _edge;
DebugView _debugView;
PGraphics g;
PGraphics b;

void setup() {
  size(500, 500);
  stroke(0);
  smooth();
  fill(0);
  g = createGraphics(width, height, P2D);
  b = createGraphics(width, height, P2D);
  _strokes = new ArrayList();
  _edge = new Edge();
  _debugView = new DebugView();
}


void draw() {
  background(255);

  drawEdge();
  drawBuffer();
  image(g, 0, 0);
  image(b, 0, 0);
  _debugView.draw();
}

void drawBuffer() {

  b.beginDraw();
  drawStroke(b);
  b.endDraw();
}

void drawEdge() {
  noFill();
  int x = width - 81;
  int y = 20;
  rect(x, y, 60, 60);
  println("pG :" + g);
  PGraphics pG = g;
  _edge.draw(x + 30, y + 30, 2.5);
}

void drawStroke(PGraphics pG) {
  if (_stroke == null)
    return;

  ArrayList p = _stroke.getPoints();
  int l = p.size();
  PVector pV;
  for (int i = 0 ;i < l; i ++) {
    pV = (PVector) p.get(i); 
    _edge.draw((int)pV.x, (int)pV.y, pG);
  }
}


void drawStrokes() {
  int l = _strokes.size();
  for (int i = 0 ; i < l; i ++) {
    Stroke s = (Stroke)_strokes.get(i);
    ArrayList p = s.getPoints();
    int pL = p.size();
    PVector pV = new PVector();
    for (int j = 0; j < pL; j ++) {
      pV = (PVector) p.get(j);
      _edge.draw((int)pV.x, (int)pV.y, g);
    }
  }
}


void mousePressed() {
  if (_stroke != null)
    return;

  _stroke = new Stroke(mouseX, mouseY);
}

void mouseDragged() {
  _stroke.handleMove(mouseX, mouseY);
}

void mouseReleased() {
  b.background(255, 255, 255, 0);
  b.stroke(0);
  b.fill(0);
  g.beginDraw();
  drawStroke(g);
  g.endDraw();

  _strokes.add(_stroke);
  _stroke = null;

  _edge.doFracture();
}

