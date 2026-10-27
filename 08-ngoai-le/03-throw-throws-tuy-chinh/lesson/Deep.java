public class Deep {
    public static void main(String[] args) {
        try {
            asIs();
        } catch (IllegalStateException e) {
            System.out.println("rethrown as is: top frame " + e.getStackTrace()[0].getMethodName());
        }
        try {
            fresh();
        } catch (IllegalStateException e) {
            System.out.println("new exception: top frame " + e.getStackTrace()[0].getMethodName() + ", cause " + e.getCause());
        }
        try {
            withCause();
        } catch (IllegalStateException e) {
            System.out.println("new with cause: top frame " + e.getStackTrace()[0].getMethodName() + ", cause frame " + e.getCause().getStackTrace()[0].getMethodName());
        }
    }

    static void lowLevel() {
        throw new IllegalStateException("low");
    }

    static void asIs() {
        try {
            lowLevel();
        } catch (IllegalStateException e) {
            // throw e: ném lại đúng đối tượng cũ, dấu vết giữ nguyên chỗ phát sinh
            throw e;
        }
    }

    static void fresh() {
        try {
            lowLevel();
        } catch (IllegalStateException e) {
            // Ném đối tượng mới không kèm cause: mất dấu vết gốc
            throw new IllegalStateException("wrapped");
        }
    }

    static void withCause() {
        try {
            lowLevel();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("wrapped", e);
        }
    }
}
