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

import java.util.Random

/*
 * Created by Hippo on 6/7/2017.
 */

/**
 * Returns the absolute value.
 */
inline fun Long.abs() = Math.abs(this)

/**
 * Clamped the `Int` to the range &#91;bound1, bound2&#93; if bound2 &gt;= bound1,
 * otherwise &#91;bound2, bound1&#93;.
 */
fun Int.clamp(bound1: Int, bound2: Int): Int {
  if (bound2 >= bound1) {
    if (this > bound2) return bound2
    if (this < bound1) return bound1
  } else {
    if (this > bound1) return bound1
    if (this < bound2) return bound2
  }
  return this
}

/**
 * Clamped the `Long` to the range &#91;bound1, bound2&#93; if bound2 &gt;= bound1,
 * otherwise &#91;bound2, bound1&#93;.
 */
fun Long.clamp(bound1: Long, bound2: Long): Long {
  if (bound2 >= bound1) {
    if (this > bound2) return bound2
    if (this < bound1) return bound1
  } else {
    if (this > bound1) return bound1
    if (this < bound2) return bound2
  }
  return this
}

/**
 * Clamped the `Float` to the range &#91;bound1, bound2&#93; if bound2 &gt;= bound1,
 * otherwise &#91;bound2, bound1&#93;.
 *
 * Returns [Float.NaN] if any of `bound1` and `bound2` is [Float.NaN].
 */
fun Float.clamp(bound1: Float, bound2: Float): Float {
  if (bound1.isNaN() || bound2.isNaN()) return Float.NaN

  if (bound2 >= bound1) {
    if (this > bound2) return bound2
    if (this < bound1) return bound1
  } else {
    if (this > bound1) return bound1
    if (this < bound2) return bound2
  }
  return this
}

/**
 * Calculates a linear interpolation between two inputs.
 */
inline fun Float.lerp(from: Float, to: Float) = from + this * (to - from)


private val random = Random()

/**
 * Returns a pseudo-random uniformly distributed `int`
 * in the half-open range [0, howbig).

 * @param howbig the upper bound (exclusive), must be positive
 * *
 * @return a random `int`
 * *
 * @throws IllegalArgumentException if howbig &lt;= 0
 */
@Throws(IllegalArgumentException::class)
fun random(howbig: Int): Int {
  return random.nextInt(howbig)
}

/**
 * Returns a pseudo-random uniformly distributed `int`
 * in the half-open range [howsmall, howbig).
 *
 * @param howsmall the lower bound (inclusive)
 * @param howbig the upper bound (exclusive)
 *
 * @return a random `int`
 *
 * @throws IllegalArgumentException if howbig &lt;= howsmall
 */
@Throws(IllegalArgumentException::class)
fun random(howsmall: Int, howbig: Int): Int {
  if (howsmall >= howbig) {
    throw IllegalArgumentException("howsmall >= howbig: $howsmall >= $howbig")
  }
  var r = random.nextInt()
  val n = howbig - howsmall
  val m = n - 1
  if (n and m == 0)
    r = (r and m) + howsmall
  else if (n > 0) {
    var u = r.ushr(1)
    r = u % n
    while (u + m - r < 0) {
      u = random.nextInt().ushr(1)
      r = u % n
    }
    r += howsmall
  } else {
    while (r < howsmall || r >= howbig)
      r = random.nextInt()
  }
  return r
}