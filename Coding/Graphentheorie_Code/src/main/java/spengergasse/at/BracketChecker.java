package spengergasse.at;

import java.util.ArrayDeque;
import java.util.Deque;

         public class BracketChecker {
/**
  * Prueft, ob alle Klammern im String korrekt
  geschachtelt sind.
  * Erlaubt: (), [], {}
  * Alle anderen Zeichen werden ignoriert.
  *
  * @return true wenn alle Klammern korrekt geschachtelt
  * false sonst
  */
         public static boolean checkBrackets(String input) {
         Deque<Character> stack = new ArrayDeque<>();

         for (char c : input.toCharArray()) {
             if (c == '(' || c == '[' || c == '{') {
                 stack.push(c);
             }

             // TODO 1: Oeffnende Klammern auf den Stack legen
             // ... Ihre Loesung ...

             // TODO 2: Bei schliessender Klammer pruefen:
             // - Ist der Stack nicht leer?
             // - Passt das oberste Element zur
             // schliessenden Klammer?
             // Falls nicht: return false
             // ... Ihre Loesung ...

         // TODO 3: Am Ende pruefen, ob alle Klammern
         // geschlossen wurden
         // ... Ihre Loesung ...

            else if (c == ')' || c == ']' || c == '}') {
                 if (stack.isEmpty()) {
                     return false;
                 }
             char last = stack.pop();

                 if (c == ')' && last != '(') return false;
                 if (c == ']' && last != '[') return false;
                 if (c == '}' && last != '{') return false;
             }
             }

         return stack.isEmpty(); // Platzhalter
         }

         public static void main(String[] args) {
         System.out.println(
                 checkBrackets("{[()]}"));
        System.out.println(
                 checkBrackets("([)]")); // (b)
         System.out.println(
                 checkBrackets("((())")); // (c)
         System.out.println(
                 checkBrackets("(a + [b * {c}])")); // (d)
         System.out.println(
                 checkBrackets("")); // (e)
         System.out.println(
                 checkBrackets(")test(")); // (f)
             System.out.println(
                     checkBrackets("([[]])"));
         }
 }