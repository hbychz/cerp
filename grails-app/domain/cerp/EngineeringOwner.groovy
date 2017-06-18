package cerp

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes=['name'])
@ToString(includes=['name'], includeNames=true, includePackage=false)
class EngineeringOwner {

    String name

    static constraints = {
        name blank: false, unique: true
    }
}
