import Mediator.Mediator;
import java.util.Scanner;
import java.io.IOException;

import static java.lang.System.exit;

public class Client {
    public static void main(String[] args) {
        try (Mediator mediator = new Mediator("127.0.0.1", 8000))
        {
                int user_case = user_case();
                String request =user_case+"";
                mediator.writeLine(request);
                switch (user_case)
                {
                    case 1:
                        printBook(mediator);
                        break;
                    case 2:
                        printBook(mediator);
                        delBook(mediator);
                        printBook(mediator);
                        break;
                    case 3:
                        searchBook(mediator);
                        break;
                    case 4:
                        addBook(mediator);
                        break;
                    case 5:
                        exit(0);
                        break;
                    default:
                        System.out.println("Неправильный выбор");
                }

        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    public static int  user_case()
    {
        Scanner in = new Scanner(System.in);
        int userCase;
        System.out.println("Выберите действие");
        System.out.println("1. Просмотреть список книг");
        System.out.println("2. Удалить книгу");
        System.out.println("3. Поиск");
        System.out.println("4. Добавить книгу");
        System.out.println("5. Выход");
        System.out.print("Ваш выбор:");
        userCase =  in.nextInt();

        return userCase;
    }
    public static void printBook(Mediator mediator)
    {
        int size = Integer.parseInt(mediator.readLine());
        System.out.println("В нашей картотеке:"+size+"книги");
        for (int i=0; i < size; i++)
        {
            System.out.println(mediator.readLine());
        }
    }
    public static void addBook(Mediator mediator)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите автора книги");
        mediator.writeLine(in.nextLine().toString());
        System.out.println("Введите название книги");
        mediator.writeLine(in.nextLine().toString());
        System.out.println(mediator.readLine());
    }
    public static void delBook(Mediator mediator)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите номер книги(левый столбец)");
        System.out.print("Ваш выбор:");
        mediator.writeLine(in.nextLine().toString());
        System.out.println(mediator.readLine());
    }
    public static void searchBook(Mediator mediator)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите автора");
        mediator.writeLine(in.nextLine().toString());
        String temp = mediator.readLine();
        if (temp.equals(null))
        {
            System.out.println("Совпадений не найдено");
        }
        else
        {
            int size = Integer.parseInt(temp);
            for (int i=0; i < size; i++)
            {
                System.out.println(mediator.readLine());
            }
        }

    }
}