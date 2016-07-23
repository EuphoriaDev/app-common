package ru.euphoria.commons.base;

import java.util.regex.Pattern;

/**
 * Determines a true or false value for any Java {@code char} value.
 * Also offers basic text processing methods based on this function.
 *
 * Based on CharMatcher on Google Guava
 *
 * @author Kevin Bourrillion
 * @author Igor Morozkin
 * @since 1.0
 */
public abstract class CharMatcher {

    /**
     * Returns a {@link CharMatcher} which can be used to determine whether
     * a character is ASCII. meaning that its code point
     * is less than 128.
     */
    public static CharMatcher ascii() {
        return AsciiMatcher.INSTANCE;
    }

    /**
     * Returns a new {@link CharMatcher} which can be used to determine whether
     * a character is Russian.
     * Supported lower and upper case
     */
    public static CharMatcher russian() {
        return inRange('а', 'я').or(inRange('А', 'Я'));
    }

    /**
     * Matches none character.
     * {@link CharMatcher#matches(char)} always returns {@code false}
     */
    public static CharMatcher none() {
        return NoneMatcher.INSTANCE;
    }

    /**
     * Matches any character.
     * {@link CharMatcher#matches(char)} always returns {@code true}.
     */
    public static CharMatcher any() {
        return AnyMatcher.INSTANCE;
    }

    /**
     * Returns a new {@link CharMatcher} that matches only one specified character
     *
     * @param c the char to match
     */
    public static CharMatcher is(char c) {
        return new IsMatcher(c);
    }

    /**
     * Returns a new {@link CharMatcher}
     * Using to matches any character except the one specified
     *
     * @param c the char to match
     */
    public static CharMatcher isNot(char c) {
        return new IsNotMatcher(c);
    }

    /**
     * Returns a new {@link CharMatcher} that matches any character in
     * a specified range (both endpoints are inclusive).
     *
     * For example: to match any lowercase letter of the English alphabet, use {@code
     * CharMatcher.inRange('a', 'z')}.
     *
     * @param start the first char range
     * @param end   the last char range
     */
    public static CharMatcher inRange(char start, char end) {
        return new InRangeMatcher(start, end);
    }

    /**
     * Returns a new {@link CharMatcher} that matches any character
     * using {@link java.util.regex.Matcher}
     *
     * @param pattern the regex pattern of Matcher
     */
    public static CharMatcher regex(String pattern) {
        return new RegexMatcher(pattern);
    }

    /**
     * Returns a new {@link CharMatcher} that matches any character
     * matched by both this matcher OR specified {@code other}.
     *
     * @param other the other char matcher
     */
    public CharMatcher or(CharMatcher other) {
        return new OrMatcher(this, other);
    }

    /**
     * Returns a new {@link CharMatcher} that matches any character
     * matched by both this matcher AND specified {@code other}.
     *
     * @param other the other char matcher
     */
    public CharMatcher and(CharMatcher other) {
        return new AndMatcher(this, other);
    }

    /**
     * Determines a {@code true} or {@link false} for matches
     * the specified character.
     *
     * @param c the char to match
     */
    public abstract boolean matches(char c);

    private static class IsMatcher extends CharMatcher {
        private char match;

        protected IsMatcher(char match) {
            this.match = match;
        }

        @Override
        public boolean matches(char c) {
            return c == match;
        }
    }

    private static class IsNotMatcher extends CharMatcher {
        private char match;

        protected IsNotMatcher(char match) {
            this.match = match;
        }

        @Override
        public boolean matches(char c) {
            return c != match;
        }
    }

    private static class NoneMatcher extends CharMatcher {
        static final NoneMatcher INSTANCE = new NoneMatcher();

        @Override
        public boolean matches(char c) {
            return false;
        }
    }

    private static class AnyMatcher extends CharMatcher {
        static final AnyMatcher INSTANCE = new AnyMatcher();

        @Override
        public boolean matches(char c) {
            return true;
        }
    }

    private static class AsciiMatcher extends CharMatcher {
        static final AsciiMatcher INSTANCE = new AsciiMatcher();

        @Override
        public boolean matches(char c) {
            return c <= '\u007f';
        }
    }

    private static final class OrMatcher extends CharMatcher {
        private CharMatcher first;
        private CharMatcher second;

        protected OrMatcher(CharMatcher first, CharMatcher second) {
            this.first = first;
            this.second = second;
        }


        @Override
        public boolean matches(char c) {
            return first.matches(c) || second.matches(c);
        }
    }

    private static final class AndMatcher extends CharMatcher {
        private CharMatcher first;
        private CharMatcher second;

        protected AndMatcher(CharMatcher first, CharMatcher second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean matches(char c) {
            return first.matches(c) && second.matches(c);
        }
    }

    private static final class RegexMatcher extends CharMatcher {
        private Pattern pattern;

        protected RegexMatcher(String pattern) {
            this.pattern = Pattern.compile(pattern);
        }

        @Override
        public boolean matches(char c) {
            return pattern.matcher(String.valueOf(c)).find();
        }
    }

    private static final class InRangeMatcher extends CharMatcher {
        private char start;
        private char end;

        protected InRangeMatcher(char start, char end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean matches(char c) {
            return start <= c && c <= end;
        }
    }
}
