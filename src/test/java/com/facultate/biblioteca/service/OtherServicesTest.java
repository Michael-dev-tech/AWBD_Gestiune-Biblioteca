package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Author;
import com.facultate.biblioteca.model.Category;
import com.facultate.biblioteca.model.Loan;
import com.facultate.biblioteca.model.Role;
import com.facultate.biblioteca.model.User;
import com.facultate.biblioteca.model.UserProfile;
import com.facultate.biblioteca.repository.AuthorRepository;
import com.facultate.biblioteca.repository.CategoryRepository;
import com.facultate.biblioteca.repository.LoanRepository;
import com.facultate.biblioteca.repository.RoleRepository;
import com.facultate.biblioteca.repository.UserProfileRepository;
import com.facultate.biblioteca.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OtherServicesTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProfileRepository userProfileRepository;

    @InjectMocks
    private AuthorService authorService;

    @InjectMocks
    private CategoryService categoryService;

    @InjectMocks
    private LoanService loanService;

    @InjectMocks
    private RoleService roleService;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private UserProfileService userProfileService;

    @Test
    public void testAuthorServiceCrudMethods() {
        Author author = new Author();
        author.setName("Mihail Sadoveanu");

        when(authorRepository.save(author)).thenReturn(author);
        when(authorRepository.findAll()).thenReturn(List.of(author));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(authorRepository.existsById(1L)).thenReturn(true);
        when(authorRepository.findById(99L)).thenReturn(Optional.empty());
        when(authorRepository.existsById(99L)).thenReturn(false);

        assertEquals(author, authorService.saveAuthor(author));
        assertEquals(1, authorService.getAllAuthors().size());
        assertEquals("Mihail Sadoveanu", authorService.getAuthorById(1L).getName());
        assertThrows(RuntimeException.class, () -> authorService.getAuthorById(99L));

        authorService.deleteAuthor(1L);
        verify(authorRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> authorService.deleteAuthor(99L));
    }

    @Test
    public void testCategoryServiceCrudMethods() {
        Category category = new Category();
        category.setName("Roman");

        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.existsById(1L)).thenReturn(true);
        when(categoryRepository.findById(99L)).thenReturn(Optional.empty());
        when(categoryRepository.existsById(99L)).thenReturn(false);

        assertEquals(category, categoryService.saveCategory(category));
        assertEquals(1, categoryService.getAllCategories().size());
        assertEquals("Roman", categoryService.getCategoryById(1L).getName());
        assertThrows(RuntimeException.class, () -> categoryService.getCategoryById(99L));

        categoryService.deleteCategory(1L);
        verify(categoryRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> categoryService.deleteCategory(99L));
    }

    @Test
    public void testLoanServiceCrudMethods() {
        Loan loan = new Loan();

        when(loanRepository.save(loan)).thenReturn(loan);
        when(loanRepository.findAll()).thenReturn(List.of(loan));
        when(loanRepository.findById(1L)).thenReturn(Optional.of(loan));
        when(loanRepository.existsById(1L)).thenReturn(true);
        when(loanRepository.findById(99L)).thenReturn(Optional.empty());
        when(loanRepository.existsById(99L)).thenReturn(false);

        assertEquals(loan, loanService.saveLoan(loan));
        assertEquals(1, loanService.getAllLoans().size());
        assertEquals(loan, loanService.getLoanById(1L));
        assertThrows(RuntimeException.class, () -> loanService.getLoanById(99L));

        loanService.deleteLoan(1L);
        verify(loanRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> loanService.deleteLoan(99L));
    }

    @Test
    public void testRoleServiceCrudMethods() {
        Role role = new Role();
        role.setName("ROLE_USER");

        when(roleRepository.save(role)).thenReturn(role);
        when(roleRepository.findAll()).thenReturn(List.of(role));
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(roleRepository.existsById(1L)).thenReturn(true);
        when(roleRepository.findById(99L)).thenReturn(Optional.empty());
        when(roleRepository.existsById(99L)).thenReturn(false);

        assertEquals(role, roleService.saveRole(role));
        assertEquals(1, roleService.getAllRoles().size());
        assertEquals("ROLE_USER", roleService.getRoleById(1L).getName());
        assertThrows(RuntimeException.class, () -> roleService.getRoleById(99L));

        roleService.deleteRole(1L);
        verify(roleRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> roleService.deleteRole(99L));
    }

    @Test
    public void testUserServiceCrudMethods() {
        User user = new User();
        user.setUsername("student");

        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findAll()).thenReturn(List.of(user));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.existsById(1L)).thenReturn(true);
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        when(userRepository.existsById(99L)).thenReturn(false);

        assertEquals(user, userService.saveUser(user));
        assertEquals(1, userService.getAllUsers().size());
        assertEquals("student", userService.getUserById(1L).getUsername());
        assertThrows(RuntimeException.class, () -> userService.getUserById(99L));

        userService.deleteUser(1L);
        verify(userRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> userService.deleteUser(99L));
    }

    @Test
    public void testUserProfileServiceCrudMethods() {
        UserProfile profile = new UserProfile();
        profile.setFullName("Student Test");

        when(userProfileRepository.save(profile)).thenReturn(profile);
        when(userProfileRepository.findAll()).thenReturn(List.of(profile));
        when(userProfileRepository.findById(1L)).thenReturn(Optional.of(profile));
        when(userProfileRepository.existsById(1L)).thenReturn(true);
        when(userProfileRepository.findById(99L)).thenReturn(Optional.empty());
        when(userProfileRepository.existsById(99L)).thenReturn(false);

        assertEquals(profile, userProfileService.saveUserProfile(profile));
        assertEquals(1, userProfileService.getAllUserProfiles().size());
        assertEquals("Student Test", userProfileService.getUserProfileById(1L).getFullName());
        assertThrows(RuntimeException.class, () -> userProfileService.getUserProfileById(99L));

        userProfileService.deleteUserProfile(1L);
        verify(userProfileRepository).deleteById(1L);
        assertThrows(RuntimeException.class, () -> userProfileService.deleteUserProfile(99L));
    }
}
