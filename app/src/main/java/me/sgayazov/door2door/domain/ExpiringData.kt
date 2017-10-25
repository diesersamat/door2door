package me.sgayazov.door2door.domain

/*todo we can create different classes with different expiration time values, like "2HoursExpireObject", "1DayExpireObject"
* or just move field EXPIRATION_TIME inside class and override it in its children
*/
const private val EXPIRATION_TIME = 2 * 60 * 60 * 1000 //2 hours

abstract class ExpiringData {

    //todo save timestamp to database and retrieve it again
    private val created: Long = 10

    fun isUpToDate(): Boolean {
        return System.currentTimeMillis() > created + EXPIRATION_TIME
    }
}