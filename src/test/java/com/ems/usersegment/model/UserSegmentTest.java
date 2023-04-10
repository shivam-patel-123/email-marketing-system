package com.ems.usersegment.model;

import com.ems.usersegment.persistence.IUserSegmentPersistent;
import com.ems.usersegment.persistence.UserSegmentMockDB;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserSegmentTest {
    private static UserSegment userSegment;
    private static IUserSegmentPersistent userSegmentPersistent;

    @BeforeAll
    public static void init(){
        userSegment = new UserSegment("");
        userSegmentPersistent = new UserSegmentMockDB();
    }

    @Test
    public void getUserSegmentByNullUserIdTest(){
        UserSegment userSegmentResponse = userSegment.getUserSegmentByUserId(userSegmentPersistent, "");
        assertNull(userSegmentResponse);
    }

    @Test
    public void getUserSegmentByUserIdSuccessTest(){
        UserSegment userSegmentResponse = userSegment.getUserSegmentByUserId(userSegmentPersistent ,"4a91009c-f993-450b-bf82-4047de4590a2");
        assertEquals(userSegmentResponse.userId, "4a91009c-f993-450b-bf82-4047de4590a2");
    }
}
