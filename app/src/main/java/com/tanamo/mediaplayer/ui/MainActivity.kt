@file:Suppress("DEPRECATION")

package com.tanamo.mediaplayer.ui

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


import android.Manifest
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import com.tanamo.mediaplayer.R
import com.tanamo.mediaplayer.adapter.Adapt
import com.tanamo.mediaplayer.model.About
import com.tanamo.mediaplayer.model.Kons
import com.tanamo.mediaplayer.model.Kons.AUTHOR_EMAIL_ADDRESS
import com.tanamo.mediaplayer.model.Kons.App_name
import com.tanamo.mediaplayer.model.Kons.PERMISSION
import com.tanamo.mediaplayer.model.Kons.murl
import com.tanamo.mediaplayer.model.Kons.sel
import com.tanamo.mediaplayer.model.Mod
import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by ${TANDOH} on ${6/20/2017}.
 */
class MainActivity : AppCompatActivity() {

    val lis = ArrayList<Mod>()
    var adapter: Adapt? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        perm()
        setContentView(R.layout.activity_main)

        init()

        allMedia()

    }

    fun init() {


        setSupportActionBar(toolBar)

        sbT()

        navigation_view.setNavigationItemSelectedListener({ menuItem ->
            drawer_layout.closeDrawers()
            val intent: Intent

            if (menuItem.itemId == R.id.home) {


            } else if (menuItem.itemId == R.id.rat) {
                try {
                    val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.market_link) + App_name))
                    sendIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(sendIntent)
                } catch (exx: ActivityNotFoundException) {
                    val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.playstore_link) + App_name))
                    sendIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(sendIntent)
                }

            } else if (menuItem.itemId == R.id.tell) {
                intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, getString(com.tanamo.mediaplayer.R.string.sha))
                startActivity(Intent.createChooser(intent, getString(com.tanamo.mediaplayer.R.string.shavi)))

            } else if (menuItem.itemId == R.id.more) {
                try {
                    val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/search?q=tanamo%20inc&c=apps"))
                    sendIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(sendIntent)
                } catch (exx: ActivityNotFoundException) {
                    val sendIntent = Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.playstore_link) + App_name))
                    sendIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(sendIntent)
                }

            } else if (menuItem.itemId == R.id.feedback) {
                intent = Intent(Intent.ACTION_SEND)
                intent.type = "message/rfc822"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(AUTHOR_EMAIL_ADDRESS))
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(com.tanamo.mediaplayer.R.string.fb))
                try {
                    startActivity(Intent.createChooser(intent, getString(com.tanamo.mediaplayer.R.string.fb)))
                } catch (ex: ActivityNotFoundException) {
                    Toast.makeText(this@MainActivity, com.tanamo.mediaplayer.R.string.no_em_ins, Toast.LENGTH_SHORT).show()
                }

                startActivity(intent)
            } else if (menuItem.itemId == R.id.quit) {

                val dialog = AlertDialog.Builder(this@MainActivity)
                dialog.setTitle(com.tanamo.mediaplayer.R.string.app_name)
                dialog.setIcon(R.drawable.ic_launcher)
                dialog.setMessage(com.tanamo.mediaplayer.R.string.dwq)
                dialog.setNegativeButton("No") { _, _ -> }
                dialog.setPositiveButton("Yes") { _, _ ->
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                    System.exit(0)

                    Toast.makeText(applicationContext, com.tanamo.mediaplayer.R.string.log_out_suc, Toast.LENGTH_SHORT).show()
                }.show()

            }

            false
        }

        )

        val abt: ActionBarDrawerToggle = ActionBarDrawerToggle(this@MainActivity, drawer_layout, toolBar, R.string.app_name, R.string.app_name)
        abt.isDrawerIndicatorEnabled = true
        drawer_layout.setDrawerListener(abt)
        abt.syncState()


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            R.id.abt -> About(this@MainActivity).show()

        }
        return super.onOptionsItemSelected(item)
    }

    fun allMedia() {

        val cursor = contentResolver.query(murl, null, sel, null, null)

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val data = cursor.getString(cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.DATA))
                    val art = cursor.getString(cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST))
                    val dis = cursor.getString(cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.DISPLAY_NAME))

                    lis.add(Mod(dis, art, data))


                } while (cursor.moveToNext())


            }
            cursor.close()

            adapter = Adapt(this@MainActivity, lis)
            lv.adapter = adapter
        }


    }

    @android.annotation.SuppressLint("InlinedApi")
    fun sbT() {
        Kons.suV {
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }


    private fun perm() {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val hasStoragePermission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
            val hasInternetPermission = checkSelfPermission(Manifest.permission.INTERNET)
            val hasNetworkPermission = checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)
            val hasWifiPermission = checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)


            val permissions = java.util.ArrayList<String>()

            if (hasWifiPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_WIFI_STATE)
            }

            if (hasNetworkPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_NETWORK_STATE)
            }

            if (hasInternetPermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.INTERNET)
            }


            if (hasStoragePermission != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
            }

            if (!permissions.isEmpty()) {
                requestPermissions(permissions.toTypedArray(), PERMISSION)
            }

        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION -> permissions.indices
                    .filter { grantResults[it] == PackageManager.PERMISSION_DENIED }
                    .forEach {
                        Toast.makeText(this, "Permission denied: " + permissions[it] + ". This may cause " +
                                "the app to behave abnormally", Toast.LENGTH_SHORT).show()
                    }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }


}


