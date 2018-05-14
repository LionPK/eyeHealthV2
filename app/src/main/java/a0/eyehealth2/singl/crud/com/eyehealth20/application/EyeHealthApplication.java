package a0.eyehealth2.singl.crud.com.eyehealth20.application;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatDelegate;

public class EyeHealthApplication extends Application {
    private static EyeHealthApplication instance;
    private static Context appContext;

    public static EyeHealthApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context mAppContext) {
        this.appContext = mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        this.setAppContext(getApplicationContext());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }
}
