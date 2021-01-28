package covidtracer;

import static com.tngtech.archunit.lang.SimpleConditionEvent.satisfied;
import static com.tngtech.archunit.lang.SimpleConditionEvent.violated;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noMethods;
import static rules.IsAnnotatedWithClassOnlyARCHCOND.IS_ANNOTATED_WITH_CLASSONLY;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaAnnotation;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaMethodCall;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.syntax.elements.GivenClass;
import covidtracer.stereotypes.ClassOnly;
import covidtracer.stereotypes.Mutable;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

// @AnalyzeClasses(packages = {"covidtracer.controller", "covidtracer.kontaktliste", "covidtracer.persistence", "covidtracer.service", "covidtracer.stereotypes"}
@AnalyzeClasses(packagesOf = CovidtracerApplication.class)
public class ArchitectureTests {

  @Mutable static DescribedPredicate<JavaMethod> areNotAnnotatedWithClassOnly =
      new DescribedPredicate<>("is annotated with ClassOnly"){
        @Override
        public boolean apply(JavaMethod method) {
          Set<JavaAnnotation<JavaMethod>> annotations = method.getAnnotations();
          for(JavaAnnotation<JavaMethod> annotation : annotations) {
            if (annotation.getRawType().getName().equals("ClassOnly")) return false;
          }
          return true;
        }
      };

  @ArchTest
  static final ArchRule noDeprecatedMethods =  methods()
      .should()
      .notBeAnnotatedWith(Deprecated.class);

  @ArchTest
  static final ArchRule getNoDeprecatedClasses = classes()
      .should()
      .notBeAnnotatedWith(Deprecated.class);

  @ArchTest
  static final ArchRule fieldsNotFinalAreMutable = fields()
      .that()
      .areNotFinal()
      .should()
      .beAnnotatedWith(Mutable.class);

  @ArchTest
  static final ArchRule methodClassOnly = methods()
      .that()
      .areAnnotatedWith(ClassOnly.class)
      .should(new ArchCondition<JavaMethod>("description") {
        @Override
        public void check(JavaMethod item, ConditionEvents events) {
          boolean outsideMethodCall = false;
          Set<JavaMethodCall> accessesToSelf = item.getAccessesToSelf();
          String classCalling = item.getOwner().getName();
          String methodOwner = "";

          for(JavaMethodCall methodCall: accessesToSelf){
            if(!item.getOwner().getName().equals(methodCall.getOriginOwner().getName())) {
              outsideMethodCall = true;
              methodOwner = item.getOwner().getName();
              classCalling = methodCall.getOriginOwner().getName();
            }
          }

          if(outsideMethodCall){
            events.add(violated(item, "method " + item.getName() + " from class " + methodOwner + "  called from class " + classCalling));
          }else{
            events.add(satisfied(item, "ok"));
          }
        }
      });






}
