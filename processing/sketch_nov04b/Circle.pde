class Circle {

  public Point a;
  public int r;

  Circle() {
  }

  Circle(int x, int y, int r) {
    this(new Point(x, y), r);
  }
  Circle(Point a, int r) {
    _a = a;
    _r = r;
  } 

  void draw() {
    ellipse(_a.x, _a.y, _r, _r);
  }
  
  int getRadius(){
   return _r/2; 
  }
  
}

