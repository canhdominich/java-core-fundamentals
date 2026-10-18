package dev.canhdominich.core.pool;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class PoolTest {

    @Test
    void literalsShareOneObjectButNewStringAndRuntimeResultsDoNot() {
        assertThat(PoolIdentity.identities()).isEqualTo(
                "literal == literal true, literal == new String false, literal == intern true, literal == substring false (equals true), literal == runtime concat false, literal == runtime concat interned true");
    }

    @Test
    void methodsThatChangeNothingReturnTheSameObject() {
        assertThat(PoolIdentity.methodsThatReturnTheSameObject()).isEqualTo(
                "toString true, concat empty true, trim without spaces true, toLowerCase already lower true, replace missing char true, substring(0) true, toUpperCase false");
    }

    @Test
    void internCollapsesEqualStringsIntoOneObject() {
        assertThat(PoolIdentity.distinctObjects(1000, false)).isEqualTo(1000);
        assertThat(PoolIdentity.distinctObjects(1000, true)).isEqualTo(1);
        assertThat(PoolIdentity.distinctLiteralObjects(1000)).isEqualTo(1);
    }

    @Test
    void everyPlusEqualsCreatesANewObject() {
        assertThat(PoolIdentity.objectsCreatedByPlusEquals(500)).isEqualTo(500);
    }

    @Test
    void reassigningAVariableLeavesTheOldStringAlone() {
        assertThat(PoolIdentity.reassignNotMutate()).isEqualTo("original cats, alias cat, same object false");
    }

    @Test
    void aMutableKeyChangesItsHashAndIsLostInTheMap() {
        assertThat(WhyImmutable.mutableKeyIsLost()).isEqualTo(
                "before value, after changing the key: get(key) null, get(List.of(1, 2)) null, get(List.of(1, 2, 3)) null, size 1");
    }

    @Test
    void aStringKeyCannotChangeUnderTheMap() {
        assertThat(WhyImmutable.stringKeyIsStable()).isEqualTo("get(id-42) value, get(changed variable) null, size 1");
    }

    @Test
    void equalHashCodesDoNotMeanEqualStrings() {
        assertThat(WhyImmutable.collisionsStillWork()).isEqualTo("hash of Aa 2112, hash of BB 2112, equal strings false, lookups 1 and 2");
    }

    @Test
    void theHashCodeDependsOnlyOnTheContent() {
        assertThat(WhyImmutable.hashIsComputedFromTheContent()).isEqualTo("hashCode 99162322 and 99162322 equal true, empty string hash 0");
    }

    @Test
    void aStringBuiltFromAnArrayIsASnapshot() {
        assertThat(WhyImmutable.arrayIsMutableStringIsNot()).isEqualTo("array Lava, string made before the change java");
    }
}
