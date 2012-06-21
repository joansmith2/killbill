/* 
 * Copyright 2010-2011 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.ning.billing.payment.provider;

import java.util.List;
import java.util.UUID;

import com.ning.billing.payment.api.PaymentMethodPlugin;

public class DefaultNoOpPaymentMethodPlugin implements PaymentMethodPlugin {

    private String externalId;
    private boolean isDefault;
    private List<PaymentMethodKVInfo> props;

    public DefaultNoOpPaymentMethodPlugin(final PaymentMethodPlugin src) {
        this.externalId = UUID.randomUUID().toString();
        this.isDefault = src.isDefaultPaymentMethod();
        this.props = src.getProperties();
    }

    public DefaultNoOpPaymentMethodPlugin(final String externalId, final boolean isDefault,
                                          final List<PaymentMethodKVInfo> props) {
        super();
        this.externalId = externalId;
        this.isDefault = isDefault;
        this.props = props;
    }

    @Override
    public String getExternalPaymentMethodId() {
        return externalId;
    }

    @Override
    public boolean isDefaultPaymentMethod() {
        return isDefault;
    }

    @Override
    public List<PaymentMethodKVInfo> getProperties() {
        return props;
    }

    public void setExternalId(final String externalId) {
        this.externalId = externalId;
    }

    public void setDefault(final boolean isDefault) {
        this.isDefault = isDefault;
    }

    public void setProps(final List<PaymentMethodKVInfo> props) {
        this.props = props;
    }

    @Override
    public String getValueString(final String key) {
        if (props == null) {
            return null;
        }
        for (final PaymentMethodKVInfo cur : props) {
            if (cur.getKey().equals(key)) {
                return cur.getValue().toString();
            }
        }
        return null;
    }
}