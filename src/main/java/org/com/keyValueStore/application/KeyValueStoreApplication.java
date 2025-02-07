package org.com.keyValueStore.application;

import java.util.Scanner;

public class KeyValueStoreApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String inputCommand = scanner.nextLine();
            String[] parsedCommand = inputCommand.split(" ");
            String command = parsedCommand[0];


            switch (command) {

            }
        }
    }

}
