package dev.canhdominich.core.regex;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexDemo {

    public static String syntaxErrors(String regex) {
        try {
            Pattern.compile(regex);
            return "valid";
        } catch (PatternSyntaxException e) {
            return e.getDescription() + " near index " + e.getIndex();
        }
    }

    public static void main(String[] args) {
        System.out.println("three questions    = " + Basics.threeQuestions());
        System.out.println("character classes  = " + Basics.characterClasses());
        System.out.println("anchors            = " + Basics.anchors());
        System.out.println("greedy and lazy    = " + Basics.greedyVersusLazy());
        System.out.println("quantifiers        = " + Basics.quantifiers());
        System.out.println("flags              = " + Basics.alternationAndFlags());
        System.out.println("named groups       = " + Groups.parseDate("2026-10-18"));
        System.out.println("not a date         = " + Groups.parseDate("18/10/2026"));
        System.out.println("numbered groups    = " + Groups.numberedGroups());
        System.out.println("replacing          = " + Groups.replacing());
        System.out.println("splitting          = " + Groups.splitting());
        System.out.println("optional group     = " + Groups.optionalGroup());
        System.out.println("phones             = " + Validation.phones());
        System.out.println("emails             = " + Validation.emails());
        System.out.println("passwords          = " + Validation.passwords());
        System.out.println("unclosed group     = " + syntaxErrors("(abc"));
        System.out.println("dangling quantifier= " + syntaxErrors("*abc"));
        System.out.println("bad range          = " + syntaxErrors("[z-a]"));
        System.out.println("unclosed class     = " + syntaxErrors("[abc"));
        String illegalEscape = "class A { String p = \"\\d+\"; }";
        System.out.println("backslash d in a Java string = " + Javac.messages("A", illegalEscape));
    }
}
