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

package com.hippo.nimingban.component.scene

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hippo.nimingban.component.GroupUi
import com.hippo.nimingban.component.paper.ToolbarUi
import com.hippo.nimingban.component.paper.forums
import com.hippo.nimingban.component.paper.toolbar

/*
 * Created by Hippo on 6/22/2017.
 */

class ForumsSceneUi(
    logic: ForumsSceneLogic,
    override val inflater: LayoutInflater,
    container: ViewGroup
) : GroupUi() {

  override val view: View

  init {
    toolbar(logic.toolbarLogic, container) {
      forums(logic.forumsLogic, ToolbarUi.CONTAINER_ID)
    }
    view = getChild(0).view
  }
}