import converter.DefaultConverter;
import domain.pets.Husky;
import org.junit.Test;

public class ConvertTest {

    @Test
    public void convertTest() {
        DefaultConverter defaultConverter = new DefaultConverter(new Husky(), Husky.class);
        defaultConverter.convertTo();
    }
}
