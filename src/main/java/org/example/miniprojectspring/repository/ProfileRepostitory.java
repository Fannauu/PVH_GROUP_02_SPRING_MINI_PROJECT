package org.example.miniprojectspring.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.miniprojectspring.model.entity.AppUser;

import java.util.List;

@Mapper
public interface ProfileRepostitory {
    @Select("""
    SELECT * FROM app_users;
""")
    AppUser getAllUsers();
}
