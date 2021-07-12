/*
 * Created by 2NF Software  on 05/05/20 9:35 AM
 * Copyright© 2020 2NF software. All Rights Reserved.
 * Last modified 08/05/20 3:27 PM
 *
 */

package com.base.mvvm.common.utils;

/**
 * Constants all commons key
 */
public class Constants {

    public static final int PAGE_SIZE = 50;
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAIL = "FAIL";

    public static class LoginType {

        public static final int GOOGLE = 1;
        public static final int FACEBOOK = 2;

    }

    public enum TypeError {

        VALIDATE(1),
        SERVER(2);
        private final int value;

        TypeError(final int newValue) {
            value = newValue;
        }

        public int getValue() {
            return value;
        }
    }


}
