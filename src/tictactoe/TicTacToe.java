/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Scanner;


/**
 *
 * @author wangyulin
 */
public class TicTacToe {
    
    private int nRows;
    private int nColumns;
    private int numToWin;
    private char grid[][];
    private char turn;
    private TicTacToeEnum gameState;
    private int nMarks;

    
    public TicTacToe(char initialTurn){
        
        this(3, 3, 3, initialTurn);//The default constructor sets nRows, nColumns and numTowin to 3 and initialize turn to initialTurn.
    }
    
    public TicTacToe(int nRows , int nColumns, int numToWin , char initialTurn){
        if (nRows < 0 || nColumns < 0 || numToWin < 0) 
            throw new IllegalArgumentException("Grid cannot be a negative size");
        
        if (numToWin > nRows || numToWin > nColumns) 
            throw new IllegalArgumentException("sizeToWin cannot be bigger than the number of rows and columns");
        
        this.gameState = TicTacToeEnum.IN_PROGRESS;
        this.nRows = nRows;
        this.nColumns = nColumns;
        this.numToWin = numToWin;
        this.grid = new char[nRows][nColumns];
        
        reset(initialTurn); //The four argument constructor
        
    }
    
    public void reset(char initialTurn){
        
        for(int r = 0; r < this.nRows ; r++){
            for(int c = 0; c < this.nColumns; c++){
                this.grid[r][c] = ' ';
            }
        }
        
      this.turn = initialTurn;
      this.nMarks = 0;
      this.gameState = TicTacToeEnum.IN_PROGRESS;
      
    } //To reset all variable in this class
        
  
    
    
    public int getTurn(){
        return this.turn;
        
    }// To find out which turn it is right now.
    
    public TicTacToeEnum getGameState(){
        return this.gameState;
    }//To find out the game state right now.
    
    private TicTacToeEnum charToEnum(int player){
        if(player == 'X')
            return TicTacToeEnum.X_WON;
        if(player == 'O')
            return TicTacToeEnum.O_WON;
        else{
            throw new IllegalArgumentException("charToEnum(" + player + "): player must be X or O");
        }
    
        
    } // A private mothed to find winner
    
    public TicTacToeEnum takeTurn(int row, int column){
        
        if(row < 0 || this.nRows < row)  
            throw new IllegalArgumentException(" row cannot be out of the range of nRows");
        if(column < 0 || this.nColumns < column)
            throw new IllegalArgumentException("column cannot be out of the range of nColumns ");
        if (this.grid[row][column] != ' ')
            throw new IllegalArgumentException("This cell is not empty");
        if(this.gameState != TicTacToeEnum.IN_PROGRESS)
            throw new IllegalArgumentException("Game Over");
        this.grid[row][column] = this.turn;
        this.nMarks = this.nMarks + 1;
        
        
        
        if (this.turn == 'X'){
            this.turn = 'O';
        }
        if (this.turn == 'O'){
            this.turn = 'X';
        }
        
        
        return this.gameState;
        
    }//To set X or O to the required position and then try to find out the winner.
    
    private TicTacToeEnum findWinner(){
        TicTacToeEnum newGameState;
        for (int r1_1 = 0; r1_1 < nRows ; r1_1++){
            for(int c1_1 = 0; c1_1 < nColumns ; c1_1++){
                int Xcount1 = 0;
                
        
                if(grid[r1_1][c1_1] != ' '){
                    if (grid[r1_1][c1_1] == 'X'){
                        Xcount1++;
                        if(Xcount1 == numToWin){
                            newGameState = TicTacToeEnum.X_WON;
                            return newGameState;
                        }
                    }
                    if (grid[r1_1][c1_1] != 'X'){
                        newGameState = TicTacToeEnum.IN_PROGRESS;
                        Xcount1 = 0;//horizontally X
                    }        
                        
                    
                }
            }
        }
                    
        for (int r1_2 = 0; r1_2 < nRows ; r1_2++){
            for(int c1_2 = 0; c1_2 < nColumns ; c1_2++){
                
                int Ocount1 = 0;            
                    
                    if (grid[r1_2][c1_2] == 'O'){
                        Ocount1++;
                        if(Ocount1 == numToWin){
                            newGameState = TicTacToeEnum.O_WON;
                            return newGameState;//horizontally O
                        }
                    }
                    if (grid[r1_2][c1_2] != 'O'){
                        newGameState = TicTacToeEnum.IN_PROGRESS;
                        Ocount1 = 0;
                    }
                
            }
        }//To check is there a winner in horizontal direction.
        
        
        
        for (int c2_1 = 0; c2_1 < nColumns ; c2_1++){
            for(int r2_1 = 0; r2_1 < nRows ; r2_1++){
                int Xcount2 = 0;
                //int Ocount2 = 0;
        
                if(grid[r2_1][c2_1] != ' '){
                    if (grid[r2_1][c2_1] == 'X'){
                        Xcount2++;
                       
                        if(Xcount2 == numToWin){
                            newGameState = TicTacToeEnum.X_WON;
                            return newGameState;//vertically X
                        }
                    }
                    if (grid[r2_1][c2_1] != 'X'){
                        newGameState = TicTacToeEnum.IN_PROGRESS;
                        Xcount2 = 0;
                    }
                }
            }
        }
        for (int c2_2 = 0; c2_2 < nColumns ; c2_2++){
            for (int r2_2 = 0; r2_2 < nRows ; r2_2++){
                //int Xcount2 = 0;
                int Ocount2 = 0;
        
                if(grid[r2_2][c2_2] != ' '){
                    if (grid[r2_2][c2_2] == 'O'){
                        Ocount2++;
                        if(Ocount2 == numToWin){
                            newGameState = TicTacToeEnum.X_WON;
                            return newGameState;//vertically O
                        }
                    }
                    if (grid[r2_2][c2_2] != 'O'){
                        newGameState = TicTacToeEnum.IN_PROGRESS;
                        Ocount2 = 0;
                    }
                }
            }
        }//To check is there a winner in vertical direction
        
                
                    
                    
                 
        int c3_1 = 0;
        int r3_1 = 0;
        int Xcount = 0;
        int Ocount = 0;
        if (grid[r3_1][c3_1] != ' '){
            for(r3_1 = 0; r3_1 < nRows ; r3_1++){
                if(c3_1 < nColumns){
                    if(grid[r3_1][c3_1] == 'X'){
                        Xcount++;
                        Ocount = 0;
                        if(Xcount == numToWin){
                            newGameState = TicTacToeEnum.X_WON;
                            return newGameState;
                        }
                    }
                    
                    
                    if(grid[r3_1][r3_1] == 'O'){
                        Ocount++;
                        Xcount = 0;
                        if(Ocount == numToWin){
                            newGameState = TicTacToeEnum.O_WON;
                            return newGameState;//left slash
                        }
                                
                    }
                    c3_1++;
                }
            }
        }// To check is there a winner in left slash direction
        
            
        c3_1 = nColumns-1;
        r3_1 = 0;
        Xcount = 0;
        Ocount = 0;
        if(grid[r3_1][c3_1] != ' '){
            for(r3_1 = 1 ; r3_1 < nRows ; r3_1++){
                if(c3_1 >= 0){
                    if(grid[r3_1][c3_1] == 'X'){
                        Xcount++;
                        Ocount = 0;
                        if(Xcount == numToWin){
                            newGameState = TicTacToeEnum.X_WON;
                            return newGameState;
                        }
                    }
                    if(grid[r3_1][c3_1] == 'O'){
                        Ocount++;
                        Xcount = 0;
                        if(Ocount == numToWin){
                            newGameState = TicTacToeEnum.O_WON;
                            return newGameState;
                        }
                    }
                }
                
            }
        }//To check is there a winner in right slash
            
            newGameState = TicTacToeEnum.IN_PROGRESS;
            return newGameState;
            
    }// To find the winner

            
   
         
                   
                
                    
    
    public String toString(){
        String table = "";
        
        
        for (int r = 0 ; r < nRows ; r++){
            for(int c = 0 ; c < nColumns ; c ++){
                table += grid[r][c] + " | ";
        }
            table += "\n";
    }    
       return table; 
    }//To draw the table


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe('X');
        Scanner scanner = new Scanner(System.in);

        do { 
            System.out.println(game.toString());
            System.out.println(game.getTurn() + 
                ": Where do you want to mark? Enter row column");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            scanner.nextLine();
            game.takeTurn(row, column);
            
        } while (game.getGameState() == TicTacToeEnum.IN_PROGRESS);
        System.out.println( game.getGameState());

        // TODO code application logic here
    }
}
    

