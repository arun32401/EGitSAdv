package com.staples.saf.utilities;

import com.staples.saf.base.BaseTest;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;

public class TestLinkConnection {

        public final String DEV_KEY = BaseTest.testLinkDevKey;

        public final String SERVER_URL = BaseTest.testLinkServerUrl;
        // Substitute your project name Here
        public final String PROJECT_NAME = BaseTest.testLinkProjectName;

        // Substitute your test plan Here
        public final String PLAN_NAME = BaseTest.testLinkPlanName;
        
        // Substitute your build name
        public final String BUILD_NAME = BaseTest.testLinkBuildName;
                
        public void updateTestLinkResult(String testCase, String exception, String result) throws TestLinkAPIException 
        {
        TestLinkAPIClient testlinkAPIClient = new TestLinkAPIClient(DEV_KEY,SERVER_URL);
        testlinkAPIClient.reportTestCaseResult(PROJECT_NAME, PLAN_NAME,testCase, BUILD_NAME, exception, result);
        }
}

