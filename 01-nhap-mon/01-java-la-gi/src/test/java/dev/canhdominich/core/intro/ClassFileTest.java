package dev.canhdominich.core.intro;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import org.junit.jupiter.api.Test;

class ClassFileTest {

    @Test
    void everyClassFileStartsWithCafebabe() {
        for (int release : new int[] {8, 11, 17}) {
            ClassFileHeader header = ClassFileHeader.parse(InMemoryCompiler.compile("Greeter", Sources.GREETER, release));
            assertThat(header.magicHex()).isEqualTo("CAFEBABE");
        }
    }

    @Test
    void theMajorVersionFollowsTheRelease() {
        assertThat(ClassFileHeader.parse(InMemoryCompiler.compile("Greeter", Sources.GREETER, 8)).major()).isEqualTo(52);
        assertThat(ClassFileHeader.parse(InMemoryCompiler.compile("Greeter", Sources.GREETER, 11)).major()).isEqualTo(55);
        assertThat(ClassFileHeader.parse(InMemoryCompiler.compile("Greeter", Sources.GREETER, 17)).major()).isEqualTo(61);
    }

    @Test
    void anOlderClassFileRunsOnANewerJvm() {
        assertThat(TooNewBytecode.tryLoad(52)).startsWith("loaded Greeter");
    }

    @Test
    void aNewerClassFileIsRefusedByAnOlderJvm() {
        int tooNew = Runtime.version().feature() + 45;
        assertThat(TooNewBytecode.tryLoad(tooNew))
                .startsWith("UnsupportedClassVersionError")
                .contains("class file version " + tooNew + ".0")
                .contains("up to " + (tooNew - 1) + ".0");
    }

    @Test
    void javacIsPartOfTheJdk() {
        assertThat(javax.tools.ToolProvider.getSystemJavaCompiler()).isNotNull();
    }

    @Test
    void theSameSourceCompilesToDifferentBytecodeForDifferentTargets() throws IOException {
        assertThat(BytecodePeek.disassemble(8)).anyMatch(line -> line.contains("java/lang/StringBuilder"));
        assertThat(BytecodePeek.disassemble(17)).anyMatch(line -> line.contains("makeConcatWithConstants"));
    }
}
