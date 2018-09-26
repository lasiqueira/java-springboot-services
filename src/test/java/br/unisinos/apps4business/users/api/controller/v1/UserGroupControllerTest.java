package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.converter.v1.UserGroupConverter;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupResponseDTO;
import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.service.UserGroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static io.github.benas.randombeans.api.EnhancedRandom.random;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserGroupController.class)
//@SpringBootTest
public class UserGroupControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private UserGroupService userGroupService;
    @MockBean
    private UserGroupConverter userGroupConverter;
    private UserGroupRequestDTO userGroupRequestDTO;
    private UserGroupResponseDTO userGroupResponseDTO;
    private List<UserGroupResponseDTO> userGroupResponseDTOList;
    private UserGroup userGroupRet;
    private List<UserGroup> userGroupList;


    @Before
    public void setup(){
        userGroupRequestDTO = random(UserGroupRequestDTO.class);
        userGroupRet = random(UserGroup.class);
        userGroupResponseDTO = random(UserGroupResponseDTO.class);
        userGroupList = new ArrayList<>();
        userGroupList.add(userGroupRet);
        userGroupResponseDTOList = new ArrayList<>();
        userGroupResponseDTOList.add(userGroupResponseDTO);

    }

    @Test
    public void fetchAllUserGroupsTest() throws Exception{
        when(userGroupService.fetchAllUserGroups())
                .thenReturn(userGroupList);
        when(userGroupConverter.convertEntityListToResponseList(userGroupList)).thenReturn(userGroupResponseDTOList);
        mockMvc.perform(get("/v1/usergroups")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    public void createUserGroupTest() throws Exception{
        when(userGroupService.createUserGroup(Mockito.any(UserGroup.class)))
                .thenReturn(userGroupRet);
        when(userGroupConverter.convertEntityToResponse(Mockito.any(UserGroup.class))).thenReturn(userGroupResponseDTO);
        mockMvc.perform(post("/v1/usergroups")
                .content(objectMapper.writeValueAsString(userGroupRequestDTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserGroup() throws Exception{
        when(userGroupService.updateUserGroup(Mockito.anyLong(),Mockito.any(UserGroup.class)))
                .thenReturn(userGroupRet);
        when(userGroupConverter.convertEntityToResponse(Mockito.any(UserGroup.class))).thenReturn(userGroupResponseDTO);
        mockMvc.perform(patch("/v1/usergroups/{id}", 1)
                .content(objectMapper.writeValueAsString(userGroupRequestDTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
