package data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private String login;
    private String password;
    private String phone;
    private Calendar birthDate;
    private List<Spent> spents;

    public User(String name, String login, String password, String phone, Calendar birthDate) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
        spents = new ArrayList<>();
    }

    public User() {
        spents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
        this.birthDate = birthDate;
    }

    public List<Spent> getSpents() {
        return spents;
    }

    public void setSpents(List<Spent> spents) {
        this.spents = spents;
    }

    @Override
    public String toString() {
        return "Nome: " + name + ", Telefone: " + phone + ", Nascimento: " + birthDate.get(Calendar.DATE) + "/" + birthDate.get(Calendar.MONTH) + "/" + birthDate.get(Calendar.YEAR) + ", Lista de despesas: " + spents;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && login.equals(user.login) && password.equals(user.password) && phone.equals(user.phone) && birthDate.get(Calendar.DATE) == user.birthDate.get(Calendar.DATE) && birthDate.get(Calendar.MONTH) == user.birthDate.get(Calendar.MONTH) && birthDate.get(Calendar.YEAR) == user.birthDate.get(Calendar.YEAR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, login, password, phone, birthDate, spents);
    }
}
