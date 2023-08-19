package com.example.reddit;

import com.example.reddit.dto.Group;
import com.example.reddit.service.GroupsService;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = GroupsController.class)
class GroupsControllerTest {
    @MockBean
    private GroupsService groupsService;
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void testCreateGroup() throws Exception {
        //mock
        UUID referenceId = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String name = "srk-fans";
        when(groupsService.createGroup(any())).thenReturn(new Group(referenceId, userId, name));

        //act and verify
        mockMvc.perform(post("/group").content("{\n" +
                "    \"groupName\": \"srk-fans\",\n" +
                "    \"userId\": \"" + userId +"\"\n" +
                "}").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.groupName").value("srk-fans"))
        ;
    }
}