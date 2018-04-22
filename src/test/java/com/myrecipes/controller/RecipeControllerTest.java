package com.myrecipes.controller;

import com.myrecipes.exception.RecipeNotFoundException;
import com.myrecipes.model.Category;
import com.myrecipes.model.Recipe;
import com.myrecipes.service.RecipeService;
import com.myrecipes.core.web.FlashMessage;
import com.myrecipes.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.myrecipes.data.TestData.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class RecipeControllerTest
{
    private MockMvc mockMvc;

    @InjectMocks
    private RecipeController controller;

    @Mock
    private RecipeService recipeService;

    @Mock
    private UserService userService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void home_ShouldRenderIndexView() throws Exception
    {
        List<Recipe> recipes = recipeList();
        when(recipeService.findAll()).thenReturn(recipes);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/index"))
                .andExpect(model().attribute("recipes", recipes));
        verify(recipeService).findAll();
    }

    @Test
    public void add_ShouldReturnAddForm() throws Exception
    {
        mockMvc.perform(get("/recipes/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/form"))
                .andExpect(model().attribute("recipe", new Recipe()));
    }

    @Test
    public void add_ShouldRedirectToIndex() throws Exception
    {
        mockMvc.perform(
                fileUpload("/recipes")
                        .file(new MockMultipartFile("file", "test".getBytes()))
                        .param("name", "Tacos")
                        .param("description", "Alannas Tacos")
                        .param("category", "DINNER")
                        .param("prepTime", "15")
                        .param("cookTime", "20")
                        .param("ingredients[0].id", "1")
                        .param("ingredients[0].item", "Black Beans")
                        .param("ingredients[0].condition", "Fresh")
                        .param("ingredients[0].quantity", "1 Can")
                        .param("directions[0].id", "1")
                        .param("directions[0].directionName", "direction 1")
        )
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))
                .andExpect(redirectedUrl("/"))
                .andExpect(status().is3xxRedirection());
        verify(recipeService).save(any(Recipe.class), any(MultipartFile.class));
    }

    @Test
    public void add_ShouldFailForInvalidData() throws Exception
    {
        mockMvc.perform(
                fileUpload("/recipes")
                        .file(new MockMultipartFile("file", "test".getBytes()))
                        .param("name", "")
        )
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.FAILURE))))
                .andExpect(redirectedUrl("/recipes/add"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void detail_ShouldReturnRecipe() throws Exception
    {
        Recipe recipe = recipe1();
        recipe.setId(1L);
        when(recipeService.findById(1L)).thenReturn(recipe);

        mockMvc.perform(get("/recipes/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/detail"))
                .andExpect(model().attribute("recipe", recipe));
        verify(recipeService).findById(1L);
    }

    @Test
    public void detail_ShouldReturnNotFoundIfIDNotExist() throws Exception
    {
        when(recipeService.findById(1L)).thenThrow(new RecipeNotFoundException("No recipe with id 1 was found"));

        mockMvc.perform(get("/recipes/1"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void gifImage_ShouldReturnImage() throws Exception
    {
        Recipe recipe = recipe1();
        recipe.setImage("test".getBytes());
        when(recipeService.findById(1L)).thenReturn(recipe);

        mockMvc.perform(get("/recipes/1.png"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/octet-stream"))
                .andExpect(content().bytes("test".getBytes()));
    }

    @Test
    public void edit_ShouldReturnEditForm() throws Exception
    {
        Recipe recipe = recipe1();
        recipe.setId(1L);

        when(recipeService.findById(1L)).thenReturn(recipe);

        mockMvc.perform(get("/recipes/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/form"))
                .andExpect(model().attribute("recipe", recipe));
    }

    @Test
    public void edit_ShouldRedirectToDetails() throws Exception
    {
        Recipe recipe = recipe1();
        recipe.setId(1L);

        when(recipeService.findById(1L)).thenReturn(recipe);

        mockMvc.perform(
                fileUpload("/recipes/1")
                        .file(new MockMultipartFile("file", "test".getBytes()))
                        .param("id", "1")
                        .param("name", "Eggs Over Rice")
                        .param("description", "Lightly seasoned eggs, sunny side up, and diced tomato over lightly sugared rice")
                        .param("category", "BREAKFAST")
                        .param("prepTime", "5")
                        .param("cookTime", "10")
                        .param("ingredients[0].id", "1")
                        .param("ingredients[0].item", "Eggs")
                        .param("ingredients[0].condition", "Fresh")
                        .param("ingredients[0].quantity", "~2")
                        .param("directions[0].id", "1")
                        .param("directions[0].directionName", "direction 1")
        )
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))
                .andExpect(redirectedUrl("/recipes/1"))
                .andExpect(status().is3xxRedirection());
        verify(recipeService).save(any(Recipe.class), any(MultipartFile.class));
    }

    @Test
    public void edit_ShouldFailForInvalidData() throws Exception
    {
        Recipe recipe = recipe1();
        recipe.setId(1L);

        when(recipeService.findById(1L)).thenReturn(recipe);

        mockMvc.perform(
                fileUpload("/recipes/1")
                        .file(new MockMultipartFile("file", "test".getBytes()))
                        .param("id", "1")
                        .param("name", "")
        )
                .andExpect(redirectedUrl("/recipes/1/edit"))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.FAILURE))))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void delete_ShouldRedirectToIndex() throws Exception
    {
        mockMvc.perform(get("/recipes/1/delete"))
                .andExpect(flash().attribute("flash", hasProperty("status", is(FlashMessage.Status.SUCCESS))))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
        verify(recipeService).delete(1L);
    }

    @Test
    public void delete_ShouldReturnNotFoundIfIDNotExist() throws Exception
    {
        doThrow(new RecipeNotFoundException("No recipe with id 1 was found")).when(recipeService).delete(1L);

        mockMvc.perform(get("/recipes/1/delete"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void favoriteRecipe_togglesFavorite() throws Exception
    {
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>(
                Arrays.asList(new SimpleGrantedAuthority("STANDARD")));
        Authentication auth = new UsernamePasswordAuthenticationToken("Harry", "password", list);
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(userService.findByUsername("Harry")).thenReturn(user1());
        when(recipeService.findById(1L)).thenReturn(recipe1());

        mockMvc.perform(get("/recipes/1/favorite")
                .principal(auth))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/recipes/1"));

        verify(userService).toggleFavorite(user1(), recipe1());
    }

    @Test
    public void searchByDescription_ShouldReturnOneRecipe() throws Exception
    {
        List<Recipe> searchResult = new ArrayList<>(Arrays.asList(recipe2()));
        when(recipeService.findByDescription("rice")).thenReturn(searchResult);

        mockMvc.perform(get("/recipes/search?description=rice"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/index"))
                .andExpect(model().attribute("recipes", searchResult));

        verify(recipeService).findByDescription("rice");
    }

    @Test
    public void searchByIngredient_ShouldReturnOneRecipe() throws Exception
    {
        List<Recipe> searchResult = new ArrayList<>(Arrays.asList(recipe1()));
        when(recipeService.findByIngredient("eggs")).thenReturn(searchResult);

        mockMvc.perform(get("/recipes/search?ingredient=eggs"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/index"))
                .andExpect(model().attribute("recipes", searchResult));

        verify(recipeService).findByIngredient("eggs");
    }

    @Test
    public void filterByCategory_ShouldReturnOneRecipe() throws Exception
    {
        List<Recipe> filteredResult = new ArrayList<>(Arrays.asList(recipe1()));
        when(recipeService.findByCategory("lunch")).thenReturn(filteredResult);

        mockMvc.perform(get("/recipes/category/lunch"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/index"))
                .andExpect(model().attribute("selectedCategory", Category.LUNCH))
                .andExpect(model().attribute("recipes", filteredResult));

        verify(recipeService).findByCategory("lunch");
    }
}