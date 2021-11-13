package com.example.assignment.data.remote.mapper

import android.content.Context
import com.example.assignment.common.AppConstant.APP_DATE_FORMAT
import com.example.assignment.common.AppConstant.APP_SERVER_DATE_FORMAT
import com.example.assignment.common.utills.getAppDateFromServerDate
import com.example.assignment.data.local.LocalDao
import com.example.assignment.data.model.BaseResponse
import com.example.assignment.data.model.Person
import com.example.assignment.data.model.PersonLocal
import com.example.assignment.data.remote.apiHelper.EntityMapper
import dagger.hilt.android.qualifiers.ApplicationContext
import java.text.SimpleDateFormat
import javax.inject.Inject
import javax.inject.Named

class PersonListMapper @Inject constructor(
    @ApplicationContext private val context: Context,
    @Named(value = APP_SERVER_DATE_FORMAT)
    private val serverDateFormat: SimpleDateFormat,
    @Named(value = APP_DATE_FORMAT)
    private val appDateFormat: SimpleDateFormat,
    private val localDao: LocalDao
) : EntityMapper<BaseResponse<List<Person>>, BaseResponse<List<PersonLocal>>> {
    override suspend fun mapFromEntity(entity: BaseResponse<List<Person>>): BaseResponse<List<PersonLocal>> {

        val personLocalList: MutableList<PersonLocal> = emptyList<PersonLocal>().toMutableList()

        localDao.deleteAll()
        entity.data?.let {

            it.map { person ->

                person.birthdayInfo.dobDate = getAppDateFromServerDate(
                    person.birthdayInfo.dobDate,
                    appDateFormat,
                    serverDateFormat
                )

                person.personName.apply {
                    person.personName.nameTitle = "$nameTitle $firstName $lastName"
                }

                person.personLocation.apply {
                    city = "$city $state $country"
                }

                person.userRegisteredDetails.registrationDate = getAppDateFromServerDate(
                    person.userRegisteredDetails.registrationDate,
                    appDateFormat,
                    serverDateFormat
                )

                val personLocal = PersonLocal().apply {
                    userPicture = person.userPicture.large
                    nameWithGender = person.personName.nameTitle
                    location = person.personLocation.city
                    age = "${person.birthdayInfo.age}, yrs"
                }
                localDao.insert(personLocal)
                personLocalList.add(personLocal)
            }
        }

        return BaseResponse(

            data = personLocalList
        )
    }

    override suspend fun mapToEntity(domainModel: BaseResponse<List<PersonLocal>>): BaseResponse<List<Person>> {
        TODO("Not yet implemented")
    }
}