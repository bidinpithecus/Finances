package data;

import java.util.Calendar;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
	@Test
	void testNewUser() {
		User user1 = new User();
		user1.setName("Moyses Marinus");
		user1.setPhone("7366504967");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, Calendar.OCTOBER,20);
		user1.setBirthDate(calendar);
		user1.setLogin("moysesMarinus");
		user1.setPassword("qweasdzxc123456789");

		Calendar birthDate = Calendar.getInstance();
		birthDate.set(2000, Calendar.OCTOBER, 20);

		assertEquals(user1, new User("Moyses Marinus", "moysesMarinus", "qweasdzxc123456789", "7366504967", birthDate));
	}
}
