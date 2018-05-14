import java.awt.*;

public class Paddle {

    private int x;
    private int y;
    private int dx;
    private int width = 60;
    private int height = 20;

    public Paddle(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Paddle(int x, int y, int width, int height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.dx = 2;
    }

    public void move()
    {
        if( x+dx <=  500 - width && x + dx >= 0)
            x += dx;
        else
            dx *= -1;
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
    }

    public void toLeft()
    {
        if(dx > 0)
            dx*=-1;
    }

    public void toRight()
    {
        if(dx < 0)
            dx*= -1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDx() {
        return dx;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

}
