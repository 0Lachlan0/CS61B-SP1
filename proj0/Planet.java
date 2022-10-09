public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, 
				  double yV, double m, String img) {
		xxPos = xP; yyPos = yP; xxVel = xV; yyVel = yV;
		mass = m; imgFileName = img;
	}	
	public Planet(Planet P) {
		xxPos = P.xxPos; yyPos = P.yyPos; xxVel = P.xxVel; yyVel = P.yyVel;
		mass = P.mass; imgFileName = P.imgFileName;
	}
	public double calcDistance(Planet P) {
		double xx=P.xxPos-xxPos;
		double yy=P.yyPos-yyPos;
		return Math.sqrt(xx*xx+yy*yy);
	}
	public double calcForceExertedBy(Planet P) {
		double dis = calcDistance(P);
		return G*P.mass*mass/(dis*dis);
	}
	public double calcForceExertedByX(Planet P) {
		double force = calcForceExertedBy(P);
		double dis = calcDistance(P);
		return force*((P.xxPos-xxPos)/dis);
	}
	public double calcForceExertedByY(Planet P) {
		double force = calcForceExertedBy(P);
		double dis = calcDistance(P);
		return force*((P.yyPos-yyPos)/dis);
	}
	public double calcNetForceExertedByX(Planet[] P) {
		double total = 0;
		for(Planet P1: P) {
			if(this.equals(P1))
				continue;
			total += calcForceExertedByX(P1);
		}
		return total;
	}
	public double calcNetForceExertedByY(Planet[] P) {
		double total = 0;
		for(Planet P1: P) {
			if(this.equals(P1))
				continue;
			total += calcForceExertedByY(P1);
		}
		return total;
	}
	public void update(double dt, double  fX, double fY) {
		double aX = fX/mass;
		double aY = fY/mass;
		xxVel += aX*dt;
		yyVel += aY*dt;
		xxPos += xxVel*dt;
		yyPos += yyVel*dt;
	}
	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}