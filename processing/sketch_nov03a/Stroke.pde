class Stroke {

  PVector _downPos;
  int _dY;
  int _dX;
  ArrayList _points;

  Stroke(int x, int y) {
    _downPos = new PVector(x, y);
    _points = new ArrayList();
    handleMove(x, y);
  }

  void handleMove(int x, int y) {

    if (y < 0) y = 0;
    if (x < 0) x = 0;

    _dX = x - (int)_downPos.x;
    _dY = y - (int)_downPos.y;

    if (abs(_dY) > abs(_dX)) {
      renderVerticalBias(x, y);
    } 
    else {
      renderHorizontalBias(x, y);
    }
    _downPos = new PVector(x, y);
  }

  void renderVerticalBias(int x, int y) {
    if (_dY != 0) {
      println("render y : " + x + " : " + y);
      int Y;
      int X;
      float m = ((float) _dX / (float) _dY);
      int dir =  _dY /abs(_dY);
      int ceil = (int) floor(abs(_dY));
      for (int i = 0; i < ceil; i++) {
        Y =(int)( i * dir + _downPos.y);
        X = (int)( (i * dir * m) + _downPos.x);
        //_bitmap.setPixel(X, Y, Color.RED);
        _points.add(new PVector(X, Y));
      }
    }
  }

  private void renderHorizontalBias(int x, int y) {
    // if (_dX != 0) {

    println("render x : " + x + " : " + y);
    int Y;
    int X;
    float m = ((float) _dY / (float) _dX);
    if (_dX == 0) {
      _points.add(new PVector(x, y));
    }
    else {
      int dir = _dX / abs(_dX);
      for (int i = 0; i < abs(_dX); i++) {
        X =(int)( i * dir + _downPos.x);
        Y = (int) ((i * dir * m) + _downPos.y);
        // _bitmap.setPixel(X, Y, Color.BLUE);
        _points.add(new PVector(X, Y));
      }
    }
  }

  ArrayList getPoints() {
    return _points;
  }
}

