package com.edu.module.controller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Data extends Config {

    private static Data data;
    private static Connection connection;

    public Data(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(Config.HOST, Config.USER, Config.PASS);
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public String select(String id){
        try {
            PreparedStatement select = connection.prepareStatement("SELECT question FROM " + Config.TABLE_NAME_QUESTIONS +
                    " WHERE id = ?");
            select.setString(1,id);
            ResultSet resultSet = select.executeQuery();
            String question = "";
            while (resultSet.next()){
                question = resultSet.getString(1);
            }
            return question;
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public int getResult(String question, String answer){
        int result = 0;
        try {
            PreparedStatement select = connection.prepareStatement("SELECT answer, points FROM " + Config.TABLE_NAME_QUESTIONS +
                    " WHERE question = ?");
            select.setString(1,question);
            ResultSet resultSet = select.executeQuery();
            String userAnswer = "";
            while (resultSet.next()){
                userAnswer = resultSet.getString(1);
                if(answer.equals(userAnswer)){
                    result = resultSet.getInt(2);
                }
            }
            return 0;
        }catch (Exception e){
            System.out.println(e);
        }
        return result;
    }
    public void insertNewUser(String name){
        try {
            PreparedStatement insert  = connection.prepareStatement("INSERT INTO " + Config.TABLE_NAME_USERS +
                    " (name)" + "VALUES(?)");
            insert.setString(1,name);

            insert.executeUpdate();
            System.out.println("Success Insert");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Data data(){
        if(data == null){
            data = new Data();
        }
        return data;
    }

}
