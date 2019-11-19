package com.thoughtworks;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {
    /**
     * tasks:
     * 1.指定的输入非四个格子，提示需输入4个格子的数字
     * 2.指定的输入有两个格子的数字相同，提示输入的格子必须两两不同
     * 3.指定输入格子数字为5678，返回UNFORTUNATELY
     * 4.指定输入格子数字为4567，返回0A1B
     * 5.指定输入格子数字为1567，返回1A0B
     * 6.指定输入格子数字为1276，返回2A0B
     * 7.指定输入格子数字为1243，返回2A2B
     * 7.指定输入格子数字为1238，返回3A0B
     * 8.指定输入格子数字为1234，返回CONGRATULATION
     */


    @Test
    public void should_return_UNFORTUNATELY_when_guess_5678_given_answer_is_1234() throws Exception {
        Game game = new Game("1234");
        String ret = game.guess("5678");
        assertThat(ret, is("UNFORTUNATELY"));
    }


    @Test
    public void should_return_0A1B_when_guess_4567_given_answer_is_1234() throws Exception {
        Game game = new Game("1234");
        String ret = game.guess("4567");
        assertThat(ret, is("0A1B"));
    }

    @Test
    public void should_return_1A0B_when_guess_1567_given_answer_is_1234() throws Exception {
        Game game = new Game("1234");
        String ret = game.guess("1567");
        assertThat(ret, is("1A0B"));
    }

    @Test
    public void should_return_2A0B_when_guess_1276_given_answer_is_1234() throws Exception {
        Game game = new Game("1234");
        String ret = game.guess("1276");
        assertThat(ret, is("2A0B"));
    }

    @Test
    public void should_return_2A2B_when_guess_1243_given_answer_is_1234() throws Exception {
        Game game = new Game("1234");
        String ret = game.guess("1243");
        assertThat(ret, is("2A2B"));
    }

    @Test
    public void should_return_3A0B_when_guess_1238_given_answer_is_1234() throws Exception {
        Game game = new Game("1234");
        String ret = game.guess("1238");
        assertThat(ret, is("3A0B"));
    }

    @Test
    public void should_return_CONGRATULATION_when_guess_1234_given_answer_is_1234() throws Exception {
        Game game = new Game("1234");
        String ret = game.guess("1234");
        assertThat(ret, is("CONGRATULATION"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1233", "1223", "1123"})
    public void should_throw_Exception_when_given_not_four_different_digit(String answer) throws Exception {
        Game game = new Game("1234");
        assertThrows(Game.NotDiffDigitAnswerException.class, () -> game.guess(answer));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12345", "1234567"})
    public void should_throw_Exception_when_given_not_four_digit(String answer) throws Exception {
        Game game = new Game("1234");
        assertThrows(Game.NotFourDigitAnswerException.class, () -> game.guess(answer));
    }

    @org.junit.jupiter.api.Test
    void name2() {

    }
}
