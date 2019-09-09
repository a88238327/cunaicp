package com.mocun.cunaicp.cunaicp.dao;

import java.sql.*;

public class baseDao {
    //实现增删改查的功能  以及连接 和释放资源
    static Connection cnt=null;
    static PreparedStatement ps=null;
    static ResultSet rs=null;

    //连接有3种方式
    public static boolean getConnection(){
        String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://47.107.251.222:1433;DatabaseName=chejiacloud";
        String username="sa";
        String password="88238327";
        try {
            Class.forName(driver);
            cnt= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return true;

    }

    //关闭资源
    public static boolean closeResource(){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        if(cnt!=null){
            try {
                cnt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return true;

    }

    //增删改
    public int executeUpdate(String sql){
        int updateRows=0;
        if(this.getConnection()){
            //获得sql语句
            try {
                ps=cnt.prepareStatement(sql);
                //执行 增删改,返回影响的行数
                updateRows=ps.executeUpdate();

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return updateRows;

    }

    //查询 全部信息 或者指定部分信息
    public static ResultSet ExecuteQuery(String sql){
        if(baseDao.getConnection()){
            try {
                ps=cnt.prepareStatement(sql);
                // 执行查询，返回值是ResultSet型的
                rs=ps.executeQuery();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return rs;
    }



}

