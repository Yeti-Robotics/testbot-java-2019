/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

/**
 * Add your docs here.
 */
public class Contour {
    public double area;
    public int x, y, w, h;

    public Contour(String area, String x, String y, String h, String w) {
        this.area = Double.parseDouble(area);
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        this.w = Integer.parseInt(w);
        this.h = Integer.parseInt(h);
    }

    public String toString(){
        return "area: " + area + ", " +  "x: " + x + ", " +  "y: " + y + ", " +  "h: " + h + ", " +  "w: " + w;
    }
}
