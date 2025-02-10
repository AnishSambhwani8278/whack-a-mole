import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class WhackAMole{
    int boardWidth = 600;
    int boardHeight = 650;

    JFrame frame = new JFrame("WhackAMole Game");
    JLabel textLabel = new JLabel();
    JLabel bestScore = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel gamePanel = new JPanel();

    JButton[] buttonBoard = new JButton[9];
    JButton restartButton = new JButton();

    Image cactusImage = new ImageIcon(getClass().getResource("/cactus.png")).getImage();
    ImageIcon cactusIcon = new ImageIcon(cactusImage.getScaledInstance(150,150, Image.SCALE_SMOOTH));

    Image moleImage = new ImageIcon(getClass().getResource("/mole.png")).getImage();
    ImageIcon moleIcon = new ImageIcon(moleImage.getScaledInstance(150,150, Image.SCALE_SMOOTH));

    JButton currCactus;
    JButton currCactus1;
    JButton currCactus2;
    JButton currMole;
    JButton currMole1;
    JButton currMole2;

    Random random = new Random();
    Timer cactusTimer;
    Timer cactusTimer1;
    Timer cactusTimer2;
    Timer moleTimer;
    Timer moleTimer1;
    Timer moleTimer2;

    int score = 0;
    int highScore = 0;

    WhackAMole(){
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setIconImage(new ImageIcon(getClass().getResource("/mole.png")).getImage());

        textLabel.setFont(new Font("Arial",Font.PLAIN,30));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);
        textLabel.setText("Score: 0");

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);

        restartButton.setText("Restart");
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score = 0;
                textLabel.setText("Score: 0");

                for(int i=0;i<9;i++){
                    buttonBoard[i].setEnabled(true);
                }

                if (currMole != null) {
                    currMole.setIcon(null);
                }
                if (currMole1 != null) {
                    currMole1.setIcon(null);
                }
                if (currMole2 != null) {
                    currMole2.setIcon(null);
                }
                if (currCactus != null) {
                    currCactus.setIcon(null);
                }
                if (currCactus1 != null) {
                    currCactus1.setIcon(null);
                }
                if (currCactus2 != null) {
                    currCactus2.setIcon(null);
                }

                moleTimer.restart();
                moleTimer1.restart();
                moleTimer2.restart();
                cactusTimer.restart();
                cactusTimer1.restart();
                cactusTimer2.restart();
            }
        });
        restartButton.setFocusable(false);
        textPanel.add(restartButton, BorderLayout.WEST);

        bestScore.setFont(new Font("Arial",Font.PLAIN,15));
        bestScore.setHorizontalAlignment(JLabel.CENTER);
        bestScore.setOpaque(true);
        bestScore.setText("High Score: " + highScore);
        textPanel.add(bestScore, BorderLayout.EAST);

        frame.add(textPanel, BorderLayout.NORTH);

        gamePanel.setLayout(new GridLayout(3,3));
        frame.add(gamePanel);

        for(int i=0;i<9;i++){
            JButton tile = new JButton();
            buttonBoard[i] = tile;
            gamePanel.add(tile);
            tile.setFocusable(false);

            tile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton tile = (JButton) e.getSource();
                    if(tile == currMole || tile == currMole1 || tile == currMole2){
                        score+=10;
                        textLabel.setText("Score: " + Integer.toString(score));
                    }
                    else if(tile == currCactus || tile == currCactus1 || tile == currCactus2){
                        textLabel.setText("Game Over: " + Integer.toString(score));
                        if(score > highScore){
                            highScore = score;
                        }
                        bestScore.setText("High Score: " + highScore);
                        moleTimer.stop();
                        moleTimer1.stop();
                        moleTimer2.stop();
                        cactusTimer.stop();
                        cactusTimer1.stop();
                        cactusTimer2.stop();
                        for(int i=0;i<9;i++){
                            buttonBoard[i].setEnabled(false);
                        }
                    }
                }
            });
        }

        moleTimer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currMole!=null){
                    currMole.setIcon(null);
                    currMole = null;
                }

                int num = random.nextInt(9);
                JButton tile = buttonBoard[num];

                if(tile == currCactus || tile == currMole1 || tile == currCactus1 || tile == currMole2 || tile == currCactus2){
                    return;
                }

                currMole = tile;
                currMole.setIcon(moleIcon);
            }
        });

        moleTimer1 = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currMole1!=null){
                    currMole1.setIcon(null);
                    currMole1 = null;
                }

                int num = random.nextInt(9);
                JButton tile = buttonBoard[num];

                if(tile == currCactus || tile == currMole || tile == currCactus1 || tile == currMole2 || tile == currCactus2){
                    return;
                }

                currMole1 = tile;
                currMole1.setIcon(moleIcon);
            }
        });

        moleTimer2 = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currMole2!=null){
                    currMole2.setIcon(null);
                    currMole2 = null;
                }

                int num = random.nextInt(9);
                JButton tile = buttonBoard[num];

                if(tile == currCactus || tile == currMole || tile == currCactus1 || tile == currMole1 || tile == currCactus2){
                    return;
                }

                currMole2 = tile;
                currMole2.setIcon(moleIcon);
            }
        });

        cactusTimer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currCactus!=null){
                    currCactus.setIcon(null);
                    currCactus = null;
                }

                int num = random.nextInt(9);
                JButton tile = buttonBoard[num];

                if(tile == currMole || tile == currMole1 || tile == currCactus1 || tile == currMole2 || tile == currCactus2){
                    return;
                }

                currCactus = tile;
                currCactus.setIcon(cactusIcon);
            }
        });

        cactusTimer1 = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currCactus1!=null){
                    currCactus1.setIcon(null);
                    currCactus1 = null;
                }

                int num = random.nextInt(9);
                JButton tile = buttonBoard[num];

                if(tile == currMole || tile == currMole1 || tile == currCactus || tile == currMole2 || tile == currCactus2){
                    return;
                }

                currCactus1 = tile;
                currCactus1.setIcon(cactusIcon);
            }
        });

        cactusTimer2 = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currCactus2!=null){
                    currCactus2.setIcon(null);
                    currCactus2 = null;
                }

                int num = random.nextInt(9);
                JButton tile = buttonBoard[num];

                if(tile == currMole || tile == currMole1 || tile == currCactus || tile == currMole2 || tile == currCactus1){
                    return;
                }

                currCactus2 = tile;
                currCactus2.setIcon(cactusIcon);
            }
        });

        moleTimer.start();
        moleTimer1.start();
        moleTimer2.start();
        cactusTimer.start();
        cactusTimer1.start();
        cactusTimer2.start();
        frame.setVisible(true);
    }
}

public class Main {
    public static void main(String[] args) {
        WhackAMole wa = new WhackAMole();
    }
}