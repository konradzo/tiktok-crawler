package pl.kzochowski.tiktokcrawler.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.kzochowski.tiktokcrawler.model.PageUrl;
import pl.kzochowski.tiktokcrawler.model.Profile;
import pl.kzochowski.tiktokcrawler.service.ProfileService;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    private static ObjectMapper mapper;

    @BeforeClass
    public static void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldReturnBadRequestStatusWhenEmptyBody() throws Exception {

        //todo add return error object

        // then
         mockMvc.perform(post("/profiles")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

    }

    @Test
    public void shouldAddProfile() throws Exception {

        // given
        Profile profile = sampleProfile();
        PageUrl pageUrl = new PageUrl("https://www.tiktok.com/@sample_profile");
        String jsonAsString = mapper.writeValueAsString(pageUrl);
        given(profileService.addProfile("https://www.tiktok.com/@sample_profile")).willReturn(profile);

        // then
        MvcResult result = mockMvc.perform(post("/profiles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonAsString))
                .andExpect(status().isCreated())
                .andReturn();

        assertThat(result.getResponse().getContentAsString(), equalTo(mapper.writeValueAsString(profile)));

    }

    @Test
    public void shouldReturnEmptyList() throws Exception {

        // then
//        mockMvc.perform(get("/profiles"))
//                .andExpect();

    }

    private Profile sampleProfile() {
        return Profile.builder()
                .id(1)
                .userId("12214")
                .nickname("nickname")
                .uniqueId("uniqueId")
                .profilePageUrl("https://www.tiktok.com/@sample_profile")
                .lastExecution(null)
                .build();
    }

    private Profile secondSampleProfile() {
        return Profile.builder()
                .id(2)
                .userId("122165")
                .nickname("nickname_2")
                .uniqueId("uniqueId_2")
                .profilePageUrl("https://www.tiktok.com/@sample_profile_second")
                .lastExecution(null)
                .build();
    }

}
