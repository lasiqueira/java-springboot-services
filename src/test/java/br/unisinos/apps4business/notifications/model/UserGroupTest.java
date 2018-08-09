package br.unisinos.apps4business.notifications.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class UserGroupTest {
    private static final Long ID = 1l;
    private static final String NAME = "test";
    private static final String DESCRIPTION = "test description";

    @Test
    public void testUserGroupEmptyConstructor() {
        UserGroup userGroup = new UserGroup();
        assertNotNull(userGroup);
    }

    @Test
    public void testUserGroupConstructor() {
        UserGroup userGroup = new UserGroup(NAME, DESCRIPTION);
        assertNotNull(userGroup);
    }

    @Test
    public void testUserGroupGettersAndSetters() {
        UserGroup userGroup = new UserGroup();
        userGroup.setId(ID);
        userGroup.setName(NAME);
        userGroup.setDescription(DESCRIPTION);
        assertNotNull(userGroup);
        assertEquals(userGroup.getName(), NAME);
        assertEquals(userGroup.getDescription(), DESCRIPTION);
        assertEquals(userGroup.getId(), ID);
    }
}
