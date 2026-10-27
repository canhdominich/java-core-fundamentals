public class JvmReport {
    // Module có mặt trong lớp khởi động của runtime không
    static boolean hasModule(String name) {
        return ModuleLayer.boot().findModule(name).isPresent();
    }

    // Có module trình biên dịch thì java Hello.java mới tự dịch được
    static String verdict(boolean hasCompilerModule) {
        if (hasCompilerModule) {
            return "this runtime can compile source, so java Hello.java works";
        }
        return "this runtime cannot compile source";
    }

    public static void main(String[] args) {
        System.out.println("step 1: " + System.getProperty("java.vm.name") + " / Java " + Runtime.version().feature());
        System.out.println("step 2: " + ModuleLayer.boot().modules().size() + " modules");
        System.out.println("step 3: java.base=" + hasModule("java.base") + " jdk.compiler=" + hasModule("jdk.compiler") + " jdk.jshell=" + hasModule("jdk.jshell"));
        System.out.println("step 4: " + verdict(hasModule("jdk.compiler")));
    }
}
