package JCF;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Person {
    private String name;
    private String surname;
    private Integer age;

    public void Hello() {
        System.out.println("Hello im " + name + " " + surname + "!");
    }

    public int hashCode() {
        int code = ((int) name.charAt(0)) + ((int) surname.charAt(0)) + age;
        code %= 10;

        System.out.println(code);

        return code;
    }

    public boolean equals(Person person) {
        System.out.println("equals used");
        if (!this.name.equals(person.name))
            return false;
        if (!this.surname.equals(person.surname))
            return false;
        if (!this.age.equals(person.age))
            return false;
        return true;
    }
}
