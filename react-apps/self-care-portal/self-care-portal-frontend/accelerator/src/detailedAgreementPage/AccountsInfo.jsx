/**
 * Copyright (c) 2023, WSO2 LLC. (https://www.wso2.com).
 *
 * WSO2 LLC. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

//AccountsInfo for Default Application
import React from "react";
import {lang, specConfigurations} from "../specConfigs/specConfigurations.js";
import {permissionBindTypes} from "../specConfigs/common";

export const AccountsInfo = ({consent, consentType}) => {

    const consentStatus = consent.currentStatus;
    const debtorAccounts = consent.consentMappingResources;
    let keyDatesConfig = lang[consentType].filter((lbl) =>
        lbl.id.toLowerCase().includes(consent.currentStatus.toLowerCase()))[0];
    return (
        <div className="accountsInfoBody">
            {specConfigurations.consent.permissionsView.permissionBindType ===
            permissionBindTypes.samePermissionSetForAllAccounts ? (
                <>
                    <h5>{keyDatesConfig.accountsInfoLabel}</h5>
                    {debtorAccounts.map((account, index) => (
                        account.mappingStatus === "active" ?
                            <p key= {index}>{account.accountId}</p>
                            :
                            <> </>
                    ))}
                    <h5> {""}</h5>
                </>
            ) : (
                <></>
            )
            }

        </div>
    );
};
