import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item item;

    @BeforeEach
    void set_Up4(){
        item = new Item("Name","Descrption", LocalDate.now());
    }
    public static Stream<Arguments> itemProvider() {
        return Stream.of(
                Arguments.of("", "", null),
                Arguments.of("", "Description", LocalDate.now()),
                Arguments.of("Name", "", LocalDate.now()),
                Arguments.of("Name", "Description", null)
        );
    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si l'item contient un paramètre null ou blank, une exception est levée
     */
    @ParameterizedTest
    @MethodSource("itemProvider")
    void testItemWithNullOrBlankParameters(final String name, final String description, LocalDate localDate) {
        assertThrows(IllegalArgumentException.class, ()->item.verifyParameters(name,description, localDate));

    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si l'item est valide, aucune exception est valide
     */
    @Test
    void testItemValid(){
        assertDoesNotThrow(()->item.verifyParameters(item.getName(), item.getDescription(), item.getDateCreation()));
    }
}