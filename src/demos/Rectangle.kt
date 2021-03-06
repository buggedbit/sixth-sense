package demos

import processing.core.PApplet
import robot.sensing.RANSACLeastSquares
import simulator.LaserSensor
import simulator.Simulator

fun main() {
    // Settings
    LaserSensor.DRAW_LASERS = false
    RANSACLeastSquares.DRAW_PARTITIONS = false
    Simulator.GHOST_MODE = true
    RANSACLeastSquares.DISCONTINUITY_THRESHOLD = 60.0
    // Scene
    val appletArgs = arrayOf("demos.Simulation", "data/simple_rectangle.scn", "160f", "50f")
    PApplet.main(appletArgs)
}
