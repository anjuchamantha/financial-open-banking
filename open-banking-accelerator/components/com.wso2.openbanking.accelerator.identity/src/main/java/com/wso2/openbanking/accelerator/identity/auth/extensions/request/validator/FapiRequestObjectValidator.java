/**
 * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com).
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

package com.wso2.openbanking.accelerator.identity.auth.extensions.request.validator;

import com.wso2.openbanking.accelerator.common.validator.OpenBankingValidator;
import com.wso2.openbanking.accelerator.identity.auth.extensions.request.validator.models.FapiRequestObject;
import com.wso2.openbanking.accelerator.identity.auth.extensions.request.validator.models.OBRequestObject;
import com.wso2.openbanking.accelerator.identity.auth.extensions.request.validator.models.ValidationResponse;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * The extension class for enforcing FAPI Request Object Validations.
 */
public class FapiRequestObjectValidator extends DefaultOBRequestObjectValidator {

    @Override
    public ValidationResponse validateOBConstraints(OBRequestObject obRequestObject, Map<String, Object> dataMap) {

        ValidationResponse superValidationResponse = super.validateOBConstraints(obRequestObject, dataMap);

        if (superValidationResponse.isValid()) {
            FapiRequestObject fapiRequestObject = new FapiRequestObject(obRequestObject);
            String violation = OpenBankingValidator.getInstance().getFirstViolation(fapiRequestObject);

            if (StringUtils.isEmpty(violation)) {
                return new ValidationResponse(true);
            } else {
                return new ValidationResponse(false, violation);
            }
        } else {
            return superValidationResponse;
        }
    }

}
