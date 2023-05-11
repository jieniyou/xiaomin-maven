package com.xiaomin.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public abstract class ObjectUtil {
    public ObjectUtil() {
    }

    public static boolean isEmpty(Object[] objects) {
        return objects == null || objects.length == 0;
    }

    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else if (o instanceof Optional) {
            return !((Optional)o).isPresent();
        } else if (o instanceof CharSequence) {
            return ((CharSequence)o).length() == 0;
        } else if (o.getClass().isArray()) {
            return Array.getLength(o) == 0;
        } else if (o instanceof Collection) {
            return ((Collection)o).isEmpty();
        } else {
            return o instanceof Map ? ((Map)o).isEmpty() : false;
        }
    }

    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    public static String capitalize(String str) {
        if (str != null && str.length() != 0) {
            char[] chars = str.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);
        } else {
            return str;
        }
    }

    public static int nullSafeHashCode(Object o) {
        if (o == null) {
            return 0;
        } else {
            if (o.getClass().isArray()) {
                if (o instanceof Object[]) {
                    return nullSafeHashCode((Object[])((Object[])((Object[])o)));
                }

                if (o instanceof boolean[]) {
                    return nullSafeHashCode((boolean[])((boolean[])((boolean[])o)));
                }

                if (o instanceof byte[]) {
                    return nullSafeHashCode((byte[])((byte[])((byte[])o)));
                }

                if (o instanceof char[]) {
                    return nullSafeHashCode((char[])((char[])((char[])o)));
                }

                if (o instanceof double[]) {
                    return nullSafeHashCode((double[])((double[])((double[])o)));
                }

                if (o instanceof float[]) {
                    return nullSafeHashCode((float[])((float[])((float[])o)));
                }

                if (o instanceof int[]) {
                    return nullSafeHashCode((int[])((int[])((int[])o)));
                }

                if (o instanceof long[]) {
                    return nullSafeHashCode((long[])((long[])((long[])o)));
                }

                if (o instanceof short[]) {
                    return nullSafeHashCode((short[])((short[])((short[])o)));
                }
            }

            return o.hashCode();
        }
    }

    public static int nullSafeHashCode(Object[] objects) {
        if (objects == null) {
            return 0;
        } else {
            int j = 7;
            Object[] objectList = objects;
            int length = objects.length;

            for(int i = 0; i < length; ++i) {
                Object o = objectList[i];
                j = 31 * j + nullSafeHashCode(o);
            }

            return j;
        }
    }

    public static int nullSafeHashCode(boolean[] booleans) {
        if (booleans == null) {
            return 0;
        } else {
            int j = 7;
            boolean[] booleanList = booleans;
            int length = booleans.length;

            for(int i = 0; i < length; ++i) {
                boolean b = booleanList[i];
                j = 31 * j + Boolean.hashCode(b);
            }

            return j;
        }
    }

    public static int nullSafeHashCode(byte[] bytes) {
        if (bytes == null) {
            return 0;
        } else {
            int j = 7;
            byte[] byteList = bytes;
            int length = bytes.length;

            for(int i = 0; i < length; ++i) {
                byte b = byteList[i];
                j = 31 * j + b;
            }

            return j;
        }
    }

    public static int nullSafeHashCode(char[] chars) {
        if (chars == null) {
            return 0;
        } else {
            int j = 7;
            char[] charList = chars;
            int length = chars.length;

            for(int i = 0; i < length; ++i) {
                char c = charList[i];
                j = 31 * j + c;
            }

            return j;
        }
    }

    public static int nullSafeHashCode(double[] doubles) {
        if (doubles == null) {
            return 0;
        } else {
            int j = 7;
            double[] doubleList = doubles;
            int length = doubles.length;

            for(int i = 0; i < length; ++i) {
                double d = doubleList[i];
                j = 31 * j + Double.hashCode(d);
            }

            return j;
        }
    }

    public static int nullSafeHashCode(float[] floats) {
        if (floats == null) {
            return 0;
        } else {
            int j = 7;
            float[] floatList = floats;
            int length = floats.length;

            for(int i = 0; i < length; ++i) {
                float f = floatList[i];
                j = 31 * j + Float.hashCode(f);
            }

            return j;
        }
    }

    public static int nullSafeHashCode(int[] ints) {
        if (ints == null) {
            return 0;
        } else {
            int j = 7;
            int[] intList = ints;
            int length = ints.length;

            for(int i = 0; i < length; ++i) {
                int I = intList[i];
                j = 31 * j + I;
            }

            return j;
        }
    }

    public static int nullSafeHashCode(long[] longs) {
        if (longs == null) {
            return 0;
        } else {
            int j = 7;
            long[] longList = longs;
            int length = longs.length;

            for(int i = 0; i < length; ++i) {
                long l = longList[i];
                j = 31 * j + Long.hashCode(l);
            }

            return j;
        }
    }

    public static int nullSafeHashCode(short[] shorts) {
        if (shorts == null) {
            return 0;
        } else {
            int j = 7;
            short[] shortList = shorts;
            int length = shorts.length;

            for(int i = 0; i < length; ++i) {
                short s = shortList[i];
                j = 31 * j + s;
            }

            return j;
        }
    }

    /** @deprecated */
    @Deprecated
    public static int hashCode(boolean b) {
        return Boolean.hashCode(b);
    }

    /** @deprecated */
    @Deprecated
    public static int hashCode(double d) {
        return Double.hashCode(d);
    }

    /** @deprecated */
    @Deprecated
    public static int hashCode(float f) {
        return Float.hashCode(f);
    }

    /** @deprecated */
    @Deprecated
    public static int hashCode(long l) {
        return Long.hashCode(l);
    }
}
