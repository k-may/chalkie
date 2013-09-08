Point getLineIntersection(Line a, Line b) {
  float det = a.A*b.B - b.A*a.B;
  
  if (det == 0) {
    //Lines are parallel
    return null;
  }

  float x = (b.B*a.C - a.B*b.C)/det;
  float y = (a.A*b.C - b.A*a.C)/det;
  return new Point(x, y);
}

ArrayList getLineIntersections(Line[] lines) {
  ArrayList arr = new ArrayList();
  int n = 0;
  do {
    for (int i = n; i < lines.length; i ++) {
      Point a = getLineIntersection(lines[n], lines[i]);

      if (a != null)
        arr.add(a);
    }
    n ++;
  }
  while (n < lines.length);
  
  return arr;
}

ArrayList getCircleLineIntersections(Line[] lines, Circle cir) {
  ArrayList arr = new ArrayList();
  int n = 0;
  do {
    for (int i = n;i < lines.length; i ++) {
      ArrayList a = getCircleLineIntersection(lines[n]._a, lines[n]._b, cir._a, 250);
      if (a != null) {
        arr.addAll(a);
      }
    }
    n ++;
  }
  while (n < lines.length);
  return arr;
}
// Code adapted from Paul Bourke:
// http://local.wasp.uwa.edu.au/~pbourke/geometry/sphereline/raysphere.c
ArrayList getCircleLineIntersection(Point A, Point B, Point C, int rad) {
  //boolean circleLineIntersect(float x1, float y1, float x2, float y2, float cx, float cy, float cr ) {
  float dx = B.x - A.x;
  float dy = B.y - A.y;
  float a = dx * dx + dy * dy;
  float b = 2 * (dx * (A.x - C.x) + dy * (A.y - C.y));
  float c = C.x * C.x + C.y * C.y;
  c += A.x * A.x + A.y * A.y;
  c -= 2 * (C.x * A.x + C.y * A.y);
  c -= rad * rad;
  float bb4ac = b * b - 4 * a * c;
  //println(bb4ac);

  if (bb4ac < 0)  // Not intersecting
    return null;
  //
  float mu1 = (-b + sqrt( b*b - 4*a*c )) / (2*a);
  float ix1 = A.x + mu1*(dx);
  float iy1 = A.y + mu1*(dy);
  float mu2 = (-b - sqrt(b*b - 4*a*c )) / (2*a);
  float ix2 = A.x + mu2*(dx);
  float iy2 = A.y + mu2*(dy);
  //
  //  //  if ((mu1 > 1 && mu2 < 0) || (mu1 < 0 && mu2 > 1))
  //  //    return null;
  //
  Point p1 = new Point(ix1, iy1);
  Point p2 = new Point(ix2, iy2);
  ArrayList arr = new ArrayList();
  arr.add(p1);
  arr.add(p2);
  return arr;
  //    float testX;
  //    float testY;
  //    // Figure out which point is closer to the circle
  //    if (dist(A.x, A.y, C.x, C.y) < dist(B.x, B.y, C.x, C.y)) {
  //      testX = B.x;
  //      testY = B.y;
  //    } 
  //    else {
  //      testX = A.x;
  //      testY = A.y;
  //    }
  //
  //    if (dist(testX, testY, ix1, iy1) < dist(A.x, A.y, B.x, B.y) || dist(testX, testY, ix2, iy2) < dist(A.x, A.y, B.x, B.y)) {
  //      return arr;
  //    } 
  //    else {
  //      return null;
  //    }
  // return new ArrayList();
}

//ArrayList getCircleLineIntersectionPoint(Point A, Point B, Point center, float radius) {
//  ArrayList arr = new ArrayList();
//  
//  float baX = B.x - A.x;
//  float baY = B.y - A.y;
//  float caX = center.x - A.x;
//  float caY = center.y - A.y;
//
//  float a = baX * baX + baY * baY;
//  float bBy2 = baX * caX + baY * caY;
//  float c = caX * caX + caY * caY - radius * radius;
//
//  float pBy2 = bBy2 / a;
//  float q = c / a;
//
//  float disc = pBy2 * pBy2 - q;
//  if (disc < 0) {
//    println("null!");
//    return null;
//  }
//  // if disc == 0 ... dealt with later
//  float tmpSqrt = sqrt(disc);
//  float abScalingFactor1 = -pBy2 + tmpSqrt;
//  float abScalingFactor2 = -pBy2 - tmpSqrt;
//
//  arr.add(new Point(A.x - baX * abScalingFactor1, A.y - baY * abScalingFactor1));
//  if (disc == 0) { // abScalingFactor1 == abScalingFactor2
//    return arr;
//  }
//  arr.add(new Point(A.x - baX * abScalingFactor2, A.y - baY * abScalingFactor2));
//  
//  return arr;
//}

