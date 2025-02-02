package nine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class GuitarTest {

    @Test
    public void createGuitarWithArguments() {
        Guitar guitar = Guitar.withTypeAndName(GuitarType.ACOUSTIC, "Yamaha");
        Assertions.assertEquals(GuitarType.ACOUSTIC, guitar.getGuitarType());
        Assertions.assertEquals("Yamaha", guitar.getName());
        Assertions.assertTrue(guitar.getStrings().isEmpty());
    }

    @Test
    public void checkGuitarForNullStrings() {
        Guitar guitar = Guitar.withTypeAndName(GuitarType.ACOUSTIC, "Yamaha");
        Assertions.assertThrows(NullPointerException.class, () -> guitar.addStrings(null));

    }

    @Test
    public void noMoreThatSixStrings() {
        Guitar guitar = Guitar.withTypeAndName(GuitarType.ACOUSTIC, "Yamaha");
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            guitar.addStrings(Set.of(
                    GuitarString.steelWithNumGauge(1, 10),
                    GuitarString.steelWithNumGauge(2, 14),
                    GuitarString.steelWithNumGauge(3, 23),
                    GuitarString.steelWithNumGauge(4, 30),
                    GuitarString.steelWithNumGauge(5, 39),
                    GuitarString.steelWithNumGauge(6, 45),
                    GuitarString.steelWithNumGauge(7, 47)
            ));
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            guitar.addStrings(Set.of(
                    GuitarString.steelWithNumGauge(1, 10),
                    GuitarString.steelWithNumGauge(2, 14),
                    GuitarString.steelWithNumGauge(3, 23),
                    GuitarString.steelWithNumGauge(4, 30),
                    GuitarString.steelWithNumGauge(5, 39)
            ));
        });
    }

    @Test
    public void shoudRemoveStrings(){
        Guitar guitar = Guitar.withTypeAndName(GuitarType.ACOUSTIC, "Yamaha");
        guitar.addStrings(Set.of(
                GuitarString.steelWithNumGauge(1, 10),
                GuitarString.steelWithNumGauge(2, 14),
                GuitarString.steelWithNumGauge(3, 23),
                GuitarString.steelWithNumGauge(4, 30),
                GuitarString.steelWithNumGauge(5, 39),
                GuitarString.steelWithNumGauge(6, 45)
        ));
        guitar.removeStrings();

        Assertions.assertTrue(guitar.getStrings().isEmpty());

    }

    @Test
    public void checkRightPlay() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));


        Guitar acousticGuitar = GuitarFactory.createAcousticWithDefaultStrings("myguitar");
        Chord g = Chord.of("G-dur", Map.of(
                1, 3,
                2, 0,
                3, 0,
                4, 0,
                5, 2,
                6, 3
        ));

        GuitarPlayer guitarPlayer = GuitarPlayer.withGuitar(acousticGuitar);

        guitarPlayer.addChord(g, 1000);

        guitarPlayer.play();

        String rightPlay = "";
        Chord currentChord = g;
        int delay = 1000;

        rightPlay += "Playing chord: " + currentChord.getName() + "\n";

        for (Map.Entry<Integer, Integer> entry : currentChord.getStringToFret().entrySet()) {
            int stringNumber = entry.getKey();
            int fret = entry.getValue();

            GuitarString realString = acousticGuitar.getStringByNumber(stringNumber);

            rightPlay += "String #" + realString.getNumber()
                    + " (" + realString.getStringType()
                    + ", gauge " + realString.getGauge() + ")"
                    + " -> Fret " + fret + "\n";
        }

        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Assertions.assertEquals(rightPlay, outputStream.toString());

        System.setOut(originalOut);
    }
}
