
package org.soa.math.dispatch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for add complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="add">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operand1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operand2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "add", propOrder = {
    "operand1",
    "operand2"
})
public class Add {

    protected String operand1;
    protected String operand2;

    /**
     * Gets the value of the operand1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperand1() {
        return operand1;
    }

    /**
     * Sets the value of the operand1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperand1(String value) {
        this.operand1 = value;
    }

    /**
     * Gets the value of the operand2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperand2() {
        return operand2;
    }

    /**
     * Sets the value of the operand2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperand2(String value) {
        this.operand2 = value;
    }

}
