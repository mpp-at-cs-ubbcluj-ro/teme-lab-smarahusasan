import model.ComputerRepairRequest;
import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComputerRepairRequestTest {
    @Test
    @DisplayName("First Test")
    public void test1() {
        ComputerRepairRequest request1 = new ComputerRepairRequest(
                1,
                "Ion Popescu",
                "Strada Mihai Eminescu, nr. 10, București",
                "0723-456-789",
                "Dell XPS 15",
                "2025-03-03",
                "Ecranul nu se aprinde"
        );
        ComputerRepairRequest request2 = new ComputerRepairRequest(
                2,
                "Maria Ionescu",
                "Bd. Unirii, nr. 25, Cluj-Napoca",
                "0741-987-654",
                "MacBook Pro 16\"",
                "2025-03-02",
                "Ventilatorul face zgomot puternic"
        );

        assertEquals(request1.getID(),1);
        assertEquals(request2.getID() ,2);

        assertEquals(request1.getOwnerName(),"Ion Popescu");
        assertEquals(request2.getOwnerName(),"Maria Ionescu");

        assertEquals(request1.getOwnerAddress(),"Strada Mihai Eminescu, nr. 10, București");
        assertEquals(request2.getOwnerAddress(),"Bd. Unirii, nr. 25, Cluj-Napoca");

        assertEquals(request1.getPhoneNumber(),"0723-456-789");
        assertEquals(request2.getPhoneNumber(),"0741-987-654");

        assertEquals(request1.getModel(),"Dell XPS 15");
        assertEquals(request2.getModel(),"MacBook Pro 16\"");

        assertEquals(request1.getDate(),"2025-03-03");
        assertEquals(request2.getDate(),"2025-03-02");

        assertEquals(request1.getProblemDescription(),"Ecranul nu se aprinde");
        assertEquals(request2.getProblemDescription(),"Ventilatorul face zgomot puternic");
    }

    @Test
    @DisplayName("Second test")
    public void test2() {
        ComputerRepairRequest request1 = new ComputerRepairRequest();
        assertEquals(request1.getID(), 0);
        assertEquals(request1.getOwnerName(), "");
        assertEquals(request1.getOwnerAddress(), "");
        assertEquals(request1.getPhoneNumber(), "");
        assertEquals(request1.getModel(), "");
        assertEquals(request1.getDate(), "");
        assertEquals(request1.getProblemDescription(), "");
    }
}
