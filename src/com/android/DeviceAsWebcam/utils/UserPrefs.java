/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.DeviceAsWebcam.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.DeviceAsWebcam.R;

/**
 * Utility class for reading/writing user preferences from/to SharedPreferences.
 */
public class UserPrefs {
    private final Context mContext;
    private final SharedPreferences mPrefs;
    private final SharedPreferences.Editor mPrefsEditor;

    public UserPrefs(Context context) {
        mContext = context;
        mPrefs = context.getSharedPreferences(context.getString(R.string.prefs_file_name),
                Context.MODE_PRIVATE);
        mPrefsEditor = mPrefs.edit();
    }

    /**
     * Read the stored zoom ratio for the given cameraId. Returns {@code defaultZoom} if no entry
     * is found.
     */
    public synchronized float fetchZoomRatio(String cameraId, float defaultZoom) {
        return mPrefs.getFloat(mContext.getString(R.string.prefs_zoom_ratio_key, cameraId),
                defaultZoom);
    }

    /**
     * Write the provided zoom ratio for the given cameraId to SharedPrefs.
     */
    public synchronized void storeZoomRatio(String cameraId, float zoom) {
        mPrefsEditor.putFloat(mContext.getString(R.string.prefs_zoom_ratio_key, cameraId), zoom);
        mPrefsEditor.apply();
    }

    /**
     * Read and return the stored cameraId, or return {@code defaultCameraId} if no cameraId is
     * stored.
     */
    public synchronized String fetchCameraId(String defaultCameraId) {
        return mPrefs.getString(mContext.getString(R.string.prefs_camera_id_key), defaultCameraId);
    }

    /**
     * Write cameraId to SharedPrefs.
     */
    public synchronized void storeCameraId(String cameraId) {
        mPrefsEditor.putString(mContext.getString(R.string.prefs_camera_id_key), cameraId);
        mPrefsEditor.apply();
    }
}
