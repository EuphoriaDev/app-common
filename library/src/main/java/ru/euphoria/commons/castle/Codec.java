package ru.euphoria.commons.castle;


import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import ru.euphoria.commons.io.Charsets;
import ru.euphoria.commons.util.ArrayUtil;

/**
 * Static utils methods for hashing and {@link java.security.MessageDigest}
 *
 * @since 1.1
 */
public class Codec {

    /**
     * Returns a new hex {@link Coder} which can be used to
     * encoding and decoding hexadecimal data
     */
    public static Coder hex() {
        return new Hex();
    }

    /**
     * Returns a new base64 {@link Coder} for encoding and decoding
     * the Base64 representation of binary data.
     *
     * Work only on Android
     */
    public static Coder base64() {
        return new Base64();
    }

    /**
     * Returns a new MD5 {@link Coder} for encoding and decoding
     * the MD5 representation of binary data
     */
    public static Coder md5() {
        return new Md5();
    }

    /**
     * Returns a new SHA-1 {@link Coder} for encoding and decoding
     * the SHA-1 representation of binary data
     */
    public static Coder sha1() {
        return new Sha1();
    }

    /**
     * Returns a new SHA-256 {@link Coder} for encoding and decoding
     * the SHA-256 representation of binary data
     */
    public static Coder sha256() {
        return new Sha256();
    }

    /**
     * Returns a new Binary {@link Coder} for encoding and decoding
     * the binary representation of binary data
     */
    public static Coder binary() {
        return new Binary();
    }

    /**
     * Returns a new Morse {@link Coder} for encoding and decoding
     * the morse representation of data
     */
    public static Coder morse() {
        return new Morse();
    }

    public static class Morse extends Coder {
        static final char[] EN_ALPHABET = new char[45];
        static {
            int i = 0;
            for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
                EN_ALPHABET[i++] = alphabet;
            }
            for (int j = 1; j <= 10; j++) {
                EN_ALPHABET[i++] = (char) j;
            }
            EN_ALPHABET[i++] = '.';
            EN_ALPHABET[i++] = ',';
            EN_ALPHABET[i++] = ':';
            EN_ALPHABET[i++] = '"';
            EN_ALPHABET[i++] = '«';
            EN_ALPHABET[i++] = '-';
            EN_ALPHABET[i++] = '/';
            EN_ALPHABET[i++] = '?';
            EN_ALPHABET[i] = '!';
        }

        /** Morse alphabet for English language */
        static final String[] TABLE =
    /* a */     {"•−", "•••−", "−•−•", "•−−", "•", "••−•", "−−•",
    /* h */      "••••", "••", "•−−−", "−•−", "•−••", "−−", "−•",
    /* o */      "−−−", "•−−•", "−−•−", "•−•", "•••", "−",
    /* u */      "••−", "•••−", "•−−", "−••−", "−•−−", "−−••",
    /* 1-5 */    "•−−−−", "••−−−", "•••−−", "••••−", "•••••",
    /* 6-10 */   "−••••", "−−•••", "−−−••", "−−−−•", "−−−−−",
    //               .         ,        :         ;         "
    /* others */ "••••••", "•−•−•−", "−−−•••", "−•−•−•", "•−−−•",
    //              «        -         /         ?         !
                "•−••−•", "−••••−", "−••−•", "••−−••−", "−−••−−"};

        @Override
        public String encode(String source) {
            StringBuilder morse = new StringBuilder();
            for (int i = 0; i < source.length(); i++) {
                char c = Character.toLowerCase(source.charAt(i));
                int index = ArrayUtil.linearSearch(EN_ALPHABET, c);
                if (index != -1) {
                    morse.append(TABLE[index]);
                    morse.append(" ");
                }
            }

            return morse.toString();
        }

        @Override
        public String encode(byte[] source) {
            return encode(new String(source, Charsets.UTF_8));
        }

        @Override
        public byte[] decode(String source) throws UnsupportedOperationException {
            String[] codes = source.split(" ");
            StringBuilder buffer = new StringBuilder();

            for (String code : codes) {
                int index = ArrayUtil.linearSearch(TABLE, code);
                if (index != -1) {
                    buffer.append(EN_ALPHABET[index]);
                }
            }

            return buffer.toString().getBytes(Charsets.UTF_8);
        }
    }

    private static class Binary extends Coder {
        @Override
        public String encode(byte[] source) {
            StringBuilder builder = new StringBuilder(source.length * 8);
            for (byte value : source) {
                builder.append(Integer.toBinaryString(value));
                builder.append(' ');
            }

            return builder.toString();
        }

        @Override
        public byte[] decode(String source) throws UnsupportedOperationException {
            String binaries[] = source.split(" ");
            StringBuilder builder = new StringBuilder();

            for (String b : binaries) {
                builder.append((char) Integer.parseInt(b, 2));
            }
            return builder.toString().getBytes(Charsets.UTF_8);
        }
    }

    private static class Sha256 extends Coder {
        @Override
        public String encode(byte[] source) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] array = digest.digest(source);

                return toHex(array);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public byte[] decode(String source) {
            throw new UnsupportedOperationException("can't decode md5");
        }

        @Override
        public boolean canDecode(byte[] source) {
            return false;
        }
    }


    private static class Sha1 extends Coder {
        @Override
        public String encode(byte[] source) {
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-1");
                byte[] array = digest.digest(source);

                return toHex(array);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public byte[] decode(String source) {
            throw new UnsupportedOperationException("can't decode md5");
        }

        @Override
        public boolean canDecode(byte[] source) {
            return false;
        }
    }

    private static class Md5 extends Coder {
        @Override
        public String encode(byte[] source) {
            try {
                MessageDigest digest = MessageDigest.getInstance("MD5");
                byte[] array = digest.digest(source);

                return toHex(array);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public byte[] decode(String source) {
            throw new UnsupportedOperationException("can't decode md5");
        }

        @Override
        public boolean canDecode(byte[] source) {
            return false;
        }
    }

    private static class Base64 extends Coder {

        @Override
        public String encode(byte[] source) {
            return android.util.Base64.encodeToString(source, android.util.Base64.DEFAULT);
        }

        @Override
        public byte[] decode(String source) {
            return android.util.Base64.decode(source, android.util.Base64.DEFAULT);
        }
    }

    private static class Hex extends Coder {
        /** Table for hexadecimal alphabet */
        private static final char[] TABLE = new char[] {
                '0', '1', '2', '3', '4',
                '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D',
                'E', 'F'
        };

        @Override
        public String encode(byte[] source) {
            char[] chars = new char[source.length * 2];
            for (int i = 0; i < source.length; i++) {
                int v = source[i] & 0xFF;
                chars[i * 2] = TABLE[v >>> 4];
                chars[i * 2 + 1] = TABLE[v & 0x0F];
            }
            return String.valueOf(chars);
        }

        @Override
        public byte[] decode(String source) {
            ByteBuffer buffer = ByteBuffer.allocate(source.length() / 2);
            for (int i = 0; i < source.length(); i += 2) {
                buffer.put((byte) Integer.parseInt(source.substring(i, i + 2), 16));
            }

//            int len = s.length();
//            byte[] data = new byte[len / 2];
//            for (int i = 0; i < len; i += 2) {
//                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
//                        + Character.digit(s.charAt(i+1), 16));
//            }
//            return data;
            return buffer.array();
        }
    }

    /**
     * Class for encode and decode data (bytes, text)
     */
    public static abstract class Coder {
        /**
         * Returns a new encoded-data text from specified source
         *
         * @param source the byte array to encode
         */
        public abstract String encode(byte[] source);

        /**
         * Returns a new encoded-data text from specified source
         *
         * @param source the text to encode
         */
        public String encode(String source) {
            return encode(source.getBytes(Charsets.UTF_8));
        }

        /**
         * Returns a new decoded byte array from specified encoded-data text.
         *
         * @param source the text to decode, which is converted to bytes
         * @throws UnsupportedOperationException when can't decode the source
         */
        public abstract byte[] decode(String source) throws UnsupportedOperationException;

        /**
         * Returns true if this coder can decode specified byte array
         *
         * @param source the array to check
         */
        public boolean canDecode(byte[] source) {
            return true;
        }

        /**
         * Converts the specified byte array into its hexadecimal string
         * representation.
         *
         * @param array the bytes to convert
         */
        public String toHex(byte[] array) {
            return Codec.hex().encode(array);
        }
    }
}
