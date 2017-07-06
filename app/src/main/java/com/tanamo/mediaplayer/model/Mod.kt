package com.tanamo.mediaplayer.model

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
class Mod {

    var Title: String? = null
    var Author: String? = null
    var Url: String? = null

    constructor(Title: String, Author: String, Url: String) {
        this.Title = Title
        this.Author = Author
        this.Url = Url
    }

}
