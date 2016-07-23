package ru.euphoria.commons.util;


import android.database.Cursor;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Static utils methods for array and {@link Collection}
 *
 * For powerful collection util - use {@link Collections}
 *
 * @author Igor Morozkin
 * @since 1.0
 */
public class ArrayUtil {
    private static final CharsetDecoder DECODER = Charset.defaultCharset().newDecoder();

    /** An immutable empty arrays */
    public static final Object[] EMPTY_OBJECT  = new Object[0];
    public static final String[] EMPTE_STRINGS = new String[0];
    public static final byte[]   EMPTY_BYTES   = new byte[0];
    public static final char[]   EMPTY_CHARS   = new char[0];
    public static final short[]  EMPTY_SHORTS  = new short[0];
    public static final int[]    EMPTE_INTS    = new int[0];
    public static final long[]   EMPTY_LONGS   = new long[0];
    public static final float[]  EMPTY_FLOATS  = new float[0];
    public static final double[] EMPTY_DOUBLES = new double[0];

    /** The index for linear and binary search, if the value is not found */
    public static final int VALUE_NOT_FOUND = -1;

    // private, because uses only static methods
    private ArrayUtil() {
        /* empty */
    }

    /**
     * Performs a linear search for value in the ascending array.
     * Beware that linear search returns only the first found element.
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int linearSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Performs a linear search for value in the ascending array.
     * Beware that linear search returns only the first found element.
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int linearSearch(long[] array, long value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Performs a linear search for value in the ascending array.
     * Beware that linear search returns only the first found element.
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int linearSearch(char[] array, char value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Performs a linear search for value in the ascending array.
     * Beware that linear search returns only the first found element.
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int linearSearch(short[] array, short value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Performs a linear search for value in the ascending array.
     * Beware that linear search returns only the first found element.
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int linearSearch(byte[] array, byte value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Performs a linear search for value in the ascending array.
     * Beware that linear search returns only the first found element.
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int linearSearch(float[] array, float value) {
        for (int i = 0; i < array.length; i++) {
            if (Float.compare(array[i], value) == 0) {
                return i;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Performs a linear search for value in the ascending array.
     * Beware that linear search returns only the first found element.
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int linearSearch(double[] array, double value) {
        for (int i = 0; i < array.length; i++) {
            if (Double.compare(array[i], value) == 0) {
                return i;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Performs a linear search for value in the ascending array.
     * Beware that linear search returns only the first found element.
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int linearSearch(Object[] array, Object value) {
        for (int i = 0; i < array.length; i++) {
            Object o = array[i];
            if (o == value || o.equals(value)) {
                return i;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Performs a binary search for value in the array. The array con be unsorted.
     * Beware that binary search returns only the first found element
     * <p/>
     * This algorithm differs from the classical binary search
     * is that, it is more optimized for unsorted arrays.
     * As the classic method - the search starts at the half-size of array
     * <p/>
     * NOTE: If array is sorted, use {@link Arrays#binarySearch(int[], int)}
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int binarySearch(int[] array, int value) {
        int high = array.length - 1;
        int startIndex = high / 2;
        int index = startIndex;
        while (index >= 0 && index <= high) {
            if (array[index] == value) {
                return index;
            }

            if (index > startIndex) {
                index = index - ((index - startIndex) * 2);
            } else {
                index = index + ((startIndex - index) * 2) + 1;
            }
        }
        return VALUE_NOT_FOUND;
    }


    /**
     * Performs a binary search for value in the array. The array con be unsorted.
     * Beware that binary search returns only the first found element
     * <p/>
     * This algorithm differs from the classical binary search
     * is that, it is more optimized for unsorted arrays.
     * As the classic method - the search starts at the half-size of array
     * <p/>
     * NOTE: If array is sorted, use {@link Arrays#binarySearch(long[], long)}
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int binarySearch(long[] array, long value) {
        int high = array.length - 1;
        int startIndex = high / 2;
        int index = startIndex;
        while (index >= 0 && index <= high) {
            if (array[index] == value) {
                return index;
            }

            if (index > startIndex) {
                index = index - ((index - startIndex) * 2);
            } else {
                index = index + ((startIndex - index) * 2) + 1;
            }
        }
        return VALUE_NOT_FOUND;
    }


    /**
     * Performs a binary search for value in the array. The array con be unsorted.
     * Beware that binary search returns only the first found element
     * <p/>
     * This algorithm differs from the classical binary search
     * is that, it is more optimized for unsorted arrays.
     * As the classic method - the search starts at the half-size of array
     * <p/>
     * NOTE: If array is sorted, use {@link Arrays#binarySearch(short[], short)}
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int binarySearch(short[] array, short value) {
        int high = array.length - 1;
        int startIndex = high / 2;
        int index = startIndex;
        while (index >= 0 && index <= high) {
            if (array[index] == value) {
                return index;
            }

            if (index > startIndex) {
                index = index - ((index - startIndex) * 2);
            } else {
                index = index + ((startIndex - index) * 2) + 1;
            }
        }
        return VALUE_NOT_FOUND;
    }


    /**
     * Performs a binary search for value in the array. The array con be unsorted.
     * Beware that binary search returns only the first found element
     * <p/>
     * This algorithm differs from the classical binary search
     * is that, it is more optimized for unsorted arrays.
     * As the classic method - the search starts at the half-size of array
     * <p/>
     * NOTE: If array is sorted, use {@link Arrays#binarySearch(char[], char)}
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int binarySearch(char[] array, char value) {
        int high = array.length - 1;
        int startIndex = high / 2;
        int index = startIndex;
        while (index >= 0 && index <= high) {
            if (array[index] == value) {
                return index;
            }

            if (index > startIndex) {
                index = index - ((index - startIndex) * 2);
            } else {
                index = index + ((startIndex - index) * 2) + 1;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Performs a binary search for value in the array. The array con be unsorted.
     * Beware that binary search returns only the first found element
     * <p/>
     * This algorithm differs from the classical binary search
     * is that, it is more optimized for unsorted arrays.
     * As the classic method - the search starts at the half-size of array
     * <p/>
     * NOTE: If array is sorted, use {@link Arrays#binarySearch(byte[], byte)}
     *
     * @param array the array to search
     * @param value the value fo find
     * @return the non-negative index of value, or -1 if value not found
     */
    public static int binarySearch(byte[] array, byte value) {
        int high = array.length - 1;
        int startIndex = high / 2;
        int index = startIndex;
        while (index >= 0 && index <= high) {
            if (array[index] == value) {
                return index;
            }

            if (index > startIndex) {
                index = index - ((index - startIndex) * 2);
            } else {
                index = index + ((startIndex - index) * 2) + 1;
            }
        }
        return VALUE_NOT_FOUND;
    }

    /**
     * Compares the two lists.
     *
     * @param list1 the first collection
     * @param list2 the second collection
     * @return true, if both list are have same length
     * and all elements at index is equal according to {@link Object#equals},
     * false otherwise
     */
    public static boolean equals(List list1, List list2) {
        if (list1 == list2) {
            return true;
        }

        if (list1.size() != list2.size()) {
            return false;
        }

        for (int i = 0; i < list1.size(); i++) {
            Object o1 = list1.get(i);
            Object o2 = list2.get(i);
            if (!(o1 == null ? o2 == null : o1.equals(o2))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the array has the specified value
     *
     * @param array the array to search specified value
     * @param value the value to search for
     */
    public static boolean contains(int[] array, int value) {
        return linearSearch(array, value) != VALUE_NOT_FOUND;
    }

    /**
     * Returns true if the array has the specified value
     *
     * @param array the array to search specified value
     * @param value the value to search for
     */
    public static boolean contains(long[] array, long value) {
        return linearSearch(array, value) != VALUE_NOT_FOUND;
    }

    /**
     * Returns true if the array has the specified value
     *
     * @param array the array to search specified value
     * @param value the value to search for
     */
    public static boolean contains(char[] array, char value) {
        return linearSearch(array, value) != VALUE_NOT_FOUND;
    }

    /**
     * Returns true if the array has the specified value
     *
     * @param array the array to search specified value
     * @param value the value to search for
     */
    public static boolean contains(short[] array, short value) {
        return linearSearch(array, value) != VALUE_NOT_FOUND;
    }

    /**
     * Returns true if the array has the specified value
     *
     * @param array the array to search specified value
     * @param value the value to search for
     */
    public static boolean contains(byte[] array, byte value) {
        return linearSearch(array, value) != VALUE_NOT_FOUND;
    }

    /**
     * Returns true if the array has the specified value
     *
     * @param array the array to search specified value
     * @param value the value to search for
     */
    public static boolean contains(float[] array, float value) {
        return linearSearch(array, value) != VALUE_NOT_FOUND;
    }

    /**
     * Returns true if the array has the specified value
     *
     * @param array the array to search specified value
     * @param value the value to search for
     */
    public static boolean contains(double[] array, double value) {
        return linearSearch(array, value) != VALUE_NOT_FOUND;
    }

    /**
     * Returns true if the array has the specified value
     *
     * @param array the array to search specified value
     * @param value the value to search for
     */
    public static boolean contains(Object[] array, Object value) {
        return linearSearch(array, value) != VALUE_NOT_FOUND;
    }

    /**
     * Copies all elements from source into new {@link ArrayList}.
     * For array use copyOf in {@link Arrays} class
     *
     * @param source the source to copy into new List
     * @return a new {@link ArrayList} containing
     * all elements from source
     */
    @SuppressWarnings("unchecked")
    public static <E> ArrayList<E> copyOf(Collection<E> source) {
        ArrayList<E> list = new ArrayList<>(source.size());
        list.addAll(source);
        return list;
    }

    /**
     * Returns a new {@link ArrayList} contains the union
     * of specified {@link Collection}s
     *
     * @param list1 the first list, can not be null
     * @param list2 the second list, can not be null
     * @throws NullPointerException if lists is null
     */
    public static <E> ArrayList<E> union(Collection<E> list1, Collection<E> list2) {
        if (list1 == null || list2 == null) {
            throw new NullPointerException("lists can not be null");
        }
        ArrayList<E> output = new ArrayList<>(list1.size() + list2.size());
        output.addAll(list1);
        output.addAll(list2);
        return output;
    }

    /**
     * Searches for the maximum element of specified array using linear search.
     * To search in {@link Collection} you should
     * use {@link java.util.Collections#max(Collection)}.
     *
     * @param array the array to search
     * @return the max element in specified array
     */
    public static byte max(byte... array) {
        byte max = array[0];

        for (int i = 1; i < array.length; i++) {
            byte value = array[i];
            if (max < value) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Searches for the maximum element of specified array using linear search.
     * To search in {@link Collection} you should
     * use {@link java.util.Collections#max(Collection)}.
     *
     * @param array the array to search
     * @return the max element in specified array
     */
    public static int max(int... array) {
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            if (max < value) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Searches for the maximum element of specified array using linear search.
     * To search in {@link Collection} you should
     * use {@link java.util.Collections#max(Collection)}.
     *
     * @param array the array to search
     * @return the max element in specified array
     */
    public static long max(long... array) {
        long max = array[0];

        for (int i = 1; i < array.length; i++) {
            long value = array[i];
            if (max < value) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Searches for the maximum element of specified array using linear search.
     * To search in {@link Collection} you should
     * use {@link java.util.Collections#max(Collection)}.
     *
     * @param array the array to search
     * @return the max element in specified array
     */
    public static char max(char... array) {
        char max = array[0];

        for (int i = 1; i < array.length; i++) {
            char value = array[i];
            if (max < value) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Searches for the maximum element of specified array using linear search.
     * To search in {@link Collection} you should
     * use {@link java.util.Collections#max(Collection)}.
     *
     * @param array the array to search
     * @return the max element in specified array
     */
    public static short max(short... array) {
        short max = array[0];

        for (int i = 1; i < array.length; i++) {
            short value = array[i];
            if (max < value) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Searches for the maximum element of specified array using linear search.
     * To search in {@link Collection} you should
     * use {@link java.util.Collections#max(Collection)}.
     *
     * @param array the array to search
     * @return the max element in specified array
     */
    public static float max(float... array) {
        float max = array[0];

        for (int i = 1; i < array.length; i++) {
            float value = array[i];
            if (Float.compare(value, max) >= 1) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Searches for the maximum element of specified array using linear search.
     * To search in {@link Collection} you should
     * use {@link java.util.Collections#max(Collection)}.
     *
     * @param array the array to search
     * @return the max element in specified array
     */
    public static double max(double... array) {
        double max = array[0];

        for (int i = 1; i < array.length; i++) {
            double value = array[i];
            if (Double.compare(value, max) >= 1) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Searches for the minimum element of specified array using linear search.
     * To search in {@link Collection} you should
     * use {@link java.util.Collections#max(Collection)}.
     *
     * @param array the array to search
     * @return the min element in specified array
     */
    public static int min(int... array) {
        int min = array[0];

        for (int i = 1; i < array.length; i++) {
            int value = array[i];
            if (min > value) {
                min = value;
            }
        }
        return min;
    }

    /**
     * Searches for the minimum element of specified array using linear search.
     * To search in {@link Collection} you should
     * use {@link java.util.Collections#max(Collection)}.
     *
     * @param array the array to search
     * @return the min element in specified array
     */
    public static long min(long... array) {
        long min = array[0];

        for (int i = 1; i < array.length; i++) {
            long value = array[i];
            if (min > value) {
                min = value;
            }
        }
        return min;
    }

    /**
     * Returns the total sum of specified array using linear search
     *
     * @param array the array to calculate
     */
    public static int total(int... array) {
        int total = 0;
        for (int value : array) {
            total += value;
        }
        return total;
    }

    /**
     * Returns the total sum of specified array using linear search
     *
     * @param array the array to calculate
     */
    public static long total(long... array) {
        long total = 0;
        for (long value : array) {
            total += value;
        }
        return total;
    }

    /**
     * Creates a {@link String} representation of the {@link Collection} passed.
     * Each element is converted to a {@link String} and separated by {@code ","}.
     * Returns null if items is null or empty
     *
     * @param items the items to convert
     * @param <T>   the generic type of {@link Collection}
     * @return the {@link String} representation of items,
     * or {@code ""} if items is is null or empty
     */
    public static <T> String toString(List<T> items) {
        if (isEmpty(items)) return "";

        StringBuilder buffer = new StringBuilder(items.size() * 12);
        buffer.append(items.get(0));
        for (int i = 1; i < items.size(); i++) {
            buffer.append(',');
            buffer.append(items.get(i));
        }
        return buffer.toString();
    }

    public static String toString(byte[] array) {
//        try {
//            return (String) Class.forName("java.lang.StringFactory")
//                    .getMethod("newStringFromBytes", byte[].class).invoke(null, array);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new String(array);
//        }

        try {
            CharBuffer buffer = DECODER.decode(ByteBuffer.wrap(array));
            return buffer.toString();
        } catch (CharacterCodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Moves every element of the specified array to a random new position
     *
     * @param array the array to manipulate
     */
    public static void shuffle(int... array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);

            swap(array, i, index);
        }
    }

    /**
     * Swaps the value of specified array from index {@code i} to {@code j}
     *
     * @param array the array to manipulate
     * @param i     the index of element to swap on {@code j}
     * @param j     the position of the second value
     */
    public static void swap(byte[] array, int i, int j) {
        byte temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Swaps the value of specified array from index {@code i} to {@code j}
     *
     * @param array the array to manipulate
     * @param i     the index of element to swap on {@code j}
     * @param j     the position of the second value
     */
    public static void swap(short[] array, int i, int j) {
        short temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Swaps the value of specified array from index {@code i} to {@code j}
     *
     * @param array the array to manipulate
     * @param i     the index of element to swap on {@code j}
     * @param j     the position of the second value
     */
    public static void swap(char[] array, int i, int j) {
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Swaps the value of specified array from index {@code i} to {@code j}
     *
     * @param array the array to manipulate
     * @param i     the index of element to swap on {@code j}
     * @param j     the position of the second value
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Swaps the value of specified array from index {@code i} to {@code j}
     *
     * @param array the array to manipulate
     * @param i     the index of element to swap on {@code j}
     * @param j     the position of the second value
     */
    public static void swap(long[] array, int i, int j) {
        long temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Swaps the value of specified array from index {@code i} to {@code j}
     *
     * @param array the array to manipulate
     * @param i     the index of element to swap on {@code j}
     * @param j     the position of the second value
     */
    public static void swap(float[] array, int i, int j) {
        float temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Swaps the value of specified array from index {@code i} to {@code j}
     *
     * @param array the array to manipulate
     * @param i     the index of element to swap on {@code j}
     * @param j     the position of the second value
     */
    public static void swap(double[] array, int i, int j) {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Swaps the value of specified array from index {@code i} to {@code j}
     *
     * @param array the array to manipulate
     * @param i     the index of element to swap on {@code j}
     * @param j     the position of the second value
     */
    public static void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int average(int... array) {
        long sum = 0;
        for (int item : array) {
            sum += item;
        }
        return (int) (sum / array.length);
    }

    public static long average(long... array) {
        long sum = 0;
        for (long item : array) {
            sum += item;
        }
        return (sum / array.length);
    }

    /**
     * Creates a {@link String} representation of the specified array passed.
     * Each element is converted to a {@link String} and separated by {@code ","}.
     * Returns null if items is null or empty
     *
     * @param array the array to convert
     * @param <T>   the generic type of {@link Collection}
     * @return the {@link String} representation of items,
     * or {@code ""} if array is null or empty
     */
    @SafeVarargs
    public static <T> String toString(T... array) {
        if (array == null || array.length == 0) {
            return null;
        }

        StringBuilder buffer = new StringBuilder(array.length * 12);
        buffer.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            buffer.append(',');
            buffer.append(array[i]);
        }
        return buffer.toString();
    }

    /**
     * Creates a {@link String} representation of the specified array passed.
     * Each element is converted to a {@link String} and separated by {@code ","}.
     * Returns null if items is null or empty
     *
     * @param array the array to convert
     * @return the {@link String} representation of items,
     * or {@code ""} if array is null or empty
     */
    public static String toString(int... array) {
        if (array == null || array.length == 0) {
            return null;
        }

        StringBuilder buffer = new StringBuilder(array.length * 12);
        buffer.append(array[0]);
        for (int i = 1; i < array.length; i++) {
            buffer.append(',');
            buffer.append(array[i]);
        }
        return buffer.toString();
    }

    public static int[] toIntArray(List<Integer> list) {
        int[] array = new int[list.size()];
        if (isEmpty(list)) {
            return array;
        }

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    /**
     * Returns true if the collection is null or empty
     *
     * @param collection the collection to be examined
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Returns true if the array is null or empty
     *
     * @param array the array to be examined
     */
    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Returns true if the array is null or empty
     *
     * @param array the array to be examined
     */
    public static boolean isEmpty(long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Returns true if the array is null or empty
     *
     * @param array the array to be examined
     */
    public static boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Returns true if the array is null or empty
     *
     * @param array the array to be examined
     */
    public static boolean isEmpty(char[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Returns true if the array is null or empty
     *
     * @param array the array to be examined
     */
    public static boolean isEmpty(short[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Returns true if the array is null or empty
     *
     * @param array the array to be examined
     */
    public static boolean isEmpty(float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Returns true if the array is null or empty
     *
     * @param array the array to be examined
     */
    public static boolean isEmpty(double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Returns true if the array is null or empty
     *
     * @param array the array to be examined
     */
    public static boolean isEmpty(byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * Wraps cursor in list
     *
     * @param cursor the cursor to wrap
     */
    @SuppressWarnings("unchecked")
    public static CursorListWrapper cursorWrapper(Cursor cursor) {
        return new CursorListWrapper(cursor);
    }

    public static class CursorListWrapper extends AbstractList<Cursor> {
        private Cursor cursor;

        public CursorListWrapper(Cursor cursor) {
            this.cursor = cursor;
        }

        @Override
        public Cursor get(int location) {
            cursor.moveToPosition(location);
            return cursor;
        }

        public Cursor getCursor() {
            return cursor;
        }

        public void setCursor(Cursor newCursor) {
            if (cursor != null && cursor != newCursor) {
                cursor.close();
                cursor = null;
            }
            cursor = newCursor;
        }

        @Override
        public int size() {
            return cursor.getCount();
        }

        @Override
        public void clear() {
            cursor.close();
        }

    }
}
