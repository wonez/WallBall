import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class WallBall extends Applet implements Runnable
{

    private Paddle paddle;

    private boolean end = false;
    private int level = 1;

    private Brick bricks7[] = new Brick[7];
    private Brick bricks5[] = new Brick[5];
    private Brick bricks3[] = new Brick[3];
    private Brick brick1;

    private Ball ball;
    private Image i;
    private Graphics doubleG;
    private int width = 60;
    private int height = 20;

    public void init() {

        setSize(500, 500);
        this.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {

                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(!end)
                        {
                            switch (e.getKeyCode())
                            {

                                case KeyEvent.VK_LEFT:
                                    paddle.toLeft();
                                    break;
                                case KeyEvent.VK_RIGHT:
                                    paddle.toRight();
                                    break;

                            }
                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
    }

    public void start() {

        sort();
        Thread th = new Thread(this);
        th.start();
    }

    public void run()
    {
        while(true) {
            //if collided with paddle change direction
            //  --------
            // |        |O  -> ...
            //  --------
            if((Math.abs(ball.getX() + Ball.radius - paddle.getX()) < 2 || Math.abs(ball.getX() - paddle.getX() - paddle.getWidth()) < 2)
                    && Math.abs(ball.getY() + Ball.radius - paddle.getY()) < 2 ) {
                ball.setX(ball.getX() - 2);
                ball.setDx(ball.getDx() * -1);
            }
            else if (ball.getX() + Ball.radius > paddle.getX() && ball.getX() < paddle.getX() + paddle.getWidth() &&
                    ball.getY() + Ball.radius > paddle.getY() )
            {
                ball.setY(ball.getY() - 2);
                ball.setDy(ball.getDy() * (-1));
            }
            //////////////////////////////////////////////

            for(Brick b : bricks7)
            {
                if (b.checkColision(ball)) {
                    ball.setDy(ball.getDy() * -1);
                    b.setX(1000);
                }
            }
            for(Brick b : bricks5)
            {
                if (b.checkColision(ball)) {
                    ball.setDy(ball.getDy() * -1);
                    b.setX(1000);
                }
            }
            for(Brick b : bricks3)
            {
                if (b.checkColision(ball)) {
                    ball.setDy(ball.getDy() * -1);
                    b.setX(1000);
                }
            }
            if (brick1.checkColision(ball)) {
                ball.setDy(ball.getDy() * -1);
                brick1.setX(1000);
            }
            ball.move();
            paddle.move();
            end = checkifEnd();
            if(end)
                ball.setX(1000);
            //new level
            if(checkifEndOfLevel())
            {
                level++;
                sort();
                ball.setDy(ball.getDy()+level);
                ball.setDx(level);
                paddle.setDx(Math.abs(paddle.getDx())+1);
            }
            //Keep refreshing 60fps
            repaint();
            try{
                Thread.sleep(17);

            }catch ( InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void stop()
    {
        //TO DO
    }

    public void destroy()
    {
        //TO DO
    }

    public void paint(Graphics g)
    {
        //background
        super.paint(g);
        g.setColor(Color.WHITE);
        g.fillRect(0,0, 500,500);
        if(!end) {
            g.setColor(new Color(250, 250, 250));
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
            g.drawString("" + level, 225, 250);
        } else {
            g.setColor(new Color(250,250,250));
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 100));
            g.drawString("The End", 35,250);
        }

        paddle.paint(g);
        ball.paint(g);
        for (int i = 0; i < 7; i++)
            bricks7[i].paint(g);
        for (int i = 0; i < 5; i++)
            bricks5[i].paint(g);
        for (int i = 0; i < 3; i++)
            bricks3[i].paint(g);
        brick1.paint(g);
    }

    //Double Buffering, have no idea what this means, repaint uses it
    @Override
    public void update(Graphics g) {
        if(i == null) {
            i = createImage(500, 500);
            doubleG = i.getGraphics();
        }

        doubleG.setColor(getBackground());
        doubleG.fillRect(0,0, 500, 500);

        doubleG.setColor(getForeground());
        paint(doubleG);

        g.drawImage(i, 0 ,0 , this);
    }

    public boolean checkifEndOfLevel(){
        for(int i = 0; i<7; i++)
        {
            if(bricks7[i].getX() != 1000)
                return false;
            if(i < 5){
                if(bricks5[i].getX() != 1000)
                    return false;
            }
            if(i < 3){
                if(bricks3[i].getX() != 1000)
                    return false;
            }
        }
        return brick1.getX() == 1000;
    }

    public boolean checkifEnd()
    {
        if(ball.getY() > 500)
            return true;
        return false;
    }

    public void sort()
    {
        paddle = new Paddle(500/2 - width, 500 - height, width*2, height);
        ball = new Ball(250, 250);
        for(int i=0; i<7; i++)
            bricks7[i] = new Brick(10 + i * (width + 10 ), 10, width ,height);

        for(int i=0; i<5; i++)
            bricks5[i] = new Brick(80 + i * (width + 10), 20 + height, width, height);

        for(int i=0; i<3; i++)
            bricks3[i] = new Brick(150 + i * (width + 10), 30 + 2*height, width, height);

        brick1 = new Brick(220,40 + 3*height, width, height);
    }
}
