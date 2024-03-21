package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class dbFunction {
    public Connection connect_to_db(String dbname, String user, String pass){
        Connection conn=null;
        try{
            Class.forName("org.postgresql.Driver");
            conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if(conn!=null){
                System.out.println("Connection Established");
            }else{
                System.out.println("Connection failed");
            }

        }catch(Exception e){
            System.out.println(e);
        }
        return conn;
    }
    public void createTable(Connection conn, String table_name){
        Statement statement;
        try{
            String query= "create table "+ table_name + "(empid SERIAL, name varchar(200), address varchar(200), primary key(empid))";
            statement= conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table created");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void insertRow(Connection conn, String table_name, String name, String address){
        Statement statement;
        try {
            String query= String.format("insert into %s(name, address) values('%s', '%s')", table_name, name, address);
            statement= conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("Row created");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void read_data(Connection conn, String table_name){
        Statement statement;
        ResultSet rs= null;
        try{
            String query= String.format("select * from %s", table_name);
            statement= conn.createStatement();
            rs= statement.executeQuery(query);
            while(rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void update_name(Connection conn, String table_name, String new_name, String old_name){
        Statement statement;
        try{
            String query= String.format("update %s set name= '%s' where name= '%s'", table_name, new_name, old_name);
            statement= conn.createStatement();
            statement.executeUpdate(query);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void search_name(Connection conn, String table_name, String name) {
        Statement statement;
        ResultSet rs= null;
        try{
            String query= String.format("select * from %s where name= '%s'",table_name, name);
            statement= conn.createStatement();
            rs= statement.executeQuery(query);
            while (rs.next()){
                System.out.print(rs.getString("empid")+" ");
                System.out.print(rs.getString("name")+" ");
                System.out.println(rs.getString("address"));
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
