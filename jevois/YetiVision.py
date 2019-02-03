
import libjevois as jevois
import cv2
import numpy as np
import time
import re
from datetime import datetime
from enum import Enum



class Contour(Enum):
    LEFT = 1
    RIGHT = 2





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
       

        self.__resize_image_width = 320.0
        self.__resize_image_height = 240.0
        self.__resize_image_interpolation = cv2.INTER_CUBIC

        self.resize_image_output = None

        self.__hsv_threshold_input = self.resize_image_output
        self.__hsv_threshold_hue = np.array([63.12949640287769, 95.15151515151516])
        self.__hsv_threshold_saturation = np.array([176.57374100719423, 255.0])
        self.__hsv_threshold_value = np.array([146.76258992805754, 255.0])

        self.hsv_threshold_output = None

        self.__find_contours_input = self.hsv_threshold_output
        self.__find_contours_external_only = True

        self.find_contours_output = None

        self.__filter_contours_contours = self.find_contours_output
        self.__filter_contours_min_area = 200.0
        self.__filter_contours_min_perimeter = 0.0
        self.__filter_contours_min_width = 0.0
        self.__filter_contours_max_width = 1000.0
        self.__filter_contours_min_height = 0.0
        self.__filter_contours_max_height = 1000.0
        self.__filter_contours_solidity = np.array([0, 100])
        self.__filter_contours_max_vertices = 1000000.0
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
        outimg = inframe.getCvBGR()
        self.frame += 1

        #Mark start of pipeline time
        pipline_start_time = datetime.now()

        # hsv = cv2.cvtColor(inimg, cv2.COLOR_BGR2HSV)
        # hsv_max = cv2.inRange(hsv, self.hsv_threshold_min, self.hsv_threshold_max)
        # Step HSV_Threshold0:
        self.__hsv_threshold_input = inimg
        (self.hsv_threshold_output) = self.__hsv_threshold(self.__hsv_threshold_input, self.__hsv_threshold_hue, self.__hsv_threshold_saturation, self.__hsv_threshold_value)

        # # Step Find_Contours0:
        self.__find_contours_input = self.hsv_threshold_output
        (self.find_contours_output) = self.__find_contours(self.__find_contours_input, self.__find_contours_external_only)

        # # Step Filter_Contours0:
        self.__filter_contours_contours = self.find_contours_output
        (self.filter_contours_output) = self.__filter_contours(self.__filter_contours_contours, self.__filter_contours_min_area, self.__filter_contours_min_perimeter, self.__filter_contours_min_width, self.__filter_contours_max_width, self.__filter_contours_min_height, self.__filter_contours_max_height, self.__filter_contours_solidity, self.__filter_contours_max_vertices, self.__filter_contours_min_vertices, self.__filter_contours_min_ratio, self.__filter_contours_max_ratio)

        def getArea(con): # Gets the area of the contour
            return cv2.contourArea(con)

        def getYcoord(con): # Gets the Y coordinate of the contour
            M = cv2.moments(con)
            try:
                cy = int(M['m01']/M['m00'])
            except ZeroDivisionError:
                cy = 0
            return cy

        def getXcoord(con): # Gets the X coordinate of the contour
            M = cv2.moments(con)
            try:
                cy = int(M['m10']/M['m00'])
            except ZeroDivisionError:
                cy = 0
            return cy

        def sortByArea(conts) : # Returns an array sorted by area from smallest to largest
            contourNum = len(conts) # Gets number of contours
            sortedBy = sorted(conts, key=getArea) # sortedBy now has all the contours sorted by area
            return sortedBy

        def determineContourOrientation(contour):
            rect = cv2.minAreaRect(contour)
            box = cv2.boxPoints(rect)
            box = np.int0(box)
            sortedBox = sorted(box, key = lambda point: point[1])

            topPoint = sortedBox[0]
            bottomPoint = sortedBox[3]

            if (topPoint[0] > bottomPoint[0]):
                return Contour.RIGHT
            else:
                return Contour.LEFT




    # make a function to compare 2 contours and return whether they're pairs
        def compareContours(contourA, contourB):
            rectA = cv2.minAreaRect(contourA)
            boxA = cv2.boxPoints(rectA)
            boxA = np.int0(boxA)
            sortedBoxA = sorted(boxA, key = lambda point: point[0])
            rectB = cv2.minAreaRect(contourB)
            boxB = cv2.boxPoints(rectB)
            boxB = np.int0(boxB)
            sortedBoxB = sorted(boxB, key = lambda point: point[0])

            if ((sortedBoxA[0][0] < sortedBoxB[0][0] and determineContourOrientation(contourA) == Contour.LEFT and determineContourOrientation(contourB) == Contour.RIGHT) or 
                (sortedBoxB[0][0] < sortedBoxA[0][0] and determineContourOrientation(contourB) == Contour.LEFT and determineContourOrientation(contourA) == Contour.RIGHT)):
                return True
            else:
                return False

        
        
        #Draws all contours on original image in red
        # cv2.drawContours(outimg, self.filter_contours_output, -1, (0, 0, 255), 1)
        
        # Gets number of contours
        contourNum = len(self.filter_contours_output)

        # Sorts contours by the smallest area first
        newContours = sortByArea(self.filter_contours_output)

        

        # if contourNum == 3
        #       get rotated rectangles
        #       figure out the correct pair
        #       discard the odd one out
        #       continue
        
        
        # Send the contour data over Serial
        if(contourNum == 2):
            message = ""
            if (compareContours(newContours[0], newContours[1])):
                message = message + "T: "
            else:
                message = message + "F: "
            for i in range (contourNum):
                cnt = newContours[i]
                x,y,w,h = cv2.boundingRect(cnt) # Get the stats of the contour including width and height
            
                area = str(getArea(cnt))
                # x = str(round((getXcoord(cnt)*1000/320)-500, 2))
                # y = str(round(375-getYcoord(cnt)*750/240, 2))
                # h = str(round(h*750/240, 2))
                # w = str(round(w*1000/320, 2)))
                x = str(x)
                y = str(y)
                h = str(h)
                w = str(w)

                message = message + "{},{},{},{},{}".format(area, x, y, h, w)
                if (i + 1 < contourNum):
                    message = message + "|"

        
            jevois.sendSerial(message)            
        # Write a title:
        # cv2.putText(outimg, "YetiVision", (3, 20), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255,255,255), 1, cv2.LINE_AA)
        
        # # Write frames/s info from our timer into the edge map (NOTE: does not account for output conversion time):
        # fps = self.timer.stop()
        # #height, width, channels = outimg.shape # if outimg is grayscale, change to: height, width = outimg.shape
        # height, width, channels = outimg.shape
        # cv2.putText(outimg, fps, (3, height - 6), cv2.FONT_HERSHEY_SIMPLEX, 0.5, (255,255,255), 1, cv2.LINE_AA)

        # # Convert our BGR output image to video output format and send to host over USB. If your output image is not
        # # BGR, you can use sendCvGRAY(), sendCvRGB(), or sendCvRGBA() as appropriate:
        # outframe.sendCvBGR(outimg)
        # # outframe.sendCvGRAY(outimg)
        
        
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
