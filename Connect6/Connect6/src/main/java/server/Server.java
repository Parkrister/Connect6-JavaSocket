package server;
/*
 * Connect5
 * Joe O'Regan
 * 02/02/2019
 */

import java.net.ServerSocket;
import java.net.InetAddress;

public class Server {
    private static int PORT = 8000; // default port number

    public static void main(String[] args) throws Exception {
        int portNumber = (args.length == 0) ? PORT : Integer.parseInt(args[0]);
        InetAddress inetAddress = InetAddress.getLocalHost();

        try (ServerSocket listener = new ServerSocket(portNumber)) {
            System.out.println("Connect6 Server Running\n" + "Port:" + portNumber);

            while (true) {
                Game game = new Game();

                Player player1 = new Player(listener.accept(), Game.PLAYER_1, game);
                Player player2 = new Player(listener.accept(), Game.PLAYER_2, game);

                player1.setOpponent(player2);
                player2.setOpponent(player1);

                game.setCurrentPlayer(player1);

                player1.start();
                player2.start();
            }
        }
    }
}
