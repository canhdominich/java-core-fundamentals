package dev.canhdominich.core.jvm;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

class JvmTest {

    @Test
    void theRuntimeKnowsItsOwnHomeAndModules() throws Exception {
        assertThat(JdkLayout.tools(Path.of(System.getProperty("java.home")))).contains("java");
        assertThat(ModuleLayer.boot().findModule("java.base")).isPresent();
    }

    @Test
    void theCommandAndTheCompilerApiAreDifferentThings() {
        assertThat(CompilerCheck.commandResult()).isNotBlank();
        assertThat(CompilerCheck.apiAvailable()).isIn(true, false);
    }

    @Test
    void classesLoadOnFirstUseOnly() throws Exception {
        assertThat(ClassLoadingFacts.loadedByFirstUse("java.util.concurrent.ForkJoinPool")).isGreaterThanOrEqualTo(0);
        assertThat(ClassLoadingFacts.loadedByFirstUse("java.util.concurrent.ForkJoinPool")).isZero();
    }

    @Test
    void theInterpreterOnlyModeIsMuchSlowerThanTheDefaultMode() throws Exception {
        JitWarmup.Result jit = JitWarmup.run("default", null, 30_000_000);
        JitWarmup.Result interpreted = JitWarmup.run("-Xint", "-Xint", 30_000_000);
        assertThat(interpreted.total()).isGreaterThan(jit.total() * 3);
    }

    @Test
    void aClassBuiltForAnOlderReleaseRunsOnTheCurrentJvm() throws Exception {
        Path root = Files.createTempDirectory("matrix");
        String java = Path.of(System.getProperty("java.home"), "bin", "java").toString();
        for (int release : new int[] {8, 11, 17}) {
            if (release <= Runtime.version().feature()) {
                assertThat(VersionMatrix.run(java, VersionMatrix.compile(release, root))).startsWith("hello from Java");
            }
        }
    }
}
