package com.smartdo.scc.mabang.backend.dao;

import com.smartdo.scc.mabang.backend.bean.User;
import org.apache.ibatis.annotations.Select;

public interface IUser {
     @Select("select * from user where id= #{id}")
     User getUserByID(int id);
}