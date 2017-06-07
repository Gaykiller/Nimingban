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

package com.hippo.nimingban.activity

/*
 * Created by Hippo on 5/12/2017.
 */

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import com.hippo.stage.Director
import com.hippo.stage.Scene
import com.hippo.stage.Stage

abstract class StageActivity : AppCompatActivity() {

  companion object {
    @JvmField val STAGE_ID = 0
  }

  private var director: Director? = null
  private var stage: Stage? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    onSetContentView()

    val director = Director.hire(this, savedInstanceState)
    this.director = director

    val needRoot = !director.contains(STAGE_ID)

    val stage = director.direct(onGetStageLayout(), STAGE_ID)
    this.stage = stage

    if (needRoot) {
      stage.pushScene(onCreateRootScene())
    }
  }

  /**
   * Sets content view for this `Activity`.
   *
   * Please call [.setContentView], [.setContentView] or
   * [.setContentView] here.
   *
   * Called in [.onCreate].
   */
  protected abstract fun onSetContentView()

  /**
   * Returns a container for scenes.
   */
  protected abstract fun onGetStageLayout(): ViewGroup

  /**
   * Returns root scene for this `StageActivity`.
   */
  protected abstract fun onCreateRootScene(): Scene

  override fun onDestroy() {
    super.onDestroy()
    director = null
    stage = null
  }

  override fun onBackPressed() {
    if (!(director?.handleBack() ?: false)) {
      super.onBackPressed()
    }
  }

  /**
   * Pushes a scene to the stage.
   * It's a no-op if the activity is destroyed.
   */
  fun pushScene(scene: Scene) {
    stage?.pushScene(scene)
  }

  /**
   * Replace the top scene.
   * It's a no-op if the activity is destroyed.
   */
  fun replaceTopScene(scene: Scene) {
    stage?.replaceTopScene(scene)
  }

  /**
   * Returns the top scene.
   * Always returns `null` if the activity is destroyed.
   */
  fun getTopScene(): Scene? {
    return stage?.topScene
  }
}
