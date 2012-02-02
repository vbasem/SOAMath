/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dic;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.*;
import static org.junit.Assert.*;
/**
 *
 * @author Basem
 */
public class GuiceInjectorTest
{
    @Test
    public void test_instantiatingServiceProviderFromInjector()
    {
        
        Injector injector = Guice.createInjector(new ServiceModule());
        injector.getBinding(ServiceConfigurationInterface.class).getKey().
        ServiceProvider sp = injector.getInstance(ServiceProvider.class);
        assertEquals("fake_config", sp.getConfiguration());
    }
    
    @Test
    public void test_getCounter_incrementsStaticInjection()
    {
        Injector inj = Guice.createInjector(new AbstractModule() {

            @Override
            protected void configure()
            {
               bind(ServiceConfigurationInterface.class).to(FakeServiceConfiguration.class);
            }
        });
        
        
    }
}
