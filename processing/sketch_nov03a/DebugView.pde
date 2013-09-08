class DebugView {

  int maxMemory, totalMemory, freeMemory, percentage;
  int MEGABYTE = 1024*1024;
  PFont font;
  
  DebugView() {
    font = loadFont("ISOCP-36.vlw");
    textFont(font);
  }

  void draw() {
    fill(0);
    maxMemory = int(Runtime.getRuntime().maxMemory()/MEGABYTE);
    totalMemory = int(Runtime.getRuntime().totalMemory()/MEGABYTE);
    freeMemory = int(Runtime.getRuntime().freeMemory()/MEGABYTE);
    percentage = int((float)totalMemory/maxMemory*100);
    text(totalMemory + " / ", 10, 50);
    text(freeMemory + " / ", 70, 50);
    text(percentage + "%", 120, 50);
  }
}

