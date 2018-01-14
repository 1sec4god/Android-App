package com.onesecforgod.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

/**
 ** Created by afiouni on 1/10/16.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

  @Override
  public final void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Load the preferences from an XML resource
    this.addPreferencesFromResource(R.xml.preferences);

    //Register Listener
    this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);

    this.onSharedPreferenceChanged(null, CONST.preference.key.LANGUAGE);
  }

  @Override
  public final void onResume() {
    super.onResume();

    // Register Listener
    this.getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
  }

  @Override
  public final void onPause() {
    super.onPause();

    // Unregister the listener
    this.getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
  }

  @Override
  public final void onSharedPreferenceChanged(final SharedPreferences sharedPreferences, final String key) {

    //noinspection CallToStringEquals
    if (CONST.preference.key.LANGUAGE.equals(key)) {
      final ListPreference languageListPref = (ListPreference)this.findPreference(key);
      final ListPreference textListPref = (ListPreference)this.findPreference(CONST.preference.key.TEXT);

      switch (languageListPref.getValue()) {

        case CONST.preference.value.ENGLISH_LANGUAGE:
          textListPref.setEntries(R.array.preference_text_options_en);
          break;

        case CONST.preference.value.ARABIC_LANGUAGE:
        default:
          textListPref.setEntries(R.array.preference_text_options_ar);
          break;

      }
    }
  }
}