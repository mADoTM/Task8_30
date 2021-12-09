package ru.vsu.cs.dolzhenkoms;

import ru.vsu.cs.dolzhenkoms.Utils.ConsoleUI;
import ru.vsu.cs.dolzhenkoms.Utils.WindowUI;

import java.io.IOException;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) throws IOException {
        String answer = readString();
        runInterface(answer);
    }

    private static String readString() {
        System.out.print("Вы хотите запустить графический интерфейс? Ответ да/нет - ");
        String answer = (new Scanner(System.in)).next();

        if(answer.equals("нет") || answer.equals("да")) {
            return answer;
        }

        System.out.println("Вы ввели неправильный ответ. Повторите заново");
        return readString();
    }

    private static void runInterface(String answer) throws IOException {
        if(answer.equals("нет")) {
            (new ConsoleUI()).run();
        }
        else {
            new WindowUI();
        }
    }
}
