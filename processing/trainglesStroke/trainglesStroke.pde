PVector pos;
Boolean isDragging = false;
Boolean isDown = false;

int lineWidth = 10;
PVector[] positions;
int translateZ = 10;
PGraphics pg;
PShader shdr;
void setup() {
  size(500, 500, P2D);
  smooth();
  pg = createGraphics(500, 500, P2D);
  pg.noSmooth();
  pg.beginDraw();
  pg.background(0);
  pg.endDraw();
  shdr = loadShader("frag.glsl");
  shdr.set("resolution", float(pg.width), float(pg.height));  
  positions = new PVector[4];
}

void draw() {
  background(255 );
  if (isDragging) {
  } 
  translate(0, 0);//,2);
  if (isDown) {

    updateStroke();

    shdr.set("time", millis()/1000.0);

    //line(pos.x, pos.y, mouseX, mouseY);
    pg.beginDraw();
    pg.shader(shdr);
    pos = new PVector(mouseX, mouseY);
    pg.beginShape(TRIANGLE_STRIP);
    pg.vertex(positions[0].x, positions[0].y);//, -2);
    pg.vertex(positions[1].x, positions[1].y);//, -2);
    pg.vertex(positions[3].x, positions[3].y);//, 0);

    pg.vertex(positions[0].x, positions[0].y);//, -2);
    pg.vertex(positions[2].x, positions[2].y);//, 0);
    pg.vertex(positions[3].x, positions[3].y);//, 0);

    pg.endShape();
    pg.endDraw();


    /*
    
     line(positions[0].x, positions[0].y, positions[1].x, positions[1].y);
     line(positions[1].x, positions[1].y, positions[3].x, positions[3].y);
     
     line(positions[2].x, positions[2].y, positions[3].x, positions[3].y);
     line(positions[2].x, positions[2].y, positions[0].x, positions[0].y);
     
     line(positions[0].x, positions[0].y, positions[3].x, positions[3].y);
     */
  }
  image(pg, 0, 0, width, height);
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
