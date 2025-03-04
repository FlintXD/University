import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        log("main() begins");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Введите значение для x:");
            int x = scanner.nextInt(); // Получаем значение x от пользователя
            f1(x); // Передаем введенное значение
            log("main(): no errors in f1()");
        } catch (AppException | RuntimeException e) {
            log(e.getMessage() + " AppException cached!");
        }
        log("main(): inner block done!");
        log("main() done!");
    }

    static void f1(int x) throws Exception {
        log("f1() begins");
        try {
            f2(x);
        } catch (AppException e) {
            log("f1() exception catch");
            throw new Exception(e);
        } finally {
            log("f1() finally works!");
        }
        log("f1() done!");
    }

    static void f2(int x) throws Exception {
        log("f2() begins");
        if (x < 0) {
            log("f2() throws an exception");
            throw new AppException("error in f2()");
        }
        log("f2 done!");
    }

    static void log(Object o) {
        System.out.println(o + "");
    }

    static class AppException extends Exception {
        public AppException(String message) {
            super(message);
        }
    }
}