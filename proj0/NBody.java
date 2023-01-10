public class NBody {
    private static double radius;

    public static double readRadius(String path) {
        In in = new In(path);

        in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int num = in.readInt();
        radius = in.readDouble();


        Planet[] planets = new Planet[num];

        for (int i = 0; i < num; i++) {
            planets[i] = new Planet(
                    in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readString());
        }

        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        // read data
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-radius, radius);

        StdDraw.picture(0.5, 0.5, "images/starfield.jpg");
        for (Planet p : planets) {
            p.draw();
        }
        StdDraw.enableDoubleBuffering();
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];
        for (double t = 0; t < T; t += dt) {
            // 更新所有行星的状态
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            // 画背景
            StdDraw.picture(0.5, 0.5, "images/starfield.jpg");
            // 画行星
            for (Planet p : planets) {
                p.draw();
            }
            // show
            StdDraw.show();

            StdDraw.pause(10);
        }

        // 输出最终状态
        System.out.println(planets.length);
        System.out.println(radius);
        for (Planet p : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
            p.xxPos,p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
        }
    }

}