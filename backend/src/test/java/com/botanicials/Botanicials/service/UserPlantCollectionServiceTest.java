package com.botanicials.Botanicials.service;

import com.botanicials.Botanicials.dto.UserPlantCollectionDTO;
import com.botanicials.Botanicials.model.User;
import com.botanicials.Botanicials.model.UserPlantCollection;
import com.botanicials.Botanicials.repository.UserPlantCollectionRepository;
import com.botanicials.Botanicials.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserPlantCollectionServiceTest {

    @InjectMocks
    private UserPlantCollectionService userPlantCollectionService;

    @Mock
    private UserPlantCollectionRepository userPlantCollectionRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddPlantToCollection() {
        User user = new User();
        user.setId(1L);

        UserPlantCollection collection = new UserPlantCollection();
        collection.setUser(user);
        collection.setPlantId(10L);
        collection.setPlantName("Rose");
        collection.setImageUrl("img.jpg");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userPlantCollectionRepository.save(any(UserPlantCollection.class))).thenReturn(collection);

        UserPlantCollection result = userPlantCollectionService.addPlantToCollection(1L, 10L, "Rose", "img.jpg");

        assertNotNull(result);
        assertEquals("Rose", result.getPlantName());
    }

    @Test
    void testGetAllPlants() {
        UserPlantCollection plant = new UserPlantCollection();
        plant.setId(1L);

        when(userPlantCollectionRepository.findAll()).thenReturn(List.of(plant));

        List<UserPlantCollection> result = userPlantCollectionService.getAllPlants();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void testGetPlantById_Found() {
        UserPlantCollection plant = new UserPlantCollection();
        plant.setId(1L);

        when(userPlantCollectionRepository.findById(1L)).thenReturn(Optional.of(plant));

        UserPlantCollection result = userPlantCollectionService.getPlantById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testGetPlantById_NotFound() {
        when(userPlantCollectionRepository.findById(1L)).thenReturn(Optional.empty());

        UserPlantCollection result = userPlantCollectionService.getPlantById(1L);

        assertNull(result);
    }

    @Test
    void testDeletePlant() {
        UserPlantCollection plant = new UserPlantCollection();
        plant.setId(1L);

        when(userPlantCollectionRepository.findById(1L)).thenReturn(Optional.of(plant));

        userPlantCollectionService.deletePlant(1L);

        verify(userPlantCollectionRepository).delete(plant);
    }

    @Test
    void testConvertToDTO() {
        UserPlantCollection plant = new UserPlantCollection();
        plant.setId(1L);
        plant.setPlantId(99L);
        plant.setPlantName("Tulip");
        plant.setImageUrl("img.png");

        UserPlantCollectionDTO dto = userPlantCollectionService.convertToDTO(plant);

        assertEquals(1L, dto.getId());
        assertEquals("Tulip", dto.getPlantName());
    }

    @Test
    void testGetAllUserPlantCollectionsDTO() {
        UserPlantCollection plant = new UserPlantCollection();
        plant.setId(1L);
        plant.setPlantName("Tulip");

        when(userPlantCollectionRepository.findAll()).thenReturn(List.of(plant));

        List<UserPlantCollectionDTO> dtos = userPlantCollectionService.getAllUserPlantCollectionsDTO();

        assertEquals(1, dtos.size());
        assertEquals("Tulip", dtos.get(0).getPlantName());
    }

    @Test
    void testDeletePlantFromCollection() {
        UserPlantCollection plant = new UserPlantCollection();
        plant.setId(1L);

        when(userPlantCollectionRepository.findByUserIdAndPlantId(1L, 99L)).thenReturn(Optional.of(plant));

        userPlantCollectionService.deletePlantFromCollection(1L, 99L);

        verify(userPlantCollectionRepository).delete(plant);
    }

    @Test
    void testGetAllPlantsByUser() {
        UserPlantCollection plant = new UserPlantCollection();
        plant.setUser(new User());

        when(userPlantCollectionRepository.findAllByUserId(1L)).thenReturn(List.of(plant));

        List<UserPlantCollection> result = userPlantCollectionService.getAllPlantsByUser(1L);

        assertEquals(1, result.size());
    }
}
