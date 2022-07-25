package com.example.dele_fashion_home.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.dele_fashion_home.dto.LoginDto;
import com.example.dele_fashion_home.dto.SignUpDto;
import com.example.dele_fashion_home.enums.UserRole;
import com.example.dele_fashion_home.model.UserEntity;
import com.example.dele_fashion_home.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Autowired
    private UserController userController;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link UserController#logout()}
     */
    @Test
    void testLogout() throws Exception {
        when(userService.logout()).thenReturn("Logout");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/log-out");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Logout"));
    }

    /**
     * Method under test: {@link UserController#logout()}
     */
    @Test
    void testLogout2() throws Exception {
        when(userService.logout()).thenReturn("Logout");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/log-out");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Logout"));
    }

    /**
     * Method under test: {@link UserController#logout()}
     */
    @Test
    void testLogout3() throws Exception {
        when(userService.logout()).thenReturn("Logout");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/log-out");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Logout"));
    }

    /**
     * Method under test: {@link UserController#logout()}
     */
    @Test
    void testLogout4() throws Exception {
        when(userService.logout()).thenReturn("Logout");
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/users/log-out");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Logout"));
    }

    /**
     * Method under test: {@link UserController#userLogin(LoginDto)}
     */
    @Test
    void testUserLogin() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(userService.login((LoginDto) any())).thenReturn(userEntity);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("jane.doe@example.org");
        loginDto.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(loginDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"userName\":\"janedoe\",\"userRole\":"
                                        + "\"ADMIN\",\"posts\":[],\"comments\":[],\"likedItems\":[]}"));
    }

    /**
     * Method under test: {@link UserController#userLogin(LoginDto)}
     */
    @Test
    void testUserLogin2() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(userService.login((LoginDto) any())).thenReturn(userEntity);

        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("jane.doe@example.org");
        loginDto.setPassword("iloveyou");
        String content = (new ObjectMapper()).writeValueAsString(loginDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"userName\":\"janedoe\",\"userRole\":"
                                        + "\"ADMIN\",\"posts\":[],\"comments\":[],\"likedItems\":[]}"));
    }

    /**
     * Method under test: {@link UserController#userSignUp(SignUpDto)}
     */
    @Test
    void testUserSignUp() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(userService.signUp((SignUpDto) any())).thenReturn(userEntity);

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setEmail("jane.doe@example.org");
        signUpDto.setPassword("iloveyou");
        signUpDto.setUserName("janedoe");
        signUpDto.setUserRole(UserRole.ADMIN);
        String content = (new ObjectMapper()).writeValueAsString(signUpDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"userName\":\"janedoe\",\"userRole\":"
                                        + "\"ADMIN\",\"posts\":[],\"comments\":[],\"likedItems\":[]}"));
    }

    /**
     * Method under test: {@link UserController#userSignUp(SignUpDto)}
     */
    @Test
    void testUserSignUp2() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(userService.signUp((SignUpDto) any())).thenReturn(userEntity);

        SignUpDto signUpDto = new SignUpDto();
        signUpDto.setEmail("jane.doe@example.org");
        signUpDto.setPassword("iloveyou");
        signUpDto.setUserName("janedoe");
        signUpDto.setUserRole(UserRole.ADMIN);
        String content = (new ObjectMapper()).writeValueAsString(signUpDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/sign_up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":123,\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"userName\":\"janedoe\",\"userRole\":"
                                        + "\"ADMIN\",\"posts\":[],\"comments\":[],\"likedItems\":[]}"));
    }
}

