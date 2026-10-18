package dev.canhdominich.core.oop.abstraction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AbstractionTest {

    @BeforeEach
    void clear() {
        Shape.clearLog();
    }

    @Test
    void callersWorkWithTheAbstractTypeAndEachShapeSuppliesItsOwnMaths() {
        assertThat(AbstractionDemo.shapes()).containsExactly(
                "circle: area 3.14, perimeter 6.28", "rectangle: area 12.00, perimeter 14.00");
    }

    @Test
    void theAbstractClassConstructorRunsBeforeTheSubclassConstructorBody() {
        assertThat(AbstractionDemo.constructionOrder()).containsExactly("Shape(rectangle)", "Rectangle(2.0, 5.0)");
    }

    @Test
    void validationInTheSubclassRunsAfterTheAbstractPartExists() {
        assertThat(AbstractionDemo.invalidShape()).isEqualTo("radius must be positive: -1.0; abstract part already built: true");
    }

    @Test
    void theTemplateMethodFixesTheStepsAndTheSubclassFillsTheGaps() {
        assertThat(AbstractionDemo.csv()).isEqualTo("number,value\n1,alpha; beta\n2,gamma\n");
        assertThat(AbstractionDemo.html()).isEqualTo("<ul> <!-- 2 items -->\n  <li>a &lt; b</li>\n  <li>c</li>\n</ul>");
    }

    @Test
    void theTemplateRefusesEmptyInputForEverySubclass() {
        assertThat(AbstractionDemo.emptyExport()).isEqualTo("refused: nothing to export");
    }

    @Test
    void theCompilerProtectsTheAbstractContract() {
        assertThat(Javac.messages("B", "abstract class A { abstract void f(); } class B { A a = new A(); }"))
                .containsExactly("line 1: A is abstract; cannot be instantiated");
        assertThat(Javac.messages("B", "abstract class A { abstract void f(); } class B extends A { }"))
                .containsExactly("line 1: B is not abstract and does not override abstract method f() in A");
        assertThat(Javac.messages("A", "abstract class A { abstract void f() { } }")).containsExactly("line 1: abstract methods cannot have a body");
        assertThat(Javac.messages("A", "abstract class A { abstract static void f(); }"))
                .containsExactly("line 1: illegal combination of modifiers: abstract and static");
        assertThat(Javac.messages("B", "abstract class A { final void run() { } } class B extends A { void run() { } }"))
                .containsExactly("line 1: run() in B cannot override run() in A");
    }
}
