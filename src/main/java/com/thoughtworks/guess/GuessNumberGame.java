package com.thoughtworks.guess;

import com.thoughtworks.exception.IllegalDigitException;
import java.util.regex.Pattern;

public class GuessNumberGame {

  private static Pattern ANSWER_PATTERN = Pattern.compile("\\d{4}");

  private String answer;

  public GuessNumberGame(String answer) {
    if (!ANSWER_PATTERN.matcher(answer).matches()) {
      throw new IllegalDigitException();
    }

    this.answer = answer;
  }


  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }
}
