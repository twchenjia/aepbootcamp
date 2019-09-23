package com.thoughtworks.guess;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.thoughtworks.exception.IllegalDigitException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GuessNumberGameTest {

  @ParameterizedTest
  @ValueSource(strings = {"1", "12", "123", "12345", "12ab", "abcd"})
  void should_throw_exception_when_guess_given_answer_is_not_four_digit_number(String answer) {
    assertThrows(IllegalDigitException.class, () -> new GuessNumberGame(answer));
  }
}
