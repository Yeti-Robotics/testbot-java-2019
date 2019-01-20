/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.controls;

import java.util.ArrayList;

import org.opencv.core.Rect;

/**
 * Add your docs here.
 */
public class VisionProcessor {
    private Contour rightCon, leftCon;

    public VisionProcessor(Contour rightCon, Contour leftCon) {
        this.rightCon = rightCon;
        this.leftCon = leftCon;
    } 

    public Rect boundRect() {
        return new Rect(leftCon.x, leftCon.y, (rightCon.x + rightCon.w) - leftCon.x, rightCon.h);
    }
}
