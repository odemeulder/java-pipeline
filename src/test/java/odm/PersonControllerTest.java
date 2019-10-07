package odm;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonRepository repo;

    @Test
    public void getPersonShouldReturnRightPerson() throws Exception {
        Person p = new Person();
        p.setFirstName("Olivier");
        p.setLastName("DM");
        when(repo.findById(1L)).thenReturn(Optional.ofNullable(p));
        this.mockMvc.perform(get("/persons/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Olivier")));
        this.mockMvc.perform(get("/persons/2")).andDo(print()).andExpect(status().isNotFound());
    }
}