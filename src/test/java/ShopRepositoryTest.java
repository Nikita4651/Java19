import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShopRepositoryTest {

    @Test
    void shouldRemoveExistingProductSuccessfully() {

        ShopRepository repository = new ShopRepository();
        Product product1 = new Product(1, "Laptop", 1000);
        Product product2 = new Product(2, "Mouse", 50);

        repository.add(product1);
        repository.add(product2);


        repository.removeById(1);


        Product[] products = repository.findAll();
        assertEquals(1, products.length);
        assertEquals(2, products[0].getId());
        assertEquals("Mouse", products[0].getTitle());
    }

    @Test
    void shouldThrowNotFoundExceptionWhenRemovingNonExistentProduct() {

        ShopRepository repository = new ShopRepository();
        Product product = new Product(1, "Laptop", 1000);
        repository.add(product);


        assertThrows(NotFoundException.class, () -> {
            repository.removeById(999);
        });


        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            repository.removeById(999);
        });
        assertEquals("Element with id: 999 not found", exception.getMessage());
    }
}
