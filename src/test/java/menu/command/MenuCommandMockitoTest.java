package menu.command;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import service.FlowerShop;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MenuCommandMockitoTest {
    FlowerShop service = Mockito.mock(FlowerShop.class);
    @Test
    void viewCatalogCommand_callsServiceOnce() {
        // FlowerShop service = Mockito.mock(FlowerShop.class);
        ViewCatalog cmd = new ViewCatalog(service);

        cmd.execute();

        verify(service, times(1)).viewCatalog();
        verifyNoMoreInteractions(service);
        assertEquals("Переглянути", cmd.getName());
    }

    @Test
    void addFlowerCommand_callsServiceOnce() {
       // FlowerShop service = Mockito.mock(FlowerShop.class);
        AddFlowerToCatalog cmd = new AddFlowerToCatalog(service);

        cmd.execute();

        verify(service, times(1)).addFlowerToCatalog();
        verifyNoMoreInteractions(service);
        assertEquals("Додати", cmd.getName());
    }

    @Test
    void createBouquetCommand_callsServiceOnce() {
        //FlowerShop service = Mockito.mock(FlowerShop.class);
        CreateBouquet cmd = new CreateBouquet(service);

        cmd.execute();

        verify(service, times(1)).createBouquet();
        verifyNoMoreInteractions(service);
        assertEquals("Створити", cmd.getName());
    }

    @Test
    void viewAllBouquetsCommand_callsServiceOnce() {
       // FlowerShop service = Mockito.mock(FlowerShop.class);
        ViewAllBouquets cmd = new ViewAllBouquets(service);

        cmd.execute();

        verify(service, times(1)).viewAllBouquets();
        verifyNoMoreInteractions(service);
        assertEquals("Переглянути все", cmd.getName());
    }

    @Test
    void editBouquetCommand_callsServiceOnce() {
       // FlowerShop service = Mockito.mock(FlowerShop.class);
        EditBouquet cmd = new EditBouquet(service);

        cmd.execute();

        verify(service, times(1)).editBouquet();
        verifyNoMoreInteractions(service);
        assertEquals("Змінити", cmd.getName());
    }

    @Test
    void sortInBouquetCommand_callsServiceOnce() {
       // FlowerShop service = Mockito.mock(FlowerShop.class);
        SortInBouquet cmd = new SortInBouquet(service);

        cmd.execute();

        verify(service, times(1)).sortBouquet();
        verifyNoMoreInteractions(service);
        assertEquals("Відсортувати", cmd.getName());
    }

    @Test
    void findFlowerInBouquetCommand_callsServiceOnce() {
      //  FlowerShop service = Mockito.mock(FlowerShop.class);
        FindFlowerInBouquet cmd = new FindFlowerInBouquet(service);

        cmd.execute();

        verify(service, times(1)).findFlowerInBouquet();
        verifyNoMoreInteractions(service);
        assertEquals("Знайти квітку", cmd.getName());
    }

    @Test
    void saveAndExitCommand_callsServiceOnce() {
       // FlowerShop service = Mockito.mock(FlowerShop.class);
        SaveAndExit cmd = new SaveAndExit(service);

        cmd.execute();

        verify(service, times(1)).saveCatalogToFile();
        verifyNoMoreInteractions(service);
        assertEquals("Зберегти", cmd.getName());
    }

    @Test
    void deleteFlowerCommand_callsServiceOnce() {
       // FlowerShop service = Mockito.mock(FlowerShop.class);
        DeleteFlowerFromCatalog cmd = new DeleteFlowerFromCatalog(service);

        cmd.execute();

        verify(service, times(1)).deleteFlowerFromCatalog();
        verifyNoMoreInteractions(service);
        assertEquals("Видалити", cmd.getName());
    }
}
