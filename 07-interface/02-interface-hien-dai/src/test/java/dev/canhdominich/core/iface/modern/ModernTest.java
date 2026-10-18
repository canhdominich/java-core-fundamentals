package dev.canhdominich.core.iface.modern;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ModernTest {

    @Test
    void anImplementerGetsTheDefaultMethodsForFree() {
        assertThat(ModernDemo.defaultMethodsFromTheInterface()).containsExactly("[INFO] started", "[ERROR] failed");
    }

    @Test
    void aClassCanOverrideADefaultAndStillCallTheInterfaceVersion() {
        assertThat(ModernDemo.overridingADefault()).containsExactly("app: [INFO] started", "app: [ERROR] FAILED");
    }

    @Test
    void aStaticFactoryOnTheInterfaceCreatesAnImplementation() {
        Logger silent = Logger.silent();
        silent.info("x");
        assertThat(ModernDemo.staticFactory()).startsWith("silent logger created");
    }

    @Test
    void twoUnrelatedDefaultsForceTheClassToChooseExplicitly() {
        assertThat(ModernDemo.conflictResolved()).isEqualTo("Duck walks and swims");
    }

    @Test
    void aMethodInTheSuperclassBeatsAnInterfaceDefault() {
        assertThat(ModernDemo.classWins()).isEqualTo("Goose moves (class method)");
    }

    @Test
    void aFunctionalInterfaceBuildsRulesFromSmallRules() {
        assertThat(ModernDemo.functionalInterface()).isEqualTo(
                "rule on 'java' true, on 'javascript' false, on blank false, negated on 'javascript' true");
        assertThat(StringRule.notBlank().test(null)).isFalse();
    }

    @Test
    void theCompilerReportsDefaultConflictsAndInvalidFunctionalInterfaces() {
        assertThat(Javac.messages("C", "interface A { default String f() { return \"a\"; } } interface B { default String f() { return \"b\"; } } class C implements A, B { }"))
                .containsExactly("line 1: types A and B are incompatible;");
        assertThat(Javac.messages("C", "interface I { static void s() { } } class C implements I { void g() { s(); } }"))
                .containsExactly("line 1: cannot find symbol");
        assertThat(Javac.messages("F", "@FunctionalInterface interface F { void a(); void b(); }"))
                .containsExactly("line 1: Unexpected @FunctionalInterface annotation");
    }
}
