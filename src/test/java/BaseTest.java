import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import ru.rencredit.framework.managers.InitializeManager;
import ru.rencredit.framework.managers.PageManager;

import static ru.rencredit.framework.managers.DriverManager.getDriver;
import static ru.rencredit.framework.managers.InitializeManager.props;
import static ru.rencredit.framework.util.PropertyConstant.APP_URL;

public class BaseTest {
    protected PageManager app = PageManager.getPageManager();


    @BeforeClass
    public static void beforeAll() {
        InitializeManager.initFramework();
    }

    @Before
    public void beforeEach() {
        getDriver().get(props.getProperty(APP_URL));
    }

    @AfterClass
    public static void afterAll() {
        InitializeManager.quitFramework();
    }
}

