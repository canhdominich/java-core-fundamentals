package dev.canhdominich.core.syntax;

import java.util.List;
import javax.lang.model.SourceVersion;

public class IdentifierRules {

    public static String classify(String word) {
        if (SourceVersion.isKeyword(word, SourceVersion.RELEASE_17)) {
            return "keyword or reserved literal";
        }
        return SourceVersion.isIdentifier(word) ? "valid identifier" : "not an identifier";
    }

    public static void main(String[] args) {
        List<String> words = List.of("age", "Age", "AGE", "_name", "$price", "total2", "2total", "my-var", "my var", "class", "int", "true", "null", "goto", "const", "var", "record", "_", "số_lượng", "π");
        for (String word : words) {
            System.out.printf("%-10s %s%n", word, classify(word));
        }
        System.out.println("age, Age, AGE are " + List.of("age", "Age", "AGE").stream().distinct().count() + " different names");
    }
}
