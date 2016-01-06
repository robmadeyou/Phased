package server.util;

import java.util.Scanner;

public class InputManager extends Thread {


    public InputManager() {
        setDaemon(true);
    }

    public void handleInput(String line) {

    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            this.handleInput(scanner.nextLine());

        }
    }
}
