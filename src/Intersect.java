import java.awt.Polygon;

public class Intersect {
  public boolean intersection(Polygon a, Polygon b) {
    int[] bx = b.xpoints;
    int[] by = b.ypoints;
    
    if (a.equals(b)) {
      return false;
    }
    
    for (int i = 0; i < a.npoints; i++) {
      for (int j = 0; i < b.npoints; i++) {
        if (a.contains(bx[i], by[i])) {
          return true;
        }
      }
    }
    
    return false;
  }
  
  public boolean clearIntersection(Polygon a, Polygon b) {
    int[] ax = a.xpoints;
    int[] ay = a.ypoints;
    int[] bx = b.xpoints;
    int[] by = b.ypoints;
    
    if (a.equals(b)) {
      return false;
    }
    
    for (int i = 0; i < a.npoints; i++) {
      for (int j = 0; i < b.npoints; i++) {
        if (a.contains(bx[i], by[i])) {
          return true;
        }
      }
    }
    for (int i = 0; i < b.npoints; i++) {
      for (int j = 0; i < a.npoints; i++) {
        if (b.contains(ax[i], ay[i])) {
          return true;
        }
      }
    }
    
    return false;
  }
}