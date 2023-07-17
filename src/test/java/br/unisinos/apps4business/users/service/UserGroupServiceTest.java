package br.unisinos.apps4business.users.service;

import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.repository.UserGroupRespository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserGroupServiceTest {
    @Autowired
    private UserGroupService userGroupService;
    @MockBean
    private UserGroupRespository userGroupRespository;
    private UserGroup userGroup;
    private UserGroup userGroupRet;
    private List<UserGroup> userGroupList;
    private static final Long ID = 1l;


    @BeforeAll
    public void setup(){
        userGroup = new UserGroup(null, "test", "test");
        userGroupRet = new UserGroup(ID, "test", "test");
        userGroupList = List.of(userGroupRet);
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