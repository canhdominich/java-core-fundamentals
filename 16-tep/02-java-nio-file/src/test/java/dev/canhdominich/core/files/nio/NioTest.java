package dev.canhdominich.core.files.nio;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class NioTest {

    @TempDir
    Path tmp;

    @Test
    void aPathIsJustNamesAndNeedsNoFile() {
        assertThat(PathOps.parts()).isEqualTo("path data/reports/out.txt, file name out.txt, parent data/reports, name count 3, absolute false, root null");
        assertThat(PathOps.pathDoesNotNeedToExist()).isEqualTo("path built no/such/place.txt, exists false, notExists true");
    }

    @Test
    void resolveRelativizeAndNormalizeBuildPaths() {
        assertThat(PathOps.resolveAndRelativize()).isEqualTo("resolve /srv/app/logs/today.log, relativize ../../config/app.yml, resolve with an absolute path gives /etc/hosts");
        assertThat(PathOps.normalize()).isEqualTo("normalize /srv/app/config/app.yml, same without normalizing false");
    }

    @Test
    void normalizeBeforeStartsWithStopsPathTraversal() {
        assertThat(PathOps.traversal()).isEqualTo("report.pdf inside true, ../../etc/passwd inside false, /etc/passwd inside false, naive startsWith on the raw text true");
    }

    @Test
    void wholeFileOperationsRoundTripText() throws Exception {
        assertThat(Quick.wholeFile(tmp)).isEqualTo("readString 17 characters, readAllLines [Hello, Café, Line 3], size 18 bytes");
    }

    @Test
    void openOptionsControlOverwriteAndAppend() throws Exception {
        assertThat(Quick.createNewRefusesToOverwrite(tmp)).isEqualTo("second CREATE_NEW threw FileAlreadyExistsException, content still first");
        assertThat(Quick.appendVersusTruncate(tmp)).isEqualTo("default write replaces: b, with APPEND: [b, c]");
    }

    @Test
    void errorsAreSpecificExceptions() throws Exception {
        assertThat(Quick.missingFile(tmp)).isEqualTo("NoSuchFileException with the path in the message true");
        assertThat(Quick.badUtf8IsRejected(tmp)).isEqualTo("readString threw MalformedInputException: Input length = 1, decoding the same bytes by hand gives 'Caf?'");
    }

    @Test
    void linesStreamReadsOnlyAsFarAsNeeded() throws Exception {
        assertThat(Quick.linesAreLazy(tmp)).isEqualTo("100000 lines, ERROR lines 4, first match 'ERROR failure number 25000' found after reading 25000 lines");
    }

    @Test
    void directoriesCopyMoveAndDelete() throws Exception {
        assertThat(Tree.createDirectories(tmp)).isEqualTo("createDirectories built the chain, second call did not fail, isDirectory true");
        assertThat(Tree.copyAndMove(tmp)).isEqualTo("second copy without REPLACE_EXISTING threw FileAlreadyExistsException, after move the old path exists false and the new path holds payload");
        assertThat(Tree.deleteBehaviour(tmp)).isEqualTo("delete on a missing file threw NoSuchFileException, deleteIfExists returned false, delete on a non-empty directory threw DirectoryNotEmptyException");
    }

    @Test
    void walkListsTheWholeTreeAndAtomicMoveReplacesAFile() throws Exception {
        assertThat(Tree.walkAndFilter(tmp)).isEqualTo("java files [src/main/App.java, src/main/util/Helper.java], Files.list shows only the first level [App.java, util]");
        assertThat(Tree.atomicReplace(tmp)).isEqualTo("after the swap config holds {\"version\":2}, temp file still exists false");
    }
}
