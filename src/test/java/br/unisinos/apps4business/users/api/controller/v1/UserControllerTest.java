package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.converter.v1.UserConverter;
import br.unisinos.apps4business.users.api.dto.v1.UserRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserResponseDTO;
import br.unisinos.apps4business.users.model.User;
import br.unisinos.apps4business.users.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;
    @MockBean
    private UserConverter userConverter;

    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;
    private List<UserResponseDTO> userResponseDTOList;
    private List<User> userList;
    private User user;

    @Before
    public void setup(){
        user = random(User.class);
        userRequestDTO = random(UserRequestDTO.class);
        userResponseDTO = random(UserResponseDTO.class);
        userList = new ArrayList<>();
        userList.add(user);
        userResponseDTOList = new ArrayList<>();
        userResponseDTOList.add(userResponseDTO);
    }

    @Test
    public void createUserTest() throws Exception{
        when(userService.createUser(Mockito.any(User.class))).thenReturn(user);
        when(userConverter.convertEntityToResponse(Mockito.any(User.class))).thenReturn(userResponseDTO);
        mockMvc.perform(post("/v1/users")
                .content(objectMapper.writeValueAsString(userRequestDTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void updateUserTest() throws Exception{
        when(userService.updateUser(Mockito.anyLong(), Mockito.any(User.class))).thenReturn(user);
        when(userConverter.convertEntityToResponse(Mockito.any(User.class))).thenReturn(userResponseDTO);
        mockMvc.perform(patch("/v1/users/{id}", 1)
                .content(objectMapper.writeValueAsString(userRequestDTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    @Test
    public void getUserTest() throws Exception{
        when(userService.findById(Mockito.anyLong())).thenReturn(user);
        when(userConverter.convertEntityToResponse(Mockito.any(User.class))).thenReturn(userResponseDTO);
        mockMvc.perform(get("/v1/users/{id}", 1)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void fetchAllUsersTest() throws Exception{
        when(userService.fetchAllUsers()).thenReturn(userList);
        when(userConverter.convertEntityListToResponseList(userList)).thenReturn(userResponseDTOList);
        mockMvc.perform(get("/v1/users")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void deleteUserTest() throws Exception{
        mockMvc.perform(delete("/v1/users/{id}", 1)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
