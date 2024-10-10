//Jose Flores
//Hemant Kulkarni
//Taich Ite
//10/08/24
// CPSC-39: Programming and Methodology II

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ScrabbleGame {

private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
//list that hold words from file
private List<Word> words = new ArrayList<>();

public ScrabbleGame(String wordFile) throws IOException {
        loadWords(wordFile);
}
//loads words from file into word list
private void loadWords(String wordFile) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(wordFile));       
    String line;
    while ((line = br.readLine()) != null) {
        words.add(new Word(line.trim().toLowerCase()));
    }
    br.close();
}

//generates 4 random letters
public char[] generateRandomLetters() {
    Random random = new Random();
    char[] letters = new char[4];
    for (int i = 0; i < 4; i++) {
        letters[i] = (char) (random.nextInt(26) + 'a'); 
    }
    return letters;
}

//checks if word is good using a binary search check to compare
public boolean isWordGood(String wordToSearch) {
    int low = 0;
    int high = words.size() - 1;

    while (low <= high) {
        int mid = (low + high) / 2;
        Word midWord = words.get(mid);

        if (midWord.getWord().equals(wordToSearch.toLowerCase())) {
            return true;
        }

        if (midWord.getWord().compareTo(wordToSearch.toLowerCase()) < 0) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return false;
}

//game progress of how it prints out methods
public void play() {
   //do-while that keeps running untill you say exit
    do {
    char[] randomLetters = generateRandomLetters();
    replaceFourthWithVowel(randomLetters);
    System.out.println("Here are your letters: " + Arrays.toString(randomLetters));
    Scanner scanner = new Scanner(System.in);
    String userWord;
   

    
    System.out.print("Enter a word using these letters(or type 'exit' to quit): ");
    userWord = scanner.nextLine();
    if (userWord.equalsIgnoreCase("exit")) {
        System.out.print("Thank you for playing!");
        scanner.close();
        break;
    }
    if (isWordGood(userWord)) {
        System.out.println(userWord + " is a valid Scrabble word!");
    
    } else {
        System.out.println(userWord + " is not a valid Scrabble word.");
        System.out.println("Try again.");
    }
} while (true);

    
}
//method that overrides 4th char generated and replaces with a random vovel
public void replaceFourthWithVowel(char[] letters) {
    Random random = new Random();
    letters[3] = VOWELS[random.nextInt(VOWELS.length)];  
}
//main where game is initialated
    public static void main(String[] args) {
        try {
            ScrabbleGame game = new ScrabbleGame("CollinsScrabbleWords_2019.txt");
            game.play();
        } catch(IOException e) {
            System.out.println("Error " + e.getMessage());
        }
    }
}

