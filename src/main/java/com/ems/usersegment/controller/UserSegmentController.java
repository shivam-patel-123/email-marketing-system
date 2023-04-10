package com.ems.usersegment.controller;

import com.ems.dbconnection.MySqlPersistenceConnection;
import com.ems.authentication.model.User;
import com.ems.usersegment.model.UserSegment;
import com.ems.usersegment.persistence.IUserSegmentPersistent;
import com.ems.usersegment.persistence.UserSegmentDB;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

@RestController()
@RequestMapping("/api")
public class UserSegmentController {

    @GetMapping("/user-segment")
    public UserSegment getUserSegmentByUserId(HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("user");

        String userId = user.userId;

        try{
            IUserSegmentPersistent userSegmentPersistent = new UserSegmentDB(MySqlPersistenceConnection.getInstance().getConnection());
            UserSegment userSegment = userSegmentPersistent.getUserSegmentByUserId(userId);
            if(userSegment != null){
                return userSegment;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
