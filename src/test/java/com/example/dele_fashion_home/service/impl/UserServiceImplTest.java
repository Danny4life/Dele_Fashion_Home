package com.example.dele_fashion_home.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.dele_fashion_home.dto.SignUpDto;
import com.example.dele_fashion_home.enums.UserRole;
import com.example.dele_fashion_home.exceptions.UserNotFoundException;
import com.example.dele_fashion_home.exceptions.UsersAlreadyExistsException;
import com.example.dele_fashion_home.model.UserEntity;
import com.example.dele_fashion_home.repository.UsersRepository;

import java.util.ArrayList;
import javax.servlet.http.HttpSession;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private HttpSession httpSession;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @MockBean
    private UsersRepository usersRepository;

    /**
     * Method under test: {@link UserServiceImpl#signUp(SignUpDto)}
     */
    @Test
    void testSignUp() {
        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(usersRepository.existsByEmail((String) any())).thenReturn(true);
        when(usersRepository.save((UserEntity) any())).thenReturn(userEntity);
        assertThrows(UsersAlreadyExistsException.class,
                () -> userServiceImpl.signUp(new SignUpDto("jane.doe@example.org", "iloveyou", "janedoe", UserRole.ADMIN)));
        verify(usersRepository).existsByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#signUp(SignUpDto)}
     */
    @Test
    void testSignUp2() {
        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(usersRepository.existsByEmail((String) any())).thenReturn(false);
        when(usersRepository.save((UserEntity) any())).thenReturn(userEntity);
        assertSame(userEntity,
                userServiceImpl.signUp(new SignUpDto("jane.doe@example.org", "iloveyou", "janedoe", UserRole.ADMIN)));
        verify(usersRepository).existsByEmail((String) any());
        verify(usersRepository).save((UserEntity) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#signUp(SignUpDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSignUp3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.dele_fashion_home.dto.SignUpDto.getEmail()" because "signUpDto" is null
        //       at com.example.dele_fashion_home.service.impl.UserServiceImpl.signUp(UserServiceImpl.java:24)
        //   In order to prevent signUp(SignUpDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   signUp(SignUpDto).
        //   See https://diff.blue/R013 to resolve this issue.

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(usersRepository.existsByEmail((String) any())).thenReturn(true);
        when(usersRepository.save((UserEntity) any())).thenReturn(userEntity);
        userServiceImpl.signUp(null);
    }

    /**
     * Method under test: {@link UserServiceImpl#signUp(SignUpDto)}
     */
    @Test
    void testSignUp4() {
        when(usersRepository.existsByEmail((String) any())).thenThrow(new UserNotFoundException("An error occurred"));
        when(usersRepository.save((UserEntity) any())).thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class,
                () -> userServiceImpl.signUp(new SignUpDto("jane.doe@example.org", "iloveyou", "janedoe", UserRole.ADMIN)));
        verify(usersRepository).existsByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#signUp(SignUpDto)}
     */
    @Test
    void testSignUp5() {
        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(usersRepository.existsByEmail((String) any())).thenReturn(true);
        when(usersRepository.save((UserEntity) any())).thenReturn(userEntity);
        assertThrows(UsersAlreadyExistsException.class,
                () -> userServiceImpl.signUp(new SignUpDto("jane.doe@example.org", "iloveyou", "janedoe", UserRole.ADMIN)));
        verify(usersRepository).existsByEmail((String) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#signUp(SignUpDto)}
     */
    @Test
    void testSignUp6() {
        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(usersRepository.existsByEmail((String) any())).thenReturn(false);
        when(usersRepository.save((UserEntity) any())).thenReturn(userEntity);
        assertSame(userEntity,
                userServiceImpl.signUp(new SignUpDto("jane.doe@example.org", "iloveyou", "janedoe", UserRole.ADMIN)));
        verify(usersRepository).existsByEmail((String) any());
        verify(usersRepository).save((UserEntity) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#signUp(SignUpDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testSignUp7() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "com.example.dele_fashion_home.dto.SignUpDto.getEmail()" because "signUpDto" is null
        //       at com.example.dele_fashion_home.service.impl.UserServiceImpl.signUp(UserServiceImpl.java:24)
        //   In order to prevent signUp(SignUpDto)
        //   from throwing NullPointerException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   signUp(SignUpDto).
        //   See https://diff.blue/R013 to resolve this issue.

        UserEntity userEntity = new UserEntity();
        userEntity.setComments(new ArrayList<>());
        userEntity.setEmail("jane.doe@example.org");
        userEntity.setLikedItems(new ArrayList<>());
        userEntity.setPassword("iloveyou");
        userEntity.setPosts(new ArrayList<>());
        userEntity.setUserId(123L);
        userEntity.setUserName("janedoe");
        userEntity.setUserRole(UserRole.ADMIN);
        when(usersRepository.existsByEmail((String) any())).thenReturn(true);
        when(usersRepository.save((UserEntity) any())).thenReturn(userEntity);
        userServiceImpl.signUp(null);
    }

    /**
     * Method under test: {@link UserServiceImpl#signUp(SignUpDto)}
     */
    @Test
    void testSignUp8() {
        when(usersRepository.existsByEmail((String) any())).thenThrow(new UserNotFoundException("An error occurred"));
        when(usersRepository.save((UserEntity) any())).thenThrow(new UserNotFoundException("An error occurred"));
        assertThrows(UserNotFoundException.class,
                () -> userServiceImpl.signUp(new SignUpDto("jane.doe@example.org", "iloveyou", "janedoe", UserRole.ADMIN)));
        verify(usersRepository).existsByEmail((String) any());
    }
}

