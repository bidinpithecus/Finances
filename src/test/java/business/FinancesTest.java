package business;

import data.User;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

class FinancesTest {
    @Test
    void newUser() {
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789", "7366504967", birthDate);

    }

    @Test
    void login() {
    }

    @Test
    void logoff() {
    }

    @Test
    void isUserLogged() {
    }

    @Test
    void newSpent() {
    }

    @Test
    void deleteSpent() {
    }

    @Test
    void listSpent() {
    }

    @Test
    void testListSpent() {
    }

    @Test
    void testListSpent1() {
    }

    @Test
    void editSpent() {
    }

    @Test
    void testEditSpent() {
    }

    @Test
    void testEditSpent1() {
    }

    @Test
    void testEditSpent2() {
    }
}
