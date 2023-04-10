package com.ems.usersegment.persistence;

import com.ems.usersegment.model.UserSegment;

public class UserSegmentMockDB implements IUserSegmentPersistent{

    @Override
    public UserSegment getUserSegmentByUserId(String userId) {
        if(userId.equals("4a91009c-f993-450b-bf82-4047de4590a2")){
            return new UserSegment("4a91009c-f993-450b-bf82-4047de4590a2");
        }
        return null;
    }
}
