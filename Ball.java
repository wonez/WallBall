import java.awt.*;

public class Ball {

    private int x;
    private int y;
    public static int radius = 20;
    private int dx = 3;
    private int dy = 2;

    public Ball(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void move()
    {
        if( x + dx < 500 - radius && x + dx > 0)
            x += dx;
        else
            dx *= -1;

        if( y + dy > 0 )
            y += dy;
        else
            dy *= -1;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, radius, radius);
    }


}
