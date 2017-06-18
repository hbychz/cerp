package cerp

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes=['type1', 'type2'])
@ToString(includes=['type1', 'type2'], includeNames=true, includePackage=false)
class EngineeringType {

    String type1
    String type2

    static constraints = {
        type2 blank: false, unique: 'type1'
    }
}
