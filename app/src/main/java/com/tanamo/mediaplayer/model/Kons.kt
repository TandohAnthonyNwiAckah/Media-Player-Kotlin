package com.tanamo.mediaplayer.model

import android.content.Context
import android.content.pm.PackageManager
import android.media.MediaPlayer

/*
 * Copyright (C) 2017 Tanamo Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
* Created by ${TANDOH} on ${6/20/2017}.
*/
object Kons {


    val PERMISSION = 354
    var playRadio = false
    var medi: MediaPlayer? = null


    val murl = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    val sel = android.provider.MediaStore.Audio.Media.IS_MUSIC + "!=0"

    val COUNTS = 6000


    val AUTHOR_EMAIL_ADDRESS = "tanamoinc@gmail.com"
    val App_name = "com.tanamo.dicto"


    fun suV(code: () -> Unit) {
        sVersion(code, 21)
    }

    fun sVersion(code: () -> Unit, sdk: Int) {
        if (android.os.Build.VERSION.SDK_INT >= sdk) {
            code.invoke()
        }
    }

    fun getAppName(ctx: Context, pkgName: String): String {
        try {
            val pm = ctx.packageManager
            val appInfo = pm.getApplicationInfo(pkgName, 0)
            val label = pm.getApplicationLabel(appInfo).toString()
            return label
        } catch (e: android.content.pm.PackageManager.NameNotFoundException) {
            return ""
        }

    }

    fun getAppVersionName(ctx: Context, pkgName: String): String {
        try {
            val pm = ctx.packageManager
            val pkgInfo = pm.getPackageInfo(pkgName, 0)
            val ver = pkgInfo.versionName
            return ver
        } catch (e: PackageManager.NameNotFoundException) {
            return "0"
        }

    }


}
