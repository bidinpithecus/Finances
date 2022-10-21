package data;

import java.util.Calendar;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SpentTest {
    @Test
    void testNewSpent() {
        Spent spent1 = new Spent();
        spent1.setIndex(0);
        spent1.setName("Viagem Exterior");
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, Calendar.DECEMBER,20);
        spent1.setDate(calendar);
        spent1.setDescription("Viagem ao Canadá com a família");
        spent1.setValue(40000.50F);
        spent1.setCategory(Category.LAZER);

        Calendar date = Calendar.getInstance();
        date.set(2022, Calendar.DECEMBER, 20);

        assertEquals(spent1, new Spent(0, "Viagem Exterior", date, "Viagem ao Canadá com a família", 40000.50F, Category.LAZER));
    }
}
