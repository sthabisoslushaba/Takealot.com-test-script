package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import utility.Utils;
import static accelerators.Base.*;

public class CucumberHooks {
//    	Everything that needs to be done before every scenario is run
    @Before
    public void initialize() throws InterruptedException {
        setUp();
        String home_url = Utils.ConfigReader.getProperty("home_url");
        driver.get(home_url);
    }

//    	Everything that needs to be done after every scenario is run
    @After
    public void cleanup() {
        tearDown();
    }
}
