import camera.QueasyCam
import math.Vec2
import org.ejml.data.DMatrixRMaj
import org.ejml.dense.row.CommonOps_DDRM
import processing.core.PApplet
import processing.core.PConstants
import simulator.Laser
import simulator.Robot
import simulator.Simulator

class Main : PApplet() {
    companion object {
        const val WIDTH = 800
        const val HEIGHT = 800
    }

    var sim: Simulator? = null
    var cam: QueasyCam? = null
    override fun settings() {
        size(WIDTH, HEIGHT, PConstants.P3D)
    }

    override fun setup() {
        surface.setTitle("Processing")
        colorMode(PConstants.RGB, 1.0f)
        rectMode(PConstants.CENTER)
        noStroke()
        cam = QueasyCam(this)
        reset()
    }

    private fun reset() {
        val scene_name = "data/apartment.scn"
        // Play with these
        Simulator.SCALE = 100.0
        Simulator.CONTROL_FREQ = 1
        Simulator.LASER_SCAN_FREQUENCY = 10
        Robot.MAX_LINEAR_ACCELERATION = 20.0
        Robot.LINEAR_VELOCITY_ERROR_LIMIT = 2.0
        Robot.MAX_ANGULAR_ACCELERATION = 0.5
        Robot.ANGULAR_VELOCITY_ERROR_LIMIT = 0.1
        Laser.ANGLE_ERROR_LIMIT = 0.05
        Laser.MAX_DISTANCE = 500.0
        Laser.DISTANCE_ERROR_LIMIT = 5.0
        sim = Simulator(this, scene_name)
    }

    override fun draw() {
        background(0)
        stroke(1)
        sim!!.draw()
        surface.setTitle("Processing - FPS: " + Math.round(frameRate))
    }

    override fun keyPressed() {
        if (key == 'r') {
            reset()
        }
        if (key == 'p') {
            sim!!.applyControl(Vec2.zero())
        }
        if (keyCode == PConstants.UP) {
            sim!!.applyControl(Vec2.of(100.0, 0.0))
        }
        if (keyCode == PConstants.DOWN) {
            sim!!.applyControl(Vec2.of(-100.0, 0.0))
        }
        if (keyCode == PConstants.LEFT) {
            sim!!.applyControl(Vec2.of(0.0, -0.5))
        }
        if (keyCode == PConstants.RIGHT) {
            sim!!.applyControl(Vec2.of(0.0, 0.5))
        }
    }
}

fun main(passedArgs: Array<String>) {
    val appletArgs = arrayOf("Main")
    if (passedArgs != null) {
        PApplet.main(PApplet.concat(appletArgs, passedArgs))
    } else {
        PApplet.main(appletArgs)
    }
}