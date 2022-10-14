package data;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class User {
    private String name;
    private String login;
    private String password;
    private String phone;
    private Calendar birthDate;
    private List<Spent> spents = new ArrayList<>();

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
    
    public String toString() {
        return "Nome: " + name + ", Telefone: " + phone + "Nascimento: " + birthDate.get(Calendar.DATE) + "/" + birthDate.get(Calendar.MONTH) + "/" + birthDate.get(Calendar.YEAR) + ", Lista de despesas: " + spents;
    }
    
}
