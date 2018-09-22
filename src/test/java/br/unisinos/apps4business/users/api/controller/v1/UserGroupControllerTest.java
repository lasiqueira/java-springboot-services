package br.unisinos.apps4business.users.api.controller.v1;

import br.unisinos.apps4business.users.api.controller.v1.v1.UserGroupController;
import br.unisinos.apps4business.users.service.UserGroupService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(UserGroupController.class)
public class UserGroupControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserGroupService userGroupService;

    //TODO implement tests
}
