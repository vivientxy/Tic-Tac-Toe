import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to a game of Tic Tac Toe!");
        System.out.println();

        // print out board with index for player to reference
        System.out.println("The index for each position on the board is as follows:");
        System.out.println();
        System.out.println(" 7 | 8 | 9 ");
        System.out.println("---------");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("---------");
        System.out.println(" 1 | 2 | 3 ");
        System.out.println();

        // declare player and computer icons
        char playerIcon = 'O';
        char computerIcon = 'X';
        System.out.print("You will be playing as " + playerIcon + ". ");

        // randomize if player or computer goes first
        Random random = new Random();
        int randomNum = random.nextInt(2);
        String playerTurn = "";
        if (randomNum == 0) {
            playerTurn = "Player";
        } else {
            playerTurn = "Computer";
        }
        System.out.println(playerTurn + " goes first!");

        // instantiate a new board
        Board board = new Board();

        // game loop start!
        boolean gameOn = true;
        while (gameOn) {
            if (playerTurn.equals("Computer")) {
                int compMove = computerMove(board);
                board.setSlot(compMove, computerIcon);
                // System.out.println();
                // board.printBoard();
                if (hasWon(board)) {
                    System.out.println();
                    board.printBoard();
                    System.out.println("\nSorry, you've lost!");
                    gameOn = false;
                }
                playerTurn = "Player";
            } else {
                System.out.println();
                board.printBoard();
                String input = "";
                System.out.println("\nPlease select a box: ");
                while (!isValidMove(input, board)) {
                    System.out.print("> ");
                    input = scan.next();
                }
                int playerMove = Integer.parseInt(input);
                playerMove --;
                board.setSlot(playerMove, playerIcon);
                // System.out.println();
                // board.printBoard();
                if (hasWon(board)) {
                    System.out.println();
                    board.printBoard();
                    System.out.println("\nCongratulations, you've won!");
                    gameOn = false;
                }
                playerTurn = "Computer";
            }
            if (gameOver(board)) {
                System.out.println();
                board.printBoard();
                System.out.println("\nGame over! It was a tie.");
                gameOn = false;
            }
        }
        scan.close();
    }

    // public static boolean isValidKey(char key) {
    //     return key == 'X' || key == 'O';
    // }

    public static boolean isValidMove(String index, Board board) {
        boolean result = false;
        try {
            int intIndex = Integer.parseInt(index) - 1;
            if (board.getSlot()[intIndex] == ' ') {
                result = true;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static boolean gameOver(Board board) {
        boolean result = true;
        for (char slot : board.getSlot()) {
            if (slot == ' ') {
                result = false;
            }
        }
        return result;
    }

    public static boolean hasWon(Board board) {
        char[] slot = board.getSlot();
        boolean result = false;
        // horizontal match
        if (slot[0] == slot[1] && slot[1] == slot[2] && slot[2] != ' ') result = true;
        else if (slot[3] == slot[4] && slot[4] == slot[5] && slot[5] != ' ') result = true;
        else if (slot[6] == slot[7] && slot[7] == slot[8] && slot[8] != ' ') result = true;
        // vertical match
        else if (slot[6] == slot[3] && slot[3] == slot[0] && slot[0] != ' ') result = true;
        else if (slot[7] == slot[4] && slot[4] == slot[1] && slot[1] != ' ') result = true;
        else if (slot[8] == slot[5] && slot[5] == slot[2] && slot[2] != ' ') result = true;
        // diagonal match
        else if (slot[6] == slot[4] && slot[4] == slot[2] && slot[2] != ' ') result = true;
        else if (slot[8] == slot[4] && slot[4] == slot[0] && slot[0] != ' ') result = true;
        return result;
    }

    public static int computerMove(Board board) {
        int index = 0;
        char[] slot = board.getSlot();
        char player = 'O';
        char computer = 'X';
        // check for comp winning condition first
        // horizontal
        if (slot[0] == slot[1] && slot[1] == computer && slot[2] == ' ') index = 2;
        else if (slot[0] == slot[2] && slot[2] == computer && slot[1] == ' ') index = 1;
        else if (slot[1] == slot[2] && slot[2] == computer && slot[0] == ' ') index = 0;
        else if (slot[3] == slot[4] && slot[4] == computer && slot[5] == ' ') index = 5;
        else if (slot[3] == slot[5] && slot[5] == computer && slot[4] == ' ') index = 4;
        else if (slot[4] == slot[5] && slot[5] == computer && slot[3] == ' ') index = 3;
        else if (slot[6] == slot[7] && slot[7] == computer && slot[8] == ' ') index = 8;
        else if (slot[6] == slot[8] && slot[8] == computer && slot[7] == ' ') index = 7;
        else if (slot[7] == slot[8] && slot[8] == computer && slot[6] == ' ') index = 6;
        // vertical
        else if (slot[6] == slot[3] && slot[3] == computer && slot[0] == ' ') index = 0;
        else if (slot[6] == slot[0] && slot[0] == computer && slot[3] == ' ') index = 3;
        else if (slot[0] == slot[3] && slot[3] == computer && slot[6] == ' ') index = 6;
        else if (slot[7] == slot[4] && slot[4] == computer && slot[1] == ' ') index = 1;
        else if (slot[7] == slot[1] && slot[1] == computer && slot[4] == ' ') index = 4;
        else if (slot[4] == slot[1] && slot[1] == computer && slot[7] == ' ') index = 7;
        else if (slot[5] == slot[2] && slot[2] == computer && slot[8] == ' ') index = 8;
        else if (slot[2] == slot[8] && slot[8] == computer && slot[5] == ' ') index = 5;
        else if (slot[5] == slot[8] && slot[8] == computer && slot[2] == ' ') index = 2;
        // diagonal
        else if (slot[6] == slot[4] && slot[4] == computer && slot[2] == ' ') index = 2;
        else if (slot[2] == slot[4] && slot[4] == computer && slot[6] == ' ') index = 6;
        else if (slot[8] == slot[4] && slot[4] == computer && slot[0] == ' ') index = 0;
        else if (slot[0] == slot[4] && slot[4] == computer && slot[8] == ' ') index = 8;
        else if (slot[0] == slot[8] && slot[8] == computer && slot[4] == ' ') index = 4;
        else if (slot[6] == slot[2] && slot[2] == computer && slot[4] == ' ') index = 4;
        // block player moves
        // horizontal
        else if (slot[0] == slot[1] && slot[1] == player && slot[2] == ' ') index = 2;
        else if (slot[0] == slot[2] && slot[2] == player && slot[1] == ' ') index = 1;
        else if (slot[1] == slot[2] && slot[2] == player && slot[0] == ' ') index = 0;
        else if (slot[3] == slot[4] && slot[4] == player && slot[5] == ' ') index = 5;
        else if (slot[3] == slot[5] && slot[5] == player && slot[4] == ' ') index = 4;
        else if (slot[4] == slot[5] && slot[5] == player && slot[3] == ' ') index = 3;
        else if (slot[6] == slot[7] && slot[7] == player && slot[8] == ' ') index = 8;
        else if (slot[6] == slot[8] && slot[8] == player && slot[7] == ' ') index = 7;
        else if (slot[7] == slot[8] && slot[8] == player && slot[6] == ' ') index = 6;
        // vertical
        else if (slot[6] == slot[3] && slot[3] == player && slot[0] == ' ') index = 0;
        else if (slot[6] == slot[0] && slot[0] == player && slot[3] == ' ') index = 3;
        else if (slot[0] == slot[3] && slot[3] == player && slot[6] == ' ') index = 6;
        else if (slot[7] == slot[4] && slot[4] == player && slot[1] == ' ') index = 1;
        else if (slot[7] == slot[1] && slot[1] == player && slot[4] == ' ') index = 4;
        else if (slot[4] == slot[1] && slot[1] == player && slot[7] == ' ') index = 7;
        else if (slot[5] == slot[2] && slot[2] == player && slot[8] == ' ') index = 8;
        else if (slot[2] == slot[8] && slot[8] == player && slot[5] == ' ') index = 5;
        else if (slot[5] == slot[8] && slot[8] == player && slot[2] == ' ') index = 2;
        // diagonal
        else if (slot[6] == slot[4] && slot[4] == player && slot[2] == ' ') index = 2;
        else if (slot[2] == slot[4] && slot[4] == player && slot[6] == ' ') index = 6;
        else if (slot[8] == slot[4] && slot[4] == player && slot[0] == ' ') index = 0;
        else if (slot[0] == slot[4] && slot[4] == player && slot[8] == ' ') index = 8;
        else if (slot[0] == slot[8] && slot[8] == player && slot[4] == ' ') index = 4;
        else if (slot[6] == slot[2] && slot[2] == player && slot[4] == ' ') index = 4;
        // else, take random empty slot
        else {
            Random random = new Random();
            int ranNum = random.nextInt(9);
            while (slot[ranNum] != ' ') {
                ranNum = random.nextInt(9);
            }
            index = ranNum;
        }
        return index;
    }
}