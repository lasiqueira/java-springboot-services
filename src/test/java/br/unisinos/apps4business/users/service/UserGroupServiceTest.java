package br.unisinos.apps4business.users.service;

import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.repository.UserGroupRespository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserGroupServiceTest {
    @Autowired
    private UserGroupService userGroupService;
    @MockBean
    private UserGroupRespository userGroupRespository;
    private UserGroup userGroup;
    private UserGroup userGroupRet;
    private List<UserGroup> userGroupList;
    private static final Long ID = 1l;


    @Before
    public void setup(){
        userGroup = random(UserGroup.class);
        userGroupRet = new UserGroup();
        userGroupRet.setDescription(userGroup.getDescription());
        userGroupRet.setName(userGroup.getName());
        userGroupRet.setId(userGroup.getId());
        userGroup.setId(null);
        userGroupRet.setId(ID);
        userGroupList = new ArrayList<>();
        userGroupList.add(userGroupRet);
    }

    @Test
    public void createUserGroupTest(){
        when(userGroupRespository.save(userGroup)).thenReturn(userGroupRet);
        UserGroup savedEntity = userGroupService.createUserGroup(userGroup);

        assertNotNull(savedEntity);
        assertEquals(savedEntity.getId(), userGroupRet.getId());
        assertEquals(savedEntity.getDescription(), userGroupRet.getDescription());
        assertEquals(savedEntity.getName(), userGroupRet.getName());
    }

    @Test
    public void updateUserGroupTest(){
        when(userGroupRespository.save(userGroup)).thenReturn(userGroupRet);
        when(userGroupRespository.existsById(ID)).thenReturn(Boolean.TRUE);
        UserGroup savedEntity = userGroupService.updateUserGroup(ID, userGroup);

        assertNotNull(savedEntity);
        assertEquals(savedEntity.getId(), userGroupRet.getId());
        assertEquals(savedEntity.getDescription(), userGroupRet.getDescription());
        assertEquals(savedEntity.getName(), userGroupRet.getName());
    }

    @Test
    public void fetchAllUserGroupsTest(){
        when(userGroupRespository.findAll()).thenReturn(userGroupList);
        List<UserGroup> returnList = userGroupService.fetchAllUserGroups();

        assertNotNull(returnList);
        assertFalse(returnList.isEmpty());

    }

}
