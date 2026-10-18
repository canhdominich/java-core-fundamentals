package dev.canhdominich.core.conditions;

public class IfPitfalls {

    public static String grade(int score) {
        if (score >= 80) {
            return "A";
        } else if (score >= 65) {
            return "B";
        } else if (score >= 50) {
            return "C";
        } else {
            return "F";
        }
    }

    public static String wrongOrder(int score) {
        if (score >= 50) {
            return "C";
        } else if (score >= 65) {
            return "B";
        } else if (score >= 80) {
            return "A";
        }
        return "F";
    }

    public static String danglingElse(boolean a, boolean b) {
        String result = "none";
        if (a)
            if (b)
                result = "a and b";
        else
            result = "belongs to the inner if";
        return result;
    }

    public static String assignmentInCondition(boolean flag) {
        if (flag = true) {
            return "entered, flag is now " + flag;
        }
        return "skipped";
    }

    public static boolean stringsWithEqualsSigns(String typed) {
        return typed == "yes";
    }

    public static void main(String[] args) {
        for (int score : new int[] {95, 80, 79, 65, 50, 49}) {
            System.out.println("score " + score + " -> grade " + grade(score) + ", with the conditions in the wrong order " + wrongOrder(score));
        }
        System.out.println("danglingElse(true, false)  = " + danglingElse(true, false));
        System.out.println("danglingElse(false, false) = " + danglingElse(false, false));
        System.out.println("assignmentInCondition(false) = " + assignmentInCondition(false));
        String typed = new String("yes");
        System.out.println("typed == \"yes\"      = " + stringsWithEqualsSigns(typed) + ", equals = " + typed.equals("yes"));
        System.out.println("0.1 + 0.2 == 0.3     = " + (0.1 + 0.2 == 0.3));
    }
}
