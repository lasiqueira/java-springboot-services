package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.converter.v1.Converter;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserGroupDTO;
import br.unisinos.apps4business.users.model.UserGroup;
import br.unisinos.apps4business.users.service.UserGroupService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserGroupController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserGroupControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private UserGroupService userGroupService;
    @MockBean
    private Converter converter;
    private UserGroupRequestDTO userGroupRequestDTO;
    private UserGroupDTO userGroupDTO;
    private List<UserGroupDTO> userGroupDTOList;
    private UserGroup userGroupRet;
    private List<UserGroup> userGroupList;


    @BeforeAll
    public void setup(){
        userGroupRequestDTO = new UserGroupRequestDTO("test", "test");
        userGroupRet =  new UserGroup(1L, "test", "test");
        userGroupDTO = new UserGroupDTO(1L, "test", "test");
        userGroupList =List.of(userGroupRet);
        userGroupDTOList = List.of(userGroupDTO);

    }

    @Test
    public void fetchAllUserGroupsTest() throws Exception{
        when(userGroupService.fetchAllUserGroups())
                .thenReturn(userGroupList);
        when(converter.convertUserGroupListToUserGroupResponseDTOList(userGroupList)).thenReturn(userGroupDTOList);
        mockMvc.perform(get("/v1/usergroups")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    public void createUserGroupTest() throws Exception{
        when(userGroupService.createUserGroup(Mockito.any(UserGroup.class)))
                .thenReturn(userGroupRet);
        when(converter.convertUserGroupToUserGroupResponseDTO(userGroupRet)).thenReturn(userGroupDTO);
        mockMvc.perform(post("/v1/usergroups")
                .content(objectMapper.writeValueAsString(userGroupRequestDTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void updateUserGroup() throws Exception{
        when(userGroupService.updateUserGroup(Mockito.anyLong(),Mockito.any(UserGroup.class)))
                .thenReturn(userGroupRet);
        when(converter.convertUserGroupToUserGroupResponseDTO(userGroupRet)).thenReturn(userGroupDTO);
        mockMvc.perform(patch("/v1/usergroups/{id}", 1)
                .content(objectMapper.writeValueAsString(userGroupRequestDTO))
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
