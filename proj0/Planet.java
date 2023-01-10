public class Planet{
    private static final double G=6.67e-11;
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        this.xxPos=xP;
        this.yyPos=yP;
        this.xxVel=xV;
        this.yyVel=yV;
        this.mass=m;
        this.imgFileName=img;
    }

    public Planet(Planet p){
        this.xxPos=p.xxPos;
        this.yyPos=p.yyPos;
        this.xxVel=p.xxVel;
        this.yyVel=p.yyVel;
        this.mass=p.mass;
        this.imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet p){
        return Math.pow(Math.pow(this.xxPos-p.xxPos, 2) + Math.pow(this.yyPos-p.yyPos, 2), 0.5);
    }

    public double calcForceExertedBy(Planet p){
        double r = this.calcDistance(p);
        return G*mass*p.mass/r/r;
    }

    public double calcForceExertedByX(Planet p){
        return this.calcForceExertedBy(p)*(p.xxPos-this.xxPos)/this.calcDistance(p);
    }

    public double calcForceExertedByY(Planet p){
        return this.calcForceExertedBy(p)*(p.yyPos-this.yyPos)/this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double xforce=0;
        for (Planet p : allPlanets) {
            if (p!=this)
                xforce+=this.calcForceExertedByX(p);
        }
        return xforce;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double yforce=0;
        for (Planet p : allPlanets) {
            if (p!=this)
                yforce+=this.calcForceExertedByY(p);
        }
        return yforce;
    }

    public void update(double dt, double fX, double fY){
        this.xxVel += fX/this.mass*dt;
        this.yyVel += fY/this.mass*dt;
        this.xxPos += this.xxVel*dt;
        this.yyPos += this.yyVel*dt;
    }

    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/"+this.imgFileName);
    }
}