package cerp

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.apache.commons.lang.builder.EqualsBuilder

@EqualsAndHashCode(includes=['name', 'versionNo'])
@ToString(includes=['name', 'versionNo'], includeNames=true, includePackage=false)
class EngineeringEarlyPhase {

    static belongsTo = [engineering:Engineering]

    String name
    String versionNo

    static constraints = {
        versionNo nullable: true , validator: { val, obj ->
            if (obj.engineering == null || obj.name == null) {
                return true
            }

            def engineeringEarlyPhase = EngineeringEarlyPhase.createCriteria().get {
                eq('engineering', obj.engineering)
                eq('name', obj.name)
                if (obj.versionNo) {
                    eq('versionNo', obj.versionNo)
                } else {
                    isNull('versionNo')
                }
            }

            if (obj.id) {  // update case
                if (engineeringEarlyPhase && obj.id != engineeringEarlyPhase.id && new EqualsBuilder().append(obj, engineeringEarlyPhase).isEquals()) {
                    return false
                } else {
                    return true
                }
            } else if (new EqualsBuilder().append(obj, engineeringEarlyPhase).isEquals()) {
                return false
            } else {
                return true
            }
        }
    }
}
