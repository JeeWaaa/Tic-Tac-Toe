import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe{
    int width = 600;
    int height = 650;

    JFrame frame = new JFrame("Tic-Tac_Toe");
    JPanel panel = new JPanel();
    JPanel mainPanel = new JPanel();
    JLabel label = new JLabel();
    JButton [][] play = new JButton[3][3];

    JPanel resetPanel = new JPanel();
    JButton reset = new JButton();

    String playerX = "X";
    String playerO = "O";
    String currPlayer =playerX;

    boolean end = false;
    int turns = 0;

    public TicTacToe(){

        //Creating the window.

        frame.setVisible(true);
        frame.setSize(width,height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        label.setBackground(Color.darkGray);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("Tic-Tac-Toe");
        label.setOpaque(true);

        panel.setLayout(new BorderLayout());
        panel.add(label);
        frame.add(panel);
        frame.add(panel, BorderLayout.NORTH);

        mainPanel.setLayout(new GridLayout(3,3));
        mainPanel.setBackground(Color.darkGray);
        frame.add(mainPanel);

        for(int r = 0; r<3; r++){
            for(int c = 0; c < 3; c++){

                //Creating clickable tiles.

                JButton tile = new JButton();
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Times New Roman", Font.BOLD, 120));
                tile.setFocusable(false);

                //Performing an action when a tile is pressed.

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event){

                        //If the game is over.

                        if(end){
                            return;
                        }

                        //Placing their character down.

                        JButton tile = (JButton) event.getSource();
                        if (tile.getText() == ""){
                            tile.setText(currPlayer);
                            turns++;
                            checkWinner();
                            checkLoser();
                            if(!end){
                                currPlayer = currPlayer == playerX ? playerO : playerX;
                                label.setText(currPlayer + "'s  turn");
                            }
                        }
                    }
                });

                //Adding the tile.

                play[r][c] = tile;
                mainPanel.add(tile);
            }
        }

        //Reset button.

        reset.setBackground(Color.lightGray);
        reset.setForeground(Color.black);
        reset.setText("Restart");
        reset.setFocusable(false);
        reset.setSize(100,25);

        resetPanel.setLayout(new BorderLayout());
        resetPanel.add(reset);
        frame.add(resetPanel, BorderLayout.SOUTH);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JButton reset = (JButton) e.getSource();
                end = false;
                turns = 0;
                for(int r=0; r<3; r++){
                    for(int c=0; c<3; c++){
                    play[r][c].setText("");
                    play[r][c].setBackground(Color.darkGray);
                    play[r][c].setForeground(Color.white);
                    }
                }
                currPlayer = playerX;
                label.setText("Tic-Tac-Toe");

            }
        });
    }

    public void checkWinner(){
        
        //Horizontal.

        for(int r = 0 ; r < 3 ; r++){
            if(play[r][0].getText() == ""){
                break;
            }
            else if ((play[r][0].getText() == play[r][1].getText()) && (play[r][1].getText() == play[r][2].getText())){

                for(int i = 0 ; i < 3 ; i++){
                    setWinner(play[r][i]);
                }
                end = true;
                return;
            }
        }

        //Vertical.

        for(int c = 0 ; c < 3 ; c++){
            if(play[0][c].getText() == ""){
                break;
            }
            else if ((play[0][c].getText() == play[1][c].getText()) && (play[1][c].getText() == play[2][c].getText())){
                for(int i = 0 ; i < 3 ; i++){
                    setWinner(play[i][c]);
                }
                end = true;
                return;
            }
        }

        //Diagonal (left to right).

        if((play[0][0].getText() == play[1][1].getText()) && (play[1][1].getText() == play[2][2].getText()) && play[0][0].getText() != ""){
            for(int i = 0 ; i < 3 ; i++){
                setWinner(play[i][i]);
            }
            end = true;
            return;
        }

        //Diagonal (right to left).

        else if ((play[0][2].getText() == play[1][1].getText()) && (play[1][1].getText() == play[2][0].getText()) && (play[0][2].getText() != "")){
            int i = 0;
            for(int c = 2 ; c >= 0 ; c--){
                setWinner(play[i][c]);
                i++;
            }
            end = true;
            return;
        }

        //Tie.
        
        if(turns == 9){
            for(int r=0; r<3; r++){
                for(int c=0; c<3; c++){
                    setTie(play[r][c]);
                }
            }
            end = true;
            return;
        }
    }

    public void checkLoser(){
        if(end && (turns !=9)){
            for (int r =0; r<3; r++){
                for(int c=0; c<3; c++){
                    if(play[r][c].getText() != currPlayer){
                        setLoser(play[r][c]);
                    }
                }
            }
            return;
        }
    }

    public void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        label.setText(currPlayer + " is the winner!");
    }

    public void setTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        label.setText("Tie!");
    }

    public void setLoser(JButton tile){
        tile.setForeground(Color.red);
    }
}