/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * This file contains a minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Blue Steel TeleOp Mode", group="Linear Opmode")
//@Disabled
public class BlueSteelTeleOpMode extends LinearOpMode {

    // Declare OpMode members.
    Hardware11241 robot           = new Hardware11241();   // Use Team 11241's hardware

    public ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables.
        // * The init() method of the hardware class does all the work here
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftFrontPower;
            double rightFrontPower;
            double leftRearPower;
            double rightRearPower;
            //init for claw
            int i = 0;
            // POV Mode uses right stick to go forward/back, and side to side, left x stick to turn/spin.
            // - This uses basic math to combine motions and is easier to drive straight.
            double driveForward = -gamepad1.right_stick_y;
            double driveSideways  =  -gamepad1.right_stick_x;
            double turn  =  gamepad1.left_stick_x;
            leftFrontPower    = Range.clip(driveForward - driveSideways + turn, -1.0, 1.0) ;
            rightFrontPower   = Range.clip(driveForward + driveSideways - turn, -1.0, 1.0) ;
            leftRearPower    = Range.clip(driveForward + driveSideways + turn, -1.0, 1.0) ;
            rightRearPower   = Range.clip(driveForward - driveSideways - turn, -1.0, 1.0) ;

            // D pad controls liftArm.
            if (gamepad1.dpad_up)
               robot.liftArm.setPower (-1);
            if (gamepad1.dpad_down)
               robot.liftArm.setPower (0.5);
            //if (gamepad1.dpad_right)
            // robot.gripper.setPower (1);
            //if (gamepad1.dpad_left)
            //robot.gripper.setPower (-1);
            else
            robot.liftArm.setPower (0);
            //robot.gripper.setPower (0);

            // button a score and y reverse is duckSpinner.
            if (gamepad1.a)
                robot.duckSpinner.setPower (1);
            if (gamepad1.y)
                robot.duckSpinner.setPower (-1);
            //
            else
                robot.duckSpinner.setPower (0);

            //if (gamepad1.left_bumper)
            //robot.leftGate.setPosition (.03);
            //if (gamepad1.left_bumper)
            //    robot.rightGate.setPosition (.99);
            if (gamepad1.right_bumper) {
                //open
                robot.claw.setPosition(0.50);
            }
            if (gamepad1.left_bumper){
                robot.claw.setPosition(0.60);
            }
            // right/left trigger controls the extender
            robot.extender.setPower(-gamepad1.left_trigger);
            robot.extender.setPower(gamepad1.right_trigger);

            // Send calculated power to wheels
            robot.leftFrontDrive.setPower(leftFrontPower);
            robot.rightFrontDrive.setPower(rightFrontPower);
            robot.leftRearDrive.setPower(leftRearPower);
            robot.rightRearDrive.setPower(rightRearPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "LF (%.2f), RF (%.2f), LR (%.2f), RR (%.2f)", leftFrontPower, rightFrontPower, leftRearPower, rightRearPower);
            telemetry.update();
        }
    }
}