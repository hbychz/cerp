package cerp

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes=['country', 'province', 'city', 'district', 'street'])
@ToString(includes=['country', 'province', 'city', 'district', 'street'], includeNames=true, includePackage=false)
class Address {

    String country
    String province
    String city
    String district
    String street

    Float lat
    Float lng

    static constraints = {
        district nullable: true
        street nullable: true
        lat nullable: true
        lng nullable: true

        province nullable: true
        city nullable: true
    }
}
