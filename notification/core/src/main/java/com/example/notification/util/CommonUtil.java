package com.example.notification.util;

import java.util.Arrays;
import java.util.Objects;

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
}
