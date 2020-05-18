package com.cxyxh.c02_frame;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建一个窗口
 */
public class TankFrame extends Frame {
    private Tank tank = new Tank(400, 600, Dir.UP, this, Group.GOOD);
    List<Bullet> bullet = new ArrayList<>();
    public static final int GAME_HIGHT = 800, GAME_WIDTH = 1200;
    List<Tank> tanks = new ArrayList<>();
//    Explodes explodes = new Explodes(200, 200, this);
    List<Explodes> explodesList = new ArrayList<>();

    public TankFrame() {
        //设置宽度
        setSize(GAME_WIDTH, GAME_HIGHT);
        //设置窗口不能变换大小
        setResizable(false);
        //设置标题
        setTitle("坦克大战");
        //设置图标
        String src = "/img/tank-icon.png";
        Image image = null;
        try {
            image = ImageIO.read(getClass().getResource(src));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setIconImage(image);
        //设置窗口事件
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //窗口关闭事件
                System.exit(0);
            }
        });
        //添加键盘处理类
        addKeyListener(new MyKeyListener());
        //设置可见，需要最后设置
        setVisible(true);
    }

    Image offScreenImage = null;

    /**
     * 解决闪烁问题
     * @param g
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     * paint方法在每次窗口重新绘制时会调用
     * 也就是在窗口最小化最大化的时候会调用
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        tank.paint(g);
//        List<Bullet> collect = bullet.stream().filter(o1 -> (o1.getX() < GAME_WIDTH && o1.getX() > 0) && (o1.getY() < GAME_HIGHT && o1.getY() > 0)).collect(Collectors.toList());
//        bullet = collect;
//        collect.forEach(o1 -> o1.paint(g));
        g.setColor(Color.WHITE);
        g.drawString("子弹数量" + bullet.size(), 10, 60);
        g.drawString("敌人数量" + tanks.size(), 10, 80);
        for (int i = 0; i < bullet.size(); i++) {
            Bullet bullet = this.bullet.get(i);
            bullet.paint(g);
        }
        for (int i = 0; i < explodesList.size(); i++) {
            Explodes explodes = explodesList.get(i);
            explodes.paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //碰撞检测
        for (int i = 0; i < bullet.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullet.get(i).collideWith(tanks.get(j));
            }
            //判断己方坦克碰撞检测
            bullet.get(i).collideWith(tank);
        }
//        explodes.paint(g);
    }

    /**
     * 键盘监听处理类
     */
    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        /**
         * 当某个键被按下
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
            }
            //重新设置方向
            setTankMainDir();
        }

        private void setTankMainDir() {
            if (bL) tank.setDir(Dir.LEFT);
            if (bR) tank.setDir(Dir.RIGHT);
            if (bD) tank.setDir(Dir.DOWN);
            if (bU) tank.setDir(Dir.UP);

            if (!bD && !bL && !bR && !bU){
                tank.setMoving(false);
            }else{
                tank.setMoving(true);
            }
        }

        /**
         * 当某个键释放的时候
         *
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    tank.fire();
                    break;
                case KeyEvent.VK_SPACE:
                    tank.fire(new FourDirFireStrategy());
            }
            //重新设置方向
            setTankMainDir();
        }
    }


}
