import math.Vec2;
import processing.core.PApplet;
import simulator.OdometryData;
import simulator.Simulator;

public class SimulatorTest extends PApplet {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    public static int SCALE = 100;

    Simulator sim;

    public void settings() {
        size(WIDTH, HEIGHT, P2D);
    }

    public void setup() {
        surface.setTitle("Processing");
        colorMode(RGB, 1.0f);
        rectMode(CENTER);
        noStroke();

        String scene_name = "data/simple_rectangle.scn";
        sim = new Simulator(this, scene_name);
    }

    public void draw() {
        background(0);
        stroke(1);

        OdometryData odom = sim.getOdometryThreadSafe();
        sim.draw(SCALE, WIDTH, HEIGHT);
        surface.setTitle("Processing - FPS: " + Math.round(frameRate));
    }

    public void keyPressed() {
        if (key == 'p') {
            sim.applyControlThreadSafe(Vec2.zero());
        }
        if (keyCode == UP) {
            sim.applyControlThreadSafe(Vec2.of(10, 0));
        }
        if (keyCode == DOWN) {
            sim.applyControlThreadSafe(Vec2.of(-10, 0));
        }
        if (keyCode == LEFT) {
            sim.applyControlThreadSafe(Vec2.of(0, -0.5));
        }
        if (keyCode == RIGHT) {
            sim.applyControlThreadSafe(Vec2.of(0, 0.5));
        }
        if (key == '+') {
            SCALE++;
        }
        if (key == '-') {
            SCALE--;
        }
    }

    static public void main(String[] passedArgs) {
        String[] appletArgs = new String[]{"SimulatorTest"};
        if (passedArgs != null) {
            PApplet.main(concat(appletArgs, passedArgs));
        } else {
            PApplet.main(appletArgs);
        }
    }
}
