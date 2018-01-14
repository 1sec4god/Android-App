package com.onesecforgod.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 ** Created by afiouni on 1/17/16.
 */
public class ListPreferenceShowSummary extends ListPreference {

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public ListPreferenceShowSummary (final Context context, final AttributeSet attrs, final int defStyleAttr, final int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public ListPreferenceShowSummary (final Context context, final AttributeSet attrs, final int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  public ListPreferenceShowSummary(final Context context, final AttributeSet attrs) {
    super(context, attrs);
  }

  public ListPreferenceShowSummary(final Context context) {
    super(context);
  }


  // NOTE:
  // The framework forgot to call notifyChanged() in setValue() on previous versions of android.
  // This bug has been fixed in android-4.4_r0.7.
  // Commit: platform/frameworks/base/+/94c02a1a1a6d7e6900e5a459e9cc699b9510e5a2
  // Time: Tue Jul 23 14:43:37 2013 -0700
  //
  // However on previous versions, we have to workaround it by ourselves.
  //Source: http://stackoverflow.com/a/21642401
  @Override
  public final void setValue(final String value) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      super.setValue(value);
    } else {
      final String oldValue = this.getValue();
      super.setValue(value);
      if (!TextUtils.equals(value, oldValue)) {
        this.notifyChanged();
      }
    }
  }

}
