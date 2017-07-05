package com.example.vietis.designsample.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vietis on 7/5/2017.
 */

public class Utils {
    private static final String PREFERENCES_FILE = "design_sample_settings";

    public static String readSharedSetting(Context context, String settingName, String defaultValue){
        /* Tạo ra đối tượng SharedPreferences vào file "PREFERENCES_FILE" .xml nến lưu thành công. Với kiểu lưu MODE_PRIVATE. */
        SharedPreferences sharedPre = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);

        /* Lấy giá trị được lưu trong key "settingName", nếu không thấy thì sẽ lấy giá trị "defaultValue". */
        return sharedPre.getString(settingName, defaultValue);
    }

}
