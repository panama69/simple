package net.hpe;

import static java.lang.System.*;
import static org.junit.Assert.*;

import com.hp.lft.sdk.internal.web.WebTable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.hp.lft.sdk.*;
import com.hp.lft.sdk.web.*;


import com.hp.lft.verifications.*;

import unittesting.*;

import java.net.URI;
import java.util.List;

public class LeanFtTest extends UnitTestClassBase {

    public LeanFtTest() throws Exception {
        //Change this constructor to private if you supply your own public constructor
        ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
        String serverAddress = System.getProperty("serverAddress");
        if (!(serverAddress == null)) {
            config.setServerAddress(URI.create(System.getProperty("serverAddress")));
        }
        SDK.init(config);
        System.out.println("Executing against: "+config.getServerAddress().toString());
    }


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        instance = new LeanFtTest();
        globalSetup(LeanFtTest.class);

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        globalTearDown();
    }

    @Before
    public void setUp() throws Exception {
        //ModifiableSDKConfiguration config = new ModifiableSDKConfiguration();
        //config.setServerAddress(URI.create(System.getProperty("serverAddress")));
        //SDK.init(config);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws GeneralLeanFtException, InterruptedException {
        Browser browser = BrowserFactory.launch(BrowserType.CHROME);
        browser.navigate("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_table_insertrow");

        //Browser browser = BrowserFactory.attach(new BrowserDescription.Builder().openURL("https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_table_insertrow").build());

        //net.hpe.ApplicationModel appModel = new net.hpe.ApplicationModel(browser);

        Button tryIt = browser.describe(Frame.class, new FrameDescription.Builder()
                .name("iframeResult").build())
                .describe(Button.class, new ButtonDescription.Builder()
                        .buttonType("submit")
                        .name("Try it")
                        .tagName("BUTTON").build());

        //Table table = appModel.ModifiedTable();
        Table table = browser.describe(Frame.class, new FrameDescription.Builder()
                .name("iframeResult").build())
                .describe(Table.class, new TableDescription.Builder()
                        .tagName("TABLE").build());

        String value = "";
        for (int x=0; x<10; x++){
            tryIt.click();
            int lastRow = table.getRows().size()-1;
            value = table.getRows().get(lastRow).getCells().get(0).getText();

            //value = browser.describe(WebElement.class, new WebElementDescription.Builder()
            //        .xpath("//TR[last()]/TD[1]").build()).getInnerText();

            out.println("Total Rows: "+lastRow+" Value of last row: "+ value);
            Thread.sleep(2);
        }
        browser.close();

    }

}