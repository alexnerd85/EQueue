/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.alexnerd.listeners.InitEQueueListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Popov Aleksey 2017
 */
public class ConfigJUnitTest {
    
    public ConfigJUnitTest() {
    }
    
    @Test
    public void testConfigFile() {
        String resourceName = "config.properties";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties props = new Properties();
        try (InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
            props.load(resourceStream);
        } catch (IOException ex) {
            Logger.getLogger(InitEQueueListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        assertNotNull(props);
        assertFalse(props.isEmpty());
    } 
}
