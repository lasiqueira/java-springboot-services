package br.unisinos.apps4business.users.api.converter.v1;

import br.unisinos.apps4business.users.api.dto.v1.UserRequestDTO;
import br.unisinos.apps4business.users.api.dto.v1.UserResponseDTO;
import br.unisinos.apps4business.users.model.User;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class UserConverterTest {
    @Autowired
    private UserConverter userConverter;
    private User user;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;


    //TODO implement tests
}
