package business;

import data.*;
import java.util.*;

public class Finances {
    private final Map<User, List<Spent>> users = new HashMap<>();
    private User logged = null;

    public boolean newUser(User user) {
        if (getUserByUsername(user.getLogin()) == null) {
            List<Spent> spents = new ArrayList<>();
            users.put(user, spents);
            return true;
        }
        return false;
    }

    // https://www.baeldung.com/java-password-hashing
    // for this first part of the work, it will be going to be the simplest login system
    // with the database integration, a better a safer system is going to be implemented
    // in memory won't be as efficient
    public boolean login(String login, char[] password) {
        for (User user : users.keySet()) {
            if (user.getLogin().equals(login) && Arrays.equals(user.getPassword(), password)) {
                logged = user;
                return true;
            }
        }
        return false;
    }

    public boolean logoff() {
        if (isUserLogged()) {
            logged = new User();
            return true;
        }
        return false;
    }

    public User getLogged() {
        return logged;
    }

    public boolean confirmedUser(String name, String login, String phone, Calendar birthDate) {
        User user = getUserByUsername(login);
        if (user != null) {
            boolean isBirthDateSame = user.getBirthDate().get(Calendar.DATE) == birthDate.get(Calendar.DATE) && user.getBirthDate().get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && user.getBirthDate().get(Calendar.YEAR) == birthDate.get(Calendar.YEAR);
            boolean isNameSame = user.getName().equals(name);
            boolean isLoginSame = user.getLogin().equals(login);
            boolean isPhoneSame = user.getPhone().equals(phone);
            if (isNameSame && isLoginSame && isPhoneSame && isBirthDateSame) {
                logged = user;
                return true;
            }
            return false;
        }
        return false;
    }

    public User getUserByUsername(String username) {
        for (Map.Entry<User, List<Spent>> user : users.entrySet()) {
            if (user.getKey().getLogin().equals(username)) {
                return user.getKey();
            }
        }
        return null;
    }

    public boolean changePassword(String name, String login, String phone, Calendar birthDate, char[] newPassword) {
        if (confirmedUser(name, login, phone, birthDate)) {
            logged.setPassword(newPassword);
            users.put(logged, users.remove(logged));
            return true;
        } else {
            return false;
        }
    }

    public boolean isUserLogged() {
        if (logged == null) return false;
        else {
            return logged.getLogin() != null;
        }
    }

    public boolean newSpent(Spent spent) {
        if (isUserLogged()) {
            users.get(logged).add(spent);
            return true;
        }
        return false;
    }

    public boolean spentExists(UUID id) {
        if (!isUserLogged()) {
            return true;
        }
        for (Spent spent : listSpent()) {
            if (spent.getIndex().equals(id)) {
                return false;
            }
        }
        return true;
    }

    public Spent getSpentById(UUID id) {
        if (!isUserLogged()) {
            return null;
        }
        if (spentExists(id)) {
            return null;
        }
        for (Spent spent : listSpent()) {
            if (spent.getIndex().equals(id)) {
                return spent;
            }
        }
        return null;
    }

    public boolean deleteSpent(UUID id) {
        if (isUserLogged()) {
            if (spentExists(id)) {
                return false;
            }
            users.get(logged).remove(getSpentById(id));
            return true;
        }
        return false;
    }

    public List<Spent> listSpent() {
        return users.get(logged);
    }

    public List<Spent> listSpent(int month) {
        if (isUserLogged()) {
            List<Spent> list = new ArrayList<>();
            for (Spent spent : users.get(logged)) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(spent.getDate().getTime());
                if (calendar.get(Calendar.MONTH) == month) {
                    list.add(spent);
                }
            }
            return list;
        }
        return Collections.emptyList();
    }

    public List<Spent> listSpent(Category category) {
        if (isUserLogged()) {
            List<Spent> list = new ArrayList<>();
            for (Spent spent : users.get(logged)) {
                if (spent.getCategory().equals(category)) {
                    list.add(spent);
                }
            }
            return list;
        }
        return Collections.emptyList();
    }

    // By string, change either the name or description
    public boolean editSpent(UUID id, int property, String content) {
        if (isUserLogged()) {
            if (spentExists(id)) {
                return false;
            }
            if (property == 1) {
                users.get(logged).get(listSpent().indexOf(getSpentById(id))).setName(content);
            } else if (property == 2) {
                users.get(logged).get(listSpent().indexOf(getSpentById(id))).setDescription(content);
            } else {
                return false;
            }
            return true;
        }
        return true;
    }

    public boolean editSpent(UUID id, Calendar date) {
        if (isUserLogged()) {
            if (spentExists(id)) {
                return false;
            }
            users.get(logged).get(listSpent().indexOf(getSpentById(id))).setDate(date);
            return true;
        }
        return false;
    }

    public boolean editSpent(UUID id, float value) {
        if (isUserLogged()) {
            if (spentExists(id)) {
                return false;
            }
            users.get(logged).get(listSpent().indexOf(getSpentById(id))).setValue(value);
            return true;
        }
        return false;
    }

    public boolean editSpent(UUID id, Category category) {
        if (isUserLogged()) {
            if (spentExists(id)) {
                return false;
            }
            users.get(logged).get(listSpent().indexOf(getSpentById(id))).setCategory(category);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Finances{" +
                "users=" + users +
                '}';
    }
}
