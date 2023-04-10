package com.ems.usersegment.persistence;

import com.ems.usersegment.model.UserSegment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserSegmentDB implements IUserSegmentPersistent{
    public Connection connection;
    public UserSegmentDB(Connection conn){
        this.connection=conn;
    }

    @Override
    public UserSegment getUserSegmentByUserId(String userId) {
        if (connection != null) {
            String sql = "select * from user_segment where user_id= ?";
            try {
                UserSegment userSegment = new UserSegment(userId);
                PreparedStatement stmt = connection.prepareStatement(sql);
                stmt.setString(1, userId);

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    userSegment.userSegmentId = rs.getString("user_segment_id");
                    userSegment.apiKey = rs.getString("api_key");
                    userSegment.applicationName = rs.getString("application_name");
                }

                return userSegment;

            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
