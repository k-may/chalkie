class Line {
  Point _a;
  Point _b;
  float A;
  float B;
  float C;

  Line() {
  }
  Line(Point a, Point b) {
    _a = a;
    _b = b;

    A = _b.y - _a.y;
    B = _a.x - _b.x;
    C = A*_a.x+B*_a.y;
  }

  void draw() {
    line(_a.x, _a.y, _b.x, _b.y);
  }
  
  Point a(){
  return _a;
  }
  
  Point b(){
   return _b; 
  }
  
  String toString(){
   return _a.toString() + " / " + _b.toString(); 
  }
}

