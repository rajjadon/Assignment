package com.example.assignment.data.model

import com.example.assignment.data.remote.NetworkingConstant.AGE
import com.example.assignment.data.remote.NetworkingConstant.CITY
import com.example.assignment.data.remote.NetworkingConstant.CONTACT_NAME
import com.example.assignment.data.remote.NetworkingConstant.CONTACT_VALUE
import com.example.assignment.data.remote.NetworkingConstant.COORDINATES
import com.example.assignment.data.remote.NetworkingConstant.COUNTRY
import com.example.assignment.data.remote.NetworkingConstant.DESCRIPTION
import com.example.assignment.data.remote.NetworkingConstant.DOB
import com.example.assignment.data.remote.NetworkingConstant.DOB_DATE
import com.example.assignment.data.remote.NetworkingConstant.EMAIL
import com.example.assignment.data.remote.NetworkingConstant.FIRST_NAME
import com.example.assignment.data.remote.NetworkingConstant.GENDER
import com.example.assignment.data.remote.NetworkingConstant.LAST_NAME
import com.example.assignment.data.remote.NetworkingConstant.LATITUDE
import com.example.assignment.data.remote.NetworkingConstant.LOCATION
import com.example.assignment.data.remote.NetworkingConstant.LOGIN
import com.example.assignment.data.remote.NetworkingConstant.LONGITUDE
import com.example.assignment.data.remote.NetworkingConstant.MD5
import com.example.assignment.data.remote.NetworkingConstant.MOBILE
import com.example.assignment.data.remote.NetworkingConstant.NAME
import com.example.assignment.data.remote.NetworkingConstant.NAME_TITTLE
import com.example.assignment.data.remote.NetworkingConstant.NAT
import com.example.assignment.data.remote.NetworkingConstant.OFF_SET
import com.example.assignment.data.remote.NetworkingConstant.PASSWORD
import com.example.assignment.data.remote.NetworkingConstant.PHONE
import com.example.assignment.data.remote.NetworkingConstant.POST_CODE
import com.example.assignment.data.remote.NetworkingConstant.SALT
import com.example.assignment.data.remote.NetworkingConstant.SHA1
import com.example.assignment.data.remote.NetworkingConstant.SHA1256
import com.example.assignment.data.remote.NetworkingConstant.STATE
import com.example.assignment.data.remote.NetworkingConstant.STREET
import com.example.assignment.data.remote.NetworkingConstant.STREET_NAME
import com.example.assignment.data.remote.NetworkingConstant.STREET_NUMBER
import com.example.assignment.data.remote.NetworkingConstant.TIME_ZONE
import com.example.assignment.data.remote.NetworkingConstant.USER_ID
import com.example.assignment.data.remote.NetworkingConstant.USER_NAME
import com.example.assignment.data.remote.NetworkingConstant.USER_PICTURE
import com.example.assignment.data.remote.NetworkingConstant.USER_PICTURE_LARGE
import com.example.assignment.data.remote.NetworkingConstant.USER_PICTURE_MEDIUM
import com.example.assignment.data.remote.NetworkingConstant.USER_PICTURE_THUMBNAIL
import com.example.assignment.data.remote.NetworkingConstant.USER_REGISTERED
import com.example.assignment.data.remote.NetworkingConstant.UUID
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = GENDER)
    var gender: String = "",
    var requestTittle: String = "",
    var colorId: Int = 0,
    @Json(name = MOBILE)
    var mobile: String = "",
    @Json(name = DOB)
    var birthdayInfo: BirthdayInfo,
    @Json(name = EMAIL)
    var email: String = "",
    @Json(name = USER_ID)
    var userIdDetails: UserIdDetails,
    @Json(name = LOCATION)
    var personLocation: PersonLocation,
    @Json(name = LOGIN)
    var personLogin: Login,
    @Json(name = NAME)
    var personName: PersonName,
    @Json(name = NAT)
    var nat: String,
    @Json(name = PHONE)
    var phone: String,
    @Json(name = USER_PICTURE)
    var userPicture: UserPicture,
    @Json(name = USER_REGISTERED)
    var userRegisteredDetails: UserRegisteredDetails
)

data class BirthdayInfo(
    @Json(name = AGE)
    var age: Int,
    @Json(name = DOB_DATE)
    var dobDate: String
)

data class UserIdDetails(
    @Json(name = CONTACT_NAME)
    var name: String,
    @Json(name = CONTACT_VALUE)
    var value: String
)

data class PersonLocation(
    @Json(name = CITY)
    var city: String,
    @Json(name = COORDINATES)
    var coordinates: Coordinates,
    @Json(name = COUNTRY)
    var country: String,
    @Json(name = POST_CODE)
    var postcode: Int,
    @Json(name = STATE)
    var state: String,
    @Json(name = STREET)
    var street: Street,
    @Json(name = TIME_ZONE)
    var timezone: Timezone
)

data class Login(
    @Json(name = MD5)
    var md5: String,
    @Json(name = PASSWORD)
    var password: String,
    @Json(name = SALT)
    var salt: String,
    @Json(name = SHA1)
    var sha1: String,
    @Json(name = SHA1256)
    var sha256: String,
    @Json(name = USER_NAME)
    var username: String,
    @Json(name = UUID)
    var uuid: String
)

data class PersonName(
    @Json(name = FIRST_NAME)
    var firstName: String,
    @Json(name = LAST_NAME)
    var lastName: String,
    @Json(name = NAME_TITTLE)
    var nameTitle: String
)

data class UserPicture(
    @Json(name = USER_PICTURE_LARGE)
    var large: String,
    @Json(name = USER_PICTURE_MEDIUM)
    var medium: String,
    @Json(name = USER_PICTURE_THUMBNAIL)
    var thumbnail: String
)

data class UserRegisteredDetails(
    @Json(name = AGE)
    var registrationAge: Int,
    @Json(name = DOB_DATE)
    var registrationDate: String
)

data class Coordinates(
    @Json(name = LATITUDE)
    var latitude: String,
    @Json(name = LONGITUDE)
    var longitude: String
)

data class Street(
    @Json(name = STREET_NAME)
    var name: String,
    @Json(name = STREET_NUMBER)
    var number: Int
)

data class Timezone(
    @Json(name = DESCRIPTION)
    var description: String,
    @Json(name = OFF_SET)
    var offset: String
)