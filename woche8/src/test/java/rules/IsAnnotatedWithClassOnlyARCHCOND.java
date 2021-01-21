package rules;

import static com.tngtech.archunit.lang.SimpleConditionEvent.satisfied;
import static com.tngtech.archunit.lang.SimpleConditionEvent.violated;

import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import java.util.Set;

public class IsAnnotatedWithClassOnlyARCHCOND extends ArchCondition<JavaMethod> {

  public static final IsAnnotatedWithClassOnlyARCHCOND IS_ANNOTATED_WITH_CLASSONLY =
      new IsAnnotatedWithClassOnlyARCHCOND("are annotated with ClassOnly");

  IsAnnotatedWithClassOnlyARCHCOND(String description, Object... args) {
    super(description, args);
  }

  public void check(JavaMethod method, ConditionEvents events) {
    boolean found = false;
    Set<JavaAnnotation<JavaMethod>> annotations = method.getAnnotations();
    for(JavaAnnotation<JavaMethod> annotation : annotations) {
      if (annotation.getRawType().getName().equals("ClassOnly")) {
        found = true;
      }
    }
    if(found) events.add(satisfied(method, "ok!"));
    else events.add(violated(method," is not annotated with ClassOnly"));
  }
}