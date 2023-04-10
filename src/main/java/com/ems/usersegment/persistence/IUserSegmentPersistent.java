package com.ems.usersegment.persistence;

import com.ems.usersegment.model.UserSegment;

public interface IUserSegmentPersistent {
    UserSegment getUserSegmentByUserId(String userId);
}
