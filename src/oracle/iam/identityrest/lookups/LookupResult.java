

package oracle.iam.identityrest.lookups;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LookupResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private String key;

    @XmlElement
    private String value;

    public LookupResult() {
        // Required by JAXB
    }

    public LookupResult(String key, String val) {
        this.key = key;
        this.value = val;
    }
}
