package no.nils.mongoupdate;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;
import com.mongodb.DB;
import com.mongodb.DBCollection;

class ScenarioStorage {
    private JacksonDBCollection<Scenario, String> scenarioCollection;

    public ScenarioStorage(DB db) {
        DBCollection dbCollection = db.getCollection("scenario");
        scenarioCollection = JacksonDBCollection.wrap(dbCollection, Scenario.class, String.class);
    }

    public void update(Scenario scenario) {
        WriteResult<Scenario, String> writeResult = scenarioCollection.updateById(scenario._id, scenario);
        if (writeResult.getError() != null) throw new RuntimeException(writeResult.getError());
    }

    public String saveNewScenario(Scenario scenario) {
        WriteResult<Scenario, String> writeResult = scenarioCollection.insert(scenario);
        if (writeResult.getError() != null) throw new RuntimeException(writeResult.getError());
        return writeResult.getSavedId();
    }


    public Scenario getScenario(String id) {
        return scenarioCollection.findOneById(id);
    }
}

