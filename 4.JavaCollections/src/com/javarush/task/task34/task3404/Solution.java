package com.javarush.task.task34.task3404;

import java.util.ArrayList;
import java.util.List;

/* 
Рекурсия для мат. выражения
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.recurse("sin(2*(-5+1.5*4)+28)", 0); // Expected output: 0.5 6 //sin(2*(-5+1.5*4)+28)
    }

    public void recurse(final String expression, int countOperation) {
        List<Lexeme> lexemes = getLexemes(expression);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);

        double result = expr(lexemeBuffer);
        countOperation = lexemeBuffer.getCountOperation();

        System.out.printf("%.2f %d%n", result, countOperation);
    }

    public Solution() {
        //don't delete
    }

    private double expr(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.getLexeme();
        if (lexeme.type == LexemeType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    private double plusminus(LexemeBuffer lexemes) {
        double value = multdiv(lexemes);

        while (true) {
            Lexeme lexeme = lexemes.getLexeme();
            switch (lexeme.type) {
                case PLUS:
                    value += multdiv(lexemes);
                    lexemes.incOper();
                    break;
                case MINUS:
                    value -= multdiv(lexemes);
                    lexemes.incOper();
                    break;
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    private double multdiv(LexemeBuffer lexemes) {
        double value = pow(lexemes);

        while (true) {
            Lexeme lexeme = lexemes.getLexeme();
            switch (lexeme.type) {
                case MULTI:
                    value *= pow(lexemes);
                    lexemes.incOper();
                    break;
                case DIV:
                    value /= pow(lexemes);
                    lexemes.incOper();
                    break;
                default:
                    lexemes.back();
                    return value;
            }
        }
    }

    private double pow(LexemeBuffer lexemes) {
        double value = factor(lexemes);

        Lexeme lexeme = lexemes.getLexeme();
        if (lexeme.type == LexemeType.POW) {
            lexemes.incOper();
            double pow = expr(lexemes);
            return Math.pow(value, pow);
        } else {
            lexemes.back();
            return value;
        }
    }

    private double factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.getLexeme();
        switch (lexeme.type) {
            case NUMBER:
                return Double.parseDouble(lexeme.value);
            case MINUS:
                lexemes.incOper();
                return -factor(lexemes);
            case LEFT_BRACKET:
                double value = expr(lexemes);
                lexeme = lexemes.getLexeme();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new IllegalArgumentException("Unexpected token: " + lexeme.value + " at pos: " + lexemes.getPos());
                }
                return value;
            case SIN:
            case COS:
            case TAN:
                lexemes.back();
                return function(lexemes);
            default:
                throw new IllegalArgumentException("Unexpected token: " + lexeme.value + " at pos: " + lexemes.getPos());
        }
    }

    private double function(LexemeBuffer lexemes) {
        double degrees;
        double value = 0;
        Lexeme lexeme = lexemes.getLexeme();
        switch (lexeme.type) {
            case SIN:
                lexeme = lexemes.getLexeme();
                if (lexeme.type != LexemeType.LEFT_BRACKET) throw new IllegalArgumentException("Unexpected token: " +
                        lexeme.value + " at pos: " + lexemes.getPos());

                degrees = plusminus(lexemes);
                value = Math.sin(Math.toRadians(degrees));

                lexeme = lexemes.getLexeme();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) throw new IllegalArgumentException("Unexpected token: " +
                        lexeme.value + " at pos: " + lexemes.getPos());
                break;
            case COS:
                lexeme = lexemes.getLexeme();
                if (lexeme.type != LexemeType.LEFT_BRACKET) throw new IllegalArgumentException("Unexpected token: " +
                        lexeme.value + " at pos: " + lexemes.getPos());

                degrees = plusminus(lexemes);
                value = Math.cos(Math.toRadians(degrees));

                lexeme = lexemes.getLexeme();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) throw new IllegalArgumentException("Unexpected token: " +
                        lexeme.value + " at pos: " + lexemes.getPos());
                break;
            case TAN:
                lexeme = lexemes.getLexeme();
                if (lexeme.type != LexemeType.LEFT_BRACKET) throw new IllegalArgumentException("Unexpected token: " +
                        lexeme.value + " at pos: " + lexemes.getPos());

                degrees = plusminus(lexemes);
                value = Math.tan(Math.toRadians(degrees));

                lexeme = lexemes.getLexeme();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) throw new IllegalArgumentException("Unexpected token: " +
                        lexeme.value + " at pos: " + lexemes.getPos());
                break;
        }
        lexemes.incOper();
        return value;
    }

    private List<Lexeme> getLexemes(String expression) {
        List<Lexeme> lexemes = new ArrayList<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    break;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    break;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.PLUS, c));
                    break;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.MINUS, c));
                    break;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.MULTI, c));
                    break;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.DIV, c));
                    break;
                case '^':
                    lexemes.add(new Lexeme(LexemeType.POW, c));
                    break;
                case 's':
                    lexemes.add(new Lexeme(LexemeType.SIN, "sin"));
                    i += 2;
                    break;
                case 'c':
                    lexemes.add(new Lexeme(LexemeType.COS, "cos"));
                    i += 2;
                    break;
                case 't':
                    lexemes.add(new Lexeme(LexemeType.TAN, "tan"));
                    i += 2;
                    break;
                default:
                    if (c <= '9' && c >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            i++;
                            if (i == expression.length()) break;
                            c = expression.charAt(i);
                        }
                        while ((c <= '9' && c >= '0') || c == '.');
                        i--;
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    }
                    break;
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));

        return lexemes;
    }

    private enum LexemeType {
        LEFT_BRACKET, RIGHT_BRACKET,
        PLUS, MINUS, MULTI, DIV, POW,
        SIN, COS, TAN,
        NUMBER,
        EOF
    }

    private class Lexeme {
        LexemeType type;
        String value;

        public Lexeme(LexemeType type, String value) {
            this.type = type;
            this.value = value;
        }

        public Lexeme(LexemeType type, Character value) {
            this.type = type;
            this.value = value.toString();
        }
    }

    private class LexemeBuffer {
        private int pos;
        private List<Lexeme> lexemes;
        private int countOperation;

        public LexemeBuffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme getLexeme() {
            return lexemes.get(pos++);
        }

        public void back() {
            pos--;
        }

        public int getPos() {
            return pos;
        }

        public void incOper() {
            countOperation++;
        }

        public int getCountOperation() {
            return countOperation;
        }
    }
}
