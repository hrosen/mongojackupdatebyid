package no.nils.mongoupdate;

import java.net.UnknownHostException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class ScenarioStorageTest {

    private ScenarioStorage storage;

    @Before
    public void createDb() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB db = mongoClient.getDB("junittest");
        storage = new ScenarioStorage(db);
    }

    @Test
    public void shouldUpdateNameAfterUpdate() {
        Scenario scenario = new Scenario("first");
        final String id = storage.saveNewScenario(scenario);
        scenario._id = id;
        scenario.name = "newname";
        storage.update(scenario);
        Scenario updated = storage.getScenario(id);
        assertNotNull("Stored object should have been fetched.", updated.name);
        assertEquals("The stored object should now have the name 'newname'.", updated.name, "newname");
    }
}
