package com.bogeplus.user;

import com.bogeplus.massage.user.BogeplusUserServiceApplication;
import com.bogeplus.massage.user.entity.UserInfo;
import com.bogeplus.massage.user.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BogeplusUserServiceApplication.class) // 指定spring配置文件
class BogeplusUserServiceApplicationTests {

    @Autowired
    UserInfoMapper mapper;
    @Test
    void testDelete() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(1L);
        int i = mapper.deleteById(userInfo);

    }

    @Test
    void testUpdate() {

            UserInfo userInfo = new UserInfo();
            userInfo.setId(1L);
            userInfo.setHeadImg("2222.jpg");
            int i = mapper.updateById(userInfo);
    }
@Test
    void testInsert() {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(5L);
            userInfo.setHeadImg("aaabbb.jpg");
            int i = mapper.updateById(userInfo);
    }
}
