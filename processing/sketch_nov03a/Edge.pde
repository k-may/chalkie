class Edge {

  int maxPoints = 4;
  PVector[] _points;
  int _width = 20;
  int mX = _width/2;
  int mY = _width/2;

  Edge() {
    _points = new PVector[maxPoints];
    doFracture();
  } 

  void doFracture() {
    float x, y;
    PVector[] _randPoints = new PVector[maxPoints]; 
    int p = 0;
    float ix = _width;
//    for (int i = 0; i < maxPoints; i ++) {
//      x = random(-_width/2, _width/2);
//      y = random(-_width/2, _width/2);
//      mX += x;
//      mY += y;
//
//     
//        if (ix > x){
//         ix = x;
//          p = i;
//        }
//
//       
//      
//      _randPoints[i] = new PVector(x, y);
//       println("p : " + _randPoints[i]);
//    }

_randPoints[0] = new PVector(-6, 0);
_randPoints[1] = new PVector(0, - 1);
_randPoints[2] = new PVector(-4, -8);
_randPoints[3] = new PVector(8, -1);

//    mX /= maxPoints;
//    mY /= maxPoints;
int start = 0;
   // int start = p;
    println("p --> " + p);
    do {
      int n = -1;
      for (int i = 0; i<maxPoints; i++) {

        //Don't go back to the same point you came from
        if (i == p)continue;

        //If there is no N yet, set it to i
        if (n == -1)n = i;

        PVector a = _randPoints[i];
        PVector c = _randPoints[p];
        PVector b = _randPoints[n];

        a.sub(c);
        b.sub(c);
        int cross = (int) a.cross(b).z;
        // int cross = (_randPoints[i] - _randPoints[p]) x (_randPoints[n] - _randPoints[p]);
println(p  + "/" + n + "/" + i + " : " + a + " : " + b + " -> " + cross);
        if (cross < 0) {
          //As described above, set N=X
          n = i;
        }
      }
      println("p --> " + n);
      p = n;
    }
    while (start!=p);
  }

  void draw(int x, int y, PGraphics g) {
    draw(x, y, 1.0, g);
  }

  void draw(int x, int y, float _scale, PGraphics g) {
    g.fill(0);
    g.beginShape();
    for (int i = 0 ;i < maxPoints; i ++) {
      g.vertex(_points[i].x*_scale + x, _points[i].y*_scale + y);
    } 
    g.endShape();
  }

  void draw(int x, int y, float _scale) {
    fill(255, 103, 30);
    stroke(215, 120, 0);
    beginShape();
    for (int i = 0 ;i < maxPoints; i ++) {
      for (int j = i + 1; j < maxPoints; j ++) {
        vertex(_points[i].x*_scale + x, _points[i].y*_scale + y);
        vertex(_points[j].x*_scale + x, _points[j].y*_scale + y);
      }
    }
    endShape();
  }
  //  void draw(int x, int y) {
  //    draw(x, y, 1.0);
  //  }
}

