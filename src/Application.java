import com.sun.xml.internal.ws.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    public static class Cat {
        public void sayHello() {
            System.out.println("Мяу!");
        }
    }

    public static class Dog {
        public void sayHello() {
            System.out.println("Гав!");
        }

        public void catchCat(Cat cat) {
            System.out.println("Кошка поймана");
            this.sayHello();
            cat.sayHello();
        }
    }

    public static class Human {
        private byte age;
        private String name;
        private String secondName;
        private String favoriteSport;
        public Human(){
        }
        public Human(int age, String name, String secondName) {
            this.age = (byte)age;
            this.name = name;
            this.secondName = secondName;
        }
        public Human (int age, String name, String secondName, String favoriteSport) {
            this.age = (byte)age;
            this.name = name;
            this.secondName = secondName;
            this.favoriteSport = favoriteSport;
        }


    }


    public static String printTextPerRole(String[] roles, String[] textLines) {
        int rl = roles.length;
        int tl = textLines.length;
        String outer = "";
        List<StringBuilder> parsed = new ArrayList<StringBuilder>();
        List<StringBuilder> names = new ArrayList<StringBuilder>();
        for (int i = 0; i < roles.length; i++) {
            StringBuilder str = new StringBuilder(roles[i] + ":\n");
            StringBuilder str2 = new StringBuilder(roles[i] + ":");
            parsed.add(str);
            names.add(str2);
        }
        for (int i = 0; i < textLines.length; i++) {
            StringBuilder str = new StringBuilder(textLines[i]);

            if (str.length() > 1) {
                for (int j = 0; j < roles.length; j++) {
                    if (str.indexOf(roles[j] + ":") == 0) {
                        StringBuilder adder = parsed.get(j);// текущий набор слов роли
                        int b = i + 1;//нумерация строк не с 0 поэтому +1
                        String a = roles[j]; //находим текущую роль, убираем из строки начало слов роли
                        int l = str.length();
                        StringBuilder phrase = new StringBuilder(str.substring(str.indexOf(roles[j] + ":") + a.length() + 1, l));
                        str.replace(str.indexOf(roles[j] + ":"), l, "");//str.indexOf(roles[j] + ":") + a.length() + 1 +
                        adder.append(Integer.toString(b));
                        adder.append(")");
                        adder.append(phrase);
                        if ((adder.indexOf("\n") < 0) && (adder.lastIndexOf("\n") < (adder.length() - 1))) {
                            adder.insert(adder.length(), "\n");
                        }
                        adder.insert(adder.length(), "\n");
                        parsed.set(j, adder);
                    }
                }
            }
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < roles.length; i++) {
            out.insert(out.length(), parsed.get(i));
            out.insert(out.length(), "\n");
        }
        outer = out.toString();
        return outer;
    }

    public static void main(String[] args) {
        System.out.println("читаем роли");
        List<String> roles = new ArrayList<String>();
        List<String> words = new ArrayList<String>();
        try {
            roles = Files.readAllLines(Paths.get("C:\\Users\\Дмитрий\\IdeaProjects\\rolefinder\\src\\role_names"));
        } catch (IOException e) {
            System.out.println("не прочли");
            ;
        }
        ;
        try {
            words = Files.readAllLines(Paths.get("C:\\Users\\Дмитрий\\IdeaProjects\\rolefinder\\src\\role_text"));
        } catch (IOException e) {
            System.out.println("не прочли");
            ;
        }
        ;
        String l = printTextPerRole(roles.toArray(new String[0]), words.toArray(new String[0]));
        System.out.println(l);

        byte[] arr = new byte[]{(byte) 'a', (byte) 'b',(byte) 'c', (byte) 'd'};
        System.out.println(arr.toString());
        AsciiCharSequence seq = new AsciiCharSequence(arr);
        System.out.println(seq.toString());
        System.out.println(seq.charAt(1));
        System.out.println(seq.length());
        System.out.println(seq.subSequence(1,3));
/*
        try (Scanner s = new Scanner("role_names").useDelimiter("\\n")) {
            while(s.hasNext()) {
                String line = s.next();
            }
        }
        System.out.println("читаем текст");
        try (Scanner s = new Scanner("role_text").useDelimiter("\\n")) {
            while(s.hasNext()) {
                String line = s.next();
            }
        }*/

    }
}


