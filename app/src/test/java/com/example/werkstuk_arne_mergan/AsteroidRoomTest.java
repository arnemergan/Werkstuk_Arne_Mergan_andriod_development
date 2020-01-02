package com.example.werkstuk_arne_mergan;
import com.example.werkstuk_arne_mergan.repositories.DataSingleton;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class AsteroidRoomTest {
    @Test
    public void check_if_string(){
        assertEquals(String.class,DataSingleton.getInstance().downloadPlainText("https://api.nasa.gov/neo/rest/v1/feed?start_date=2015-09-07&end_date=2015-09-08&api_key=16Y4RrkQMXVR6yfSVeiaejNKkIq3pK2o7dgRrz1c").getClass());
    }
}
