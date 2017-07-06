package com.tanamo.mediaplayer.model

import android.view.View
import android.widget.TextView
import com.tanamo.mediaplayer.R
import com.tanamo.mediaplayer.model.Kons.getAppName
import com.tanamo.mediaplayer.model.Kons.getAppVersionName

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

class About(val mCtx: android.content.Context) {

    fun show() {
        val factory = android.view.LayoutInflater.from(mCtx)

        @android.annotation.SuppressLint("InflateParams")
        val dialogView = factory.inflate(com.tanamo.mediaplayer.R.layout.about, null)


        innerUpdate(dialogView)

        val adBuilder = android.app.AlertDialog.Builder(mCtx)
                .setTitle(com.tanamo.mediaplayer.R.string.app_name)
                .setIcon(com.tanamo.mediaplayer.R.drawable.ic_launcher)
                .setCancelable(true)
                .setPositiveButton("OK", null)
                .setView(dialogView)

        adBuilder.show()

    }

    @android.annotation.SuppressLint("SetTextI18n")
    fun innerUpdate(dialogView: View) {

        val appName = dialogView.findViewById<View>(R.id.app_name) as TextView

        val author = dialogView.findViewById<View>(R.id.author) as TextView

        val need = dialogView.findViewById<View>(R.id.needs) as TextView

        val website = dialogView.findViewById<View>(R.id.website) as TextView

        val face = dialogView.findViewById<View>(R.id.face) as TextView

        val bugs = dialogView.findViewById<View>(R.id.bugs) as TextView


        // app name & version
        val appText = "${getAppName(mCtx, mCtx.packageName)} v ${getAppVersionName(mCtx, mCtx.packageName)}"
        appName.text = appText
        appName.setTextColor(android.graphics.Color.parseColor("#00b8d4"))

        // author
        author.text = "Developed by:${mCtx.getString(com.tanamo.mediaplayer.R.string.author)}"

        // text
        need.setText(com.tanamo.mediaplayer.R.string.about_text)

        // website
        website.text = "${mCtx.getString(com.tanamo.mediaplayer.R.string.website)}:\n${mCtx.getString(com.tanamo.mediaplayer.R.string.website_url)}"
        website.setTextColor(android.graphics.Color.parseColor("#00b8d4"))

        // Github
        bugs.text = "${mCtx.getString(com.tanamo.mediaplayer.R.string.github)}:\n${mCtx.getString(com.tanamo.mediaplayer.R.string.github_link)}"
        bugs.setTextColor(android.graphics.Color.parseColor("#00b8d4"))

        // face
        face.text = "${mCtx.getString(com.tanamo.mediaplayer.R.string.face)}:\n${mCtx.getString(com.tanamo.mediaplayer.R.string.face_url)}"
        face.setTextColor(android.graphics.Color.parseColor("#00b8d4"))


    }

}
