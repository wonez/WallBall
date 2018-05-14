import java.awt.*;

public class Brick extends Paddle {

    public Brick(int x, int y)
    {
        super(x,y);
    }

    public Brick(int x, int y, int width, int height)
    {
        super(x,y,width,height);
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(getX(),getY(),getWidth(),getHeight());
    }

    public boolean checkColision(Ball ball){

        if(ball.getDy() < 0) {
            if (ball.getX() + Ball.radius > getX() && ball.getX() < getX() + getWidth() && ball.getY() < getY() + getHeight() && ball.getY() > getY())
                return true;
        } else {
            if(ball.getX() + Ball.radius > getX() && ball.getX() < getX() + getWidth() &&
                    ball.getY() + Ball.radius  > getY() && ball.getY() < getY() + getHeight())
                return true;
        }

        return false;
    }

}
