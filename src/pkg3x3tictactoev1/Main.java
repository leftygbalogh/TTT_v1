/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3x3tictactoev1;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author lefty
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        boolean game_over = false;
        Scanner keyboard = new Scanner(System.in);
        Random ran = new Random();
        String[][] players = new String[3][3];
        int player1 = 0;
        int player2 = 1;
        int active_player = 2;
        int name = 0;
        int status = 1; //won or playing
        int figure = 2;

        String[][] board = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
        Utils.PrintBoard(board);

        //introduce players and let the game begin
        players = Utils.GameIntro(keyboard, ran); //ask for input from player
        active_player = Utils.WhoStartsTheGame(ran, players[player1][name], players[player2][name]);
        do {
            String next_step = Utils.GetPlayersNextStep(keyboard,
                    "it is your turn now. What is your next step?",
                    players[active_player][name]);
            //put player figure in place
            Utils.PlaceFigureOnBoard(players[active_player][figure], next_step, board);
            //print board
            Utils.PrintBoard(board);
            // check if player won - if so game_over=true;
            if (Utils.IsThereAWinner(board,
                    players[active_player][name],
                    players[active_player][figure])) {
                game_over = true;

            }

            active_player = (active_player == 0) ? 1 : 0;

//otherwise change player and start  again
        } while (game_over == false);

    }

}
