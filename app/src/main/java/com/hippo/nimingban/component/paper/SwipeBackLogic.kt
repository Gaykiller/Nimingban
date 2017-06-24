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

package com.hippo.nimingban.component.paper

import com.hippo.nimingban.component.NmbLogic
import com.hippo.stage.Scene

/*
 * Created by Hippo on 6/21/2017.
 */

class SwipeBackLogic(
    val scene: Scene
) : NmbLogic() {

  var swipeBackUi: SwipeBackUi? = null
    set(value) {
      field = value
      value?.setSwipeEdge(edge)
    }

  private var edge: Int = 0

  /**
   * Called when user swipes the ui to finish it
   */
  fun onFinishUi() {
    scene.pop()
  }

  /**
   * Set the edge to enable swiping.
   */
  fun setSwipeEdge(edge: Int) {
    this.edge = edge
    swipeBackUi?.setSwipeEdge(edge)
  }
}