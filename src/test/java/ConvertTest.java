import converter.DefaultConverter;
import domain.Host;
import domain.HouseHost;
import domain.pets.Husky;
import org.junit.Assert;
import org.junit.Test;

public class ConvertTest {

    @Test
    public void convertTest() {
        DefaultConverter defaultConverter = new DefaultConverter(new Husky(), HouseHost.class);
        Host host = defaultConverter.convertTo();
        Assert.assertEquals("withing", host.getName());
        Assert.assertEquals("jason", host.getPetName());
    }
}
