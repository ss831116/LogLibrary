package com.android.loglib;

import android.content.Context;
import android.os.Environment;

/**
 * Created by bernie.shi on 2017/3/20.
 */

public class LogSDK {
    private static LogSDK instance;

    private LogSDK() {
    }

    public static LogSDK getInstance() {
        if (instance == null)
            instance = new LogSDK();
        return instance;
    }

    public static void init(Context context) {
        Util.applicationName = AppHelper.getApplicationName(context);
        Util.logPath = Environment.getExternalStorageDirectory() + "/" + Util.applicationName + "/";
        AppHelper.createDir(Util.logPath);
        LogToFile.init(context);
        CrashHandler.getInstance().init(context);
    }
}
