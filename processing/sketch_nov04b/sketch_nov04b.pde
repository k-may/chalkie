Point _c;
Line[] _lines;
Circle _cir;

void setup() {
  size(500, 500);
  _lines = new Line[4];
  for (int i = 0; i < _lines.length; i ++) {
    _lines[i] = new Line(randomPoint(), randomPoint());
  }

  _cir = new Circle(width/2, height/2, 500);
}

void drawElements() {
  background(255);
  noFill();
  drawCircle();
  drawLines();
  drawIntersections(getIntersections());
}

void drawIntersections(ArrayList a){
 for(int i = 0; i < a.size(); i ++){
   Point p = (Point) a.get(i);
  ellipse(p.x, p.y, 10, 10);
 }
}

void drawCircle() { 
  _cir.draw();
}

void drawLines() {
  for (int i = 0 ;i < _lines.length; i ++) {
    _lines[i].draw();
  }
}

ArrayList getIntersections() {
  ArrayList intSec = new ArrayList();
  intSec.addAll(getLineIntersections(_lines));
  //remove intersections outside circle
  for(int i = 0 ;i < intSec.size(); i ++){
   Point p = (Point) intSec.get(i);
   float d = dist(p.x, p.y, _cir._a.x, _cir._a.y);
   println(d + " : " + _cir._r);
    if(d > _cir._r)
    intSec.remove(i);
  }
   intSec.addAll(getCircleLineIntersections(_lines, _cir));
  return intSec;
}



void draw() {
}

void mousePressed() {
  addLine();
  generateShape();
  drawElements();
}  

void addLine() {
  //pop last line
  Line[] temp = new Line[_lines.length];
  for (int i = 1; i < _lines.length; i ++) {
    temp[i - 1] = _lines[i] ;
  }

  Line newLine = new Line(randomPoint(), randomPoint());
  temp[_lines.length - 1] = newLine;

  _lines = temp;
}

void generateShape() {
}

Point randomPoint() {
  return new Point(random(-250, 250) + 250, random(-250, 250) + 255);
}

