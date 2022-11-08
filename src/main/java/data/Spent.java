package data;

import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class Spent {
    private UUID index;
    private String name;
    private Calendar date;
    private String description;
    private float value;
    private Category category;

    public Spent() {
    }

    public Spent(UUID index, String name, Calendar date, String description, float value, Category category) {
        this.index = index;
        this.name = name;
        this.date = date;
        this.description = description;
        this.value = value;
        this.category = category;
    }

    public UUID getIndex() {
        return index;
    }

    public void setIndex(UUID index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "ID: " + index + ", Nome: " + name + ", Data: " + date.get(Calendar.DATE) + "/" + date.get(Calendar.MONTH) + "/" + date.get(Calendar.YEAR) + ", Description: " + description + ", Valor: " + value + ", Categoria: " + category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spent spent = (Spent) o;
        return index == spent.index && Float.compare(spent.value, value) == 0 && name.equals(spent.name) && date.get(Calendar.DATE) == spent.date.get(Calendar.DATE) && date.get(Calendar.MONTH) == spent.date.get(Calendar.MONTH) && date.get(Calendar.YEAR) == spent.date.get(Calendar.YEAR) && description.equals(spent.description) && category == spent.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, name, date, description, value, category);
    }
}