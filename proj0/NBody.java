public class NBody {
	public static double readRadius(String filename) {
		In in = new In(filename);
		in.readInt();
		return in.readDouble();
	}
	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);
		int x = in.readInt(); 
		in.readDouble();
		Planet[] P = new Planet[x];
		for(int i=0; i<x; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();	
			Planet P1 = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			P[i] = P1;
		}
		return P;
	} 
	public static void main(String[] args)
		{
			StdDraw.enableDoubleBuffering();

			double T = Double.parseDouble(args[0]);
			double dt = Double.parseDouble(args[1]);
			String filename = args[2];
			double Radius = readRadius(filename);
			StdDraw.setScale(-Radius, Radius);
			StdDraw.picture(0,0,"images/starfield.jpg");
			Planet[] planets = readPlanets(filename);
			int n = planets.length;
			for(Planet p : planets) {
				p.draw();
			}
			for(double t=0; t<T; t += dt) {
				double[] xForces = new double[n];
				double[] yForces = new double[n];
				for(int i=0; i<n; i++) {
					xForces[i] = planets[i].calcNetForceExertedByX(planets);
					yForces[i] = planets[i].calcNetForceExertedByY(planets);
					planets[i].update(dt, xForces[i], yForces[i]);
				}
				StdDraw.picture(0,0,"images/starfield.jpg");
				for(Planet p : planets) {
					p.draw();
				}
				StdDraw.show();
            	StdDraw.pause(10);
			}
			StdOut.printf("%d\n", planets.length);
        	StdOut.printf("%.2e\n", Radius);
        	for (int i = 0; i < planets.length; i++) {
            	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", planets[i].xxPos, planets[i].yyPos,
                planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        	}

		}
}