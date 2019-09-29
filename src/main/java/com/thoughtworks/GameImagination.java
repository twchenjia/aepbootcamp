package com.thoughtworks;

public class GameImagination {
    private static final String INCORRECT_RET = "UNFORTUNATELY";
    private static final String CORRECT_RET = "CONGRATULATION";

    public static void main(String[] args) {
        System.out.println(guessWord("1234", "1243"));
    }
    /**
     * 1234 1267
     */
    public static String guessWord(String word, String guessWord) {
        int posCount = 0;
        int containCount = 0;
        for (int i = 0; i < word.length(); i++) {
            char temp = word.charAt(i);
            if (guessWord.charAt(i) == temp) {
                posCount++;
                guessWord =guessWord.replace(temp, ' ');
            }
        }
        containCount = countContain(word, guessWord);
        if (posCount == word.length()) {
            return CORRECT_RET;
        } else if (posCount == 0 && containCount ==0) {
            return INCORRECT_RET;

        } else {
            return posCount + "A" + containCount + "B";
        }
    }

    public static int countContain(String word, String left) {
        int containsCount = 0;
        for (int i = 0; i < left.length(); i++) {
            if (word.contains(String.valueOf(left.charAt(i)))) {
                containsCount++;
            }
        }
        return containsCount;
    }
}
