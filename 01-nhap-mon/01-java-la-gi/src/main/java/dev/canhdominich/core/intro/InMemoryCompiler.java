package dev.canhdominich.core.intro;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public final class InMemoryCompiler {

    private InMemoryCompiler() {
    }

    public static byte[] compile(String className, String source, int release) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("This runtime has no compiler module (jdk.compiler): install a JDK");
        }
        StandardJavaFileManager standard = compiler.getStandardFileManager(null, null, null);
        Map<String, ByteArrayOutputStream> output = new HashMap<>();
        JavaFileObject unit = new SimpleJavaFileObject(URI.create("string:///" + className + ".java"), JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return source;
            }
        };
        ForwardingJavaFileManager<StandardJavaFileManager> manager = new ForwardingJavaFileManager<>(standard) {
            @Override
            public JavaFileObject getJavaFileForOutput(Location location, String name, JavaFileObject.Kind kind, FileObject sibling) {
                return new SimpleJavaFileObject(URI.create("mem:///" + name + ".class"), kind) {
                    @Override
                    public OutputStream openOutputStream() {
                        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                        output.put(name, bytes);
                        return bytes;
                    }
                };
            }
        };
        boolean ok = compiler.getTask(null, manager, null, List.of("--release", String.valueOf(release), "-Xlint:-options"), null, List.of(unit)).call();
        if (!ok) {
            throw new IllegalStateException("compilation failed for release " + release);
        }
        return output.get(className).toByteArray();
    }
}
