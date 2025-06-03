package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.UserPlantWishlistDTO;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.model.UserPlantWishlist;
import com.botanicials.Botanicials.repository.UserPlantWishlistRepository;
import com.botanicials.Botanicials.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserPlantWishlistServiceTest {

    @InjectMocks
    private UserPlantWishlistService userPlantWishlistService;

    @Mock
    private UserPlantWishlistRepository userPlantWishlistRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPlantToWishlist() {
        User user = new User();
        user.setId(1L);

        UserPlantWishlist wishlist = new UserPlantWishlist();
        wishlist.setUser(user);
        wishlist.setPlantId(100L);
        wishlist.setPlantName("Monstera");
        wishlist.setImageUrl("img.jpg");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userPlantWishlistRepository.save(any(UserPlantWishlist.class))).thenReturn(wishlist);

        UserPlantWishlist result = userPlantWishlistService.addPlantToWishlist(1L, 100L, "Monstera", "img.jpg");

        assertNotNull(result);
        assertEquals("Monstera", result.getPlantName());
    }

    @Test
    void testGetAllWishlistPlants() {
        UserPlantWishlist wishlist = new UserPlantWishlist();
        wishlist.setId(1L);

        when(userPlantWishlistRepository.findAll()).thenReturn(List.of(wishlist));

        List<UserPlantWishlist> result = userPlantWishlistService.getAllWishlistPlants();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void testGetWishlistPlantById_Found() {
        UserPlantWishlist wishlist = new UserPlantWishlist();
        wishlist.setId(1L);

        when(userPlantWishlistRepository.findById(1L)).thenReturn(Optional.of(wishlist));

        UserPlantWishlist result = userPlantWishlistService.getWishlistPlantById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetWishlistPlantById_NotFound() {
        when(userPlantWishlistRepository.findById(1L)).thenReturn(Optional.empty());

        UserPlantWishlist result = userPlantWishlistService.getWishlistPlantById(1L);

        assertNull(result);
    }

    @Test
    void testGetAllWishlistPlantsByUser() {
        UserPlantWishlist wishlist = new UserPlantWishlist();
        wishlist.setPlantName("Cactus");

        when(userPlantWishlistRepository.findByUserId(1L)).thenReturn(List.of(wishlist));

        List<UserPlantWishlist> result = userPlantWishlistService.getAllWishlistPlantsByUser(1L);

        assertEquals(1, result.size());
        assertEquals("Cactus", result.get(0).getPlantName());
    }

    @Test
    void testDeletePlantFromWishlist() {
        UserPlantWishlist wishlist = new UserPlantWishlist();
        wishlist.setId(1L);

        when(userPlantWishlistRepository.findByUserIdAndPlantId(1L, 99L)).thenReturn(Optional.of(wishlist));

        userPlantWishlistService.deletePlantFromWishlist(1L, 99L);

        verify(userPlantWishlistRepository).delete(wishlist);
    }

    @Test
    void testConvertToDTO() {
        UserPlantWishlist wishlist = new UserPlantWishlist();
        wishlist.setId(1L);
        wishlist.setPlantId(88L);
        wishlist.setPlantName("Orchid");
        wishlist.setImageUrl("orchid.png");

        UserPlantWishlistDTO dto = userPlantWishlistService.convertToDTO(wishlist);

        assertEquals(1L, dto.getId());
        assertEquals("Orchid", dto.getPlantName());
    }

    @Test
    void testGetAllUserPlantWishlistDTO() {
        UserPlantWishlist wishlist = new UserPlantWishlist();
        wishlist.setPlantName("Ficus");

        when(userPlantWishlistRepository.findAll()).thenReturn(List.of(wishlist));

        List<UserPlantWishlistDTO> dtos = userPlantWishlistService.getAllUserPlantWishlistDTO();

        assertEquals(1, dtos.size());
        assertEquals("Ficus", dtos.get(0).getPlantName());
    }
}
