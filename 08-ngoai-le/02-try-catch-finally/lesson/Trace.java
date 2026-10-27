public class Trace {
    public static void main(String[] args) {
        String[] lines = {"cake,3", "soda,0", "water", "juice,two"};
        for (String line : lines) {
            process(line);
        }
    }

    static void process(String line) {
        try {
            String[] parts = line.split(",");
            int qty = Integer.parseInt(parts[1]);
            System.out.println(line + " total " + 300 / qty);
        } catch (NumberFormatException e) {
            System.out.println(line + " bad number");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(line + " missing quantity");
        } catch (ArithmeticException e) {
            System.out.println(line + " zero quantity");
        } finally {
            System.out.println("checked " + line);
        }
    }
}
