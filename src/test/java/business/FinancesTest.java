package business;

import data.Category;
import data.Spent;
import data.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class FinancesTest {
    @Test
    void newUserShouldReturnTrue() {
        Finances finances = new Finances();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);

        assertTrue(finances.newUser(user));
    }

    @Test
    void newUserShouldReturnFalse() {
        Finances finances = new Finances();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);
        User user2 = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);

        assertFalse(finances.newUser(user2));
    }

    @Test
    void loginShouldReturnTrue() {
        Finances finances = new Finances();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);

        assertTrue(finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray()));
    }

    @Test
    void loginWithWrongLoginShouldReturnFalse() {
        Finances finances = new Finances();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);

        assertFalse(finances.login("moyses", "qweasdzxc123456789".toCharArray()));
    }

    @Test
    void loginWithWrongPasswordShouldReturnFalse() {
        Finances finances = new Finances();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);

        assertFalse(finances.login("moysesMarinus", "moysesMarinus".toCharArray()));
    }

    @Test
    void isUserLoggedShouldReturnTrue() {
        Finances finances = new Finances();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);
        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());

        assertTrue(finances.isUserLogged());
    }

    @Test
    void isUserLoggedShouldReturnFalse() {
        Finances finances = new Finances();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);
        finances.login("moysesMarinus", "asda".toCharArray());

        assertFalse(finances.isUserLogged());
    }

    @Test
    void logoffShouldReturnTrue() {
        Finances finances = new Finances();
        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);
        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());

        assertTrue(finances.logoff());
    }

    @Test
    void logoffWithUserNotLoggedShouldReturnFalse() {
        Finances finances = new Finances();

        assertFalse(finances.logoff());
    }

    @Test
    void newSpentShouldReturnTrue() {
        Finances finances = new Finances();

        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);
        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        UUID uuid = UUID.randomUUID();
        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.DECEMBER, 20);
        Spent spent = new Spent(uuid, "Viagem Exterior", date, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        assertTrue(finances.newSpent(spent));
    }

    @Test
    void newSpentWithUserNotLoggedShouldReturnFalse() {
        Finances finances = new Finances();

        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.DECEMBER, 20);
        UUID uuid = UUID.randomUUID();
        Spent spent = new Spent(uuid, "Viagem Exterior", date, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);
        assertFalse(finances.newSpent(spent));
    }

    @Test
    void deleteSpentShouldReturnTrue() {
        Finances finances = new Finances();

        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);
        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());

        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.DECEMBER, 20);
        UUID uuid = UUID.randomUUID();
        Spent spent = new Spent(uuid, "Viagem Exterior", date, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newSpent(spent);

        assertTrue(finances.deleteSpent(0));
    }

    @Test
    void deleteSpentOfInvalidIndex() {
        Finances finances = new Finances();

        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);
        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());

        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.DECEMBER, 20);
        UUID uuid = UUID.randomUUID();
        Spent spent = new Spent(uuid, "Viagem Exterior", date, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newSpent(spent);

        assertFalse(finances.deleteSpent(10));
    }

    @Test
    void deleteSpentWithUserNotLoggedShouldReturnFalse() {
        Finances finances = new Finances();

        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.DECEMBER, 20);
        UUID uuid = UUID.randomUUID();
        Spent spent = new Spent(uuid, "Viagem Exterior", date, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newSpent(spent);

        assertFalse(finances.deleteSpent(1));
    }

    @Test
    void deleteNonExistentSpentShouldReturnFalse() {
        Finances finances = new Finances();

        Calendar birthDate = Calendar.getInstance();
        birthDate.set(2000, Calendar.OCTOBER, 20);
        User user = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDate);
        finances.newUser(user);
        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());

        assertFalse(finances.deleteSpent(0));
    }

    @Test
    void listEverySpentShouldReturnList() {
        Finances finances = new Finances();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar birthDateSheila = Calendar.getInstance();
        birthDateSheila.set(1998, Calendar.AUGUST, 15);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);
        UUID uuid = UUID.randomUUID();
        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);
        User sheila = new User("Sheila Koant", "sheilaKoant", "12345".toCharArray(), "9368299072", birthDateSheila);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);
        finances.newUser(sheila);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        List<Spent> listOfSpents = new ArrayList<>();
        listOfSpents.add(spent);

        assertEquals(listOfSpents, finances.listSpent());
    }

    @Test
    void listEverySpentShouldReturnEmptyList() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar birthDateSheila = Calendar.getInstance();
        birthDateSheila.set(1998, Calendar.AUGUST, 15);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);
        User sheila = new User("Sheila Koant", "sheilaKoant", "12345".toCharArray(), "9368299072", birthDateSheila);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);
        finances.newUser(sheila);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        finances.logoff();
        finances.login("sheilaKoant", "12345".toCharArray());

        List<Spent> listOfSpents = new ArrayList<>();

        assertEquals(listOfSpents, finances.listSpent());
    }

    @Test
    void listSpentByMonthShouldReturnEmptyList() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        List<Spent> listOfSpents = new ArrayList<>();

        assertEquals(listOfSpents, finances.listSpent(Calendar.NOVEMBER));
    }

    @Test
    void listSpentByMonthShouldReturnList() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        List<Spent> listOfSpents = new ArrayList<>();
        listOfSpents.add(spent);

        assertEquals(listOfSpents, finances.listSpent(Calendar.DECEMBER));
    }

    @Test
    void listSpentByCategoryShouldReturnEmptyList() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        List<Spent> listOfSpents = new ArrayList<>();

        assertEquals(listOfSpents, finances.listSpent(Category.FOOD));
    }

    @Test
    void listSpentByCategoryShouldReturnList() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        List<Spent> listOfSpents = new ArrayList<>();
        listOfSpents.add(spent);

        assertEquals(listOfSpents, finances.listSpent(Category.FUN));
    }

    @Test
    void editSpentByPropertyNameShouldReturnTrue() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        assertTrue(finances.editSpent(0, 1, "Viagem Canada"));
    }

    @Test
    void editedSpentByPropertyNameShouldBeEqual() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);
        finances.editSpent(uuid, 1, "Viagem Canadá");

        spent.setName("Viagem Canadá");

        assertEquals(spent, finances.listSpent().get(0));
    }

    @Test
    void editSpentByPropertyDescriptionShouldReturnTrue() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        assertTrue(finances.editSpent(0, 2, "Viagem à Europa com a família"));
    }

    @Test
    void editedSpentByPropertyDescriptionShouldBeEqual() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);
        finances.editSpent(0, 2, "Viagem à Europa com a família");

        spent.setDescription("Viagem à Europa com a família");

        assertEquals(spent, finances.listSpent().get(0));
    }

    @Test
    void editSpentByPropertyUnknownShouldReturnFalse() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        assertFalse(finances.editSpent(0, 3, "Viagem à Europa com a família"));
    }

    @Test
    void editSpentByDateShouldReturnTrue() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        Calendar newDate = Calendar.getInstance();
        newDate.set(2023, Calendar.JANUARY, 5);

        assertTrue(finances.editSpent(0, newDate));
    }

    @Test
    void editedSpentByDateShouldBeEqual() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        Calendar newDate = Calendar.getInstance();
        newDate.set(2023, Calendar.JANUARY, 5);

        finances.editSpent(0, newDate);
        spent.setDate(newDate);

        assertEquals(spent, finances.listSpent().get(0));
    }

    @Test
    void editSpentByValueShouldReturnTrue() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        assertTrue(finances.editSpent(0, 35000F));
    }

    @Test
    void editedSpentByValueShouldBeEqual() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        finances.editSpent(0, 35000F);
        spent.setValue(35000F);

        assertEquals(spent, finances.listSpent().get(0));
    }

    @Test
    void editSpentByCategoryShouldReturnTrue() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        assertTrue(finances.editSpent(0, Category.ANOTHER));
    }

    @Test
    void editedSpentByCategoryShouldBeEqual() {
        Finances finances = new Finances();
        UUID uuid = UUID.randomUUID();

        Calendar birthDateMoyses = Calendar.getInstance();
        birthDateMoyses.set(2000, Calendar.OCTOBER, 20);
        Calendar dateSpent = Calendar.getInstance();
        dateSpent.set(2022, Calendar.DECEMBER, 20);

        User moyses = new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789".toCharArray(), "7366504967", birthDateMoyses);

        Spent spent = new Spent(uuid, "Viagem Exterior", dateSpent, "Viagem ao Canadá com a família", 40000.50F, Category.FUN);

        finances.newUser(moyses);

        finances.login("moysesMarinus", "qweasdzxc123456789".toCharArray());
        finances.newSpent(spent);

        finances.editSpent(0, Category.ANOTHER);
        spent.setCategory(Category.ANOTHER);

        assertEquals(spent, finances.listSpent().get(0));
    }

}
