package br.unisinos.apps4business.users.api.converter.v1;

import br.unisinos.apps4business.users.api.dto.v1.UserGroupRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserResponseDTO;
import br.unisinos.apps4business.users.enumerators.Role;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.model.UserGroup;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ConverterTest {
    private Converter converter;

    private static Long USER_GROUP_ID = 1L;
    private static String USER_GROUP_NAME = "test";
    private static String USER_GROUP_DESCRIPTION = "test";

    private static Long USER_ID = 1L;
    private static Role USER_ROLE = Role.OPERATOR;
    private static String USER_LOGIN = "test";
    private static String USER_NAME = "test";
    private static String USER_EMAIL = "test@test.com";

    private User user;
    private UserGroup userGroup;
    private UserGroupDTO userGroupDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeAll
    public void setup() {
        converter = new Converter();
        userGroup = new UserGroup(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION);
        user = new User(USER_ID, USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(userGroup));
        userGroupDTO = new UserGroupDTO(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION);
        userResponseDTO = new UserResponseDTO(USER_ID, USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(userGroupDTO));
    }

    @Test
    public void convertUserGroupToUserGroupResponseDTOTest() {
        UserGroupDTO expected = new UserGroupDTO(userGroup.getId(), userGroup.getName(), userGroup.getDescription());
        UserGroupDTO actual = converter.convertUserGroupToUserGroupResponseDTO(userGroup);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserGroupRequestToUserGroupTest() {
        UserGroupRequestDTO userGroupRequestDTO = new UserGroupRequestDTO(USER_GROUP_NAME, USER_GROUP_DESCRIPTION);
        UserGroup expected = new UserGroup(null, userGroupRequestDTO.name(), userGroupRequestDTO.description());
        UserGroup actual = converter.convertUserGroupRequestDTOToUserGroup(userGroupRequestDTO);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserGroupResponseToUserGroupListTest() {
        List<UserGroupDTO> userGroupDTOList = List.of(userGroupDTO);
        List<UserGroup> expected = List.of(userGroup);
        List<UserGroup> actual = converter.convertUserGroupResponseDTOListToUserGroupList(userGroupDTOList);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserGroupListToUserGroupResponseDTOListTest() {
        List<UserGroup> userGroupResponseDTOList = List.of(userGroup);
        List<UserGroupDTO> expected = List.of(userGroupDTO);
        List<UserGroupDTO> actual = converter.convertUserGroupListToUserGroupResponseDTOList(userGroupResponseDTOList);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserRequestDTOToUserTest() {
        UserRequestDTO userGroupResponseDTO = new UserRequestDTO(USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(userGroupDTO));
        User expected = new User(null, USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(userGroup));
        User actual = converter.convertUserRequestDTOToUser(userGroupResponseDTO);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserToUserResponseDTOTest() {
        UserResponseDTO expected = userResponseDTO;
        UserResponseDTO actual = converter.convertUserToUserResponseDTO(user);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserListToUserResponseDTOListTest() {
        List<User> users = List.of(user);
        List<UserResponseDTO> expected = List.of(userResponseDTO);
        List<UserResponseDTO> actual = converter.convertUserListToUserResponseDTOList(users);
        assertEquals(expected, actual);
    }

}
