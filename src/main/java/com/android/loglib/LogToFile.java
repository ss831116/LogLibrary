package com.android.loglib;

import android.content.Context;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Created by bernie.shi on 2017/3/20.
 */

public class LogToFile {
    public static final String VERBOSE = "v";
    public static final String DEBUG = "d";
    public static final String INFO = "i";
    public static final String WARN = "w";
    public static final String ERROR = "e";
    public static boolean APP_DBG = false;

    public static void init(Context context) {
        APP_DBG = AppHelper.isApkDebugable(context);
        AppHelper.createDir(Util.logPath + LogPath.LOGS);
    }

    public static String getLogType(String type) {
        return type.equals(VERBOSE) || type.equals(DEBUG) || type.equals(INFO) || type.equals(WARN) || type.equals(ERROR) ? type : "";
    }

    public static StackTraceElement getStackTraceElement() {
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[2];
        return stackTraceElement;
    }

    public static void logOut(Class clazz, String type, String tag, Object object) {
        if (APP_DBG) return;
        switch (getLogType(type)) {
            case "":
                return;
            default:
                writeToFile(clazz.getPackage().getName(), getStackTraceElement().getClassName() + "." + getStackTraceElement().getMethodName(), type, tag, object.toString());
                break;
        }
    }

    private static void writeToFile(String packageName, String pathName, String type, String tag, String msg) {
        if (null == Util.logPath + "Logs") {
            return;
        }
        String fileName = Util.logPath + DataHelper.getDataOfDay(LogPath.LOGS, LogPath.LOGSUFFIX);
        String log = DataHelper.getDataOfSSS() + "/" + packageName + " " + type + "/" + " " + pathName + " " + tag + " " + msg + "\n";
        FileOutputStream fos = null;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(fileName, true);
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            bw.write(log);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
