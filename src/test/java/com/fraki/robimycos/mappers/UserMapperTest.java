package com.fraki.robimycos.mappers;

import com.fraki.robimycos.data.businessmodels.User;
import com.fraki.robimycos.data.entities.UserEntity;
import com.fraki.robimycos.data.mappers.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by bambo on 09.10.2017.
 */

@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Test
    public void testMappingEntityToUser() {
        UserEntity entity = new UserEntity();
        entity.setId(5);
        entity.setLogin("Login");
        entity.setPassword("Password");
        User user = UserMapper.convertToUser(entity);
        Assert.assertEquals("Login", user.getLogin());
        Assert.assertEquals(5, user.getId());
    }

}
