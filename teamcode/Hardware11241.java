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

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left Front drive motor:          "leftFrontDrive"
 * Motor channel:  Right Front drive motor:         "rightFrontDrive"
 * Motor channel:  Left Rear drive motor:           "leftRearDrive"
 * Motor channel:  Right Rear drive motor:          "leftRearDrive"
 * Motor channel:  Duck Spinner:                    "duckSpinner"
 * Motor channel:  Lift Arm:                        "liftArm"
 * Motor channel:  Extender:                        "extender"
 * Motor channel:  Gripper:                         "gripper"
 * Servo channel:  Servo to open left Gate:         "leftGate"
 * Servo channel:  Servo to open right Gate:        "rightGate"
 * Servo channel:  Servo to open claw:              "claw"

 */
public class Hardware11241
{
    /* Public OpMode members. */
    public DcMotor  leftFrontDrive    = null;
    public DcMotor  rightFrontDrive   = null;
    public DcMotor  leftRearDrive     = null;
    public DcMotor  rightRearDrive    = null;
    public DcMotor  duckSpinner       = null;
    public DcMotor  liftArm           = null;
    public DcMotor  extender          = null;
    //public DcMotor  gripper          = null;
    //public Servo    leftGate          = null;
    //public Servo    rightGate         = null;
    public Servo    claw              = null;


    public static final double MID_SERVO       =  0.50 ;

    /* local OpMode members. */
    HardwareMap hwMap           = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Hardware11241(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftFrontDrive  = hwMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hwMap.get(DcMotor.class, "rightFrontDrive");
        leftRearDrive   = hwMap.get(DcMotor.class, "leftRearDrive");
        rightRearDrive  = hwMap.get(DcMotor.class, "rightRearDrive");
        duckSpinner   = hwMap.get(DcMotor.class,   "duckSpinner");
        liftArm   = hwMap.get(DcMotor.class,       "liftArm");
        extender  = hwMap.get(DcMotor.class,       "extender");
        //gripper  = hwMap.get(DcMotor.class,        "gripper");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);  // Set to REVERSE FOR goBILDA motors
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD); // Set to FORWARD FOR goBILDA motors
        leftRearDrive.setDirection(DcMotor.Direction.REVERSE);   // Set to REVERSE FOR goBILDA motors
        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);  // Set to FORWARD FOR goBILDA motors
        duckSpinner.setDirection(DcMotor.Direction.FORWARD);     // Set to ? FOR Andy Mark motors
        liftArm.setDirection(DcMotor.Direction.REVERSE);         // Set to ? FOR Andy Mark motors
        extender.setDirection(DcMotor.Direction.FORWARD);        // Set to ? FOR Andy Mark motors
        //gripper.setDirection(DcMotor.Direction.FORWARD);        // Set to ? FOR Andy Mark motors

        // Set all motors to zero power
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftRearDrive.setPower(0);
        leftRearDrive.setPower(0);
        duckSpinner.setPower (0) ;
        liftArm.setPower (0) ;
        extender.setPower (0) ;
        //gripper.setPower (0) ;

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        duckSpinner.setMode (DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftArm.setMode (DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        extender.setMode (DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //gripper.setMode (DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        //leftGate  = hwMap.get(Servo.class, "leftGate");
        //rightGate = hwMap.get(Servo.class, "rightGate");
        claw = hwMap.get(Servo.class, "claw");
        //leftGate.setPosition(MID_SERVO);
        //rightGate.setPosition(MID_SERVO);
        claw.setPosition(0.50);
    }
 }

