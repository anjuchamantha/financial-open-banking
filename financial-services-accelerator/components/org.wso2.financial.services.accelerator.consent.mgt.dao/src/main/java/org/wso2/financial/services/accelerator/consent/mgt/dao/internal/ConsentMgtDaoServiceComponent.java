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

package org.wso2.financial.services.accelerator.consent.mgt.dao.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * Consent Management Common Service Component.
 */
@Component(name = "org.wso2.financial.services.accelerator.consent.mgt.dao.internal.ConsentMgtDaoServiceComponent",
        immediate = true)
public class ConsentMgtDaoServiceComponent {

    private static final Log log = LogFactory.getLog(ConsentMgtDaoServiceComponent.class);

    @Activate
    protected void activate(ComponentContext context) {

        log.debug("Consent Management DAO is registered successfully.");
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {

        log.debug("Consent Management DAO is deactivated");
    }

}
