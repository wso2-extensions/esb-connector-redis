/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied. See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.wso2.carbon.connector.util;

/**
 * This class contains various utility methods related to common operations.
 */
public class RedisUtils {

    /**
     * Checks if a message is in the JSON format.
     *
     * @param message Message string.
     * @return True is JSON, False otherwise.
     */
    public static boolean isJsonPayload(String message) {
        if (Constants.JSON_START_CHARACTER.contains(message.substring(0, 1))) {
            return true;
        }

        return false;
    }

    /**
     * Checks if a message is in the XML format.
     *
     * @param message Message string.
     * @return True is XML, False otherwise.
     */
    public static boolean isXMLPayload(String message) {
        if (Constants.XML_START_CHARACTER.contains(message.substring(0, 1))) {
            return true;
        }

        return false;
    }

    /**
     * Escape characters in a JSON.
     *
     * @param str string.
     * @return Escaped string.
     */
    public static String escapeCharsJSON(String str) {
        str = str.replace("\"", "\\\"");
        str = str.replace("\n", "\\n");

        return str;
    }

}
