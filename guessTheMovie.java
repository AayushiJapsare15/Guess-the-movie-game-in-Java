// Import the File class

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

class guessTheMovie {
    public static void main(String[] args) throws Exception {
        System.out.println("------Welcome to 'Guess the movie name' game------");

        System.out.println();

        // Creating file object
        String s = choose(new File("D:MoviesName.txt"));
        String replaced = null;
        String newString = new String();

        //replacing all " " with "_"
        for (int i = 1; i <= s.length(); i++) {
            replaced = s.replaceAll(s, "_");
            newString += replaced;
        }
        System.out.println(newString);

        //Printing length of movie name
        int lengthOfName = newString.length();
        System.out.println("A " + lengthOfName + " letters movies");

        //movie name typecasting from string to char array
        char[] db = newString.toCharArray();

        //scanner object to read input
        Scanner input = new Scanner(System.in);

        try {
            int userTries = 5;
            while (userTries != 0 && newString.contains("_") == true) {
                System.out.println("You have " + userTries + " tries left");
                System.out.println("\n\nEnter a letter for Your choice : ");

                //taking first char of input in consideration
                char usersInput = input.nextLine().charAt(0);
                char newUserInput = Character.toLowerCase(usersInput);
                int x = s.indexOf(newUserInput);

                //when you lose a try
                if (x == -1)
                    userTries--;

                //When you guess a correct character
                if (userTries != 0 && newString.contains("_") == true) {
                    while (x >= 0) {

                        db[x] = newUserInput;
                        x = s.indexOf(newUserInput, x + 1);
                    }
                    newString = String.valueOf(db);
                    System.out.println(newString);

                }

            }


            //when you guess all the characters
            if (newString.contains("_") == false) {
                System.out.println("Congratulations you won!!");
            } else {
                System.out.println("you loss BETTER LUCK NEXT TIME!!");
                System.out.println("Movie name was: " + s);
            }
        } catch (Exception e) {
            System.out.println("Invalid Input");

            System.out.println("Movie name was: " + s);
        }
    }


    //choose function to select a movieName randomly from file with names
    public static String choose(File f) throws FileNotFoundException {
        String result = null;
        Random rand = new Random();
        int n = 0;
        for (Scanner sc = new Scanner(f); sc.hasNext(); ) {
            ++n;
            String line = sc.nextLine();
            if (rand.nextInt(n) == 0)
                result = line;
        }

        return result;
    }


}