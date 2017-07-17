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

import android.os.Bundle
import com.hippo.nimingban.R
import com.hippo.nimingban.component.MvpPaper
import com.hippo.nimingban.component.MvpPen
import com.hippo.nimingban.component.NmbScene
import com.hippo.nimingban.component.paper.GalleryPen
import com.hippo.nimingban.component.paper.SwipeBackPaper
import com.hippo.nimingban.component.paper.SwipeBackPen
import com.hippo.nimingban.component.paper.ToolbarPaper
import com.hippo.nimingban.component.paper.ToolbarPen
import com.hippo.nimingban.component.paper.gallery
import com.hippo.nimingban.component.paper.papers
import com.hippo.nimingban.component.paper.pens
import com.hippo.nimingban.component.paper.swipeBack
import com.hippo.nimingban.component.paper.toolbar
import com.hippo.swipeback.SwipeBackLayout

/*
 * Created by Hippo on 2017/7/18.
 */

class GalleryScene : NmbScene() {

  companion object {
    internal const val KEY_IMAGE = "GalleryScene:image"
    internal const val KEY_NMB_IMAGE = "GalleryScene:nmb_image"
  }

  private val swipeBack: SwipeBackPen = object : SwipeBackPen() {

    override fun onCreate(args: Bundle) {
      super.onCreate(args)
      // TODO Get it from preference
      view.setSwipeEdge(SwipeBackLayout.EDGE_LEFT or SwipeBackLayout.EDGE_RIGHT)
    }

    override fun onFinishUi() {
      super.onFinishUi()
      pop()
    }
  }

  private val toolbar: ToolbarPen = object : ToolbarPen() {

    override fun onCreate(args: Bundle) {
      super.onCreate(args)
      view.setTitle(R.string.gallery_title)
      view.setNavigationIcon(R.drawable.arrow_left_white_x24)
    }

    override fun onClickNavigationIcon() {
      super.onClickNavigationIcon()
      pop()
    }
  }

  private val gallery: GalleryPen = object : GalleryPen() {}

  private val pen = pens(swipeBack, toolbar, gallery)

  override fun createPen(): MvpPen<*> = pen

  override fun createPaper(): MvpPaper<*> = papers(pen) {
    swipeBack(swipeBack, it) {
      toolbar(toolbar, SwipeBackPaper.CONTAINER_ID) {
        gallery(gallery, ToolbarPaper.CONTAINER_ID)
      }
    }
  }

  override fun onCreate(args: Bundle) {
    super.onCreate(args)

    opacity = TRANSLUCENT

    val image = args.getString(KEY_IMAGE)
    val nmbImage = args.getString(KEY_NMB_IMAGE)

    if (image != null) {
      gallery.setImage(image)
    } else {
      gallery.setNmbImage(nmbImage)
    }
  }
}