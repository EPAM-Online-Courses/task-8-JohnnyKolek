package efs.task.unittests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

public class PlannerTest {
    private Planner planner;

    @BeforeEach
    void init(){
        planner = new Planner();
    }

    @ParameterizedTest
    @EnumSource(ActivityLevel.class)
    void shouldCalculateDailyCaloriesDemandCorrectly(ActivityLevel activityLevel){
        //given
        User user = TestConstants.TEST_USER;

        //when
        int dailyCaloriesDemand = planner.calculateDailyCaloriesDemand(user, activityLevel);

        //then
        Map<ActivityLevel, Integer> caloriesOnActivityLevel = TestConstants.CALORIES_ON_ACTIVITY_LEVEL;
        Integer expected = caloriesOnActivityLevel.get(activityLevel);
        Assertions.assertEquals(expected,dailyCaloriesDemand);
    }

    @Test
    void shouldCalculateDailyIntakeCorrectly(){
        //given
        User user = TestConstants.TEST_USER;
        DailyIntake expectedDailyIntake = TestConstants.TEST_USER_DAILY_INTAKE;

        //when
        DailyIntake dailyIntake = planner.calculateDailyIntake(user);

        //then
        Assertions.assertEquals(expectedDailyIntake.getFat(), dailyIntake.getFat());
        Assertions.assertEquals(expectedDailyIntake.getCalories(), dailyIntake.getCalories());
        Assertions.assertEquals(expectedDailyIntake.getCarbohydrate(), dailyIntake.getCarbohydrate());
        Assertions.assertEquals(expectedDailyIntake.getProtein(), dailyIntake.getProtein());
    }
}
