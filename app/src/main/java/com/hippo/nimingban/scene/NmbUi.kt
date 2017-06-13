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

package com.hippo.nimingban.scene

import android.content.Context
import com.hippo.nimingban.activity.NmbActivity

/*
 * Created by Hippo on 6/12/2017.
 */

abstract class NmbUi<U: NmbUi<U, S>, S: NmbScene<S, U>>(
    internal val scene: S,
    internal val activity: NmbActivity,
    internal val context: Context
) : Ui() {

  fun pushScene(scene: NmbScene<*, *>) {
    this.scene.stage?.pushScene(scene)
  }
}
