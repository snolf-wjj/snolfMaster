package com.snolf.third.classmates.mapper;

import com.snolf.base.BaseMapper;
import com.snolf.third.classmates.model.ClassmatesUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassmatesUserMapper extends BaseMapper<ClassmatesUser> {

    /**
     * 同学录用户登录
     * @param userName
     * @param password
     * @return
     * @throws Exception
     */
    ClassmatesUser login(String userName, String password) throws Exception;

    /**
     * 根据innerCode删除同学录用户
     * @param innerCode
     * @return
     * @throws Exception
     */
    int deleteByInnerCode(Long innerCode) throws Exception;

}