import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HippodromeTest {

    private Hippodrome hippodrome;

    @Test
    void shouldThrowExceptionWhenConstructorParamIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                hippodrome = new Hippodrome(null));

        String expectedMessage = "Horses cannot be null.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowExceptionWhenConstructorParamIsEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                hippodrome = new Hippodrome(new ArrayList<>()));

        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int horseIdx = 0; horseIdx < 30; horseIdx++) {
            horses.add(new Horse("horse: "+horseIdx, horseIdx));
        }

        hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        for (int horseIdx = 0; horseIdx < 50; horseIdx++) {
            horses.add(mock(Horse.class));
        }

        hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    void getWinner(){
        List<Horse> horses = new ArrayList<>();
        Collections.addAll(horses,
                new Horse("name", 1, 0),
                new Horse("name", 1, 1),
                new Horse("name", 1, 20),
                new Horse("name", 1, 3),
                new Horse("name", 1, 99),
                new Horse("name", 1, 2));

        hippodrome = new Hippodrome(horses);
        var expectedResult = horses.get(4);
        var actualResult = hippodrome.getWinner();

        assertEquals(expectedResult, actualResult);
    }
}