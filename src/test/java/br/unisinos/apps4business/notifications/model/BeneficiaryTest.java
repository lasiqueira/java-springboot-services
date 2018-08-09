package br.unisinos.apps4business.notifications.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class BeneficiaryTest {
    private static final Long ID = 1l;
    private static final String USER_NAME = "test name";
    private static final String USER_LOGIN = "test login";
    private static final String USER_EMAIL = "test email";
    private List<UserGroup> userGroupList;
    private List<Notification> notificationList;

    @Test
    public void testBeneficiaryEmptyConstructor() {
        Operator operator = new Operator();
        assertNotNull(operator);
    }

    @Test
    public void testBeneficiaryConstructor() {
        Beneficiary beneficiary = new Beneficiary(USER_NAME, USER_LOGIN, USER_EMAIL, userGroupList, notificationList);
        assertNotNull(beneficiary);
    }

    @Test
    public void testBeneficiaryGettersAndSetters() {
        Beneficiary beneficiary = new Beneficiary();
        beneficiary.setId(ID);
        beneficiary.setEmail(USER_EMAIL);
        beneficiary.setLogin(USER_LOGIN);
        beneficiary.setName(USER_NAME);
        beneficiary.setUserGroups(userGroupList);
        beneficiary.setNotifications(notificationList);

        assertNotNull(beneficiary);
        assertEquals(beneficiary.getEmail(), USER_EMAIL);
        assertEquals(beneficiary.getLogin(), USER_LOGIN);
        assertEquals(beneficiary.getName(), USER_NAME);
        assertEquals(beneficiary.getId(), ID);
        assertEquals(beneficiary.getUserGroups(), userGroupList);
        assertEquals(beneficiary.getNotifications(), notificationList);
    }
}
