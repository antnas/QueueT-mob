package projektor.projekt;

import java.util.List;
import org.springframework.data.annotation.Id;

class Person {

  @Id
  private Long id;
  private final String name;

  public Person(String name) {
    this.name = name;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Person{" +
        "name='" + name + '\'' +
        '}';
  }
}
