package br.unisinos.apps4business.users.service;

import br.unisinos.apps4business.users.enumerators.Role;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    private User user;
    private User userRet;
    private UserGroup userGroup;
    private List<User> userList;
    private static final Long ID = 1l;
    @BeforeAll
    public void setup(){
        userGroup =  new UserGroup(ID, "test", "test");
        user = new User(null, Role.OPERATOR,"test", "test", "test@test.com", List.of(userGroup));
        userRet = new User(ID, Role.OPERATOR,"test", "test", "test@test.com", List.of(userGroup));


        userList = List.of(userRet);
    }

    @Test
    public void fetchAllUsersTest(){
        when(userRepository.findAll()).thenReturn(userList);
        List<User> returnList = userService.fetchAllUsers();
        assertNotNull(returnList);
        assertFalse(returnList.isEmpty());
    }
    @Test
    public void findByUserGroupTest() {
        when(userRepository.findByUserGroups(Mockito.any(UserGroup.class))).thenReturn(userList);
        List<User> returnList = userService.findByUserGroup(userGroup);
        assertNotNull(returnList);
        assertFalse(returnList.isEmpty());
        assertFalse(returnList.get(0).getUserGroups().isEmpty());
        assertEquals(returnList.get(0).getUserGroups().get(0).getId(), userGroup.getId());
        assertEquals(returnList.get(0).getUserGroups().get(0).getName(), userGroup.getName());
        assertEquals(returnList.get(0).getUserGroups().get(0).getDescription(), userGroup.getDescription());
    }
    @Test
    public void findByRoleTest(){
        when(userRepository.findByRole(Role.OPERATOR)).thenReturn(userList);
        List<User> returnList = userService.findByRole(Role.OPERATOR);
        assertNotNull(returnList);
        assertFalse(returnList.isEmpty());
        assertEquals(returnList.get(0).getRole(), Role.OPERATOR);
    }
    @Test
    public void findByIdTest(){
        when(userRepository.findById(ID)).thenReturn(Optional.of(userRet));
        User entity = userService.findById(ID);
        assertNotNull(entity);
        assertEquals(entity.getId(), ID);
    }
    @Test
    public void createUserTest(){
        when(userRepository.save(user)).thenReturn(userRet);
        User savedEntity = userService.createUser(user);
        assertNotNull(savedEntity);
        assertEquals(savedEntity.getId(), userRet.getId());
        assertEquals(savedEntity.getRole(), userRet.getRole());
        assertEquals(savedEntity.getLogin(), userRet.getLogin());
        assertEquals(savedEntity.getName(), userRet.getName());
        assertEquals(savedEntity.getEmail(), userRet.getEmail());
    }
    @Test
    public void updateUserTest(){
        when(userRepository.existsById(ID)).thenReturn(Boolean.TRUE);
        when(userRepository.save(user)).thenReturn(userRet);
        User savedEntity = userService.updateUser(ID, user);
        assertNotNull(savedEntity);
        assertEquals(savedEntity.getId(), userRet.getId());
        assertEquals(savedEntity.getRole(), userRet.getRole());
        assertEquals(savedEntity.getLogin(), userRet.getLogin());
        assertEquals(savedEntity.getName(), userRet.getName());
        assertEquals(savedEntity.getEmail(), userRet.getEmail());
    }

}
