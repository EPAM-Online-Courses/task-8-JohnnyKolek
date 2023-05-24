package efs.task.unittests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FitCalculatorTest {

    @Test
    void shouldReturnTrue_whenDietRecommended() {
        //given
        double weight = 89.2;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }

    @Test
    void shouldReturnFalse_whenDietNotRecommended() {
        //given
        double weight = 69.5;
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }


    @Test
    void shouldThrow_whenHeightZero() {
        //given
        double weight = 69.5;
        double height = 0.0;

        //when
        //then
        Assertions.assertThrows(IllegalArgumentException.class, () -> FitCalculator.isBMICorrect(weight, height));
    }

    @ParameterizedTest(name = "Weight Test: weight={0}")
    @ValueSource(doubles = {77.8, 90.7, 80.8})
    void shouldReturnTrue_whenDietRecommendedForMultipleWeights(double weight) {
        //given
        double height = 1.72;

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertTrue(recommended);
    }


    @ParameterizedTest(name = "Test: height={0}, weight={1}")
    @CsvSource({"77.8, 170", "90.7, 180", "80.8, 175"})
    void shouldReturnFalse_whenDietNotRecommendedForMultipleWeightsAndHeights(double weight, double height) {

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }

    @ParameterizedTest(name = "Test: height={1}, weight={0}")
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void shouldReturnFalse_whenDietNotRecommendedForMultipleWeightsAndHeightsCSV(double weight, double height) {

        //when
        boolean recommended = FitCalculator.isBMICorrect(weight, height);

        //then
        assertFalse(recommended);
    }


    @Test
    void shouldFindUserWithTheWorstBMI(){
        //given
        List<User> usersList = TestConstants.TEST_USERS_LIST;

        //when
        User userWithTheWorstBMI = FitCalculator.findUserWithTheWorstBMI(usersList);

        //then
        double expectedWeight = 97.3;
        double expectedHeight = 1.79;

        Assertions.assertEquals(expectedHeight, userWithTheWorstBMI.getHeight());
        Assertions.assertEquals(expectedWeight, userWithTheWorstBMI.getWeight());
    }

    @Test
    void shouldReturnNullWhenEmptyList(){
        //given
        List<User> usersList = Collections.emptyList();

        //when
        User userWithTheWorstBMI = FitCalculator.findUserWithTheWorstBMI(usersList);

        //then
        Assertions.assertNull(userWithTheWorstBMI);
    }


    @Test
    void shouldCalculateBMIScoreCorrectly(){
        //given
        List<User> usersList = TestConstants.TEST_USERS_LIST;

        //when
        double[] result = FitCalculator.calculateBMIScore(usersList);

        //then
        double[] expected = TestConstants.TEST_USERS_BMI_SCORE;
        Assertions.assertArrayEquals(expected, result);

    }






}