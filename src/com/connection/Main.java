package com.connection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args){
        dbFunction db= new dbFunction();
        Connection conn= db.connect_to_db("newbase", "susavkarki", "susav");
        //db.createTable(conn, "employee");
        //db.insertRow(conn, "employee", "yogesh", "kathmandu");
        //db.update_name(conn, "employee", "raghav", "yogesh");
        db.search_name(conn, "employee", "subham");
        db.read_data(conn, "employee");
    }
}
