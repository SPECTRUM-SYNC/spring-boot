package sync.spctrum.apispring.service.usuario.autenticacao.dto;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class UsuarioLoginGoogleDTOTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link UsuarioLoginGoogleDTO#getEmail()}
     *   <li>{@link UsuarioLoginGoogleDTO#getNome()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        UsuarioLoginGoogleDTO usuarioLoginGoogleDTO = new UsuarioLoginGoogleDTO();

        // Act
        String actualEmail = usuarioLoginGoogleDTO.getEmail();

        // Assert
        assertNull(actualEmail);
        assertNull(usuarioLoginGoogleDTO.getNome());
    }
}
