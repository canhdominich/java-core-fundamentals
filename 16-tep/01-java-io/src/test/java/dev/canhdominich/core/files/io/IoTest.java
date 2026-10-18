package dev.canhdominich.core.files.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class IoTest {

    @TempDir
    Path tmp;

    private File dir() {
        return tmp.toFile();
    }

    @Test
    void fileObjectOnlyDescribesAPathAndReportsFailureWithBooleans() throws Exception {
        assertThat(FileBasics.describe(dir())).startsWith("exists before false, createNewFile true, again false, exists after true, length 0, name data.txt").endsWith("delete true, delete again false");
    }

    @Test
    void mkdirNeedsTheParentAndMkdirsCreatesTheChain() {
        assertThat(FileBasics.mkdirVersusMkdirs(dir())).isEqualTo("mkdir on a missing parent false, mkdirs true, isDirectory true");
    }

    @Test
    void deletingANonEmptyDirectoryFailsSilently() throws Exception {
        assertThat(FileBasics.deleteNonEmptyDirectory(dir())).isEqualTo("delete a directory that still holds a file returns false, directory still exists true, no exception was thrown");
    }

    @Test
    void listingCanFilterFilesFromDirectories() throws Exception {
        assertThat(FileBasics.listing(dir())).isEqualTo("list sorted [a.txt, b.txt, sub], files only 2");
    }

    @Test
    void writeOverwritesUnlessAppendIsRequested() throws Exception {
        assertThat(TextFiles.overwriteVersusAppend(dir())).isEqualTo("after a second write without append 1 line, after appending [three, four, five]");
    }

    @Test
    void readingWithTheWrongCharsetGarblesText() throws Exception {
        assertThat(TextFiles.charsetMatters(dir())).isEqualTo("4 characters took 5 bytes, read as UTF-8 'Café', read as ISO-8859-1 'CafÃ©'");
    }

    @Test
    void bufferedDataReachesTheFileOnlyAfterFlushOrClose() throws Exception {
        assertThat(TextFiles.bufferingHidesData(dir())).isEqualTo("file length after write but before flush 0, after flush 5");
        assertThat(TextFiles.writeAfterClose(dir())).isEqualTo("IOException: Stream closed");
    }

    @Test
    void endOfFileIsNullAndMissingFilesThrow() throws Exception {
        assertThat(TextFiles.endOfFile(dir())).isEqualTo("first readLine only, second readLine null");
        assertThat(TextFiles.missingFile(dir())).startsWith("FileNotFoundException: <dir>").contains("missing.txt");
    }

    @Test
    void blockCopyNeedsFarFewerReadCallsThanByteCopy() throws Exception {
        assertThat(BinaryFiles.copyBlocksVersusBytes(dir())).isEqualTo("copied 10000 bytes with a 4096 byte buffer in 4 read calls, one byte at a time took 10001 read calls, both copies identical true");
        assertThat(BinaryFiles.readReturnsByteAsInt()).isEqualTo("the byte value as Java sees it -56, what read() returns 200, end of data -1");
        assertThat(TextFiles.readLines(new File(dir(), "none.txt").exists() ? new File(dir(), "none.txt") : writeEmpty(), StandardCharsets.UTF_8)).isEmpty();
    }

    private File writeEmpty() throws Exception {
        File f = new File(dir(), "empty.txt");
        TextFiles.write(f, false);
        return f;
    }
}
