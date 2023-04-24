package com.example.notification.util;

import java.util.*;

public class CommonUtil {

    public static void nonNull(Object object) {
        if (Objects.nonNull(object)) {
            throw new NullPointerException();
        }
    }

    public static void nonNull(Object... objects) {
        Arrays.stream(objects)
                .forEach(CommonUtil::nonNull);
    }

    public static boolean isEmpty(Object o) {

        if (o instanceof String) {
            return ((String) o).isEmpty();
        }

        if (o instanceof Integer) {
            return ((Integer) o) == 0;
        }

        if (o instanceof Double) {
            return ((Double) o) == 0D;
        }

        if (o instanceof Float) {
            return ((Float) o) == 0F;
        }

        if (o instanceof Long) {
            return ((Long) o) == 0L;
        }

        if (o instanceof Map) {
            return ((Map<?, ?>) o).isEmpty();
        }

        return ((Collection<?>) o).isEmpty();
    }
}
