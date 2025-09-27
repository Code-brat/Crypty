
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

import java.util.Scanner;

class Crypty {
  static File file;
  static BufferedReader reader;
  static FileWriter writer;
  static int KEY;

  public static void readFile() throws Exception {
    String line;

    while ((line = reader.readLine()) != null) {
      String decryptedLine = crypter(line, 'r');

      System.out.println(decryptedLine);
    }
  }

  public static void writeFile(String text) throws Exception {
    writer = new FileWriter(file, true);
  
    String encryptedText = crypter(text, 'w');

    writer.append("\n" + encryptedText);
    writer.close();
  }

  public static String crypter(String sentence, char operation) {
    String cryptedText = "";

    if (operation == 'r')
      KEY = -5;
    else if (operation == 'w')
      KEY = 5;

    for (int i = 0; i < sentence.length(); i++) {
      cryptedText += (char) ((int) sentence.charAt(i) + KEY);
    }

    return cryptedText;
  }

  public static void main(String[] args) throws Exception {
    file = new File("passwords.txt");
    reader = new BufferedReader(new FileReader(file));

    Scanner input = new Scanner(System.in);
    int choice;
    
    System.out.println("Welcome to CRYPTY!!");

    String prompt = """

      What do you want to do now?
      1. read
      2. write
      3. quit

      press 1, 2 or 3 to continue...

      Your choice here: 
      """;
    do {
      System.out.print(prompt);
      choice = input.nextInt();

      if (choice == 1) {
        System.out.println("Loading...");
        Thread.sleep(3000);
        System.out.println("\n\n");
        readFile();
        System.out.println("\n\n");
        
      } else if (choice == 2) {
        input.nextLine();
        String password = input.nextLine();
        writeFile(password);
        System.out.println("You're password is written successfully!\n\n");
        Thread.sleep(2000);
      } else if (choice == 3) {
        System.out.println("\nquiting...\n\n");
        Thread.sleep(3000);
      } else {
        System.out.println("\nSorry, Invalid choice :(\n\n");
        Thread.sleep(3000);
      }
    } while (choice != 3);
  }
}
