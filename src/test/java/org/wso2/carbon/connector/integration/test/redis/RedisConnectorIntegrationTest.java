/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

package org.wso2.carbon.connector.integration.test.redis;

import org.json.JSONException;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RedisConnectorIntegrationTest extends ConnectorIntegrationTestBase {
    private Map<String, String> eiRequestHeadersMap = new HashMap<String, String>();

    /**
     * Set up the environment.
     */
    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {
        String connectorName =
                System.getProperty("connector_name") + "-connector-" + System.getProperty("connector_version") + ".zip";
        init(connectorName);
        eiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        eiRequestHeadersMap.put("Content-Type", "application/json");
    }

    /**
     * Test case for ping command.
     */
    @Test(groups = { "wso2.ei" },
          description = "Redis {ping} integration test.")
    public void testPing() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:ping");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap);
        Assert.assertEquals(eiRestResponse.getBody().toString(), "{\"output\":\"PONG\"}");
    }

    /**
     * Test case for echo command.
     */
    @Test(groups = { "wso2.ei" },
          description = "Redis {echo} integration test.")
    public void testEcho() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:echo");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "echo.json");
        Assert.assertEquals(eiRestResponse.getBody().toString(), "{\"output\":\"beauty World\"}");
    }

    /**
     * Test case for set operation.
     */
    @Test(groups = { "wso2.ei" },
          description = "Redis {set} integration test.")
    public void testSet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:set");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "set.json");
        eiRequestHeadersMap.put("Action", "urn:exists");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "exists.json");
        Assert.assertEquals(eiRestResponse.getBody().toString(), "{\"output\":\"true\"}");
    }

    /**
     * Test case for randomKey operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 1,
          description = "Redis {randomKey} integration test.")
    public void testRandomKey() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:randomKey");
        RestResponse<JSONObject> eiRestResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap);
        Assert.assertEquals(eiRestResponse.getBody().toString().startsWith("{\"output\":\"mykey\"}"), true);
    }

    /**
     * Test case for get operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 2,
          description = "Redis {get} integration test.")
    public void testGet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:get");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "get.json");
        Assert.assertEquals(response.getBody().toString(),
                "{\"output\":\"" + connectorProperties.getProperty("value") + "\"}");
    }

    /**
     * Test case for type operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 3,
          description = "Redis {type} integration test.")
    public void testType() throws IOException, JSONException {
        testSet();
        eiRequestHeadersMap.put("Action", "urn:type");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "type.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"string\"}");
    }

    /**
     * Test case for getRange operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 4,
          description = "Redis {getRange} integration test.")
    public void testGetRange() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:getRange");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "getRange.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"ello\"}");
    }

    /**
     * Test case for keys operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 5,
          description = "Redis {keys} integration test.")
    public void testKeys() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:keys");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "keys.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"[mykey]\"}");
    }

    /**
     * Test case for getSet operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 6,
          description = "Redis {getset} integration test.")
    public void testGetSet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:getset");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "getSet.json");
        eiRequestHeadersMap.put("Action", "urn:get");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "get.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"hello\"}");
    }

    /**
     * Test case for setnX operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 7,
          description = "Redis {setnX} integration test.")
    public void testSetnX() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:setnX");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "setnX.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for hSet operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 8,
          description = "Redis {hSet} integration test.")
    public void testHSet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hSet");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hSet.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for hSetnX operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 9,
          description = "Redis {hSetnX} integration test.")
    public void testHSetnX() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hSetnX");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hSetnX.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for lPush operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 10,
          description = "Redis {lPush} integration test.")
    public void testLPush() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:lPush");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "lPush.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for mSet command.
     */
    @Test(groups = { "wso2.ei" },
          priority = 11,
          description = "Redis {mSet} integration test.")
    public void testMSet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:mSet");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "mSet.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"OK\"}");
    }

    /**
     * Test case for mSetnX command.
     */
    @Test(groups = { "wso2.ei" },
          priority = 12,
          description = "Redis {mSetnX} integration test.")
    public void testMSetnX() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:mSetnX");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "mSetnX.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for hMSet command.
     */
    @Test(groups = { "wso2.ei" },
          priority = 13,
          description = "Redis {hMSet} integration test.")
    public void testHMSet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hMSet");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hMSet.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"OK\"}");
    }

    /**
     * Test case for rename operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 14,
          description = "Redis {rename} integration test.")
    public void testRename() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:rename");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "rename.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"OK\"}");
    }

    /**
     * Test case for incrBy operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 15,
          description = "Redis {incrBy} integration test.")
    public void testIncrBy() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:incrBy");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "incrBy.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":13}");
    }

    /**
     * Test case for hGet operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 16,
          description = "Redis {hGet} integration test.")
    public void testHGet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hGet");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hGet.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"myhashvalue\"}");
    }

    /**
     * Test case for hExists operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 17,
          description = "Redis {hExists} integration test.")
    public void testHExists() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hExists");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hExists.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"true\"}");
    }

    /**
     * Test case for hIncrBy operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 18,
          description = "Redis {hIncrBy} integration test.")
    public void testHIncrBy() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hIncrBy");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hIncrBy.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":8}");
    }

    /**
     * Test case for hLen operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 19,
          description = "Redis {hLen} integration test.")
    public void testHLen() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hLen");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hLen.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":2}");
    }

    /**
     * Test case for lLen operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 20,
          description = "Redis {lLen} integration test.")
    public void testLLen() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:lLen");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "lLen.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for hMGet operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 21,
          description = "Redis {hMGet} integration test.")
    public void testHMGet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hMGet");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hMGet.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"[hello, world]\"}");
    }

    /**
     * Test case for mGet operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 22,
          description = "Redis {mGet} integration test.")
    public void testMGet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:mGet");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "mGet.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"[a, b, c]\"}");
    }

    /**
     * Test case for hKeys operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 23,
          description = "Redis {hKeys} integration test.")
    public void testHKeys() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hKeys");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hKeys.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"[myhashfield, myhashfield1]\"}");
    }

    /**
     * Test case for hVals operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 24,
          description = "Redis {hVals} integration test.")
    public void testHVals() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hVals");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hVals.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"[myhashvalue, 8]\"}");
    }

    /**
     * Test case for hGetAll operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 25,
          description = "Redis {hGetAll} integration test.")
    public void testHGetAll() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hGetAll");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hGetAll.json");
        Assert.assertEquals(response.getBody().toString().contains("myhashfield1=8"),true);
        Assert.assertEquals(response.getBody().toString().contains("myhashfield=myhashvalue"),true);
    }

    /**
     * Test case for renamenX operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 26,
          description = "Redis {renamenX} integration test.")
    public void testRenamenX() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:renamenX");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "renamenX.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
        testSet();
    }

    /**
     * Test case for decrBy operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 27,
          description = "Redis {decrBy} integration test.")
    public void testDecrBy() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:decrBy");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "decrBy.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":10}");
    }

    /**
     * Test case for lSet operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 28,
          description = "Redis {lSet} integration test.")
    public void testLSet() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:lSet");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "lSet.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"OK\"}");
    }

    /**
     * Test case for hDel operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 29,
          description = "Redis {hDel} integration test.")
    public void testHDel() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:hDel");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "hDel.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":2}");
    }

    /**
     * Test case for append and strLen operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 30,
          description = "Redis {append} integration test.")
    public void testAppendAndStrLen() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:append");
        RestResponse<JSONObject> appendResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "append.json");
        eiRequestHeadersMap.put("Action", "urn:strLen");
        RestResponse<JSONObject> strLenResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "strLen.json");
        Assert.assertEquals(appendResponse.getBody().toString(), strLenResponse.getBody().toString());
    }

    /**
     * Test case for expire operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 31,
          description = "Redis {expire} integration test.")
    public void testExpire() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:expire");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "expire.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for lTrim operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 32,
          description = "Redis {lTrim} integration test.")
    public void testLTrim() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:lTrim");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "lTrim.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"OK\"}");
    }

    /**
     * Test case for rPush operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 33,
          description = "Redis {rPush} integration test.")
    public void testRPush() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:rPush");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "rPush.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":3}");
    }

    /**
     * Test case for lInsert operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 34,
          description = "Redis {lInsert} integration test.")
    public void testLInsert() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:lInsert");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "lInsert.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":4}");
    }

    /**
     * Test case for rPushX operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 35,
          description = "Redis {rPushX} integration test.")
    public void testRPushX() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:rPushX");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "rPushX.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":5}");
    }

    /**
     * Test case for lRange operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 36,
          description = "Redis {lRange} integration test.")
    public void testLRange() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:lRange");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "lRange.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"[list1, listA, list2, list3, list4]\"}");
    }

    /**
     * Test case for lPushX operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 37,
          description = "Redis {lPushX} integration test.")
    public void testLPushX() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:lPushX");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "lPushX.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":6}");
    }

    /**
     * Test case for blPop operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 38,
          description = "Redis {blPop} integration test.")
    public void testBlPop() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:blPop");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "blPop.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"[mylist, listB]\"}");
    }

    /**
     * Test case for brPop operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 39,
          description = "Redis {brPop} integration test.")
    public void testBrPop() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:brPop");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "brPop.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"[mylist, list4]\"}");
    }

    /**
     * Test case for expireAt operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 40,
          description = "Redis {expireAt} integration test.")
    public void testExpireAt() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:expireAt");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "expireAt.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for lRem operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 41,
          description = "Redis {lRem} integration test.")
    public void testLRem() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:lRem");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "lRem.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for sadd operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 42,
          description = "Redis {sadd} integration test.")
    public void testSadd() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:sadd");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "sadd.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":5}");
    }

    /**
     * Test case for sDiffStore operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 43,
          description = "Redis {sDiffStore} integration test.")
    public void testSDiffStore() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:sadd");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "saddPop1.json");
        eiRequestHeadersMap.put("Action", "urn:sadd");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "saddPop2.json");
        eiRequestHeadersMap.put("Action", "urn:sDiffStore");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "sDiffStore.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":4}");
    }

    /**
     * Test case for sInter operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 44,
          description = "Redis {sInter} integration test.")
    public void testSInter() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:sInter");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "sInter.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"[a]\"}");
    }

    /**
     * Test case for sInterStore operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 45,
          description = "Redis {sInterStore} integration test.")
    public void testSInterStore() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:sInterStore");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "sInterStore.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for sMembers operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 46,
          description = "Redis {sMembers} integration test.")
    public void testSMembers() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:sMembers");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "sMembers.json");
        Assert.assertEquals(response.getBody().toString().contains("a"), true);
        Assert.assertEquals(response.getBody().toString().contains("b"), true);
        Assert.assertEquals(response.getBody().toString().contains("c"), true);
        Assert.assertEquals(response.getBody().toString().contains("d"), true);
        Assert.assertEquals(response.getBody().toString().contains("e"), true);
    }

    /**
     * Test case for lPop operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 47,
          description = "Redis {lPop} integration test.")
    public void testLPop() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:lPop");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "lPop.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"list1\"}");
    }

    /**
     * Test case for zadd operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 48,
          description = "Redis {zadd} integration test.")
    public void testZadd() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:zadd");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "zadd.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for rPopLPush operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 49,
          description = "Redis {rPopLPush} integration test.")
    public void testRPopLPush() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:rPopLPush");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "rPopLPush.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"hello\"}");
    }

    /**
     * Test case for sRem operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 50,
          description = "Redis {sRem} integration test.")
    public void testSRem() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:sRem");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "sRem.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":2}");
    }

    /**
     * Test case for sMove operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 51,
          description = "Redis {sMove} integration test.")
    public void testSMove() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:sadd");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "saddRemove1.json");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "saddRemove2.json");
        eiRequestHeadersMap.put("Action", "urn:sMove");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "sMove.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for zIncrBy operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 52,
          description = "Redis {zIncrBy} integration test.")
    public void testZIncrBy() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:zIncrBy");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "zIncrBy.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":2.2}");
    }

    /**
     * Test case for zRevRank operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 53,
          description = "Redis {zRevRank} integration test.")
    public void testZRevRank() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:zRevRank");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "zRevRank.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":0}");
    }

    /**
     * Test case for zRank operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 54,
          description = "Redis {zRank} integration test.")
    public void testZRank() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:zRank");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "zRank.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":0}");
    }

    /**
     * Test case for del operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 55,
          description = "Redis {del} integration test.")
    public void testDel() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:set");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "set.json");
        eiRequestHeadersMap.put("Action", "urn:del");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "del.json");
        eiRequestHeadersMap.put("Action", "urn:exists");
        RestResponse<JSONObject> existsResponse = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "exists.json");
        Assert.assertEquals(existsResponse.getBody().toString(), "{\"output\":\"false\"}");
    }

    /**
     * Test case for sIsMember operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 56,
          description = "Redis {sIsMember} integration test.")
    public void testSIsMember() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:sIsMember");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "sIsMember.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"true\"}");
    }

    /**
     * Test case for zScore operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 57,
          description = "Redis {zScore} integration test.")
    public void testZScore() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:zScore");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "zScore.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":2.2}");
    }

    /**
     * Test case for zCount operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 58,
          description = "Redis {zCount} integration test.")
    public void testZCount() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:zCount");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "zCount.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":1}");
    }

    /**
     * Test case for setBit operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 59,
          description = "Redis {setBit} integration test.")
    public void testSetBit() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:setBit");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "setBit.json");
        eiRequestHeadersMap.put("Action", "urn:get");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap,
                "getSetBit.json");
        Assert.assertEquals(response.getBody().toString().contains("\\u0000"), true);
    }

    /**
     * Test case for setRange operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 60,
          description = "Redis {setRange} integration test.")
    public void testSetRange() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:set");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "setRangeSet.json");
        eiRequestHeadersMap.put("Action", "urn:setRange");
        sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "setRange.json");
        eiRequestHeadersMap.put("Action", "urn:get");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap, "get.json");
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"beauty Redis\"}");
    }

    /**
     * Test case for flushDB operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 61,
          description = "Redis {flushDB} integration test.")
    public void testFlushDB() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:flushDB");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap);
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"OK\"}");
    }

    /**
     * Test case for flushAll operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 61,
          description = "Redis {flushAll} integration test.")
    public void testFlushAll() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:flushAll");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap);
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"OK\"}");
    }

    /**
     * Test case for quit operation.
     */
    @Test(groups = { "wso2.ei" },
          priority = 62,
          description = "Redis {quit} integration test.")
    public void testQuit() throws IOException, JSONException {
        eiRequestHeadersMap.put("Action", "urn:quit");
        RestResponse<JSONObject> response = sendJsonRestRequest(proxyUrl, "POST", eiRequestHeadersMap);
        Assert.assertEquals(response.getBody().toString(), "{\"output\":\"OK\"}");
    }
}
