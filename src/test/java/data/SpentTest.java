package data;

import java.util.Calendar;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpentTest {
    @Test
    void testNewSpent() {
        Spent spent1 = new Spent();
        UUID uuid = UUID.randomUUID();
        spent1.setIndex(uuid);
        spent1.setName("Viagem Exterior");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.DECEMBER,20);
        spent1.setDate(calendar);
        spent1.setDescription("Viagem ao Canadá com a família");
        spent1.setValue(40000.50F);
        spent1.setCategory(Category.FUN);

        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.DECEMBER, 20);

        assertEquals(spent1, new Spent(uuid, "Viagem Exterior", date, "Viagem ao Canadá com a família", 40000.50F, Category.FUN));
    }
}
