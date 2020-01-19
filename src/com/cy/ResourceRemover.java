package com.cy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**F:\android\android-sdk\tools>lint --check "UnusedResources" F:\projects\android2\
 * umsqmf2g-2.2.0_develop > result.txt
 * @author wangxc <br/>
 */
public class ResourceRemover {
    private static final String TAG = ResourceRemover.class.getName();
    
    static final String pathResult="/Users/apple/projects/android/tmlauncher/app/build/reports/lint-results.xml";

    
    public static void main(String[] args){
        XmlParser.start(pathResult);
    }

}

