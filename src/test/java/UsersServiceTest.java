import Exceptions.CustomFieldException;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UsersServiceTest {
    private UsersService usersService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void globalSetUp(){
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp(){
        System.out.println("Code executes before each method.");
        Users user1 = new Users("John", LocalDate.of(1994, 3, 17));
        Users user2 = new Users("Alice", LocalDate.of(1970, 4, 17));
        Users user3 = new Users("Melinda", LocalDate.of(1997, 6, 23));
        List<Users> usersList = new ArrayList<>();
        usersList.add(user1);
        usersList.add(user2);
        usersList.add(user3);
        usersService = new UsersService(usersList);
    }

    @Test
    public void whenCreatNewUserThenReturnListWithNewUser() throws CustomFieldException {
        assertThat(usersService.getUsers().size(), is(3));
        usersService.createNewUser("New User", LocalDate.of(1990, 2, 1));
        assertThat(usersService.getUsers().size(), is(4));
    }

    @Test
    public void whenRemoveUserThenReturnListWithoutUser() {
        assertThat(usersService.getUsers().size(), is(3));
        usersService.removeUser("Alice");
        assertThat(usersService.getUsers().size(), is(2));
    }

    @Test
    public void whenCreateUserWithNullNameThenThrowCustomFiledException() throws CustomFieldException {
        thrown.expect(CustomFieldException.class);
        thrown.expectMessage("Name could not be empty or null");
        usersService.createNewUser(null, LocalDate.of(1990, 2, 1));
    }

    @Test
    public void whenCreateUserWithEmptyNameThenThrowCustomFiledException() throws CustomFieldException {
        thrown.expect(CustomFieldException.class);
        thrown.expectMessage("Name could not be empty or null");
        usersService.createNewUser("  ", LocalDate.of(1990, 2, 1));
    }

    @Test
    public void whenCreateUserWithNullBirthDayThenThrowCustomFiledException() throws CustomFieldException {
        thrown.expect(CustomFieldException.class);
        thrown.expectMessage("Date of birth could not be null");
        usersService.createNewUser("Dave", null);
    }

    @AfterClass
    public static void tearDown(){
        System.out.println("All tests completed");
    }

    @After
    public void afterMethod(){
        System.out.println("Code executes after each test method");
    }
}
