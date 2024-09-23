import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {

    static final int WIDTH = 600;
    static final int HEIGHT = 600;
    static final int UNIT_SIZE = 30;
    static final int GAME_UNITS = (WIDTH * HEIGHT) / UNIT_SIZE;
    static final int DELAY = 100;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int points;
    int pointPositionX;
    int pointPositionY;
    char direction = 'R';
    boolean is_running = false;
    Timer timer;
    Random random;

    Panel(){
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame() {
        newPoint();
        is_running = true;
        timer = new Timer(DELAY,this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        if(is_running) {
            for (int i = 0; i < HEIGHT / UNIT_SIZE; i++) {
                graphics.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT);
                graphics.drawLine(0, i * UNIT_SIZE, WIDTH, i * UNIT_SIZE);
            }
            graphics.setColor(Color.RED);
            graphics.fillOval(pointPositionX + 2, pointPositionY + 2, UNIT_SIZE - 4, UNIT_SIZE - 4);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    graphics.setColor(Color.GREEN);
                    graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    graphics.setColor(new Color(50, 180, 0));
                    graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            graphics.setColor(Color.RED);
            graphics.setFont(new Font("Arial",Font.BOLD,50));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: "+ points, (WIDTH - metrics.stringWidth("Score" + points))/2,graphics.getFont().getSize());
        }
        else {
            gameOver(graphics);
        }
    }

    public void newPoint() {
        pointPositionX = random.nextInt((int)(WIDTH/UNIT_SIZE))* UNIT_SIZE;
        pointPositionY = random.nextInt((int)(HEIGHT/UNIT_SIZE)) * UNIT_SIZE;

    }

    public void move() {
        for(int i=bodyParts; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
        }
    }

    public void checkPoint() {
        if((x[0] == pointPositionX) && (y[0] == pointPositionY)){
            bodyParts++;
            points++;
            newPoint();
        }
    }

    public void checkCollision() {
        // body colliding
        for(int i = bodyParts;i > 0;i--) {
            if((x[0] == x[i]) && (y[0] == y[i])){
                is_running = false;
            }
        }
        // wall colliding
        if(x[0] < 0) is_running = false;
        if(x[0] > WIDTH) is_running = false;
        if(y[0] < 0) is_running = false;
        if(y[0] > HEIGHT) is_running = false;
    }

    public void gameOver(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Arial",Font.BOLD,90));
        FontMetrics metrics1 = getFontMetrics(graphics.getFont());
        graphics.drawString("GAME OVER", (WIDTH - metrics1.stringWidth("GAME OVER"))/2,HEIGHT/2);
        FontMetrics metrics2 = getFontMetrics(graphics.getFont());
        graphics.setColor(Color.RED);
        graphics.setFont(new Font("Arial",Font.BOLD,60));
        graphics.drawString("Score: "+points, (WIDTH - metrics2.stringWidth("Score"+points))/2,HEIGHT/4);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(is_running) {
            move();
            checkPoint();
            checkCollision();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_LEFT, KeyEvent.VK_A:
                    if (direction != 'R') direction = 'L';
                    break;
                case KeyEvent.VK_RIGHT, KeyEvent.VK_D:
                    if (direction != 'L') direction = 'R';
                    break;
                case KeyEvent.VK_UP, KeyEvent.VK_W:
                    if (direction != 'D') direction = 'U';
                    break;
                case KeyEvent.VK_DOWN, KeyEvent.VK_S:
                    if (direction != 'U') direction = 'D';
                    break;
            }
        }
    }
}
