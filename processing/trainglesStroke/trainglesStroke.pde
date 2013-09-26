PVector pos;
Boolean isDragging = false;
Boolean isDown = false;

int lineWidth = 10;
PVector[] positions;
int translateZ = 10;
void setup() {
  size(500, 500, P3D);
  smooth();
  positions = new PVector[4];
}

void draw() {
  if (isDragging) {
  } 
    translate(0,0,2);
  if (isDown) {
    
    updateStroke();
    

    
    //line(pos.x, pos.y, mouseX, mouseY);
    pos = new PVector(mouseX, mouseY);
    beginShape();
    vertex(positions[0].x, positions[0].y, -2);
    vertex(positions[1].x, positions[1].y, -2);
    vertex(positions[3].x, positions[3].y, 0);
    
    vertex(positions[0].x, positions[0].y, -2);
    vertex(positions[2].x, positions[2].y, 0);
    vertex(positions[3].x, positions[3].y, 0);
    
    endShape();
    
    /*
    
    line(positions[0].x, positions[0].y, positions[1].x, positions[1].y);
    line(positions[1].x, positions[1].y, positions[3].x, positions[3].y);
    
    line(positions[2].x, positions[2].y, positions[3].x, positions[3].y);
    line(positions[2].x, positions[2].y, positions[0].x, positions[0].y);
    
    line(positions[0].x, positions[0].y, positions[3].x, positions[3].y);
    */
  }
}
//
void startStroke() {
  isDown= true;
  pos = new PVector(mouseX, mouseY);
}

void updateStroke() {
  PVector d = new PVector(mouseX - pos.x, mouseY - pos.y);
  d.normalize();
  d = new PVector(-d.y, d.x);
  positions[0] = new PVector(pos.x + d.x * 5, pos.y + d.y * 5);
  positions[1] = new PVector(pos.x + d.x * -5, pos.y + d.y * -5);
  positions[2] = new PVector(mouseX + d.x * 5, mouseY + d.y * 5);
  positions[3] = new PVector(mouseX + d.x * -5, mouseY + d.y * -5);
}
void mouseDragged() {
  isDragging = true;
}

void mousePressed() {
  startStroke();
}

void mouseReleased() {
  isDown = false;
}

