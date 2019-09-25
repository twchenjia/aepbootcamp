package com.thoughtworks;

public class Game {

    private String answer;

    public Game(String answer) {
        this.answer = answer;
    }

    public String guess(String str) throws Exception {
        if (str.length() != 4) {
            throw new NotFourDigitAnswerException();
        }
        if (!validateAnswer(str)) {
            throw new NotDiffDigitAnswerException();
        }
        int positionCount = 0, containCount = 0;
        for (int i = 0; i < answer.length(); i++) {
            if (answer.charAt(i) == str.charAt(i)) {
                positionCount++;
            }
        }

        for (int i = 0; i < str.length(); i++) {
            if (answer.contains(String.valueOf(str.charAt(i))) && answer.charAt(i) != str.charAt(i)) {
                containCount++;
            }
        }
        if (positionCount == 4) {
            return "CONGRATULATION";
        } else if (positionCount == 0 && containCount == 0) {
            return "UNFORTUNATELY";
        } else {
            return positionCount + "A" + containCount + "B";
        }
    }

    private boolean validateAnswer(String answer) {
        for (int i = 0; i < answer.length(); i++) {
            for (int j = i + 1; j < answer.length(); j++) {
                if (answer.charAt(i) == answer.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    static class NotFourDigitAnswerException extends Exception {
        public NotFourDigitAnswerException() {
            super();
        }
    }


    static class NotDiffDigitAnswerException extends Exception {
        public NotDiffDigitAnswerException() {
            super();
        }
    }
}
