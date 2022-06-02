package app;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

import static java.lang.System.exit;

public class LocaleExplore {
    Properties commands = new Properties();
    public void start() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, IOException {
        commands.load(this.getClass().getClassLoader().getResourceAsStream("Locales.properties"));

        CommandsLocale.setLocale("en-US"); // or ro-RO for romanian
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(CommandsLocale.message("prompt"));
            String command = scanner.nextLine();
            if (command.equals("Exit")) break;
            String[] params = command.trim().split("\\s+");
            switch (params[0]) {
                case "DisplayLocales" : {
                    Class clazz = Class.forName(commands.getProperty("display-locale.impl"));
                    clazz.getConstructor().newInstance();
                    break;
                }
                case "SetLocale" : {
                    Class clazz = Class.forName(commands.getProperty("set-locale.impl"));
                    if (params.length >= 2)
                        clazz.getConstructor(String.class).newInstance(params[1]);
                    else
                        System.out.println(CommandsLocale.message("invalid"));
                    break;
                }
                case "Info" : {
                    Class clazz = Class.forName(commands.getProperty("info-locale.impl"));
                    clazz.getConstructor().newInstance();
                    break;
                }
                case "Stop" : {
                    System.out.println("Program stopped!");
                    exit(1);
                }
                default : System.out.println(CommandsLocale.message("invalid"));

            }
        }
    }


    public static void main(String args[]) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        new LocaleExplore().start();
    }
}