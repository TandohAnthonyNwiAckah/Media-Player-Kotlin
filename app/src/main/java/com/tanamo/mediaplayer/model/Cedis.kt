package com.tanamo.mediaplayer.model

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.tanamo.mediaplayer.R
import kotlinx.android.synthetic.main.cedis.*

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

class Cedis : Fragment() {

    var vie: View? = null
    var adr: AdRequest? = null

    override fun onCreateView(inflater: LayoutInflater?, @android.support.annotation.Nullable container: ViewGroup?, @android.support.annotation.Nullable savedInstanceState: Bundle?): View? {
        vie = inflater?.inflate(R.layout.cedis, container, false)
        return vie
    }

    override fun onActivityCreated(savedInstanceState: android.os.Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adr = AdRequest.Builder().build()
        adV.loadAd(adr!!)
    }

}
