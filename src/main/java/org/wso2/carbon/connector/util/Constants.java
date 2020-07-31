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

import java.util.ArrayList;

/**
 * This class contains various constants related to the connector.
 */
public class Constants {

    // Properties
    public static final String REDIS_MESSAGE_TYPE_PROPERTY = "redisResponseMessageType";

    // Message types
    public static final String REDIS_MESSAGE_TYPE_TEXT = "text";
    public static final String REDIS_MESSAGE_TYPE_JSON = "json";

    // Message format types
    public static final ArrayList<String> JSON_START_CHARACTER = new ArrayList<String>() {{
        add("{");
        add("[");
    }};

    public static final ArrayList<String> XML_START_CHARACTER = new ArrayList<String>() {{
        add("<");
    }};

    // Miscellaneous
    public static final String NULL_STRING = "null";
    public static final String REDIS_RESPONSE_STATUS = "redisResponseStatus";
    public static final String REDIS_RESPONSE_MESSAGE = "redisMessage";
    public static final String REDIS_RESPONSE_SUCCESS = "success";
    public static final String REDIS_RESPONSE_ERROR = "error";



}
