/*
 * Copyright 2014 Groupon, Inc
 *
 * Groupon licenses this file to you under the Apache License, version 2.0
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

package org.killbill.billing.payment.core.sm.control;

import org.killbill.automaton.OperationResult;
import org.killbill.billing.osgi.api.OSGIServiceRegistration;
import org.killbill.billing.payment.api.Payment;
import org.killbill.billing.payment.api.PaymentApiException;
import org.killbill.billing.payment.core.PaymentProcessor;
import org.killbill.billing.payment.dispatcher.PluginDispatcher;
import org.killbill.billing.routing.plugin.api.PaymentRoutingPluginApi;
import org.killbill.commons.locker.GlobalLocker;

public class PurchaseControlOperation extends OperationControlCallback {

    public PurchaseControlOperation(final GlobalLocker locker, final PluginDispatcher<OperationResult> paymentPluginDispatcher, final PaymentStateControlContext paymentStateContext, final PaymentProcessor paymentProcessor, final OSGIServiceRegistration<PaymentRoutingPluginApi> retryPluginRegistry) {
        super(locker, paymentPluginDispatcher, paymentStateContext, paymentProcessor, retryPluginRegistry);
    }

    @Override
    protected Payment doCallSpecificOperationCallback() throws PaymentApiException {
        return paymentProcessor.createPurchase(paymentStateControlContext.isApiPayment(),
                                               paymentStateControlContext.getAttemptId(),
                                               paymentStateControlContext.getAccount(),
                                               paymentStateControlContext.getPaymentMethodId(),
                                               paymentStateControlContext.getPaymentId(),
                                               paymentStateControlContext.getAmount(),
                                               paymentStateControlContext.getCurrency(),
                                               paymentStateControlContext.getPaymentExternalKey(),
                                               paymentStateControlContext.getPaymentTransactionExternalKey(),
                                               false,
                                               paymentStateControlContext.getProperties(),
                                               paymentStateControlContext.getCallContext(),
                                               paymentStateControlContext.getInternalCallContext());
    }
}
