package github.action.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    void memberAdd() throws Exception {
        MemberRequestDTO memberRequestDTO = MemberRequestDTO.builder()
                                                            .name("하수민")
                                                            .email("soominna@gmail.com")
                                                            .build();

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/members/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(memberRequestDTO)));

        resultActions.andExpect(status().isCreated()).andExpect(jsonPath("$.name")
                .value(equalTo("하수민")));
    }

    @Test
    void memberDetailById() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/members/1"));

        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(equalTo(1)))
                .andDo(print());
    }

    @Test
    void memberNameModify() throws Exception {
        MemberRequestDTO memberRequestDTO = MemberRequestDTO.builder().name("김수연").build();

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/members/1/name")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(equalTo("김수연")))
                .andDo(print());
    }

    @Test
    void memberPointModify() throws Exception {
        PointRequestDTO pointRequestDTO = PointRequestDTO.builder().point(3).build();

        this.mockMvc.perform(MockMvcRequestBuilders.patch("/members/1/point")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(pointRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.point").value(equalTo(3)))
                .andDo(print());
    }

    @Test
    void memberDelete() throws Exception {
        this.mockMvc.perform((MockMvcRequestBuilders.delete("/members/1")))
                .andExpect(status().isOk())
                .andDo(print());
    }
}