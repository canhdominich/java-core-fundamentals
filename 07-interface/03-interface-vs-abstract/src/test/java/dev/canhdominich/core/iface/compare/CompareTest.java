package dev.canhdominich.core.iface.compare;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class CompareTest {

    @Test
    void anAbstractClassKeepsStateAndAppliesSharedRulesToEverySubclass() {
        assertThat(CompareDemo.skeletalClass()).isEqualTo(
                "size 2, writes 2, isEmpty false, blank key refused: key must not be blank, writes still 2");
    }

    @Test
    void decoratorsWrapAnyStorageThroughTheInterface() {
        assertThat(CompareDemo.decoratorsOnlyNeedTheInterface()).isEqualTo(
                "value v, audit [put k, get k], read-only wrapper: storage is read-only, real storage size 1");
    }

    @Test
    void aClassHasOneParentClassButManyInterfaces() {
        assertThat(CompareDemo.oneParentManyInterfaces()).isEqualTo(
                "AuditedStorage is Storage true, Auditable true, AbstractStorage false; MapStorage is AbstractStorage true, Auditable false");
    }

    @Test
    void theDefaultMethodGivesAnEmptyStorageCheckForFree() {
        Storage storage = new MapStorage();
        assertThat(storage.isEmpty()).isTrue();
        storage.put("a", "1");
        assertThat(storage.isEmpty()).isFalse();
        assertThat(new ReadOnlyStorage(storage).get("a")).isEqualTo("1");
    }

    @Test
    void addingAnAbstractMethodBreaksImplementersButAddingADefaultDoesNot() {
        assertThat(Javac.messages("Old", "interface Storage2 { void put(String k); void remove(String k); } class Old implements Storage2 { public void put(String k) { } }"))
                .containsExactly("line 1: Old is not abstract and does not override abstract method remove(java.lang.String) in Storage2");
        assertThat(Javac.messages("Old", "interface Storage2 { void put(String k); default void remove(String k) { } } class Old implements Storage2 { public void put(String k) { } }"))
                .isEmpty();
    }

    @Test
    void interfacesCannotHoldInstanceStateOrNonPublicMembers() {
        assertThat(Javac.messages("A", "interface I { int count = 0; } class A implements I { void inc() { count++; } }"))
                .containsExactly("line 1: cannot assign a value to final variable count");
        assertThat(Javac.messages("I", "interface I { protected void f(); }")).containsExactly("line 1: modifier protected not allowed here");
    }
}
