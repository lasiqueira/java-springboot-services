package br.unisinos.apps4business.notifications.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
@RunWith(SpringRunner.class)
public class OperatorTest {

    private static final Long ID = 1l;
    private static final String USER_NAME = "test name";
    private static final String USER_LOGIN = "test login";
    private static final String USER_EMAIL = "test email";
    private static final String OPERATOR_ROLE = "test role";


    @Test
    public void testOperatorEmptyConstructor() {
        Operator operator = new Operator();
        assertNotNull(operator);
    }

    @Test
    public void testOperatorConstructor() {
        Operator operator = new Operator(USER_NAME, USER_LOGIN, USER_EMAIL, OPERATOR_ROLE);
        assertNotNull(operator);
    }

    @Test
    public void testOperatorGettersAndSetters() {
       Operator operator = new Operator();
       operator.setId(ID);
       operator.setEmail(USER_EMAIL);
       operator.setLogin(USER_LOGIN);
       operator.setName(USER_NAME);
       operator.setRole(OPERATOR_ROLE);

       assertNotNull(operator);
       assertEquals(operator.getRole(), OPERATOR_ROLE);
       assertEquals(operator.getEmail(), USER_EMAIL);
       assertEquals(operator.getLogin(), USER_LOGIN);
       assertEquals(operator.getName(), USER_NAME);
       assertEquals(operator.getId(), ID);
    }

}
