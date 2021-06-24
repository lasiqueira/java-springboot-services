package br.unisinos.apps4business.users.api.converter.v1;

import br.unisinos.apps4business.users.api.dto.v1.UserGroupRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupResponseDTO;
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

    @BeforeAll
    public void setup() {
        converter = new Converter();

    }

    @Test
    public void convertUserGroupToUserGroupResponseDTOTest() {
        UserGroup userGroup = new UserGroup(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION);
        UserGroupResponseDTO expected = new UserGroupResponseDTO(userGroup.getId(), userGroup.getName(), userGroup.getDescription());
        UserGroupResponseDTO actual = converter.convertUserGroupToUserGroupResponseDTO(userGroup);
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
    public void convertUserGroupRequestToUserGroupListTest() {
        List<UserGroupResponseDTO> userGroupResponseDTOList = List.of(new UserGroupResponseDTO(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION));
        List<UserGroup> expected = List.of(new UserGroup(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION));
        List<UserGroup> actual = converter.convertUserGroupResponseDTOListToUserGroupList(userGroupResponseDTOList);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserGroupListToUserGroupResponseDTOListTest() {
        List<UserGroup> userGroupResponseDTOList = List.of(new UserGroup(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION));
        List<UserGroupResponseDTO> expected = List.of(new UserGroupResponseDTO(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION));
        List<UserGroupResponseDTO> actual = converter.convertUserGroupListToUserGroupResponseDTOList(userGroupResponseDTOList);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserRequestDTOToUserTest() {
        UserRequestDTO userGroupResponseDTOList = new UserRequestDTO(USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(new UserGroupResponseDTO(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION)));
        User expected = new User(null, USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(new UserGroup(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION)));
        User actual = converter.convertUserRequestDTOToUser(userGroupResponseDTOList);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserToUserResponseDTOTest() {
        User user = new User(USER_ID, USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(new UserGroup(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION)));
        UserResponseDTO expected = new UserResponseDTO(USER_ID, USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(new UserGroupResponseDTO(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION)));
        UserResponseDTO actual = converter.convertUserToUserResponseDTO(user);
        assertEquals(expected, actual);
    }

    @Test
    public void convertUserListToUserResponseDTOListTest() {
        List<User> users = List.of(new User(USER_ID, USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(new UserGroup(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION))));
        List<UserResponseDTO> expected = List.of(new UserResponseDTO(USER_ID, USER_ROLE, USER_LOGIN, USER_NAME, USER_EMAIL, List.of(new UserGroupResponseDTO(USER_GROUP_ID, USER_GROUP_NAME, USER_GROUP_DESCRIPTION))));
        List<UserResponseDTO> actual = converter.convertUserListToUserResponseDTOList(users);
        assertEquals(expected, actual);
    }

}
