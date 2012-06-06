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

package com.ning.billing.util.callcontext;

import java.util.UUID;

import com.ning.billing.util.clock.DefaultClock;
import org.joda.time.DateTime;

public class TestCallContext implements CallContext {
	
    private final String userName;
    private final DateTime updatedDate;
    private final DateTime createdDate;
    private final UUID userToken;
    
    public TestCallContext(String userName) {
    	this(userName, new DefaultClock().getUTCNow(), new DefaultClock().getUTCNow());
    }

    public TestCallContext(String userName, DateTime createdDate, DateTime updatedDate) {
        this.userName = userName;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.userToken = UUID.randomUUID();
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public CallOrigin getCallOrigin() {
        return CallOrigin.TEST;
    }

    @Override
    public UserType getUserType() {
        return UserType.TEST;
    }

    @Override
    public String getReasonCode() {
        return null;
    }

    @Override
    public String getComment() {
        return null;
    }

    @Override
    public DateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public DateTime getUpdatedDate() {
        return updatedDate;
    }

	@Override
	public UUID getUserToken() {
		return userToken;
	}
}