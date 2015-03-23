/*
 * Copyright (c) 2011-2014 Jarek Sacha. All Rights Reserved.
 *
 * Author's e-mail: jpsacha at gmail.com
 */

package opencv2_cookbook.chapter02

import java.io.File

import opencv2_cookbook.OpenCVUtils._
import org.bytedeco.javacpp.indexer.ByteIndexer
import org.bytedeco.javacpp.opencv_core.Mat
import org.bytedeco.javacpp.opencv_highgui._

import scala.util.Random

/**
 * Set individual, randomly selected, pixels to a fixed value.
 *
 * Illustrates access to pixel values using absolute indexing.
 */
object Ex1Salt extends App {

  // Read input image
  val image = loadAndShowOrExit(new File("data/boldt.jpg"), CV_LOAD_IMAGE_COLOR)

  // Add salt noise
  val dest = salt(image, 2000)

  // Display
  show(dest, "Salted")


  /**
   * Add 'salt' noise.
   * @param image input image.
   * @param n number of 'salt' grains.
   */
  def salt(image: Mat, n: Int): Mat = {

    val nbChannels = image.channels
    val random = new Random()
    // Get access to image data
    val indexer = image.createIndexer().asInstanceOf[ByteIndexer]

    // Place `n` grains at random locations
    for (i <- 1 to n) {
      // Create random index of a pixel
      val row = random.nextInt(image.rows)
      val col = random.nextInt(image.cols)
      // Set it to white by setting each of the channels to max (255)
      for (i <- 0 until nbChannels) {
        indexer.put(row, col, i, 255.toByte)
      }
    }

    image
  }
}