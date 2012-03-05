
package services.arithmatic.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "calculate", namespace = "http://arithmatic.services/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "calculate", namespace = "http://arithmatic.services/", propOrder = {
    "arg0",
    "arg1"
})
public class Calculate {

    @XmlElement(name = "arg0", namespace = "")
    private Object arg0;
    @XmlElement(name = "arg1", namespace = "")
    private Object arg1;

    /**
     * 
     * @return
     *     returns Object
     */
    public Object getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Object arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns Object
     */
    public Object getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(Object arg1) {
        this.arg1 = arg1;
    }

}
