package com.michael

import android.app.NotificationManager
import android.appwidget.AppWidgetManager
import android.content.ClipboardManager
import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.nsd.NsdManager
import android.net.wifi.WifiManager
import android.preference.PreferenceManager
import android.telephony.TelephonyManager
import android.view.inputmethod.InputMethodManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * A module for Android-specific dependencies which require a [Context] or
 * [android.app.Application] to create.
 */
@Module
class AndroidModule internal constructor(private val application: MyApplication) {

    @Provides
    @Singleton
    internal fun providePreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Provides
    @Singleton
    internal fun provideClipboardManager(): ClipboardManager {
        return application.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    @Provides
    @Singleton
    internal fun provideOldNotificationManager(): NotificationManager {
        return application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    @Provides
    @Singleton
    internal fun provideTelephonyManager(): TelephonyManager {
        return application.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    }

    @Provides
    @Singleton
    internal fun provideConnectivityManager(): ConnectivityManager {
        return application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    @Provides
    @Singleton
    internal fun provideLocationManager(): LocationManager {
        return application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    @Provides
    @Singleton
    internal fun provideInputMethodManager(): InputMethodManager {
        return application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    @Provides
    @Singleton
    internal fun provideContentResolver(): ContentResolver {
        return application.contentResolver
    }

    @Provides
    @Singleton
    internal fun provideAppWidgetManager(): AppWidgetManager {
        return AppWidgetManager.getInstance(application)
    }

    @Provides
    @Singleton
    internal fun provideWifiManager(): WifiManager {
        return application.getSystemService(Context.WIFI_SERVICE) as WifiManager
    }

    @Provides
    @Singleton
    internal fun provideNsdManager(): NsdManager {
        return application.getSystemService(Context.NSD_SERVICE) as NsdManager
    }

    @Provides
    @Singleton
    internal fun provideLocalBroadcastManager(): androidx.localbroadcastmanager.content.LocalBroadcastManager {
        return androidx.localbroadcastmanager.content.LocalBroadcastManager.getInstance(application)
    }
}