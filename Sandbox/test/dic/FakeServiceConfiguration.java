/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dic;

/**
 *
 * @author Basem
 */
public class FakeServiceConfiguration implements ServiceConfigurationInterface
{

    @Override
    public String getConfig()
    {
        return "fake_config";
    }
    
}
