/**
 * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com).
 * <p>
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 *     http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.financial.services.accelerator.consent.mgt.extensions.util;

import org.wso2.financial.services.accelerator.consent.mgt.dao.models.AuthorizationResource;
import org.wso2.financial.services.accelerator.consent.mgt.dao.models.ConsentFile;
import org.wso2.financial.services.accelerator.consent.mgt.dao.models.ConsentHistoryResource;
import org.wso2.financial.services.accelerator.consent.mgt.dao.models.ConsentMappingResource;
import org.wso2.financial.services.accelerator.consent.mgt.dao.models.ConsentResource;
import org.wso2.financial.services.accelerator.consent.mgt.dao.models.ConsentStatusAuditRecord;
import org.wso2.financial.services.accelerator.consent.mgt.dao.models.DetailedConsentResource;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestUtil {

    public static AuthorizationResource getSampleAuthorizationResource(String consentID, String authorizationID) {

        AuthorizationResource authorizationResource = new AuthorizationResource(consentID, TestConstants.SAMPLE_USER_ID,
                TestConstants.SAMPLE_AUTHORIZATION_STATUS, TestConstants.SAMPLE_AUTH_TYPE,
                System.currentTimeMillis() / 1000);
        authorizationResource.setAuthorizationID(authorizationID);

        return authorizationResource;
    }

    public static ArrayList<AuthorizationResource> getSampleAuthorizationResourceArray(String consentID,
                                                                                       String authorizationID) {
        return new ArrayList<>(List.of(TestUtil.getSampleAuthorizationResource(consentID, authorizationID)));
    }

    public static DetailedConsentResource getSampleDetailedConsentResource() {

        ArrayList<AuthorizationResource> authorizationResources = new ArrayList<>();
        authorizationResources.add(getSampleAuthorizationResource(TestConstants.SAMPLE_CONSENT_ID,
                TestConstants.SAMPLE_AUTH_ID));

        ArrayList<ConsentMappingResource> consentMappingResources = new ArrayList<>();
        consentMappingResources.add(getSampleConsentMappingResource(TestConstants.SAMPLE_AUTH_ID));
        consentMappingResources.add(getSampleInactiveConsentMappingResource(TestConstants.SAMPLE_AUTH_ID));

        return new DetailedConsentResource(
                TestConstants.SAMPLE_CONSENT_ID, TestConstants.SAMPLE_CLIENT_ID,
                TestConstants.VALID_INITIATION, TestConstants.ACCOUNTS,
                TestConstants.SAMPLE_AUTHORIZATION_STATUS, TestConstants.SAMPLE_CONSENT_FREQUENCY,
                TestConstants.SAMPLE_CONSENT_VALIDITY_PERIOD, System.currentTimeMillis() / 1000,
                System.currentTimeMillis() / 1000, TestConstants.SAMPLE_RECURRING_INDICATOR,
                TestConstants.SAMPLE_CONSENT_ATTRIBUTES_MAP, authorizationResources, consentMappingResources);
    }

    public static DetailedConsentResource getSampleDetailedConsentResourceForCOF() {

        ArrayList<AuthorizationResource> authorizationResources = new ArrayList<>();
        authorizationResources.add(getSampleAuthorizationResource(TestConstants.SAMPLE_CONSENT_ID,
                TestConstants.SAMPLE_AUTH_ID));

        ArrayList<ConsentMappingResource> consentMappingResources = new ArrayList<>();
        consentMappingResources.add(getSampleConsentMappingResource(TestConstants.SAMPLE_AUTH_ID));
        consentMappingResources.add(getSampleInactiveConsentMappingResource(TestConstants.SAMPLE_AUTH_ID));

        return new DetailedConsentResource(
                TestConstants.SAMPLE_CONSENT_ID, TestConstants.SAMPLE_CLIENT_ID,
                TestConstants.COF_RECEIPT, TestConstants.FUNDS_CONFIRMATIONS,
                TestConstants.SAMPLE_AUTHORIZATION_STATUS, TestConstants.SAMPLE_CONSENT_FREQUENCY,
                TestConstants.SAMPLE_CONSENT_VALIDITY_PERIOD, System.currentTimeMillis() / 1000,
                System.currentTimeMillis() / 1000, TestConstants.SAMPLE_RECURRING_INDICATOR,
                TestConstants.SAMPLE_CONSENT_ATTRIBUTES_MAP, authorizationResources, consentMappingResources);
    }

    public static DetailedConsentResource getSampleDetailedConsentResourceForPayments() {

        ArrayList<AuthorizationResource> authorizationResources = new ArrayList<>();
        authorizationResources.add(getSampleAuthorizationResource(TestConstants.SAMPLE_CONSENT_ID,
                TestConstants.SAMPLE_AUTH_ID));

        ArrayList<ConsentMappingResource> consentMappingResources = new ArrayList<>();
        consentMappingResources.add(getSampleConsentMappingResource(TestConstants.SAMPLE_AUTH_ID));
        consentMappingResources.add(getSampleInactiveConsentMappingResource(TestConstants.SAMPLE_AUTH_ID));

        return new DetailedConsentResource(
                TestConstants.SAMPLE_CONSENT_ID, TestConstants.SAMPLE_CLIENT_ID,
                TestConstants.PAYMENT_INITIATION, TestConstants.PAYMENTS,
                TestConstants.SAMPLE_AUTHORIZATION_STATUS, TestConstants.SAMPLE_CONSENT_FREQUENCY,
                TestConstants.SAMPLE_CONSENT_VALIDITY_PERIOD, System.currentTimeMillis() / 1000,
                System.currentTimeMillis() / 1000, TestConstants.SAMPLE_RECURRING_INDICATOR,
                TestConstants.SAMPLE_CONSENT_ATTRIBUTES_MAP, authorizationResources, consentMappingResources);
    }

    public static ConsentMappingResource getSampleConsentMappingResource(String authorizationID) {

        ConsentMappingResource consentMappingResource = new ConsentMappingResource(authorizationID,
                TestConstants.SAMPLE_ACCOUNT_ID, TestConstants.SAMPLE_PERMISSION,
                TestConstants.SAMPLE_MAPPING_STATUS);
        consentMappingResource.setMappingID(TestConstants.SAMPLE_MAPPING_ID);

        return consentMappingResource;
    }

    public static ConsentMappingResource getSampleInactiveConsentMappingResource(String authorizationID) {

        ConsentMappingResource consentMappingResource = getSampleConsentMappingResource(authorizationID);
        consentMappingResource.setMappingID(TestConstants.SAMPLE_MAPPING_ID_2);
        consentMappingResource.setMappingStatus(TestConstants.SAMPLE_NEW_MAPPING_STATUS);

        return consentMappingResource;
    }

    public static ConsentResource getSampleConsentResource(String status) {

        return new ConsentResource(UUID.randomUUID().toString(), TestConstants.SAMPLE_CLIENT_ID,
                TestConstants.VALID_INITIATION, TestConstants.SAMPLE_CONSENT_TYPE,
                TestConstants.SAMPLE_CONSENT_FREQUENCY, TestConstants.SAMPLE_CONSENT_VALIDITY_PERIOD,
                TestConstants.SAMPLE_RECURRING_INDICATOR, status,
                System.currentTimeMillis() / 1000, System.currentTimeMillis() / 1000);
    }

    public static ConsentResource getSampleConsentResource(String consentType, String payload, String status) {

        return new ConsentResource(null, UUID.randomUUID().toString(), payload, consentType,
                TestConstants.SAMPLE_CONSENT_FREQUENCY, TestConstants.SAMPLE_CONSENT_VALIDITY_PERIOD,
                TestConstants.SAMPLE_RECURRING_INDICATOR, status, System.currentTimeMillis() / 1000,
                System.currentTimeMillis() / 1000);
    }

    public static ConsentHistoryResource getSampleConsentHistoryResource() {

        ConsentHistoryResource consentHistoryResource = new ConsentHistoryResource();

        consentHistoryResource.setTimestamp(TestConstants.SAMPLE_CONSENT_AMENDMENT_TIMESTAMP);
        consentHistoryResource.setReason(TestConstants.SAMPLE_AMENDMENT_REASON);
        consentHistoryResource.setDetailedConsentResource(getSampleDetailedConsentResource());

        return consentHistoryResource;
    }

    public static ConsentStatusAuditRecord getSampleConsentStatusAuditRecord(String consentID,
                                                                                 String currentStatus) {

        return new ConsentStatusAuditRecord(consentID, currentStatus,
                System.currentTimeMillis() / 1000, TestConstants.SAMPLE_REASON,
                TestConstants.SAMPLE_ACTION_BY, TestConstants.SAMPLE_PREVIOUS_STATUS);
    }

    public static ConsentFile getSampleConsentFileObject(String fileContent) {

        return new ConsentFile(UUID.randomUUID().toString(), fileContent);
    }
}
