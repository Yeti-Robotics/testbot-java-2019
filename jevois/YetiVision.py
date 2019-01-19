
import libjevois as jevois
import cv2
import numpy as np
import time
import re
from datetime import datetime

## Detects stuff for FRC
#
# Add some description of your module here.
#
# @author Robot Casserole
# 
# @videomapping YUYV 352 288 15 YUYV 352 288 60 RobotCasserole CasseroleVision
# @email 
# @address 123 first street, Los Angeles CA 90012, USA
# @copyright Copyright (C) 2018 by Robot Casserole
# @mainurl 
# @supporturl 
# @otherurl 
# @license 
# @distribution Unrestricted
# @restrictions None
# @ingroup modules
class YetiVision:
    # ###################################################################################################
    ## Constructor
    def __init__(self):
       
        self.timer = jevois.Timer("CasseroleVisionStats", 25, jevois.LOG_DEBUG)
        self.frame = 0
        self.framerate_fps = "0"
        self.frame_dec_factor = 6
       
        self.hsv_threshold_min = [24.280575539568336, 192.62589928057554, 197.21223021582733]
        self.hsv_threshold_max = [180.0, 255.0, 255.0]

        # self.__hsv_threshold_hue = [24.280575539568336, 180.0]
        # self.__hsv_threshold_saturation = [192.62589928057554, 255.0]
        # self.__hsv_threshold_value = [197.21223021582733, 255.0]

        self.hsv_threshold_output = None

        self.__find_contours_input = self.hsv_threshold_output
        self.__find_contours_external_only = True

        self.find_contours_output = None

        self.__filter_contours_contours = self.find_contours_output
        self.__filter_contours_min_area = 0
        self.__filter_contours_min_perimeter = 0
        self.__filter_contours_min_width = 0
        self.__filter_contours_max_width = 1000
        self.__filter_contours_min_height = 0
        self.__filter_contours_max_height = 1000
        self.__filter_contours_solidity = [0, 100]
        self.__filter_contours_max_vertices = 1000000
        self.__filter_contours_min_vertices = 0.0
        self.__filter_contours_min_ratio = 0.3
        self.__filter_contours_max_ratio = 0.9

        self.filter_contours_output = None

    # ###################################################################################################
    ## Process function with only Serial output
    def processNoUSB(self, inframe):
        self.processCommon( inframe, None)
        
    # ###################################################################################################
    ## Process function with USB Video output
    def process(self, inframe, outframe):
        self.processCommon( inframe, outframe)


    # ###################################################################################################
    ## Process Common
    def processCommon(self, inframe, outframe):
        # Start measuring image processing time:
        self.timer.start()

        #No targets found yet
        self.tgtAvailable = False
        self.curTargets = []
        
        #Capture image from camera
        inimg = inframe.getCvBGR()
        self.frame += 1

        #Mark start of pipeline time
        pipline_start_time = datetime.now()

        # Step HSV_Threshold0:
        # self.__hsv_threshold_input = source0
        # (self.hsv_threshold_output) = self.__hsv_threshold(self.__hsv_threshold_input, self.__hsv_threshold_hue, self.__hsv_threshold_saturation, self.__hsv_threshold_value)

        # # Step Find_Contours0:
        # self.__find_contours_input = self.hsv_threshold_output
        # (self.find_contours_output) = self.__find_contours(self.__find_contours_input, self.__find_contours_external_only)

        # # Step Filter_Contours0:
        # self.__filter_contours_contours = self.find_contours_output
        # (self.filter_contours_output) = self.__filter_contours(self.__filter_contours_contours, self.__filter_contours_min_area, self.__filter_contours_min_perimeter, self.__filter_contours_min_width, self.__filter_contours_max_width, self.__filter_contours_min_height, self.__filter_contours_max_height, self.__filter_contours_solidity, self.__filter_contours_max_vertices, self.__filter_contours_min_vertices, self.__filter_contours_min_ratio, self.__filter_contours_max_ratio)

        # def getArea(con): # Gets the area of the contour
        #     return cv2.contourArea(con)

        # def getYcoord(con): # Gets the Y coordinate of the contour
        #     M = cv2.moments(con)
        #     try:
        #         cy = int(M['m01']/M['m00'])
        #     except ZeroDivisionError:
        #         cy = 0
        #     return cy

        # def getXcoord(con): # Gets the X coordinate of the contour
        #     M = cv2.moments(con)
        #     try:
        #         cy = int(M['m10']/M['m00'])
        #     except ZeroDivisionError:
        #         cy = 0
        #     return cy

        # def sortByArea(conts) : # Returns an array sorted by area from smallest to largest
        #     contourNum = len(conts) # Gets number of contours
        #     sortedBy = sorted(conts, key=getArea) # sortedBy now has all the contours sorted by area
        #     return sortedBy
        
        # Draws all contours on original image in red
        # cv2.drawContours(outimg, self.filter_contours_output, -1, (0, 0, 255), 1)
        
        # # Gets number of contours
        # contourNum = len(self.filter_contours_output)

        # # Sorts contours by the smallest area first
        # newContours = sortByArea(self.filter_contours_output)       

        # jevois.sendSerial("hi")
        # # Send the contour data over Serial
        # for i in range (contourNum):
        #     cnt = newContours[i]
        #     x,y,w,h = cv2.boundingRect(cnt) # Get the stats of the contour including width and height
            
        #     # which contour, 0 is first
        #     toSend = ("CON" + str(i) +  
        #              "area" + str(getArea(cnt)) +  # Area of contour
        #              "x" + str(round((getXcoord(cnt)*1000/320)-500, 2)) +  # x-coordinate of contour, -500 to 500 rounded to 2 decimal
        #              "y" + str(round(375-getYcoord(cnt)*750/240, 2)) +  # y-coordinate of contour, -375 to 375 rounded to 2 decimal
        #              "h" + str(round(h*750/240, 2)) +  # Height of contour, 0-750 rounded to 2 decimal
        #              "w" + str(round(w*1000/320, 2))) # Width of contour, 0-1000 rounded to 2 decimal
        #     jevois.sendSerial(toSend)
            
        # # Write a title:
        # cv2.putText(outimg, "YetiVision", (3, 20), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255,255,255), 1, cv2.LINE_AA)
        
        # # Write frames/s info from our timer into the edge map (NOTE: does not account for output conversion time):
        # fps = self.timer.stop()
        # #height, width, channels = outimg.shape # if outimg is grayscale, change to: height, width = outimg.shape
        # height, width, channels = outimg.shape
        # cv2.putText(outimg, fps, (3, height - 6), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255,255,255), 1, cv2.LINE_AA)

        # # Convert our BGR output image to video output format and send to host over USB. If your output image is not
        # # BGR, you can use sendCvGRAY(), sendCvRGB(), or sendCvRGBA() as appropriate:
        # outframe.sendCvBGR(outimg)
        # outframe.sendCvGRAY(outimg)
        

        # ###############################################
        # ## Start Image Processing Pipeline
        # ###############################################
        # # Move the image to HSV color space
        # hsv = cv2.cvtColor(inimg, cv2.COLOR_BGR2HSV)

        # #Create a mask of only pixells which match the HSV color space thresholds we've determined
        # hsv_mask = cv2.inRange(hsv,self.hsv_thres_lower, self.hsv_thres_upper)

        # # Erode image to remove noise if necessary.
        # hsv_mask = cv2.erode(hsv_mask, None, iterations = 3)
        # #Dilate image to fill in gaps
        # hsv_mask = cv2.dilate(hsv_mask, None, iterations = 3)

        # #Find all countours of the outline of shapes in that mask
        # contours,hierarchy  = cv2.findContours(hsv_mask, cv2.RETR_LIST, cv2.CHAIN_APPROX_TC89_KCOS)

        # #Extract Pertenant params from contours
        # for c in contours:
        #     #Calcualte unrotated bounding rectangle (top left corner x/y, plus width and height)
        #     br_x, br_y, w, h = cv2.boundingRect(c)
        #     #minimal amount of qualification on targets
        #     if(w > 5 and h > 5): 
        #         moments = cv2.moments(c)
        #         if(moments['m00'] != 0):
        #             #Calculate total filled in area
        #             area = cv2.contourArea(c)
        #             #Calculate centroid X and Y
        #             c_x = int(moments['m10']/moments['m00'])
        #             c_y = int(moments['m01']/moments['m00'])
        #             self.curTargets.append(TargetObservation(c_x, c_y, area, w, h)) 

        # #If we have some contours, figure out which is the target.
        # if(len(self.curTargets) > 0):
        #     self.tgtAvailable = True
        #     #Find the best contour, which we will call the target
        #     best_target = self.curTargets[0] #Start presuming the first is the best
        #     for tgt in self.curTargets[1:]:
        #         #Super-simple algorithm: biggest target wins
        #         #TODO - make this better
        #         if(tgt.boundedArea > best_target.boundedArea):
        #             best_target = tgt 


        # # Calculate target physical location and populate output
        # if(self.tgtAvailable == True):
        #     #TODO: Actual math to make this right
        #     self.tgtAngle = best_target.X
        #     self.tgtRange = best_target.boundedArea
        # else:
        #     self.tgtAngle = 0
        #     self.tgtRange = 0

        ###############################################
        ## End Image Processing Pipeline
        ###############################################

        #Mark end of pipline
        # For accuracy, Must be done as close to sending the serial data as possible 
        pipeline_end_time = datetime.now() - pipline_start_time
        self.pipelineDelay_us = pipeline_end_time.microseconds
        
        # Send processed data about target location and current status
        # Note the order and number of params here must match with the roboRIO code.
        jevois.sendSerial("{{{},{},{},{}}}\n".format(self.frame,("T" if self.tgtAvailable else "F"), self.framerate_fps,self.pipelineDelay_us))
        

        # Broadcast the frame if we have an output sink available
        # if(outframe != None):
        #     #Even if we're connected, don't send every frame we process. This will
        #     # help keep our USB bandwidth usage down.
        #     if(self.frame  % self.frame_dec_factor == 0):
        #         #Generate a debug image of the input image, masking non-detected pixels
        #         outimg = inimg

        #         #Overlay target info if found
        #         if(self.tgtAvailable):
        #             top    = int(best_target.Y - best_target.height/2)
        #             bottom = int(best_target.Y + best_target.height/2)
        #             left   = int(best_target.X - best_target.width/2)
        #             right  = int(best_target.X + best_target.width/2)
        #             cv2.rectangle(outimg, (right,top),(left,bottom),(0,255,0), 2,cv2.LINE_4)

        #         # We are done with the output, ready to send it to host over USB:
        #         outframe.sendCvBGR(outimg)

        # # Track Processor Statistics
        # results = self.pattern.match(self.timer.stop())
        # if(results is not None):
        #     self.framerate_fps = results.group(1)
        #     self.CPULoad_pct = results.group(2)
        #     self.CPUTemp_C = results.group(3)
            

    # ###################################################################################################
    ## Parse a serial command forwarded to us by the JeVois Engine, return a string
    def parseSerial(self, str):

        if(str.strip() == ""):
            #For some reason, the jevois engine sometimes sends empty strings.
            # Just do nothing in this case.
            return ""

        jevois.LINFO("parseserial received command [{}]".format(str))
        if str == "hello":
            return self.hello()
        elif str == "Geevoooice":
            return self.hi()
        return "ERR: Unsupported command. "

    # ###################################################################################################
    ## Return a string that describes the custom commands we support, for the JeVois help message
    def supportedCommands(self):
        # use \n seperator if your module supports several commands
        return "hello - print hello using python"

    # ###################################################################################################
    ## Internal method that gets invoked as a custom command
    def hello(self):
        return "Hello from python!"
    
    def hi(self):
        return "Hi from python!"

            
    @staticmethod
    def __hsv_threshold(input, hue, sat, val):
        """Segment an image based on hue, saturation, and value ranges.
        Args:
            input: A BGR numpy.ndarray.
            hue: A list of two numbers the are the min and max hue.
            sat: A list of two numbers the are the min and max saturation.
            lum: A list of two numbers the are the min and max value.
        Returns:
            A black and white numpy.ndarray.
        """
        out = cv2.cvtColor(input, cv2.COLOR_BGR2HSV)
        return cv2.inRange(out, (hue[0], sat[0], val[0]),  (hue[1], sat[1], val[1]))

    @staticmethod
    def __find_contours(input, external_only):
        """Sets the values of pixels in a binary image to their distance to the nearest black pixel.
        Args:
            input: A numpy.ndarray.
            external_only: A boolean. If true only external contours are found.
        Return:
            A list of numpy.ndarray where each one represents a contour.
        """
        if(external_only):
            mode = cv2.RETR_EXTERNAL
        else:
            mode = cv2.RETR_LIST
        method = cv2.CHAIN_APPROX_SIMPLE
        contours, hierarchy =cv2.findContours(input, mode=mode, method=method)
        return contours

    @staticmethod
    def __filter_contours(input_contours, min_area, min_perimeter, min_width, max_width,
                        min_height, max_height, solidity, max_vertex_count, min_vertex_count,
                        min_ratio, max_ratio):
        """Filters out contours that do not meet certain criteria.
        Args:
            input_contours: Contours as a list of numpy.ndarray.
            min_area: The minimum area of a contour that will be kept.
            min_perimeter: The minimum perimeter of a contour that will be kept.
            min_width: Minimum width of a contour.
            max_width: MaxWidth maximum width.
            min_height: Minimum height.
            max_height: Maximimum height.
            solidity: The minimum and maximum solidity of a contour.
            min_vertex_count: Minimum vertex Count of the contours.
            max_vertex_count: Maximum vertex Count.
            min_ratio: Minimum ratio of width to height.
            max_ratio: Maximum ratio of width to height.
        Returns:
            Contours as a list of numpy.ndarray.
        """
        output = []
        for contour in input_contours:
            x,y,w,h = cv2.boundingRect(contour)
            if (w < min_width or w > max_width):
                continue
            if (h < min_height or h > max_height):
                continue
            area = cv2.contourArea(contour)
            if (area < min_area):
                continue
            if (cv2.arcLength(contour, True) < min_perimeter):
                continue
            hull = cv2.convexHull(contour)
            try:
                solid = 100 * area / cv2.contourArea(hull)
            except ZeroDivisionError:
                print("****************")
                print(area)
                continue
            if (solid < solidity[0] or solid > solidity[1]):
                continue
            if (len(contour) < min_vertex_count or len(contour) > max_vertex_count):
                continue
            ratio = (float)(w) / h
            if (ratio < min_ratio or ratio > max_ratio):
                continue
            output.append(contour)
        return output


#BlurType = Enum('BlurType', 'Box_Blur Gaussian_Blur Median_Filter Bilateral_Filter')


        


class TargetObservation(object):
    def __init__(self, X_in, Y_in, area_in, width_in, height_in):
        self.X = (X_in)
        self.Y = (Y_in)
        self.boundedArea = (area_in)
        self.width = (width_in)
        self.height = (height_in)
        