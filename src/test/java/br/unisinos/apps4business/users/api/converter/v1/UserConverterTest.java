package br.unisinos.apps4business.users.api.converter.v1;

import br.unisinos.apps4business.users.api.dto.v1.UserRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserResponseDTO;
import br.unisinos.apps4business.users.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserConverterTest {
    @Autowired
    private UserConverter userConverter;
    private User user;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;
    private List<User> users;

    @Before
    public void setup(){
        user = random(User.class);
        userRequestDTO = random(UserRequestDTO.class);
        userResponseDTO = random(UserResponseDTO.class);
        users = new ArrayList<>();
        users.add(user);
    }

    @Test
    public void convertRequestToEntityTest(){
        User entity = userConverter.convertRequestToEntity(userRequestDTO);
        assertNotNull(entity);
        assertEquals(entity.getEmail(), userRequestDTO.getEmail());
        assertEquals(entity.getName(), userRequestDTO.getName());
        assertEquals(entity.getLogin(), userRequestDTO.getLogin());
        assertEquals(entity.getRole(), userRequestDTO.getRole());

        assertNotNull(entity.getUserGroups());
        assertFalse(entity.getUserGroups().isEmpty());

        assertEquals(entity.getUserGroups().get(0).getName(), userRequestDTO.getUserGroups().get(0).getName());
        assertEquals(entity.getUserGroups().get(0).getId(), userRequestDTO.getUserGroups().get(0).getId());
        assertEquals(entity.getUserGroups().get(0).getDescription(), userRequestDTO.getUserGroups().get(0).getDescription());
    }

    @Test
    public void convertEntityToResponseTest(){
        UserResponseDTO response = userConverter.convertEntityToResponse(user);
        assertNotNull(response);
        assertEquals(response.getEmail(), user.getEmail());
        assertEquals(response.getName(), user.getName());
        assertEquals(response.getLogin(), user.getLogin());
        assertEquals(response.getRole(), user.getRole());
        assertEquals(response.getId(), user.getId());

        assertNotNull(response.getUserGroups());
        assertFalse(response.getUserGroups().isEmpty());

        assertEquals(response.getUserGroups().get(0).getName(), user.getUserGroups().get(0).getName());
        assertEquals(response.getUserGroups().get(0).getId(), user.getUserGroups().get(0).getId());
        assertEquals(response.getUserGroups().get(0).getDescription(), user.getUserGroups().get(0).getDescription());
    }
    @Test
    public void convertEntityListToResponseListTest(){
        List<UserResponseDTO> userResponseDTOList = userConverter.convertEntityListToResponseList(users);
        assertNotNull(userResponseDTOList);
        assertFalse(userResponseDTOList.isEmpty());
    }
}
