package com.tanamo.mediaplayer.adapter

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


import android.content.Context
import android.media.MediaPlayer
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.tanamo.mediaplayer.R
import com.tanamo.mediaplayer.model.Kons.medi
import com.tanamo.mediaplayer.model.Kons.playRadio
import com.tanamo.mediaplayer.model.Mod
import kotlinx.android.synthetic.main.item.view.*

/**
 * Created by ${TANDOH} on ${6/20/2017}.
 */

class Adapt(val con: Context, lise: ArrayList<Mod>) : BaseAdapter() {

    var lis = ArrayList<Mod>()

    init {
        this.lis = lise

    }

    override fun getView(postion: Int, p1: View?, p2: ViewGroup?): View {
        val myView = View.inflate(con, R.layout.item, null)
        val Tanamo = this.lis[postion]
        myView.txt1.text = Tanamo.Title
        myView.txt2.text = Tanamo.Author


        myView.but!!.setBackgroundResource(android.R.drawable.ic_media_play)

        myView.but.setOnClickListener({

            if (playRadio) {
                playRadio = false
                medi!!.stop()
                myView.but!!.setBackgroundResource(android.R.drawable.ic_media_play)
            } else {
                playRadio = true
                medi = MediaPlayer()

                try {
                    medi!!.setDataSource(Tanamo.Url)
                    medi!!.prepare()
                    medi!!.start()
                    myView.but!!.setBackgroundResource(android.R.drawable.ic_media_pause)
                } catch (ex: Exception) {
                }
            }


        })

        return myView

    }

    override fun getItem(item: Int): Any {
        return this.lis[item]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return this.lis.size
    }


}