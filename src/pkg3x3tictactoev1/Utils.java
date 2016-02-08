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
public class Utils {

    public static void InitGame() {

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
        //Utils.PrintBoard(board);

        //introduce players and let the game begin
        players = Utils.GameIntro(keyboard, ran); //ask for input from player
        active_player = Utils.WhoStartsTheGame(ran, players[player1][name], players[player2][name]);

        do {
            Utils.PrintBoard(board);
            String next_step = Utils.GetPlayersNextStep(keyboard,
                    " what is your next step? ",
                    players[active_player][name]);

//put player figure in place
            Utils.PlaceFigureOnBoard(players[active_player][figure], next_step, board);

//print board
//            Utils.PrintBoard(board);
// check if player won - if so game_over=true;
            if (Utils.IsThereAWinner(board,
                    players[active_player][name],
                    players[active_player][figure])) {
                game_over = true;

            } else {

                active_player = (active_player == 0) ? 1 : 0;
            }

//otherwise change player and start  again
        } while (game_over == false);

        PrintBoard(board);
        System.out.println("");
        System.out.println(players[active_player][name] + ", you did an aaaaaawwwwwwwsome job.");
        System.out.println("Would you care to play again? (y/n)");
        if (keyboard.nextLine().equals("y")) {
            System.out.println("Yessss.");
            System.out.println("_____________________________");
            System.out.println("");
            InitGame();
        } else {
            System.out.println("Oh... Well...");
        }
    }

    public static boolean IsThereAWinner(String[][] board, String player, String figure) {
        boolean won = false;
        int horizontal_counter = 0, vertical_counter = 0, NW_SE_counter = 0, SW_NE_counter = 0;
        //TODO check results for each direction
        int horizontal = HorizontalWinner(board, player, figure);
        int vertical = VerticalWinner(board, player, figure);
        int NW_SE_ = NW_SE_Winner(board, player, figure);
        int SW_NE_ = SW_NE_Winner(board, player, figure);

        if (horizontal == 3
                || vertical == 3
                || NW_SE_ == 3
                || SW_NE_ == 3) {
            won = true;
        }

        return won;
    }

    public static int SW_NE_Winner(String[][] board, String player, String figure) {
        int counter = 0, row = 2, col = 0, won = 3;
        for (int i = 0; i < 3; i++) {
            if (board[row - i][col + i] == figure) {
                counter++;
            }
        }

        return counter;

    }

    public static int NW_SE_Winner(String[][] board, String player, String figure) {
        int counter = 0, row = 0, col = 0, won = 3;

        for (int i = 0; i < 3; i++) {
            row = col = i;
            if (board[row][col] == figure) {
                counter++;
            }

        }

        return counter;
    }

    public static int VerticalWinner(String[][] board, String player, String figure) {

        int counter = 0, col = 0, won = 3;

        while (col < 3) {
            int temp_count = 0;

            for (int row = 0; row < 3; row++) {


                if (board[row][col].equals(figure)) {
                    temp_count++;
                    if (temp_count == won) {
                        counter = won;
                        col = 3;
                    }
                }
            }
            col++;
        }

        return counter;
    }

    public static int HorizontalWinner(String[][] board, String player, String figure) {
        int counter = 0, row = 0, won = 3;
        do {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == figure) {
                    counter++;
                }
            }
            if (counter == won) {
                row = won;

            } else {
                counter = 0;
                row++;
            }

        } while (row < won);

        return counter;
    }

    public static String[][] GameIntro(Scanner keyboard, Random ran) {
        String[][] info = new String[3][3];
        int player1 = 0;
        int player2 = 1;
        int starting_player;
        int name = 0;
        int figure = 2;

        System.out.print("Can I have the first player's name:");
        info[player1][name] = keyboard.nextLine();
        System.out.println();
        System.out.print(info[player1][name] + ", choose your figure now.\nNoughts or Crosses?\nEnter x or o here:");
        info[player1][figure] = keyboard.nextLine();

        System.out.println();
        System.out.print("And the name of your opponent is: ");
        
        info[player2][name] = keyboard.nextLine();
        if (info[player1][figure].equals("x")) {
            info[player2][figure]=("o");
        }
        else{
            info[player2][figure]=("x");
        }
        System.out.println();
        System.out.println(info[player2][name] + ", you will be playing - " + info[player2][figure] + " - today.");

        return info;
    }

    public static int WhoStartsTheGame(Random ran, String player1, String player2) {
        int odds = ran.nextInt(2);
        String starting_player;

        if (odds > 0) {
            starting_player = player2;
        } else {
            starting_player = player1;

        }

        System.out.println("And now... \nLet the games begin...\n\n");
        System.out.println(starting_player + " takes the first turn. Good luck.");

        return odds;
    }

    public static void PrintBoard(String[][] board) {
        String top_border = "╔═══╤═══╤═══╗";
        String first_row = "║ " + board[0][0] + " │ " + board[0][1] + " │ " + board[0][2] + " ║";
        String first_border = "╟───┼───┼───╢";
        String second_row = "║ " + board[1][0] + " │ " + board[1][1] + " │ " + board[1][2] + " ║";
        String second_border = "╟───┼───┼───╢";
        String third_row = "║ " + board[2][0] + " │ " + board[2][1] + " │ " + board[2][2] + " ║";
        String bottom_border = "╚═══╧═══╧═══╝";

        System.out.println(top_border);
        System.out.println(first_row);
        System.out.println(first_border);
        System.out.println(second_row);
        System.out.println(second_border);
        System.out.println(third_row);
        System.out.println(bottom_border);

    }

    public static String GetPlayersNextStep(Scanner keyboard, String message, String player) {
        System.out.println(player + ", " + message);
        String next_step = keyboard.nextLine();

        return next_step;
    }

    public static String[][] PlaceFigureOnBoard(String player, String step, String[][] board) {
        switch (step) {
            case "1":
                board[0][0] = player;
                break;
            case "2":
                board[0][1] = player;
                break;
            case "3":
                board[0][2] = player;
                break;
            case "4":
                board[1][0] = player;
                break;
            case "5":
                board[1][1] = player;
                break;
            case "6":
                board[1][2] = player;
                break;
            case "7":
                board[2][0] = player;
                break;
            case "8":
                board[2][1] = player;
                break;
            case "9":
                board[2][2] = player;
                break;
            default:

                break;
        }

        return board;
    }

}
