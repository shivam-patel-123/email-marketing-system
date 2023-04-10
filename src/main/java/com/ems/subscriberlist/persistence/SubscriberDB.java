package com.ems.subscriberlist.persistence;

import com.ems.companydetails.Exception.DatabaseNotFound;
import com.ems.subscriberlist.model.Subscriber;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriberDB implements ISubscriberPersistence{

    public Connection connection;
    public SubscriberDB(Connection conn)
    {
        this.connection = conn;
    }
    @Override
    public List<Subscriber> loadSubscriber(Subscriber subscriber) throws Exception {
        if(connection == null)
        {
            throw  new DatabaseNotFound();
        }
        String sql = "select * from subscriber_list";
        List<Subscriber> totalsubscriber = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                Subscriber subscriberList = new Subscriber();
                subscriberList.sub_id = rs.getString("sub_id");
                subscriberList.sub_email = rs.getString("sub_email");
                subscriberList.sub_first_name = rs.getString("sub_first_name");
                subscriberList.sub_last_name = rs.getString("sub_last_name");
                subscriberList.sub_location = rs.getString("sub_location");
                subscriberList.subscription_date = rs.getString("subscription_date");
                subscriberList.sub_status = rs.getString("sub_status");

                totalsubscriber.add(subscriberList);

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return totalsubscriber;
    }
    @Override
    public int saveSubscriber(Subscriber saveSubscriber) throws Exception {
        try
        {
            Statement statement = connection.createStatement();
            String saveSubscriberQuery = "INSERT INTO subscriber_list VALUES (" +
                    "\"" + saveSubscriber.getSub_id() + "\", " +
                    "\"" + saveSubscriber.getSub_email() + "\", " +
                    "\"" +saveSubscriber.getSub_first_name() + "\", " +
                    "\"" +saveSubscriber.getSub_last_name() + "\", " +
                    "\"" +saveSubscriber.getSub_location() + "\", " +
                    "\"" +saveSubscriber.getSubscription_date() + "\", " +
                    "\"" +saveSubscriber.getSub_status() + "\")";
            return statement.executeUpdate(saveSubscriberQuery);

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public int saveSubcriberAndUserSegmentID(Subscriber saveSubscriberwithUserSegmentID, String UserSegmentId) throws Exception {
        try
        {
            Statement statement = connection.createStatement();
            String saveSubscriberIdwithUserSegmentId = "INSERT INTO user_segment_has_subscriber VALUES (" +
                    "\"" + saveSubscriberwithUserSegmentID.getSub_id() + "\", " +
                    "\"" +UserSegmentId + "\")";
            return statement.executeUpdate(saveSubscriberIdwithUserSegmentId);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return -1;
        }

    }

    @Override
    public List<Subscriber> getSubscriberByCampaignId(String campaignId) throws Exception {
        if(connection == null)
        {
            throw  new DatabaseNotFound();
        }
        String sql = "select subscriber_list.* from campaign,user_segment_has_subscriber,subscriber_list where campaign.user_segment_id = user_segment_has_subscriber.user_segment_id\n" +
                "and user_segment_has_subscriber.sub_id = subscriber_list.sub_id and campaign_id =\""+campaignId +"\"";
        List<Subscriber> totalSubscriberForCampaign = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next())
            {
                Subscriber subscriberList = new Subscriber();
                subscriberList.sub_id = rs.getString("sub_id");
                subscriberList.sub_email = rs.getString("sub_email");
                subscriberList.sub_first_name = rs.getString("sub_first_name");
                subscriberList.sub_last_name = rs.getString("sub_last_name");
                subscriberList.sub_location = rs.getString("sub_location");
                subscriberList.subscription_date = rs.getString("subscription_date");
                subscriberList.sub_status = rs.getString("sub_status");

                totalSubscriberForCampaign.add(subscriberList);

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return totalSubscriberForCampaign;


    }

    @Override
    public List<Subscriber> getSubscriberByUserId(String userId) throws Exception {
        if(connection == null)
        {
            throw  new DatabaseNotFound();
        }
        String sqlquery = "SELECT subscriber_list.* FROM user,user_segment,user_segment_has_subscriber,subscriber_list\n" +
                "where user.user_id=user_segment.user_id\n" +
                "and user_segment.user_segment_id=user_segment_has_subscriber.user_segment_id\n" +
                "and user_segment_has_subscriber.sub_id=subscriber_list.sub_id\n" +
                "and user.user_id=\""+userId +"\"";
        List<Subscriber> totalSubscriberForCompany = new ArrayList<>();
        try
        {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sqlquery);
            while(rs.next())
            {
                Subscriber subscriberList = new Subscriber();
                subscriberList.sub_id = rs.getString("sub_id");
                subscriberList.sub_email = rs.getString("sub_email");
                subscriberList.sub_first_name = rs.getString("sub_first_name");
                subscriberList.sub_last_name = rs.getString("sub_last_name");
                subscriberList.sub_location = rs.getString("sub_location");
                subscriberList.subscription_date = rs.getString("subscription_date");
                subscriberList.sub_status = rs.getString("sub_status");

                totalSubscriberForCompany.add(subscriberList);

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return totalSubscriberForCompany;
    }

    @Override
    public Subscriber getSubscriberBySubscriberId(String subscriberId) {
        if (connection != null) {
            String sql = "select * from subscriber_list where sub_id= \"" + subscriberId + "\"";
            try {
                Subscriber subscriber = new Subscriber();
                PreparedStatement stmt = connection.prepareStatement(sql);

                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    subscriber.sub_id = rs.getString("sub_id");
                    subscriber.sub_first_name = rs.getString("sub_first_name");
                    subscriber.sub_last_name = rs.getString("sub_last_name");
                    subscriber.sub_location = rs.getString("sub_location");
                    subscriber.sub_email = rs.getString("sub_email");
                    subscriber.subscription_date = rs.getString("subscription_date");
                    subscriber.sub_status = rs.getString("sub_status");
                }

                return subscriber;

            } catch (Exception exception) {
                exception.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
