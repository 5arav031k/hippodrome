import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {

    private Horse horse;

    @Test
    void shouldThrowExceptionWhenFirstConstructorParamIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                horse = new Horse(null, 1));

        String expectedMessage = "Name cannot be null.";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "    "})
    void shouldThrowExceptionWhenFirstConstructorParamIsEmpty(String param) {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                horse = new Horse(param, 1));

        String expectedMessage = "Name cannot be blank.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowExceptionWhenSecondConstructorParamIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                horse = new Horse("name", -1));

        String expectedMessage = "Speed cannot be negative.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowExceptionWhenThirdConstructorParamIsNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                horse = new Horse("name", 1, -1));

        String expectedMessage = "Distance cannot be negative.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {"name", "16584651", "NaMe 2", "!@#$%^&*()_+*-+"})
    void getName(String name) {
        horse = new Horse(name, 1);
        assertEquals(name, horse.getName());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 15651, 879796465})
    void getSpeed(int speed) {
        horse = new Horse("name", speed, 1);
        assertEquals(speed, horse.getSpeed());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 15651, 879796465})
    void getDistanceForThreeParams(int distance) {
        horse = new Horse("name", 1, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDistanceForTwoParams() {
        horse = new Horse("name", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    void move() {
        horse = new Horse("name", 1);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            double expectedResult = 0.5;

            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(expectedResult);
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));

            assertEquals(expectedResult, horse.getDistance());
        }
    }
}