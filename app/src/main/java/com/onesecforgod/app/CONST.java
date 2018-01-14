package com.onesecforgod.app;

/**
 ** Created by afiouni on 1/18/16.
 */
public final class CONST {

  public static final class actions {
    public static final String ACTION_SHOW_MESSAGE = "com.onesecforgod.app.action.SHOW_MESSAGE";
  }

  public static final class config {
    public static final String MESSAGE_TEXT = "com.onesecforgod.app.message.MESSAGE_TEXT";
    public static final String MESSAGE_DURATION = "com.onesecforgod.app.message.MESSAGE_DURATION";
  }

  public static final class preference {

    public static final class key {
      public static final String STYLE = "preferences_style";
      public static final String LANGUAGE = "preferences_language";
      public static final String TEXT = "preferences_text";
    }
    public static final class value {
      public static final String ENGLISH_LANGUAGE = "en";
      public static final String ARABIC_LANGUAGE = "ar";
      public static final String MESSAGE_STYLE_FULL_SCREEN = "full_screen";
    }
    public static final class defaults {
      public static final String MESSAGE_TEXT = "bismillah";
      public static final String MESSAGE_STYLE = "full_screen";
      public static final Long MESSAGE_DURATION = (Long) 1000L;
    }
  }

}

