import converter.DefaultConverter;
import domain.Host;
import domain.pets.Husky;
import org.junit.Test;

public class ConvertTest {

    @Test
    public void convertTest() {
        DefaultConverter defaultConverter = new DefaultConverter(new Husky(), Host.class);
        defaultConverter.convertTo();
    }
}
