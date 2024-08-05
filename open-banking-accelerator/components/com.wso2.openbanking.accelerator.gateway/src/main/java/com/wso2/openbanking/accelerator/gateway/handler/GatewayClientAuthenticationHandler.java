/**
 * Copyright (c) 2024, WSO2 LLC. (https://www.wso2.com). All Rights Reserved.
 * <p>
 * This software is the property of WSO2 LLC. and its suppliers, if any.
 * Dissemination of any information or reproduction of any material contained
 * herein in any form is strictly forbidden, unless permitted by WSO2 expressly.
 * You may not alter or remove any copyright or other notice from copies of this content.
 */

package com.wso2.openbanking.accelerator.gateway.handler;

import com.wso2.openbanking.accelerator.common.error.OpenBankingErrorCodes;
import com.wso2.openbanking.accelerator.gateway.internal.GatewayDataHolder;
import com.wso2.openbanking.accelerator.gateway.util.GatewayConstants;
import com.wso2.openbanking.accelerator.gateway.util.GatewayUtils;
import org.apache.axis2.context.MessageContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.synapse.core.axis2.Axis2MessageContext;
import org.apache.synapse.rest.AbstractHandler;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

/**
 * Handler to send transport certificate as a header to identity server.
 * Responds with an error if the transport certificate is not found.
 */
public class GatewayClientAuthenticationHandler extends AbstractHandler {

    private static final Log log = LogFactory.getLog(GatewayClientAuthenticationHandler.class);
    public static final String BEGIN_CERT = "-----BEGIN CERTIFICATE-----";
    public static final String END_CERT = "-----END CERTIFICATE-----";

    @Override
    public boolean handleRequest(org.apache.synapse.MessageContext messageContext) {

        if (log.isDebugEnabled()) {
            log.debug("Gateway Client Authentication Handler engaged");
        }

        MessageContext ctx = ((Axis2MessageContext) messageContext).getAxis2MessageContext();
        X509Certificate x509Certificate = extractAuthCertificateFromMessageContext(ctx);
        Map headers = (Map) ctx.getProperty(MessageContext.TRANSPORT_HEADERS);

        Optional<String> encodedCert = Optional.empty();
        if (x509Certificate != null) {
            if (log.isDebugEnabled()) {
                log.debug("Valid certificate found in request");
            }
            try {
                encodedCert = Optional.of(getPEMEncodedString(x509Certificate));
            } catch (CertificateEncodingException e) {
                log.error("Unable to encode certificate to PEM string", e);
            }
        } else {
            if (log.isDebugEnabled()) {
                log.debug(GatewayConstants.TRANSPORT_CERT_NOT_FOUND);
            }
            GatewayUtils.returnSynapseHandlerJSONError(messageContext, OpenBankingErrorCodes.BAD_REQUEST_CODE,
                    GatewayUtils.getOAuth2JsonErrorBody(GatewayConstants.INVALID_REQUEST,
                            GatewayConstants.TRANSPORT_CERT_NOT_FOUND));
            return true;
        }

        if (encodedCert.isPresent()) {
            String certificateHeader = GatewayDataHolder.getInstance().getClientTransportCertHeaderName();
            if (log.isDebugEnabled()) {
                log.debug(String.format("Added PEM encoded certificate in header %s", certificateHeader));
            }
            headers.put(certificateHeader, encodedCert.get().replaceAll("\n", ""));
            ctx.setProperty(MessageContext.TRANSPORT_HEADERS, headers);
        }
        return true;
    }

    @Override
    public boolean handleResponse(org.apache.synapse.MessageContext messageContext) {

        return true;
    }

    /**
     * Convert X509Certificate to PEM encoded string.
     *
     * @param certificate X509Certificate
     * @return PEM encoded string
     */
    private String getPEMEncodedString(X509Certificate certificate) throws CertificateEncodingException {
        StringBuilder certificateBuilder = new StringBuilder();
        Base64.Encoder encoder = Base64.getMimeEncoder(64, "\n".getBytes());

        // Get the encoded certificate in DER format
        byte[] encoded = certificate.getEncoded();

        // Encode the byte array to a Base64 string
        String base64Encoded = encoder.encodeToString(encoded);

        // Build the PEM formatted certificate
        certificateBuilder.append(BEGIN_CERT);
        certificateBuilder.append(base64Encoded);
        certificateBuilder.append("\n");
        certificateBuilder.append(END_CERT);

        return certificateBuilder.toString();
    }

    /**
     * Extract Certificate from Message Context.
     *
     * @param ctx Message Context
     * @return X509Certificate
     */
    public static X509Certificate extractAuthCertificateFromMessageContext(
            org.apache.axis2.context.MessageContext ctx) {

        Object sslCertObject = ctx.getProperty(GatewayConstants.AXIS2_MTLS_CERT_PROPERTY);
        if (sslCertObject != null) {
            X509Certificate[] certs = (X509Certificate[]) sslCertObject;
            return certs[0];
        } else {
            return null;
        }
    }
}
