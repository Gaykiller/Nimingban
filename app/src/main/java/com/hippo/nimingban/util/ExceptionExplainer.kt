/*
 * Copyright 2017 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.nimingban.util

import android.content.Context
import android.graphics.drawable.Drawable
import com.hippo.nimingban.NMB_APP
import com.hippo.nimingban.R
import com.hippo.nimingban.exception.GeneralException
import com.hippo.nimingban.exception.PresetException

/*
 * Created by Hippo on 6/8/2017.
 */

/**
 * Explains the exception with a String.
 */
fun explain(error: Throwable, context: Context = NMB_APP): String {
  return when (error) {
    is PresetException -> context.string(error.text)
    is GeneralException -> error.message
    else -> context.string(R.string.error_unknown)
  }
}

/**
 * Explains the exception with a Drawable.
 */
fun explainVividly(error: Throwable, context: Context): Drawable {
  val drawable: Int = when (error) {
    is PresetException -> if (error.icon != 0) error.icon else R.drawable.emoticon_sad_primary_x64
    else -> R.drawable.emoticon_sad_primary_x64
  }
  return context.drawable(drawable)
}
