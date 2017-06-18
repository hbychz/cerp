package cerp

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes=['name'])
@ToString(includes=['name'], includeNames=true, includePackage=false)
class Engineering {

    static hasMany = [engineeringEarlyPhaseSet: EngineeringEarlyPhase]
    static hasOne = [engineeringExecutionPhase: EngineeringExecutionPhase]

    String name
    EngineeringOwner engineeringOwner
    Address address
    EngineeringType engineeringType

    static embedded = ['address']

    static constraints = {
        name blank: false, unique: true

        engineeringExecutionPhase nullable: true
        engineeringOwner nullable: true
        engineeringType nullable: true
    }
}
