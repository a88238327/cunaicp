package com.mocun.cunaicp.cunaicp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class IndexController {

    @Autowired
    private JdbcTemplate jdbcTemple;

    @RequestMapping("/index")
    public List<Map<String,Object>> index(){
        String sql = "SELECT * FROM users";
        List<Map<String,Object>> list = jdbcTemple.queryForList(sql);
        for(Map<String,Object> map:list){
            Set<Map.Entry<String,Object>> entries = map.entrySet();
            if(entries!=null){
                Iterator<Map.Entry<String,Object>> iterator = entries.iterator();
                while (iterator.hasNext()){
                    Map.Entry<String,Object> entry = iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    System.out.println(key+":"+value);
                }
            }
        }
        return list;
    }
}