import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;


class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("Tony", "Stark", "t.stark@myges.fr", LocalDate.now(),
                "123AhBBBBBBBgdfuyzge_è-(-è_");
    }

    public static Stream<Arguments> userProvider() {
        return Stream.of(
                Arguments.of("", "", "", null, ""),
                Arguments.of("", "Stark", "t.stark@myges.fr", LocalDate.now(),
                        "123AhBBBBBBBgdfuyzge_è-(-è_"),
                Arguments.of("Tony", "", "t.stark@myges.fr", LocalDate.now(),
                        "123AhBBBBBBBgdfuyzge_è-(-è_"),
                Arguments.of("Tony", "Stark", "", LocalDate.now(),
                        "123AhBBBBBBBgdfuyzge_è-(-è_"),
                Arguments.of("Tony", "Stark", "t.stark@myges.fr", LocalDate.now(),
                        ""),
                Arguments.of(null, "Stark", "t.stark@myges.fr", LocalDate.now(),
                        "123AhBBBBBBBgdfuyzge_è-(-è_"),
                Arguments.of("Tony", null, "t.stark@myges.fr", LocalDate.now(),
                        "123AhBBBBBBBgdfuyzge_è-(-è_"),
                Arguments.of("Tony", "Stark", null, LocalDate.now(),
                        "123AhBBBBBBBgdfuyzge_è-(-è_"),
                Arguments.of("Tony", "Stark", "t.stark@myges.fr", null,
                        "123AhBBBBBBBgdfuyzge_è-(-è_"),
                Arguments.of("Tony", "Stark", "t.stark@myges.fr", LocalDate.now(),
                        null)
        );
    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si le user contient un paramètre null ou blank, une exception est levée
     * */
    @ParameterizedTest
    @MethodSource("userProvider")
    void testUserWithNullOrBlankParameters(final String firstname, final String name, final String mail, LocalDate localDate, final String password) {
        User userProviderTest = new User(firstname, name, mail, localDate, password);
        assertThrows(ExceptionUserAndToDoList.class, userProviderTest::isValid, ExceptionMessageType.PARAMETERS_MISSING.getMessage());

    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * La fonction renvoie true si le user est valide
     * */
    @Test
    void testUserIsValid() {
        user.setDateOfBirth(LocalDate.of(LocalDate.now().getYear() - User.MAX_AGE, LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth() - 1));
        try {
            Assertions.assertTrue(user.isValid());
        } catch (ExceptionUserAndToDoList e) {
            fail("unexpected exception");
        }
    }


    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si le mail de l'utilisateur ne contient pas de @ alors une exception est levée
     * */
    @Test
    void testUserMailWithNoAtChar() {
        user.setMail("t.starkmyges.fr");
        assertThrows(ExceptionUserAndToDoList.class, () -> user.isValid(), ExceptionMessageType.EMAIL_INVALID.getMessage());
    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si le mail de l'utilisateur ne contient pas de . après le @ alors une exception est levée
     * */
    @Test
    void testUserWithMailNoDotChar() {
        user.setMail("t.stark@mygesfr");
        assertThrows(ExceptionUserAndToDoList.class, () -> user.isValid(), ExceptionMessageType.EMAIL_INVALID.getMessage());

    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si l'age de l'utilisateur est inférieur à 13 alors une exception est levée
     * */
    @Test
    void testUserNonValidCauseTooYoung() {
        user.setDateOfBirth(LocalDate.of(LocalDate.now().getYear() - User.MAX_AGE + 1, LocalDate.now().getMonthValue(),
                LocalDate.now().getDayOfMonth()));
        assertThrows(ExceptionUserAndToDoList.class, () -> user.isValid(), ExceptionMessageType.AGE_INVALID.getMessage());

    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si la taille du mot de passe est < 8 alors une exception est levée
     * */
    @Test
    void testUserPasswordWithNotEnoughLength() {
        user.setPassword("he123");
        assertThrows(ExceptionUserAndToDoList.class, () -> user.isValid(), ExceptionMessageType.PASSWORD_INVALID.getMessage());
    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si le mot de passe de l'utlisateur ne contient pas de MAJ alors une exception est levée
     * */
    @Test
    void testUserPasswordWithNotUpperCase() {
        user.setPassword("123hgdfuyzge_è-(-è_");
        assertThrows(ExceptionUserAndToDoList.class, () -> user.isValid(), ExceptionMessageType.PASSWORD_INVALID.getMessage());
    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si le mot de passe de l'utlisateur ne contient pas de MIN alors une exception est levée
     * */
    @Test
    void testUserPasswordWithNotLowerCase() {
        user.setPassword("123AAAAAAAAAAAAAAAAAA");
        assertThrows(ExceptionUserAndToDoList.class, () -> user.isValid(), ExceptionMessageType.PASSWORD_INVALID.getMessage());
    }

    /**
     * Cette fonction teste le fonctionnement suivant :
     * Si le mot de passe de l'utlisateur ne contient pas de NOMBRE alors une exception est levée
     * */
    @Test
    void testUserPasswordWithNotNumber() {
        user.setPassword("AhBBBBBBBgdfuyzge_è-(-è_");
        assertThrows(ExceptionUserAndToDoList.class, () -> user.isValid(), ExceptionMessageType.PASSWORD_INVALID.getMessage());
    }
}