package Fire_Agents;

public class Edge {

    private int startX = 0;
    private int startY = 0;
    private int endX = 0;
    private int endY = 0;

    public Edge(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public void setStartXY(int x, int y) {
        this.startX = x;
        this.startY = y;
    }

    public void setEndXY(int x, int y) {
        this.endX = x;
        this.endY = y;
    }
}
