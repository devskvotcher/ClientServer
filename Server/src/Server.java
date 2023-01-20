import Mediator.Mediator;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Server {

    public static void main(String[] args)
    {
        Book book = new Book(1, "Lev Tolstoy", "Voina i Mir");
        Book book1 = new Book(2, "Nikolay Gogol`", "Mertvye Dyshi");
        Book book2 = new Book(3, "Ronald Miller", "Anaesthesia i Krovoobrashenie");
        ArrayList<Book> list = new ArrayList<>();
        list.add(book);
        list.add(book1);
        list.add(book2);


    try (ServerSocket server = new ServerSocket(8000))
    {
    System.out.println("Server started");
    while (true)
    {
        try(Mediator mediator = new Mediator(server))
        {
            String request=mediator.readLine();
            int userCase =Integer.parseInt(request);
            switch (userCase)
            {
                case 1:
                    printBook(mediator, list);
                    break;
                case 2:
                    printBook(mediator, list);
                    delBook(mediator, list);
                    printBook(mediator, list);
                    break;
                case 3:
                    searchBook(mediator, list);
                    break;
                case 4:
                    addBook(mediator, list);
                    break;
                case 5:
                    exit(0);
                default:
                    System.out.println("Некорректный запрос");
            }
        }catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }
    }catch (IOException e)
    {
        throw new RuntimeException(e);
    }
    }

    public static void  printBook(Mediator mediator, ArrayList<Book> list )
    {
        String size = list.size()+"";
        System.out.println(size);
        mediator.writeLine(size);
       for (int i=0; i<list.size(); i++)
           {
               mediator.writeLine(list.get(i).getId()+"  "+list.get(i).getAuthor()+"  "+ list.get(i).getName());
           }
    }
    public static void  addBook(Mediator mediator, ArrayList<Book> list )
    {
        int Id = list.size()+1;
        String author = mediator.readLine();
        String name = mediator.readLine();
        System.out.println(Id+"  "+author+"  "+name);
        Book book = new Book(Id, author,name);
        list.add(book);
        mediator.writeLine("Книга успешно добавлена");

    }
    public static void delBook(Mediator mediator, ArrayList<Book> list)
    {
        int Id = Integer.parseInt(mediator.readLine())-1;
       list.remove(Id);
        mediator.writeLine("Книга успешно удалена");

    }
    public static void searchBook(Mediator mediator, ArrayList<Book> list)
    {
        String search = mediator.readLine();
        ArrayList<Integer> source = new ArrayList<>();
        for (int i=0; i < list.size(); i++) {
            if (search.equals(list.get(i).getAuthor()) || search.equals(list.get(i).getName()))
            {
                source.add(list.get(i).getId());
            }
        }
        mediator.writeLine(source.size()+"");
        if (source.size() > 0)
        {

            for (int i=0; i < list.size(); i++)
            {
               mediator.writeLine(list.get(source.get(i)-1).getId()+"  "+list.get(source.get(i)-1).getAuthor()+"  "+ list.get(source.get(i)-1).getName());
            }
        }

    }

}